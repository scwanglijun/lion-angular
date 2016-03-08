/*
 * Copyright (c)  2012, Newtouch
 * All rights reserved. 
 *
 * $id: TsCodeTypeDao.java 9552 2012-12-31 下午7:14:53 WangLijun$
 */
package com.newtouch.lion.dao.system.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.newtouch.lion.common.sql.HqlUtils;
import com.newtouch.lion.dao.impl.BaseDaoImpl;
import com.newtouch.lion.dao.system.CodeTypeDao;
import com.newtouch.lion.model.system.CodeType;
import com.newtouch.lion.page.PageResult;
import com.newtouch.lion.query.QueryCriteria;

/**
 * <p>
 * Title: 通用编码类型Dao实现类
 * </p>
 * <p>
 * Description: 通用编码类型Dao实现类
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
@Repository("codeTypeDao")
public class CodeTypeDaoImpl extends BaseDaoImpl<CodeType,Long> implements CodeTypeDao {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7307456949562991641L;
	
	@Override
	public CodeType doFindTypeByNameEn(String nameEn) {
		String hql = "from CodeType  where nameEn=:nameEn";
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("nameEn", nameEn);
		java.util.List<CodeType> codeTypes = this.query(hql, params);
		if (codeTypes != null && codeTypes.size() > 0) {
			return codeTypes.get(0);
		}
		return null;
	}
	
	@Override
	public PageResult<CodeType> doFindByCriteria(QueryCriteria criteria) {
		String queryEntry = "from CodeType";
		
		String[] whereBodies = {"type =:type","isDeleted=false"," nameZh  like :nameZh " };
		
		String fromJoinSubClause = "";
		
		Map<String, Object> params = criteria.getQueryCondition();
		
		String orderField = criteria.getOrderField();
		
		String orderDirection = criteria.getOrderDirection();
		
		String hql = HqlUtils.generateHql(queryEntry, fromJoinSubClause, whereBodies, orderField, orderDirection, params);
		
		int pageSize = criteria.getPageSize();
		
		int startIndex = criteria.getStartIndex();
		
		PageResult<CodeType> pageResult = this.query(hql, HqlUtils.generateCountHql(hql, null), params, startIndex, pageSize);
		
	 
		
		return pageResult;
	}
}
