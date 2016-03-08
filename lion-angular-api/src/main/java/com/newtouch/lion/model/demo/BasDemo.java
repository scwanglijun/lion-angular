/*
* Copyright (c)  2015, Newtouch
* All rights reserved. 
*
* $id: BasDemo.java 9552 2015年9月16日 上午11:12:58 WangLijun$
*/
package com.newtouch.lion.model.demo; 

import java.util.Date;

import com.newtouch.lion.model.AuditEntity;

/**
 * <p>
 * Title: 
 * </p>
 * <p>
 * Description: 
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
public class BasDemo {

	private Long id;
	
	private Long parentId;
	
	private String nameZh;
	
	/***
	 * @Fields createById: is create UserId
	 */
	private Long createdById;
	/***
	 * @Fields createdDate: is create Date
	 */
	private Date createdDate;
	/***
	 * @Fields updatedById: is update UserId
	 */
	private Long updatedById;
	/***
	 * @Fields updatedById: is update Date
	 */
	private Date updatedDate;
	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}
	/**
	 * @return the parentId
	 */
	public Long getParentId() {
		return parentId;
	}
	/**
	 * @param parentId the parentId to set
	 */
	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}
	/**
	 * @return the nameZh
	 */
	public String getNameZh() {
		return nameZh;
	}
	/**
	 * @param nameZh the nameZh to set
	 */
	public void setNameZh(String nameZh) {
		this.nameZh = nameZh;
	}
	/**
	 * @return the createdById
	 */
	public Long getCreatedById() {
		return createdById;
	}
	/**
	 * @param createdById the createdById to set
	 */
	public void setCreatedById(Long createdById) {
		this.createdById = createdById;
	}
	/**
	 * @return the createdDate
	 */
	public Date getCreatedDate() {
		return createdDate;
	}
	/**
	 * @param createdDate the createdDate to set
	 */
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}
	/**
	 * @return the updatedById
	 */
	public Long getUpdatedById() {
		return updatedById;
	}
	/**
	 * @param updatedById the updatedById to set
	 */
	public void setUpdatedById(Long updatedById) {
		this.updatedById = updatedById;
	}
	/**
	 * @return the updatedDate
	 */
	public Date getUpdatedDate() {
		return updatedDate;
	}
	/**
	 * @param updatedDate the updatedDate to set
	 */
	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}
}