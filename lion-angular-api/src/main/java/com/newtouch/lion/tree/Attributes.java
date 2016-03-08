/*
 * Copyright (c)  2013, Newtouch
 * All rights reserved. 
 *
 * $id: Attributes.java 9552 2013-4-12 上午12:03:31 WangLijun$
 */
package com.newtouch.lion.tree;

/**
 * <p>
 * Title: TreeNode属性-与EasyUI树绑定
 * </p>
 * <p>
 * Description:TreeNode属性-与EasyUI树绑定
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
public class Attributes {
	/** Id */
	private Long id;
	/** 路径 */
	private String path;
	/** 目标 */
	private String target;

	/** Default */
	public Attributes() {
		super();
	}

	/***
	 * 
	 * @param id
	 * @param path
	 * @param target
	 */
	public Attributes(Long id, String path, String target) {
		super();
		this.id = id;
		this.path = path;
		this.target = target;
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
	 * @return the path
	 */
	public String getPath() {
		return path;
	}

	/**
	 * @param path
	 *            the path to set
	 */
	public void setPath(String path) {
		this.path = path;
	}

	/**
	 * @return the target
	 */
	public String getTarget() {
		return target;
	}

	/**
	 * @param target
	 *            the target to set
	 */
	public void setTarget(String target) {
		this.target = target;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Attributes [id=" + id + ", path=" + path + ", target=" + target
				+ "]";
	}

}
