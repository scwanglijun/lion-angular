
/*
* Copyright (c)  2015, NewTouch
* All rights reserved. 
*
* $id: CalendarDao.java 9552 Mar 14, 2015 10:46:59 PM MaoJiaWei$
*/
package com.newtouch.lion.dao.system; 

import java.util.Date;
import java.util.List;

import com.newtouch.lion.dao.BaseDao;
import com.newtouch.lion.model.system.Calendar;
import com.newtouch.lion.model.system.User;

/**
 * <p>
 * Title: 日历Dao类
 * </p>
 * <p>
 * Description: 日历Dao类
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
public interface CalendarDao extends BaseDao<Calendar, Long> {
	
	public int doDeleteById(Long id);
	
	public List<Calendar> doFindCalendarByuser(User user);
	
	public List<Calendar> doFindCalendarByuser(Long userId, Date start, Date end);

}

	