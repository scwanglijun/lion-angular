
/*
* Copyright (c)  2015, NewTouch
* All rights reserved. 
*
* $id: UserRoleDao.java 9552 Feb 15, 2015 11:31:38 AM MaoJiaWei$
*/
package com.newtouch.lion.dao.system; 

import com.newtouch.lion.dao.BaseDao;
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
public interface UserRoleDao extends BaseDao<UserRole,Long> {
	public PageResult<UserRole> doFindUserRoleByCriteria(
			QueryCriteria criteria);
}

	