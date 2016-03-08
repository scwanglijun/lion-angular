/*
 * Copyright (c)  2014, NewTouch
 * All rights reserved. 
 *
 * $id: GroupController.java 9552 2014-3-31 上午11:01:46 WangLijun$
 */
package com.newtouch.lion.admin.web.controller.system.group;

import com.newtouch.lion.admin.web.model.system.auth.AuthModel;
import com.newtouch.lion.admin.web.model.system.group.GroupVo;
import com.newtouch.lion.common.date.DateUtil;
import com.newtouch.lion.common.file.FileUtil;
import com.newtouch.lion.data.DataTable;
import com.newtouch.lion.model.datagrid.DataGrid;
import com.newtouch.lion.model.system.*;
import com.newtouch.lion.page.PageResult;
import com.newtouch.lion.query.QueryCriteria;
import com.newtouch.lion.service.datagrid.DataGridService;
import com.newtouch.lion.service.excel.ExcelExportService;
import com.newtouch.lion.service.system.GroupService;
import com.newtouch.lion.service.system.RoleService;
import com.newtouch.lion.service.system.UserService;
import com.newtouch.lion.web.constant.ConstantMessage;
import com.newtouch.lion.web.controller.AbstractController;
import com.newtouch.lion.web.model.QueryDt;
import com.newtouch.lion.web.servlet.view.support.BindMessage;
import com.newtouch.lion.web.servlet.view.support.BindResult;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * Title: 用户组管理
 * </p>
 * <p>
 * Description: 用户组管理包含添加、删除、修改及用户组授权等
 * </p>
 * <p>
 * Copyright: Copyright (c) 2014
 * </p>
 * <p>
 * Company: NewTouch
 * </p>
 * 
 * @author WangLijun
 * @version 1.0
 */
@Controller
@RequestMapping("/system/group/")
public class GroupController extends AbstractController{

	private final Logger logger = LoggerFactory.getLogger(super.getClass());
	/** 默认排序字段名称 */
	private static final String DEFAULT_ORDER_FILED_NAME = "id";
	/** 默认列表 */
	public static final String INDEX_LISTS_TB = "sys_group_list_tb";
	/** 用户组管理首页 */
	public static final String INDEX_RETURN = "/system/group/index";
	/** 添加页面 */
	public static final String ADD_DIALOG_RETURN = "/system/group/adddialog";
	/** 编辑页面 */
	public static final String EDIT_DIALOG_RETURN = "/system/group/editdialog";
	/** 授权页面 */
	public static final String AUTH_DIALOG_RETURN = "/system/group/authdialog";

	@Autowired
	private GroupService groupService;
	@Autowired
	private UserService userService;
	@Autowired
	private RoleService roleService;
	/**DataGrid表格*/
	@Autowired
	private DataGridService dataGridService;
	/**Excel通用导出*/
	@Autowired
	private ExcelExportService excelExportService;

	/** 用户组数据添加对话框 */
	@RequestMapping(value = "adddialog")
	public String addDialog() {
		return ADD_DIALOG_RETURN;
	}

	/** 用户组数据添加保存 */
	@RequestMapping(value = "add")
	@ResponseBody
	public ModelAndView add(@Valid @ModelAttribute("group") GroupVo groupVo,
			Errors errors, ModelAndView modelAndView) {

		if (!errors.hasErrors()&& this.isExistByNameEn(groupVo.getNameEn())) {
			errors.rejectValue(GroupVo.NAMEEN,
					"sys.group.form.nameen.existed.message",
					new Object[] { groupVo.getNameEn() }, null);
		}
		//是否错误消息
		if (errors.hasErrors()) {
			modelAndView.addObject(BindMessage.ERRORS_MODEL_KEY, errors);
			return this.getJsonView(modelAndView);
		}
		Group group = new Group();

		BeanUtils.copyProperties(groupVo, group);
		groupService.doCreate(group);
		Map<String, String> params = new HashMap<String, String>();
		params.put(BindResult.SUCCESS, "sys.group.add.success");
		modelAndView.addObject(BindMessage.SUCCESS, params);
		return this.getJsonView(modelAndView);
	}

	/** 用户组编辑对话框 */
	@RequestMapping(value = "editdialog")
	public String editDialog(@RequestParam Long id, Model model) {
		if (id != null) {
			Group group = groupService.doFindById(id);
			model.addAttribute("group", group);
		} else {
			logger.error("Eidt Object id is not null!");
		}
		return EDIT_DIALOG_RETURN;
	}

	/** 编辑用户组 */
	@RequestMapping(value = "edit")
	@ResponseBody
	public ModelAndView edit(@Valid @ModelAttribute("group") GroupVo groupVo,
			Errors errors, ModelAndView modelAndView) {

		modelAndView=this.getJsonView(modelAndView);
		if (!errors.hasErrors() && groupVo.getId() == null) {
			errors.reject("sys.group.form.id.empty");
			modelAndView.addObject(BindMessage.ERRORS_MODEL_KEY, errors);
			return modelAndView; 
		} 
		Group group = groupService.doFindById(groupVo.getId());
		if (group == null) {
			errors.reject("sys.group.form.id.empty");
			return modelAndView;
		}
		
		if (!errors.hasErrors()
			&& this.isExistByNameEn(groupVo.getNameEn(),group.getNameEn())) {errors.rejectValue(GroupVo.NAMEEN,	"sys.group.form.nameen.existed.message",new Object[] { groupVo.getNameEn() }, null);

		}

		if (errors.hasErrors()) {
			modelAndView.addObject(BindMessage.ERRORS_MODEL_KEY, errors);
			return modelAndView;
		}

		BeanUtils.copyProperties(groupVo, group);
		groupService.doUpdate(group);

		Map<String, String> params = new HashMap<String, String>();
		params.put(BindResult.SUCCESS, "sys.group.edit.success");
		modelAndView.addObject(BindMessage.SUCCESS, params);
		return modelAndView;
	}

	/** 用户组授权对话框 */
	@RequestMapping(value = "authdialog")
	public String authDialog(@RequestParam Long id, Model model) {
		if (id != null) {
			Group group = groupService.doFindById(id);
			model.addAttribute("group", group);
		} else {
			logger.error("Eidt Object id is not null!");
		}
		return AUTH_DIALOG_RETURN;
	}

	/** 授权到用户 */
	@RequestMapping(value = "addusertogroup",method=RequestMethod.POST)
	@ResponseBody
	public ModelAndView addUsers(@RequestBody(required=false)AuthModel authModel,ModelAndView modelAndView) {
		//查询用户列表
		QueryCriteria queryCriteria = new QueryCriteria(0,9999);
		// 查询条件 参数类型 用户名
		if (authModel.getId()>0) {
			queryCriteria.addQueryCondition("groupId",authModel.getId());
		}
		//查询所有空的
		if(!CollectionUtils.isEmpty(authModel.getAuths())){
			queryCriteria.addQueryCondition("userIds",authModel.getAuths());
		}
		PageResult<User> userPageResult=this.userService.doFindByCriteriaAndGroup(queryCriteria);
		
		
		Group group = this.groupService.doFindById(authModel.getId());
		List<Long> targetUserIds =authModel.getSelecteds();
		List<Long> deleteUserIds = new ArrayList<Long>();
		for (User user :userPageResult.getContent()) {
			if (targetUserIds.contains(user.getId())) {
				targetUserIds.remove(user.getId());
			} else {
				deleteUserIds.add(user.getId());
			}
		}

		if (logger.isInfoEnabled()) {
			logger.info("targetUserIds[]：" + targetUserIds.toString());
			logger.info("deleteUserIds[]:" + deleteUserIds.toString());
		}
		this.groupService.idoAuthUserToGroup(targetUserIds, deleteUserIds,group);
		Map<String, String> params = new HashMap<String, String>();
		params.put(BindResult.SUCCESS, ConstantMessage.ADD_SUCCESS_MESSAGE_CODE);
		modelAndView.addObject(BindMessage.SUCCESS, params);
		return this.getJsonView(modelAndView);
	}

	/** 授权到角色 */
	@RequestMapping(value = "addroletogroup",method=RequestMethod.POST)
	@ResponseBody
	public ModelAndView addRoles(@RequestBody(required=false)AuthModel authModel,ModelAndView modelAndView) {
		//查询角色列表
		QueryCriteria queryCriteria = new QueryCriteria(0,9999);
		// 查询条件用户组ID
		if (authModel.getId()>0) {
			queryCriteria.addQueryCondition("groupId",authModel.getId());
		}
		//查询所有空的
		if(!CollectionUtils.isEmpty(authModel.getAuths())){
			queryCriteria.addQueryCondition("roleIds",authModel.getAuths());
		}
		PageResult<Role> pageResult=this.roleService.doFindByCriteriaAndGroup(queryCriteria);
		//验证输入
		
		List<Long> targetRoleIds=authModel.getSelecteds();
		List<Long> deleteRoleIds=new ArrayList<Long>();
		for (Role role : pageResult.getContent()) {
			//过滤已授权的列表
			if (targetRoleIds.contains(role.getId())) {
				targetRoleIds.remove(role.getId());
			} else {
				//删除未授权
				deleteRoleIds.add(role.getId());
			}
		}
		if (logger.isInfoEnabled()) {
			logger.info("targetUserIds[]：" + targetRoleIds.toString());
			logger.info("deleteUserIds[]:" + deleteRoleIds.toString());
		}
		Group group = this.groupService.doFindById(authModel.getId());
		this.groupService.doAuthRoleToGroup(targetRoleIds, deleteRoleIds, group);
		Map<String, String> params = new HashMap<String, String>();
		params.put(BindResult.SUCCESS, ConstantMessage.ADD_SUCCESS_MESSAGE_CODE);
		modelAndView.addObject(BindMessage.SUCCESS, params);
		return   this.getJsonView(modelAndView);
	}

	/** 查询用户组已授权的角色 */
	@RequestMapping(value = "authroles")
	@ResponseBody
	public DataTable<Role> authRoles(QueryDt query,@RequestParam(required = false) Long id) {
		QueryCriteria queryCriteria = new QueryCriteria();
		// 设置分页 启始页
		queryCriteria.setStartIndex(query.getPage());
		// 每页大小
		queryCriteria.setPageSize(query.getRows());
		 
		// 设置排序字段及排序方向
		if (StringUtils.isNotEmpty(query.getSort()) && StringUtils.isNotEmpty(query.getOrder())) {
			queryCriteria.setOrderField("role."+query.getSort());
			queryCriteria.setOrderDirection(query.getOrder());
		} else {
			queryCriteria.setOrderField("role."+DEFAULT_ORDER_FILED_NAME);
		}
		 
		// 查询条件 参数类型 用户名
		if (id!=null&&id>0) {
			queryCriteria.addQueryCondition("groupId",id);
		}
		
		PageResult<Role> pageResult = this.roleService.doFindByCriteriaAndGroup(queryCriteria);
		
		return pageResult.getDataTable(query.getRequestId());
	}

	/** 查询用户组已授权的用户 */
	@RequestMapping(value = "authusers")
	@ResponseBody
	public DataTable<User> authUsers(QueryDt query,@RequestParam(required = false) Long id) {
		QueryCriteria queryCriteria = new QueryCriteria();
		// 设置分页 启始页
		queryCriteria.setStartIndex(query.getPage());
		// 每页大小
		queryCriteria.setPageSize(query.getRows());
		 
		// 设置排序字段及排序方向
		if (StringUtils.isNotEmpty(query.getSort()) && StringUtils.isNotEmpty(query.getOrder())) {
			queryCriteria.setOrderField("user."+query.getSort());
			queryCriteria.setOrderDirection(query.getOrder());
		} else {
			queryCriteria.setOrderField("user."+DEFAULT_ORDER_FILED_NAME);
		}
		 
		// 查询条件 参数类型 用户名
		if (id!=null&&id>0) {
			queryCriteria.addQueryCondition("groupId",id);
		}
		
		PageResult<User> pageResult = this.userService.doFindByCriteriaAndGroup(queryCriteria);
		
		return pageResult.getDataTable(query.getRequestId());
	}

	/** 查询所有的用户 */
	@RequestMapping(value = "users")
	@ResponseBody
	public DataTable<UserGroup> users(QueryDt query,@RequestParam(required = false) Long id) {
		QueryCriteria queryCriteria = new QueryCriteria();
		// 设置分页 启始页
		queryCriteria.setStartIndex(query.getPage());
		// 每页大小
		queryCriteria.setPageSize(query.getRows());
		 
		// 设置排序字段及排序方向
		if (StringUtils.isNotEmpty(query.getSort()) && StringUtils.isNotEmpty(query.getOrder())) {
			queryCriteria.setOrderField(query.getSort());
			queryCriteria.setOrderDirection(query.getOrder());
		} else {
			queryCriteria.setOrderField(DEFAULT_ORDER_FILED_NAME);
		} 
		
		PageResult<UserGroup> pageResult = this.userService.doFindUserGroupByCriteria(queryCriteria);
		
		
		if(CollectionUtils.isEmpty(pageResult.getContent())){
			return pageResult.getDataTable(query.getRequestId());
		}
		List<UserGroup> userGroups=pageResult.getContent();
		List<Long> userIds=new ArrayList<Long>();
		for(UserGroup userGroup:userGroups){
			userIds.add(userGroup.getId());
		}
		queryCriteria = new QueryCriteria();
		// 设置分页 启始页
		queryCriteria.setStartIndex(0);
		// 每页大小
		queryCriteria.setPageSize(query.getRows());
		// 查询条件 参数类型 用户名
		if (id!=null&&id>0) {
			queryCriteria.addQueryCondition("groupId",id);
		}
		//查询所有空的
		if(!CollectionUtils.isEmpty(userIds)){
			queryCriteria.addQueryCondition("userIds", userIds);
		}
		PageResult<User> userPageResult=this.userService.doFindByCriteriaAndGroup(queryCriteria);
		
		Map<Long,Long> userIdsMap=new HashMap<Long,Long>();
		
		for(User user:userPageResult.getContent()){
			userIdsMap.put(user.getId(), user.getId());
		}
		
		List<UserGroup> contents=new ArrayList<UserGroup>();
		
		for(UserGroup userGroup:userGroups){
			if(userIdsMap.containsKey(userGroup.getId())){
				userGroup.setGroupId(id);
			}
			contents.add(userGroup);
		}
		
		pageResult.setContent(contents);
		
		return pageResult.getDataTable(query.getRequestId());
	 
	}

	/** 查询所有的角色 */
	@RequestMapping(value = "roles")
	@ResponseBody
	public DataTable<RoleGroup> roles(QueryDt query,@RequestParam(required = false) Long id) {
		QueryCriteria queryCriteria = new QueryCriteria();
		// 设置分页 启始页
		queryCriteria.setStartIndex(query.getPage());
		// 每页大小
		queryCriteria.setPageSize(query.getRows());
		 
		// 设置排序字段及排序方向
		if (StringUtils.isNotEmpty(query.getSort()) && StringUtils.isNotEmpty(query.getOrder())) {
			queryCriteria.setOrderField(query.getSort());
			queryCriteria.setOrderDirection(query.getOrder());
		} else {
			queryCriteria.setOrderField(DEFAULT_ORDER_FILED_NAME);
		} 
		
		PageResult<RoleGroup> pageResult = this.roleService.doFindRoleGroupByCriteria(queryCriteria,id);		
		 
		return pageResult.getDataTable(query.getRequestId());
	}

	@RequestMapping(value = "index")
	public String index() {
		return INDEX_RETURN;
	}

	/** 删除用户组 */
	@RequestMapping(value = "delete")
	@ResponseBody
	public ModelAndView delete(@RequestParam Long id, ModelAndView modelAndView) {
		Map<String, String> params = new HashMap<String, String>();
		int updateRow = this.groupService.doDeleteById(id);
		if (updateRow > 0) {
			params.put(BindResult.SUCCESS,"sys.group.delete.success");
		} else {
			params.put(BindResult.SUCCESS,"sys.group.delete.fail");
		}
		modelAndView.addObject(BindMessage.SUCCESS, params);
		return this.getJsonView(modelAndView);
	}

	@RequestMapping(value = "list")
	@ResponseBody
	public DataTable<Group> list(QueryDt query,@ModelAttribute("group") GroupVo groupVo) {
		QueryCriteria queryCriteria = new QueryCriteria();

		// 设置分页 启始页
		queryCriteria.setStartIndex(query.getPage());
		// 每页大小
		queryCriteria.setPageSize(query.getRows());
		// 设置排序字段及排序方向
		if (StringUtils.isNotEmpty(query.getSort()) && StringUtils.isNotEmpty(query.getOrder())) {
			queryCriteria.setOrderField(query.getSort());
			queryCriteria.setOrderDirection(query.getOrder());
		} else {
			queryCriteria.setOrderField(DEFAULT_ORDER_FILED_NAME);
			queryCriteria.setOrderDirection(QueryCriteria.ASC);
		}
		//查询条件 中文参数名称按模糊查询
		if(StringUtils.isNotEmpty(groupVo.getNameZh())){
			queryCriteria.addQueryCondition("nameZh","%"+groupVo.getNameZh()+"%");
		}

		PageResult<Group> pageResult = groupService.doFindByCriteria(queryCriteria);
		return pageResult.getDataTable(query.getRequestId());
	}
	/*add by maojiawei*/
	private Boolean isExistByNameEn(String nameEn) {
		Boolean flag = false;
		if (StringUtils.isNotEmpty(nameEn)) {
			flag = groupService.doIsExistByNameEn(nameEn.trim());
		}
		return flag;
	}
	/*add by maojiawei*/
	private Boolean isExistByNameEn(String nameEn, String oldNameEn) {
		Boolean flag = false;
		if (StringUtils.isNotEmpty(nameEn) && !nameEn.equals(oldNameEn)) {
			flag = groupService.doIsExistByNameEn(nameEn.trim());
		}
		return flag;
	}
	/*add by maojiawei*/
	@RequestMapping(value = "checkisexitnameen")
	@ResponseBody
	public String checkIsExistByNameEn(HttpServletRequest servletRequest,
			@RequestParam(required = false) String nameEn,@RequestParam(required=false) Long id) {
		Boolean flag=Boolean.FALSE;
		
		if(id==null){
			flag = this.isExistByNameEn(nameEn)? false : true;
		}else{
			Group group = groupService.doFindById(id);
			if(group==null){
				flag = this.isExistByNameEn(nameEn)? false : true;
			}else{
				flag=this.isExistByNameEn(nameEn, group.getNameEn())?false:true;
			}
		}
		return flag.toString();
	}
	
	/****
	 * 
	 * @param tableId
	 * @param groupVo
	 * @param modelAndView
	 * @return
	 */
	@RequestMapping(value = "export")
	@ResponseBody
	public ModelAndView exportExcel(@RequestParam(required=false) String tableId,@RequestParam(required = false) String sort,@RequestParam(required = false) String order,@ModelAttribute("group") GroupVo groupVo,ModelAndView modelAndView){
		
		DataGrid dataGrid=dataGridService.doFindByTableIdAndSort(tableId);
		QueryCriteria queryCriteria=new QueryCriteria();
		queryCriteria.setPageSize(10000);
		// 设置排序字段及排序方向
		if (StringUtils.isNotEmpty(sort) && StringUtils.isNotEmpty(order)) {
			queryCriteria.setOrderField(sort);
			queryCriteria.setOrderDirection(order);
		} else {
			queryCriteria.setOrderField(DEFAULT_ORDER_FILED_NAME);
			queryCriteria.setOrderDirection("ASC");
		}
		//查询条件 中文参数名称按模糊查询
		if(StringUtils.isNotEmpty(groupVo.getNameZh())){
			queryCriteria.addQueryCondition("nameZh","%"+groupVo.getNameZh()+"%");
		}
		//查询用户组
		PageResult<Group> result=groupService.doFindByCriteria(queryCriteria);
		
		Map<String, Map<Object, Object>> fieldCodeTypes = new HashMap<String, Map<Object, Object>>();

		Map<String, String> dataFormats = new HashMap<String, String>();		
		dataFormats.put("birthday", DateUtil.FORMAT_DATE_YYYY_MM_DD);
		//创建.xls的文件名
		String fileName=this.createFileName(FileUtil.EXCEL_EXTENSION);
		
		modelAndView.addObject("title", dataGrid.getTitle()==null?"用户组":dataGrid.getTitle());
		
		Long startTime=System.currentTimeMillis();
		
		fileName=excelExportService.export(dataGrid, result.getContent(), fileName, fieldCodeTypes, dataFormats);
		
		logger.info("fileName:{}",fileName);
		
		Long costTime=System.currentTimeMillis()-startTime;
		
		modelAndView.addObject(FILENAME,fileName);
		
		logger.info("export Excel {} cost:{} time,fileName:{}",dataGrid.getTitle(),costTime,fileName);
		logger.info("out Excel导出");
		return this.getExcelView(modelAndView);
	}
}
