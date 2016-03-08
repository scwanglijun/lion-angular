/*
 * Copyright (c)  2012, Newtouch
 * All rights reserved. 
 *
 * $id: TsParameter.java 9552 2012-7-8 上午12:36:16 WangLijun$
 */
package com.newtouch.lion.model.system;

import com.newtouch.lion.model.VersionEntity;

/**
 * <p>
 * Title:Parameter
 * </p>
 * <p>
 * Description:系统参数表
 * </p>
 * <p>
 * Copyright: Copyright (c) 2012
 * </p>
 * <p>
 * Company: Newtouch
 * </p>
 * 
 * @author WangLijun
 * @version 1.0
 */

public class Parameter extends VersionEntity<Long> {

	/**
	 * @Fields serialVersionUID
	 */
	private static final long serialVersionUID = -1573409277875254236L;
	/**
	 * @Fields id:参数序号
	 */
	private Long id;
	/**
	 * @Fields type：参数类型
	 */
	private String type;
	/** @Fileds editable 是否可编辑 */
	private Boolean editable;
	/**
	 * @Fields nameEn：英文名称
	 */
	private String nameEn;
	/**
	 * @Fields nameZh：中文名称
	 */
	private String nameZh;
	/**
	 * @Fields value:参数值
	 */
	private String value;
	/**
	 * @Fields description:参数描述
	 */
	private String description;

	public Parameter() {
		super();
	}

	/***
	 * 
	 * @param id
	 *            物理主键ID
	 */
	public Parameter(Long id) {
		super();
		this.id = id;
	}

	/*
	 * <p>Title: getId</p> <p>Description: </p>
	 * 
	 * @return
	 * 
	 * @see com.lion.framework.model.BaseEntity#getId()
	 */
	public Long getId() {
		return this.id;
	}

	/**
	 * getter method
	 * 
	 * @return the type
	 */
	public String getType() {
		return type;
	}

	/**
	 * setter method
	 * 
	 * @param type
	 *            the type to set
	 */
	public void setType(String type) {

		this.type = type;
	}

	/**
	 * getter method
	 * 
	 * @return the nameEn
	 */
	
	public String getNameEn() {

		return this.nameEn;
	}

	/**
	 * setter method
	 * 
	 * @param nameEn
	 *            the nameEn to set
	 */
	public void setNameEn(String nameEn) {

		this.nameEn = nameEn;
	}

	/**
	 * getter method
	 * 
	 * @return the nameZh
	 */
	
	public String getNameZh() {

		return nameZh;
	}

	/**
	 * setter method
	 * 
	 * @param nameZh
	 *            the nameZh to set
	 */
	public void setNameZh(String nameZh) {

		this.nameZh = nameZh;
	}

	/**
	 * getter method
	 * 
	 * @return the value
	 */
	public String getValue() {

		return value;
	}

	/**
	 * setter method
	 * 
	 * @param value
	 *            the value to set
	 */
	public void setValue(String value) {

		this.value = value;
	}

	/**
	 * getter method
	 * 
	 * @return the description
	 */
	
	public String getDescription() {

		return description;
	}

	/**
	 * setter method
	 * 
	 * @param description
	 *            the description to set
	 */
	public void setDescription(String description) {

		this.description = description;
	}

	/**
	 * setter method
	 * 
	 * @param Id
	 *            the id to set
	 */
	public void setId(Long Id) {

		this.id = Id;
	}

	/**
	 * getter method
	 * 
	 * @return the editable
	 */
	public Boolean getEditable() {

		return editable;
	}

	/**
	 * setter method
	 * 
	 * @param editable
	 *            the editable to set
	 */
	public void setEditable(Boolean editable) {

		this.editable = editable;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "TsParameter [Id=" + id + ", type=" + type + ", editable="
				+ editable + ", nameEn=" + nameEn + ", nameZh=" + nameZh
				+ ", value=" + value + ", description=" + description
				+ ", getOptCounter()=" + getOptCounter() + ", getIsDeleted()="
				+ getIsDeleted() + ", getDeleteDate()=" + getDeleteDate()
				+ ", getCreatedById()=" + getCreatedById()
				+ ", getCreatedDate()=" + getCreatedDate()
				+ ", getUpdatedById()=" + getUpdatedById()
				+ ", getUpdatedDate()=" + getUpdatedDate() + "]";
	}

}
