/*
 * Copyright (c)  2013, Newtouch
 * All rights reserved. 
 *
 * $id: CodeListService.java 9552 2013-1-12 下午1:40:29 WangLijun$
 */
package com.newtouch.lion.service.system;

import java.util.List;

import com.newtouch.lion.model.system.CodeList;
import com.newtouch.lion.page.PageResult;
import com.newtouch.lion.query.QueryCriteria;

/**
 * <p>
 * Title: 通用编码定义列表Service
 * </p>
 * <p>
 * Description: 通用编码列表
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
public interface CodeListService {
	/***
	 * 创建CodeList对象
	 * 
	 * @param codeList
	 */
	public void doCreateObj(CodeList codeList);

	/** 根据ID，查询对象 */
	public CodeList doFindById(Long id);

	/**
	 * 根据Id删除CodeList对象
	 * 
	 * @param id
	 * @return
	 * */
	public int doDeleteById(Long id);

	/**
	 * 删除CodeList
	 * 
	 * @param codeList
	 * 
	 * */
	public void doDeleteByObj(CodeList codeList);

	/***
	 * 更新
	 */
	public void doUpdateByParams(Long id, Long codeTypeId, String codeValue,
								 String nameEn, String nameZh, int sortNo, Boolean editable);

	/** 更新 */
	public CodeList doUpdateObj(CodeList obj);
	
	/** 通用类型编码查询，并返回分页对象 */
	public PageResult<CodeList> doFindByCriteria(QueryCriteria criteria);

	/***
	 * 根据通过编码类型英文名称，查询CodeList对象
	 */
	public List<CodeList> doFindCodeListByCodeTypeNameEn(String codeTypeNameEn);

	/**
	 * 根据通过编码类型英文名称，查询CodeList对象,根据默认选择
	 * */
	public List<CodeList> doFindCodeListByNameEn(String codeTypeNameEn,
												 String selectedValue);

	/**
	 * 判断参数的英文名是否已存在，
	 * 
	 * @param nameEn
	 * @return boolean
	 * @author dengkang
	 * */
	public boolean doIsExistByNameEn(String nameEn);

	/***
	 * 根据通过编码列表英文名称，并返回JSON字符串
	 */
	// public String getCodeListForJSONByCodeTypeNameEn(String codeTypeNameEn);
	/***
	 * 保存CodeList对象
	 * @author maojiawei
	 * @param codeList
	 */
	public void doCreate(CodeList codeList);
}
