package com.newtouch.lion.admin.web.model.role;/**
 * Created by jovi on 3/25/16.
 */

import com.newtouch.lion.admin.web.model.query.QueryReq;
import com.newtouch.lion.model.system.Group;
import com.newtouch.lion.model.system.Resource;
import com.newtouch.lion.model.system.User;

import java.util.HashSet;
import java.util.Set;

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
 * Company: XiQing
 * </p>
 *
 * @author MaoJiaWei
 * @version 1.0
 */
public class RoleGetReq extends QueryReq{
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNameZh() {
        return nameZh;
    }

    public void setNameZh(String nameZh) {
        this.nameZh = nameZh;
    }

    public String getNameEn() {
        return nameEn;
    }

    public void setNameEn(String nameEn) {
        this.nameEn = nameEn;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean getEditable() {
        return editable;
    }

    public void setEditable(Boolean editable) {
        this.editable = editable;
    }

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }

    public Set<Group> getGroups() {
        return groups;
    }

    public void setGroups(Set<Group> groups) {
        this.groups = groups;
    }

    public Set<Resource> getResources() {
        return resources;
    }

    public void setResources(Set<Resource> resources) {
        this.resources = resources;
    }
}
