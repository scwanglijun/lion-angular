/**
 * 
 */
package com.newtouch.lion.admin.web.controller.system.position;

import com.newtouch.lion.admin.web.model.system.position.PositionVo;
import com.newtouch.lion.admin.web.model.system.role.RoleVo;
import com.newtouch.lion.common.date.DateUtil;
import com.newtouch.lion.common.file.FileUtil;
import com.newtouch.lion.data.DataTable;
import com.newtouch.lion.model.datagrid.DataGrid;
import com.newtouch.lion.model.system.Department;
import com.newtouch.lion.model.system.Position;
import com.newtouch.lion.page.PageResult;
import com.newtouch.lion.query.QueryCriteria;
import com.newtouch.lion.service.datagrid.DataGridService;
import com.newtouch.lion.service.excel.ExcelExportService;
import com.newtouch.lion.service.system.DepartmentService;
import com.newtouch.lion.service.system.ExtendDepartmentService;
import com.newtouch.lion.service.system.PositionService;
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

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * <p>
 * Title: 职位控制器
 * </p>
 * <p>
 * Description: 包括职位查询、添加、删除、修改
 * </p>
 * <p>
 * Copyright: Copyright (c) 2015
 * </p>
 * <p>
 * Company: Newtouch
 * </p>
 * 
 * @author LiXiaoHao
 * @version 1.0
 */
@Controller
@RequestMapping("/system/position/")
public class PositionController extends AbstractController {
	
	private final String INDEX_RETURN="/system/position/index";
	private final String DEFAULT_ORDER_FILED_NAME="id";
	@Autowired
	private PositionService positionService;
	@Autowired
	private ExtendDepartmentService  extendDepartmentService;
	@Autowired
	private DepartmentService departmentService;
	
	/**DataGrid表格*/
	@Autowired
	private DataGridService dataGridService;
	/**Excel通用导出*/
	@Autowired
	private ExcelExportService excelExportService;
	
	@RequestMapping("index")
	public String index(){
		return INDEX_RETURN;
	}
	
	
	@RequestMapping(value = "department")
	@ResponseBody
	public List<Department> departmentList() {
		List<Department> dList = departmentService.doFindAll();
		return dList;
	}
	
	@RequestMapping(value = "list")
	@ResponseBody
	public DataTable<Position> list(QueryDt query,@ModelAttribute("position") PositionVo positionVo) {
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
		//查询条件 中文参数名称按模糊查询
		if(StringUtils.isNotEmpty(positionVo.getNameZh())){
			queryCriteria.addQueryCondition("nameZh","%"+positionVo.getNameZh()+"%");
		}
		PageResult<Position> pageResult = positionService.doFindByCriteria(queryCriteria);
		for(Position position:pageResult.getContent()){
			System.out.println("--department:"+position.getDepartment());
		}
		return pageResult.getDataTable(query.getRequestId());
	}

	
	@RequestMapping(value = "add")
    @ResponseBody
	public ModelAndView add(@Valid @ModelAttribute("position") PositionVo  positionVo,Errors  errors,ModelAndView modelAndView){    	
		if (!errors.hasErrors()&& this.isExistByNameEn(positionVo.getNameEn())) {
			errors.rejectValue(RoleVo.NAMEEN,
					"sys.role.form.nameen.existed.message",
					new Object[] { positionVo.getNameEn() }, null);
		}

		//是否错误消息
		if (errors.hasErrors()) {
			modelAndView.addObject(BindMessage.ERRORS_MODEL_KEY, errors);
			return this.getJsonView(modelAndView);
		}
		Position position = new Position();

		BeanUtils.copyProperties(positionVo, position);
		positionService.doCreatePosition(position);
		Map<String, String> params = new HashMap<String, String>();
		params.put(BindResult.SUCCESS, "sys.role.add.success");
		modelAndView.addObject(BindMessage.SUCCESS, params);
		
		return this.getJsonView(modelAndView);
	}
	
	/***
	 * 编辑职位信息
	 * @param positionVo
	 * @param errors
	 * @param modelAndView
	 * @return
	 */
	@RequestMapping(value = "edit")
	@ResponseBody
	public ModelAndView edit(@Valid @ModelAttribute("position") PositionVo  positionVo,Errors  errors,ModelAndView modelAndView){
		
		modelAndView=this.getJsonView(modelAndView);
		if (!errors.hasErrors() && positionVo.getId() == null) {
			errors.reject("sys.group.form.id.empty");
			modelAndView.addObject(BindMessage.ERRORS_MODEL_KEY, errors);
			return modelAndView; 
		} 
		Position position = positionService.doFindById(positionVo.getId());
		
		if (position==null ) {
			errors.reject("sys.role.form.id.empty");
			return modelAndView;
		}
		
		if (!errors.hasErrors()
				&& this.isExistByNameEn(positionVo.getNameEn(),positionVo.getNameEn())) {errors.rejectValue(positionVo.getNameEn(),	"sys.role.form.nameen.existed.message",new Object[] { positionVo.getNameEn() }, null);

		}

		if (errors.hasErrors()) {
			modelAndView.addObject(BindMessage.ERRORS_MODEL_KEY, errors);
			return modelAndView;
		}

		BeanUtils.copyProperties(positionVo, position);
		positionService.doUpdate(position);;

		Map<String, String> params = new HashMap<String, String>();
		params.put(BindResult.SUCCESS, "sys.role.edit.success");
		modelAndView.addObject(BindMessage.SUCCESS, params);
		return modelAndView;
	}
	
	
	/*add by lixiaohaoi*/
	private Boolean isExistByNameEn(String nameEn) {
		Boolean flag = false;
		if (StringUtils.isNotEmpty(nameEn)) {
			flag = positionService.doIsExistByNameEn(nameEn.trim());
		}
		return flag;
	}
	
	@RequestMapping(value="delete")
	@ResponseBody
	public ModelAndView delete(@RequestParam Long id,ModelAndView modelAndView){
		Map<String, String> params = new HashMap<String, String>();
		int updateRow = this.positionService.doDeleteById(id);
		if (updateRow > 0) {
			params.put(BindResult.SUCCESS,"sys.role.delete.success");
		} else {
			params.put(BindResult.SUCCESS,"sys.role.delete.fail");
		}
		modelAndView.addObject(BindMessage.SUCCESS, params);
		return this.getJsonView(modelAndView);
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
	public ModelAndView exportExcel(@RequestParam(required=false) String tableId,@RequestParam(required = false) String sort,@RequestParam(required = false) String order,@ModelAttribute("role") RoleVo roleVo,ModelAndView modelAndView){
		
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
		//查询条件 中文参数名称按模糊查询
		if(StringUtils.isNotEmpty(roleVo.getNameZh())){
			queryCriteria.addQueryCondition("nameZh","%"+roleVo.getNameZh()+"%");
		}

		PageResult<Position> result=positionService.doFindByCriteria(queryCriteria);
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
	
	/*add by lixiaohao*/
	@RequestMapping(value = "checkisexitnameen")
	@ResponseBody
	public String checkIsExistByNameEn(HttpServletRequest servletRequest,
			@RequestParam(required = false) String nameEn,@RequestParam(required=false) Long id) {
		Boolean flag=Boolean.FALSE;
		
		if(id==null){
			flag = this.isExistByNameEn(nameEn)? false : true;
		}else{
			Position position = positionService.doFindById(id);
			if(position==null){
				flag = this.isExistByNameEn(nameEn)? false : true;
			}else{
				flag=this.isExistByNameEn(nameEn, position.getNameEn())?false:true;
			}
		}
		return flag.toString();
	}
	
	/*add by lixiaohao*/
	private Boolean isExistByNameEn(String nameEn, String oldNameEn) {
		Boolean flag = false;
		if (StringUtils.isNotEmpty(nameEn) && !nameEn.equals(oldNameEn)) {
			flag = positionService.doIsExistByNameEn(nameEn.trim());
		}
		return flag;
	}
}
