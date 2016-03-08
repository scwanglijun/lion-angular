
/*
* Copyright (c)  2015, NewTouch
* All rights reserved. 
*
* $id: CalendarDaoImpl.java 9552 Mar 14, 2015 10:48:32 PM MaoJiaWei$
*/
package com.newtouch.lion.dao.system.impl; 

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.newtouch.lion.dao.impl.BaseDaoImpl;
import com.newtouch.lion.dao.system.CalendarDao;
import com.newtouch.lion.model.system.Calendar;
import com.newtouch.lion.model.system.User;

/**
 * <p>
 * Title: 日历Dao实现类
 * </p>
 * <p>
 * Description: 日历Dao实现类
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
@Repository("calendarDao")
public class CalendarDaoImpl extends BaseDaoImpl<Calendar, Long> implements CalendarDao {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6915395255709654538L;

	@Override
	public int doDeleteById(Long id) {
		String hql="delete from Calendar c where c.id=:id";
		Map<String,Object> params=new  HashMap<String, Object>();
		params.put("id",id);
		return this.updateHQL(hql, params);
	}
	
	@Override
	public List<Calendar> doFindCalendarByuser(User user){
		String hql = "from Calendar where user.id=:id";
		Map<String,Object>  params=new HashMap<String, Object>();
		params.put("id",user.getId());
		return this.query(hql, params);
	}
	
	@Override
	public List<Calendar> doFindCalendarByuser(Long userId, Date start, Date end) {
		String hql = "from Calendar where userId=:userId and startdate between :start and :end";
		Map<String,Object>  params=new HashMap<String, Object>();
		params.put("userId",userId);
		params.put("start", start);
		params.put("end", end);
		return this.query(hql, params);
	}
}

	