
/*
* Copyright (c)  2016, Newtouch
* All rights reserved. 
*
* $id: UserGetReq.java 9552 2016年3月29日 下午4:50:42 LiJie$
*/
package com.newtouch.lion.admin.web.model.user; 

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import com.newtouch.lion.admin.web.model.query.QueryReq;
import com.newtouch.lion.model.system.Department;
import com.newtouch.lion.model.system.Group;
import com.newtouch.lion.model.system.Role;

/**
 * <p>
 * Title: 
 * </p>
 * <p>
 * Description: 
 * </p>
 * <p>
 * Copyright: Copyright (c) 2016
 * </p>
 * <p>
 * Company: Newtouch
 * </p>
 * 
 * @author LiJie
 * @version 1.0
 */
public class UserGetReq extends QueryReq{
	
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
	private Integer gender;
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
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getDepartmentId() {
		return departmentId;
	}
	public void setDepartmentId(Long departmentId) {
		this.departmentId = departmentId;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getPasswordHint() {
		return passwordHint;
	}
	public void setPasswordHint(String passwordHint) {
		this.passwordHint = passwordHint;
	}
	public String getEmployeeCode() {
		return employeeCode;
	}
	public void setEmployeeCode(String employeeCode) {
		this.employeeCode = employeeCode;
	}
	public String getRealnameZh() {
		return realnameZh;
	}
	public void setRealnameZh(String realnameZh) {
		this.realnameZh = realnameZh;
	}
	public String getRealnameEn() {
		return realnameEn;
	}
	public void setRealnameEn(String realnameEn) {
		this.realnameEn = realnameEn;
	}
	public Integer getGender() {
		return gender;
	}
	public void setGender(Integer gender) {
		this.gender = gender;
	}
	public String getUsertype() {
		return usertype;
	}
	public void setUsertype(String usertype) {
		this.usertype = usertype;
	}
	public String getAuthtype() {
		return authtype;
	}
	public void setAuthtype(String authtype) {
		this.authtype = authtype;
	}
	public String getTelephone() {
		return telephone;
	}
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getOfficePhone() {
		return officePhone;
	}
	public void setOfficePhone(String officePhone) {
		this.officePhone = officePhone;
	}
	public String getFax() {
		return fax;
	}
	public void setFax(String fax) {
		this.fax = fax;
	}
	public String getPostcode() {
		return postcode;
	}
	public void setPostcode(String postcode) {
		this.postcode = postcode;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public Boolean getAccountExpired() {
		return accountExpired;
	}
	public void setAccountExpired(Boolean accountExpired) {
		this.accountExpired = accountExpired;
	}
	public Boolean getAccountLocked() {
		return accountLocked;
	}
	public void setAccountLocked(Boolean accountLocked) {
		this.accountLocked = accountLocked;
	}
	public Boolean getCredentialExpired() {
		return credentialExpired;
	}
	public void setCredentialExpired(Boolean credentialExpired) {
		this.credentialExpired = credentialExpired;
	}
	public Date getCredentialExpiredDate() {
		return credentialExpiredDate;
	}
	public void setCredentialExpiredDate(Date credentialExpiredDate) {
		this.credentialExpiredDate = credentialExpiredDate;
	}
	public Date getAccountExpiredDate() {
		return accountExpiredDate;
	}
	public void setAccountExpiredDate(Date accountExpiredDate) {
		this.accountExpiredDate = accountExpiredDate;
	}
	public Boolean getEditable() {
		return editable;
	}
	public void setEditable(Boolean editable) {
		this.editable = editable;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Department getDepartment() {
		return department;
	}
	public void setDepartment(Department department) {
		this.department = department;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public Set<Role> getRoles() {
		return roles;
	}
	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}
	public Set<Group> getGroups() {
		return groups;
	}
	public void setGroups(Set<Group> groups) {
		this.groups = groups;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	

}

	