/*
 * Copyright (c)  2013, Newtouch
 * All rights reserved. 
 *
 * $id: ApplicationInfoServiceImpl.java 9552 2013-5-19 下午8:17:54 WangLijun$
 */
package com.newtouch.lion.service.application.impl;

import com.newtouch.lion.common.constant.Constants;
import com.newtouch.lion.common.date.DateUtil;
import com.newtouch.lion.model.application.ApplicationInfo;
import com.newtouch.lion.model.application.AuthorizeInfo;
import com.newtouch.lion.model.application.DataBaseInfo;
import com.newtouch.lion.service.AbstractService;
import com.newtouch.lion.service.application.ApplicationInfoService;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * Title:应用基础信息服务
 * </p>
 * <p>
 * Description:应用基础信息服务
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
@Service("applicationInfoService")
public class ApplicationInfoServiceImpl extends AbstractService implements
		ApplicationInfoService {

	/** 应用程序信息 */
	private static Map<String, Object> applicationMap = new HashMap<String, Object>();

	/** 静态变量-应用信息KEY */
	public static final String APPLICATION_INFO = "ApplicationInfo";
	/** 静态变量-数据库信息 */
	public static final String DATABASE_INFO = "DatabaseInfo";
	/** 静态变量－授权信息 */
	public static final String AUTHORIZE_INFO = "AuthorizeInfo";
	
	/**系统运行时间*/
	public static Date START_DATE;
	
	static{
		START_DATE=new Date();
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.lion.framework.service.application.ApplicationInfoService#
	 * getApplicationInfo()
	 */
	@Override
	public ApplicationInfo getApplicationInfo(Map<String, String> params) {
		ApplicationInfo applicationInfo = (ApplicationInfo) applicationMap
				.get(APPLICATION_INFO);
		Runtime runtime = Runtime.getRuntime();
		Long freeMemory = runtime.freeMemory() / 1024 / 1024;
		Long totalMemory = runtime.totalMemory() / 1024 / 1024;
		Long maxMemory = runtime.maxMemory() / 1024 / 1024;
		// TODO Auto-generated method stub
		if (applicationInfo != null) {
			applicationInfo.setFreeMemory(freeMemory);
			applicationInfo.setTotalMemory(totalMemory);
			applicationInfo.setMaxMemory(maxMemory);
			applicationInfo.setMaxUseMemory(maxMemory - totalMemory
					+ freeMemory);
			return applicationInfo;
		}
		applicationInfo = new ApplicationInfo();
		// 应用名称
		applicationInfo.setName(Constants.APPLICATION_NAME);
		// 应用版本
		applicationInfo.setVersion(Constants.APPLICATION_VERSION);
		// 应用启动的时间
		applicationInfo.setStartDate(DateUtil.formatDate(START_DATE,DateUtil.FORMAT_DATETIME_YYYY_MM_DD_HH_MM_SS));
		// 用户登录数
		applicationInfo.setLoginUserCount(50);
		// 启动模式 DEV-开发 PRODUCTION-生产环境
		applicationInfo.setMode("DEV");
		// 操作系统名称
		applicationInfo.setOsName(System.getProperty(Constants.OS_NAME));
		// 操作系统版本
		applicationInfo.setOsVersion(System.getProperty(Constants.OS_VERSION));
		// 操作系统补丁
		applicationInfo.setOsArch(System.getProperty(Constants.OS_ARCH));
		// JDK厂商
		applicationInfo.setJdkVendor(System.getProperty(Constants.JDK_VENDOR));
		// JDK版本
		applicationInfo
				.setJdkVersion(System.getProperty(Constants.JDK_VERSION));
		// JDK主目录
		applicationInfo.setJdkHomePath(System.getProperty(Constants.JDK_HOME));
		// Web容器名称
		applicationInfo.setWebContainerName(params
				.get(Constants.WEB_CONTAINER_NAME));
		// 启动Web容器的用户名
		applicationInfo.setStartWebContainerUser(System
				.getProperty(Constants.USER_NAME));
		// 系统编码
		applicationInfo
				.setEncoding(System.getProperty(Constants.FILE_ENCODING));

		// 将应用基本信息保存到map中。
		this.addApplicationObject(APPLICATION_INFO, applicationInfo);

		return applicationInfo;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.lion.framework.service.application.ApplicationInfoService#
	 * getApplicationInfo()
	 */
	@Override
	public ApplicationInfo getApplicationInfo() {
		Map<String, String> params = new HashMap<String, String>();
		return this.getApplicationInfo(params);
	}

	public void addApplicationObject(String key, Object object) {
		applicationMap.put(key, object);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.lion.framework.service.application.ApplicationInfoService#
	 * getDataBaseInfo()
	 */
	@Override
	public DataBaseInfo getDataBaseInfo() {
		// TODO Auto-generated method stub
		DataBaseInfo dataBaseInfo = (DataBaseInfo) applicationMap
				.get(DATABASE_INFO);

		if (dataBaseInfo != null) {
			return dataBaseInfo;
		}
		dataBaseInfo = new DataBaseInfo();
		dataBaseInfo.setDataBaseType("MySQL");
		applicationMap.put(DATABASE_INFO, dataBaseInfo);
		return dataBaseInfo;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.lion.framework.service.application.ApplicationInfoService#
	 * getAuthorizeInfo()
	 */
	@Override
	public AuthorizeInfo getAuthorizeInfo() {
		AuthorizeInfo authorizeInfo = (AuthorizeInfo) applicationMap
				.get(AUTHORIZE_INFO);
		if (authorizeInfo != null) {
			return authorizeInfo;
		}
		authorizeInfo = new AuthorizeInfo();
		applicationMap.put(AUTHORIZE_INFO, authorizeInfo);
		return authorizeInfo;
	}
}
