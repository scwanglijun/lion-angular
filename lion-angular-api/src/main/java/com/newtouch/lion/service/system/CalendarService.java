
/*
* Copyright (c)  2015, NewTouch
* All rights reserved. 
*
* $id: CalendarService.java 9552 Mar 15, 2015 1:04:12 PM MaoJiaWei$
*/
package com.newtouch.lion.service.system; 

import java.util.Date;
import java.util.List;

import com.newtouch.lion.model.system.Calendar;
import com.newtouch.lion.model.system.User;

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
public interface CalendarService {
	/** 根据ID，查询对象 */
	public Calendar doFindById(Long id);

	/**
	 * 根据Id删除Calendar对象
	 * 
	 * @param id
	 * @return
	 * */
	public int doDeleteById(Long id);

	/**
	 * 删除Calendar
	 * 
	 * @param calendar
	 * 
	 * */
	public void doDeleteByObj(Calendar calendar);

	/** 更新 */
	public Calendar doUpdateObj(Calendar calendar);

	/***
	 * 保存Calendar对象
	 * @author maojiawei
	 * @param calendar
	 */
	public void doCreateObj(Calendar calendar);
	
	/**
	 * 根据用户搜索日历对象
	 * @author maojiawei
	 * @param user
	 * */
	public List<Calendar> doFindCalendarByuser(User user);
	
	/**
	 * 根据用户和时间段搜索日历对象
	 * @author maojiawei
	 * @param userId
	 * @param start
	 * @param end
	 * */
	public List<Calendar> doFindCalendarByuser(Long userId, Date start, Date end);
}

	