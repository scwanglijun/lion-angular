/*
 * Copyright (c)  2014, NewTouch
 * All rights reserved. 
 *
 * $id: ResourceVo.java 9552 2014-4-4 下午08:14:36 WangLijun$
 */
package com.newtouch.lion.admin.web.model.system.resource;

import com.newtouch.lion.model.system.Attributes;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.Digits;

/**
 * <p>
 * Title: 资源管理类
 * </p>
 * <p>
 * Description: 资源管理类-Vo
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
public class ResourceVo {
	/***
	 * 资源ID
	 */
	private Long id;
	/** 资源父ID */
	private Long parentResourceId;
	/** 资源类型 */
	@NotEmpty(message="{sys.resource.type.missing}")
	private String type;
	/** 资源路径 URL Class.Method */
	private String path;
	/** 资源名称－中文 */
	@NotEmpty(message="{sys.resource.namezh.missing}")
	@Length(max=128,min=1,message="{sys.resource.namezh.length}")
	private String nameZh;
	/** 资源名称－英文 */
	@NotEmpty(message="{sys.resource.namezh.missing}")
	@Length(max=128,min=1,message="{sys.resource.namezh.length}")
	private String nameEn;
	/** 资源描述 */
	private String description;
	/** 资源排序 */
	//@NotNull(message="{sys.resource.seqnum.missing}")
	@Digits(fraction=0,integer=10,message="{sys.resource.seqnum.digits}")
	private Integer seqNum;
	/** 资源是否叶节点，其下没有子资源 默认为：true */
	private Boolean isLeaf = Boolean.FALSE;
	/** 资源是否可编辑 */
	private Boolean editable = Boolean.FALSE;
	/** 资源目标 指HTML链接的target属性 */
	private String target;
	/** 资源性 */
	private Attributes attributes;
	/**权限名称*/
	private String permission;
	/**ICON图标*/
	private String icon;

	public ResourceVo() {
		super();
	}

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
	 * @return the parentResourceId
	 */
	public Long getParentResourceId() {
		return parentResourceId;
	}

	/**
	 * @param parentResourceId
	 *            the parentResourceId to set
	 */
	public void setParentResourceId(Long parentResourceId) {
		this.parentResourceId = parentResourceId;
	}

	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}

	/**
	 * @param type
	 *            the type to set
	 */
	public void setType(String type) {
		this.type = type;
	}

	/**
	 * @return the path
	 */
	public String getPath() {
		return path;
	}

	/**
	 * @param path
	 *            the path to set
	 */
	public void setPath(String path) {
		this.path = path;
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
	 * @return the seqNum
	 */
	public Integer getSeqNum() {
		return seqNum;
	}

	/**
	 * @param seqNum
	 *            the seqNum to set
	 */
	public void setSeqNum(Integer seqNum) {
		this.seqNum = seqNum;
	}

	/**
	 * @return the isLeaf
	 */
	public Boolean getIsLeaf() {
		return isLeaf;
	}

	/**
	 * @param isLeaf
	 *            the isLeaf to set
	 */
	public void setIsLeaf(Boolean isLeaf) {
		this.isLeaf = isLeaf;
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
	 * @return the target
	 */
	public String getTarget() {
		return target;
	}

	/**
	 * @param target
	 *            the target to set
	 */
	public void setTarget(String target) {
		this.target = target;
	}

	/**
	 * @return the attributes
	 */
	public Attributes getAttributes() {
		return attributes;
	}

	/**
	 * @param attributes
	 *            the attributes to set
	 */
	public void setAttributes(Attributes attributes) {
		this.attributes = attributes;
	}

	/**
	 * @return the permission
	 */
	public String getPermission() {
		return permission;
	}

	/**
	 * @param permission the permission to set
	 */
	public void setPermission(String permission) {
		this.permission = permission;
	}

	/**
	 * @return  ICON图标
	 */
	public String getIcon() {
		return icon;
	}

	/**
	 * @param icon  ICON图标
	 */
	public void setIcon(String icon) {
		this.icon = icon;
	}
	
	
	
}
