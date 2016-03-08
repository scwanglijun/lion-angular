/*
* Copyright (c)  2015, Newtouch
* All rights reserved. 
*
* $id: DataTable.java 9552 2015年1月9日 下午3:36:35 WangLijun$
*/
package com.newtouch.lion.data; 

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * Title: DataTable的表示数据，支持EasyUI和DataTables
 * </p>
 * <p>
 * Description: DataTable的表示数据 支持EasyUI和DataTables
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
public class DataTable<T> {
	
	/**总记录数*/
	private Long total;
	/** 当前页记录的集合对象 */
	private List<T> rows = new ArrayList<T>(0);
	/** 当前页记录的集合对象*/
	private List<T> data=new ArrayList<T>(0);
	/**当前页数*/
	private int draw;
	/**总记录数*/
	private Long recordsTotal;
	/**过滤的记录数*/
	private Long recordsFiltered;
	
	/**
	 * 默认构造函数
	 * */
	public DataTable() {
		super();
	}
	
	/**
	 * @param total 总记录数
	 * @param row 集合对象
	 */
	public DataTable(Long total, List<T> rows, int draw) {
		super();
		this.total = total;
		this.rows= rows;
		this.recordsTotal=this.total;
		this.recordsFiltered=this.total;
		this.data=this.rows;
		this.draw=draw;
	}
	
	

	/**
	 * @return the data
	 */
	public List<T> getData() {
		return data;
	}

	/**
	 * @param data the data to set
	 */
	public void setData(List<T> data) {
		this.data = data;
	}

	/**
	 * @return the draw
	 */
	public int getDraw() {
		return draw;
	}

	/**
	 * @param draw the draw to set
	 */
	public void setDraw(int draw) {
		this.draw = draw;
	}

	/**
	 * @return the recordsTotal
	 */
	public Long getRecordsTotal() {
		return recordsTotal;
	}

	/**
	 * @param recordsTotal the recordsTotal to set
	 */
	public void setRecordsTotal(Long recordsTotal) {
		this.recordsTotal = recordsTotal;
	}

	/**
	 * @return the recordsFiltered
	 */
	public Long getRecordsFiltered() {
		return recordsFiltered;
	}

	/**
	 * @param recordsFiltered the recordsFiltered to set
	 */
	public void setRecordsFiltered(Long recordsFiltered) {
		this.recordsFiltered = recordsFiltered;
	}

	/**
	 * @return the total
	 */
	public Long getTotal() {
		return total;
	}

	/**
	 * @param total the total to set
	 */
	public void setTotal(Long total) {
		this.total = total;
	}

	/**
	 * @return the rows
	 */
	public List<T> getRows() {
		return rows;
	}

	/**
	 * @param rows the rows to set
	 */
	public void setRows(List<T> rows) {
		this.rows = rows;
	}
}	

	