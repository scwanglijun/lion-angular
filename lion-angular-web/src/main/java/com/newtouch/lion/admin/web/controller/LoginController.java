/*
 * Copyright (c)  2014, Newtouch
 * All rights reserved. 
 *
 * $id: LoginController.java 9552 2014年12月30日 上午10:24:10 WangLijun$
 */
package com.newtouch.lion.admin.web.controller;

import com.newtouch.lion.common.ip.IPAddressUtil;
import com.newtouch.lion.common.user.UserInfo;
import com.newtouch.lion.model.system.LoginLog;
import com.newtouch.lion.service.system.LoginLogService;
import com.newtouch.lion.web.controller.AbstractController;
import com.newtouch.lion.web.shiro.authc.CredentialExpiredException;
import com.newtouch.lion.web.shiro.authc.ExpiredAccountException;
import com.newtouch.lion.web.shiro.cache.SessionCacheManager;
import com.newtouch.lion.web.shiro.constant.Constants;
import com.newtouch.lion.web.shiro.model.LoginUser;
import com.newtouch.lion.web.shiro.session.LoginSecurityUtil;
import com.newtouch.lion.web.vo.login.resp.LoginResp;
import com.newtouch.lion.webtrans.trans.Trans;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

/**
 * <p>
 * Title: 用户登录Controller
 * </p>
 * <p>
 * Description: 用户登录Controller
 * </p>
 * <p>
 * Copyright: Copyright (c) 2014
 * </p>
 * <p>
 * Company: Newtouch
 * </p>
 * 
 * @author WangLijun
 * @version 1.0
 */
@Controller
public class LoginController extends AbstractController {

	/** Shiro Session缓存管理*/
	@Autowired
	private SessionCacheManager sessionCacheManager;
	
	/**登陆日志*/
	@Autowired
	private LoginLogService loginLogService;
	/***
	 * 接收登录请求
	 * @param loginUser
	 * @param model
	 * @return
	 */
	@Trans(value = "user.login")
	public LoginResp login(LoginUser loginUser,Model model) {
		logger.info("进入登录页面");
		UserInfo userInfo = LoginSecurityUtil.getUser();
		//获取当前的Subject
		UsernamePasswordToken token=new UsernamePasswordToken(loginUser.getUsername(), loginUser.getPassword(),loginUser.getRememberMe());
		//token.setRememberMe();
		//获取当前的Subject
		Subject currentUser=SecurityUtils.getSubject();
		try{
			logger.info("验证用户和密码开始...");
			currentUser.login(token);
			logger.info("验证用户和密码结束...");
			//
		}catch(UnknownAccountException e){
			model.addAttribute(Constants.LOGIN_ERROR_MSG,"用户或密码不正确.");
			logger.error(e.getMessage(),e);
		}catch(IncorrectCredentialsException e){
			model.addAttribute(Constants.LOGIN_ERROR_MSG,"用户或密码不正确.");
			logger.error(e.getMessage(),e);
		}catch(LockedAccountException e){
			logger.error(e.getMessage(),e);
			model.addAttribute(Constants.LOGIN_ERROR_MSG,"用户已锁定.");
		}catch(ExpiredAccountException e){
			logger.error(e.getMessage(),e);
			model.addAttribute(Constants.LOGIN_ERROR_MSG,"用户已过期，请联系管理员.");
		}catch(CredentialExpiredException e){
			logger.error(e.getMessage(),e);
			model.addAttribute(Constants.LOGIN_ERROR_MSG,"密码已过期，请联系管理员.");	
		}catch(AuthenticationException e){
			model.addAttribute(Constants.LOGIN_ERROR_MSG,"用户或密码不正确.");
			logger.error(e.getMessage(),e);
		}
		if(currentUser.isAuthenticated()){
			logger.info("用户名:{}，ID：{} 已经登录，重定向到首页", loginUser.getUsername(),userInfo.getId());
			model.asMap().clear();
			return new LoginResp("201","已经登录，重定向到首页");
		}else{
			 token.clear(); 
		}
		return new LoginResp("200","登录成功");
	}
	

}
