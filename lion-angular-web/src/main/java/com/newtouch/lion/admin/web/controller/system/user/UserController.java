/*
 * Copyright (c)  2014, NewTouch
 * All rights reserved. 
 *
 * $id: UserController.java 9552 2014-2-22 下午05:32:34 WangLijun$
 */
package com.newtouch.lion.admin.web.controller.system.user;

import com.newtouch.lion.admin.web.model.system.auth.AuthModel;
import com.newtouch.lion.admin.web.model.system.user.UserVo;
import com.newtouch.lion.common.date.DateUtil;
import com.newtouch.lion.common.file.FileUtil;
import com.newtouch.lion.common.user.UserInfo;
import com.newtouch.lion.data.DataTable;
import com.newtouch.lion.json.JSONParser;
import com.newtouch.lion.model.datagrid.DataGrid;
import com.newtouch.lion.model.system.*;
import com.newtouch.lion.page.PageResult;
import com.newtouch.lion.query.QueryCriteria;
import com.newtouch.lion.service.datagrid.DataGridService;
import com.newtouch.lion.service.excel.ExcelExportService;
import com.newtouch.lion.service.system.*;
import com.newtouch.lion.tree.TreeNode;
import com.newtouch.lion.util.ResourceConvertUtil;
import com.newtouch.lion.util.ResourceTreeUtil;
import com.newtouch.lion.web.constant.ConstantMessage;
import com.newtouch.lion.web.controller.AbstractController;
import com.newtouch.lion.web.model.QueryDt;
import com.newtouch.lion.web.model.QueryVo;
import com.newtouch.lion.web.servlet.view.support.BindMessage;
import com.newtouch.lion.web.servlet.view.support.BindResult;
import com.newtouch.lion.web.shiro.credentials.PasswordEncoder;
import com.newtouch.lion.web.shiro.session.LoginSecurityUtil;
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
import java.util.*;
import java.util.Calendar;

/**
 * <p>
 * Title: 后台管理登录用户控制类
 * </p>
 * <p>
 * Description: 后台管理登录用户控制类
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
@Controller(value = "sysUserController")
@RequestMapping("/system/user")
public class UserController extends AbstractController {
	private final Logger logger = LoggerFactory.getLogger(super.getClass());
	/** 默认排序字段 */
	private static final String DEFAULT_ORDER_FILED_NAME = "id";
	/** 密码修改 */
	private static final String EDIT_USER_PASSWORD_RETURN = "/system/user/pwdindex";
	/** 我的资源 */
	private static final String USERINFO_RETURN = "/system/user/userinfo";
	/** 首页 */
	private static final String INDEX_RETURN = "/system/user/index";
	/** 编辑对话框 */
	private static final String EDIT_DIALOG_RETURN = "/system/user/editdialog";
	/** 授权对话框 */
	private static final String AUTH_DIALOG_RETURN = "/system/user/authdialog";
	/** 首页列表名称 */
	@SuppressWarnings("unused")
	private static final String INDEX_TB = "userlist_dt";
	/** 已授权组 */
	private static final String AUTH_USER_GROUPS_TB = "usergroup_tb";
	/** 默认密码 */
	private static final String DEFAULT_PASSWORD ="111aaa";
	@Autowired
	private UserService userService;
	@Autowired
	private RoleService roleService;
	@Autowired
	private GroupService groupService;
	@Autowired
	private PasswordEncoder passwordEncoderService;
	@Autowired
	private ResourceService resourceService;
	/**DataGrid表格*/
	@Autowired
	private DataGridService dataGridService;
	/**Excel通用导出*/
	@Autowired
	private ExcelExportService excelExportService;
	/**部门列表*/
	@Autowired
	private DepartmentService departmentService;
	/** 首页显示 */
	@RequestMapping(value = "index")
	public String index(Model model) {
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.YEAR, 1);// TODO 默认账户有效期为一年；

		model.addAttribute("accountExpiredDate",DateUtil.formatDate(calendar.getTime(),DateUtil.FORMAT_DATE_YYYY_MM_DD));
		
		Calendar credentialExpiredCalendar = Calendar.getInstance();
		
		credentialExpiredCalendar.add(Calendar.MONTH, 3);// TODO 默认密码有效期为3个月；
		
		model.addAttribute("credentialExpiredDate",DateUtil.formatDate(credentialExpiredCalendar.getTime(),DateUtil.FORMAT_DATE_YYYY_MM_DD));
		
		return INDEX_RETURN;
	}

	/** 添加提交 */
	@RequestMapping(value = "add")
	@ResponseBody
	public ModelAndView add(@Valid @ModelAttribute("userVo") UserVo userVo,Errors errors, ModelAndView modelAndView) {
		//检查基于Model的验证框架
		if (errors.hasErrors()) {
			modelAndView.addObject(BindMessage.ERRORS_MODEL_KEY, errors);
			return this.getJsonView(modelAndView);
		}
		
		//检查用户是否已存在
		if(userService.checkUsername(userVo.getUsername())){
			errors.rejectValue("username",	"sys.user.username.existed.message",new Object[] {userVo.getUsername()}, null);
		}
		//检查员工号是否已存在
		if(userService.checkEmployeeCode(userVo.getEmployeeCode())){
			errors.rejectValue("employeeCode",	"sys.user.employeecode.existed",new Object[] {userVo.getEmployeeCode()}, null);
		}
		//检查邮箱是否已经存在
		if(userService.checkEmail(userVo.getEmail())){
			errors.rejectValue("email",	"sys.user.email.existed",new Object[] {userVo.getEmail()}, null);
		}
		//再次检查是否出错
		if (errors.hasErrors()) {
			modelAndView.addObject(BindMessage.ERRORS_MODEL_KEY, errors);
			return this.getJsonView(modelAndView);
		}
		
		
		User user = new User();

		BeanUtils.copyProperties(userVo, user);
		//去空格 用户名、员工号、邮箱的空格
		user.setUsername(userVo.getUsername().trim());
		user.setEmployeeCode(userVo.getEmployeeCode().trim());
		user.setEmail(userVo.getEmail().trim());

		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.YEAR, 1);// TODO 默认账户有效期为一年；

		Calendar credentialExpiredCalendar = Calendar.getInstance();
		credentialExpiredCalendar.add(Calendar.MONTH,3);// TODO 默认密码有效期为3个月；

		user.setAccountExpiredDate(calendar.getTime());
		user.setCredentialExpiredDate(credentialExpiredCalendar.getTime());
		String passwordEncoder = passwordEncoderService.encodePassword(DEFAULT_PASSWORD,user.getUsername());
		logger.info("passwordEncoder:{}",passwordEncoder);
		// 密码加密码
		user.setPassword(passwordEncoder);
		// 将登录用户转换为小写
		user.setUsername(user.getUsername().toLowerCase());
		userService.doCreateUser(user);
		Map<String, String> params = new HashMap<String, String>();
		params.put(BindResult.SUCCESS, ConstantMessage.ADD_SUCCESS_MESSAGE_CODE);
		modelAndView.addObject(BindMessage.SUCCESS, params);
		return  this.getJsonView(modelAndView);
	}

	/** 授权 对话框 */
	@RequestMapping(value = "authdialog")
	public String authDialog(@RequestParam(required = false) Long id,
			Model model) {
		User user = this.userService.doFindById(id);
		model.addAttribute("user", user);
		return AUTH_DIALOG_RETURN;
	}

	/** 显示已关联的用户组 */
	@RequestMapping(value = "authgroups")
	@ResponseBody
	public ModelAndView authGroups(@RequestParam(required = false) Long id,ModelAndView modelAndView) {
		String str=this.userService.doFindAllAuthGroupsById(id, AUTH_USER_GROUPS_TB);
		return this.getStrJsonView(str, modelAndView);
	}
	
	/** 显示已关联的用户组 */
	@RequestMapping(value = "authgroup")
	@ResponseBody
	public DataTable<Group> authGroup(QueryDt query,@RequestParam(required = false) Long id,ModelAndView modelAndView) {
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
			queryCriteria.addQueryCondition("userId",id);
		}
		PageResult<Group>  pageResult=groupService.doFindByCriteriaAndUser(queryCriteria);
		
		return  pageResult.getDataTable(query.getRequestId());
	}

	/** 显示已关联的角色 */
	@RequestMapping(value = "authroles")
	@ResponseBody
	public DataTable<Role> authRoles(QueryDt query,@RequestParam(required = false) Long id,ModelAndView modelAndView) {
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
			queryCriteria.addQueryCondition("userId",id);
		}
		//查询用户所关联角色
		PageResult<Role>  pageResult=this.roleService.doFindByCriteriaAndUser(queryCriteria);
		
		return  pageResult.getDataTable(query.getRequestId());
	}

	/** 显示所有的角色 */
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
		
		PageResult<RoleGroup> pageResult = this.roleService.doFindRoleUserByCriteria(queryCriteria,id);		
		 
		return pageResult.getDataTable(query.getRequestId());
	}

	/** 显示所有的角色 */
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
		
		PageResult<GroupRole> pageResult = this.groupService.doFindGroupUserByCriteria(queryCriteria,id);		
		 
		return pageResult.getDataTable(query.getRequestId());
	}

	/** 为用户添加用户组集合 */
	@RequestMapping(value = "addgroups",method=RequestMethod.POST)
	@ResponseBody
	public ModelAndView addGroups(@RequestBody(required=false)AuthModel authModel,ModelAndView modelAndView) {
		//验证输入
		Long userId=authModel.getId();		
		//查询角色列表
		QueryCriteria queryCriteria = new QueryCriteria(0,9999);
		// 查询条件用户组ID
		if (authModel.getId()>0) {
			queryCriteria.addQueryCondition("userId",userId);
		}
		//查询条件不能为空
		if(!CollectionUtils.isEmpty(authModel.getAuths())){
			queryCriteria.addQueryCondition("groupIds",authModel.getAuths());
		}
		PageResult<Group> pageResult=this.groupService.doFindByCriteriaAndUser(queryCriteria);
		
		List<Long> targetGroupIds =authModel.getSelecteds();
		
		List<Long> deleteGroupIds = new ArrayList<Long>();
		
		for (Group group : pageResult.getContent()) {
			if (targetGroupIds.contains(group.getId())) {
				targetGroupIds.remove(group.getId());
			} else {
				deleteGroupIds.add(group.getId());
			}
		}
		logger.info("targetGroupIds:{}", targetGroupIds);
		logger.info("deleteGroupIds:{}", deleteGroupIds);
		
		User user = this.userService.doFindById(userId);
		
		this.userService.doAuthGroupToUser(targetGroupIds, deleteGroupIds, user);
		Map<String, String> params = new HashMap<String, String>();
		params.put(BindResult.SUCCESS, ConstantMessage.ADD_SUCCESS_MESSAGE_CODE);
		modelAndView.addObject(BindMessage.SUCCESS, params);
		return this.getJsonView(modelAndView);
	}

	/** 为用户添加用户组集合 */
	@RequestMapping(value = "addroles",method=RequestMethod.POST)
	@ResponseBody
	public ModelAndView addRoles(@RequestBody(required=false)AuthModel authModel,ModelAndView modelAndView) {
		//验证输入
		Long userId=authModel.getId();
		//查询角色列表
		QueryCriteria queryCriteria = new QueryCriteria(0,9999);
		// 查询条件用户组ID
		if (authModel.getId()>0) {
			queryCriteria.addQueryCondition("userId",authModel.getId());
		}
		//查询所有空的
		if(!CollectionUtils.isEmpty(authModel.getAuths())){
			queryCriteria.addQueryCondition("roleIds",authModel.getAuths());
		}
		PageResult<Role> pageResult=this.roleService.doFindByCriteriaAndUser(queryCriteria);
		User user = this.userService.doFindById(userId);
		List<Long> targetRoleIds =authModel.getSelecteds();
		List<Long> deleteRoleIds = new ArrayList<Long>();
		for (Role role : pageResult.getContent()) {
			if (targetRoleIds.contains(role.getId())) {
				targetRoleIds.remove(role.getId());
			} else {
				deleteRoleIds.add(role.getId());
			}
		}
		logger.info("targetRoleIds:{}", targetRoleIds);
		logger.info("deleteRoleIds:{}", deleteRoleIds);
		this.userService.doAuthRoleToUser(targetRoleIds, deleteRoleIds, user);
		Map<String, String> params = new HashMap<String, String>();
		params.put(BindResult.SUCCESS, ConstantMessage.ADD_SUCCESS_MESSAGE_CODE);
		modelAndView.addObject(BindMessage.SUCCESS, params);
		return this.getJsonView(modelAndView);
	}
	/***
	 * 根据ID删除用户数据，超级用户不能删除
	 * @param id
	 * @param modelAndView
	 * @return
	 */
	@RequestMapping(value = "delete")
	@ResponseBody
	public ModelAndView delete(@RequestParam Long id, ModelAndView modelAndView) {
		Map<String, String> params = new HashMap<String, String>();	    
		//检查用户是否超级删除
		if(this.userService.checkSuperUserById(id)){
			params.put(BindResult.SUCCESS,"sys.user.super.username.delete");
			modelAndView.addObject(BindMessage.SUCCESS, params);
			return this.getJsonView(modelAndView);
		}
		
		int updateRow= this.userService.doDeleteById(id);
		if (updateRow>0) {
			params.put(BindResult.SUCCESS,ConstantMessage.DELETE_SUCCESS_MESSAGE_CODE);
		} else {
			params.put(BindResult.SUCCESS,ConstantMessage.DELETE_FAIL_MESSAGE_CODE);
		}
		modelAndView.addObject(BindMessage.SUCCESS, params);
		return this.getJsonView(modelAndView);
	}
	
	/****
	 * 重置密码功能
	 * @param id 用户ID
	 * @param modelAndView
	 * @return 
	 */
	@RequestMapping(value="resetpwd",method=RequestMethod.POST)
	@ResponseBody
	public ModelAndView resetPwd(UserVo userVo,Errors errors,ModelAndView modelAndView){
		 
		Map<String, String> params = new HashMap<String, String>();
		User user = this.userService.doFindById(userVo.getId());
		if(user==null){
			errors.reject("sys.user.resetpwd.empty","");
		}
		if(!errors.hasErrors()&&this.userService.getSuperUsername().equals(user.getUsername())){
			errors.reject("sys.user.super.resetpwd","");
		}
		//再次检查是否出错
		if (errors.hasErrors()) {
			modelAndView.addObject(BindMessage.ERRORS_MODEL_KEY, errors);
			return this.getJsonView(modelAndView);
		}
		String password=passwordEncoderService.encodePassword(DEFAULT_PASSWORD,user.getUsername());
		user.setPassword(password);
		user=this.userService.doUpdate(user);
		if (user!=null) {
			params.put(BindResult.SUCCESS,ConstantMessage.RESET_PASSWORD_SUCCESS_MESSAGE_CODE);
		} else {
			params.put(BindResult.SUCCESS,ConstantMessage.RESET_PASSWORD_FAIL_MESSAGE_CODE);
		}
		modelAndView.addObject(BindMessage.SUCCESS, params);
		return this.getJsonView(modelAndView);
	}
	
	/****
	 * 重置密码功能
	 * @param id 用户ID
	 * @param modelAndView
	 * @return 
	 */
	@RequestMapping(value="lock",method=RequestMethod.POST)
	@ResponseBody
	public ModelAndView lock(UserVo userVo,Errors errors,ModelAndView modelAndView){
		 
		Map<String, String> params = new HashMap<String, String>();
		User user = this.userService.doFindById(userVo.getId());
		if(user==null){
			errors.reject("sys.user.lock.empty","");
		}
		if(!errors.hasErrors()&&this.userService.getSuperUsername().equals(user.getUsername())){
			errors.reject("sys.user.lock.superuser","");
		}
		//再次检查是否出错
		if (errors.hasErrors()) {
			modelAndView.addObject(BindMessage.ERRORS_MODEL_KEY, errors);
			return this.getJsonView(modelAndView);
		}
		if(!user.getAccountLocked()){
			user.setAccountLocked(Boolean.TRUE);
			user=this.userService.doUpdate(user);
			if (user!=null) {
				params.put(BindResult.SUCCESS,"sys.user.lock.success");
			} else {
				params.put(BindResult.SUCCESS,"sys.user.lock.fail");
			}
		}else{
			 params.put(BindResult.SUCCESS,"sys.user.lock.already");
		}
		
		modelAndView.addObject(BindMessage.SUCCESS, params);
		return this.getJsonView(modelAndView);
	}
	
	@RequestMapping(value="unlock",method=RequestMethod.POST)
	@ResponseBody
	public ModelAndView unlock(UserVo userVo,Errors errors,ModelAndView modelAndView){
		 
		Map<String, String> params = new HashMap<String, String>();
		User user = this.userService.doFindById(userVo.getId());
		if(user==null){
			errors.reject("sys.user.unlock.empty","");
		}
		if(!errors.hasErrors()&&this.userService.getSuperUsername().equals(user.getUsername())){
			errors.reject("sys.user.lock.superuser","");
		}
		//再次检查是否出错
		if (errors.hasErrors()) {
			modelAndView.addObject(BindMessage.ERRORS_MODEL_KEY, errors);
			return this.getJsonView(modelAndView);
		}
		if(user.getAccountLocked()){
			user.setAccountLocked(Boolean.FALSE);
			user=this.userService.doUpdate(user);
			if (user!=null) {
				params.put(BindResult.SUCCESS,"sys.user.unlock.success");
			} else {
				params.put(BindResult.SUCCESS,"sys.user.unlock.fail");
			}
		}else{
			 params.put(BindResult.SUCCESS,"sys.user.unlock.already");
		}
		modelAndView.addObject(BindMessage.SUCCESS, params);
		return this.getJsonView(modelAndView);
	}
	 
	/** 编辑对话框 */
	@RequestMapping(value = "editdialog")
	public String editDialog(@RequestParam(required = false) Long id,
			Model model) {
		User user = this.userService.doFindById(id);
		model.addAttribute("user", user);
		return EDIT_DIALOG_RETURN;
	}

	/** 编辑提交 */
	@RequestMapping(value = "edit")
	@ResponseBody
	public ModelAndView edit(@Valid @ModelAttribute("userVo") UserVo userVo,
			Errors errors, ModelAndView modelAndView) {

		User user = null;
		if (userVo.getId() != null) {
			user = this.userService.doFindById(userVo.getId());
			if (user == null) {
				errors.reject(ConstantMessage.EDIT_ISEMPTY_FAIL_MESSAGE_CODE);
			}
		} else {
			errors.reject(ConstantMessage.EDIT_ISEMPTY_FAIL_MESSAGE_CODE);
		}

		if (errors.hasErrors()) {
			modelAndView.addObject(BindMessage.ERRORS_MODEL_KEY, errors);
			return this.getJsonView(modelAndView);
		}
		//检查用户是否已存在
		if(userService.checkUsername(userVo.getUsername(),userVo.getId())){
			errors.rejectValue("username",	"sys.user.username.existed.message",new Object[] {userVo.getUsername()}, null);
		}
		//检查员工号是否已存在
		if(userService.checkEmployeeCode(userVo.getEmployeeCode(),userVo.getId())){
			errors.rejectValue("employeeCode",	"sys.user.employeecode.existed",new Object[] {userVo.getEmployeeCode()}, null);
		}
		//检查邮箱是否已经存在
		if(userService.checkEmail(userVo.getEmail(),userVo.getId())){
			errors.rejectValue("email",	"sys.user.email.existed",new Object[] {userVo.getEmail()}, null);
		}
		//再次检查是否出错
		if (errors.hasErrors()) {
			modelAndView.addObject(BindMessage.ERRORS_MODEL_KEY, errors);
			return this.getJsonView(modelAndView);
		}
		
		//userVo.setAccountExpiredDate(user.getAccountExpiredDate());
		//userVo.setCredentialExpiredDate(user.getCredentialExpiredDate());
		userVo.setPassword(user.getPassword());

		BeanUtils.copyProperties(userVo, user);
		//去空格 用户名、员工号、邮箱的空格
		user.setUsername(userVo.getUsername().trim());
		user.setEmployeeCode(userVo.getEmployeeCode().trim());
		user.setEmail(userVo.getEmail().trim());
		// 将登录用户转换为小写
		user.setUsername(user.getUsername().toLowerCase());
		this.userService.doUpdate(user);
		Map<String, String> params = new HashMap<String, String>();
		params.put(BindResult.SUCCESS,ConstantMessage.EDIT_SUCCESS_MESSAGE_CODE);
		modelAndView.addObject(BindMessage.SUCCESS, params);
		return this.getJsonView(modelAndView);
	}

	/** 列表显示 */
	@RequestMapping(value = "list")
	@ResponseBody
	public DataTable<User> lists(@ModelAttribute("queryDt")QueryDt queryDt,@ModelAttribute("user") UserVo userVo) {
		QueryCriteria queryCriteria = new QueryCriteria();
		// 设置分页 启始页
		queryCriteria.setStartIndex(queryDt.getPage());
		// 每页大小
		queryCriteria.setPageSize(queryDt.getRows());
		// 设置排序字段及排序方向
		if (StringUtils.isNotEmpty(queryDt.getSort()) && StringUtils.isNotEmpty(queryDt.getOrder())) {
			queryCriteria.setOrderField(queryDt.getSort());
			queryCriteria.setOrderDirection(queryDt.getOrder());
		} else {
			queryCriteria.setOrderField(DEFAULT_ORDER_FILED_NAME);
		}
		// 查询条件 参数类型 用户名
		if (StringUtils.isNotEmpty(userVo.getUsername())) {
			queryCriteria.addQueryCondition("username", "%" + userVo.getUsername() + "%");
		}
		
		// 查询条件 参数类型 员工号
		if (StringUtils.isNotEmpty(userVo.getEmployeeCode())) {
			queryCriteria.addQueryCondition("employeeCode", "%" + userVo.getEmployeeCode() + "%");
		}
		// 查询条件 参数类型 邮箱
		if (StringUtils.isNotEmpty(userVo.getEmail())) {
			queryCriteria.addQueryCondition("email", "%" + userVo.getEmail()+ "%");
		}
		
		PageResult<User> pageResult = this.userService.doFindByCriteria(queryCriteria);
		for(User user:pageResult.getContent()){
				user.getDepartment().getNameZh();
		}
		return pageResult.getDataTable(queryDt.getRequestId());
	}

	
	/****
	 * 
	 * @param tableId
	 * @param parameterVo
	 * @param modelAndView
	 * @return
	 */
	@RequestMapping(value = "export")
	@ResponseBody
	public ModelAndView exportExcel(@ModelAttribute("queryVo")QueryVo queryVo,@ModelAttribute("user") UserVo userVo,Errors errors, ModelAndView modelAndView){
				
		DataGrid dataGrid=dataGridService.doFindByTableIdAndSort(queryVo.getTableId());
		QueryCriteria queryCriteria=new QueryCriteria();
		queryCriteria.setPageSize(10000);
	 
		// 设置分页 启始页
		queryCriteria.setStartIndex(queryVo.getRows() * (queryVo.getPage() - 1));
		// 每页大小
		queryCriteria.setPageSize(queryVo.getRows());
		// 设置排序字段及排序方向
		if (StringUtils.isNotEmpty(queryVo.getSort()) && StringUtils.isNotEmpty(queryVo.getOrder())) {
			queryCriteria.setOrderField(queryVo.getSort());
			queryCriteria.setOrderDirection(queryVo.getOrder());
		} else {
			queryCriteria.setOrderField(DEFAULT_ORDER_FILED_NAME);
		}
		// 查询条件 参数类型 用户名
		if (StringUtils.isNotEmpty(userVo.getUsername())) {
			queryCriteria.addQueryCondition("username", "%" + userVo.getUsername() + "%");
		}
		
		// 查询条件 参数类型 员工号
		if (StringUtils.isNotEmpty(userVo.getEmployeeCode())) {
			queryCriteria.addQueryCondition("employeeCode", "%" + userVo.getEmployeeCode() + "%");
		}
		// 查询条件 参数类型 邮箱
		if (StringUtils.isNotEmpty(userVo.getEmail())) {
			queryCriteria.addQueryCondition("email", "%" + userVo.getEmail()+ "%");
		}

		PageResult<User> result=this.userService.doFindByCriteria(queryCriteria);
		Map<String, Map<Object, Object>> fieldDepartment= new HashMap<String, Map<Object, Object>>();
		fieldDepartment.put("department",null);

		Map<String, String> dataFormats = new HashMap<String, String>();		
		dataFormats.put("accountExpiredDate", DateUtil.FORMAT_DATE_YYYY_MM_DD);
		//创建.xls的文件名
		String fileName=this.createFileName(FileUtil.EXCEL_EXTENSION);
		
		modelAndView.addObject("title",dataGrid.getTitle());
		
		Long startTime=System.currentTimeMillis();
		
		fileName=excelExportService.export(dataGrid, result.getContent(), fileName,fieldDepartment,dataFormats);
		
		logger.info("fileName:{}",fileName);
		
		Long costTime=System.currentTimeMillis()-startTime;
		
		modelAndView.addObject(FILENAME,fileName);
		
		logger.info("export Excel {} cost:{} time,fileName:{}",dataGrid.getTitle(),costTime,fileName);
		logger.info("out Excel导出");
		return this.getExcelView(modelAndView);
	}
	
	
	@RequestMapping(value = "pwdindex")
	public String loadUserEditPasswordPage(HttpServletRequest request,
			Model model) {
		UserInfo userInfo=LoginSecurityUtil.getUser();
		// 获取用户登录的IP地址
		User user = this.userService.doFindById(userInfo.getId());
		model.addAttribute("userDetails", user);
		return EDIT_USER_PASSWORD_RETURN;
	}


	/** 我的信息 */
	@RequestMapping(value = "userinfo")
	public String userInfo(Model model) {	 
		model.addAttribute("user", LoginSecurityUtil.getUser());
		return USERINFO_RETURN;
	}

	/** 我的资源 */
	@RequestMapping(value = "myresource")
	@ResponseBody
	public String myResource() {
		
		UserInfo userInfo =LoginSecurityUtil.getUser();
		
		List<Resource> resources = resourceService.doFindFirstLevel();

		List<Resource> userResources = this.resourceService.doFindByUserId(userInfo.getId());

		Map<Long, Resource> menuResourcesMap = ResourceConvertUtil.convertListToMap(userResources);
		
		List<TreeNode> children = ResourceTreeUtil.resourceAttrUser(resources,menuResourcesMap, Boolean.TRUE, 0);
		
		Set<String> properties = new HashSet<String>();		
		properties.add("id");
		properties.add("text");
		properties.add("checked");
		properties.add("state");
		properties.add("path");
		properties.add("attributes");
		properties.add("children");
		String result = JSONParser.toJSONString(children, properties);
		return result;
	}
}
