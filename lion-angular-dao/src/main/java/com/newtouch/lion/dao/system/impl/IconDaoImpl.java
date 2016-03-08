
/*
* Copyright (c)  2015, NewTouch
* All rights reserved. 
*
* $id: IconDaoImpl.java 9552 Mar 4, 2015 11:30:43 AM MaoJiaWei$
*/
package com.newtouch.lion.dao.system.impl; 

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.newtouch.lion.common.Assert;
import com.newtouch.lion.dao.impl.BaseDaoImpl;
import com.newtouch.lion.dao.system.IconDao;
import com.newtouch.lion.model.system.Icon;

/**
 * <p>
 * Title: 图标Icon的Dao实现层
 * </p>
 * <p>
 * Description: 图标Icon的Dao实现层
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
@Repository("iconDao")
public class IconDaoImpl extends BaseDaoImpl<Icon,Long> implements IconDao{

	/**
	 * 序列化
	 */
	private static final long serialVersionUID = -1494476134711376183L;

	@Override
	public int doDeleteById(Long id) {
		String hql="delete from Icon i where i.id=:id";
		Map<String,Object> params=new  HashMap<String, Object>();
		params.put("id",id);
		return this.updateHQL(hql, params);
	}

	@Override
	public Icon doFindTypeByIconClass(String iconClass) {
		String hql = "from Icon where iconClass=:iconClass";
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("iconClass", iconClass);
		List<Icon> icons = this.query(hql, params);
		if (icons != null && icons.size() > 0) {
			return icons.get(0);
		}
		return null;
	}
	
	public List<Icon> doFindByType(String iconType) {
		Assert.notNull(iconType);		 
		String queryHQL = "from Icon where iconType=:iconType";
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("iconType", iconType);
		return this.query(queryHQL, params);
	}

}

	