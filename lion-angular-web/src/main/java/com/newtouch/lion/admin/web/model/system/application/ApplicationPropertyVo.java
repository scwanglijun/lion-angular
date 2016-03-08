
/*
* Copyright (c)  2015, NewTouch
* All rights reserved. 
*
* $id: ApplicationPropertyVo.java 9552 Jan 28, 2015 9:59:40 PM MaoJiaWei$
*/
package com.newtouch.lion.admin.web.model.system.application; 

import org.hibernate.validator.constraints.Length;

/**
 * <p>
 * Title:TsParameter
 * </p>
 * <p>
 * Description:项目属性配置列表
 * </p>
 * <p>
 * Copyright: Copyright (c) 2014
 * </p>
 * <p>
 * Company: Newtouch
 * </p>
 * 
 * @author maojiawei
 * @version 1.0
 */
public class ApplicationPropertyVo {
	public static final String NAMEEN = "value";
	/**
	 * @Fields id:参数序号
	 */
	private Long id;
	/**
	 * @Fields appId：应用名称
	 */
	@Length(max = 20,min = 0,message = "{sys.applicationProperty.form.appId.length.message}")
	private String appId;

	/** @Fileds key 配置项-key*/
	@Length(max = 60,min = 2, message = "{sys.applicationProperty.form.key.length.message}")
	private String key;

	/**
	 * @Fields value：配置项-value
	 */
	@Length(max = 120, min = 2, message = "{sys.applicationProperty.form.value.length.message}")
	private String value;
	
	/**
	 * @Fields description:配置项描述
	 */
	@Length(min = 0, max = 120, message = "{sys.applicationProperty.form.description.length.message}")
	private String description;
	
	/**
	 * 
	 */
	public ApplicationPropertyVo() {
		super();
	}

	/**
	 * @param id
	 * @param appId
	 * @param key
	 * @param value
	 * @param description
	 */
	public ApplicationPropertyVo(Long id, String appId, String key, String value,
			String description) {
		super();
		this.id = id;
		this.appId = appId;
		this.key = key;
		this.value = value;
		this.description = description;
	}

	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @return the appId
	 */
	public String getAppId() {
		return appId;
	}

	/**
	 * @param appId the appId to set
	 */
	public void setAppId(String appId) {
		this.appId = appId;
	}

	/**
	 * @return the key
	 */
	public String getKey() {
		return key;
	}

	/**
	 * @param key the key to set
	 */
	public void setKey(String key) {
		this.key = key;
	}

	/**
	 * @return the value
	 */
	public String getValue() {
		return value;
	}

	/**
	 * @param value the value to set
	 */
	public void setValue(String value) {
		this.value = value;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @return the nameen
	 */
	public static String getNameen() {
		return NAMEEN;
	}
	
	
}

	