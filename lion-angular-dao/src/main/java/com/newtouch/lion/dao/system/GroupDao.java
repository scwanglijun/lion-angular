/*
 * Copyright (c)  2012, Newtouch
 * All rights reserved. 
 *
 * $id: TsGroupDao.java 9552 2012-12-31 下午7:01:40 WangLijun$
 */
package com.newtouch.lion.dao.system;

import java.util.List;

import com.newtouch.lion.dao.BaseDao;
import com.newtouch.lion.model.system.Group;
import com.newtouch.lion.page.PageResult;
import com.newtouch.lion.query.QueryCriteria;

/**
 * <p>
 * Title: 用户组管理Dao类
 * </p>
 * <p>
 * Description: 用户组管理Dao类
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
public interface GroupDao extends BaseDao<Group,Long> {
	
	public List<Group> doFindByUserId(Long userId);
	
	public List<Group> doFindByRoleId(Long roleId);
	
	public PageResult<Group> doFindByCriteriaAndUser(QueryCriteria queryCriteria);
	
	public PageResult<Group> doFindByCriteria(QueryCriteria criteria);
	
	public Group doFindTypeByNameEn(String nameEn);
	
	public PageResult<Group> doFindByCriteriaAndRole(QueryCriteria criteria);
	
	public int doDeleteById(Long id);

}
