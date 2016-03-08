/*
 * Copyright (c)  2012, Newtouch
 * All rights reserved. 
 *
 * $id: TsCodeTypeDao.java 9552 2012-12-31 下午7:12:53 WangLijun$
 */
package com.newtouch.lion.dao.system;

import com.newtouch.lion.dao.BaseDao;
import com.newtouch.lion.model.system.CodeType;
import com.newtouch.lion.page.PageResult;
import com.newtouch.lion.query.QueryCriteria;

/**
 * <p>
 * Title: 通用编码类型Dao类
 * </p>
 * <p>
 * Description: 通用编码类型Dao类
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
public interface CodeTypeDao extends BaseDao<CodeType,Long> {
	
	public CodeType doFindTypeByNameEn(String nameEn);
	
	public PageResult<CodeType> doFindByCriteria(QueryCriteria criteria);

}
