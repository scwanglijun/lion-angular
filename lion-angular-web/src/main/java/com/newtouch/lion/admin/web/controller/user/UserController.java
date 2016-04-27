
/*
* Copyright (c)  2016, Newtouch
* All rights reserved. 
*
* $id: UserController.java 9552 2016年3月30日 上午9:54:07 LiJie$
*/
package com.newtouch.lion.admin.web.controller.user;

import com.newtouch.lion.admin.web.model.user.*;
import com.newtouch.lion.cache.system.ParameterUtil;
import com.newtouch.lion.common.constant.Constants;
import com.newtouch.lion.common.date.DateUtil;
import com.newtouch.lion.model.system.Department;
import com.newtouch.lion.model.system.User;
import com.newtouch.lion.page.PageResult;
import com.newtouch.lion.query.QueryCriteria;
import com.newtouch.lion.service.system.DepartmentService;
import com.newtouch.lion.service.system.UserService;
import com.newtouch.lion.web.page.Page;
import com.newtouch.lion.web.shiro.credentials.PasswordEncoder;
import com.newtouch.lion.web.util.QueryUtils;
import com.newtouch.lion.webtrans.trans.Trans;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * <p>
 * Title: 
 * </p>
 * <p>
 * Description: 
 * </p>
 * <p>
 * Copyright: Copyright (c) 2016
 * </p>
 * <p>
 * Company: Newtouch
 * </p>
 * 
 * @author LiJie
 * @version 1.0
 */
@Controller
public class UserController {
	@Autowired
	private UserService userService;
	@Autowired
	private PasswordEncoder passwordEncoderService;

	/** 默认排序字段*/
	private static final String DEFAULT_ORDER_FILED_NAME="id";
	/** 默认密码 */
	private static final String DEFAULT_PASSWORD = ParameterUtil.getValue(Constants.DEFLAUT_PASSWORD_KEY);
	 
	@Trans("system.user.list")
	public Page<UserGetResp> list(UserGetReq req) {
		//排序
		QueryCriteria queryCriteria = QueryUtils.pageFormat(new QueryCriteria() ,req);
		// 设置排序字段及排序方向
		queryCriteria.setOrderField(DEFAULT_ORDER_FILED_NAME);
		queryCriteria.setOrderDirection(QueryCriteria.ASC);
		// 查询条件 参数类型 用户名
		if (StringUtils.isNotEmpty(req.getUsername())) {
			queryCriteria.addQueryCondition("username", "%" + req.getUsername() + "%");
		}

		// 查询条件 参数类型 员工号
		if (StringUtils.isNotEmpty(req.getEmployeeCode())) {
			queryCriteria.addQueryCondition("employeeCode", "%" + req.getEmployeeCode() + "%");
		}
		// 查询条件 参数类型 邮箱
		if (StringUtils.isNotEmpty(req.getEmail())) {
			queryCriteria.addQueryCondition("email", "%" + req.getEmail()+ "%");
		}
		PageResult<User> pageResult = userService.doFindByCriteria(queryCriteria);
		List<UserGetResp> list = new ArrayList<UserGetResp>();

		for (User user : pageResult.getContent()){
			UserGetResp userGetResp = new UserGetResp();
			BeanUtils.copyProperties(user,userGetResp);
			String accountExpiredDate  = DateUtil.formatDate(user.getAccountExpiredDate(),DateUtil.FORMAT_DATE_YYYY_MM_DD);
			String credentialExpiredDate  = DateUtil.formatDate(user.getCredentialExpiredDate(),DateUtil.FORMAT_DATE_YYYY_MM_DD);
			userGetResp.setAccountExpiredDate(accountExpiredDate);
			userGetResp.setCredentialExpiredDate(credentialExpiredDate);
			userGetResp.setDepartment(user.getDepartment().getNameZh());
			if(user.getAccountExpired() == false) {
				userGetResp.setAccountExpired("无效");
			} else {
				userGetResp.setAccountExpired("有效");
			}
			if(user.getAccountLocked() == false) {
				userGetResp.setAccountLocked("未锁定");
			} else {
				userGetResp.setAccountLocked("已锁定");
			}
			list.add(userGetResp);
		}

		PageResult<UserGetResp> pageResultResp = new PageResult<UserGetResp>();
		BeanUtils.copyProperties(pageResult,pageResultResp);
		pageResultResp.setContent(list);

		return new Page(pageResultResp);
	}
	//添加用户信息
	/** add*/
	@Trans("system.user.add")
	public UserAddResp add(UserAddReq req){
		//检查用户是否已存在
		if(userService.checkUsername(req.getUsername())){
			return new UserAddResp(UserAddResp.FAIL_USER_ADD_CODE,UserAddResp.FAIL_USER_ADD_MESSAGE);
		}
		//检查员工号是否已存在
		if(userService.checkEmployeeCode(req.getEmployeeCode())){
			return new UserAddResp(UserAddResp.FAIL_USER_ADD_CODE,UserAddResp.FAIL_USER_ADD_MESSAGE);
		}
		//检查邮箱是否已经存在
		if(userService.checkEmail(req.getEmail())){
			return new UserAddResp(UserAddResp.FAIL_USER_ADD_CODE,UserAddResp.FAIL_USER_ADD_MESSAGE);
		}
		User user = new User();
		BeanUtils.copyProperties(req, user);
		//去空格 用户名、员工号、邮箱的空格
		user.setUsername(req.getUsername().trim());
		user.setEmployeeCode(req.getEmployeeCode().trim());
		user.setEmail(req.getEmail().trim());
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.YEAR, 1);

		Calendar credentialExpiredCalendar = Calendar.getInstance();
		credentialExpiredCalendar.add(Calendar.MONTH,3);

		user.setAccountExpiredDate(calendar.getTime());
		user.setCredentialExpiredDate(credentialExpiredCalendar.getTime());
		String passwordEncoder = passwordEncoderService.encodePassword(DEFAULT_PASSWORD,user.getUsername());
		// 密码加密码
		user.setPassword(passwordEncoder);
		// 将登录用户转换为小写
		user.setUsername(user.getUsername().toLowerCase());
		userService.doCreateUser(user);
		return new UserAddResp(UserAddResp.SUCCESS_USER_ADD_CODE,UserAddResp.SUCCESS_USER_ADD_MESSAGE);
	}

	/***
	 * 根据ID删除用户数据，超级用户不能删除
	 * @param req
	 * @return UserDelResp
	 */
	@Trans("system.user.delete")
	public UserDelResp del(UserDelReq req){

		//检查用户是否超级删除
		if(this.userService.checkSuperUserById(req.getId())){
			return new UserDelResp(UserDelResp.FAIL_USER_DELETE_CODE,UserDelResp.FAIL_USER_DELETE_MESSAGE);
		}

		int updateRow= this.userService.doDeleteById(req.getId());
		if (updateRow>0) {
			return new UserDelResp(UserDelResp.SUCCESS_USER_DELETE_CODE, UserDelResp.SUCCESS_USER_DELETE_MESSAGE);
		}
		return new UserDelResp(UserDelResp.FAIL_USER_DELETE_CODE, UserDelResp.FAIL_USER_DELETE_MESSAGE);
	}

	//编辑用户信息
	/**edit*/
	@Trans("system.user.edit")
	public UserEditResp edit(UserEditReq req){
		User user = this.userService.doFindById(req.getId());
		if (user == null) {
			return new UserEditResp(UserEditResp.FAIL_USER_EDIT_CODE, UserEditResp.FAIL_USER_EDIT_MESSAGE);
		}
		//检查用户是否已存在
		if(userService.checkUsername(req.getUsername(),req.getId())){
			return new UserEditResp(UserEditResp.FAIL_USER_EDIT_CODE, UserEditResp.FAIL_USER_EDIT_MESSAGE);
		}
		//检查员工号是否已存在
		if(userService.checkEmployeeCode(req.getEmployeeCode(),req.getId())){
			return new UserEditResp(UserEditResp.FAIL_USER_EDIT_CODE, UserEditResp.FAIL_USER_EDIT_MESSAGE);
		}
		//检查邮箱是否已经存在
		if(userService.checkEmail(req.getEmail(),req.getId())){
			return new UserEditResp(UserEditResp.FAIL_USER_EDIT_CODE, UserEditResp.FAIL_USER_EDIT_MESSAGE);
		}
		req.setPassword(user.getPassword());

		BeanUtils.copyProperties(req, user);
		//去空格 用户名、员工号、邮箱的空格
		user.setUsername(req.getUsername().trim());
		user.setEmployeeCode(req.getEmployeeCode().trim());
		user.setEmail(req.getEmail().trim());
		// 将登录用户转换为小写
		user.setUsername(user.getUsername().toLowerCase());
		userService.doUpdate(user);
		return new UserEditResp(UserEditResp.SUCCESS_USER_EDIT_CODE,UserEditResp.SUCCESS_USER_EDIT_MESSAGE);
	}



}
