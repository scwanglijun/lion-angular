
/*
* Copyright (c)  2015, NewTouch
* All rights reserved. 
*
* $id: IconServiceImpl.java 9552 Mar 4, 2015 11:47:35 AM MaoJiaWei$
*/
package com.newtouch.lion.service.system.impl;

import com.newtouch.lion.common.Assert;
import com.newtouch.lion.common.sql.HqlUtils;
import com.newtouch.lion.dao.system.IconDao;
import com.newtouch.lion.model.system.Icon;
import com.newtouch.lion.page.PageResult;
import com.newtouch.lion.query.QueryCriteria;
import com.newtouch.lion.service.AbstractService;
import com.newtouch.lion.service.system.IconService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * Title: 图标Icon的Service实现层
 * </p>
 * <p>
 * Description: 图标Icon的Service实现层
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
@Service("iconService")
public class IconServiceImpl extends AbstractService implements IconService {
	
	@Autowired
	private IconDao iconDao;
	
	/* (non-Javadoc)
	 * @see com.newtouch.lion.service.system.IconService#doFindById(long)
	 */
	@Override
	public Icon doFindById(long id) {
		return this.iconDao.findById(id);
	}

	/* (non-Javadoc)
	 * @see com.newtouch.lion.service.system.IconService#doDeleteById(java.lang.Long)
	 */
	@Override
	public int doDeleteById(Long id) {	 
		return iconDao.doDeleteById(id);
	}

	/* (non-Javadoc)
	 * @see com.newtouch.lion.service.system.IconService#doDelete(com.newtouch.lion.model.system.Icon)
	 */
	@Override
	public void doDelete(Icon icon) {
		this.iconDao.remove(icon);
	}

	/* (non-Javadoc)
	 * @see com.newtouch.lion.service.system.IconService#doFindByCriteria(com.newtouch.lion.query.QueryCriteria)
	 */
	@Override
	public PageResult<Icon> doFindByCriteria(QueryCriteria criteria) {
		String queryEntry = "from Icon";
		
		String[] whereBodies = {"iconType =:iconType"," iconClass like:iconClass " };
		
		String fromJoinSubClause = "";
		
		Map<String, Object> params = criteria.getQueryCondition();
		
		String orderField = criteria.getOrderField();
		
		String orderDirection = criteria.getOrderDirection();
		
		String hql = HqlUtils.generateHql(queryEntry, fromJoinSubClause, whereBodies, orderField, orderDirection, params);
		
		int pageSize = criteria.getPageSize();
		
		int startIndex = criteria.getStartIndex();
		
		PageResult<Icon> pageResult = this.iconDao.query(hql, HqlUtils.generateCountHql(hql, null), params, startIndex, pageSize);
		
		return pageResult;
	}

	/* (non-Javadoc)
	 * @see com.newtouch.lion.service.system.IconService#doCreate(com.newtouch.lion.model.system.Icon)
	 */
	@Override
	public void doCreate(Icon icon) {
		Assert.notNull(icon);
		iconDao.save(icon);
	}

	/* (non-Javadoc)
	 * @see com.newtouch.lion.service.system.IconService#doUpdate(com.newtouch.lion.model.system.Icon)
	 */
	@Override
	public Icon doUpdate(Icon icon) {
		Assert.notNull(icon);
		iconDao.update(icon);
		return icon;
	}

	/* (non-Javadoc)
	 * @see com.newtouch.lion.service.system.IconService#doFindTypeByIconClass(java.lang.String)
	 */
	@Override
	public Icon doFindTypeByIconClass(String iconClass) {
		Assert.notNull(iconClass);
		return iconDao.doFindTypeByIconClass(iconClass);
	}

	/* (non-Javadoc)
	 * @see com.newtouch.lion.service.system.IconService#doIsExistByIconClass(java.lang.String)
	 */
	@Override
	public boolean doIsExistByIconClass(String iconClass) {
		Assert.notNull(iconClass);
		Icon icon = this.doFindTypeByIconClass(iconClass);
		if (icon != null)
			return true;
		return false;
	}
	
	
	@Override
	public List<Icon> doFindByType(String iconType) {
		Assert.notNull(iconType);		 
		return iconDao.doFindByType(iconType);
	}
}

	
