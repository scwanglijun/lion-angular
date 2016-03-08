/*
 * Copyright (c)  2012, Newtouch
 * All rights reserved. 
 *
 * $id: ParameterService.java 9552 2012-7-8 下午09:24:04 WangLijun$
 */
package com.newtouch.lion.service.system;

import java.util.List;

import com.newtouch.lion.model.system.CodeList;
import com.newtouch.lion.model.system.Parameter;
import com.newtouch.lion.page.PageResult;
import com.newtouch.lion.query.QueryCriteria;

/**
 * <p>
 * Title:系统参数定义Service接口
 * </p>
 * <p>
 * Description:用于保存系统参数设置
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
public interface ParameterService{
	
	/***
	 * 根据ID查询Parameter对象，并返回TsParameter对象
	 * @param id 参数ID
	 * @return parameter
	 */
	public Parameter doFindById(long id);
	/**
	 * 根据id删除parameter对象
	 * */
	public int doDeleteById(Long id);
	
	/**删除参数对象
	 * @param parameter
	 * */
	public void  doDelete(Parameter parameter);
	/***
	 * 查询所有参数对象，并返回列表
	 * @return
	 */
	public List<Parameter>  doFindAll();
	/***
	 * 根据不同查询条件查询系统参数，并返回分页对象
	 * @param queryCriteria
	 * @return PageResult<Parameter>
	 */
	public PageResult<Parameter>  doFindByCriteria(QueryCriteria queryCriteria);
	
	/***
	 * 保存Parameter对象
	 * @param parameter
	 */
	public void doCreate(Parameter parameter);
	
	 
	/**
	 * 判断参数的英文名是否已存在，
	 * @param nameEn
	 * @return  boolean
	 * */
	public boolean doIsExistByNameEn(String nameEn); 
	/***
	 * 根据参数英文名称获取参数列表
	 * @param type
	 * @param nameEn
	 * @return {@link Parameter}
	 */
	public Parameter  doFindTypeByNameEn(String nameEn);
	
	/***
	 * 更新参数对象
	 * @param parameter
	 * @return Parameter
	 */
	public Parameter doUpdate(Parameter parameter);
	
	/**更新Parameter对象
	 * @param id
	 * @param type
	 * @param nameEn
	 * @param nameZh
	 * @param value
	 * @param description
	 * @param editable
	 * */
	public void doUpdate(Long id, String type, String nameEn, String nameZh, String value, String description, Boolean editable);
	
	
	
	/***
	 * 根据通过编码类型英文名称，查询CodeList对象
	 * @param codeTypeNameEn
	 * @param defaultSelectValue
	 */
	public List<CodeList> doFindCodeListByNameEn(String codeTypeNameEn, String defaultSelectValue);
}
