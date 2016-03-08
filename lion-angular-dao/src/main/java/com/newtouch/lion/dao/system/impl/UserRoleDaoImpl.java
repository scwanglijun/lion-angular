
/*
* Copyright (c)  2015, NewTouch
* All rights reserved. 
*
* $id: UserRoleDaoImpl.java 9552 Feb 15, 2015 11:48:10 AM MaoJiaWei$
*/
package com.newtouch.lion.dao.system.impl; 

import java.util.Map;

import org.springframework.stereotype.Repository;

import com.newtouch.lion.common.sql.HqlUtils;
import com.newtouch.lion.dao.impl.BaseDaoImpl;
import com.newtouch.lion.dao.system.UserRoleDao;
import com.newtouch.lion.model.system.UserRole;
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
 * Copyright: Copyright (c) 2015
 * </p>
 * <p>
 * Company: NewTouch
 * </p>
 * 
 * @author MaoJiaWei
 * @version 1.0
 */
@Repository("userRoleDao")
public class UserRoleDaoImpl extends BaseDaoImpl<UserRole,Long> implements UserRoleDao{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8842508630699092859L;

	@Override
	public PageResult<UserRole> doFindUserRoleByCriteria(QueryCriteria criteria) {
		String queryEntry = "select new com.newtouch.lion.model.system.UserRole(id,username,employeeCode,realnameZh,realnameEn) from User ";
		
		String[] whereBodies = { "username like :username", "employeeCode like :employeeCode","email like :email" };
		
		String fromJoinSubClause = "";

		Map<String, Object> params = criteria.getQueryCondition();

		String orderField = criteria.getOrderField();

		String orderDirection = criteria.getOrderDirection();

		String hql = HqlUtils.generateHql(queryEntry, fromJoinSubClause,
				whereBodies, orderField, orderDirection, params);

		int pageSize = criteria.getPageSize();

		int startIndex = criteria.getStartIndex();
		
		return this.query(hql,HqlUtils.generateCountHql(hql, null),params,startIndex,pageSize);
	}

}

	