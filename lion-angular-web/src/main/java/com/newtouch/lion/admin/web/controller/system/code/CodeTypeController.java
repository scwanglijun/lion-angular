/*
 * Copyright (c)  2014, NewTouch
 * All rights reserved. 
 *
 * $id: CodeTypeController.java 9552 2014-4-8 上午10:06:58 WangLijun$
 */
package com.newtouch.lion.admin.web.controller.system.code;

import com.newtouch.lion.admin.web.model.system.code.CodeTypeVo;
import com.newtouch.lion.common.date.DateUtil;
import com.newtouch.lion.common.file.FileUtil;
import com.newtouch.lion.data.DataTable;
import com.newtouch.lion.model.datagrid.DataGrid;
import com.newtouch.lion.model.system.CodeType;
import com.newtouch.lion.page.PageResult;
import com.newtouch.lion.query.QueryCriteria;
import com.newtouch.lion.service.datagrid.DataGridService;
import com.newtouch.lion.service.excel.ExcelExportService;
import com.newtouch.lion.service.system.CodeTypeService;
import com.newtouch.lion.vo.CodeTypeView;
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
 * Title: IM-编码类型
 * </p>
 * <p>
 * Description: IM-编码类型-Controller
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
@Controller("sysCodeTypeController")
@RequestMapping("/system/codetype/")
public class CodeTypeController extends AbstractController{

	private final Logger logger = LoggerFactory.getLogger(super.getClass());

	/**默认排序字段名称*/
	private static final String DEFAULT_ORDER_FILED_NAME="id";
	@SuppressWarnings("unused")
	private static final String INDEX_LIST_TB = "sys_codetype_lists_tb";
	/** 首页返回路径 */
	private static final String INDEX_RETURN = "system/codetype/index";
	/** 参数编辑首页 */
	private static final String EDIT_DIALOG_RETURN = "/system/codetype/editdialog";

	@Autowired
	private CodeTypeService codeTypeService;
	/**DataGrid表格*/
	@Autowired
	private DataGridService dataGridService;
	/**Excel通用导出*/
	@Autowired
	private ExcelExportService excelExportService;
	
	/**
	 * 编码类型下拉列表
	 * */
	@RequestMapping("combox")
	@ResponseBody
	public List<CodeType> combox() {
		List<CodeType> codeTypes = this.codeTypeService.doFindAll();
		return codeTypes;
	}

	@RequestMapping(value = "editdialog")
	public String editDialog(@RequestParam Long id, Model model) {
		if (id != null) {
			CodeType codeType = this.codeTypeService.doFindById(id);
			model.addAttribute("codeType", codeType);
		} else {
			logger.error("Eidt Object id is not null!");
		}
		return EDIT_DIALOG_RETURN;
	}

	@RequestMapping(value = "add")
	@ResponseBody
	public ModelAndView add(
			@Valid @ModelAttribute("codeList") CodeTypeVo codeTypeVo,
			Errors errors, ModelAndView modelAndView) {
		if (!errors.hasErrors()&& this.isExistByNameEn(codeTypeVo.getNameEn())) {
			errors.rejectValue(CodeTypeVo.NAMEEN,
					"sys.codeType.form.nameen.existed.message",
					new Object[] { codeTypeVo.getNameEn() }, null);
		}
		//是否错误消息
		if (errors.hasErrors()) {
			modelAndView.addObject(BindMessage.ERRORS_MODEL_KEY, errors);
			return this.getJsonView(modelAndView);
		}
		CodeType codeType = new CodeType();

		BeanUtils.copyProperties(codeTypeVo, codeType);
		codeTypeService.doCreate(codeType);
		Map<String, String> params = new HashMap<String, String>();
		params.put(BindResult.SUCCESS, "sys.codeType.add.success");
		modelAndView.addObject(BindMessage.SUCCESS, params);
		
		return this.getJsonView(modelAndView);
	}

	/** 编辑 */
	@RequestMapping(value = "edit")
	@ResponseBody
	public ModelAndView edit(
			@Valid @ModelAttribute("codeTypeVo") CodeTypeVo codeTypeVo,
			Errors errors, ModelAndView modelAndView) {
		modelAndView=this.getJsonView(modelAndView);
		if (!errors.hasErrors() && codeTypeVo.getId() == null) {
			errors.reject("sys.codeType.form.id.empty");
			modelAndView.addObject(BindMessage.ERRORS_MODEL_KEY, errors);
			return modelAndView;
		}
		CodeType codeType = codeTypeService.doFindById(codeTypeVo.getId());
		if (codeType == null) {
			errors.reject("sys.codeType.form.id.empty");
			return modelAndView;
		}
		
		if (!errors.hasErrors()
				&& this.isExistByNameEn(codeTypeVo.getNameEn(),codeType.getNameEn())) {errors.rejectValue(CodeTypeVo.NAMEEN,	"sys.codeType.form.nameen.existed.message",new Object[] { codeTypeVo.getNameEn() }, null);

		}

		if (errors.hasErrors()) {
			modelAndView.addObject(BindMessage.ERRORS_MODEL_KEY, errors);
			return modelAndView;
		}

		BeanUtils.copyProperties(codeTypeVo, codeType);
		codeTypeService.doUpdate(codeType);

		Map<String, String> params = new HashMap<String, String>();
		params.put(BindResult.SUCCESS, "sys.codeType.edit.success");
		modelAndView.addObject(BindMessage.SUCCESS, params);
		return modelAndView;
	}

	@RequestMapping(value = "delete")
	@ResponseBody
	public ModelAndView delete(@RequestParam Long id, ModelAndView modelAndView) {
		Map<String, String> params = new HashMap<String, String>();
		int updateRow = this.codeTypeService.doDeleteById(id);
		if (updateRow > 0) {
			params.put(BindResult.SUCCESS,"sys.codeType.delete.success");
		} else {
			params.put(BindResult.SUCCESS,"sys.codeType.delete.fail");
		}
		modelAndView.addObject(BindMessage.SUCCESS, params);
		return this.getJsonView(modelAndView);
	}

	@RequestMapping(value = "list")
	@ResponseBody
	public DataTable<CodeTypeView> list(QueryDt query,@ModelAttribute("codetype") CodeTypeVo codeTypeVo){
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
		if (StringUtils.isNotEmpty(codeTypeVo.getType())) {
			queryCriteria.addQueryCondition("type", codeTypeVo.getType());
		}
		//查询条件 中文参数名称按模糊查询
		if(StringUtils.isNotEmpty(codeTypeVo.getNameZh())){
			queryCriteria.addQueryCondition("nameZh","%"+codeTypeVo.getNameZh()+"%");
		}

		PageResult<CodeTypeView> pageResult = codeTypeService.doFindVoByCriteria(queryCriteria);
		
		return pageResult.getDataTable(query.getRequestId());
	}
	
	/** 首页显示 */
	@RequestMapping(value = "index")
	public String index() {
		return INDEX_RETURN;
	}
	/*add by maojiawei*/
	private Boolean isExistByNameEn(String nameEn) {
		Boolean flag = false;
		if (StringUtils.isNotEmpty(nameEn)) {
			flag = codeTypeService.doIsExistByNameEn(nameEn.trim());
		}
		return flag;
	}
	/*add by maojiawei*/
	private Boolean isExistByNameEn(String nameEn, String oldNameEn) {
		Boolean flag = false;
		if (StringUtils.isNotEmpty(nameEn) && !nameEn.equals(oldNameEn)) {
			flag = codeTypeService.doIsExistByNameEn(nameEn.trim());
		}
		return flag;
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
			CodeType codeType = codeTypeService.doFindById(id);
			if(codeType==null){
				flag = this.isExistByNameEn(nameEn)? false : true;
			}else{
				flag=this.isExistByNameEn(nameEn, codeType.getNameEn())?false:true;
			}
		}
		return flag.toString();
	}
	
	/****
	 * 
	 * @param tableId
	 * @param codeTypeVo
	 * @param modelAndView
	 * @return
	 */
	@RequestMapping(value = "export")
	@ResponseBody
	public ModelAndView exportExcel(@RequestParam(required=false) String tableId,@RequestParam(required = false) String sort,@RequestParam(required = false) String order,@ModelAttribute("codetype") CodeTypeVo codeTypeVo,ModelAndView modelAndView){
		
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
		if (StringUtils.isNotEmpty(codeTypeVo.getType())) {
			queryCriteria.addQueryCondition("type", codeTypeVo.getType());
		}
		//查询条件 中文参数名称按模糊查询
		if(StringUtils.isNotEmpty(codeTypeVo.getNameZh())){
			queryCriteria.addQueryCondition("nameZh","%"+codeTypeVo.getNameZh()+"%");
		}

		PageResult<CodeType> result=codeTypeService.doFindByCriteria(queryCriteria);
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
