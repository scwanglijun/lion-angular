/*
 * Copyright (c)  2014, NewTouch
 * All rights reserved. 
 *
 * $id: CodeListController.java 9552 2014-2-18 下午04:35:02 WangLijun$
 */
package com.newtouch.lion.admin.web.controller.system.code;

import com.newtouch.lion.admin.web.model.system.code.CodeListVo;
import com.newtouch.lion.common.date.DateUtil;
import com.newtouch.lion.common.file.FileUtil;
import com.newtouch.lion.data.DataTable;
import com.newtouch.lion.json.JSONParser;
import com.newtouch.lion.model.datagrid.DataColumn;
import com.newtouch.lion.model.datagrid.DataGrid;
import com.newtouch.lion.model.system.CodeList;
import com.newtouch.lion.page.PageResult;
import com.newtouch.lion.query.QueryCriteria;
import com.newtouch.lion.service.datagrid.DataColumnService;
import com.newtouch.lion.service.datagrid.DataGridService;
import com.newtouch.lion.service.excel.ExcelExportService;
import com.newtouch.lion.service.system.CodeListService;
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
import java.util.*;

/**
 * <p>
 * Title:IM字典列表
 * </p>
 * <p>
 * Description:IM字典列表
 * </p>
 * <p>
 * Copyright: Copyright (c) 2014
 * </p>
 * <p>
 * Company: NewTouch
 * </p>
 * 
 * @author dengkang
 * @version 1.0
 */
@Controller(value = "sysCodelistController")
@RequestMapping("/system/codelist/")
public class CodeListController extends AbstractController{

	private final Logger logger = LoggerFactory.getLogger(super.getClass());
	/** 参数添加首页 */
	private static final String ADD_DIALOG_RETURN = "/system/codelist/adddialog";
	/** 参数编辑首页 */
	private static final String EDIT_DIALOG_RETURN = "/system/codelist/editdialog";
	/** 首页返回路径 */
	private static final String INDEX_RETURN = "/system/codelist/index";
	/**默认排序字段名称*/
	private static final String DEFAULT_ORDER_FILED_NAME="id";
	@Autowired
	private CodeListService codeListService;
	@Autowired
	protected DataColumnService dataColumnService;
 
	/**DataGrid表格*/
	@Autowired
	private DataGridService dataGridService;
	/**Excel通用导出*/
	@Autowired
	private ExcelExportService excelExportService;
	@RequestMapping(value = "/editdialog")
	public String editDialog(@RequestParam Long id, Model model) {
		if (id != null) {
			CodeList codeList = this.codeListService.doFindById(id);
			model.addAttribute("codeList", codeList);
		} else {
			logger.error("Eidt Object id is not null!");
		}
		return EDIT_DIALOG_RETURN;
	}

	/** 编辑 */
	@RequestMapping(value = "edit")
	@ResponseBody
	public ModelAndView edit(
			@Valid @ModelAttribute("codeList") CodeListVo codeListVo,
			Errors errors, ModelAndView modelAndView) {
		modelAndView=this.getJsonView(modelAndView);
		if (!errors.hasErrors() && codeListVo.getId() == null) {
			errors.reject("sys.codeList.form.id.empty");
			modelAndView.addObject(BindMessage.ERRORS_MODEL_KEY, errors);
			return modelAndView;
		}
		CodeList codeList = codeListService.doFindById(codeListVo.getId());
		if (codeList == null) {
			errors.reject("sys.codeList.form.id.empty");
			return modelAndView;
		}
		
		if (!errors.hasErrors()
				&& this.isExistByNameEn(codeListVo.getNameEn(),codeList.getNameEn())) {errors.rejectValue(CodeListVo.NAMEEN,	"sys.codeList.form.nameen.existed.message",new Object[] { codeListVo.getNameEn() }, null);

		}

		if (errors.hasErrors()) {
			modelAndView.addObject(BindMessage.ERRORS_MODEL_KEY, errors);
			return modelAndView;
		}
		BeanUtils.copyProperties(codeListVo, codeList);
		codeListService.doUpdateObj(codeList);

		Map<String, String> params = new HashMap<String, String>();
		params.put(BindResult.SUCCESS, "sys.codeList.edit.success");
		modelAndView.addObject(BindMessage.SUCCESS, params);
		return modelAndView;
	}

	@RequestMapping(value = "/adddialog")
	public String addDialog(HttpServletRequest servletRequest, Model model) {
		return ADD_DIALOG_RETURN;
	}

	@RequestMapping(value = "add")
	@ResponseBody
	public ModelAndView add(
			@Valid @ModelAttribute("codeList") CodeListVo codeListVo,
			Errors errors, ModelAndView modelAndView) {

		if (!errors.hasErrors()&& this.isExistByNameEn(codeListVo.getNameEn())) {
			errors.rejectValue(CodeListVo.NAMEEN,
					"sys.codeList.form.nameen.existed.message",
					new Object[] { codeListVo.getNameEn() }, null);
		}
		//是否错误消息
		if (errors.hasErrors()) {
			modelAndView.addObject(BindMessage.ERRORS_MODEL_KEY, errors);
			return this.getJsonView(modelAndView);
		}
		CodeList codeList = new CodeList();

		BeanUtils.copyProperties(codeListVo, codeList);
		codeListService.doCreate(codeList);
		Map<String, String> params = new HashMap<String, String>();
		params.put(BindResult.SUCCESS, "sys.codeList.add.success");
		modelAndView.addObject(BindMessage.SUCCESS, params);
		
		return this.getJsonView(modelAndView);
	}

	@RequestMapping(value = "delete")
	@ResponseBody
	public ModelAndView delete(@RequestParam Long id, ModelAndView modelAndView) {
		Map<String, String> params = new HashMap<String, String>();
		int updateRow = this.codeListService.doDeleteById(id);
		if (updateRow > 0) {
			params.put(BindResult.SUCCESS,"sys.codeList.delete.success");
		} else {
			params.put(BindResult.SUCCESS,"sys.codeList.delete.fail");
		}
		modelAndView.addObject(BindMessage.SUCCESS, params);
		return this.getJsonView(modelAndView);
	}

	private Boolean isExistByNameEn(String nameEn, String oldNameEn) {
		Boolean flag = false;
		if (StringUtils.isNotEmpty(nameEn) && !nameEn.equals(oldNameEn)) {
			flag = codeListService.doIsExistByNameEn(nameEn.trim());
		}
		return flag;
	}

	private Boolean isExistByNameEn(String nameEn) {
		Boolean flag = false;
		if (StringUtils.isNotEmpty(nameEn)) {
			flag = codeListService.doIsExistByNameEn(nameEn.trim());
		}
		return flag;
	}

	@RequestMapping(value = "list")
	@ResponseBody
	public DataTable<CodeList> list(QueryDt query,@ModelAttribute("codelist") CodeListVo codeListVo){
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
		if (codeListVo.getCodeTypeId() != null) {
			queryCriteria.addQueryCondition("codeTypeId", codeListVo.getCodeTypeId());
		}
		//查询条件 中文参数名称按模糊查询
		if(StringUtils.isNotEmpty(codeListVo.getNameZh())){
			queryCriteria.addQueryCondition("nameZh","%"+codeListVo.getNameZh()+"%");
		}

		PageResult<CodeList> pageResult = codeListService
				.doFindByCriteria(queryCriteria);
		for(CodeList codeList:pageResult.getContent()){
			codeList.getCodeType().getNameZh();
		}
		return pageResult.getDataTable(query.getRequestId());
	}

	/** 根据TableId配置DataGrid */
	protected Set<String> getColumns(String tableId) {
		List<DataColumn> dataColumns = this.dataColumnService
				.doFindByTableId(tableId);
		Set<String> properties = new HashSet<String>();
		for (DataColumn dataColumn : dataColumns) {
			properties.add(dataColumn.getField());
		}
		return properties;
	}

	protected String getJSONString(Long total, List<CodeList> objects,
			Set<String> properties, String datePattern,
			Boolean includeProperties) {
		StringBuffer sb = new StringBuffer();
		sb.append("{\"total\":");
		sb.append(total);
		sb.append(",\"rows\":");
		if (objects == null) {
			// log.info("-----objects is null");
		}
		// log.info("objects.size():"+objects.size());
		String objectJSONStr = JSONParser.toJSONString(objects, datePattern,
				properties, includeProperties);
		sb.append(objectJSONStr);
		sb.append("}");
		return sb.toString();
	}
	
	/** 首页显示 */
	@RequestMapping(value = "index")
	public String index() {
		return INDEX_RETURN;
	}
	
	/*add by maojiawei*/
	@RequestMapping(value = "checkisexitnameen")
	@ResponseBody
	public String checkIsExistByNameEn(HttpServletRequest servletRequest,
			@RequestParam(required = false) String nameEn,@RequestParam(required=false) Long id) {
		Boolean flag=Boolean.FALSE;
		
		if(id==null){
			flag = this.isExistByNameEn(nameEn)? false : true;
		}else{
			CodeList codeList = codeListService.doFindById(id);
			if(codeList==null){
				flag = this.isExistByNameEn(nameEn)? false : true;
			}else{
				flag=this.isExistByNameEn(nameEn, codeList.getNameEn())?false:true;
			}
		}
		return flag.toString();
	}
	
	/****
	 * 
	 * @param tableId
	 * @param codeListVo
	 * @param modelAndView
	 * @return
	 */
	@RequestMapping(value = "export")
	@ResponseBody
	public ModelAndView exportExcel(@RequestParam(required=false) String tableId,@RequestParam(required = false) String sort,@RequestParam(required = false) String order,@ModelAttribute("codeList") CodeListVo codeListVo,ModelAndView modelAndView){
		
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
		if (codeListVo.getCodeTypeId() != null) {
			queryCriteria.addQueryCondition("codeTypeId", codeListVo.getCodeTypeId());
		}
		//查询条件 中文参数名称按模糊查询
		if(StringUtils.isNotEmpty(codeListVo.getNameZh())){
			queryCriteria.addQueryCondition("nameZh","%"+codeListVo.getNameZh()+"%");
		}

		PageResult<CodeList> result=codeListService.doFindByCriteria(queryCriteria);
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
