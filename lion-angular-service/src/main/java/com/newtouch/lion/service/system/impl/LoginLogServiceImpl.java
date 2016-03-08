/*
 * Copyright (c)  2013, Newtouch
 * All rights reserved. 
 *
 * $id: LoginLogServiceImpl.java 9552 2013-1-12 下午8:37:13 WangLijun$
 */
package com.newtouch.lion.service.system.impl;

import com.newtouch.lion.common.sql.HqlUtils;
import com.newtouch.lion.dao.system.LoginLogDao;
import com.newtouch.lion.dao.system.LoginLogGroupDao;
import com.newtouch.lion.model.system.LoginLog;
import com.newtouch.lion.model.system.LoginLogGroup;
import com.newtouch.lion.page.PageResult;
import com.newtouch.lion.query.QueryCriteria;
import com.newtouch.lion.service.AbstractService;
import com.newtouch.lion.service.system.LoginLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * <p>
 * Title:
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Copyright: Copyright (c) 2013
 * </p>
 * <p>
 * Company: Newtouch
 * </p>
 * 
 * @author WangLijun
 * @version 1.0
 */
@Service("loginLogService")
public class LoginLogServiceImpl extends AbstractService implements
		LoginLogService{
	
	@Autowired
	private LoginLogDao loginLogDao;
	
	@Autowired
	private LoginLogGroupDao loginLogGroupDao;
	 
	/**
	 * 查询
	  *
	  * @param queryCriteria
	  * 
	  */
	public PageResult<LoginLogGroup> doFindByCriteria(QueryCriteria queryCriteria) {
		String queryEntry = "select  new com.newtouch.lion.model.system.LoginLogGroup(lg.loginType,lg.loginTime,lg.logoutTime,user1.username,lg.osInfo) "
				+ " from LoginLog lg left join lg.user user1 ";

		String[] whereBodies = { "user1.username like :username"};

		String fromJoinSubClause = "";

		Map<String, Object> params = queryCriteria.getQueryCondition();

		String orderField ="";

		String orderDirection = queryCriteria.getOrderDirection();

		String hql = HqlUtils.generateHql(queryEntry, fromJoinSubClause,
				whereBodies, orderField, orderDirection, params);

		int pageSize = queryCriteria.getPageSize();

		int startIndex = queryCriteria.getStartIndex();

		PageResult<LoginLogGroup> result = this.loginLogGroupDao.query(hql,
				HqlUtils.generateCountHql(hql, null), params, startIndex,
				pageSize);

		return result;
	}

	@Override
	public void save(LoginLog loginLog) {
		this.loginLogDao.save(loginLog);
	}
	
}
