/*
 * Copyright (c)  2012, Newtouch
 * All rights reserved. 
 *
 * $id: User.java 9552 2012-12-30 下午8:37:24 WangLijun$
 */
package com.newtouch.lion.model.system;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import com.newtouch.lion.model.VersionEntity;

/**
 * <p>
 * Title: 用户 Model
 * </p>
 * <p>
 * Description: 用户 Model
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

public class User extends VersionEntity<Long> {
	/** 序列化 */
	private static final long serialVersionUID = -3674276844049006131L;
	/*** USERID */
	private Long id;
	/** 部门ID */
	private Long departmentId;
	/** 登录用户名 */
	private String username;
	/** 登录密码 */
	private String password;
	/** 密码提示语 */
	private String passwordHint;
	/** 员工号 */
	private String employeeCode;
	/** 用户真实姓名－中文 */
	private String realnameZh;
	/** 用户真实姓名－英文 */
	private String realnameEn;
	/** 性别 */
	private int gender;
	/** 用户类型EMPLOYEE, SUPPLIER, DEALER可自行扩展 */
	private String usertype;
	/** 认证类型 LDAP, DB, DUMMY */
	private String authtype;
	/** 联系电话 */
	private String telephone;
	/** 联系电话－手机 */
	private String mobile;
	/** E-mail */
	private String email;
	/** 联系电话－办公室 */
	private String officePhone;
	/** 传真 */
	private String fax;
	/** 邮编 */
	private String postcode;
	/** 办公室位置 */
	private String location;
	/** 账户是否有效 */
	private Boolean accountExpired;
	/** 账户是否被锁定 */
	private Boolean accountLocked;
	/** 账户密码是否有效 */
	private Boolean credentialExpired;
	/** 账户密码有效日期 */
	private Date credentialExpiredDate;
	/** 账户有效日期 */
	private Date accountExpiredDate;
	/** 是否可编辑 */
	private Boolean editable;
	/** 用户描述 */
	private String description;
	/** 该用户部门 */
	private Department department;
	/**用户头像*/
	private String image;
	/** 该用户所有权限列表 */
	private Set<Role> roles = new HashSet<Role>(0);
	/** 该用户所有用户组 */
	private Set<Group> groups = new HashSet<Group>(0);
	@Override
	public Long getId() {
		return this.id;
	}

	/**
	 * @return the department
	 */
 
	public Department getDepartment() {
		return department;
	}

	/**
	 * @return the departmentId
	 */
 
	public Long getDepartmentId() {
		return departmentId;
	}

	/**
	 * @return the username
	 */
 
	public String getUsername() {
		return username;
	}
	/**
	 * @return the password
	 */
	
	public String getPassword() {
		return password;
	}

	/**
	 * @return the passwordHint
	 */
	public String getPasswordHint() {
		return passwordHint;
	}

	/**
	 * @return the employeeCode
	 */
	public String getEmployeeCode() {
		return employeeCode;
	}

	/**
	 * @return the authtype
	 */
	public String getAuthtype() {
		return authtype;
	}

	/**
	 * @return the usertype
	 */
	public String getUsertype() {
		return usertype;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @return the realnameZh
	 */
	public String getRealnameZh() {
		return realnameZh;
	}

	/**
	 * @return the realnameEn
	 */
	public String getRealnameEn() {
		return realnameEn;
	}

	/**
	 * @return the telephone
	 */
	public String getTelephone() {
		return telephone;
	}

	/**
	 * @return the mobile
	 */

	public String getMobile() {
		return mobile;
	}

	/**
	 * @return the officePhone
	 */
	public String getOfficePhone() {
		return officePhone;
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @return the gender
	 */
	public int getGender() {
		return gender;
	}

	/**
	 * @return the location
	 */
	public String getLocation() {
		return location;
	}

	/**
	 * @return the fax
	 */
	 
	public String getFax() {
		return fax;
	}

	/**
	 * @return the postcode
	 */
	 
	public String getPostcode() {
		return postcode;
	}

	/**
	 * @return the accountExpired
	 */

	public Boolean getAccountExpired() {
		return accountExpired;
	}

	/**
	 * @return the accountLocked
	 */
	public Boolean getAccountLocked() {
		return accountLocked;
	}

	/**
	 * @return the credentialExpired
	 */
	public Boolean getCredentialExpired() {
		return credentialExpired;
	}

	/**
	 * @return the credentialExpiredDate
	 */
	public Date getCredentialExpiredDate() {
		return credentialExpiredDate;
	}

	/**
	 * @return the accountExpiredDate
	 */
	public Date getAccountExpiredDate() {
		return accountExpiredDate;
	}

	/**
	 * @return the editable
	 */
	public Boolean getEditable() {
		return editable;
	}

	/**
	 * @return the roles
	 */

	public Set<Role> getRoles() {
		return roles;
	}

	/**
	 * @return the groups
	 */

	public Set<Group> getGroups() {
		return groups;
	}

	/**
	 * default
	 * */
	public User() {
		super();
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @param tsDepartment
	 *            the Department to set
	 */
	public void setDepartment(Department department) {
		this.department = department;
	}

	/**
	 * @param departmentId
	 *            the DepartmentId to set
	 */
	public void setDepartmentId(Long departmentId) {
		this.departmentId = departmentId;
	}

	/**
	 * @param username
	 *            the username to set
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * @param password
	 *            the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * @param passwordHint
	 *            the passwordHint to set
	 */
	public void setPasswordHint(String passwordHint) {
		this.passwordHint = passwordHint;
	}

	/**
	 * @param employeeCode
	 *            the employeeCode to set
	 */
	public void setEmployeeCode(String employeeCode) {
		this.employeeCode = employeeCode;
	}

	/**
	 * @param authtype
	 *            the authtype to set
	 */
	public void setAuthtype(String authtype) {
		this.authtype = authtype;
	}

	/**
	 * @param usertype
	 *            the usertype to set
	 */
	public void setUsertype(String usertype) {
		this.usertype = usertype;
	}

	/**
	 * @param description
	 *            the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @param realnameZh
	 *            the realnameZh to set
	 */
	public void setRealnameZh(String realnameZh) {
		this.realnameZh = realnameZh;
	}

	/**
	 * @param realnameEn
	 *            the realnameEn to set
	 */
	public void setRealnameEn(String realnameEn) {
		this.realnameEn = realnameEn;
	}

	/**
	 * @param telephone
	 *            the telephone to set
	 */
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	/**
	 * @param mobile
	 *            the mobile to set
	 */
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	/**
	 * @param officePhone
	 *            the officePhone to set
	 */
	public void setOfficePhone(String officePhone) {
		this.officePhone = officePhone;
	}

	/**
	 * @param email
	 *            the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @param gender
	 *            the gender to set
	 */
	public void setGender(int gender) {
		this.gender = gender;
	}

	/**
	 * @param location
	 *            the location to set
	 */
	public void setLocation(String location) {
		this.location = location;
	}

	/**
	 * @param fax
	 *            the fax to set
	 */
	public void setFax(String fax) {
		this.fax = fax;
	}

	/**
	 * @param postcode
	 *            the postcode to set
	 */
	public void setPostcode(String postcode) {
		this.postcode = postcode;
	}

	/**
	 * @param accountExpired
	 *            the accountExpired to set
	 */
	public void setAccountExpired(Boolean accountExpired) {
		this.accountExpired = accountExpired;
	}

	/**
	 * @param accountLocked
	 *            the accountLocked to set
	 */
	public void setAccountLocked(Boolean accountLocked) {
		this.accountLocked = accountLocked;
	}

	/**
	 * @param credentialExpired
	 *            the credentialExpired to set
	 */
	public void setCredentialExpired(Boolean credentialExpired) {
		this.credentialExpired = credentialExpired;
	}

	/**
	 * @param credentialExpiredDate
	 *            the credentialExpiredDate to set
	 */
	public void setCredentialExpiredDate(Date credentialExpiredDate) {
		this.credentialExpiredDate = credentialExpiredDate;
	}

	/**
	 * @param accountExpiredDate
	 *            the accountExpiredDate to set
	 */
	public void setAccountExpiredDate(Date accountExpiredDate) {
		this.accountExpiredDate = accountExpiredDate;
	}

	/**
	 * @param editable
	 *            the editable to set
	 */
	public void setEditable(Boolean editable) {
		this.editable = editable;
	}

	/**
	 * @param roles
	 *            the roles to set
	 */
	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

	/**
	 * @param groups
	 *            the groups to set
	 */
	public void setGroups(Set<Group> groups) {
		this.groups = groups;
	}

	/**
	 * @return the image
	 */
	public String getImage() {
		return image;
	}

	/**
	 * @param image the image to set
	 */
	public void setImage(String image) {
		this.image = image;
	}

}
