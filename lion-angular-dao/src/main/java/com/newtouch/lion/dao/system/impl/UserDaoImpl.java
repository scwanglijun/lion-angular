/*
 * Copyright (c)  2012, Newtouch
 * All rights reserved. 
 *
 * $id: TsUserDao.java 9552 2012-12-16 下午8:42:49 WangLijun$
 */
package com.newtouch.lion.dao.system.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;

import com.newtouch.lion.common.sql.HqlUtils;
import com.newtouch.lion.dao.impl.BaseDaoImpl;
import com.newtouch.lion.dao.system.UserDao;
import com.newtouch.lion.model.system.User;
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
@Repository("userDao")
public class UserDaoImpl extends BaseDaoImpl<User,Long> implements UserDao {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1985783112082066534L;

	@Override
	public User doFindByUserName(String username) {
		String hql = "from  User u where u.username=:username";
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("username", username);
		List<User> list = this.query(hql, params);
		if (!CollectionUtils.isEmpty(list)) {
			return list.get(0);
		}
		return null;
	}

	@Override
	public User doFindByEmpolyeeCode(String employeeCode) {
		String hql="from User u where u.employeeCode=:employeeCode";
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("employeeCode", employeeCode);
		List<User> list = this.query(hql, params);
		if (!CollectionUtils.isEmpty(list)) {
			return list.get(0);
		}
		return null;
	}

	@Override
	public User doFindByEmail(String email) {
		String hql="from User u where u.email=:email";
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("email", email);
		List<User> list = this.query(hql, params);
		if (!CollectionUtils.isEmpty(list)) {
			return list.get(0);
		}
		return null;
	}
	
	@Override
	public int doDeleteById(Long id) {
		String hql = "delete from User u where u.id=:id";
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("id", id);
		return this.updateHQL(hql, params);
	}
	
	@Override
	public PageResult<User> doFindByCriteriaAndGroup(QueryCriteria criteria) {
		String queryEntry = " select user from User as  user inner join fetch user.groups g ";

		String[] whereBodies = { "user.nameZh like :nameZh","g.id =:groupId","user.id in(:userIds)"};

		String fromJoinSubClause = "";

		Map<String, Object> params = criteria.getQueryCondition();

		String orderField = criteria.getOrderField();

		String orderDirection = criteria.getOrderDirection();

		String hql = HqlUtils.generateHql(queryEntry, fromJoinSubClause,
				whereBodies, orderField, orderDirection, params);
		
		String countHql=HqlUtils.generateCountHql(hql,"user.id ");

		int pageSize = criteria.getPageSize();

		int startIndex = criteria.getStartIndex();

		PageResult<User> pageResult = this.query(hql,countHql, params, startIndex,pageSize);
		
		return pageResult;
	}

	@Override
	public PageResult<User> doFindByCriteriaAndRole(QueryCriteria criteria) {
		String queryEntry = " select user from User as  user inner join fetch user.roles r ";

		String[] whereBodies = { "user.nameZh like :nameZh","r.id =:roleId","user.id in(:userIds)"};

		String fromJoinSubClause = "";

		Map<String, Object> params = criteria.getQueryCondition();

		String orderField = criteria.getOrderField();

		String orderDirection = criteria.getOrderDirection();

		String hql = HqlUtils.generateHql(queryEntry, fromJoinSubClause,
				whereBodies, orderField, orderDirection, params);
		
		String countHql=HqlUtils.generateCountHql(hql,"user.id ");

		int pageSize = criteria.getPageSize();

		int startIndex = criteria.getStartIndex();

		PageResult<User> pageResult = this.query(hql,countHql, params, startIndex,pageSize);
		
		return pageResult;
	}
	
	@Override
	public PageResult<User> doFindByCriteria(QueryCriteria criteria) {
		String queryEntry = " from User ";

		String[] whereBodies = { "username like :username", "employeeCode like :employeeCode","email like :email" };

		String fromJoinSubClause = "";

		Map<String, Object> params = criteria.getQueryCondition();

		String orderField = criteria.getOrderField();

		String orderDirection = criteria.getOrderDirection();

		String hql = HqlUtils.generateHql(queryEntry, fromJoinSubClause,
				whereBodies, orderField, orderDirection, params);

		int pageSize = criteria.getPageSize();

		int startIndex = criteria.getStartIndex();
		PageResult<User> pageResult = this.query(hql, 
				HqlUtils.generateCountHql(hql, null), params, startIndex, 
				pageSize);
		return pageResult;
	}
}
