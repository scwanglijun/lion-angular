/*
* Copyright (c)  2015, Newtouch
* All rights reserved. 
*
* $id: ExcelExportServiceImpl.java 9552 2015年1月27日 下午8:41:17 WangLijun$
*/
package com.newtouch.lion.service.excel.impl;

import com.newtouch.lion.common.excel.CellAlign;
import com.newtouch.lion.common.excel.CellDataType;
import com.newtouch.lion.common.excel.ExcelExport;
import com.newtouch.lion.common.file.FileUtil;
import com.newtouch.lion.common.number.NumberUtils;
import com.newtouch.lion.exception.ErrorCode;
import com.newtouch.lion.exception.ExcelException;
import com.newtouch.lion.model.datagrid.DataColumn;
import com.newtouch.lion.model.datagrid.DataGrid;
import com.newtouch.lion.model.system.CodeList;
import com.newtouch.lion.model.system.Department;
import com.newtouch.lion.service.excel.ExcelExportService;
import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.CellRangeAddress;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.util.*;

/**
 * <p>
 * Title: Excel通用导出实现
 * </p>
 * <p>
 * Description: Excel通用导出实现
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
@Service("excelExportService")
public class ExcelExportServiceImpl extends ExcelExport<Object>  implements ExcelExportService{
	
	/** 日志 */
	private final static Logger logger = LoggerFactory.getLogger(ExcelExportServiceImpl.class);	 
	
	/***Excel临时存放目录*/
	@Value("${excel.temp.path}")
	protected String excelTempPath;
	
	
	/* (non-Javadoc)
	 * @see com.newtouch.lion.service.excel.ExcelExportService#export(com.newtouch.lion.model.datagrid.DataGrid, java.util.Collection, java.io.OutputStream, java.util.Map)
	 */
	@Override
	public void export(DataGrid dataGrid, Collection<?> data, OutputStream out,
			Map<String, Map<Object, Object>> codeTypes) {
		this.export(dataGrid, data, out, codeTypes, null);
	}


	/* (non-Javadoc)
	 * @see com.newtouch.lion.service.excel.ExcelExportService#export(com.newtouch.lion.model.datagrid.DataGrid, java.util.Collection, java.io.OutputStream)
	 */
	@Override
	public void export(DataGrid dataGrid, Collection<?> data, OutputStream out) {
		this.export(dataGrid, data, out, null);
	}


	/* (non-Javadoc)
	 * @see com.newtouch.lion.service.excel.ExcelExportService#export(com.newtouch.lion.model.datagrid.DataGrid, java.util.Collection, java.lang.String, java.util.Map, java.util.Map)
	 */
	@Override
	public String export(DataGrid dataGrid, Collection<?> data,
			String fullFileName, Map<String, Map<Object, Object>> codeTypes,
			Map<String, String> dataFormats) {
		OutputStream out = null;
		File file=FileUtil.newFile(this.excelTempPath,fullFileName);
		fullFileName=file.getAbsolutePath();
		try {
			out=new FileOutputStream(file);
			this.export(dataGrid, data, out, codeTypes, dataFormats);
		} catch (FileNotFoundException e) {
		   throw new ExcelException(ErrorCode.EXCEL_FILE_NOTFOUND.code(),e.getMessage(),e);
		}finally{
			IOUtils.closeQuietly(out);
		}
		return fullFileName;
	}


	/* (non-Javadoc)
	 * @see com.newtouch.lion.service.excel.ExcelExportService#export(com.newtouch.lion.model.datagrid.DataGrid, java.util.Collection, java.lang.String, java.util.Map)
	 */
	@Override
	public String export(DataGrid dataGrid, Collection<?> data,
			String fullFileName, Map<String, Map<Object, Object>> codeTypes) {
		return this.export(dataGrid, data, fullFileName, codeTypes, null);
	}


	/* (non-Javadoc)
	 * @see com.newtouch.lion.service.excel.ExcelExportService#export(com.newtouch.lion.model.datagrid.DataGrid, java.util.Collection, java.lang.String)
	 */
	@Override
	public String export(DataGrid dataGrid, Collection<?> data,
			String fullFileName) {
		return this.export(dataGrid, data, fullFileName,null);
	}


	/* (non-Javadoc)
	 * @see com.newtouch.lion.service.excel.ExcelExportService#export(com.newtouch.lion.model.datagrid.DataGrid, java.util.Collection, java.io.OutputStream, java.util.Map, java.util.Map)
	 */
	@Override
	public void export(DataGrid dataGrid, Collection<?> data, OutputStream out,
			Map<String, Map<Object, Object>> codeTypes,
			Map<String, String> dataFormats) {
			// 声明一个工作薄
			this.createWorkBook();
			// 生成一个表格
			HSSFSheet sheet = this.createSheet(dataGrid.getTitle());
			// 设置行的样式
			HSSFCellStyle rowStyle = this.getRowStyle();
			// **画图的顶级管理器***
			HSSFPatriarch patriarch = sheet.createDrawingPatriarch();
			Integer index = 0;
			// 设置标题和标题栏的样式
			HSSFRow row;
		 
			// 设置标题和标题样式
			this.setHeader(sheet,dataGrid.getTitle(),dataGrid.getSortColumns(), index);
			index++;
			// 遍历集合数据，产生数据行
			Iterator<?> it = data.iterator();
			HSSFCellStyle temp=this.workbook.createCellStyle();
			while (it.hasNext()) {
				index++;
				row = sheet.createRow(index);
				Object obj = (Object) it.next();
				for (int i = 0; i <dataGrid.getSortColumns().size(); i++) {
					HSSFCell cell = row.createCell(i);
					DataColumn  dataColumn=dataGrid.getSortColumns().get(i);
					String fieldName = dataColumn.getField();
					Object value = this.getProperty(obj, fieldName);
					// 判断值的类型后进行强制类型转换
					String textValue = null;
					/**默认为字符串*/
					CellDataType dataType=CellDataType.STRING;
					
					if(!CollectionUtils.isEmpty(codeTypes)&&codeTypes.containsKey(fieldName)){
						textValue=this.getCellValueForCodeList(temp,fieldName, value, codeTypes);
					}else if (value instanceof Boolean) {
						textValue = this.getCellValueForBoolean(value, fieldName,codeTypes);
						dataType=CellDataType.BOOLEAN;
					} else if (value instanceof Date) {
						textValue = this.getCallValueForDate(value, fieldName,dataFormats);
						dataType=CellDataType.DATE;
					} else if (value instanceof byte[]) {
						dataType=CellDataType.BYTES;
						this.setCellForImage(sheet, patriarch, i, index, row, cell,value, rowStyle);
					} else {
						//其它数据类型都当作字符串简单处理
						textValue = String.valueOf(value==null?StringUtils.EMPTY:value);
					}
					
					if(value instanceof String && StringUtils.isNotEmpty(textValue)){
						this.setCellForString(temp,cell, textValue, rowStyle,dataType,dataColumn.getAlign());
					}else if (StringUtils.isNotEmpty(textValue)&&NumberUtils.isNumeric(textValue)) {						 
							dataType=CellDataType.NUMBER;
							this.setCellForNumber(temp,cell, textValue, rowStyle,dataType,dataColumn.getAlign());
					} else {
						textValue=(StringUtils.isEmpty(textValue)?StringUtils.EMPTY:textValue);
						this.setCellForString(temp,cell,textValue, rowStyle,dataType,dataColumn.getAlign());
					}
				}
			}
			try{
				this.workbook.write(out);
			}catch(IOException e){
				logger.error(e.getMessage(),e);
				throw new  ExcelException(ErrorCode.EXCEL_EXPORT_WRITE.code(),e.getMessage(),e);
			}
	}
	
	
	/***
	 * 
	 * @param cell 单元格
	 * @param textValue 数据
	 * @param cellStyle 单元格样式
	 * @param dataType 数据类型
	 * @param align 对齐方式
	 */
	protected void setCellForString(HSSFCellStyle temp,HSSFCell cell, String str,
			HSSFCellStyle cellStyle, CellDataType dataType, String align) {
		HSSFRichTextString richString = new HSSFRichTextString(str);
		cell.setCellValue(richString);
		cell.setCellStyle(cellStyle);
		
		temp.cloneStyleFrom(cell.getCellStyle());
		temp.setAlignment(this.getCellForAlign(dataType, align));
		cell.setCellStyle(temp);
		
	}

	/***
	 * 处理字格式
	 * @param cell 单元格
	 * @param textValue 数据
	 * @param cellStyle 单元格样式
	 * @param dataType 数据类型
	 * @param align 对齐方式
	 */
	protected void setCellForNumber(HSSFCellStyle temp,HSSFCell cell, String str,
			HSSFCellStyle cellStyle, CellDataType dataType, String align) {
		//是数字当作double处理	
		cell.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
		cell.setCellValue(Double.parseDouble(str));
		if(NumberUtils.isDecimal(str)){
			cellStyle=this.getCallDataStyle(this.decimalFmt);
		}
		cell.setCellStyle(cellStyle);
		temp.cloneStyleFrom(cell.getCellStyle());
		temp.setAlignment(this.getCellForAlign(dataType, align));
		cell.setCellStyle(temp);
	}


	/***
	 * Excel单元格对齐方式处理  ,如DataGrid中对齐方式与定义不匹配的情况的，使用Cell单元格默认对齐方式
	 * @param cellDateType 单元格数据类型
	 * @param align HTML单元格对齐方式;
	 * @return Excel中单元格对齐方式
	 */
	protected short getCellForAlign(CellDataType dataType,String align){
		
		if(CellAlign.CENTER.code().equalsIgnoreCase(align)){
			return CellAlign.CENTER.cellCode();
		}else if(CellAlign.LEFT.code().equalsIgnoreCase(align)){
			return CellAlign.LEFT.cellCode();
		}else  if(CellAlign.RIGHT.code().equalsIgnoreCase(align)){
			return CellAlign.RIGHT.cellCode();
		}else{
			return this.cellDefaultAlign(dataType);
		}
	}
	/***
	 * 根据数据类型设置默认对齐方式
	 * 数据默认右对齐，字符串左对齐、布尔和日期及字节数组居中对齐
	 * @param dataType
	 * @return
	 */
	protected short cellDefaultAlign(CellDataType dataType){
		short cellAlign=HSSFCellStyle.ALIGN_LEFT;
		switch (dataType.code()) {
		case 0:
			cellAlign=HSSFCellStyle.ALIGN_LEFT;
			break;
		case 1:
			cellAlign=HSSFCellStyle.ALIGN_RIGHT;
			break;
		case 2:
		case 3:
		case 4:
			cellAlign=HSSFCellStyle.ALIGN_CENTER;
			break;
		}
		return cellAlign;
	}
	
	/***
	 * @param fieldName 字段名称
	 * @param value     字段值
	 * @param codeTypes 参数数据
	 * @return  String
	 */
	protected String getCellValueForCodeList(HSSFCellStyle temp,String fieldName,Object value,Map<String,Map<Object,Object>> codeTypes){
		String filedValue=String.valueOf(value);
		if(value instanceof Department){
			 Department department=(Department) value;
			 return department.getNameZh();
		}
		if(!CollectionUtils.isEmpty(codeTypes)&&codeTypes.containsKey(fieldName)){
			 Map<Object,Object>  codeLists=codeTypes.get(fieldName);
			 if(!CollectionUtils.isEmpty(codeLists)&&codeLists.containsKey(filedValue)){
				 Object obj=codeLists.get(filedValue);
				 if(obj instanceof CodeList){
					 CodeList codeList=(CodeList) obj;
					 return codeList.getNameZh();
				 }else{
					 return String.valueOf(obj);
				 }
			 }
		}
		return filedValue;
	}
	
	/***
	 * 根据工作表、标题、数据列、行索引行成表头（标题、标题列）
	 * @param sheet 工作
	 * @param title 标题
	 * @param dataColumns 数据列表定义
	 * @param index 行索引
	 */
	protected void setHeader(HSSFSheet sheet, String title, List<DataColumn> dataColumns,
			Integer index) {
		// 设置标题栏样式
		HSSFCellStyle headerStyle = this.getHeaderStyle();
		// 产生表格标题栏
		Row rowTitle = sheet.createRow(index++);
		HSSFRow row = sheet.createRow(index++);
		for (int i = 0; i < dataColumns.size(); i++) {
			DataColumn dataColumn=dataColumns.get(i);
			Cell titleCell = rowTitle.createCell(i);
			if (i == 0) {
				rowTitle.createCell(i).setCellValue(new HSSFRichTextString(title));
				titleCell.setCellValue(title);
			}
			// 设置标签样式
			titleCell.setCellStyle(this.getHeaderTitleStytle());
			HSSFCell cell = row.createCell(i);
			cell.setCellStyle(headerStyle);
		    //设置宽度
			if(i!=0&&dataColumn.getWidth()!=null&&dataColumn.getWidth()>0){
				sheet.setColumnWidth(cell.getColumnIndex(),(dataColumn.getWidth()*70));
			}
			HSSFRichTextString text = new HSSFRichTextString(dataColumn.getName());
			cell.setCellValue(text);
		}
		Integer  callRangeColSize=dataColumns.size()>0?dataColumns.size()-1:1;
		sheet.addMergedRegion(new CellRangeAddress(0, 0, 0,callRangeColSize));
	}
	
	/***
	 * 根据对象获取字段的值，获取Value
	 * @param obj 对象
	 * @return Object的Value
	 */
	protected Object getProperty(Object obj,String fieldName){
		Object value=null;
		try {
			value = PropertyUtils.getProperty(obj,fieldName);
		} catch (IllegalAccessException e) {
			throw new  ExcelException(ErrorCode.EXCEL_PROPERTY_READ.code(),e.getMessage(),e);
		} catch (InvocationTargetException e) {
			throw new  ExcelException(ErrorCode.EXCEL_PROPERTY_READ.code(),e.getMessage(),e);
		} catch (NoSuchMethodException e) {
			throw new  ExcelException(ErrorCode.EXCEL_PROPERTY_READ.code(),e.getMessage(),e);
		}
		return value;
	}
	
	public static void main(String[] args) {
		File file =new File("D:/app/exce11l/temp/");    
		//如果文件夹不存在则创建    
		if(!file .exists()&&!file .isDirectory()){
		    file.mkdirs();
		}else{  
		    System.out.println("//目录存在");  
		} 
		System.out.println(file.getPath());
	}
	
}

	