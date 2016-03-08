/*
* Copyright (c)  2015, Newtouch
* All rights reserved. 
*
* $id: UserGroup.java 9552 2015年2月14日 下午11:42:28 WangLijun$
*/
package com.newtouch.lion.model.system; 

import com.newtouch.lion.model.BaseEntity;

/**
 * <p>
 * Title: 用户与用户组查询类
 * </p>
 * <p>
 * Description: 
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
public class UserGroup extends BaseEntity<Long> {	
	/**
	 * 序列化
	 */
	private static final long serialVersionUID = 3038003527954580204L;
	/*** 用户ID */
	private Long id;
	/** 登录用户名 */
	private String username;
	/** 员工号 */
	private String employeeCode;
	/** 用户真实姓名－中文 */
	private String realnameZh;
	/** 用户真实姓名－英文 */
	private String realnameEn;
	/**用户组ID*/
	private Long  groupId;
	
	/**默认构造函数*/
	public UserGroup() {
		super();
	}
	
	
	/***
	 * 
	 * @param id 用户ID
	 * @param username 用户名
	 * @param employeeCode 员工
	 * @param realnameZh 用户真实姓名－中文
	 * @param realnameEn 用户真实姓名－英文
	 */
	public UserGroup(Long id, String username, String employeeCode,
			String realnameZh, String realnameEn) {
		super();
		this.id = id;
		this.username = username;
		this.employeeCode = employeeCode;
		this.realnameZh = realnameZh;
		this.realnameEn = realnameEn;
	}



	/**
	 * @return 用户ID
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id 用户ID
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @return 用户名
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * @param username 用户名
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * @return  员工号
	 */
	public String getEmployeeCode() {
		return employeeCode;
	}

	/**
	 * @param employeeCode 员工号
	 */
	public void setEmployeeCode(String employeeCode) {
		this.employeeCode = employeeCode;
	}

	/**
	 * @return  用户真实姓名－中文
	 */
	public String getRealnameZh() {
		return realnameZh;
	}

	/**
	 * @param realnameZh 用户真实姓名－中文
	 */
	public void setRealnameZh(String realnameZh) {
		this.realnameZh = realnameZh;
	}

	/**
	 * @return 用户真实姓名－英文
	 */
	public String getRealnameEn() {
		return realnameEn;
	}

	/**
	 * @param realnameEn the 用户真实姓名－英文
	 */
	public void setRealnameEn(String realnameEn) {
		this.realnameEn = realnameEn;
	}


	/**
	 * @return the groupId 用户组ID
	 */
	public Long getGroupId() {
		return groupId;
	}


	/**
	 * @param  groupId    用户组ID
	 */
	public void setGroupId(Long groupId) {
		this.groupId = groupId;
	}
	
	
	
}

	