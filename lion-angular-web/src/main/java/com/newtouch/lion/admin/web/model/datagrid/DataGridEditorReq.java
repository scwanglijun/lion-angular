package com.newtouch.lion.admin.web.model.datagrid;

import com.newtouch.lion.model.datagrid.DataColumn;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * <p>
 * Title:
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Copyright: Copyright (c) 2016
 * </p>
 * <p>
 * Company:
 * </p>
 *
 * @author LiXiaoHao
 * @version 1.0
 */
public class DataGridEditorReq {
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

    public DataGridEditorReq() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTableId() {
        return tableId;
    }

    public void setTableId(String tableId) {
        this.tableId = tableId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Boolean getShowTitle() {
        return showTitle;
    }

    public void setShowTitle(Boolean showTitle) {
        this.showTitle = showTitle;
    }

    public Boolean getFit() {
        return fit;
    }

    public void setFit(Boolean fit) {
        this.fit = fit;
    }

    public Boolean getFrozen() {
        return frozen;
    }

    public void setFrozen(Boolean frozen) {
        this.frozen = frozen;
    }

    public Boolean getShowGroup() {
        return showGroup;
    }

    public void setShowGroup(Boolean showGroup) {
        this.showGroup = showGroup;
    }

    public Boolean getPagination() {
        return pagination;
    }

    public void setPagination(Boolean pagination) {
        this.pagination = pagination;
    }

    public Boolean getFitColumns() {
        return fitColumns;
    }

    public void setFitColumns(Boolean fitColumns) {
        this.fitColumns = fitColumns;
    }

    public Boolean getStriped() {
        return striped;
    }

    public void setStriped(Boolean striped) {
        this.striped = striped;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public Boolean getNowrap() {
        return nowrap;
    }

    public void setNowrap(Boolean nowrap) {
        this.nowrap = nowrap;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getLoadMsg() {
        return loadMsg;
    }

    public void setLoadMsg(String loadMsg) {
        this.loadMsg = loadMsg;
    }

    public Boolean getRownumbers() {
        return rownumbers;
    }

    public void setRownumbers(Boolean rownumbers) {
        this.rownumbers = rownumbers;
    }

    public Boolean getSingleSelect() {
        return singleSelect;
    }

    public void setSingleSelect(Boolean singleSelect) {
        this.singleSelect = singleSelect;
    }

    public Boolean getCheckOnSelect() {
        return checkOnSelect;
    }

    public void setCheckOnSelect(Boolean checkOnSelect) {
        this.checkOnSelect = checkOnSelect;
    }

    public Boolean getSelectOnCheck() {
        return selectOnCheck;
    }

    public void setSelectOnCheck(Boolean selectOnCheck) {
        this.selectOnCheck = selectOnCheck;
    }

    public String getPagePosition() {
        return pagePosition;
    }

    public void setPagePosition(String pagePosition) {
        this.pagePosition = pagePosition;
    }

    public Long getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(Long pageNumber) {
        this.pageNumber = pageNumber;
    }

    public Long getPageSize() {
        return pageSize;
    }

    public void setPageSize(Long pageSize) {
        this.pageSize = pageSize;
    }

    public String getPageList() {
        return pageList;
    }

    public void setPageList(String pageList) {
        this.pageList = pageList;
    }

    public String getQueryParams() {
        return queryParams;
    }

    public void setQueryParams(String queryParams) {
        this.queryParams = queryParams;
    }

    public String getSortName() {
        return sortName;
    }

    public void setSortName(String sortName) {
        this.sortName = sortName;
    }

    public String getSortOrder() {
        return sortOrder;
    }

    public void setSortOrder(String sortOrder) {
        this.sortOrder = sortOrder;
    }

    public Boolean getRemoteSort() {
        return remoteSort;
    }

    public void setRemoteSort(Boolean remoteSort) {
        this.remoteSort = remoteSort;
    }

    public Boolean getShowHeader() {
        return showHeader;
    }

    public void setShowHeader(Boolean showHeader) {
        this.showHeader = showHeader;
    }

    public Boolean getShowFooter() {
        return showFooter;
    }

    public void setShowFooter(Boolean showFooter) {
        this.showFooter = showFooter;
    }

    public Integer getScrollbarSize() {
        return scrollbarSize;
    }

    public void setScrollbarSize(Integer scrollbarSize) {
        this.scrollbarSize = scrollbarSize;
    }

    public String getRowStyler() {
        return rowStyler;
    }

    public void setRowStyler(String rowStyler) {
        this.rowStyler = rowStyler;
    }

    public String getLoader() {
        return loader;
    }

    public void setLoader(String loader) {
        this.loader = loader;
    }

    public String getLoadFilter() {
        return loadFilter;
    }

    public void setLoadFilter(String loadFilter) {
        this.loadFilter = loadFilter;
    }

    public String getEditors() {
        return editors;
    }

    public void setEditors(String editors) {
        this.editors = editors;
    }

    public String getView() {
        return view;
    }

    public void setView(String view) {
        this.view = view;
    }

    public List<DataColumn> getSortColumns() {
        return sortColumns;
    }

    public void setSortColumns(List<DataColumn> sortColumns) {
        this.sortColumns = sortColumns;
    }

    public Set<DataColumn> getColumns() {
        return columns;
    }

    public void setColumns(Set<DataColumn> columns) {
        this.columns = columns;
    }
}
