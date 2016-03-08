/*
 * Copyright (c)  2014, Newtouch
 * All rights reserved. 
 *
 * $id: ApplicationProperty.java 9552 2014年12月23日 下午2:54:19 WangLijun$
 */
package com.newtouch.lion.model.application;

import com.newtouch.lion.model.VersionEntity;

/**
 * <p>
 * Title: 项目属性配置包括JPA等配置文件
 * </p>
 * <p>
 * Description: 包括JPA等配置文件，
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
public class ApplicationProperty extends VersionEntity<Long> {

	/**
	 * 序列化
	 */
	private static final long serialVersionUID = -4666964281741956046L;
	/**
	 * ID
	 * */
	private Long id;

	/** 应用名称 */
	private String appId;
	/** 配置项-key */
	private String key;

	/** 配置项-value */
	private String value;

	/** 配置项描述 */
	private String description;

	/** ID */
	@Override
	public Long getId() {
		return this.id;
	}

	/**
	 * @return 配置项APPID
	 */
	public String getAppId() {
		return appId;
	}

	/**
	 * @param appId
	 *            配置项APPID
	 */
	public void setAppId(String appId) {
		this.appId = appId;
	}

	/**
	 * @return the 配置项键
	 */
	public String getKey() {
		return key;
	}

	/**
	 * @param key
	 *            配置项键
	 */
	public void setKey(String key) {
		this.key = key;
	}

	/**
	 * @return the 配置项值
	 */
	public String getValue() {
		return value;
	}

	/**
	 * @param value
	 *            配置项值
	 */
	public void setValue(String value) {
		this.value = value;
	}

	/**
	 * @return 配置描述
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description
	 *            配置描述
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @param 记录ID
	 */
	public void setId(Long id) {
		this.id = id;
	}

}
