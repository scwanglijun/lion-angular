/*
 * Copyright (c)  2012, Newtouch
 * All rights reserved. 
 *
 * $id: TsParameterDaoImpl.java 9552 2012-7-8 上午01:39:26 WangLijun$
 */
package com.newtouch.lion.dao.system.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.newtouch.lion.common.sql.HqlUtils;
import com.newtouch.lion.dao.impl.BaseDaoImpl;
import com.newtouch.lion.dao.system.ParameterDao;
import com.newtouch.lion.model.system.Parameter;
import com.newtouch.lion.page.PageResult;
import com.newtouch.lion.query.QueryCriteria;

/**
 * <p>
 * Title:参数配置信息数据存储类
 * </p>
 * <p>
 * Description:用天处理参数配置信息，保存、更新、保存、删除等
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
@Repository("parameterDao")
public class ParameterDaoImpl extends BaseDaoImpl<Parameter,Long> implements ParameterDao {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1772480301507118710L;

	@Override
	public Parameter doFindTypeByNameEn(String nameEn) {
		String hql = "from Parameter  where nameEn=:nameEn";
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("nameEn", nameEn);
		java.util.List<Parameter> parameters = this.query(hql, params);
		if (parameters != null && parameters.size() > 0) {
			return parameters.get(0);
		}
		return null;
	}

	@Override
	public int doDeleteById(Long id) {
		String hql = "delete from Parameter p where p.id=:id";
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("id", id);
		return this.updateHQL(hql, params);
	}
	
	@Override
	public PageResult<Parameter> doFindByCriteria(QueryCriteria queryCriteria) {

		String queryEntry = "select parameter ";

		String[] whereBodies = { " parameter.type=:type "," parameter.nameZh  like :nameZh "};

		String fromJoinSubClause = "from Parameter parameter";

		Map<String, Object> params = queryCriteria.getQueryCondition();

		String orderField = queryCriteria.getOrderField();

		String orderDirection = queryCriteria.getOrderDirection();

		String hql = HqlUtils.generateHql(queryEntry, fromJoinSubClause,
				whereBodies, orderField, orderDirection, params);

		int pageSize = queryCriteria.getPageSize();

		int startIndex = queryCriteria.getStartIndex();

		PageResult<Parameter> result = this.query(hql,
				HqlUtils.generateCountHql(hql, null), params, startIndex,
				pageSize);

		return result;
	}
	
}
