/*
* Copyright (c)  2014, Newtouch
* All rights reserved. 
*
* $id: IndexController.java 9552 2014年12月30日 上午10:10:29 WangLijun$
*/
package com.newtouch.lion.admin.web.controller;

import com.newtouch.lion.web.controller.AbstractController;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * <p>
 * Title: 后台管理首页Controller
 * </p>
 * <p>
 * Description: 后台管理首页Controller
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
@RequestMapping("/")
public class IndexController extends  AbstractController{
	/**首页模板路径*/
	private static final String INDEX_RETURN="/index";
	/**测试页面*/
	private static final String TEST_RETURN="/test";
	/**选择页面*/
	private static final String COMBO_RETURN="/combo";
	
	/**首页测试版进入index.html**/
//	private static final String INDEX_RETURN_HTML="/html/index";
	//
	@RequestMapping("index")
	public String index(){
		//权限校验。判断是否包含权限。
		Subject subject = SecurityUtils.getSubject();
		//具体响应ShiroDbRealm。doGetAuthorizationInfo，判断是否包含此url的响应权限
		boolean isPermitted = subject.isPermitted("/index.htm");
		logger.info("isPermitted:{}",isPermitted);
		logger.info("进入首页.....");
		System.out.println("===================");
		return INDEX_RETURN;
	}
	
	
	@RequestMapping("test")
	public String test(){
        logger.info(" request.getRequestURL():{}",this.getRequest().getRequestURL());
        logger.info(" request.getRequestURI():{}",this.getRequest().getRequestURI());
        logger.info(":{}",this.getRequest().getContextPath());
		logger.info("进入测试页面");
		return TEST_RETURN;
	}
	
	@RequestMapping("test2")
	public String test2(){
        logger.info(" request.getRequestURL():{}",this.getRequest().getRequestURL());
        logger.info(" request.getRequestURI():{}",this.getRequest().getRequestURI());
        logger.info(":{}",this.getRequest().getContextPath());
		logger.info("进入测试页面");
		return TEST_RETURN;
	}
	
	@RequestMapping("combo")
	public String combo(){
		return COMBO_RETURN;
	}
	
	
}

	