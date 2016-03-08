
/*
* Copyright (c)  2015, NewTouch
* All rights reserved. 
*
* $id: Calendar.java 9552 Mar 14, 2015 9:13:09 PM MaoJiaWei$
*/
package com.newtouch.lion.model.system; 

import java.util.Date;

import com.newtouch.lion.model.BaseEntity;

/**
 * <p>
 * Title: 日历 model类
 * </p>
 * <p>
 * Description: 日历 model类
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
public class Calendar extends BaseEntity<Long>{
	/**
	 * 
	 */
	private static final long serialVersionUID = 9095497281150691398L;
	/** 日历id */
	private Long id;
	/**事件*/
	private String event;
	/**开始日期*/
	private Date startdate;
	/**开始时间*/
	private Date starttime;
	/**结束日期*/
	private Date enddate;
	/**结束时间*/
	private Date endtime;
	/**是否为整天*/
	private Boolean allday;
	/**用户*/
	private Long userId;
	/***/
	@Override
	public Long getId() {
		// TODO Auto-generated method stub
		return id;
	}
	/**
	 * @return the event
	 */
	public String getEvent() {
		return event;
	}
	/**
	 * @param event the event to set
	 */
	public void setEvent(String event) {
		this.event = event;
	}
	/**
	 * @return the starttime
	 */
	public Date getStarttime() {
		return starttime;
	}
	/**
	 * @param starttime the starttime to set
	 */
	public void setStarttime(Date starttime) {
		this.starttime = starttime;
	}
	/**
	 * @return the endtime
	 */
	public Date getEndtime() {
		return endtime;
	}
	/**
	 * @param endtime the endtime to set
	 */
	public void setEndtime(Date endtime) {
		this.endtime = endtime;
	}
	/**
	 * @return the allday
	 */
	public Boolean getAllday() {
		return allday;
	}
	/**
	 * @param allday the allday to set
	 */
	public void setAllday(Boolean allday) {
		this.allday = allday;
	}
	/**
	 * @return the userId
	 */
	public Long getUserId() {
		return userId;
	}
	/**
	 * @param userId the userId to set
	 */
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}
	/**
	 * @return the startdate
	 */
	public Date getStartdate() {
		return startdate;
	}
	/**
	 * @param startdate the startdate to set
	 */
	public void setStartdate(Date startdate) {
		this.startdate = startdate;
	}
	/**
	 * @return the enddate
	 */
	public Date getEnddate() {
		return enddate;
	}
	/**
	 * @param enddate the enddate to set
	 */
	public void setEnddate(Date enddate) {
		this.enddate = enddate;
	}
	/**
	 * @return the serialversionuid
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
}