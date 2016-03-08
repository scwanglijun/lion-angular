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
@Controller("adminLoginController1")
public class LoginController extends AbstractController {
	/** 进入登录页面 */
	private static final String LOGIN_RETURN = "/login";
	/** 登录成功 */
	private static final String LOGIN_SUCCESS = "/index.htm";
	/**重定向到登录*/
	private static final String REDIRECT_LOGIN="/login.htm";
	/**未授权页面*/
	private static final String UNAUTHORIZED_RETURN="/unauthorized";
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
	@RequestMapping(value = "/login",method=RequestMethod.POST)
	public String login(LoginUser loginUser,Model model) {
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
			//
			//saveLoginLog(userInfo,"success","login");
			return this.redirect(LOGIN_SUCCESS);
		}else{
			 //saveLoginLog(userInfo,"error","login");
			 token.clear(); 
		}
		return LOGIN_RETURN;
	}
	
	
	/****
	 * 登录跳转
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/login",method=RequestMethod.GET)
	public String welcome(Model model) {
		String forcelogout=this.getRequest().getParameter(Constants.FORCE_LOGOUT);
		if(Constants.LOGIN_FORCE_ERROR.equals(forcelogout)){
			model.addAttribute(Constants.LOGIN_ERROR_MSG,"您被系统管理员强制退出系统，请联系系统管理员.");
		}else if(Constants.LOGIN_MAXS_ERROR.equals(forcelogout)){
			model.addAttribute(Constants.LOGIN_ERROR_MSG,"您的用户名已登录系统正在使用中…如有疑问请联系系统管理员.");
		}
		UserInfo userInfo = LoginSecurityUtil.getUser();
		if (userInfo != null) {
			logger.info("用户名:{}，ID：{} 已经登录，重定向到首页", userInfo.getUsername(),userInfo.getId());
			return this.redirect(LOGIN_SUCCESS);
		}
		return LOGIN_RETURN;
	}
	
	@RequestMapping(value = "/logout",method=RequestMethod.GET)
	public String logout(){
		logger.info("退出系统");
		Subject subject = SecurityUtils.getSubject();
		if (subject.isAuthenticated()) {
			UserInfo userInfo = LoginSecurityUtil.getUser();
			//退出
			//saveLoginLog(userInfo,"success","logout");
			// session 会销毁，在SessionListener监听session销毁，清理权限缓存
			subject.logout(); 
			//清除缓存
			sessionCacheManager.removeSessionController(Constants.CACHE_SESSION_NAME,userInfo.getUsername());
		}
		return this.redirect(REDIRECT_LOGIN);
	}
	
	/***
	 * 未授权的情况
	 */
	@RequestMapping(value="unauthorized",method=RequestMethod.GET)
	public String unauthorized(){
		return UNAUTHORIZED_RETURN;
	}
	
	
	
	
	/**
	 * 登录错误处理
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/loginerror",method=RequestMethod.GET)
	public String loginerror(Model model){
		String forcelogout=this.getRequest().getParameter(Constants.FORCE_LOGOUT);
		model.addAttribute(Constants.FORCE_LOGOUT, forcelogout);
		return  this.redirect(REDIRECT_LOGIN);
	}
	
	/**
	 * 
	 * */
	private void saveLoginLog(UserInfo userInfo,String loginResult,String loginType){
		try{
			HttpServletRequest request=super.getRequest();
			String requestHeader=request.getHeader("User-Agent");
			LoginLog loginLog=new LoginLog();
			loginLog.setUserId(userInfo.getId());
			loginLog.setLoginIP(IPAddressUtil.getIPAddress(request));
			loginLog.setLoginMAC(userInfo.getMacAddress());
			loginLog.setBrowserName(requestHeader);
			loginLog.setSessionId(request.getSession().getId());
			loginLog.setLoginResult(loginResult);
			loginLog.setLoginTime(new Date());
			if(!LoginController.isMobileDevice(requestHeader)){
				loginLog.setOsInfo("PC");
	        }else{
	        	loginLog.setOsInfo("APP");
	        }
			
			loginLog.setLoginType(loginType);
			loginLogService.save(loginLog);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	 /**
     * android : 所有android设备
     * mac os : iphone ipad
     * windows phone:Nokia等windows系统的手机
     */
	public static boolean  isMobileDevice(String requestHeader){
       
		String[] deviceArray = new String[]{"android","mac os","windows phone"};
        if(requestHeader == null)
            return false;
        requestHeader = requestHeader.toLowerCase();
        for(int i=0;i<deviceArray.length;i++){
            if(requestHeader.indexOf(deviceArray[i])>0){
                return true;
            }
        }
        return false;
    }
}
