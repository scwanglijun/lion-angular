/*
* Copyright (c)  2015, Newtouch
* All rights reserved. 
*
* $id: CodeService.java 9552 2015年1月28日 下午5:01:23 WangLijun$
*/
package com.newtouch.lion.service.system; 

import java.util.List;
import java.util.Map;

import com.newtouch.lion.model.system.CodeList;
import org.hibernate.annotations.Cache;
import org.springframework.cache.annotation.Cacheable;

/**
 * <p>
 * Title: 通用代码接口定义
 * </p>
 * <p>
 * Description: 通用代码接口定义 主要面向外部提供通用服务
 * <br/>如返回Map<String,CodeList>,List<CodeList>
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
public interface CodeService {
	/***
	 * 根据参数类型英文名称查找数据列表，返回Map<String,CodeList>
	 * @param codeTypeNameEn 参数类型英文名称
	 * @return Map<String,CodeList> key-codeValue Value-CodeList
	 */
	@Cacheable(value ="codeCache",key="#codeTypeNameEn")
	public Map<Object, Object> doFindMap(String codeTypeNameEn);
	
	/***
	 * 根据参数类型英文名称查找数据列表，返回Map<String,CodeList>
	 * @param codeTypeNameEn 参数类型英文名称
	 * @return Map<String,String> key-codeValue Value-CodeList
	 */
	@Cacheable(value="codeCache",key="#codeTypeNameEn+'.doFindToMap'")
	public Map<String,String> doFindToMap(String codeTypeNameEn);
	/**
	 * 据参数类型英文名称查找数据列表,返回List<
	 * @param codeTypeNameEn 参数类型英文名称
	 * @return  List<CodeList> 编码列表
	 */
	public List<CodeList> doFindList(String codeTypeNameEn);
}	

	