/*
* Copyright (c)  2015, Newtouch
* All rights reserved. 
*
* $id: RoleGroup.java 9552 2015年2月15日 上午9:58:18 WangLijun$
*/
package com.newtouch.lion.dao.system; 

import com.newtouch.lion.dao.BaseDao;
import com.newtouch.lion.model.system.RoleGroup;
import com.newtouch.lion.page.PageResult;
import com.newtouch.lion.query.QueryCriteria;

/**
 * <p>
 * Title: 用户角色与用户、用户组查询对象
 * </p>
 * <p>
 * Description: 用户角色与用户、用户组查询对象
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
public interface RoleGroupDao extends BaseDao<RoleGroup,Long>{
	
	public PageResult<RoleGroup> doFindRoleUserByCriteria(QueryCriteria queryCriteria);
		
}

	