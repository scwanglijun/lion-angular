/*
 * Copyright (c)  2014, NewTouch
 * All rights reserved. 
 *
 * $id: DataGridController.java 9552 2014-3-31 下午04:53:44 WangLijun$
 */
package com.newtouch.lion.admin.web.controller.system.datagrid;

import com.newtouch.lion.admin.web.model.system.datagrid.DataGridVo;
import com.newtouch.lion.common.date.DateUtil;
import com.newtouch.lion.common.file.FileUtil;
import com.newtouch.lion.data.DataTable;
import com.newtouch.lion.model.datagrid.DataGrid;
import com.newtouch.lion.page.PageResult;
import com.newtouch.lion.query.QueryCriteria;
import com.newtouch.lion.service.datagrid.DataGridService;
import com.newtouch.lion.service.excel.ExcelExportService;
import com.newtouch.lion.web.controller.AbstractController;
import com.newtouch.lion.web.model.QueryDt;
import com.newtouch.lion.web.servlet.view.support.BindMessage;
import com.newtouch.lion.web.servlet.view.support.BindResult;
import org.apache.commons.lang.StringUtils;
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
 * Title: 表格控件管理类
 * </p>
 * <p>
 * Description: 表格控件管理类
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
@Controller(value = "sysDataGridContorller")
@RequestMapping("/system/datagrid/")
public class DataGridController extends AbstractController {
	private final Logger logger = LoggerFactory.getLogger(super.getClass());
	/** 默认排序字段 */
	private static final String DEFAULT_ORDER_FILED_NAME = "id";
	/** 首页返回路径 */
	private static final String INDEX_RETURN = "system/datagrid/index";
	/** 新增对话返回路径 */
	private static final String ADD_DIALOG_RETURN = "system/datagrid/dialog/add";
	/** 修改对话返回路径 */
	private static final String EDIT_DIALOG_RETURN = "system/datagrid/editdialog";

	/** 首页显示列表名称 */
	@SuppressWarnings("unused")
	private static final String INDEX_TB = "datagrid_dt";
	@Autowired
	private DataGridService dataGridService;
	/**Excel通用导出*/
	@Autowired
	private ExcelExportService excelExportService;
	
	/** 新增的对话框 */
	@RequestMapping(value = "dialog/add.htm")
	public String addDialog() {
		return ADD_DIALOG_RETURN;
	}

	/** 编辑对话框 */
	@RequestMapping(value = "editdialog")
	public String editDialog(@RequestParam Long id, Model model) {
		if (id != null) {
			DataGrid dataGrid = this.dataGridService.doGetById(id);
			model.addAttribute("dataGrid", dataGrid);
		} else {
			logger.error("Eidt Object id is not null!");
		}
		return EDIT_DIALOG_RETURN;
	}

	/** 首页显示 */
	@RequestMapping(value = "index")
	public String index() {
		return INDEX_RETURN;
	}

	/** DataGrid列表 */
	@RequestMapping(value = "combox")
	@ResponseBody
	public List<DataGrid> comobx() {
		return this.dataGridService.doFindAll();
	}

	/** 首页显示 */
	@RequestMapping(value = "comboxwithtype")
	@ResponseBody
	public String comobxByType(@RequestParam(required = false) String type) {
		return this.dataGridService.doFindComboxByType(type);
	}

	@RequestMapping(value = "delete")
	@ResponseBody
	public ModelAndView delete(@RequestParam Long id, ModelAndView modelAndView) {
		Map<String, String> params = new HashMap<String, String>();
		int updateRow = this.dataGridService.doDeleteById(id);
		if (updateRow > 0) {
			params.put(BindResult.SUCCESS,"sys.datagrid.delete.success");
		} else {
			params.put(BindResult.SUCCESS,"sys.datagrid.delete.fail");
		}
		modelAndView.addObject(BindMessage.SUCCESS, params);
		return this.getJsonView(modelAndView);
	}

	@RequestMapping(value = "add")
	@ResponseBody
	public ModelAndView add(@Valid @ModelAttribute("dataGridVo")DataGridVo dataGridVo,Errors errors, ModelAndView modelAndView){
		
		if (!errors.hasErrors()&& this.isExistByTableId(dataGridVo.getTableId())) {
			errors.rejectValue(DataGridVo.TABLEID,
					"sys.datagrid.form.tableId.existed.message",
					new Object[] { dataGridVo.getTableId() }, null);
		}
		//是否错误消息
		if (errors.hasErrors()) {
			modelAndView.addObject(BindMessage.ERRORS_MODEL_KEY, errors);
			return this.getJsonView(modelAndView);
		}
		DataGrid dataGrid = new DataGrid();

		BeanUtils.copyProperties(dataGridVo, dataGrid);
		dataGridService.doCreate(dataGrid);
		Map<String, String> params = new HashMap<String, String>();
		params.put(BindResult.SUCCESS, "sys.datagrid.add.success");
		modelAndView.addObject(BindMessage.SUCCESS, params);
		
		return this.getJsonView(modelAndView);
	}

	@SuppressWarnings("deprecation")
	@RequestMapping(value = "checktableid")
	@ResponseBody
	public String checkTableId(String tableId) {
		Boolean flag = true;
		if (StringUtils.isEmpty(tableId)) {
			return flag.toString();
		}
		DataGrid dataGrid = this.dataGridService
				.doFindByTableId(tableId.trim());
		if (dataGrid != null && StringUtils.isNotEmpty(dataGrid.getTableId())) {
			flag = Boolean.FALSE;
		}
		return flag.toString();
	}

	@RequestMapping(value = "edit")
	@ResponseBody
	public ModelAndView edit(
			@Valid @ModelAttribute("dataGrid") DataGridVo dataGridVo,
			Errors errors, ModelAndView modelAndView) {

		modelAndView=this.getJsonView(modelAndView);
		if (!errors.hasErrors() && dataGridVo.getId() == null) {
			errors.reject("sys.datagrid.form.id.empty");
			modelAndView.addObject(BindMessage.ERRORS_MODEL_KEY, errors);
			return modelAndView; 
		} 
		DataGrid dataGrid = dataGridService.doGetById(dataGridVo.getId());
		if (dataGrid == null) {
			errors.reject("sys.datagrid.form.id.empty");
			return modelAndView;
		}
		
		if (!errors.hasErrors()
				&& this.isExistByTableId(dataGridVo.getTableId(),dataGrid.getTableId())) {errors.rejectValue(DataGridVo.TABLEID,	"sys.datagrid.form.name.existed.message",new Object[] { dataGridVo.getTableId() }, null);

		}

		if (errors.hasErrors()) {
			modelAndView.addObject(BindMessage.ERRORS_MODEL_KEY, errors);
			return modelAndView;
		}

		BeanUtils.copyProperties(dataGridVo, dataGrid);
		dataGridService.doUpdate(dataGrid);

		Map<String, String> params = new HashMap<String, String>();
		params.put(BindResult.SUCCESS, "sys.datagrid.edit.success");
		modelAndView.addObject(BindMessage.SUCCESS, params);
		return modelAndView;
	}

	/** 列表显示 */
	@RequestMapping(value = "list")
	@ResponseBody
	public DataTable<DataGrid> lists(QueryDt query,@ModelAttribute("dataGrid") DataGridVo dataGridVo) {
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
		// 查询条件 参数类型
		if (StringUtils.isNotEmpty(dataGridVo.getType())) {
			queryCriteria.addQueryCondition("type", dataGridVo.getType());
		}
		// 查询条件 tableId
		if (StringUtils.isNotEmpty(dataGridVo.getTableId())) {
			queryCriteria.addQueryCondition("tableId", "%"+dataGridVo.getTableId()+"%");
		}
		// 查询条件 title
		if (StringUtils.isNotEmpty(dataGridVo.getTitle())) {
			queryCriteria.addQueryCondition("title", "%"+dataGridVo.getTitle()+"%");
		}
		PageResult<DataGrid> pageResult = dataGridService
				.doFindByCriteria(queryCriteria);
		return pageResult.getDataTable(query.getRequestId());
	}
	
	/*add by maojiawei*/
	private Boolean isExistByTableId(String tableId) {
		Boolean flag = false;
		if (StringUtils.isNotEmpty(tableId)) {
			flag = dataGridService.doIsExistByTableId(tableId.trim());
		}
		return flag;
	}
	/*add by maojiawei*/
	private Boolean isExistByTableId(String tableId, String oldName) {
		Boolean flag = false;
		if (StringUtils.isNotEmpty(tableId) && !tableId.equals(oldName)) {
			flag = dataGridService.doIsExistByTableId(tableId.trim());
		}
		return flag;
	}
	/*add by maojiawei*/
	@RequestMapping(value = "checkisexitnameen")
	@ResponseBody
	public String checkIsExistByTableId(HttpServletRequest servletRequest,
			@RequestParam(required = false) String tableId,@RequestParam(required=false) Long id) {
		Boolean flag=Boolean.FALSE;
		
		if(tableId==null){
			flag = this.isExistByTableId(tableId)? false : true;
		}else{
			DataGrid dataGrid = dataGridService.doGetById(id);
			if(dataGrid==null){
				flag = this.isExistByTableId(tableId)? false : true;
			}else{
				flag=this.isExistByTableId(tableId, dataGrid.getTableId())?false:true;
			}
		}
		return flag.toString();
	}
	/****
	 * 
	 * @param tableId
	 * @param dataGridVo
	 * @param queryVo
	 * @param modelAndView
	 * @return
	 */
	@RequestMapping(value = "export")
	@ResponseBody
	public ModelAndView exportExcel(@RequestParam(required=false) String tableId1,@RequestParam(required = false) String sort,@RequestParam(required = false) String order,@ModelAttribute("dataGrid") DataGridVo dataGridVo,ModelAndView modelAndView){
				
		DataGrid dataGrid=dataGridService.doFindByTableIdAndSort(tableId1);
		QueryCriteria queryCriteria=new QueryCriteria();
		queryCriteria.setPageSize(10000);
		// 设置排序字段及排序方向
		if (StringUtils.isNotEmpty(sort) && StringUtils.isNotEmpty(sort)) {
			queryCriteria.setOrderField(sort);
			queryCriteria.setOrderDirection(sort);
		} else {
			queryCriteria.setOrderField(DEFAULT_ORDER_FILED_NAME);
			queryCriteria.setOrderDirection(QueryCriteria.ASC);
		}
		// 查询条件 参数类型
		if (StringUtils.isNotEmpty(dataGridVo.getType())) {
			queryCriteria.addQueryCondition("type", dataGridVo.getType());
		}
		// 查询条件 tableId
		if (StringUtils.isNotEmpty(dataGridVo.getTableId())) {
			queryCriteria.addQueryCondition("tableId", "%"+dataGridVo.getTableId()+"%");
		}
		// 查询条件 title
		if (StringUtils.isNotEmpty(dataGridVo.getTitle())) {
			queryCriteria.addQueryCondition("title", "%"+dataGridVo.getTitle()+"%");
		}
		PageResult<DataGrid> result=dataGridService.doFindByCriteria(queryCriteria);
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
		
		logger.info("export Excel {} cost:{} time,fileName:{}",dataGrid.getTitle(),costTime,fileName);
		logger.info("out Excel导出");
		return this.getExcelView(modelAndView);
	}
}
