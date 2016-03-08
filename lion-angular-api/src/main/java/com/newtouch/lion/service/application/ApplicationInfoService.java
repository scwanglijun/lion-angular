/*
 * Copyright (c)  2013, Newtouch
 * All rights reserved. 
 *
 * $id: ApplicationInfoService.java 9552 2013-5-19 下午8:14:43 WangLijun$
 */
package com.newtouch.lion.service.application;

import java.util.Map;

import com.newtouch.lion.model.application.ApplicationInfo;
import com.newtouch.lion.model.application.AuthorizeInfo;
import com.newtouch.lion.model.application.DataBaseInfo;

/**
 * <p>
 * Title:应用基础信息服务
 * </p>
 * <p>
 * Description: 应用基础信息服务
 * </p>
 * <p>
 * Copyright: Copyright (c) 2013
 * </p>
 * <p>
 * Company: Newtouch
 * </p>
 * 
 * @author WangLijun
 * @version 1.0
 */
public interface ApplicationInfoService {

	/***
	 * 获取应用基本信息
	 * 
	 * @param Map   <String,String> params
	 * @return {@link ApplicationInfo}
	 */
	public ApplicationInfo getApplicationInfo(Map<String, String> params);

	/**
	 * 获取应用基本信息
	 * 
	 * @return {@link ApplicationInfo}
	 * */
	public ApplicationInfo getApplicationInfo();

	/**
	 * 添加获取应用基本信息
	 * 
	 * @param String
	 *            key
	 * @param Object
	 *            Object
	 * */
	public void addApplicationObject(String key, Object Object);

	/**
	 * 获取数据库信息
	 * 
	 * @return {@link DataBaseInfo}
	 * */
	public DataBaseInfo getDataBaseInfo();

	/**
	 * 授权信息
	 * 
	 * @return {@link AuthorizeInfo}
	 * */
	public AuthorizeInfo getAuthorizeInfo();

}
