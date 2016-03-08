/*
 * Copyright (c)  2013, Newtouch
 * All rights reserved. 
 *
 * $id: Department.java 9552 2013-3-22 上午9:01:29 WangLijun$
 */
package com.newtouch.lion.model.system;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Transient;

import com.newtouch.lion.model.VersionEntity;

/**
 * <p>
 * Title: 部门 Model
 * </p>
 * <p>
 * Description: 部门 Model
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
public class Department extends VersionEntity<Long> {

	private static final long serialVersionUID = 1281321300758302400L;
	/*** 部门ID */
	private Long id;
	/** 上一级部门ID */
	private Long parentDepartmentId;
	/** 部门编号 */
	private String departmentNo;
	/** 部门中文名称 */
	private String nameZh;
	/** 部门英文名称 */
	private String nameEn;
	/** 部门描述 */
	private String description;
	/** 是否可编辑 */
	private boolean editable;
	/** 部门所属用户集合 */
	private Set<User> users = new HashSet<User>(0);
	/** 部门父子对象 */
	private Department department;
	/** 该部门所属下级所有部门集合 */
	private Set<Department> departments = new HashSet<Department>(0);
	 
	/** 排序 */
	private List<Department> sortedChildDepartment;

	@Override
	public Long getId() {
		return this.id;
	}

	/**
	 * @return the parentDepartmentId
	 */

	public Long getParentDepartmentId() {
		return parentDepartmentId;
	}

	/**
	 * @return the departmentNo
	 */

	public String getDepartmentNo() {
		return departmentNo;
	}

	/**
	 * @return the nameZh
	 */

	public String getNameZh() {
		return nameZh;
	}

	/**
	 * @return the nameEn
	 */

	public String getNameEn() {
		return nameEn;
	}

	/**
	 * @return the description
	 */

	public String getDescription() {
		return description;
	}

	/**
	 * @return the isEditable
	 */
	@Column(name = "EDITABLE")
	/**
	 * @return the users
	 */
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "department")
	public Set<User> getUsers() {
		return users;
	}

	/**
	 * @return the isEditable
	 */
	@Column(name = "EDITABLE")
	public boolean isEditable() {
		return editable;
	}

	/**
	 * @return the department
	 */
	@ManyToOne(fetch = FetchType.LAZY, targetEntity = Department.class, cascade = CascadeType.MERGE)
	@JoinColumn(name = "PARENT_BAS_DEPARTMENT_ID", updatable = false, insertable = false)
	public Department getDepartment() {
		return department;
	}

	/**
	 * @return the Departments
	 */
	@OneToMany(cascade = CascadeType.MERGE, fetch = FetchType.LAZY, mappedBy = "department")
	public Set<Department> getDepartments() {
		return departments;
	}

	/***
	 * defualt
	 */
	public Department() {
		super();
	}

	/***
	 * 
	 * @param nameZh
	 * @param nameEn
	 */
	public Department(String nameZh, String nameEn) {
		super();
		this.nameZh = nameZh;
		this.nameEn = nameEn;
	}

	/****
	 * 
	 * @param parentDepartmentId
	 * @param departmentNo
	 * @param nameZh
	 * @param nameEn
	 * @param description
	 * @param isEditable
	 * @param users
	 * @param department
	 * @param departments
	 */
	public Department(Long parentDepartmentId, String departmentNo,
			String nameZh, String nameEn, String description, boolean editable,
			Set<User> users, Department department, Set<Department> departments) {
		super();
		this.parentDepartmentId = parentDepartmentId;
		this.departmentNo = departmentNo;
		this.nameZh = nameZh;
		this.nameEn = nameEn;
		this.description = description;
		this.editable = editable;
		this.users = users;
		this.department = department;
		this.departments = departments;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @param parentDepartmentId
	 *            the parentDepartmentId to set
	 */
	public void setParentDepartmentId(Long parentDepartmentId) {
		this.parentDepartmentId = parentDepartmentId;
	}

	/**
	 * @param departmentNo
	 *            the departmentNo to set
	 */
	public void setDepartmentNo(String departmentNo) {
		this.departmentNo = departmentNo;
	}

	/**
	 * @param nameZh
	 *            the nameZh to set
	 */
	public void setNameZh(String nameZh) {
		this.nameZh = nameZh;
	}

	/**
	 * @param nameEn
	 *            the nameEn to set
	 */
	public void setNameEn(String nameEn) {
		this.nameEn = nameEn;
	}

	/**
	 * @param description
	 *            the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @param isEditable
	 *            the isEditable to set
	 */
	public void setEditable(boolean editable) {
		this.editable = editable;
	}

	/**
	 * @param Users
	 *            the tsUsers to set
	 */
	public void setUsers(Set<User> users) {
		this.users = users;
	}

	/**
	 * @param department
	 *            the tsDepartment to set
	 */
	public void setDepartment(Department department) {
		this.department = department;
	}

	/**
	 * @param tsDepartments
	 *            the tsDepartments to set
	 */
	public void setDepartments(Set<Department> departments) {
		this.departments = departments;
	}

	/**
	 * @return the sortedChildDepartment
	 */
	@Transient
	public List<Department> getSortedChildDepartment() {
		return sortedChildDepartment;
	}

	/**
	 * @param sortedChildDepartment
	 *            the sortedChildDepartment to set
	 */
	public void setSortedChildDepartment(List<Department> sortedChildDepartment) {
		this.sortedChildDepartment = sortedChildDepartment;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Department [id=" + id + ", parentDepartmentId="
				+ parentDepartmentId + ", departmentNO=" + departmentNo
				+ ", nameZh=" + nameZh + ", nameEn=" + nameEn
				+ ", description=" + description + ", editable=" + editable
				+ ", Users= ";
	}

}
