/*
* Copyright (c)  2015, Newtouch
* All rights reserved. 
*
* $id: RoleGroup.java 9552 2015年2月15日 上午12:10:52 WangLijun$
*/
package com.newtouch.lion.model.system; 

import com.newtouch.lion.model.BaseEntity;

/**
 * <p>
 * Title: 角色与用户组查询实体类
 * </p>
 * <p>
 * Description: 角色与用户组查询实体类(仅用于查询)
 * </p>
 * <p>
 * Copyright: Copyright (c) 2015
 * </p>
 * <p>
 * Company: Newtouch
 * </p>
 * 
 * @author WangLijun
 * @version 1.0
 */
public class RoleGroup extends BaseEntity<Long>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1265104219286874990L;
	
	/** 角色ID */
	private Long id;

	/** 角色名称－中文 */
	private String nameZh;
	/** 角色名称－英文 */
	private String nameEn;
	/** 角色描述 */
	private String description;
	/**用户组ID*/
	private Long groupId;
	/**用户ID*/
	private Long userId;
	/**默认实体类*/
	public RoleGroup() {
		super();
	}
	
	
	/**
	 * @param id
	 * @param nameZh
	 * @param nameEn
	 * @param description
	 * @param groupId
	 */
	public RoleGroup(Long id, String nameZh, String nameEn, String description) {
		super();
		this.id = id;
		this.nameZh = nameZh;
		this.nameEn = nameEn;
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
	 * @return the nameZh
	 */
	public String getNameZh() {
		return nameZh;
	}
	/**
	 * @param nameZh the nameZh to set
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
	 * @param nameEn the nameEn to set
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
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}
	/**
	 * @return the groupId
	 */
	public Long getGroupId() {
		return groupId;
	}
	/**
	 * @param groupId the groupId to set
	 */
	public void setGroupId(Long groupId) {
		this.groupId = groupId;
	}


	/**
	 * @return the userId 用户ID
	 */
	public Long getUserId() {
		return userId;
	}


	/**
	 * @param userId the userId 用户ID
	 */
	public void setUserId(Long userId) {
		this.userId = userId;
	}

	
}

	