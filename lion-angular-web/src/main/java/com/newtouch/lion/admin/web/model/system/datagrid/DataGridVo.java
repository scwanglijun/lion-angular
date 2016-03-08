/*
 * Copyright (c)  2014, NewTouch
 * All rights reserved. 
 *
 * $id: DataGrid.java 9552 2014-4-2 上午11:15:46 WangLijun$
 */
package com.newtouch.lion.admin.web.model.system.datagrid;

import java.io.Serializable;

/**
 * <p>
 * Title: DataGridVo类
 * </p>
 * <p>
 * Description: DataGridVo类，用于字段验证
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
public class DataGridVo implements Serializable {

	/**
	 * *
	 */
	private static final long serialVersionUID = -3608093159721187393L;
	public static final  String TABLEID="tableId";
	/** ID 为DataGrid Id */
	private Long id;
	/** 表格类型 */
	private String type;
	/** 表格ID */
	private String tableId;
	/** 表格标题 */
	private String title;
	/** 是否表格标题 ,默认为false */
	private Boolean showTitle = Boolean.FALSE;
	/** 设置为true将自动使列适应表格宽度以防止出现水平滚动 */
	private Boolean fit = Boolean.FALSE;
	/** 跟列属性一样，但是这些列固定在左边，不会滚动。 */
	private Boolean frozen = Boolean.FALSE;
	/** 是否合并表头并分组显示，默认值：false */
	private Boolean showGroup = Boolean.FALSE;
	/** 是否显示分页显示条 */
	private Boolean pagination = Boolean.FALSE;
	/**
	 * True to auto expand/contract the size of the columns to fit the grid
	 * width and prevent horizontal scrolling.Default value is:false;
	 */
	private Boolean fitColumns = Boolean.FALSE;
	/** True to stripe the rows. Default value is:false */
	private Boolean striped = Boolean.FALSE;
	/** The method type to request remote data.Default value is:post */
	private String method;
	/** 默认设置为：true，当数据长度超出列宽时将会自动截取。 */
	private Boolean nowrap = Boolean.FALSE;
	/** A URL to request data from remote site. */
	private String url;
	/** The data to be loaded. */
	private String data;
	/**
	 * When loading data from remote site,show a prompt message.Default value
	 * is:'Processing, please wait …'
	 */
	private String loadMsg;

	/***
	 * True to show a row number column. Default value is:true;
	 */
	private Boolean rownumbers = Boolean.FALSE;
	/** True to allow selecting only one row. Default value is:true */
	private Boolean singleSelect = Boolean.FALSE;
	/**
	 * If true, the checkbox is checked/unchecked when the user clicks on a row.
	 * If false, the checkbox is only checked/unchecked when the user clicks
	 * exactly on the checkbox.
	 */
	private Boolean checkOnSelect = Boolean.FALSE;
	/**
	 * If set to true, clicking a checkbox will always select the row. If false,
	 * selecting a row will not check the checkbox.
	 */
	private Boolean selectOnCheck = Boolean.FALSE;
	/**
	 * Defines position of the pager bar. Available values are:
	 * 'top','bottom','both'.The default value is:bottom
	 */
	private String pagePosition;
	/** When set pagination property, initialize the page number. */
	private Long pageNumber = 1L;
	/** When set pagination property, initialize the page size. */
	private Long pageSize = 15L;
	/** When set pagination property, initialize the page size selecting list. */
	private String pageList;
	/** Defines which column can be sorted. */
	private String sortName;
	/** Defines the column sort order, can only be 'asc' or 'desc'. */
	private String sortOrder;
	/** Defines if to sort data from server. */
	private Boolean remoteSort = Boolean.FALSE;
	/** Defines if to show row header. */
	private Boolean showHeader = Boolean.FALSE;
	/** Defines if to show row footer. */
	private Boolean showFooter = Boolean.FALSE;
	/**
	 * The scrollbar width(when scrollbar is vertical) or height(when scrollbar
	 * is horizontal).
	 */
	private Integer scrollbarSize = 18;
	/**
	 * Return style such as 'background:red'. The function take two parameter:
	 * rowIndex: the row index, start with 0 rowData: the record corresponding
	 * to this row
	 */
	private String rowStyler;

	/**
	 * Defines how to load data from remote server. Return false can abort this
	 * action. This function takes following parameters: param: the parameter
	 * object to pass to remote server. success(data): the callback function
	 * that will be called when retrieve data successfully. error(): the
	 * callback function that will be called when failed to retrieve data.
	 */
	private String loader;

	/**
	 * Return the filtered data to display. The function take one parameter
	 * 'data' that indicate the original data. You can change original source
	 * data to standard data format. This function must return standard data
	 * object that contain 'total' and 'rows' properties.
	 */
	private String loadFilter;
	/** Defines the editor when editing a row. */
	private String editors;

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
	 * @return the tableId
	 */
	public String getTableId() {
		return tableId;
	}

	/**
	 * @param tableId
	 *            the tableId to set
	 */
	public void setTableId(String tableId) {
		this.tableId = tableId;
	}

	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * @param title
	 *            the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * @return the showTitle
	 */
	public Boolean getShowTitle() {
		return showTitle;
	}

	/**
	 * @param showTitle
	 *            the showTitle to set
	 */
	public void setShowTitle(Boolean showTitle) {
		this.showTitle = showTitle;
	}

	/**
	 * @return the fit
	 */
	public Boolean getFit() {
		return fit;
	}

	/**
	 * @param fit
	 *            the fit to set
	 */
	public void setFit(Boolean fit) {
		this.fit = fit;
	}

	/**
	 * @return the frozen
	 */
	public Boolean getFrozen() {
		return frozen;
	}

	/**
	 * @param frozen
	 *            the frozen to set
	 */
	public void setFrozen(Boolean frozen) {
		this.frozen = frozen;
	}

	/**
	 * @return the showGroup
	 */
	public Boolean getShowGroup() {
		return showGroup;
	}

	/**
	 * @param showGroup
	 *            the showGroup to set
	 */
	public void setShowGroup(Boolean showGroup) {
		this.showGroup = showGroup;
	}

	/**
	 * @return the pagination
	 */
	public Boolean getPagination() {
		return pagination;
	}

	/**
	 * @param pagination
	 *            the pagination to set
	 */
	public void setPagination(Boolean pagination) {
		this.pagination = pagination;
	}

	/**
	 * @return the fitColumns
	 */
	public Boolean getFitColumns() {
		return fitColumns;
	}

	/**
	 * @param fitColumns
	 *            the fitColumns to set
	 */
	public void setFitColumns(Boolean fitColumns) {
		this.fitColumns = fitColumns;
	}

	/**
	 * @return the striped
	 */
	public Boolean getStriped() {
		return striped;
	}

	/**
	 * @param striped
	 *            the striped to set
	 */
	public void setStriped(Boolean striped) {
		this.striped = striped;
	}

	/**
	 * @return the method
	 */
	public String getMethod() {
		return method;
	}

	/**
	 * @param method
	 *            the method to set
	 */
	public void setMethod(String method) {
		this.method = method;
	}

	/**
	 * @return the nowrap
	 */
	public Boolean getNowrap() {
		return nowrap;
	}

	/**
	 * @param nowrap
	 *            the nowrap to set
	 */
	public void setNowrap(Boolean nowrap) {
		this.nowrap = nowrap;
	}

	/**
	 * @return the url
	 */
	public String getUrl() {
		return url;
	}

	/**
	 * @param url
	 *            the url to set
	 */
	public void setUrl(String url) {
		this.url = url;
	}

	/**
	 * @return the data
	 */
	public String getData() {
		return data;
	}

	/**
	 * @param data
	 *            the data to set
	 */
	public void setData(String data) {
		this.data = data;
	}

	/**
	 * @return the loadMsg
	 */
	public String getLoadMsg() {
		return loadMsg;
	}

	/**
	 * @param loadMsg
	 *            the loadMsg to set
	 */
	public void setLoadMsg(String loadMsg) {
		this.loadMsg = loadMsg;
	}

	/**
	 * @return the rownumbers
	 */
	public Boolean getRownumbers() {
		return rownumbers;
	}

	/**
	 * @param rownumbers
	 *            the rownumbers to set
	 */
	public void setRownumbers(Boolean rownumbers) {
		this.rownumbers = rownumbers;
	}

	/**
	 * @return the singleSelect
	 */
	public Boolean getSingleSelect() {
		return singleSelect;
	}

	/**
	 * @param singleSelect
	 *            the singleSelect to set
	 */
	public void setSingleSelect(Boolean singleSelect) {
		this.singleSelect = singleSelect;
	}

	/**
	 * @return the checkOnSelect
	 */
	public Boolean getCheckOnSelect() {
		return checkOnSelect;
	}

	/**
	 * @param checkOnSelect
	 *            the checkOnSelect to set
	 */
	public void setCheckOnSelect(Boolean checkOnSelect) {
		this.checkOnSelect = checkOnSelect;
	}

	/**
	 * @return the selectOnCheck
	 */
	public Boolean getSelectOnCheck() {
		return selectOnCheck;
	}

	/**
	 * @param selectOnCheck
	 *            the selectOnCheck to set
	 */
	public void setSelectOnCheck(Boolean selectOnCheck) {
		this.selectOnCheck = selectOnCheck;
	}

	/**
	 * @return the pagePosition
	 */
	public String getPagePosition() {
		return pagePosition;
	}

	/**
	 * @param pagePosition
	 *            the pagePosition to set
	 */
	public void setPagePosition(String pagePosition) {
		this.pagePosition = pagePosition;
	}

	/**
	 * @return the pageNumber
	 */
	public Long getPageNumber() {
		return pageNumber;
	}

	/**
	 * @param pageNumber
	 *            the pageNumber to set
	 */
	public void setPageNumber(Long pageNumber) {
		this.pageNumber = pageNumber;
	}

	/**
	 * @return the pageSize
	 */
	public Long getPageSize() {
		return pageSize;
	}

	/**
	 * @param pageSize
	 *            the pageSize to set
	 */
	public void setPageSize(Long pageSize) {
		this.pageSize = pageSize;
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

	/**
	 * @return the showFooter
	 */
	public Boolean getShowFooter() {
		return showFooter;
	}

	/**
	 * @param showFooter
	 *            the showFooter to set
	 */
	public void setShowFooter(Boolean showFooter) {
		this.showFooter = showFooter;
	}

	/**
	 * @return the scrollbarSize
	 */
	public Integer getScrollbarSize() {
		return scrollbarSize;
	}

	/**
	 * @param scrollbarSize
	 *            the scrollbarSize to set
	 */
	public void setScrollbarSize(Integer scrollbarSize) {
		this.scrollbarSize = scrollbarSize;
	}

	/**
	 * @return the rowStyler
	 */
	public String getRowStyler() {
		return rowStyler;
	}

	/**
	 * @param rowStyler
	 *            the rowStyler to set
	 */
	public void setRowStyler(String rowStyler) {
		this.rowStyler = rowStyler;
	}

	/**
	 * @return the loader
	 */
	public String getLoader() {
		return loader;
	}

	/**
	 * @param loader
	 *            the loader to set
	 */
	public void setLoader(String loader) {
		this.loader = loader;
	}

	/**
	 * @return the loadFilter
	 */
	public String getLoadFilter() {
		return loadFilter;
	}

	/**
	 * @param loadFilter
	 *            the loadFilter to set
	 */
	public void setLoadFilter(String loadFilter) {
		this.loadFilter = loadFilter;
	}

	/**
	 * @return the editors
	 */
	public String getEditors() {
		return editors;
	}

	/**
	 * @param editors
	 *            the editors to set
	 */
	public void setEditors(String editors) {
		this.editors = editors;
	}

	/**
	 * @return the sortName
	 */
	public String getSortName() {
		return sortName;
	}

	/**
	 * @param sortName
	 *            the sortName to set
	 */
	public void setSortName(String sortName) {
		this.sortName = sortName;
	}

	/**
	 * @return the sortOrder
	 */
	public String getSortOrder() {
		return sortOrder;
	}

	/**
	 * @param sortOrder
	 *            the sortOrder to set
	 */
	public void setSortOrder(String sortOrder) {
		this.sortOrder = sortOrder;
	}

	/**
	 * @return the remoteSort
	 */
	public Boolean getRemoteSort() {
		return remoteSort;
	}

	/**
	 * @param remoteSort
	 *            the remoteSort to set
	 */
	public void setRemoteSort(Boolean remoteSort) {
		this.remoteSort = remoteSort;
	}

	/**
	 * @return the showHeader
	 */
	public Boolean getShowHeader() {
		return showHeader;
	}

	/**
	 * @param showHeader
	 *            the showHeader to set
	 */
	public void setShowHeader(Boolean showHeader) {
		this.showHeader = showHeader;
	}

	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}

	/**
	 * @param type
	 *            the type to set
	 */
	public void setType(String type) {
		this.type = type;
	}

}
