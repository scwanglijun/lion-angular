package com.newtouch.lion.admin.web.model.datacolumn;

import com.newtouch.lion.model.datagrid.DataGrid;

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
public class DataColumnAddReq {
    private Long id;
    /** 当前显示顺序从1开始，用于控制列显示顺序 */
    private int showOrder = 1;
    /** 列映射字段名称 ，与Model绑定 */
    private Long dataGridId;
    private String field;
    /** 列名显示字段 */
    private String name;
    /** 显示列宽度：eg width=30 */
    private Integer width;
    /** rowspan Indicate how many rows a cell should take up. */
    private int rowspan;
    /** colspan Indicate how many columns a cell should take up. */
    private int colspan;
    /** 是否排列,Possible vlaues are:'true','false',default value:false */
    private Boolean sortable;
    /** 排列方式：ASC,DESC Default value is:ASC */
    private String order;
    /**
     * Indicate how to align the column header. Possible values
     * are:'left','cetner','right'
     */
    private String headerAlign;
    /** 对齐方式:'left','center','right' */
    private String align;
    /** True to hide the column, Default value is:false */
    private Boolean hidden;
    /**
     * True to show a checkbox. The checkbox column has fixed width. Default
     * value is:false
     */
    private Boolean checkbox;
    /**
     * The cell formatter funcation,take three parameters:value:the field
     * value.rowData:the row record data.rowIndex:the row index.
     */
    private String formatter;
    /**
     * The cell styler function,return style string to custom the cell style
     * such as 'backgroud:red'.The funcation take three parameter:value:the
     * field value.rowData:the row record data.rowIndex:the row index
     */
    private String styler;
    /**
     * sorter The custom field sort function that used to do local sorting, take
     * two parameters: a: the first field value. b: the second field value.
     */
    private String sorter;

    /**
     * Indicate the edit type. When string indicates the edit type, when object
     * contains two properties: type: string, the edit type, possible type is:
     * text,textarea,checkbox,numberbox,validatebox,datebox,combobox,combotree.
     * options: object, the editor options corresponding to the edit type.
     */
    private String editor;

    /** 数据 */
    private DataGrid dataGrid;

    public DataColumnAddReq() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getShowOrder() {
        return showOrder;
    }

    public void setShowOrder(int showOrder) {
        this.showOrder = showOrder;
    }

    public Long getDataGridId() {
        return dataGridId;
    }

    public void setDataGridId(Long dataGridId) {
        this.dataGridId = dataGridId;
    }

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getWidth() {
        return width;
    }

    public void setWidth(Integer width) {
        this.width = width;
    }

    public int getRowspan() {
        return rowspan;
    }

    public void setRowspan(int rowspan) {
        this.rowspan = rowspan;
    }

    public int getColspan() {
        return colspan;
    }

    public void setColspan(int colspan) {
        this.colspan = colspan;
    }

    public Boolean getSortable() {
        return sortable;
    }

    public void setSortable(Boolean sortable) {
        this.sortable = sortable;
    }

    public String getOrder() {
        return order;
    }

    public void setOrder(String order) {
        this.order = order;
    }

    public String getHeaderAlign() {
        return headerAlign;
    }

    public void setHeaderAlign(String headerAlign) {
        this.headerAlign = headerAlign;
    }

    public String getAlign() {
        return align;
    }

    public void setAlign(String align) {
        this.align = align;
    }

    public Boolean getHidden() {
        return hidden;
    }

    public void setHidden(Boolean hidden) {
        this.hidden = hidden;
    }

    public Boolean getCheckbox() {
        return checkbox;
    }

    public void setCheckbox(Boolean checkbox) {
        this.checkbox = checkbox;
    }

    public String getFormatter() {
        return formatter;
    }

    public void setFormatter(String formatter) {
        this.formatter = formatter;
    }

    public String getStyler() {
        return styler;
    }

    public void setStyler(String styler) {
        this.styler = styler;
    }

    public String getSorter() {
        return sorter;
    }

    public void setSorter(String sorter) {
        this.sorter = sorter;
    }

    public String getEditor() {
        return editor;
    }

    public void setEditor(String editor) {
        this.editor = editor;
    }

    public DataGrid getDataGrid() {
        return dataGrid;
    }

    public void setDataGrid(DataGrid dataGrid) {
        this.dataGrid = dataGrid;
    }
}
