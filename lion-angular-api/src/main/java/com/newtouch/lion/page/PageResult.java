/*
 * Copyright (c)  2012, Newtouch
 * All rights reserved. 
 *
 * $id: PageResult.java 9552 2012-7-8 上午01:14:38 WangLijun$
 */
package com.newtouch.lion.page;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.newtouch.lion.data.DataTable;

/**
 * <p>
 * Title:分页类
 * </p>
 * <p>
 * Description:
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
public class PageResult<T> implements Serializable {

	/**
	 * @Fields serialVersionUID	 */

	private static final long serialVersionUID = -6684174444925096614L;
	/** 当前页索引 默认值为:0 */
	private int currentIndex = 0;
	/** 总记录数 默认值为：0 */
	private Long totalCount = 0L;
	/** 当前页 默认值为：1 */
	private int currentPage = 1;
	/** 总页数 默认值为：0 */
	private Long totalPage = 0L;
	/** 每页记录数 默认值为；10条 */
	private int pageSize = 15;
	/** 当前页记录的集合对象 */
	private List<T> content = new ArrayList<T>(0);
	/** pageList [10,15,20,30,40,50,100] */
	private String pageList = "[10,15,20,30,40,50,100]";

	/**
	 * 创建一个新的实例 PageResult.
	 * <p>
	 * Title:
	 * </p>
	 * <p>
	 * Description:
	 * </p>
	 */

	public PageResult() {
		super();
	}

	/**
	 * @return the currentIndex
	 */
	public int getCurrentIndex() {
		return currentIndex;
	}

	/**
	 * @param currentIndex
	 *            the currentIndex to set
	 */
	public void setCurrentIndex(int currentIndex) {
		this.currentIndex = currentIndex;
	}

	/**
	 * @return the totalCount
	 */
	public Long getTotalCount() {
		return totalCount;
	}

	/**
	 * @param totalCount
	 *            the totalCount to set
	 */
	public void setTotalCount(Long totalCount) {
		this.totalCount = totalCount;
	}

	/**
	 * @return the currentPage
	 */
	public int getCurrentPage() {
		return currentPage;
	}

	/**
	 * @param currentPage
	 *            the currentPage to set
	 */
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	/**
	 * @return the totalPage
	 */
	public Long getTotalPage() {
		return totalPage;
	}

	/**
	 * @param totalPage
	 *            the totalPage to set
	 */
	public void setTotalPage(Long totalPage) {
		this.totalPage = totalPage;
	}

	/**
	 * @return the pageSize
	 */
	public int getPageSize() {
		return pageSize;
	}

	/**
	 * @param pageSize
	 *            the pageSize to set
	 */
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	/**
	 * @return the content
	 */
	public List<T> getContent() {
		return content;
	}

	/**
	 * @param content
	 *            the content to set
	 */
	public void setContent(List<T> content) {
		this.content = content;
	}

	/**
	 * @return the pageList
	 */
	public String getPageList() {
		return pageList;
	}

	/**
	 * @param pageList
	 *            the pageList to set
	 */
	public void setPageList(String pageList) {
		this.pageList = pageList;
	}
	/***
	 *获取DataGrid数据集,包含总记录数、数据集合
	 */
	public DataTable<T> getDataTable(){
		return new DataTable<T>(this.totalCount,this.content,this.getCurrentIndex());
	}
	
	public DataTable<T> getDataTable(int draw){
			
		return new DataTable<T>(this.totalCount,this.content,draw);
	}

}
