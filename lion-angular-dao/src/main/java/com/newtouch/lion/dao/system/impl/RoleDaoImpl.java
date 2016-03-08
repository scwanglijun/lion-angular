/*
 * Copyright (c)  2012, Newtouch
 * All rights reserved. 
 *
 * $id: TsRoleDaoImpl.java 9552 2012-12-31 下午5:30:44 WangLijun$
 */
package com.newtouch.lion.dao.system.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.newtouch.lion.common.sql.HqlUtils;
import com.newtouch.lion.dao.impl.BaseDaoImpl;
import com.newtouch.lion.dao.system.RoleDao;
import com.newtouch.lion.model.system.Role;
import com.newtouch.lion.page.PageResult;
import com.newtouch.lion.query.QueryCriteria;

/**
 * <p>
 * Title: 用户角色DaoImpl
 * </p>
 * <p>
 * Description: 用户角色DaoImpl
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
@Repository("roleDao")
public class RoleDaoImpl extends BaseDaoImpl<Role,Long> implements RoleDao {

	/**
	 * 
	 */
	private static final long serialVersionUID = 751517159442687213L;
	
	@Override
	public int doDeleteById(Long id) {
		String hql = "delete from Role p where p.id=:id";
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("id", id);
		return this.updateHQL(hql, params);
	}

	@Override
	public PageResult<Role> doFindByCriteria(QueryCriteria criteria) {
		String queryEntry = " from Role ";

		String[] whereBodies = { "nameZh like :nameZh"};

		String fromJoinSubClause = "";

		Map<String, Object> params = criteria.getQueryCondition();

		String orderField = criteria.getOrderField();

		String orderDirection = criteria.getOrderDirection();

		String hql = HqlUtils.generateHql(queryEntry, fromJoinSubClause,
				whereBodies, orderField, orderDirection, params);

		int pageSize = criteria.getPageSize();

		int startIndex = criteria.getStartIndex();

		PageResult<Role> pageResult = this.query(hql,HqlUtils.generateCountHql(hql, null), params, startIndex,pageSize);

		return pageResult;
	}

	@Override
	public PageResult<Role> doFindByCriteriaAndGroup(QueryCriteria criteria) {
		String queryEntry = " select role from Role as  role inner join fetch role.groups g ";

		String[] whereBodies = { "role.nameZh like :nameZh","g.id =:groupId","role.id in(:roleIds)"};

		String fromJoinSubClause = "";

		Map<String, Object> params = criteria.getQueryCondition();

		String orderField = criteria.getOrderField();

		String orderDirection = criteria.getOrderDirection();

		String hql = HqlUtils.generateHql(queryEntry, fromJoinSubClause,
				whereBodies, orderField, orderDirection, params);
		
		String countHql=HqlUtils.generateCountHql(hql," role.id ");

		int pageSize = criteria.getPageSize();

		int startIndex = criteria.getStartIndex();

		PageResult<Role> pageResult = this.query(hql,countHql, params, startIndex,pageSize);
		return pageResult;
	}

	@Override
	public PageResult<Role> doFindByCriteriaAndUser(QueryCriteria queryCriteria) {
		String queryEntry = " select role from Role as  role inner join fetch role.users u ";

		String[] whereBodies = { "role.nameZh like :nameZh","u.id =:userId","role.id in(:roleIds)"};

		String fromJoinSubClause = "";

		Map<String, Object> params = queryCriteria.getQueryCondition();

		String orderField = queryCriteria.getOrderField();

		String orderDirection = queryCriteria.getOrderDirection();

		String hql = HqlUtils.generateHql(queryEntry, fromJoinSubClause,
				whereBodies, orderField, orderDirection, params);
		
		String countHql=HqlUtils.generateCountHql(hql," role.id ");

		int pageSize = queryCriteria.getPageSize();

		int startIndex = queryCriteria.getStartIndex();

		PageResult<Role> pageResult = this.query(hql,countHql, params, startIndex,pageSize);
		return pageResult;
	}

	@Override
	public List<Role> doFindByUserId(Long userId) {
		String hql = "select role from Role role join role.users  user where user.id=:userId";
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("userId", userId);
		return this.query(hql, params);
	}

	@Override
	public Role doFindTypeByNameEn(String nameEn) {
		String hql = "from Role  where nameEn=:nameEn";
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("nameEn", nameEn);
		List<Role> roles = this.query(hql, params);
		if (roles != null && roles.size() > 0) {
			return roles.get(0);
		}
		return null;
	}

	
	
	
}
