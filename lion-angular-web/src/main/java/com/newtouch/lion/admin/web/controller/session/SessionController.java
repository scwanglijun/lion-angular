/*
* Copyright (c)  2015, Newtouch
* All rights reserved. 
*
* $id: SessionController.java 9552 2015年2月26日 下午9:04:15 WangLijun$
*/
package com.newtouch.lion.admin.web.controller.session;

import com.newtouch.lion.admin.web.model.session.SessionVo;
import com.newtouch.lion.common.date.DateUtil;
import com.newtouch.lion.common.file.FileUtil;
import com.newtouch.lion.data.DataTable;
import com.newtouch.lion.model.datagrid.DataGrid;
import com.newtouch.lion.model.session.SessionModel;
import com.newtouch.lion.service.datagrid.DataGridService;
import com.newtouch.lion.service.excel.ExcelExportService;
import com.newtouch.lion.service.session.SessionService;
import com.newtouch.lion.service.system.UserService;
import com.newtouch.lion.web.constant.ConstantMessage;
import com.newtouch.lion.web.controller.AbstractController;
import com.newtouch.lion.web.model.QueryDt;
import com.newtouch.lion.web.servlet.view.support.BindMessage;
import com.newtouch.lion.web.servlet.view.support.BindResult;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * Title:Session管理
 * </p>
 * <p>
 * Description: Session管理（读取会话列表、强制退出）
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
@Controller
@RequestMapping("/sessions")
public class SessionController extends AbstractController {
	/**首页*/
	private static final String  INDEX_RETURN="/sessions/index";
	
	/**用户会话服务*/
	@Autowired
	private SessionService sessionService;
	/**用户服务*/
	@Autowired
	private UserService userService;
	/**DataGrid表格*/
	@Autowired
	private DataGridService dataGridService;
	/**Excel通用导出*/
	@Autowired
	private ExcelExportService excelExportService;
	
	/**返回到首页**/
	@RequestMapping("index")
	public String  index(){
		return INDEX_RETURN;
	}
	
	/***
	 * 用户在线列表
	 * @param query
	 * @return
	 */
	@RequestMapping("actives")
	@ResponseBody
	public DataTable<SessionModel> list(QueryDt query) {
 		
		List<SessionModel> list=sessionService.getActiveSessions();
		DataTable<SessionModel> dataTable=new DataTable<SessionModel>((long) list.size(),list,query.getRequestId()); 
		return dataTable;
	}
	
	/**
	 * 导出excel
	 * @param tableId
	 * @param sort
	 * @param order
	 * @param policy
	 * @param modelAndView
	 * @return
	 */
	@RequestMapping(value = "export")
	@ResponseBody
	public ModelAndView exportExcel(@RequestParam(required=false) String tableId,@RequestParam(required = false) String sort,@RequestParam(required = false) String order,@ModelAttribute("session") SessionVo sessionVo,ModelAndView modelAndView){
		
		DataGrid dataGrid=dataGridService.doFindByTableIdAndSort(tableId);
		List<SessionModel> result=sessionService.getActiveSessions();
		
		Map<String, Map<Object, Object>> fieldCodeTypes = new HashMap<String, Map<Object, Object>>();

		Map<String, String> dataFormats = new HashMap<String, String>();		
		dataFormats.put("birthday", DateUtil.FORMAT_DATE_YYYY_MM_DD);
		//创建.xls的文件名
		String fileName=this.createFileName(FileUtil.EXCEL_EXTENSION);
		
		modelAndView.addObject("title", dataGrid.getTitle());
		
		Long startTime=System.currentTimeMillis();
		
		fileName=excelExportService.export(dataGrid, result, fileName, fieldCodeTypes, dataFormats);
		
		logger.info("fileName:{}",fileName);
		
		Long costTime=System.currentTimeMillis()-startTime;
		
		modelAndView.addObject(FILENAME,fileName);
		
		logger.info("export Excel {} cost:{} time,fileName:{}",dataGrid.getTitle(),costTime,fileName);
		logger.info("out Excel导出");
		return this.getExcelView(modelAndView);
	}
	
	/***
	 * 强制用户退出
	 * @param sessionId 用户会ID
	 * @param modelAndView
	 * @return
	 */
	@RequestMapping(value = "forcelogout",method=RequestMethod.POST)
	@ResponseBody
	public ModelAndView forceLogout(SessionVo sessionVo,Errors errors,ModelAndView modelAndView) {
		Map<String, String> params = new HashMap<String, String>();
		
		Subject currentUser=SecurityUtils.getSubject();
		//获取当前会话的
	    String currentSessionId=(String) currentUser.getSession(false).getId();
	    //判断是否是当前用户
	    if(currentSessionId.equals(sessionVo.getId())){
	    	errors.reject("","当前登录用户不能强制退出");
	    }
	    
		if (errors.hasErrors()) {
			modelAndView.addObject(BindMessage.ERRORS_MODEL_KEY, errors);
			return  this.getJsonView(modelAndView);
		}

	    SessionModel sessionModel=sessionService.getSession(sessionVo.getId());
		//判断是否是超级用户
	    if(userService.getSuperUsername().equals(sessionModel.getUsername())){
	    	errors.reject("","超级管理用户不能强制退出");
	    }
		if (errors.hasErrors()) {
			modelAndView.addObject(BindMessage.ERRORS_MODEL_KEY, errors);
			return  this.getJsonView(modelAndView);
		}
		//调用强制退出
		boolean flag= sessionService.forceLogout(sessionVo.getId());
		if(flag){
			params.put(BindResult.SUCCESS, ConstantMessage.ADD_SUCCESS_MESSAGE_CODE);
			modelAndView.addObject(BindMessage.SUCCESS, params);
		}else{
			errors.reject("","强制退出用户未成功");			
			modelAndView.addObject(BindMessage.ERRORS_MODEL_KEY, errors);			
		}
		return this.getJsonView(modelAndView);
	}
}	

	
