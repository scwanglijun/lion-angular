
/*
* Copyright (c)  2015, NewTouch
* All rights reserved. 
*
* $id: ReminderBodyDao.java 9552 2015年3月31日 下午3:47:17 ZhongMengDie$
*/
package com.newtouch.lion.dao.system; 

import com.newtouch.lion.dao.BaseDao;
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
public interface ReminderBodyDao extends BaseDao<ReminderBody, Long> {
	
	public int doDeleteById(Long id);
	
	public ReminderBody doFindTypeByReminderBodyClass(String reminderBodyClass);
	
	public PageResult<ReminderBody> doFindByCriteria(QueryCriteria queryCriteria);

}

	