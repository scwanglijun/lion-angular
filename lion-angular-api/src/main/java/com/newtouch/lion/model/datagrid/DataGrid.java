/*
 * Copyright (c)  2013, Newtouch
 * All rights reserved. 
 *
 * $id: DataGrid.java 9552 2013-3-27 下午4:51:03 WangLijun$
 */
package com.newtouch.lion.model.datagrid;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.newtouch.lion.model.VersionEntity;

/**
 * <p>
 * Title:DataGrid Model
 * </p>
 * <p>
 * Description:DataGrid Model 定义
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
public class DataGrid extends VersionEntity<Long> {
	/** 序列化 */
	private static final long serialVersionUID = -2196344082980302505L;
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
	private Boolean fit;
	/** 跟列属性一样，但是这些列固定在左边，不会滚动。 */
	private Boolean frozen;
	/** 是否合并表头并分组显示，默认值：false */
	private Boolean showGroup;

	/** 是否显示分页显示条 */
	private Boolean pagination;
	/**
	 * True to auto expand/contract the size of the columns to fit the grid
	 * width and prevent horizontal scrolling.Default value is:false;
	 */
	private Boolean fitColumns;
	/** True to stripe the rows. Default value is:false */
	private Boolean striped;
	/** The method type to request remote data.Default value is:post */
	private String method;
	/** 默认设置为：true，当数据长度超出列宽时将会自动截取。 */
	private Boolean nowrap;
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
	private Boolean rownumbers;
	/** True to allow selecting only one row. Default value is:true */
	private Boolean singleSelect;
	/**
	 * If true, the checkbox is checked/unchecked when the user clicks on a row.
	 * If false, the checkbox is only checked/unchecked when the user clicks
	 * exactly on the checkbox.
	 */
	private Boolean checkOnSelect;
	/**
	 * If set to true, clicking a checkbox will always select the row. If false,
	 * selecting a row will not check the checkbox.
	 */
	private Boolean selectOnCheck;
	/**
	 * Defines position of the pager bar. Available values are:
	 * 'top','bottom','both'.The default value is:bottom
	 */
	private String pagePosition;
	/** When set pagination property, initialize the page number. */
	private Long pageNumber;
	/** When set pagination property, initialize the page size. */
	private Long pageSize;
	/** When set pagination property, initialize the page size selecting list. */
	private String pageList;
	/** When request remote data, sending additional parameters also. */
	private String queryParams;
	/** Defines which column can be sorted. */
	private String sortName;
	/** Defines the column sort order, can only be 'asc' or 'desc'. */
	private String sortOrder;
	/** Defines if to sort data from server. */
	private Boolean remoteSort;
	/** Defines if to show row header. */
	private Boolean showHeader;
	/** Defines if to show row footer. */
	private Boolean showFooter;
	/**
	 * The scrollbar width(when scrollbar is vertical) or height(when scrollbar
	 * is horizontal).
	 */
	private Integer scrollbarSize;
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
	/** Defines the view of datagrid. */
	private String view;
	/** 显示排序列的记录 */
	private List<DataColumn> sortColumns = new ArrayList<DataColumn>();
	/** 显示集合列 */
	private Set<DataColumn> columns = new HashSet<DataColumn>();

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.lion.framework.model.BaseEntity#getId()
	 */
	public Long getId() {
		return this.id;
	}

	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}

	/**
	 * @return the tableId
	 */
	public String getTableId() {
		return tableId;
	}

	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * @return the showTitle
	 */
	public Boolean getShowTitle() {
		return showTitle;
	}

	/**
	 * @return the fit
	 */
	public Boolean getFit() {
		return fit;
	}

	/**
	 * @return the frozen
	 */
	public Boolean getFrozen() {
		return frozen;
	}

	/**
	 * @return the showGroup
	 */
	public Boolean getShowGroup() {
		return showGroup;
	}

	/**
	 * @return the pagination
	 */
	public Boolean getPagination() {
		return pagination;
	}

	/**
	 * @return the fitColumns
	 */
	public Boolean getFitColumns() {
		return fitColumns;
	}

	/**
	 * @return the striped
	 */
	public Boolean getStriped() {
		return striped;
	}

	/**
	 * @return the method
	 */
	public String getMethod() {
		return method;
	}

	/**
	 * @return the nowrap
	 */
	public Boolean getNowrap() {
		return nowrap;
	}

	/**
	 * @return the url
	 */
	public String getUrl() {
		return url;
	}

	/**
	 * @return the data
	 */
	public String getData() {
		return data;
	}

	/**
	 * @return the loadMsg
	 */
	public String getLoadMsg() {
		return loadMsg;
	}

	/**
	 * @return the rownumbers
	 */
	public Boolean getRownumbers() {
		return rownumbers;
	}

	/**
	 * @return the singleSelect
	 */
	public Boolean getSingleSelect() {
		return singleSelect;
	}

	/**
	 * @return the checkOnSelect
	 */
	public Boolean getCheckOnSelect() {
		return checkOnSelect;
	}

	/**
	 * @return the selectOnCheck
	 */
	public Boolean getSelectOnCheck() {
		return selectOnCheck;
	}

	/**
	 * @return the pagePosition
	 */
	public String getPagePosition() {
		return pagePosition;
	}

	/**
	 * @return the pageNumber
	 */
	public Long getPageNumber() {
		return pageNumber;
	}

	/**
	 * @return the pageSize
	 */

	public Long getPageSize() {
		return pageSize;
	}

	/**
	 * @return the pageList
	 */

	public String getPageList() {
		return pageList;
	}

	/**
	 * @return the queryParams
	 */
	public String getQueryParams() {
		return queryParams;
	}

	/**
	 * @return the sortName
	 */
	public String getSortName() {
		return sortName;
	}

	/**
	 * @return the sortOrder
	 */

	public String getSortOrder() {
		return sortOrder;
	}

	/**
	 * @return the remoteSort
	 */
	public Boolean getRemoteSort() {
		return remoteSort;
	}

	/**
	 * @return the showHeader
	 */

	public Boolean getShowHeader() {
		return showHeader;
	}

	/**
	 * @return the showFooter
	 */

	public Boolean getShowFooter() {
		return showFooter;
	}

	/**
	 * @return the scrollbarSize
	 */

	public Integer getScrollbarSize() {
		return scrollbarSize;
	}

	/**
	 * @return the rowStyler
	 */

	public String getRowStyler() {
		return rowStyler;
	}

	/**
	 * @return the loader
	 */

	public String getLoader() {
		return loader;
	}

	/**
	 * @return the loadFilter
	 */

	public String getLoadFilter() {
		return loadFilter;
	}

	/**
	 * @return the editors
	 */

	public String getEditors() {
		return editors;
	}

	/**
	 * @return the view
	 */

	public String getView() {
		return view;
	}

	/**
	 * @return the columns
	 */

	public Set<DataColumn> getColumns() {
		return columns;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @param tableId
	 *            the tableId to set
	 */
	public void setTableId(String tableId) {
		this.tableId = tableId;
	}

	/**
	 * @param fit
	 *            the fit to set
	 */
	public void setFit(Boolean fit) {
		this.fit = fit;
	}

	/**
	 * @param frozen
	 *            the frozen to set
	 */
	public void setFrozen(Boolean frozen) {
		this.frozen = frozen;
	}

	/**
	 * @param showGroup
	 *            the showGroup to set
	 */
	public void setShowGroup(Boolean showGroup) {
		this.showGroup = showGroup;
	}

	/**
	 * @param columns
	 *            the columns to set
	 */
	public void setColumns(Set<DataColumn> columns) {
		this.columns = columns;
	}

	/**
	 * @param pagination
	 *            the pagination to set
	 */
	public void setPagination(Boolean pagination) {
		this.pagination = pagination;
	}

	/**
	 * @param fitColumns
	 *            the fitColumns to set
	 */
	public void setFitColumns(Boolean fitColumns) {
		this.fitColumns = fitColumns;
	}

	/**
	 * @param striped
	 *            the striped to set
	 */
	public void setStriped(Boolean striped) {
		this.striped = striped;
	}

	/**
	 * @param method
	 *            the method to set
	 */
	public void setMethod(String method) {
		this.method = method;
	}

	/**
	 * @param nowrap
	 *            the nowrap to set
	 */
	public void setNowrap(Boolean nowrap) {
		this.nowrap = nowrap;
	}

	/**
	 * @param url
	 *            the url to set
	 */
	public void setUrl(String url) {
		this.url = url;
	}

	/**
	 * @param data
	 *            the data to set
	 */
	public void setData(String data) {
		this.data = data;
	}

	/**
	 * @param loadMsg
	 *            the loadMsg to set
	 */
	public void setLoadMsg(String loadMsg) {
		this.loadMsg = loadMsg;
	}

	/**
	 * @param rownumbers
	 *            the rownumbers to set
	 */
	public void setRownumbers(Boolean rownumbers) {
		this.rownumbers = rownumbers;
	}

	/**
	 * @param singleSelect
	 *            the singleSelect to set
	 */
	public void setSingleSelect(Boolean singleSelect) {
		this.singleSelect = singleSelect;
	}

	/**
	 * @param checkOnSelect
	 *            the checkOnSelect to set
	 */
	public void setCheckOnSelect(Boolean checkOnSelect) {
		this.checkOnSelect = checkOnSelect;
	}

	/**
	 * @param selectOnCheck
	 *            the selectOnCheck to set
	 */
	public void setSelectOnCheck(Boolean selectOnCheck) {
		this.selectOnCheck = selectOnCheck;
	}

	/**
	 * @param pagePosition
	 *            the pagePosition to set
	 */
	public void setPagePosition(String pagePosition) {
		this.pagePosition = pagePosition;
	}

	/**
	 * @param pageNumber
	 *            the pageNumber to set
	 */
	public void setPageNumber(Long pageNumber) {
		this.pageNumber = pageNumber;
	}

	/**
	 * @param pageSize
	 *            the pageSize to set
	 */
	public void setPageSize(Long pageSize) {
		this.pageSize = pageSize;
	}

	/**
	 * @param pageList
	 *            the pageList to set
	 */
	public void setPageList(String pageList) {
		this.pageList = pageList;
	}

	/**
	 * @param queryParams
	 *            the queryParams to set
	 */
	public void setQueryParams(String queryParams) {
		this.queryParams = queryParams;
	}

	/**
	 * @param sortName
	 *            the sortName to set
	 */
	public void setSortName(String sortName) {
		this.sortName = sortName;
	}

	/**
	 * @param sortOrder
	 *            the sortOrder to set
	 */
	public void setSortOrder(String sortOrder) {
		this.sortOrder = sortOrder;
	}

	/**
	 * @param remoteSort
	 *            the remoteSort to set
	 */
	public void setRemoteSort(Boolean remoteSort) {
		this.remoteSort = remoteSort;
	}

	/**
	 * @param showHeader
	 *            the showHeader to set
	 */
	public void setShowHeader(Boolean showHeader) {
		this.showHeader = showHeader;
	}

	/**
	 * @param showFooter
	 *            the showFooter to set
	 */
	public void setShowFooter(Boolean showFooter) {
		this.showFooter = showFooter;
	}

	/**
	 * @param scrollbarSize
	 *            the scrollbarSize to set
	 */
	public void setScrollbarSize(Integer scrollbarSize) {
		this.scrollbarSize = scrollbarSize;
	}

	/**
	 * @param rowStyler
	 *            the rowStyler to set
	 */
	public void setRowStyler(String rowStyler) {
		this.rowStyler = rowStyler;
	}

	/**
	 * @param loader
	 *            the loader to set
	 */
	public void setLoader(String loader) {
		this.loader = loader;
	}

	/**
	 * @param loadFilter
	 *            the loadFilter to set
	 */
	public void setLoadFilter(String loadFilter) {
		this.loadFilter = loadFilter;
	}

	/**
	 * @param editors
	 *            the editors to set
	 */
	public void setEditors(String editors) {
		this.editors = editors;
	}

	/**
	 * @param view
	 *            the view to set
	 */
	public void setView(String view) {
		this.view = view;
	}

	/**
	 * @param title
	 *            the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * @param showTitle
	 *            the showTitle to set
	 */
	public void setShowTitle(Boolean showTitle) {
		this.showTitle = showTitle;
	}

	public List<DataColumn> getSortColumns() {
		return sortColumns;
	}

	public void setSortColumns(List<DataColumn> sortColumns) {
		this.sortColumns = sortColumns;
	}

	/**
	 * @param type
	 *            the type to set
	 */
	public void setType(String type) {
		this.type = type;
	}
}
