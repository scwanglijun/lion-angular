
/*
* Copyright (c)  2015, NewTouch
* All rights reserved. 
*
* $id: ReminderBodyImpl.java 9552 2015年3月31日 下午3:52:24 ZhongMengDie$
*/
package com.newtouch.lion.dao.system.impl; 


import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.newtouch.lion.common.Assert;
import com.newtouch.lion.common.sql.HqlUtils;
import com.newtouch.lion.dao.impl.BaseDaoImpl;
import com.newtouch.lion.dao.system.ReminderBodyDao;
import com.newtouch.lion.model.system.ReminderBody;
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
 * @author ZhongMengDie
 * @version 1.0
 */
@Repository("reminderDao")
public class ReminderBodyDaoImpl extends BaseDaoImpl<ReminderBody, Long> implements
		ReminderBodyDao {

	/**
	 * 序列化
	 */
	private static final long serialVersionUID = -1494476134711376183L;
	
	@Override
	public int doDeleteById(Long id) {
		String hql="delete from ReminderBody i where i.id=:id";
		Map<String,Object> params=new  HashMap<String, Object>();
		params.put("id",id);
		return this.updateHQL(hql, params);
	}
	
	@Override
	public ReminderBody doFindTypeByReminderBodyClass(String reminderBodyClass) {
		Assert.notNull(reminderBodyClass);
		String hql = "from ReminderBody where title=:reminderBodyClass";
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("title", reminderBodyClass);
		java.util.List<ReminderBody> remidner = this.query(hql, params);
		if (remidner != null && remidner.size() > 0) {
			return remidner.get(0);
		}
		return null;
	}
	
	@Override
	public PageResult<ReminderBody> doFindByCriteria(QueryCriteria queryCriteria) {
		String queryEntry = "from ReminderBody";
		
		String[] whereBodies = {"title like:title"," reminderType like:reminderType " };
	
		String fromJoinSubClause = "";
		System.out.println("");
		Map<String, Object> params = queryCriteria.getQueryCondition();
		
		String orderField = queryCriteria.getOrderField();

		String orderDirection = queryCriteria.getOrderDirection();
	
		String hql = HqlUtils.generateHql(queryEntry, fromJoinSubClause, whereBodies, orderField, orderDirection, params);

		int pageSize = queryCriteria.getPageSize();
		
		int startIndex = queryCriteria.getStartIndex();
		
		PageResult<ReminderBody> pageResult = this.query(hql, HqlUtils.generateCountHql(hql, null), params, startIndex, pageSize);
		
		return pageResult;
	}
}

	