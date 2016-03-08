/*
 * Copyright (c)  2012, Newtouch
 * All rights reserved. 
 *
 * $id: TsUserDao.java 9552 2012-12-16 下午8:41:26 WangLijun$
 */
package com.newtouch.lion.dao.system;

import com.newtouch.lion.dao.BaseDao;
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
public interface UserDao extends BaseDao<User,Long> {
	
	public User doFindByUserName(String username);
	
	public User doFindByEmpolyeeCode(String employeeCode);
	
	public User doFindByEmail(String email);
	
	public int doDeleteById(Long id);
	
	public PageResult<User> doFindByCriteriaAndGroup(QueryCriteria criteria);
	
	public PageResult<User> doFindByCriteriaAndRole(QueryCriteria criteria);
	
	public PageResult<User> doFindByCriteria(QueryCriteria criteria);

}
