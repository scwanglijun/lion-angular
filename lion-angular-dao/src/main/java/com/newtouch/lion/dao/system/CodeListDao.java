/*
 * Copyright (c)  2012, Newtouch
 * All rights reserved. 
 *
 * $id: CodeListDao.java 9552 2012-12-31 下午7:11:26 WangLijun$
 */
package com.newtouch.lion.dao.system;

import java.util.List;

import com.newtouch.lion.dao.BaseDao;
import com.newtouch.lion.model.system.CodeList;
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
public interface CodeListDao extends BaseDao<CodeList, Long> {
	
	public int doDeleteById(Long id);
	
	public List<CodeList> doFindCodeListByCodeTypeNameEn(String codeTypeNameEn);
	
	public CodeList doFindCodeListByNameEn(String nameEn);
	
	public PageResult<CodeList> doFindByCriteria(QueryCriteria criteria);
}
