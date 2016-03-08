
/*
* Copyright (c)  2015, NewTouch
* All rights reserved. 
*
* $id: CalendarDTO.java 9552 Mar 16, 2015 4:37:33 PM MaoJiaWei$
*/
package com.newtouch.lion.admin.web.controller.system.user;

import com.newtouch.lion.model.system.Calendar;

import java.util.ArrayList;
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
public class CalendarDTO {
	private int id;
	private String start;
	private String end;
	private String title;
	private boolean allday;
	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}
	/**
	 * @return the start
	 */
	public String getStart() {
		return start;
	}
	/**
	 * @param start the start to set
	 */
	public void setStart(String start) {
		this.start = start;
	}
	/**
	 * @return the end
	 */
	public String getEnd() {
		return end;
	}
	/**
	 * @param end the end to set
	 */
	public void setEnd(String end) {
		this.end = end;
	}
	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}
	/**
	 * @param title the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}
	/**
	 * @return the allday
	 */
	public boolean isAllday() {
		return allday;
	}
	/**
	 * @param allday the allday to set
	 */
	public void setAllday(boolean allday) {
		this.allday = allday;
	}
	
	public static List<CalendarDTO> tranProperties(List<Calendar> list){
		List<CalendarDTO> DTOlist = new ArrayList<CalendarDTO>();
		for(Calendar calendar:list){
			CalendarDTO calendarDTO = new CalendarDTO();
			calendarDTO.setId(calendar.getId().intValue());
			calendarDTO.setTitle(calendar.getEvent());
			calendarDTO.setStart(calendar.getStartdate().toString().split(" ")[0]+" "+calendar.getStarttime().toString().split(" ")[1]);
			calendarDTO.setEnd(calendar.getEnddate().toString().split(" ")[0]+" "+calendar.getEndtime().toString().split(" ")[1]);
			calendarDTO.setAllday(calendar.getAllday());
			DTOlist.add(calendarDTO);
		}
		return DTOlist;
	}
}

	