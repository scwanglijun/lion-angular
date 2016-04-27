/**
 * 
 */
package com.newtouch.lion.admin.web.model.role;

import java.util.HashSet;
import java.util.Set;

import com.newtouch.lion.model.system.Attributes;
import com.newtouch.lion.model.system.Role;

/**
 * @author lixiaohao
 *
 */
public class AuthResourceResp {

    /***
     * 资源ID
     */
    private Long id;
    /** 资源父ID */
    private Long parentResourceId;
    /** 资源类型 */
    private String type;
    /** 资源路径 URL Class.Method */
    private String path;
    /** 资源名称－中文 */
    private String nameZh;
    /** 资源名称－英文 */
    private String nameEn;
    /** 资源描述 */
    private String description;
    /** 资源排序 */
    private int seqNum;
    /** 资源是否叶节点，其下没有子资源 默认为：true */
    private Boolean isLeaf;
    /** 资源是否可编辑 */
    private Boolean editable;
    /** 资源目标 指HTML链接的target属性 */
    private String target;
    /**资源图标*/
    private String icon;
    /** 资源性 */
    private Attributes attributes;
    /**权限名称*/
    private String permission;
    /** 资源所关联的角色集合 */
	private Set<Role> roles = new HashSet<Role>();
	
    private Boolean checked = Boolean.FALSE;
    public AuthResourceResp() {
		super();
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getParentResourceId() {
		return parentResourceId;
	}
	public void setParentResourceId(Long parentResourceId) {
		this.parentResourceId = parentResourceId;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
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
	public int getSeqNum() {
		return seqNum;
	}
	public void setSeqNum(int seqNum) {
		this.seqNum = seqNum;
	}
	public Boolean getIsLeaf() {
		return isLeaf;
	}
	public void setIsLeaf(Boolean isLeaf) {
		this.isLeaf = isLeaf;
	}
	public Boolean getEditable() {
		return editable;
	}
	public void setEditable(Boolean editable) {
		this.editable = editable;
	}
	public String getTarget() {
		return target;
	}
	public void setTarget(String target) {
		this.target = target;
	}
	public String getIcon() {
		return icon;
	}
	public void setIcon(String icon) {
		this.icon = icon;
	}
	public Attributes getAttributes() {
		return attributes;
	}
	public void setAttributes(Attributes attributes) {
		this.attributes = attributes;
	}
	public String getPermission() {
		return permission;
	}
	public void setPermission(String permission) {
		this.permission = permission;
	}
	/**
	 * @return the roles
	 */
	public Set<Role> getRoles() {
		return roles;
	}
	/**
	 * @param roles the roles to set
	 */
	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}
	/**
	 * @return the checked
	 */
	public Boolean getChecked() {
		return checked;
	}
	/**
	 * @param checked the checked to set
	 */
	public void setChecked(Boolean checked) {
		this.checked = checked;
	}
    
    
}
