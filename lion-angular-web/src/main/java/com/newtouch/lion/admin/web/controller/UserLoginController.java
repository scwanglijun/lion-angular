/*
 * Copyright (c)  2014, Newtouch
 * All rights reserved. 
 *
 * $id: LoginController.java 9552 2014年12月30日 上午10:24:10 WangLijun$
 */
package com.newtouch.lion.admin.web.controller;

import com.newtouch.lion.admin.web.model.user.LoginUserReq;
import com.newtouch.lion.admin.web.model.user.LoginUserResp;
import com.newtouch.lion.common.user.UserInfo;
import com.newtouch.lion.service.system.LoginLogService;
import com.newtouch.lion.web.controller.AbstractController;
import com.newtouch.lion.web.shiro.authc.CredentialExpiredException;
import com.newtouch.lion.web.shiro.authc.ExpiredAccountException;
import com.newtouch.lion.web.shiro.cache.SessionCacheManager;
import com.newtouch.lion.web.shiro.session.LoginSecurityUtil;
import com.newtouch.lion.webtrans.trans.Trans;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

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
public class UserLoginController extends AbstractController {

	/** Shiro Session缓存管理*/
	@Autowired
	private SessionCacheManager sessionCacheManager;
	
	/**登陆日志*/
	@Autowired
	private LoginLogService loginLogService;
	/***
	 * 接收登录请求
	 * @param req
	 * @return
	 */
	@Trans(value = "user.login")
	public LoginUserResp login(LoginUserReq req) {
		logger.info("进入登录页面");
		UserInfo userInfo = LoginSecurityUtil.getUser();
		//获取当前的Subject
		UsernamePasswordToken token=new UsernamePasswordToken(req.getUsername(), req.getPassword(),req.getRememberMe());
		//token.setRememberMe();
		//获取当前的Subject
		Subject currentUser=SecurityUtils.getSubject();
		try{
			logger.info("验证用户和密码开始...");
			currentUser.login(token);
			logger.info("验证用户和密码结束...");
			//
		}catch(UnknownAccountException e){
			logger.error(e.getMessage(),e);
			return new LoginUserResp(LoginUserResp.UNKNOWN_ACCOUNT_CODE,LoginUserResp.UNKNOWN_ACCOUNT_MESSAGE);
		}catch(IncorrectCredentialsException e){
			logger.error(e.getMessage(),e);
            return new LoginUserResp(LoginUserResp.INCORRECT_CREDENTIALS_CODE,LoginUserResp.INCORRECT_CREDENTIALS_MESSAGE);
		}catch(LockedAccountException e){
			logger.error(e.getMessage(),e);
            return new LoginUserResp(LoginUserResp.LOCKED_ACCOUNT_CODE,LoginUserResp.LOCKED_ACCOUNT_MESSAGE);
		}catch(ExpiredAccountException e){
			logger.error(e.getMessage(),e);
            return new LoginUserResp(LoginUserResp.EXPIRE_ACCOUNT_CODE,LoginUserResp.EXPIRE_ACCOUNT_MESSAGE);
		}catch(CredentialExpiredException e){
			logger.error(e.getMessage(),e);
            return new LoginUserResp(LoginUserResp.CREDENTIAL_EXPIRED_CODE,LoginUserResp.CREDENTIAL_EXPIRED_MESSAGE);
		}catch(AuthenticationException e){
			logger.error(e.getMessage(),e);
            return new LoginUserResp(LoginUserResp.AUTHENTICATION_EXCEPTION_CODE,LoginUserResp.AUTHENTICATION_EXCEPTION_MESSAGE);
		}
		if(currentUser.isAuthenticated()){
			//logger.info("用户名:{}，ID：{} 已经登录，重定向到首页", loginUser.getUsername(),userInfo.getId());
			return new LoginUserResp(LoginUserResp.SUCCESS_CODE,LoginUserResp.SUCCESS_MESSAGE);
		}else{
			 token.clear(); 
		}
		return new LoginUserResp(LoginUserResp.SUCCESS_CODE,LoginUserResp.SUCCESS_MESSAGE);
	}

	@Trans(value="user.logout")
	public void logout(LoginUserReq req){
		logger.info("退出系统");
		Subject subject = SecurityUtils.getSubject();
		if (subject.isAuthenticated()) {
			UserInfo userInfo = LoginSecurityUtil.getUser();
			//退出
			//saveLoginLog(userInfo,"success","logout");s
			// session 会销毁，在SessionListener监听session销毁，清理权限缓存
			subject.logout();

			logger.info("退出系统成功");
			//清除缓存
			//sessionCacheManager.removeSessionController(Constants.CACHE_SESSION_NAME,userInfo.getUsername());
		}
	}
}
