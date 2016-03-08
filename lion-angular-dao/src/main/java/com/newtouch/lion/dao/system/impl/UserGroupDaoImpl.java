/*
* Copyright (c)  2015, Newtouch
* All rights reserved. 
*
* $id: UserGroupDaoImpl.java 9552 2015年2月15日 上午12:00:40 WangLijun$
*/
package com.newtouch.lion.dao.system.impl; 

import java.util.Map;

import org.springframework.stereotype.Repository;

import com.newtouch.lion.common.sql.HqlUtils;
import com.newtouch.lion.dao.impl.BaseDaoImpl;
import com.newtouch.lion.dao.system.UserGroupDao;
import com.newtouch.lion.model.system.UserGroup;
import com.newtouch.lion.page.PageResult;
import com.newtouch.lion.query.QueryCriteria;

/**
 * <p>
 * Title: 用户与用户组查询(仅用于查询)
 * </p>
 * <p>
 * Description: 用户与用户组查询(仅用于查询)
 * </p>
 * <p>
 * Copyright: Copyright (c) 2015
 * </p>
 * <p>
 * Company: Newtouch
 * </p>
 * 
 * @author WangLijun
 * @version 1.0
 */
@Repository("userGroupDao")
public class UserGroupDaoImpl extends BaseDaoImpl<UserGroup,Long> implements UserGroupDao{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3056242994637897358L;

	@Override
	public PageResult<UserGroup> doFindUserGroupByCriteria(
			QueryCriteria criteria) {
		String queryEntry = "select new com.newtouch.lion.model.system.UserGroup(id,username,employeeCode,realnameZh,realnameEn) from User ";

		String[] whereBodies = { "username like :username", "employeeCode like :employeeCode","email like :email" };

		String fromJoinSubClause = "";

		Map<String, Object> params = criteria.getQueryCondition();

		String orderField = criteria.getOrderField();

		String orderDirection = criteria.getOrderDirection();

		String hql = HqlUtils.generateHql(queryEntry, fromJoinSubClause,
				whereBodies, orderField, orderDirection, params);

		int pageSize = criteria.getPageSize();

		int startIndex = criteria.getStartIndex();

		PageResult<UserGroup> pageResult = this.query(hql,
				HqlUtils.generateCountHql(hql, null), params, startIndex,
				pageSize);
		return pageResult;
	}
	
}

	