/*
* Copyright (c)  2015, Newtouch
* All rights reserved. 
*
* $id: TreeNode.java 9552 2015年2月2日 下午4:04:35 WangLijun$
*/
package com.newtouch.lion.ztree; 

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * <p>
 * Title: ZTree树型结构
 * </p>
 * <p>
 * Description: ZTree树型结构
 * </p>
 * <p>
 * Copyright: Copyright (c) 2015
 * </p>
 * <p>
 * Company: Newtouch
 * </p>
 * 
 * @author WangLijun
 * @version 1.0
 */
public class TreeNode implements Serializable{

	/**
	 * 序列化
	 */
	private static final long serialVersionUID = -4474072503351306280L;
	
	/**树的ID*/
	private Long id;
	/**树的父级ID 默认为*/
	@JsonProperty("pId")
	private Long pId=0L;
	/**树的名称*/
	private String name;
	/**树的展开状态：true 展开，false 收缩*/
	private Boolean open=Boolean.FALSE;
	
	/**默认构造函数*/
	public TreeNode() {
		 super();
	}
	
	
	
	/**
	 * 树的ID 父级ID为0
	 * @param id   树的ID
	 * @param name 树的名称
	 * @param open 树的展开状态
	 */
	public TreeNode(Long id, String name, Boolean open) {
		super();
		this.id = id;
		this.name = name;
		this.open = open;
	}




	/**
	 * 树的ID
	 * @param id   树的ID
	 * @param pid  树的父级ID
	 * @param name 树的名称
	 * @param open 树的展开状态
	 */
	public TreeNode(Long id, Long pId, String name, Boolean open) {
		super();
		this.id = id;
		this.pId = pId;
		this.name = name;
		this.open = open;
	}





	/**
	 * @return 树的ID
	 */
	public Long getId() {
		return id;
	}
	/**
	 * @param id 设置树的ID
	 */
	public void setId(Long id) {
		this.id = id;
	}
	/**
	 * @return the 树的父级ID
	 */
	public Long getPId() {
		return this.pId;
	}
	/**
	 * @param pid 设置树的父级ID
	 */
	public void setPId(Long pId) {
		this.pId= pId;
	}
	/**
	 * @return 树的名称
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name 设置树的名称
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @return 树的展开状态
	 */
	public Boolean getOpen() {
		return open;
	}
	/**
	 * @param open 树的展开状态
	 */
	public void setOpen(Boolean open) {
		this.open = open;
	}
	
	
	
	
	
	 
}

	