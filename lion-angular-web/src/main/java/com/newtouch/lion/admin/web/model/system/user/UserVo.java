/*
 * Copyright (c)  2014, NewTouch
 * All rights reserved. 
 *
 * $id: UserVo.java 9552 2014-4-3 下午01:56:43 WangLijun$
 */
package com.newtouch.lion.admin.web.model.system.user;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * Title: 系统用户验证类
 * </p>
 * <p>
 * Description: 系统用户验证类
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
public class UserVo implements Serializable {

	private static final long serialVersionUID = -6298381436090613255L;
	/***
	 * USERID
	 */
	private Long id;
	/** 部门ID */
	@NotNull(message="${sys.user.departement.missing}")
	private Long departmentId;
	/** 经理ID */
	private Long managerId;
	/** 登录用户名 */
	@NotNull(message="${sys.user.username.missing.message}")
	@Length(max=30,min=4,message="{sys.user.username.length.message}")
	private String username;
	/** 超级管理员 */
	private String sapUsername;
	/** 登录密码 */
	
	private String password;
	/** 密码提示语 */
	private String passwordHint;
	/** 员工号 */
	@NotNull(message="${sys.user.employeecode.missing}")
	@Length(max=30,min=4,message="{sys.user.employeecode.length}")
	private String employeeCode;
	/** 认证类型 LDAP, DB, DUMMY */
	private String authtype;
	/** 用户类型EMPLOYEE, SUPPLIER, DEALER可自行扩展 */
	private String usertype;
	/** 用户描述 */
	private String description;
	/** 用户真实姓名－中文 */
	private String realnameZh;
	/** 用户真实姓名－英文 */
	private String realnameEn;

	/** 联系电话 */
	private String telephone;
	/** 联系电话－手机 */
	private String mobile;
	/** 联系电话－办公室 */
	private String officePhone;
	/** E-mail */
	@NotNull(message="{sys.user.email.missing}")
	@Email(message="{sys.user.email.pattern}")
	@Length(max=128,min=1,message="{sys.user.email.length}")
	private String email;
	/** 性别 */
	private Integer gender;
	/** 办公室位置 */
	private String location;
	/** 传真 */
	private String fax;
	/** 邮编 */
	private String postcode;
	/** 账户是否有效 */
	private Boolean accountExpired = Boolean.FALSE;
	/** 账户是否被锁定 */
	private Boolean accountLocked = Boolean.FALSE;
	/** 账户密码是否有效 */
	private Boolean credentialExpired = Boolean.FALSE;
	/** 账户密码有效日期 */
	private Date credentialExpiredDate;
	/** 账户有效日期 */
	private Date accountExpiredDate;
	/** 是否可编辑 */
	private Boolean editable = Boolean.FALSE;

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
	 * @return the departmentId
	 */
	public Long getDepartmentId() {
		return departmentId;
	}

	/**
	 * @param departmentId
	 *            the departmentId to set
	 */
	public void setDepartmentId(Long departmentId) {
		this.departmentId = departmentId;
	}

	/**
	 * @return the managerId
	 */
	public Long getManagerId() {
		return managerId;
	}

	/**
	 * @param managerId
	 *            the managerId to set
	 */
	public void setManagerId(Long managerId) {
		this.managerId = managerId;
	}

	/**
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * @param username
	 *            the username to set
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * @return the sapUsername
	 */
	public String getSapUsername() {
		return sapUsername;
	}

	/**
	 * @param sapUsername
	 *            the sapUsername to set
	 */
	public void setSapUsername(String sapUsername) {
		this.sapUsername = sapUsername;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password
	 *            the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * @return the passwordHint
	 */
	public String getPasswordHint() {
		return passwordHint;
	}

	/**
	 * @param passwordHint
	 *            the passwordHint to set
	 */
	public void setPasswordHint(String passwordHint) {
		this.passwordHint = passwordHint;
	}

	/**
	 * @return the employeeCode
	 */
	public String getEmployeeCode() {
		return employeeCode;
	}

	/**
	 * @param employeeCode
	 *            the employeeCode to set
	 */
	public void setEmployeeCode(String employeeCode) {
		this.employeeCode = employeeCode;
	}

	/**
	 * @return the authtype
	 */
	public String getAuthtype() {
		return authtype;
	}

	/**
	 * @param authtype
	 *            the authtype to set
	 */
	public void setAuthtype(String authtype) {
		this.authtype = authtype;
	}

	/**
	 * @return the usertype
	 */
	public String getUsertype() {
		return usertype;
	}

	/**
	 * @param usertype
	 *            the usertype to set
	 */
	public void setUsertype(String usertype) {
		this.usertype = usertype;
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
	 * @return the realnameZh
	 */
	public String getRealnameZh() {
		return realnameZh;
	}

	/**
	 * @param realnameZh
	 *            the realnameZh to set
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
	 * @param realnameEn
	 *            the realnameEn to set
	 */
	public void setRealnameEn(String realnameEn) {
		this.realnameEn = realnameEn;
	}

	/**
	 * @return the telephone
	 */
	public String getTelephone() {
		return telephone;
	}

	/**
	 * @param telephone
	 *            the telephone to set
	 */
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	/**
	 * @return the mobile
	 */
	public String getMobile() {
		return mobile;
	}

	/**
	 * @param mobile
	 *            the mobile to set
	 */
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	/**
	 * @return the officePhone
	 */
	public String getOfficePhone() {
		return officePhone;
	}

	/**
	 * @param officePhone
	 *            the officePhone to set
	 */
	public void setOfficePhone(String officePhone) {
		this.officePhone = officePhone;
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email
	 *            the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return the gender
	 */
	public Integer getGender() {
		return gender;
	}

	/**
	 * @param gender
	 *            the gender to set
	 */
	public void setGender(Integer gender) {
		this.gender = gender;
	}

	/**
	 * @return the location
	 */
	public String getLocation() {
		return location;
	}

	/**
	 * @param location
	 *            the location to set
	 */
	public void setLocation(String location) {
		this.location = location;
	}

	/**
	 * @return the fax
	 */
	public String getFax() {
		return fax;
	}

	/**
	 * @param fax
	 *            the fax to set
	 */
	public void setFax(String fax) {
		this.fax = fax;
	}

	/**
	 * @return the postcode
	 */
	public String getPostcode() {
		return postcode;
	}

	/**
	 * @param postcode
	 *            the postcode to set
	 */
	public void setPostcode(String postcode) {
		this.postcode = postcode;
	}

	/**
	 * @return the accountExpired
	 */
	public Boolean getAccountExpired() {
		return accountExpired;
	}

	/**
	 * @param accountExpired
	 *            the accountExpired to set
	 */
	public void setAccountExpired(Boolean accountExpired) {
		this.accountExpired = accountExpired;
	}

	/**
	 * @return the accountLocked
	 */
	public Boolean getAccountLocked() {
		return accountLocked;
	}

	/**
	 * @param accountLocked
	 *            the accountLocked to set
	 */
	public void setAccountLocked(Boolean accountLocked) {
		this.accountLocked = accountLocked;
	}

	/**
	 * @return the credentialExpired
	 */
	public Boolean getCredentialExpired() {
		return credentialExpired;
	}

	/**
	 * @param credentialExpired
	 *            the credentialExpired to set
	 */
	public void setCredentialExpired(Boolean credentialExpired) {
		this.credentialExpired = credentialExpired;
	}

	/**
	 * @return the credentialExpiredDate
	 */
	public Date getCredentialExpiredDate() {
		return credentialExpiredDate;
	}

	/**
	 * @param credentialExpiredDate
	 *            the credentialExpiredDate to set
	 */
	public void setCredentialExpiredDate(Date credentialExpiredDate) {
		this.credentialExpiredDate = credentialExpiredDate;
	}

	/**
	 * @return the accountExpiredDate
	 */
	public Date getAccountExpiredDate() {
		return accountExpiredDate;
	}

	/**
	 * @param accountExpiredDate
	 *            the accountExpiredDate to set
	 */
	public void setAccountExpiredDate(Date accountExpiredDate) {
		this.accountExpiredDate = accountExpiredDate;
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
}
