
/*
* Copyright (c)  2015, NewTouch
* All rights reserved. 
*
* $id: CalendarController.java 9552 Mar 15, 2015 12:05:44 PM MaoJiaWei$
*/
package com.newtouch.lion.admin.web.controller.system.user;

import com.newtouch.lion.admin.web.model.system.user.CalendarVo;
import com.newtouch.lion.common.user.UserInfo;
import com.newtouch.lion.json.JSONParser;
import com.newtouch.lion.model.system.Calendar;
import com.newtouch.lion.service.system.CalendarService;
import com.newtouch.lion.service.system.UserService;
import com.newtouch.lion.web.controller.AbstractController;
import com.newtouch.lion.web.servlet.view.support.BindMessage;
import com.newtouch.lion.web.servlet.view.support.BindResult;
import com.newtouch.lion.web.shiro.session.LoginSecurityUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
@Controller(value = "sysCalendar")
@RequestMapping("/system/account/")
public class CalendarController extends AbstractController {
	
	@Autowired
	private UserService userService;
	@Autowired
	private CalendarService calendarService;
	
	//添加待办事项
	@RequestMapping(value = "add")
	@ResponseBody
	public ModelAndView add(@Valid @ModelAttribute("calendarVo") CalendarVo calendarVo,Errors errors, ModelAndView modelAndView) throws ParseException {
		UserInfo userInfo =LoginSecurityUtil.getUser();
		Calendar calendar = new Calendar();
		calendar.setEvent(calendarVo.getEvent());
		SimpleDateFormat dateformatter = new SimpleDateFormat ("yyyy-MM-dd");
		SimpleDateFormat timeformatter = new SimpleDateFormat ("HH:mm");
		Date startdate = dateformatter.parse(calendarVo.getStartDate());
		calendar.setStartdate(startdate);
		Date enddate = dateformatter.parse(calendarVo.getEndDate());
		calendar.setEnddate(enddate);
		if(!calendarVo.getIsallday()){
			Date starttime = timeformatter.parse(calendarVo.getStartTime());
			calendar.setStarttime(starttime);
			Date endtime = timeformatter.parse(calendarVo.getEndTime());
			calendar.setEndtime(endtime);
		}else{
			Date starttime = timeformatter.parse("00:00");
			calendar.setStarttime(starttime);
			Date endtime = timeformatter.parse("23:59");
			calendar.setEndtime(endtime);
		}
		calendar.setAllday(calendarVo.getIsallday());
		calendar.setUserId(userInfo.getId());
		calendarService.doCreateObj(calendar);
		Map<String, String> params = new HashMap<String, String>();
		params.put(BindResult.SUCCESS, "sys.codeType.add.success");
		modelAndView.addObject(BindMessage.SUCCESS, params);
		return this.getJsonView(modelAndView);
	}
	
	//获取所有的数据
	@RequestMapping(value = "list")
	@ResponseBody
	public ModelAndView list(Date start,Date end) throws IOException, ParseException {
		UserInfo userInfo =LoginSecurityUtil.getUser();
		List<Calendar> list = this.calendarService.doFindCalendarByuser(userInfo.getId(),start,end);
		List<CalendarDTO> DTOlist = CalendarDTO.tranProperties(list);
		String str=JSONParser.toJSONString(DTOlist);
		ModelAndView modelAndView=new ModelAndView();
		return this.getStrJsonView(str, modelAndView);
    }
	
	//添加待办事项
	@RequestMapping(value = "edit",method=RequestMethod.POST)
	@ResponseBody
	public ModelAndView edit(@Valid @ModelAttribute("calendarVo") CalendarVo calendarVo,Errors errors, ModelAndView modelAndView) throws ParseException {
		Calendar calendar = calendarService.doFindById(calendarVo.getId());
		calendar.setEvent(calendarVo.getEvent());
		SimpleDateFormat dateformatter = new SimpleDateFormat ("yyyy-MM-dd");
		SimpleDateFormat timeformatter = new SimpleDateFormat ("HH:mm");
		Date startdate = dateformatter.parse(calendarVo.getStartDate());
		calendar.setStartdate(startdate);
		Date enddate = dateformatter.parse(calendarVo.getEndDate());
		calendar.setEnddate(enddate);
		if(!calendarVo.getIsallday()){
			Date starttime = timeformatter.parse(calendarVo.getStartTime());
			calendar.setStarttime(starttime);
			Date endtime = timeformatter.parse(calendarVo.getEndTime());
			calendar.setEndtime(endtime);
		}else{
			Date starttime = timeformatter.parse("00:00");
			calendar.setStarttime(starttime);
			Date endtime = timeformatter.parse("23:59");
			calendar.setEndtime(endtime);
		}
		calendar.setAllday(calendarVo.getIsallday());
		calendarService.doUpdateObj(calendar);
		Map<String, String> params = new HashMap<String, String>();
		params.put(BindResult.SUCCESS, "sys.codeType.edit.success");
		modelAndView.addObject(BindMessage.SUCCESS, params);
		return this.getJsonView(modelAndView);
	}
	
	//添加待办事项
	@RequestMapping(value = "delete")
	@ResponseBody
	public ModelAndView delete(@Valid @ModelAttribute("calendarVo") CalendarVo calendarVo,Errors errors, ModelAndView modelAndView){
		Calendar calendar = calendarService.doFindById(calendarVo.getId());
		calendarService.doDeleteByObj(calendar);
		Map<String, String> params = new HashMap<String, String>();
		params.put(BindResult.SUCCESS, "sys.codeType.delete.success");
		modelAndView.addObject(BindMessage.SUCCESS, params);
		return this.getJsonView(modelAndView);
	}
}

	