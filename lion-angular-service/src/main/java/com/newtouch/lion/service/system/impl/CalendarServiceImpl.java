
/*
* Copyright (c)  2015, NewTouch
* All rights reserved. 
*
* $id: CalendarServiceImpl.java 9552 Mar 14, 2015 11:00:56 PM MaoJiaWei$
*/
package com.newtouch.lion.service.system.impl;

import com.newtouch.lion.dao.system.CalendarDao;
import com.newtouch.lion.model.system.Calendar;
import com.newtouch.lion.model.system.User;
import com.newtouch.lion.service.system.CalendarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

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
 * @author MaoJiaWei
 * @version 1.0
 */
@Service("calendarService")
public class CalendarServiceImpl implements CalendarService {
	@Autowired
	private CalendarDao calendarDao;
	
	/* (non-Javadoc)
	 * @see com.newtouch.lion.service.system.CalendarSerivce#doFindById(java.lang.Long)
	 */
	@Override
	public Calendar doFindById(Long id) {
		return this.calendarDao.findById(id);
	}

	/* (non-Javadoc)
	 * @see com.newtouch.lion.service.system.CalendarSerivce#doDeleteById(java.lang.Long)
	 */
	@Override
	public int doDeleteById(Long id) {
		return calendarDao.doDeleteById(id);
	}

	/* (non-Javadoc)
	 * @see com.newtouch.lion.service.system.CalendarSerivce#doDeleteByObj(com.newtouch.lion.model.system.Calendar)
	 */
	@Override
	public void doDeleteByObj(Calendar calendar) {
		this.calendarDao.remove(calendar);
	}

	/* (non-Javadoc)
	 * @see com.newtouch.lion.service.system.CalendarSerivce#doUpdateObj(com.newtouch.lion.model.system.Calendar)
	 */
	@Override
	public Calendar doUpdateObj(Calendar calendar) {
		this.calendarDao.update(calendar);
		return calendar;
	}

	/* (non-Javadoc)
	 * @see com.newtouch.lion.service.system.CalendarSerivce#doCreateObj(com.newtouch.lion.model.system.Calendar)
	 */
	@Override
	public void doCreateObj(Calendar calendar) {
		this.calendarDao.save(calendar);
	}

	/* (non-Javadoc)
	 * @see com.newtouch.lion.service.system.CalendarSerivce#doFindCalendarByuser(com.newtouch.lion.model.system.User)
	 */
	@Override
	public List<Calendar> doFindCalendarByuser(User user) {
		return calendarDao.doFindCalendarByuser(user);
	}

	/* (non-Javadoc)
	 * @see com.newtouch.lion.service.system.CalendarService#doFindCalendarByuser(com.newtouch.lion.model.system.User, java.util.Date, java.util.Date)
	 */
	@Override
	public List<Calendar> doFindCalendarByuser(Long userId, Date start, Date end) {
		return calendarDao.doFindCalendarByuser(userId, start, end);
	}
	
}

	