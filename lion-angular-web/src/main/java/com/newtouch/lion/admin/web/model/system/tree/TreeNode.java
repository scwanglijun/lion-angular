/*
 * Copyright (c)  2013, Newtouch
 * All rights reserved. 
 *
 * $id: TreeNode.java 9552 2013-4-11 下午11:53:42 WangLijun$
 */
package com.newtouch.lion.admin.web.model.system.tree;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * Title:
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Copyright: Copyright (c) 2013
 * </p>
 * <p>
 * Company: Newtouch
 * </p>
 * 
 * @author WangLijun
 * @version 1.0
 */
public class TreeNode implements Serializable {

	/**
		 * 
		 */
	private static final long serialVersionUID = 5880068982514373243L;

	/** id */
	private Long id;
	/** 标题 */
	private String text;
	/** 状态 */
	private String state = State.closed;
	/** 是否选中 */
	private boolean checked = Boolean.FALSE;
	/** iconCls图标样式 */
	private String iconCls;
	/** 显示顺序 */
	private int seqNum;
	/** 是否子节点 */
	private boolean isLeaf;
	/** 扩展属性，如果不满足要求再进行扩展 */
	private Attributes attributes;
	/** 子节点集合 */
	private List<TreeNode> children = new ArrayList<TreeNode>();

	public TreeNode() {
		super();
	}

	/***
	 * 
	 * @param id
	 * @param text
	 * @param state
	 * @param checked
	 * @param iconCls
	 * @param seqNum
	 * @param isLeaf
	 * @param attributes
	 * @param children
	 */
	public TreeNode(Long id, String text, String state, boolean checked,
			String iconCls, int seqNum, boolean isLeaf, Attributes attributes,
			List<TreeNode> children) {
		super();
		this.id = id;
		this.text = text;
		this.state = state;
		this.checked = checked;
		this.iconCls = iconCls;
		this.seqNum = seqNum;
		this.isLeaf = isLeaf;
		this.attributes = attributes;
		this.children = children;
	}

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
	 * @return the text
	 */
	public String getText() {
		return text;
	}

	/**
	 * @param text
	 *            the text to set
	 */
	public void setText(String text) {
		this.text = text;
	}

	/**
	 * @return the state
	 */
	public String getState() {
		return state;
	}

	/**
	 * @param state
	 *            the state to set
	 */
	public void setState(String state) {
		this.state = state;
	}

	/**
	 * @return the checked
	 */
	public boolean isChecked() {
		return checked;
	}

	/**
	 * @param checked
	 *            the checked to set
	 */
	public void setChecked(boolean checked) {
		this.checked = checked;
	}

	/**
	 * @return the iconCls
	 */
	public String getIconCls() {
		return iconCls;
	}

	/**
	 * @param iconCls
	 *            the iconCls to set
	 */
	public void setIconCls(String iconCls) {
		this.iconCls = iconCls;
	}

	/**
	 * @return the seqNum
	 */
	public int getSeqNum() {
		return seqNum;
	}

	/**
	 * @param seqNum
	 *            the seqNum to set
	 */
	public void setSeqNum(int seqNum) {
		this.seqNum = seqNum;
	}

	/**
	 * @return the isLeaf
	 */
	public boolean isLeaf() {
		return isLeaf;
	}

	/**
	 * @param isLeaf
	 *            the isLeaf to set
	 */
	public void setLeaf(boolean isLeaf) {
		this.isLeaf = isLeaf;
	}

	/**
	 * @return the attributes
	 */
	public Attributes getAttributes() {
		return attributes;
	}

	/**
	 * @param attributes
	 *            the attributes to set
	 */
	public void setAttributes(Attributes attributes) {
		this.attributes = attributes;
	}

	/**
	 * @return the children
	 */
	public List<TreeNode> getChildren() {
		return children;
	}

	/**
	 * @param children
	 *            the children to set
	 */
	public void setChildren(List<TreeNode> children) {
		this.children = children;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "TreeNode [id=" + id + ", text=" + text + ", state=" + state
				+ ", checked=" + checked + ", iconCls=" + iconCls + ", seqNum="
				+ seqNum + ", isLeaf=" + isLeaf + ", attributes="
				+ attributes.toString() + ", children=" + children + "]";
	}

}
