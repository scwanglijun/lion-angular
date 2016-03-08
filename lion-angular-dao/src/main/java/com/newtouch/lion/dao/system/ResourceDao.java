/*
 * Copyright (c)  2012, Newtouch
 * All rights reserved. 
 *
 * $id: TsResourceDao.java 9552 2012-12-31 下午6:54:19 WangLijun$
 */
package com.newtouch.lion.dao.system;

import java.util.List;

import com.newtouch.lion.dao.BaseDao;
import com.newtouch.lion.model.system.Resource;
import com.newtouch.lion.tree.Tree;

/**
 * <p>
 * Title: 资源管理数据存储类
 * </p>
 * <p>
 * Description: 资源管理数据存储类
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
public interface ResourceDao extends BaseDao<Resource, Long> {
	
	public List<Resource> doFindAll();
	
	public List<Resource> doFindByParentId(Long parentResourceId);
	
	public List<Tree> doFindByResourceId(Long resourceId);
	
	public List<Resource> doFindFirstLevel();
	
	public List<Resource> doFindByType(String[] resTypes);
	
	public List<Resource> doFindByUserIdAndType(Long userId, String[] resourceType);
	
	public List<Resource> doFindByUserIdAndType(Long userId,
												Long parentResourceId, String[] resourceType);
	
	public List<Resource> doFindByUserIdAndType(Long userId,
												Long parentResourceId, String[] parentResourceType,
												String[] childrenResourceType);
	
	public List<Resource> doFindByUserIdAndType(Long userId,
												String[] parentResourceType, String[] resourceType);
	
	public List<Resource> doFindByUserId(Long userId);

}
