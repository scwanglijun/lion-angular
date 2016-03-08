/*
 * Copyright (c)  2014, NewTouch
 * All rights reserved. 
 *
 * $id: Tree.java 9552 2014-4-4 下午08:14:36 WangLijun$
 */
package com.newtouch.lion.admin.web.model.system.resource;

import java.util.List;

/**
 * <p>
 * Title: 资源管理类
 * </p>
 * <p>
 * Description: 资源管理类-Vo
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
public class Tree implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 679431150214183430L;

	private String id;

	private String text;

	private String state = "open";// open,closed

	private boolean checked = false;

	private Object attributes;

	private List<Tree> children;

	private String iconCls;

	private String pid;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public boolean isChecked() {
		return checked;
	}

	public void setChecked(boolean checked) {
		this.checked = checked;
	}

	public Object getAttributes() {
		return attributes;
	}

	public void setAttributes(Object attributes) {
		this.attributes = attributes;
	}

	public List<Tree> getChildren() {
		return children;
	}

	public void setChildren(List<Tree> children) {
		this.children = children;
	}

	public String getIconCls() {
		return iconCls;
	}

	public void setIconCls(String iconCls) {
		this.iconCls = iconCls;
	}

	public String getPid() {
		return pid;
	}

	public void setPid(String pid) {
		this.pid = pid;
	}

}
