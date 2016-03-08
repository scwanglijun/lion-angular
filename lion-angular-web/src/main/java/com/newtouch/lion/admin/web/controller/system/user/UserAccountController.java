/*
 * Copyright (c)  2014, NewTouch
 * All rights reserved. 
 *
 * $id: AccountController.java 9552 2014-4-8 下午08:19:49 WangLijun$
 */
package com.newtouch.lion.admin.web.controller.system.user;

import com.newtouch.lion.service.system.UserService;
import com.newtouch.lion.web.controller.AbstractController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * <p>
 * Title: 用户账户管理
 * </p>
 * <p>
 * Description: 用户账户管理，用于管理重置密码、锁定、解锁
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
@Controller(value = "sysUserAccount")
@RequestMapping("/system/useraccount/")
public class UserAccountController extends AbstractController{
	
	/**用户服务类*/
	@Autowired
	private UserService userService;

	/***
	 * 检查员用户名是否已存在
	 * @param employeeCode 用户名
	 * @return 存在返回false,不存在则返回true
	 */
	@RequestMapping(value = "/checkusername")
	@ResponseBody
	public Boolean checkIsExistByNameEn(@RequestParam(required = false) String username,@RequestParam(required = false) Long id) {
		return userService.checkUsername(username,id)?false:true;
	}
	
	/***
	 * 检查员工号是否已存在
	 * @param employeeCode 员工号
	 * @return  存在返回false,不存在则返回true
	 */
	@RequestMapping(value="/checkemployeecode")
	@ResponseBody
	public Boolean checkEmployeeCode(@RequestParam(required=false) String employeeCode,@RequestParam(required = false) Long id){
		 return userService.checkEmployeeCode(employeeCode,id)?false:true;
	}
	/***
	 * 检查邮箱是否已存在
	 * @param email 邮箱
	 * @return 存在返回false,不存在则返回true
	 */
	@RequestMapping(value="/checkemail")
	@ResponseBody
	public Boolean checkEmail(@RequestParam(required=false) String email,@RequestParam(required = false) Long id){
		 return this.userService.checkEmail(email,id)?false:true;
	}
}
