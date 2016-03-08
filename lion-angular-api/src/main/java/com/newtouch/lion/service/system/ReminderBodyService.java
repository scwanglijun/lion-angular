
/*
* Copyright (c)  2015, NewTouch
* All rights reserved. 
*
* $id: ReminderBodyService.java 9552 2015年3月31日 下午2:03:30 ZhongMengDie$
*/
package com.newtouch.lion.service.system; 

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
public interface ReminderBodyService {
	/***
	 * 根据ID查询ReminderBody对象，并返回ReminderBody对象
	 * @param id 参数ID
	 * @return reminderBody
	 */
	public ReminderBody doFindById(Long id);
	/**
	 * 根据id删除ReminderBody对象
	 * */
	public int doDeleteById(Long id);
	
	/**删除参数对象
	 * @param ReminderBody
	 * */
	public void  doDelete(ReminderBody reminderBody);
	/***
	 * 根据不同查询条件查询系统参数，并返回分页对象
	 * @param queryCriteria
	 * @return PageResult<ReminderBody>
	 */
	public PageResult<ReminderBody>  doFindByCriteria(QueryCriteria queryCriteria);
	
	/***
	 * 保存ReminderBody对象
	 * @param ReminderBody
	 */
	public void doCreate(ReminderBody reminderBody);
	
	/***
	 * 更新参数对象
	 * @param icon
	 * @return Icon
	 */
	public ReminderBody doUpdate(ReminderBody reminderBody);
	/***
	 * 根据消息类型获取消息
	 * @param reminderClass
	 */
	public ReminderBody doFindTypeByReminderBodyClass(String reminderBodyClass);
	/**
	 * 判断消息类型是否已存在
	 */
	public boolean doIsExistByReminderBodyClass(String reminderBodyClass); 
}

	