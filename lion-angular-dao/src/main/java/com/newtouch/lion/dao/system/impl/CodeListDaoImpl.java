/*
 * Copyright (c)  2012, Newtouch
 * All rights reserved. 
 *
 * $id: TsCodeListDaoImpl.java 9552 2012-12-31 下午7:11:17 WangLijun$
 */
package com.newtouch.lion.dao.system.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.newtouch.lion.common.sql.HqlUtils;
import com.newtouch.lion.dao.impl.BaseDaoImpl;
import com.newtouch.lion.dao.system.CodeListDao;
import com.newtouch.lion.model.system.CodeList;
import com.newtouch.lion.page.PageResult;
import com.newtouch.lion.query.QueryCriteria;

/**
 * <p>
 * Title: 通用编码Dao实现类
 * </p>
 * <p>
 * Description: 通用编码Dao实现类
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
@Repository("codeListDao")
public class CodeListDaoImpl extends BaseDaoImpl<CodeList, Long> implements
		CodeListDao {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5326942517929124113L;
	
	@Override
	public int doDeleteById(Long id){
		String hql="delete from CodeList c where c.id=:id";
		Map<String,Object> params=new  HashMap<String, Object>();
		params.put("id",id);
		return this.updateHQL(hql, params);
	}
	
	@Override
	public List<CodeList> doFindCodeListByCodeTypeNameEn(String codeTypeNameEn){
		String hql="select cl from CodeList cl left join cl.codeType ct where ct.nameEn=:nameEn order by cl.sortNo ASC";
		Map<String,Object>  params=new HashMap<String, Object>();
		params.put("nameEn",codeTypeNameEn);
		return this.query(hql, params);
	}

	@Override
	public CodeList doFindCodeListByNameEn(String nameEn) {
		String hql = "from CodeList  where nameEn=:nameEn";
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("nameEn",nameEn);
		List<CodeList> codeList = this
				.query(hql, params);
		if (codeList != null&&codeList.size()>0) {
			return codeList.get(0);
		}
		return null;
	}
	
	@Override
	public PageResult<CodeList> doFindByCriteria(QueryCriteria criteria) {
		String queryEntry = " select cl from CodeList cl ";

		String[] whereBodies = {"cl.codeType.id=:codeTypeId"," cl.nameZh like :nameZh " };

		String fromJoinSubClause = "";

		Map<String, Object> params = criteria.getQueryCondition();

		String orderField =criteria.getOrderField();

		String orderDirection = criteria.getOrderDirection();

		String hql = HqlUtils.generateHql(queryEntry, fromJoinSubClause,whereBodies, orderField, orderDirection, params);

		int pageSize = criteria.getPageSize();

		int startIndex = criteria.getStartIndex();

		PageResult<CodeList> pageResult = this.query(hql,HqlUtils.generateCountHql(hql, null), params, startIndex,pageSize);
		
		return pageResult;
	}
}
