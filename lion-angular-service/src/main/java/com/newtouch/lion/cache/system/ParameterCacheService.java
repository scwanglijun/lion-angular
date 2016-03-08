/*
 * Copyright (c)  2014, NewTouch
 * All rights reserved. 
 *
 * $id: ParameterServiceCache.java 9552 2014-4-11 下午09:08:02 WangLijun$
 */
package com.newtouch.lion.cache.system;

import java.util.List;

import com.newtouch.lion.cache.load.AbstractInitializedCacheService;
import com.newtouch.lion.model.system.Parameter;
import com.newtouch.lion.query.QueryCriteria;

/**
 * <p>
 * Title: 参数类缓存管理
 * </p>
 * <p>
 * Description: 参数类缓存管理
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
public interface ParameterCacheService extends AbstractInitializedCacheService {
	

	/***
	 * 
	 * @param queryCriteria
	 *            查询
	 * @return
	 */
	public List<Parameter> doFindByQueryCriteria(QueryCriteria queryCriteria);

	/***
	 * 根据名称查找
	 * 
	 * @param nameEn
	 * @return Parameter
	 */
	public Parameter doFindByNameEn(String nameEn);
	/***
	 * 根据key 获取缓存的的数据
	 * @param key 缓存有KEY
	 * @return
	 */
	public String getValue(String key);

}
