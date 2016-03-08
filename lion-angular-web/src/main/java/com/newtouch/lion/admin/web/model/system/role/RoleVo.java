/*
 * Copyright (c)  2014, NewTouch
 * All rights reserved. 
 *
 * $id: RoleVo.java 9552 2014-3-28 下午01:12:37 WangLijun$
 */
package com.newtouch.lion.admin.web.model.system.role;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;

/**
 * <p>
 * Title: 角色VO
 * </p>
 * <p>
 * Description: 角色VO
 * </p>
 * <p>
 * Copyright: Copyright (c) 2014
 * </p>
 * <p>
 * Company: NewTouch
 * </p>
 * 
 * @author WangLijun
 * @version 1.0
 */
public class RoleVo {
	
	
	public static final  String NAMEEN="nameEn";
	/**
	 * @Fields id:参数序号
	 */
	private Long id;
	
	/**@Fileds editable 是否可编辑*/
	private Boolean editable=Boolean.FALSE;
	/**
	 * @Fields nameEn：英文名称
	 */
	@NotNull(message="{sys.role.form.nameen.missing.message}")
	@Length(max=128,min=4,message="{sys.role.form.nameen.length.message}")
	private String nameEn;
	/**
	 * @Fields nameZh：中文名称
	 */
	@NotNull(message="{sys.role.form.namezh.missing.message}")
	@Length(max=128,min=4,message="{sys.role.form.namezh.length.message}")
	private String nameZh;
	/**
	 * @Fields description:角色描述
	 */
	@Length(min=0,max=512,message="{sys.role.form.description.length.message}")
	private String description;

	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @return the nameZh
	 */
	public String getNameZh() {
		return nameZh;
	}

	/**
	 * @param nameZh
	 *            the nameZh to set
	 */
	public void setNameZh(String nameZh) {
		this.nameZh = nameZh;
	}

	/**
	 * @return the nameEn
	 */
	public String getNameEn() {
		return nameEn;
	}

	/**
	 * @param nameEn
	 *            the nameEn to set
	 */
	public void setNameEn(String nameEn) {
		this.nameEn = nameEn;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description
	 *            the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @return the editable
	 */
	public Boolean getEditable() {
		return editable;
	}

	/**
	 * @param editable
	 *            the editable to set
	 */
	public void setEditable(Boolean editable) {
		this.editable = editable;
	}
	
	/**
	 * @return the nameen
	 */
	public static String getNameen() {
		return NAMEEN;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "RoleVo [id=" + id + ", nameZh=" + nameZh + ", nameEn=" + nameEn
				+ ", description=" + description + ", editable=" + editable
				+ "]";
	}

}
