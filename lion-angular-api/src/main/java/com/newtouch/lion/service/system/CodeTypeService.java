/**
 * Copyright (c)  2013, Newtouch
 * All rights reserved. 
 *
 * $id: CodeTypeService.java 9552 2013-1-12 下午1:41:46 WangLijun$
 */
package com.newtouch.lion.service.system;

import java.util.List;

import com.newtouch.lion.model.system.CodeType;
import com.newtouch.lion.page.PageResult;
import com.newtouch.lion.query.QueryCriteria;
import com.newtouch.lion.vo.CodeTypeView;

/**
 * <p>
 * Title: 通用编码类型Service
 * </p>
 * <p>
 * Description: 通用编码类型Service
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
public interface CodeTypeService {
	/***
	 * 用于创建通用编码类型
	 */
	public void doCreateCodeType(CodeType codeType);

	/**
	 * 查询所有通用编码类型的数据
	 * */
	public List<CodeType> doFindAll();

	/** 根据codeType通用编码类型对象，更新数据 */
	public CodeType doUpdate(CodeType codeType);

	public CodeType doUpdateByParams(Long id, String type, String nameEn,
									 String nameZh, Integer codeLenLimit, Boolean editable);

	/**
	 * 根据Id查询通用编码类型对象，并返回编码类型对象。
	 */
	public CodeType doFindById(Long id);

	/**
	 * 根据Id查询通用编码类型对象，并返回编码类型对象。
	 * */
	public CodeType doGetById(Long id);

	/**
	 * 根据通用编码类型对象，删除通用编码类型
	 * */
	public void doDelete(CodeType codeType);

	/***
	 * 根据通用编码类型
	 */
	public int doDeleteById(Long id);

	/** 根据ID删除通用编码类型对象 */
	public void doLogicalDelete(Long id);

	/** 通用类型编码查询，并返回分页对象 */
	public PageResult<CodeType> doFindByCriteria(QueryCriteria criteria);
	
	/** 通用类型编码查询，并返回分页对象 */
	public PageResult<CodeTypeView> doFindVoByCriteria(QueryCriteria criteria);

	/**
	 * 通用类型编码查询，并返回分页对象的JSON字符串
	 * 
	 * @param criteria
	 *            查询条件
	 * @param tableId
	 *            表格名称
	 * @return String JSON字符串
	 * */
	public String doFindByCriteria(QueryCriteria criteria, String tableId);
	/**
	 * 判断编码类型的英文名是否已存在，
	 * @param nameEn
	 * @return  boolean
	 * @author maojiawei
	 * */
	public boolean doIsExistByNameEn(String nameEn); 
	/***
	 * 根据编码类型英文名称获取用户角色列表
	 * @param type
	 * @param nameEn
	 * @author maojiawei
	 * @return {@link CodeType}
	 */
	public CodeType  doFindTypeByNameEn(String nameEn);
	/***
	 * 保存CodeType对象
	 * @author maojiawei
	 * @param codeType
	 */
	public void doCreate(CodeType codeType);
}
