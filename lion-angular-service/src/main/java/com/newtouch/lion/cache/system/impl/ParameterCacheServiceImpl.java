/*
 * Copyright (c)  2014, NewTouch
 * All rights reserved. 
 *
 * $id: ParameterServiceCacheImpl.java 9552 2014-4-11 下午09:08:17 WangLijun$
 */
package com.newtouch.lion.cache.system.impl;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.newtouch.lion.cache.ObjectCache;
import com.newtouch.lion.cache.system.ParameterCacheService;
import com.newtouch.lion.common.Assert;
import com.newtouch.lion.model.system.Parameter;
import com.newtouch.lion.query.QueryCriteria;
import com.newtouch.lion.service.system.ParameterService;

/**
 * <p>
 * Title:参数类缓存管理
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
@Service("parameterCacheService")
public class ParameterCacheServiceImpl implements ParameterCacheService {

	@Autowired
	private ParameterService parameterService;

	@Autowired(required = false)
	@Qualifier("sysParameterCache")
	private ObjectCache<Parameter> objectCache;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.newtouch.framework.cache.load.AbstractInitializedCache#initializedData
	 * ()
	 */
	@Override
	public void doFindInitializedData() {
		QueryCriteria queryCriteria = new QueryCriteria();
		queryCriteria.setPageSize(100);
		queryCriteria.setStartIndex(0);
		List<Parameter> list = this.doFindByQueryCriteria(queryCriteria);
		for (Parameter parameter : list) {
			objectCache.putObjectInCache(parameter.getNameEn(), parameter);
		}

	}

	public List<Parameter> doFindByQueryCriteria(QueryCriteria queryCriteria) {
		return this.parameterService.doFindByCriteria(queryCriteria)
				.getContent();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.newtouch.framework.cache.system.ParameterServiceCache#doFindByNameEn
	 * (java.lang.String)
	 */
	@Override
	public Parameter doFindByNameEn(String nameEn) {
		Assert.notNull(nameEn);
		Parameter parameter = this.objectCache.getObjectFromCache(nameEn);
		if (parameter == null) {
			parameter = this.parameterService.doFindTypeByNameEn(nameEn);
			this.objectCache.putObjectInCache(parameter.getNameEn(), parameter);
		}
		return parameter;
	}

	/* (non-Javadoc)
	 * @see com.newtouch.lion.cache.system.ParameterCacheService#getValue(java.lang.String)
	 */
	@Override
	public String getValue(String key) {
		Parameter parameter=this.doFindByNameEn(key);
		if(parameter!=null){
			return parameter.getValue();
		}
		return StringUtils.EMPTY;
	}
}
