
/*
* Copyright (c)  2015, NewTouch
* All rights reserved. 
*
* $id: ReminderServiceImpl.java 9552 2015年3月31日 下午3:38:32 ZhongMengDie$
*/
package com.newtouch.lion.service.system.impl;

import com.newtouch.lion.common.Assert;
import com.newtouch.lion.dao.system.ReminderBodyDao;
import com.newtouch.lion.model.system.ReminderBody;
import com.newtouch.lion.page.PageResult;
import com.newtouch.lion.query.QueryCriteria;
import com.newtouch.lion.service.AbstractService;
import com.newtouch.lion.service.system.ReminderBodyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


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
@Service("reminderService")
public class ReminderServiceImpl extends AbstractService implements ReminderBodyService{

	@Autowired
	private ReminderBodyDao reminderDao;

	@Override
	public ReminderBody doFindById(Long id) {
		return this.reminderDao.findById(id);
	}

	//根据ID选择删除
	@Override
	public int doDeleteById(Long id) {
		return reminderDao.doDeleteById(id);
	}
	//删除

	@Override
	public void doDelete(ReminderBody reminderBody) {
		this.reminderDao.remove(reminderBody);
		
	}

	
	@Override
	public void doCreate(ReminderBody reminderBody) {
		Assert.notNull(reminderBody);
		reminderDao.save(reminderBody);
		
	}

	@Override
	public ReminderBody doUpdate(ReminderBody reminderBody) {
		Assert.notNull(reminderBody);
		reminderDao.update(reminderBody);
		return reminderBody;
	}

	@Override
	public ReminderBody doFindTypeByReminderBodyClass(String reminderBodyClass) {
		Assert.notNull(reminderBodyClass);
		return reminderDao.doFindTypeByReminderBodyClass(reminderBodyClass);

	}

	@Override
	public boolean doIsExistByReminderBodyClass(String title) {
		Assert.notNull(title);
		ReminderBody reminder = this.doFindTypeByReminderBodyClass(title);
		if (reminder != null)
			return true;
		return false;
	}

	@Override
	public PageResult<ReminderBody> doFindByCriteria(QueryCriteria queryCriteria) {
		return reminderDao.doFindByCriteria(queryCriteria);
	}

}

	