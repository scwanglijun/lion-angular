/*
* Copyright (c)  2015, Newtouch
* All rights reserved. 
*
* $id: ExcelService.java 9552 2015年1月26日 下午4:32:27 WangLijun$
*/
package com.newtouch.lion.service.excel; 

import java.io.OutputStream;
import java.util.Collection;
import java.util.Map;

import com.newtouch.lion.model.datagrid.DataGrid;

/**
 * <p>
 * Title: Excel导出功能接口定义
 * </p>
 * <p>
 * Description: Excel导出功能接口定义
 * <br/>根据DataGrid导出数据
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
public interface ExcelExportService{
	
	/****
	 * 根据dataGrid,数据集，输出流，IM转换、数据格式导出Excel
	 * @param dataGrid 
	 * @param dataset 数据集
	 * @param out 输出流
	 * @param codeTypes IM 
	 * @param dataFormats 数据格式化
	 */
	public void export(DataGrid dataGrid, Collection<?> data, OutputStream out, Map<String, Map<Object, Object>> codeTypes, Map<String, String> dataFormats);
	
	
	/****
	 * 根据dataGrid,数据集，输出流，IM转换、导出Excel
	 * @param dataGrid 
	 * @param dataset 数据集
	 * @param out 输出流
	 * @param codeTypes IM
	 */
	public void export(DataGrid dataGrid, Collection<?> data, OutputStream out, Map<String, Map<Object, Object>> codeTypes);
	
	/****
	 * 根据dataGrid,数据集，输出流、导出Excel
	 * @param dataGrid 
	 * @param dataset 数据集
	 * @param codeTypes IM
	 */
	public void export(DataGrid dataGrid, Collection<?> data, OutputStream out);
	
	/****
	 * 根据dataGrid,数据集，文件名，IM转换、数据格式导出Excel
	 * @param dataGrid 数据集
	 * @param data
	 * @param fullFileName 文件名
	 * @param codeTypes IM
	 * @param dataFormats 数据格式定义
	 * @return 文件名（全路径的文件名）
	 */
	public String export(DataGrid dataGrid, Collection<?> data, String fullFileName, Map<String, Map<Object, Object>> codeTypes, Map<String, String> dataFormats);
	
	
	/****
	 * 根据dataGrid,数据集，文件名，IM转换导出Excel
	 * @param dataGrid
	 * @param data
	 * @param fullFileName 文件名
	 * @param codeTypes IM
	 * @return 文件名（全路径的文件名）
	 */
	public String export(DataGrid dataGrid, Collection<?> data, String fullFileName, Map<String, Map<Object, Object>> codeTypes);
	
	/****
	 * 根据dataGrid,数据集，文件全路径，IM转换导出Excel
	 * @param dataGrid 
	 * @param data 数据集
	 * @param fullFileName  文件名
	 *   @return 文件名（全路径的文件名）
	 */
	public String export(DataGrid dataGrid, Collection<?> data, String fullFileName);
}