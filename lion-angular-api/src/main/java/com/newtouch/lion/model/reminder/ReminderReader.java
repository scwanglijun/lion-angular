/*
* Copyright (c)  2015, Newtouch
* All rights reserved. 
*
* $id: ReminderReader.java 9552 2015年3月17日 下午5:48:23 WangLijun$
*/
package com.newtouch.lion.model.reminder; 

import com.newtouch.lion.model.VersionEntity;

/**
 * <p>
 * Title: 消息体阅读
 * </p>
 * <p>
 * Description:  消息体阅读
 * </p>
 * <p>
 * Copyright: Copyright (c) 2015
 * </p>
 * <p>
 * Company: Newtouch
 * </p>
 * 
 * @author WangLijun
 * @version 1.0
 */
public class ReminderReader extends VersionEntity<Long>{

	/**
	 *序列化 
	 */
	private static final long serialVersionUID = -8933784660614783720L;
	/**阅读者ID*/
	private Long id;
	/**用户ID*/
	private Long userId;
	/**是否阅读*/
	private boolean isRead;
	/**消息体*/
	private ReminderBody reminderBody;
	/**
	 * @return  阅读者ID
	 */
	public Long getId() {
		return id;
	}
	/**
	 * @param id  阅读者ID
	 */
	public void setId(Long id) {
		this.id = id;
	}
	/**
	 * @return  用户ID
	 */
	public Long getUserId() {
		return userId;
	}
	/**
	 * @param userId 用户ID
	 */
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	/**
	 * @return  是否阅读
	 */
	public boolean isRead() {
		return isRead;
	}
	/**
	 * @param isRead 是否阅读
	 */
	public void setRead(boolean isRead) {
		this.isRead = isRead;
	}
	/**
	 * @return  消息体
	 */
	public ReminderBody getReminderBody() {
		return reminderBody;
	}
	/**
	 * @param reminderBody  消息体
	 */
	public void setReminderBody(ReminderBody reminderBody) {
		this.reminderBody = reminderBody;
	}

}

	