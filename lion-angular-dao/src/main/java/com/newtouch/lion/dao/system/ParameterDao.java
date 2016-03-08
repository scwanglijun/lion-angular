/*
 * Copyright (c)  2012, Newtouch
 * All rights reserved. 
 *
 * $id: TsParameter.java 9552 2012-7-8 上午01:37:15 WangLijun$
 */
package com.newtouch.lion.dao.system;

import com.newtouch.lion.dao.BaseDao;
import com.newtouch.lion.model.system.Parameter;
import com.newtouch.lion.page.PageResult;
import com.newtouch.lion.query.QueryCriteria;

/**
 * <p>
 * Title:
 * </p>
 * <p>
 * Description:
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
public interface ParameterDao extends BaseDao<Parameter, Long> {
	
	public Parameter doFindTypeByNameEn(String nameEn);
	
	public int doDeleteById(Long id);
	
	public PageResult<Parameter> doFindByCriteria(QueryCriteria queryCriteria);

}
