/*
* Copyright (c)  2015, NewTouch
* All rights reserved. 
*
* $id: GroupRole.java 9552 Feb 15, 2015 1:27:21 PM MaoJiaWei$
*/
package com.newtouch.lion.model.system; 

import com.newtouch.lion.model.BaseEntity;

/**
 * <p>
 * Title: 用户组与角色查询类
 * </p>
 * <p>
 * Description: 
 * </p>
 * <p>
 * Copyright: Copyright (c) 2015
 * </p>
 * <p>
 * Company: NewTouch
 * </p>
 * 
 * @author MaoJiaWei
 * @version 1.0
 */
public class GroupRole extends BaseEntity<Long> {
	/**
	 * 
	 */
	private static final long serialVersionUID = -775741526557914478L;
	/** 用户组ID*/
	private Long id;
	/** 用户组名称－中文 */
	private String nameZh;
	/** 用户组名称－英文 */
	private String nameEn;
	/** 用户组描述 */
	private String description;
	/** 角色描述Id*/
	private Long roleId;
	/**用户ID*/
	private Long userId;
	/**
	 * 默认构造器
	 */
	public GroupRole() {
		super();
	}
	/**
	 * @param id
	 * @param nameZh
	 * @param nameEn
	 * @param description
	 */
	public GroupRole(Long id, String nameZh, String nameEn, String description) {
		super();
		this.id = id;
		this.nameZh = nameZh;
		this.nameEn = nameEn;
		this.description = description;
	}
	/* (non-Javadoc)
	 * @see com.newtouch.lion.model.BaseEntity#getId()
	 */
	@Override
	public Long getId() {
		return this.id;
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
	 * @return the roleId
	 */
	public Long getRoleId() {
		return roleId;
	}
	/**
	 * @param roleId the roleId to set
	 */
	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}
	/**
	 * @return the userId 用户ID
	 */
	public Long getUserId() {
		return userId;
	}
	/**
	 * @param userId the 用户ID
	 */
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	
	
	
}


