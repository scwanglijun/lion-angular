/*
 * Copyright (c)  2012, Newtouch
 * All rights reserved. 
 *
 * $id: TsRoleDao.java 9552 2012-12-31 下午5:26:47 WangLijun$
 */
package com.newtouch.lion.dao.system;

import java.util.List;

import com.newtouch.lion.dao.BaseDao;
import com.newtouch.lion.model.system.Role;
import com.newtouch.lion.page.PageResult;
import com.newtouch.lion.query.QueryCriteria;

/**
 * <p>
 * Title: 角色Dao
 * </p>
 * <p>
 * Description: 用户角色Dao
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
public interface RoleDao extends BaseDao<Role,Long> {
	
	public int doDeleteById(Long id);
	
	public PageResult<Role> doFindByCriteria(QueryCriteria criteria);
	
	public PageResult<Role> doFindByCriteriaAndGroup(QueryCriteria criteria);
	
	public PageResult<Role> doFindByCriteriaAndUser(QueryCriteria queryCriteria);
	
	public List<Role> doFindByUserId(Long userId);
	
	public Role doFindTypeByNameEn(String nameEn);
	
	

}
