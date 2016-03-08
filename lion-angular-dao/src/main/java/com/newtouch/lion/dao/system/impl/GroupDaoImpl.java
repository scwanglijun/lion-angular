/*
 * Copyright (c)  2012, Newtouch
 * All rights reserved. 
 *
 * $id: TsGroupDaoImpl.java 9552 2012-12-31 下午7:03:24 WangLijun$
 */
package com.newtouch.lion.dao.system.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.newtouch.lion.common.Assert;
import com.newtouch.lion.common.sql.HqlUtils;
import com.newtouch.lion.dao.impl.BaseDaoImpl;
import com.newtouch.lion.dao.system.GroupDao;
import com.newtouch.lion.model.system.Group;
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
@Repository("groupDao")
public class GroupDaoImpl extends BaseDaoImpl<Group,Long> implements GroupDao {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7374221945938855081L;
	
	@Override
	public List<Group> doFindByUserId(Long userId) {
		String hql = "from Group where users.id=:userId";
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("userId", userId);
		return this.query(hql, params);
	}
	
	@Override
	public List<Group> doFindByRoleId(Long roleId) {
		String hql = "from Group where roles.id=:roleId";
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("roleId", roleId);
		return this.query(hql, params);
	}
	
	@Override
	public PageResult<Group> doFindByCriteriaAndUser(QueryCriteria queryCriteria) {
		String queryEntry = " select groups from Group as  groups inner join fetch groups.users u ";

		String[] whereBodies = { "groups.nameZh like :nameZh","u.id =:userId","groups.id in(:groupIds)"};

		String fromJoinSubClause = "";

		Map<String, Object> params = queryCriteria.getQueryCondition();

		String orderField = queryCriteria.getOrderField();

		String orderDirection = queryCriteria.getOrderDirection();

		String hql = HqlUtils.generateHql(queryEntry, fromJoinSubClause,
				whereBodies, orderField, orderDirection, params);
		
		String countHql=HqlUtils.generateCountHql(hql," groups.id ");

		int pageSize = queryCriteria.getPageSize();

		int startIndex = queryCriteria.getStartIndex();

		PageResult<Group> pageResult = this.query(hql,countHql, params, startIndex,pageSize);
		return pageResult;
	}

	@Override
	public PageResult<Group> doFindByCriteria(QueryCriteria criteria) {
		String queryEntry = " from Group ";

		String[] whereBodies = {"nameZh like :nameZh"};

		String fromJoinSubClause = "";

		Map<String, Object> params = criteria.getQueryCondition();

		String orderField = criteria.getOrderField();

		String orderDirection = criteria.getOrderDirection();

		String hql = HqlUtils.generateHql(queryEntry, fromJoinSubClause,
				whereBodies, orderField, orderDirection, params);

		int pageSize = criteria.getPageSize();

		int startIndex = criteria.getStartIndex();

		PageResult<Group> pageResult = this.query(hql,
				HqlUtils.generateCountHql(hql, null), params, startIndex,
				pageSize);

		return pageResult;
	}
	
	@Override
	public Group doFindTypeByNameEn(String nameEn) {
		Assert.notNull(nameEn);
		String hql = "from Group  where nameEn=:nameEn";
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("nameEn", nameEn);
		List<Group> groups = this.query(hql, params);
		if (groups != null && groups.size() > 0) {
			return groups.get(0);
		}
		return null;
	}
	
	@Override
	public PageResult<Group> doFindByCriteriaAndRole(QueryCriteria criteria) {
		String queryEntry = " select groups from Group groups inner join groups.roles r ";

		String[] whereBodies = { "groups.nameZh like :nameZh","r.id =:roleId","groups.id in(:groupIds)"};

		String fromJoinSubClause = "";

		Map<String, Object> params = criteria.getQueryCondition();

		String orderField = criteria.getOrderField();

		String orderDirection = criteria.getOrderDirection();

		String hql = HqlUtils.generateHql(queryEntry, fromJoinSubClause,
				whereBodies, orderField, orderDirection, params);
		
		String countHql=HqlUtils.generateCountHql(hql,"groups.id");

		int pageSize = criteria.getPageSize();

		int startIndex = criteria.getStartIndex();

		PageResult<Group> pageResult = this.query(hql,countHql, params, startIndex,pageSize);
		
		return pageResult;
		
	}

	@Override
	public int doDeleteById(Long id) {
		String hql = "delete from Group p where p.id=:id";
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("id", id);
		return this.updateHQL(hql, params);
	}
	
}
