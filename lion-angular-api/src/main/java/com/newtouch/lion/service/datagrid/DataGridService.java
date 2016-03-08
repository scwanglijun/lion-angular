/*
 * Copyright (c)  2013, Newtouch
 * All rights reserved. 
 *
 * $id: DataGridService.java 9552 2013-3-27 下午6:16:54 WangLijun$
 */
package com.newtouch.lion.service.datagrid;

import java.util.List;

import com.newtouch.lion.model.datagrid.DataGrid;
import com.newtouch.lion.page.PageResult;
import com.newtouch.lion.query.QueryCriteria;

/**
 * <p>
 * Title:DataGrid 数据表格
 * </p>
 * <p>
 * Description:DataGrid 数据表格创建
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
public interface DataGridService {
	/**
	 * 创建数据表格对象
	 * @param dataGrid
	 */
	public void doCreateDataGrid(DataGrid dataGrid);
	/***
	 * 
	 * 根据tableId的ID获取DataGrid数据表
	 * @param tableId 表格ID
	 * @return DataGrid
	 */
	@Deprecated
	public DataGrid doFindByTableId(String tableId);
	/****
	 * 根据tableId的ID获取DataGrid数据表,并列表进行排序
	 * @param tableId  表格ID
	 * @return DataGrid 数据 
	 */
	public DataGrid doFindByTableIdAndSort(String tableId);

	public List<DataGrid> doFindAll();

	public List<DataGrid> doFindByType(String type);

	public DataGrid doGetById(Long id);

	public DataGrid doUpdate(DataGrid dataGrid);

	public void doDelete(DataGrid dataGrid);

	public int doDeleteById(Long id);

	public PageResult<DataGrid> doFindByCriteria(QueryCriteria queryCriteria);

	public String doFindByCriteria(QueryCriteria queryCriteria, String tableId);

	public String doFindAllForCombox();

	public String doFindComboxByType(String type);
	/**
	 * 判断DataGrid的tableId是否已存在，
	 * @param tableId
	 * @return  boolean
	 * @author maojiawei
	 * */
	public boolean doIsExistByTableId(String tableId); 
	/***
	 * 根据tableId称获取DataGrid
	 * @param type
	 * @param tableId
	 * @author maojiawei
	 * @return {@link DataGrid}
	 */
	public DataGrid doFindTypeByTableId(String tableId);
	/***
	 * 保存DataGrid对象
	 * @author maojiawei
	 * @param DataGrid
	 */
	public void doCreate(DataGrid dataGrid);
}
