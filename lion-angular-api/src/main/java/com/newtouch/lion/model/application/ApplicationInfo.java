/*
 * Copyright (c)  2013, Newtouch
 * All rights reserved. 
 *
 * $id: ApplicationInfo.java 9552 2013-5-19 下午1:01:27 WangLijun$
 */
package com.newtouch.lion.model.application;

import java.io.Serializable;

/**
 * <p>
 * Title: 应用程序信息
 * </p>
 * <p>
 * Description: 应用程序信息,包括应用名称
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
public class ApplicationInfo implements Serializable {

	/**
		 * 
		 */
	private static final long serialVersionUID = -8277496660857570236L;

	/** 应用名称 */
	private String name;
	/** 应用版本 */
	private String version = "1.0";
	/** 应用启动时间：yyyy-MM-dd HH:mm:ss */
	private String startDate;
	/** 登录的用户数 */
	private Integer loginUserCount;
	/** 应用启动模式 */
	private String mode;
	/** 操作系统名称 */
	private String osName;
	/** 操盘系统版本 */
	private String osVersion;
	/** 操作系统补丁 */
	private String osArch;
	/** JDK供应商 */
	private String jdkVendor;
	/** JDK版本 */
	private String jdkVersion;
	/** JDK主目录 */
	private String jdkHomePath;
	/** Web容器名称 */
	private String webContainerName;
	/** 启动Web容器的系统用户名 */
	private String startWebContainerUser;
	/** JVM内存：最大内存 */
	private Long maxMemory;
	/** JVM内存数：已分配内存 */
	private Long totalMemory;
	/** JVM内存数：已分配内存的剩余空间 */
	private Long freeMemory;
	/** JVM内存数：最大可用数 */
	private Long maxUseMemory;
	/** 物理内存：SWAP Free Size */
	private Long freeSwapSpaceSize;
	/** 物理内存：FreePhysicalMemorySize */
	private Long freePhysicalMemorySize;
	/** 物理内存：TotalPhysicalMemorySize */
	private Long totalPhysicalMemorySize;
	/** 系统字符编码 */
	private String encoding;

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the version
	 */
	public String getVersion() {
		return version;
	}

	/**
	 * @param version
	 *            the version to set
	 */
	public void setVersion(String version) {
		this.version = version;
	}

	/**
	 * @return the startDate
	 */
	public String getStartDate() {
		return startDate;
	}

	/**
	 * @param startDate
	 *            the startDate to set
	 */
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	/**
	 * @return the loginUserCount
	 */
	public Integer getLoginUserCount() {
		return loginUserCount;
	}

	/**
	 * @param loginUserCount
	 *            the loginUserCount to set
	 */
	public void setLoginUserCount(Integer loginUserCount) {
		this.loginUserCount = loginUserCount;
	}

	/**
	 * @return the mode
	 */
	public String getMode() {
		return mode;
	}

	/**
	 * @param mode
	 *            the mode to set
	 */
	public void setMode(String mode) {
		this.mode = mode;
	}

	/**
	 * @return the osName
	 */
	public String getOsName() {
		return osName;
	}

	/**
	 * @param osName
	 *            the osName to set
	 */
	public void setOsName(String osName) {
		this.osName = osName;
	}

	/**
	 * @return the osVersion
	 */
	public String getOsVersion() {
		return osVersion;
	}

	/**
	 * @param osVersion
	 *            the osVersion to set
	 */
	public void setOsVersion(String osVersion) {
		this.osVersion = osVersion;
	}

	/**
	 * @return the osArch
	 */
	public String getOsArch() {
		return osArch;
	}

	/**
	 * @param osArch
	 *            the osArch to set
	 */
	public void setOsArch(String osArch) {
		this.osArch = osArch;
	}

	/**
	 * @return the jdkVendor
	 */
	public String getJdkVendor() {
		return jdkVendor;
	}

	/**
	 * @param jdkVendor
	 *            the jdkVendor to set
	 */
	public void setJdkVendor(String jdkVendor) {
		this.jdkVendor = jdkVendor;
	}

	/**
	 * @return the jdkVersion
	 */
	public String getJdkVersion() {
		return jdkVersion;
	}

	/**
	 * @param jdkVersion
	 *            the jdkVersion to set
	 */
	public void setJdkVersion(String jdkVersion) {
		this.jdkVersion = jdkVersion;
	}

	/**
	 * @return the jdkHomePath
	 */
	public String getJdkHomePath() {
		return jdkHomePath;
	}

	/**
	 * @param jdkHomePath
	 *            the jdkHomePath to set
	 */
	public void setJdkHomePath(String jdkHomePath) {
		this.jdkHomePath = jdkHomePath;
	}

	/**
	 * @return the webContainerName
	 */
	public String getWebContainerName() {
		return webContainerName;
	}

	/**
	 * @param webContainerName
	 *            the webContainerName to set
	 */
	public void setWebContainerName(String webContainerName) {
		this.webContainerName = webContainerName;
	}

	/**
	 * @return the startWebContainerUser
	 */
	public String getStartWebContainerUser() {
		return startWebContainerUser;
	}

	/**
	 * @param startWebContainerUser
	 *            the startWebContainerUser to set
	 */
	public void setStartWebContainerUser(String startWebContainerUser) {
		this.startWebContainerUser = startWebContainerUser;
	}

	/**
	 * @return the maxMemory
	 */
	public Long getMaxMemory() {
		return maxMemory;
	}

	/**
	 * @param maxMemory
	 *            the maxMemory to set
	 */
	public void setMaxMemory(Long maxMemory) {
		this.maxMemory = maxMemory;
	}

	/**
	 * @return the totalMemory
	 */
	public Long getTotalMemory() {
		return totalMemory;
	}

	/**
	 * @param totalMemory
	 *            the totalMemory to set
	 */
	public void setTotalMemory(Long totalMemory) {
		this.totalMemory = totalMemory;
	}

	/**
	 * @return the freeMemory
	 */
	public Long getFreeMemory() {
		return freeMemory;
	}

	/**
	 * @param freeMemory
	 *            the freeMemory to set
	 */
	public void setFreeMemory(Long freeMemory) {
		this.freeMemory = freeMemory;
	}

	/**
	 * @return the maxUseMemory
	 */
	public Long getMaxUseMemory() {
		return maxUseMemory;
	}

	/**
	 * @param maxUseMemory
	 *            the maxUseMemory to set
	 */
	public void setMaxUseMemory(Long maxUseMemory) {
		this.maxUseMemory = maxUseMemory;
	}

	/**
	 * @return the encoding
	 */
	public String getEncoding() {
		return encoding;
	}

	/**
	 * @param encoding
	 *            the encoding to set
	 */
	public void setEncoding(String encoding) {
		this.encoding = encoding;
	}

	/**
	 * @return the freeSwapSpaceSize
	 */
	public Long getFreeSwapSpaceSize() {
		return freeSwapSpaceSize;
	}

	/**
	 * @param freeSwapSpaceSize
	 *            the freeSwapSpaceSize to set
	 */
	public void setFreeSwapSpaceSize(Long freeSwapSpaceSize) {
		this.freeSwapSpaceSize = freeSwapSpaceSize;
	}

	/**
	 * @return the freePhysicalMemorySize
	 */
	public Long getFreePhysicalMemorySize() {
		return freePhysicalMemorySize;
	}

	/**
	 * @param freePhysicalMemorySize
	 *            the freePhysicalMemorySize to set
	 */
	public void setFreePhysicalMemorySize(Long freePhysicalMemorySize) {
		this.freePhysicalMemorySize = freePhysicalMemorySize;
	}

	/**
	 * @return the totalPhysicalMemorySize
	 */
	public Long getTotalPhysicalMemorySize() {
		return totalPhysicalMemorySize;
	}

	/**
	 * @param totalPhysicalMemorySize
	 *            the totalPhysicalMemorySize to set
	 */
	public void setTotalPhysicalMemorySize(Long totalPhysicalMemorySize) {
		this.totalPhysicalMemorySize = totalPhysicalMemorySize;
	}

}
