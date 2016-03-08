/*
 * Copyright (c)  2014, NewTouch
 * All rights reserved. 
 *
 * $id: ParameterController.java 9552 2014-2-18 下午01:28:48 WangLijun$
 */
package com.newtouch.lion.admin.web.controller.system.loginlog;

import com.newtouch.lion.admin.web.model.system.loginlog.LoginLogGroupVo;
import com.newtouch.lion.common.file.FileUtil;
import com.newtouch.lion.data.DataTable;
import com.newtouch.lion.model.datagrid.DataGrid;
import com.newtouch.lion.model.system.LoginLogGroup;
import com.newtouch.lion.page.PageResult;
import com.newtouch.lion.query.QueryCriteria;
import com.newtouch.lion.service.datagrid.DataGridService;
import com.newtouch.lion.service.excel.ExcelExportService;
import com.newtouch.lion.service.system.LoginLogService;
import com.newtouch.lion.web.controller.AbstractController;
import com.newtouch.lion.web.model.QueryDt;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * Title: 系统参数控制类
 * </p>
 * <p>
 * Description: 系统参数控制类
 * </p>
 * <p>
 * Copyright: Copyright (c) 2014
 * </p>
 * <p>
 * Company: NewTouch
 * </p>
 * 
 * @author WangLijun
 * @version 1.0
 */
@Controller
@RequestMapping("/system/loginlog/")
public class LoginLogController extends AbstractController{
	
	private final Logger logger = LoggerFactory.getLogger(super.getClass());

	/** 参数首页 */
	private static final String INDEX_RETURN = "/system/loginlog/index";
	/** 默认排序字段名称 */
	private static final String DEFAULT_ORDER_FILED_NAME = "id";
	
	/**登陆日志*/
	@Autowired
	private LoginLogService loginLogService;
	
	/**DataGrid表格*/
	@Autowired
	private DataGridService dataGridService;
	/**Excel通用导出*/
	@Autowired
	private ExcelExportService excelExportService;
	@RequestMapping(value = "index")
	public String index(Model model) {
		return INDEX_RETURN;
	}

	@RequestMapping(value = "list")
	@ResponseBody
	public DataTable<LoginLogGroup> list(QueryDt query,@ModelAttribute("loginLogGroupVo") LoginLogGroupVo loginLogGroupVo) {
		QueryCriteria queryCriteria = new QueryCriteria();

		// 设置分页 启始页
		queryCriteria.setStartIndex(query.getPage());
		// 每页大小
		queryCriteria.setPageSize(query.getRows());
		// 设置排序字段及排序方向
		if (StringUtils.isNotEmpty(query.getSort()) && StringUtils.isNotEmpty(query.getOrder())) {
			queryCriteria.setOrderField(query.getSort());
			queryCriteria.setOrderDirection(query.getOrder());
		} else {
			queryCriteria.setOrderField(DEFAULT_ORDER_FILED_NAME);
		}
		// 查询条件 参数类型
		if (StringUtils.isNotEmpty(loginLogGroupVo.getUserName())) {
			queryCriteria.addQueryCondition("username", loginLogGroupVo.getUserName());
		}

		PageResult<LoginLogGroup> pageResult = loginLogService.doFindByCriteria(queryCriteria);
		return pageResult.getDataTable(query.getRequestId());
	}
	/****
	 * 
	 * @param tableId
	 * @param roleVo
	 * @param modelAndView
	 * @return
	 */
	@RequestMapping(value = "export")
	@ResponseBody
	public ModelAndView exportExcel(@RequestParam(required=false) String tableId,@RequestParam(required = false) String sort,@RequestParam(required = false) String order,@ModelAttribute("loginLogGroupVo") LoginLogGroupVo loginLogGroupVo,ModelAndView modelAndView){
		
		DataGrid dataGrid=dataGridService.doFindByTableIdAndSort(tableId);
		QueryCriteria queryCriteria=new QueryCriteria();
		queryCriteria.setPageSize(10000);
		// 设置排序字段及排序方向
		if (StringUtils.isNotEmpty(sort) && StringUtils.isNotEmpty(order)) {
			queryCriteria.setOrderField(sort);
			queryCriteria.setOrderDirection(order);
		} else {
			queryCriteria.setOrderField(DEFAULT_ORDER_FILED_NAME);
			queryCriteria.setOrderDirection("ASC");
		}
		// 查询条件 参数类型
		if (StringUtils.isNotEmpty(loginLogGroupVo.getUserName())) {
			queryCriteria.addQueryCondition("username", loginLogGroupVo.getUserName());
		}

		PageResult<LoginLogGroup> result=loginLogService.doFindByCriteria(queryCriteria);
		
		Map<String, Map<Object, Object>> fieldCodeTypes = new HashMap<String, Map<Object, Object>>();

		Map<String, String> dataFormats = new HashMap<String, String>();		
		//创建.xls的文件名
		String fileName=this.createFileName(FileUtil.EXCEL_EXTENSION);
		
		modelAndView.addObject("title", dataGrid.getTitle());
		
		Long startTime=System.currentTimeMillis();
		
		fileName=excelExportService.export(dataGrid, result.getContent(), fileName, fieldCodeTypes, dataFormats);
		
		logger.info("fileName:{}",fileName);
		
		Long costTime=System.currentTimeMillis()-startTime;
		
		modelAndView.addObject(FILENAME,fileName);
		
		logger.info("export Excel {} cost:{} time,fileName:{}",dataGrid.getTitle(),costTime,fileName);
		logger.info("out Excel导出");
		return this.getExcelView(modelAndView);
	}
	 
}
