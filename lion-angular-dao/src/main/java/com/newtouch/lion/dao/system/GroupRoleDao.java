
/*
* Copyright (c)  2015, NewTouch
* All rights reserved. 
*
* $id: GroupRoleDao.java 9552 Feb 15, 2015 1:31:09 PM MaoJiaWei$
*/
package com.newtouch.lion.dao.system; 

import com.newtouch.lion.dao.BaseDao;
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
public interface GroupRoleDao extends BaseDao<GroupRole,Long>{
	
	PageResult<GroupRole> doFindGroupRoleByCriteria(
			QueryCriteria criteria);
}

	