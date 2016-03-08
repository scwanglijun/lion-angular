/*
 * Copyright (c)  2012, Newtouch
 * All rights reserved. 
 *
 * $id: TsUserRole.java 9552 2012-12-30 下午5:59:39 WangLijun$
 */
package com.newtouch.lion.model.system;

import java.util.HashSet;
import java.util.Set;


import com.newtouch.lion.model.VersionEntity;

/**
 * <p>
 * Title: 角色Model
 * </p>
 * <p>
 * Description: 角色Model
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
public class Role extends VersionEntity<Long> {

	private static final long serialVersionUID = 7085639151390168040L;

	/** 角色ID */
	private Long id;

	/** 角色名称－中文 */
	private String nameZh;
	/** 角色名称－英文 */
	private String nameEn;
	/** 角色描述 */
	private String description;
	/** 是否可编辑 */
	private Boolean editable;
	/** 角色关联用户集合 */
	private Set<User> users = new HashSet<User>();
	/** 角色关联用户组集合 */
	private Set<Group> groups = new HashSet<Group>();
	/** 角色关联资源集合 */
	private Set<Resource> resources = new HashSet<Resource>();

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.lion.framework.model.BaseEntity#getId()
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
	 * @return the editable
	 */
	public Boolean getEditable() {
		return editable;
	}

	/**
	 * @return the users
	 */
	 
	public Set<User> getUsers() {
		return users;
	}

	/**
	 * @return the groups
	 */
	 
	public Set<Group> getGroups() {
		return groups;
	}

	/**
	 * @return the resources BAS_ROLE_RESOURCE
	 */
	public Set<Resource> getResources() {
		return resources;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(Long id) {
		this.id = id;
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
	 * @param editable
	 *            the editable to set
	 */
	public void setEditable(Boolean editable) {
		this.editable = editable;
	}

	/**
	 * @param tsUsers
	 *            the users to set
	 */
	public void setUsers(Set<User> users) {
		this.users = users;
	}

	/**
	 * @param tsGroups
	 *            the tsGroups to set
	 */
	public void setGroups(Set<Group> groups) {
		this.groups = groups;
	}

	/**
	 * @param tsResources
	 *            the tsResources to set
	 */
	public void setResources(Set<Resource> resources) {
		this.resources = resources;
	}
}