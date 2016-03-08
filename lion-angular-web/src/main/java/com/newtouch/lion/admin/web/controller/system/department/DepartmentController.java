/*
 * Copyright (c)  2014, NewTouch
 * All rights reserved. 
 *
 * $id: DepartmentController.java 9552 2014-4-3 下午02:05:57 WangLijun$
 */
package com.newtouch.lion.admin.web.controller.system.department;

import com.newtouch.lion.admin.web.model.system.department.DepartmentVo;
import com.newtouch.lion.common.date.DateUtil;
import com.newtouch.lion.common.file.FileUtil;
import com.newtouch.lion.model.datagrid.DataGrid;
import com.newtouch.lion.model.system.Department;
import com.newtouch.lion.query.QueryCriteria;
import com.newtouch.lion.service.datagrid.DataGridService;
import com.newtouch.lion.service.excel.ExcelExportService;
import com.newtouch.lion.service.system.ExtendDepartmentService;
import com.newtouch.lion.web.constant.ConstantMessage;
import com.newtouch.lion.web.controller.AbstractController;
import com.newtouch.lion.web.model.QueryVo;
import com.newtouch.lion.web.servlet.view.support.BindMessage;
import com.newtouch.lion.web.servlet.view.support.BindResult;
import com.newtouch.lion.ztree.TreeNode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * Title: 部门管理控制器
 * </p>
 * <p>
 * Description: 部门管理包括添加、删除、查询、修改等功能
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
@RequestMapping("/system/department/")
public class DepartmentController extends AbstractController{

	private final Logger logger = LoggerFactory.getLogger(super.getClass());
	/** 参数首页 */
	private static final String INDEX_RETURN = "/system/department/index";
	
	@SuppressWarnings("unused")
	private static final String INDEX_LISTS_TB = "sys_department_lists";	 
	/**部门扩展*/
	@Autowired
	private ExtendDepartmentService  extendDepartmentService;
	/**Excel通用导出*/
	@Autowired
	private ExcelExportService excelExportService;
	/**DataGrid表格*/
	@Autowired
	private DataGridService dataGridService;

	@RequestMapping(value = "add")
	@ResponseBody
	public ModelAndView add(
			@Valid @ModelAttribute("departmentVo") DepartmentVo departmentVo,
			Errors errors, ModelAndView modelAndView) {
		if (errors.hasErrors()) {
			modelAndView.addObject(BindMessage.ERRORS_MODEL_KEY, errors);
			return this.getJsonView(modelAndView);
		}
		Department department = new Department();
		BeanUtils.copyProperties(departmentVo, department);
		this.extendDepartmentService.doCreateDepartment(department);
		Map<String, String> params = new HashMap<String, String>();
		params.put(BindResult.SUCCESS, ConstantMessage.ADD_SUCCESS_MESSAGE_CODE);
		modelAndView.addObject(BindMessage.SUCCESS, params);
		return this.getJsonView(modelAndView);
	}

	@RequestMapping(value = "edit")
	@ResponseBody
	public ModelAndView edit(
			@Valid @ModelAttribute("departmentVo") DepartmentVo departmentVo,
			Errors errors, ModelAndView modelAndView) {
		Department department = null;
		if (departmentVo.getId() != null) {
			department = this.extendDepartmentService
					.doFindDepartmentById(departmentVo.getId());
			if (department == null) {
				errors.reject(ConstantMessage.EDIT_ISEMPTY_FAIL_MESSAGE_CODE);
			}
		} else {
			errors.reject(ConstantMessage.EDIT_ISEMPTY_FAIL_MESSAGE_CODE);
		}

		if (errors.hasErrors()) {
			modelAndView.addObject(BindMessage.ERRORS_MODEL_KEY, errors);
			return this.getJsonView(modelAndView);
		}
		BeanUtils.copyProperties(departmentVo, department);
		this.extendDepartmentService.doUpdate(department);
		Map<String, String> params = new HashMap<String, String>();
		params.put(BindResult.SUCCESS,
				ConstantMessage.EDIT_SUCCESS_MESSAGE_CODE);
		modelAndView.addObject(BindMessage.SUCCESS, params);
		return this.getJsonView(modelAndView);
	}

	@RequestMapping(value = "delete")
	@ResponseBody
	public ModelAndView delete(@RequestParam Long id, ModelAndView modelAndView) {
		logger.info("delete id:{}",id);
		Map<String, String> params = new HashMap<String, String>();
		Department department = this.extendDepartmentService.doDelete(id);
		if (department != null) {
			params.put(BindResult.SUCCESS,
					ConstantMessage.DELETE_SUCCESS_MESSAGE_CODE);
		} else {
			params.put(BindResult.SUCCESS,
					ConstantMessage.DELETE_FAIL_MESSAGE_CODE);
		}
		modelAndView.addObject(BindMessage.SUCCESS, params);
		return this.getJsonView(modelAndView);
	}
	
	@RequestMapping("list")
	@ResponseBody
	public  ModelAndView lists(ModelAndView modelAndView) {			
		String str=this.extendDepartmentService.doFindFirstLevelToTree();
		str=str.replace("departments","children").replace("parentDepartmentId", "_parentId");
		return this.getStrJsonView(str, modelAndView);
	}
	/****
	 * 
	 * @param tableId
	 * @param parameterVo
	 * @param modelAndView
	 * @return
	 */
	@RequestMapping(value = "export")
	@ResponseBody
	public ModelAndView exportExcel(@ModelAttribute("queryVo")QueryVo queryVo,@ModelAttribute("department") DepartmentVo departmentVo,Errors errors, ModelAndView modelAndView){
				
		DataGrid dataGrid=dataGridService.doFindByTableIdAndSort(queryVo.getTableId());
		QueryCriteria queryCriteria=new QueryCriteria();
		queryCriteria.setPageSize(10000);
	 
		// 设置分页 启始页
		queryCriteria.setStartIndex(queryVo.getRows() * (queryVo.getPage() - 1));
		// 每页大小
		queryCriteria.setPageSize(queryVo.getRows());	 
	 

	    List<Department> result=this.extendDepartmentService.doFindAll();
		Map<String, Map<Object, Object>> fieldDepartment= new HashMap<String, Map<Object, Object>>();
		fieldDepartment.put("department",null);

		Map<String, String> dataFormats = new HashMap<String, String>();		
		dataFormats.put("accountExpiredDate", DateUtil.FORMAT_DATE_YYYY_MM_DD);
		//创建.xls的文件名
		String fileName=this.createFileName(FileUtil.EXCEL_EXTENSION);
		
		modelAndView.addObject("title", dataGrid.getTitle());
		
		Long startTime=System.currentTimeMillis();
		
		fileName=excelExportService.export(dataGrid, result,fileName,fieldDepartment,dataFormats);
		
		logger.info("fileName:{}",fileName);
		
		Long costTime=System.currentTimeMillis()-startTime;
		
		modelAndView.addObject(FILENAME,fileName);
		
		logger.info("export Excel {} cost:{} time,fileName:{}",dataGrid.getTitle(),costTime,fileName);
		logger.info("out Excel导出");
		return this.getExcelView(modelAndView);
	}
	@RequestMapping("comboxtree")
	@ResponseBody
	public List<TreeNode> comboxTree() {
		List<TreeNode> list=extendDepartmentService.doFindDepartmentToTree();		
		return list;
	}
	
	@RequestMapping(value = "index")
	public String index(HttpServletRequest servletRequest, Model model) {
		return INDEX_RETURN;
	}
}
