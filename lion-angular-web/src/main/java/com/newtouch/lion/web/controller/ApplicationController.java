/*
 * Copyright (c)  2014, Newtouch
 * All rights reserved. 
 *
 * $id: ApplicationController.java 9552 2014-2-18 上午10:58:46 WangLijun$
 */
package com.newtouch.lion.web.controller;

import com.newtouch.lion.model.application.ApplicationInfo;
import com.newtouch.lion.model.application.DataBaseInfo;
import com.newtouch.lion.service.application.ApplicationInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;


/**
 * <p>
 * Title:应用管理控制类
 * </p>
 * <p>
 * Description: 应用管理控制类
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
@RequestMapping("/system/application")
public class ApplicationController {

	@Autowired
	private ApplicationInfoService aplicationInfoService;

	private final String INDEX_RETURN = "system/application/index";

	@RequestMapping("/index")
	public String index(Model model) {
		/** 应用基础信息 */
		ApplicationInfo applicationInfo = aplicationInfoService
				.getApplicationInfo();
		/** 应用数据库基础信息 */
		DataBaseInfo dataBaseInfo = aplicationInfoService.getDataBaseInfo();
		/** 应用授权信息 */
		//AuthorizeInfo authorizeInfo = aplicationInfoService.getAuthorizeInfo();
		//TODO
		model.addAttribute("applicationInfo", applicationInfo);

		model.addAttribute("dataBaseInfo", dataBaseInfo);

		//model.addAttribute("authorizeInfo", authorizeInfo);

		return INDEX_RETURN;
	}
}
