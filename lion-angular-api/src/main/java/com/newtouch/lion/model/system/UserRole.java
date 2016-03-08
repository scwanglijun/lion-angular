/*
* Copyright (c)  2015, NewTouch
* All rights reserved. 
*
* $id: UserRole.java 9552 Feb 15, 2015 11:08:59 AM MaoJiaWei$
*/
package com.newtouch.lion.model.system; 

import com.newtouch.lion.model.BaseEntity;

/**
 * <p>
 * Title: 用户与角色查询类
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
public class UserRole extends BaseEntity<Long>{

	/**
	 * 
	 */
	private static final long serialVersionUID = -7853041622962421803L;
	/** 角色ID */
	private Long id;
	/** 登录用户名 */
	private String username;
	/** 员工号 */
	private String employeeCode;
	/** 用户真实姓名－中文 */
	private String realnameZh;
	/** 用户真实姓名－英文 */
	private String realnameEn;
	/** 角色ID*/
	private Long  roleId;
	
	/**
	 * 
	 */
	public UserRole() {
		super();
	} 

	/**
	 * @param id
	 * @param username
	 * @param employeeCode
	 * @param realnameZh
	 * @param realnameEn
	 */
	public UserRole(Long id, String username, String employeeCode,
			String realnameZh, String realnameEn) {
		super();
		this.id = id;
		this.username = username;
		this.employeeCode = employeeCode;
		this.realnameZh = realnameZh;
		this.realnameEn = realnameEn;
	}

	/* (non-Javadoc)
	 * @see com.newtouch.lion.model.BaseEntity#getId()
	 */
	@Override
	public Long getId() {
		// TODO Auto-generated method stub
		return id;
	}

	/**
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * @param username the username to set
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * @return the employeeCode
	 */
	public String getEmployeeCode() {
		return employeeCode;
	}

	/**
	 * @param employeeCode the employeeCode to set
	 */
	public void setEmployeeCode(String employeeCode) {
		this.employeeCode = employeeCode;
	}

	/**
	 * @return the realnameZh
	 */
	public String getRealnameZh() {
		return realnameZh;
	}

	/**
	 * @param realnameZh the realnameZh to set
	 */
	public void setRealnameZh(String realnameZh) {
		this.realnameZh = realnameZh;
	}

	/**
	 * @return the realnameEn
	 */
	public String getRealnameEn() {
		return realnameEn;
	}

	/**
	 * @param realnameEn the realnameEn to set
	 */
	public void setRealnameEn(String realnameEn) {
		this.realnameEn = realnameEn;
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
	
}

	