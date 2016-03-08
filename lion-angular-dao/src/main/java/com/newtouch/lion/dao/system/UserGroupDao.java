/*
* Copyright (c)  2015, Newtouch
* All rights reserved. 
*
* $id: UserGroupDao.java 9552 2015年2月14日 下午11:58:48 WangLijun$
*/
package com.newtouch.lion.dao.system; 

import com.newtouch.lion.dao.BaseDao;
import com.newtouch.lion.model.system.UserGroup;
import com.newtouch.lion.page.PageResult;
import com.newtouch.lion.query.QueryCriteria;

/**
 * <p>
 * Title: 用户与用户组查询
 * </p>
 * <p>
 * Description: 用户与用户组查询
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
public interface UserGroupDao extends BaseDao<UserGroup,Long>{
	
	public PageResult<UserGroup> doFindUserGroupByCriteria(
			QueryCriteria criteria);
	
}	

	