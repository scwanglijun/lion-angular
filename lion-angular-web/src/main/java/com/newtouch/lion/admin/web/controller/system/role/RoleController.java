/*
* Copyright (c)  2015, Newtouch
* All rights reserved. 
*
* $id: RoleController.java 9552 2015年1月7日 上午12:06:04 WangLijun$
*/
package com.newtouch.lion.admin.web.controller.system.role;

import com.newtouch.lion.admin.web.model.system.auth.AuthModel;
import com.newtouch.lion.admin.web.model.system.role.RoleVo;
import com.newtouch.lion.common.date.DateUtil;
import com.newtouch.lion.common.file.FileUtil;
import com.newtouch.lion.common.lang.LongUtils;
import com.newtouch.lion.data.DataTable;
import com.newtouch.lion.json.JSONParser;
import com.newtouch.lion.model.datagrid.DataGrid;
import com.newtouch.lion.model.system.*;
import com.newtouch.lion.page.PageResult;
import com.newtouch.lion.query.QueryCriteria;
import com.newtouch.lion.service.datagrid.DataGridService;
import com.newtouch.lion.service.excel.ExcelExportService;
import com.newtouch.lion.service.system.GroupService;
import com.newtouch.lion.service.system.ResourceService;
import com.newtouch.lion.service.system.RoleService;
import com.newtouch.lion.service.system.UserService;
import com.newtouch.lion.tree.State;
import com.newtouch.lion.tree.TreeNode;
import com.newtouch.lion.web.constant.ConstantMessage;
import com.newtouch.lion.web.controller.AbstractController;
import com.newtouch.lion.web.model.QueryDt;
import com.newtouch.lion.web.servlet.view.support.BindMessage;
import com.newtouch.lion.web.servlet.view.support.BindResult;
import org.apache.commons.lang.StringUtils;
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
import java.util.*;

/**
 * <p>
 * Title: 角色控制器
 * </p>
 * <p>
 * Description: 包括角色查询、添加、删除、修改
 * </p>
 * <p>
 * Copyright: Copyright (c) 2015
 * </p>
 * <p>
 * Company: Newtouch
 * </p>
 * 
 * @author WangLijun
 * @version 1.0
 */
@Controller
@RequestMapping("/system/role")
public class RoleController extends AbstractController{
	/**默认排序字段名称*/
	private static final String DEFAULT_ORDER_FILED_NAME="id";
	/**默认DataGrid表格名称*/
	@SuppressWarnings("unused")
	private static final String DEFAULT_TABLE_ID="sys_rolelist_tb";
	/**角色关联用户列表*/
	@SuppressWarnings("unused")
	private static final String  AUTHROLE_USER_TABLE_ID="sys_authrole_userlist_tb";
	/**角色关联用户组列表*/
	private static final String AUTHROLE_USERGROUPS_TABLE_ID="sys_authrole_grouplist_tb";
	/**角色所有用户组列表*/
	@SuppressWarnings("unused")
	private static final String AUTHROLE_GROUPS_TB="sys_authrole_groups_tb";
	/**角色所有用户列表*/
	@SuppressWarnings("unused")
	private static final String AUTHROLE_USERS_TB="sys_authrole_users_tb";
	/**系统角色首页*/
	private static final String INDEX_RETURN="/system/role/index";
	/**系统角色添加*/
	private static final String ADD_DIALOG_RETURN="/system/role/adddialog";
	/**系角色授权*/
	private static final String AUTH_DIALOG_RETURN="/system/role/authdialog";
	
	@Autowired
	private RoleService  roleService;
	@Autowired
	private ResourceService  resourceService;
	@Autowired
	private GroupService groupService;
	@Autowired
	private UserService  userService;
	/**DataGrid表格*/
	@Autowired
	private DataGridService dataGridService;
	/**Excel通用导出*/
	@Autowired
	private ExcelExportService excelExportService;
	
	/**显示首页服务*/
	@RequestMapping("/index")
	public String index(){
		return INDEX_RETURN;
	}
	
	@RequestMapping(value = "adddialog")
	public String addDialog(){
		return ADD_DIALOG_RETURN;
	}
	
	@RequestMapping(value = "add")
    @ResponseBody
	public ModelAndView add(@Valid @ModelAttribute("role") RoleVo  roleVo,Errors  errors,ModelAndView modelAndView){    	
     
		if (!errors.hasErrors()&& this.isExistByNameEn(roleVo.getNameEn())) {
			errors.rejectValue(RoleVo.NAMEEN,
					"sys.role.form.nameen.existed.message",
					new Object[] { roleVo.getNameEn() }, null);
		}

		//是否错误消息
		if (errors.hasErrors()) {
			modelAndView.addObject(BindMessage.ERRORS_MODEL_KEY, errors);
			return this.getJsonView(modelAndView);
		}
		Role role = new Role();

		BeanUtils.copyProperties(roleVo, role);
		roleService.doCreate(role);
		Map<String, String> params = new HashMap<String, String>();
		params.put(BindResult.SUCCESS, "sys.role.add.success");
		modelAndView.addObject(BindMessage.SUCCESS, params);
		
		return this.getJsonView(modelAndView);
	}
	
	@RequestMapping(value = "authdialog")
	public String authDialog(@RequestParam(required=false) Long id,Model model){
		if(id!=null){
			Role role=roleService.doFindById(id);
			model.addAttribute("role",role);		
		}else{
			logger.error("Eidt Object id is not null!");
		}
		return AUTH_DIALOG_RETURN;
	}
	/**加载角色所有关联用户列表*/
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
			queryCriteria.addQueryCondition("roleId",id);
		}
		
		PageResult<User> pageResult = this.userService.doFindByCriteriaAndRole(queryCriteria);
		
		return pageResult.getDataTable(query.getRequestId());
	}
	
	/**加载角色所有关联用户列表*/
	@RequestMapping(value = "authgroups")
	@ResponseBody
	public DataTable<Group> authGroups(QueryDt query,@RequestParam(required = false) Long id) {
		QueryCriteria queryCriteria = new QueryCriteria();
		// 设置分页 启始页
		queryCriteria.setStartIndex(query.getPage());
		// 每页大小
		queryCriteria.setPageSize(query.getRows());
		 
		// 设置排序字段及排序方向
		if (StringUtils.isNotEmpty(query.getSort()) && StringUtils.isNotEmpty(query.getOrder())) {
			queryCriteria.setOrderField("groups."+query.getSort());
			queryCriteria.setOrderDirection(query.getOrder());
		} else {
			queryCriteria.setOrderField("groups."+DEFAULT_ORDER_FILED_NAME);
		}
		 
		// 查询条件 参数类型 用户名
		if (id!=null&&id>0) {
			queryCriteria.addQueryCondition("roleId",id);
		}
		
		PageResult<Group> pageResult = this.groupService.doFindByCriteriaAndRole(queryCriteria);
		
		return pageResult.getDataTable(query.getRequestId());
	}
	
	/**加载角色所有关联用户组列表*/
	@RequestMapping(value = "authusergroups")
	@ResponseBody
	public String authUserGroupsForRole(@RequestParam(required=false) Long id){
		return this.roleService.doFindAuthUserGroupsById(id, AUTHROLE_USERGROUPS_TABLE_ID);
	}
	
	/**所有资源列表*/
	@RequestMapping(value = "resources")
	@ResponseBody
	public  ModelAndView  resources(@RequestParam(required=false) Long id,ModelAndView modelAndView){
		List<Resource>  resources=this.resourceService.doFindFirstLevel();
		Long roleId=id;
		Set<String>  properties=new HashSet<String>();		
		properties.add("id");		
		properties.add("text");		
		properties.add("checked");	
		properties.add("state");		
		properties.add("path");		
		//properties.add("attributes");		
		properties.add("children");
		properties.add("parentResourceId");
		List<TreeNode> treeNodes=new ArrayList<TreeNode>();
		for(Resource resource:resources){
			Boolean checked=Boolean.FALSE;
			Attributes attributes=new Attributes();			
			attributes.setPath(resource.getPath());
			for(Role role:resource.getRoles()){
				if(roleId.equals(role.getId())){
					checked=Boolean.TRUE;
					continue;
				}
			}
			List<TreeNode> children=this.resourceAttr(resource.getResources(),roleId);
			Long resourceId=resource.getId();
			String text=resource.getNameZh();
			int  orderId=resource.getSeqNum();
			boolean isLeaf=false;
			logger.info("children.size()"+children.size());
			TreeNode  treeNode=new TreeNode(resourceId,text,State.open,checked,"",orderId,isLeaf,attributes.getPath(),attributes,children);
			treeNodes.add(treeNode);
		}
		String str=JSONParser.toJSONString(treeNodes,properties).replace("text","name").replace("\"state\":\"open\"","\"open\":true");
		logger.info("json:{}",str);
		return this.getStrJsonView(str, modelAndView);
	}
	
	/**为角色添加用户集合*/
	@RequestMapping(value="addusers")
	@ResponseBody
	public ModelAndView addusers(@RequestParam(required=false) Long roleId,@RequestParam(required=false) String userId,ModelAndView modelAndView){
		Role role=this.roleService.doGetById(roleId);
		List<Long> targetUserIds =LongUtils.parserStringToLong(userId,LongUtils.SPARATOR_COMMA);
		List<Long> deleteUserIds = new ArrayList<Long>();
		for(User user:role.getUsers()){
			if(targetUserIds.contains(user.getId())){
				targetUserIds.remove(user.getId());
			}else{
				deleteUserIds.add(user.getId());
			}
		}
		
		if(logger.isInfoEnabled()){
			logger.info("targetUserIds[]：" + targetUserIds.toString());
			logger.info("deleteUserIds[]:" + deleteUserIds.toString());
		}
		this.roleService.doAuthUsersToRole(targetUserIds, deleteUserIds, role);
		Map<String,String> params=new  HashMap<String,String>();
    	params.put(BindResult.SUCCESS,ConstantMessage.ADD_SUCCESS_MESSAGE_CODE);
    	modelAndView.addObject(BindMessage.SUCCESS,params);
		return modelAndView;
	}
	
	/**为角色添加用户集合*/
	@RequestMapping(value="addgroups")
	@ResponseBody
	public ModelAndView addgroups(@RequestParam(required=false) Long roleId,@RequestParam(required=false) String groupId,ModelAndView modelAndView){
		//TODO 验证输入
		Role role=this.roleService.doGetById(roleId);
		List<Long> targetGroupIds =LongUtils.parserStringToLong(groupId,LongUtils.SPARATOR_COMMA);
		List<Long> deleteGroupIds = new ArrayList<Long>();
		for(Group group:role.getGroups()){
			if(targetGroupIds.contains(group.getId())){
				targetGroupIds.remove(group.getId());
			}else{
				deleteGroupIds.add(group.getId());
			}
		}
		
		if(logger.isInfoEnabled()){
			logger.info("targetGroupIds[]：" + targetGroupIds.toString());
			logger.info("deleteGroupIds[]:" + deleteGroupIds.toString());
		}
		this.roleService.doAuthGroupsToRole(targetGroupIds, deleteGroupIds, role);
		Map<String,String> params=new  HashMap<String,String>();
    	params.put(BindResult.SUCCESS,ConstantMessage.ADD_SUCCESS_MESSAGE_CODE);
    	modelAndView.addObject(BindMessage.SUCCESS,params);
		return modelAndView;
	}
	
	/**为角色添加资源集合*/
	@RequestMapping(value="addresources")
	@ResponseBody
	public ModelAndView addresources(@RequestBody(required=false)AuthModel authModel,ModelAndView modelAndView){
		//验证输入
		Role role=this.roleService.doGetById(authModel.getId());
		List<Long> targetResourceIds=authModel.getSelecteds();
		List<Long> deleteResourceIds = new ArrayList<Long>();
		for(Resource resource:role.getResources()){
			
			if(targetResourceIds.contains(resource.getId())){
				targetResourceIds.remove(resource.getId());
			}else{
				deleteResourceIds.add(resource.getId());
			}
		}
		if(logger.isInfoEnabled()){
			logger.info("targetResourceIds[]：" + targetResourceIds.toString());
			logger.info("targetResourceIds[]:" + targetResourceIds.toString());
		}
		this.roleService.doAuthResourceToRole(targetResourceIds, deleteResourceIds, role);
		Map<String,String> params=new  HashMap<String,String>();
    	params.put(BindResult.SUCCESS,ConstantMessage.ADD_SUCCESS_MESSAGE_CODE);
    	modelAndView.addObject(BindMessage.SUCCESS,params);
		return this.getJsonView(modelAndView);
	}
	
	
	
	private List<TreeNode> resourceAttr(Set<Resource> resources,Long roleId){
		List<TreeNode>  children=new ArrayList<TreeNode>();
		for(Resource resource:resources){
			Boolean checked=Boolean.FALSE;
			Attributes attributes=new Attributes();			
			attributes.setPath(resource.getPath());
			
			for(Role role:resource.getRoles()){
				if(roleId.equals(role.getId())){
					checked=Boolean.TRUE;
					continue;
				}
			}
			List<TreeNode> childrenNext=new ArrayList<TreeNode>();
			
			if(resource.getResources().size()>0){
				childrenNext=this.resourceAttr(resource.getResources(),roleId);
			}
			
			Long id=resource.getId();
			String text=resource.getNameZh();
			int  orderId=resource.getSeqNum();
			boolean isLeaf=false;
			logger.info("childrenNext.size()"+childrenNext.size());
			TreeNode  treeNode=new TreeNode(id,text,State.open,checked,"",orderId,isLeaf,attributes.getPath(),attributes,childrenNext);
			children.add(treeNode);
		}
		return children;
	}
	
	@RequestMapping(value="delete")
	@ResponseBody
	public ModelAndView delete(@RequestParam Long id,ModelAndView modelAndView){
		Map<String, String> params = new HashMap<String, String>();
		int updateRow = this.roleService.doDeleteById(id);
		if (updateRow > 0) {
			params.put(BindResult.SUCCESS,"sys.role.delete.success");
		} else {
			params.put(BindResult.SUCCESS,"sys.role.delete.fail");
		}
		modelAndView.addObject(BindMessage.SUCCESS, params);
		return this.getJsonView(modelAndView);
    }
	
	 
	
	@RequestMapping(value = "edit")
	@ResponseBody
	public ModelAndView edit(@Valid @ModelAttribute("role") RoleVo  roleVo,Errors  errors,ModelAndView modelAndView){
		
		modelAndView=this.getJsonView(modelAndView);
		if (!errors.hasErrors() && roleVo.getId() == null) {
			errors.reject("sys.group.form.id.empty");
			modelAndView.addObject(BindMessage.ERRORS_MODEL_KEY, errors);
			return modelAndView; 
		} 
		Role role = roleService.doFindById(roleVo.getId());
		if (role == null) {
			errors.reject("sys.role.form.id.empty");
			return modelAndView;
		}
		
		if (!errors.hasErrors()
				&& this.isExistByNameEn(roleVo.getNameEn(),role.getNameEn())) {errors.rejectValue(RoleVo.NAMEEN,	"sys.role.form.nameen.existed.message",new Object[] { roleVo.getNameEn() }, null);

		}

		if (errors.hasErrors()) {
			modelAndView.addObject(BindMessage.ERRORS_MODEL_KEY, errors);
			return modelAndView;
		}

		BeanUtils.copyProperties(roleVo, role);
		roleService.doUpdate(role);

		Map<String, String> params = new HashMap<String, String>();
		params.put(BindResult.SUCCESS, "sys.role.edit.success");
		modelAndView.addObject(BindMessage.SUCCESS, params);
		return modelAndView;
	}
	
	@RequestMapping(value = "list")
	@ResponseBody
	public DataTable<Role> list(QueryDt query,@ModelAttribute("role") RoleVo roleVo) {
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
		if(StringUtils.isNotEmpty(roleVo.getNameZh())){
			queryCriteria.addQueryCondition("nameZh","%"+roleVo.getNameZh()+"%");
		}

		PageResult<Role> pageResult = roleService.doFindByCriteria(queryCriteria);
		return pageResult.getDataTable(query.getRequestId());
	}
	/*add by maojiawei*/
	private Boolean isExistByNameEn(String nameEn) {
		Boolean flag = false;
		if (StringUtils.isNotEmpty(nameEn)) {
			flag = roleService.doIsExistByNameEn(nameEn.trim());
		}
		return flag;
	}
	/*add by maojiawei*/
	private Boolean isExistByNameEn(String nameEn, String oldNameEn) {
		Boolean flag = false;
		if (StringUtils.isNotEmpty(nameEn) && !nameEn.equals(oldNameEn)) {
			flag = roleService.doIsExistByNameEn(nameEn.trim());
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
			Role role = roleService.doFindById(id);
			if(role==null){
				flag = this.isExistByNameEn(nameEn)? false : true;
			}else{
				flag=this.isExistByNameEn(nameEn, role.getNameEn())?false:true;
			}
		}
		return flag.toString();
	}
	
	/****
	 * 
	 * @param tableId
	 * @param roleVo
	 * @param modelAndView
	 * @return
	 */
	@RequestMapping(value = "export")
	@ResponseBody
	public ModelAndView exportExcel(@RequestParam(required=false) String tableId,@RequestParam(required = false) String sort,@RequestParam(required = false) String order,@ModelAttribute("role") RoleVo roleVo,ModelAndView modelAndView){
		
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
		if(StringUtils.isNotEmpty(roleVo.getNameZh())){
			queryCriteria.addQueryCondition("nameZh","%"+roleVo.getNameZh()+"%");
		}

		PageResult<Role> result=roleService.doFindByCriteria(queryCriteria);
		Map<String, Map<Object, Object>> fieldCodeTypes = new HashMap<String, Map<Object, Object>>();

		Map<String, String> dataFormats = new HashMap<String, String>();		
		dataFormats.put("birthday", DateUtil.FORMAT_DATE_YYYY_MM_DD);
		//创建.xls的文件名
		String fileName=this.createFileName(FileUtil.EXCEL_EXTENSION);
		
		modelAndView.addObject("title", dataGrid.getTitle());
		
		Long startTime=System.currentTimeMillis();
		
		fileName=excelExportService.export(dataGrid, result.getContent(), fileName, fieldCodeTypes, dataFormats);
		
		logger.info("fileName:{}",fileName);
		
		Long costTime=System.currentTimeMillis()-startTime;
		
		modelAndView.addObject(FILENAME,fileName);
		
		logger.info("export Excel {} cost:{} time,fileName:{}",dataGrid.getTitle(),costTime,fileName);
		logger.info("out Excel导出");
		return this.getExcelView(modelAndView);
	}
	
	/** 查询所有的用户 add by maojiawei */
	@RequestMapping(value = "users")
	@ResponseBody
	public DataTable<UserRole> users(QueryDt query,@RequestParam(required = false) Long id) {
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
		
		PageResult<UserRole> pageResult = this.userService.doFindUserRoleByCriteria(queryCriteria,id);		
		 
		return pageResult.getDataTable(query.getRequestId());
	 
	}
	
	/** 查询所有的用户 add by maojiawei */
	@RequestMapping(value = "groups")
	@ResponseBody
	public DataTable<GroupRole> groups(QueryDt query,@RequestParam(required = false) Long id) {
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
		
		PageResult<GroupRole> pageResult = this.groupService.doFindGroupRoleByCriteria(queryCriteria,id);

		return pageResult.getDataTable(query.getRequestId());
	}
	
	/** 授权到用户 */
	@RequestMapping(value = "addusertorole",method=RequestMethod.POST)
	@ResponseBody
	public ModelAndView addUsers(@RequestBody(required=false)AuthModel authModel,ModelAndView modelAndView) {
		//查询用户列表
		QueryCriteria queryCriteria = new QueryCriteria(0,9999);
		// 查询条件 参数类型 用户名
		if (authModel.getId()>0) {
			queryCriteria.addQueryCondition("roleId",authModel.getId());
		}
		//查询所有空的
		if(!CollectionUtils.isEmpty(authModel.getAuths())){
			queryCriteria.addQueryCondition("userIds",authModel.getAuths());
		}
		PageResult<User> userPageResult=this.userService.doFindByCriteriaAndRole(queryCriteria);
		
		
		Role role = this.roleService.doFindById(authModel.getId());
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
		this.roleService.idoAuthUserToRole(targetUserIds, deleteUserIds,role);
		Map<String, String> params = new HashMap<String, String>();
		params.put(BindResult.SUCCESS, ConstantMessage.ADD_SUCCESS_MESSAGE_CODE);
		modelAndView.addObject(BindMessage.SUCCESS, params);
		return this.getJsonView(modelAndView);
	}

	/** 授权到角色 */
	@RequestMapping(value = "addgrouptorole",method=RequestMethod.POST)
	@ResponseBody
	public ModelAndView addRoles(@RequestBody(required=false)AuthModel authModel,ModelAndView modelAndView) {
		//查询角色列表
		QueryCriteria queryCriteria = new QueryCriteria(0,9999);
		// 查询条件用户组ID
		if (authModel.getId()>0) {
			queryCriteria.addQueryCondition("roleId",authModel.getId());
		}
		//查询所有空的
		if(!CollectionUtils.isEmpty(authModel.getAuths())){
			queryCriteria.addQueryCondition("groupIds",authModel.getAuths());
		}
		PageResult<Group> pageResult=this.groupService.doFindByCriteriaAndRole(queryCriteria);
		//验证输入
		
		List<Long> targetGroupIds=authModel.getSelecteds();
		List<Long> deleteGroupIds=new ArrayList<Long>();
		for (Group group : pageResult.getContent()) {
			//过滤已授权的列表
			if (targetGroupIds.contains(group.getId())) {
				targetGroupIds.remove(group.getId());
			} else {
				//删除未授权
				deleteGroupIds.add(group.getId());
			}
		}
		if (logger.isInfoEnabled()) {
			logger.info("targetUserIds[]：" + targetGroupIds.toString());
			logger.info("deleteUserIds[]:" + deleteGroupIds.toString());
		}
		Role role = this.roleService.doFindById(authModel.getId());
		this.roleService.idoAuthGroupToRole(targetGroupIds, deleteGroupIds, role);
		Map<String, String> params = new HashMap<String, String>();
		params.put(BindResult.SUCCESS, ConstantMessage.ADD_SUCCESS_MESSAGE_CODE);
		modelAndView.addObject(BindMessage.SUCCESS, params);
		return   this.getJsonView(modelAndView);
	}
}

	