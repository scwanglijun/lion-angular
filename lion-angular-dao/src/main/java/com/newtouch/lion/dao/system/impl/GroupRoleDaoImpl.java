
/*
* Copyright (c)  2015, NewTouch
* All rights reserved. 
*
* $id: GroupRoleDaoImpl.java 9552 Feb 15, 2015 1:32:04 PM MaoJiaWei$
*/
package com.newtouch.lion.dao.system.impl; 

import java.util.Map;

import org.springframework.stereotype.Repository;

import com.newtouch.lion.common.sql.HqlUtils;
import com.newtouch.lion.dao.impl.BaseDaoImpl;
import com.newtouch.lion.dao.system.GroupRoleDao;
import com.newtouch.lion.model.system.GroupRole;
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
@Repository("groupRoleDao")
public class GroupRoleDaoImpl extends BaseDaoImpl<GroupRole,Long> implements GroupRoleDao{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2869798347710475837L;

	@Override
	public PageResult<GroupRole> doFindGroupRoleByCriteria(
			QueryCriteria criteria) {
		String queryEntry = "select new com.newtouch.lion.model.system.GroupRole(id,nameZh,nameEn,description) from Group ";

		String[] whereBodies = { "nameZh like :nameZh", "nameEn like :nameEn","description like :description" };

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

	