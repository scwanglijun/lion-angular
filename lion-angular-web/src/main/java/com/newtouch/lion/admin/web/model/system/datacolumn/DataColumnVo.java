/*
 * Copyright (c)  2014, NewTouch
 * All rights reserved. 
 *
 * $id: DatacColumnVo.java 9552 2014-4-3 上午11:05:01 WangLijun$
 */
package com.newtouch.lion.admin.web.model.system.datacolumn;

import java.io.Serializable;

/**
 * <p>
 * Title:
 * </p>
 * <p>
 * Description:
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
public class DataColumnVo implements Serializable {

	/**
		 * 
		 */
	private static final long serialVersionUID = 8656623018269420199L;
	public static final  String NAME="name";
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
	private Boolean sortable = Boolean.FALSE;
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
	private Boolean hidden = Boolean.FALSE;
	/**
	 * True to show a checkbox. The checkbox column has fixed width. Default
	 * value is:false
	 */
	private Boolean checkbox = Boolean.FALSE;
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
	 * @return the showOrder
	 */
	public int getShowOrder() {
		return showOrder;
	}

	/**
	 * @param showOrder
	 *            the showOrder to set
	 */
	public void setShowOrder(int showOrder) {
		this.showOrder = showOrder;
	}

	/**
	 * @return the dataGridId
	 */
	public Long getDataGridId() {
		return dataGridId;
	}

	/**
	 * @param dataGridId
	 *            the dataGridId to set
	 */
	public void setDataGridId(Long dataGridId) {
		this.dataGridId = dataGridId;
	}

	/**
	 * @return the field
	 */
	public String getField() {
		return field;
	}

	/**
	 * @param field
	 *            the field to set
	 */
	public void setField(String field) {
		this.field = field;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the width
	 */
	public Integer getWidth() {
		return width;
	}

	/**
	 * @param width
	 *            the width to set
	 */
	public void setWidth(Integer width) {
		this.width = width;
	}

	/**
	 * @return the rowspan
	 */
	public int getRowspan() {
		return rowspan;
	}

	/**
	 * @param rowspan
	 *            the rowspan to set
	 */
	public void setRowspan(int rowspan) {
		this.rowspan = rowspan;
	}

	/**
	 * @return the colspan
	 */
	public int getColspan() {
		return colspan;
	}

	/**
	 * @param colspan
	 *            the colspan to set
	 */
	public void setColspan(int colspan) {
		this.colspan = colspan;
	}

	/**
	 * @return the sortable
	 */
	public Boolean getSortable() {
		return sortable;
	}

	/**
	 * @param sortable
	 *            the sortable to set
	 */
	public void setSortable(Boolean sortable) {
		this.sortable = sortable;
	}

	/**
	 * @return the order
	 */
	public String getOrder() {
		return order;
	}

	/**
	 * @param order
	 *            the order to set
	 */
	public void setOrder(String order) {
		this.order = order;
	}

	/**
	 * @return the headerAlign
	 */
	public String getHeaderAlign() {
		return headerAlign;
	}

	/**
	 * @param headerAlign
	 *            the headerAlign to set
	 */
	public void setHeaderAlign(String headerAlign) {
		this.headerAlign = headerAlign;
	}

	/**
	 * @return the align
	 */
	public String getAlign() {
		return align;
	}

	/**
	 * @param align
	 *            the align to set
	 */
	public void setAlign(String align) {
		this.align = align;
	}

	/**
	 * @return the hidden
	 */
	public Boolean getHidden() {
		return hidden;
	}

	/**
	 * @param hidden
	 *            the hidden to set
	 */
	public void setHidden(Boolean hidden) {
		this.hidden = hidden;
	}

	/**
	 * @return the checkbox
	 */
	public Boolean getCheckbox() {
		return checkbox;
	}

	/**
	 * @param checkbox
	 *            the checkbox to set
	 */
	public void setCheckbox(Boolean checkbox) {
		this.checkbox = checkbox;
	}

	/**
	 * @return the formatter
	 */
	public String getFormatter() {
		return formatter;
	}

	/**
	 * @param formatter
	 *            the formatter to set
	 */
	public void setFormatter(String formatter) {
		this.formatter = formatter;
	}

	/**
	 * @return the styler
	 */
	public String getStyler() {
		return styler;
	}

	/**
	 * @param styler
	 *            the styler to set
	 */
	public void setStyler(String styler) {
		this.styler = styler;
	}

	/**
	 * @return the sorter
	 */
	public String getSorter() {
		return sorter;
	}

	/**
	 * @param sorter
	 *            the sorter to set
	 */
	public void setSorter(String sorter) {
		this.sorter = sorter;
	}

	/**
	 * @return the editor
	 */
	public String getEditor() {
		return editor;
	}

	/**
	 * @param editor
	 *            the editor to set
	 */
	public void setEditor(String editor) {
		this.editor = editor;
	}
}
