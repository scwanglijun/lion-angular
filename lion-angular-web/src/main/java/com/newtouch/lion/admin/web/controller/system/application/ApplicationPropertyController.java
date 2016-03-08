/*
 * Copyright (c)  2014, NewTouch
 * All rights reserved. 
 *
 * $id: CacheController.java 9552 2014-4-9 上午01:04:34 WangLijun$
 */
package com.newtouch.lion.admin.web.controller.system.application;

import com.newtouch.lion.admin.web.model.system.application.ApplicationPropertyVo;
import com.newtouch.lion.common.date.DateUtil;
import com.newtouch.lion.common.file.FileUtil;
import com.newtouch.lion.data.DataTable;
import com.newtouch.lion.model.application.ApplicationProperty;
import com.newtouch.lion.model.datagrid.DataGrid;
import com.newtouch.lion.page.PageResult;
import com.newtouch.lion.query.QueryCriteria;
import com.newtouch.lion.service.application.ApplicationPropertyService;
import com.newtouch.lion.service.datagrid.DataGridService;
import com.newtouch.lion.service.excel.ExcelExportService;
import com.newtouch.lion.web.controller.AbstractController;
import com.newtouch.lion.web.model.QueryDt;
import com.newtouch.lion.web.servlet.view.support.BindMessage;
import com.newtouch.lion.web.servlet.view.support.BindResult;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * Title: 项目属性配置类
 * </p>
 * <p>
 * Description: 项目属性配置类
 * </p>
 * <p>
 * Copyright: Copyright (c) 2014
 * </p>
 * <p>
 * Company: NewTouch
 * </p>
 * 
 * @author MaoJiaWei
 * @version 1.0
 */
@Controller(value = "sysApplicationPropertyContorller")
@RequestMapping("/system/applicationproperty/")
public class ApplicationPropertyController extends AbstractController {
	
	/** 默认排序字段 */
	private static final String DEFAULT_ORDER_FILED_NAME = "id";
	
	private static final String INDEX_RETURN = "/system/applicationproperty/index";

	@SuppressWarnings("unused")
	private static final String INDEX_LIST_TB = "sys_app_property_list_tb";

	@Autowired
	private ApplicationPropertyService applicationPropertyService;
	/**DataGrid表格*/
	@Autowired
	private DataGridService dataGridService;
	/**Excel通用导出*/
	@Autowired
	private ExcelExportService excelExportService;
	
	/** 首页显示 */
	@RequestMapping(value = "index")
	public String index() {
		return INDEX_RETURN;
	}
	
	/** 列表显示 */
	@RequestMapping(value = "list")
	@ResponseBody
	public DataTable<ApplicationProperty> lists(QueryDt query,
			@ModelAttribute("applicationproperty") ApplicationPropertyVo applicationPropertyVo) {
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
			queryCriteria.setOrderDirection(QueryCriteria.ASC);
		}
		
		// 查询条件 appId
		if (StringUtils.isNotEmpty(applicationPropertyVo.getAppId())) {
			queryCriteria.addQueryCondition("appId", "%"+applicationPropertyVo.getAppId()+"%");
		}
		//查询条件 value
		if(StringUtils.isNotEmpty(applicationPropertyVo.getValue())){
			queryCriteria.addQueryCondition("value","%"+applicationPropertyVo.getValue()+"%");
		}

		PageResult<ApplicationProperty> pageResult = applicationPropertyService
				.doFindByCriteria(queryCriteria);
		return pageResult.getDataTable(query.getRequestId());
	}
	
	/** 项目属性配置数据添加保存 */
	@RequestMapping(value = "add")
	@ResponseBody
	public ModelAndView add(@Valid @ModelAttribute("applicationproperty") ApplicationPropertyVo applicationPropertyVo,
			Errors errors, ModelAndView modelAndView) {

		//是否错误消息
		if (errors.hasErrors()) {
			modelAndView.addObject(BindMessage.ERRORS_MODEL_KEY, errors);
			return this.getJsonView(modelAndView);
		}
		ApplicationProperty applicationProperty = new ApplicationProperty();

		BeanUtils.copyProperties(applicationPropertyVo, applicationProperty);
		applicationPropertyService.doCreate(applicationProperty);
		Map<String, String> params = new HashMap<String, String>();
		params.put(BindResult.SUCCESS, "sys.applicationProperty.add.success");
		modelAndView.addObject(BindMessage.SUCCESS, params);
		
		return this.getJsonView(modelAndView);
	}
	
	/** 编辑项目属性配置 */
	@RequestMapping(value = "edit")
	@ResponseBody
	public ModelAndView edit(@Valid @ModelAttribute("applicationproperty") ApplicationPropertyVo applicationPropertyVo,
			Errors errors, ModelAndView modelAndView) {

		modelAndView=this.getJsonView(modelAndView);
		if (!errors.hasErrors() && applicationPropertyVo.getId() == null) {
			errors.reject("sys.applicationProperty.form.id.empty");
			modelAndView.addObject(BindMessage.ERRORS_MODEL_KEY, errors);
			return modelAndView; 
		} 
		ApplicationProperty applicationProperty = applicationPropertyService.doFindById(applicationPropertyVo.getId());
		if (applicationProperty == null) {
			errors.reject("sys.applicationProperty.form.id.empty");
			return modelAndView;
		}

		if (errors.hasErrors()) {
			modelAndView.addObject(BindMessage.ERRORS_MODEL_KEY, errors);
			return modelAndView;
		}

		BeanUtils.copyProperties(applicationPropertyVo, applicationProperty);
		applicationPropertyService.doUpdate(applicationProperty);

		Map<String, String> params = new HashMap<String, String>();
		params.put(BindResult.SUCCESS, "sys.applicationProperty.edit.success");
		modelAndView.addObject(BindMessage.SUCCESS, params);
		return modelAndView;
	}
	
	/** 删除项目属性配置 */
	@RequestMapping(value = "delete")
	@ResponseBody
	public ModelAndView delete(@RequestParam Long id, ModelAndView modelAndView) {
		Map<String, String> params = new HashMap<String, String>();
		int updateRow = this.applicationPropertyService.doDeleteById(id);
		if (updateRow > 0) {
			params.put(BindResult.SUCCESS,"sys.applicationProperty.delete.success");
		} else {
			params.put(BindResult.SUCCESS,"sys.applicationProperty.delete.fail");
		}
		modelAndView.addObject(BindMessage.SUCCESS, params);
		return this.getJsonView(modelAndView);
	}
	
	/****
	 * 
	 * @param tableId
	 * @param applicationPropertyVo
	 * @param modelAndView
	 * @return
	 */
	@RequestMapping(value = "export")
	@ResponseBody
	public ModelAndView exportExcel(@RequestParam(required=false) String tableId,@RequestParam(required = false) String sort,@RequestParam(required = false) String order,@ModelAttribute("applicationProperty") ApplicationPropertyVo applicationPropertyVo,ModelAndView modelAndView){
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
		// 查询条件 appId
		if (StringUtils.isNotEmpty(applicationPropertyVo.getAppId())) {
			queryCriteria.addQueryCondition("appId", "%"+applicationPropertyVo.getAppId()+"%");
		}
		//查询条件 value
		if(StringUtils.isNotEmpty(applicationPropertyVo.getValue())){
			queryCriteria.addQueryCondition("value","%"+applicationPropertyVo.getValue()+"%");
		}

		PageResult<ApplicationProperty> result=applicationPropertyService.doFindByCriteria(queryCriteria);
		Map<String, Map<Object, Object>> fieldCodeTypes = new HashMap<String, Map<Object, Object>>();

		Map<String, String> dataFormats = new HashMap<String, String>();		
		dataFormats.put("birthday", DateUtil.FORMAT_DATE_YYYY_MM_DD);
		//创建.xls的文件名
		String fileName=this.createFileName(FileUtil.EXCEL_EXTENSION);
		
		modelAndView.addObject("title", dataGrid.getTitle());
		
		Long startTime=System.currentTimeMillis();
		
		fileName=excelExportService.export(dataGrid, result.getContent(), fileName, fieldCodeTypes, dataFormats);
		
		logger.info("fileName:{}",fileName);
		
		Long costTime=System.currentTimeMillis()-startTime;
		
		modelAndView.addObject(FILENAME,fileName);
		
		logger.info("export Excel {} cost:{} time",dataGrid.getTitle(),costTime);
		logger.info("out Excel导出");
		return this.getExcelView(modelAndView);
	}
}
