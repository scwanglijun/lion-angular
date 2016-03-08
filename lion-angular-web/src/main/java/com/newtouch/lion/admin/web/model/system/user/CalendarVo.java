
/*
* Copyright (c)  2015, NewTouch
* All rights reserved. 
*
* $id: CalendarVo.java 9552 Mar 15, 2015 12:20:28 PM MaoJiaWei$
*/
package com.newtouch.lion.admin.web.model.system.user;

import java.io.Serializable;

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
public class CalendarVo implements Serializable  {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -107227670096962820L;
	/** 日历id */
	private Long id;
	/**事件*/
	private String event;
	/**开始日期*/
	private String startDate;
	/**开始时间*/
	private String startTime;
	/**结束日期*/
	private String endDate;
	/**结束时间*/
	private String endTime;
	/**是否为整天*/
	private Boolean isallday=Boolean.FALSE;
	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
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
	 * @return the startDate
	 */
	public String getStartDate() {
		return startDate;
	}
	/**
	 * @param startDate the startDate to set
	 */
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	/**
	 * @return the startTime
	 */
	public String getStartTime() {
		return startTime;
	}
	/**
	 * @param startTime the startTime to set
	 */
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	/**
	 * @return the endDate
	 */
	public String getEndDate() {
		return endDate;
	}
	/**
	 * @param endDate the endDate to set
	 */
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	/**
	 * @return the endTime
	 */
	public String getEndTime() {
		return endTime;
	}
	/**
	 * @param endTime the endTime to set
	 */
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	/**
	 * @return the isallday
	 */
	public Boolean getIsallday() {
		return isallday;
	}
	/**
	 * @param isallday the isallday to set
	 */
	public void setIsallday(Boolean isallday) {
		this.isallday = isallday;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "CalendarVo [id=" + id + ", event=" + event + ", startDate="
				+ startDate + ", startTime=" + startTime + ", endDate="
				+ endDate + ", endTime=" + endTime + ", isallday=" + isallday
				+ "]";
	}
	
	
}

	