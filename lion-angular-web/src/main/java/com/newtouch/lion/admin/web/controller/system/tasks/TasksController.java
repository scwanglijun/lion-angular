package com.newtouch.lion.admin.web.controller.system.tasks;

import com.newtouch.lion.admin.web.model.system.tasks.TasksVo;
import com.newtouch.lion.common.date.DateUtil;
import com.newtouch.lion.common.file.FileUtil;
import com.newtouch.lion.data.DataTable;
import com.newtouch.lion.model.datagrid.DataGrid;
import com.newtouch.lion.model.system.Tasks;
import com.newtouch.lion.page.PageResult;
import com.newtouch.lion.query.QueryCriteria;
import com.newtouch.lion.service.datagrid.DataGridService;
import com.newtouch.lion.service.excel.ExcelExportService;
import com.newtouch.lion.service.system.TasksService;
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
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/system/tasks/")
public class TasksController extends AbstractController {
	private final Logger logger = LoggerFactory.getLogger(super.getClass());
	/** 默认排序字段名称 */
	private static final String DEFAULT_ORDER_FILED_NAME = "id";
	/** 任务管理首页 */
	public static final String INDEX_RETURN = "/system/tasks/index";
	/** 添加页面 */
	public static final String ADD_DIALOG_RETURN = "/system/group/adddialog";
	/** 编辑页面 */
	public static final String EDIT_DIALOG_RETURN = "/system/group/editdialog";
	/**DataGrid表格*/
	@Autowired
	private DataGridService dataGridService;
	/**Excel通用导出*/
	@Autowired
	private ExcelExportService excelExportService;
	@Autowired
	private TasksService tasksService;
	
	/** 任务数据添加对话框 */
	@RequestMapping(value = "adddialog")
	public String addDialog() {
		return ADD_DIALOG_RETURN;
	}
	@RequestMapping(value="/index",method=RequestMethod.GET)
	public  String index(){
		
		logger.info("dddd");
		return "system/tasks/index";
	}
	
	/*add by lixiaohao*/
	private Boolean isExistByTasksBeanClass(String beanClass) {
		Boolean flag = false;
		if (StringUtils.isNotEmpty(beanClass)) {
			flag = tasksService.doIsExistByTasksBeanClass(beanClass.trim());
		}
		return flag;
	}
	/*add by lixiaohao*/
	private Boolean isExistByTasksBeanClass(String beanClass, String oldClass) {
		Boolean flag = false;
		if (StringUtils.isNotEmpty(beanClass) && !beanClass.equals(oldClass)) {
			flag = tasksService.doIsExistByTasksBeanClass(beanClass.trim());
		}
		return flag;
	}
	/*add by lixiaohao*/
	@RequestMapping(value = "checkisexitnameen")
	@ResponseBody
	public String checkIsExistByNameEn(HttpServletRequest servletRequest,
			@RequestParam(required = false) String beanClass,@RequestParam(required=false) Long id) {
		Boolean flag=Boolean.FALSE;
		
		if(id==null){
			flag = this.isExistByTasksBeanClass(beanClass)? false : true;
		}else{
			Tasks tasks = tasksService.doFindById(id);
			if(tasks==null){
				flag = this.isExistByTasksBeanClass(beanClass)? false : true;
			}else{
				flag=this.isExistByTasksBeanClass(beanClass, tasks.getBeanClass())?false:true;
			}
		}
		return flag.toString();
	}
	
	/** 任务数据添加保存 */
	@RequestMapping(value = "add")
	@ResponseBody
	public ModelAndView add(@Valid @ModelAttribute("tasks") TasksVo tasksVo,
			Errors errors, ModelAndView modelAndView) {

		if (!errors.hasErrors()&& this.isExistByTasksBeanClass(tasksVo.getBeanClass())) {
			errors.rejectValue(TasksVo.BEAN_CLASS,
					"sys.tasks.form.name.existed.message",
					new Object[] {tasksVo.getBeanClass() }, null);
		}
		//是否错误消息
		if (errors.hasErrors()) {
			modelAndView.addObject(BindMessage.ERRORS_MODEL_KEY, errors);
			return this.getJsonView(modelAndView);
		}
		Tasks tasks = new Tasks();

		BeanUtils.copyProperties(tasksVo, tasks);
		tasksService.doCreate(tasks);
		Map<String, String> params = new HashMap<String, String>();
		params.put(BindResult.SUCCESS, "sys.tasks.add.success");
		modelAndView.addObject(BindMessage.SUCCESS, params);
		return this.getJsonView(modelAndView);
	}

	/** 任务编辑对话框 */
	@RequestMapping(value = "editdialog")
	public String editDialog(@RequestParam Long id, Model model) {
		if (id != null) {
			Tasks tasks = tasksService.doFindById(id);
			model.addAttribute("tasks", tasks);
		} else {
			logger.error("Edit Object id is not null!");
		}
		return EDIT_DIALOG_RETURN;
	}

	/** 编辑任务 */
	@RequestMapping(value = "edit")
	@ResponseBody
	public ModelAndView edit(@Valid @ModelAttribute("tasks") TasksVo tasks,
			Errors errors, ModelAndView modelAndView) {

		modelAndView=this.getJsonView(modelAndView);
		if (!errors.hasErrors() && tasks.getId() == null) {
			errors.reject("sys.tasks.form.id.empty");
			modelAndView.addObject(BindMessage.ERRORS_MODEL_KEY, errors);
			return modelAndView; 
		} 
		Tasks taskss = tasksService.doFindById(tasks.getId());
		if (taskss == null) {
			errors.reject("sys.tasks.form.id.empty");
			return modelAndView;
		}
		
		if (!errors.hasErrors()
			&& this.isExistByTasksBeanClass(tasks.getBeanClass(),taskss.getBeanClass()))
		{errors.rejectValue(TasksVo.BEAN_CLASS,	"sys.tasks.form.nameen.existed.message",new Object[] { tasks.getBeanClass() }, null);

		}

		if (errors.hasErrors()) {
			modelAndView.addObject(BindMessage.ERRORS_MODEL_KEY, errors);
			return modelAndView;
		}

		BeanUtils.copyProperties(tasks, taskss);
		tasksService.doUpdate(taskss);

		Map<String, String> params = new HashMap<String, String>();
		params.put(BindResult.SUCCESS, "sys.tasks.edit.success");
		modelAndView.addObject(BindMessage.SUCCESS, params);
		return modelAndView;
	}
	
	/** 删除任务 */
	@RequestMapping(value = "delete")
	@ResponseBody
	public ModelAndView delete(@RequestParam Long id, ModelAndView modelAndView) {
		Map<String, String> params = new HashMap<String, String>();
		int updateRow = this.tasksService.doDeleteById(id);
		if (updateRow > 0) {
			params.put(BindResult.SUCCESS,"sys.tasks.delete.success");
		} else {
			params.put(BindResult.SUCCESS,"sys.tasks.delete.fail");
		}
		modelAndView.addObject(BindMessage.SUCCESS, params);
		return this.getJsonView(modelAndView);
	}
	
	
	@RequestMapping(value = "list")
	@ResponseBody
	public DataTable<Tasks> list(QueryDt query,@ModelAttribute("tasks") TasksVo tasksVo) {
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
		
		//查询条件 名称按模糊查询
		if(StringUtils.isNotEmpty(tasksVo.getName())){
			queryCriteria.addQueryCondition("name","%"+tasksVo.getName()+"%");
		}

		PageResult<Tasks> pageResult = tasksService.doFindByCriteria(queryCriteria);
		return pageResult.getDataTable(query.getRequestId());
	}
	/****
	 * 
	 * @param 
	 * @param taskss
	 * @param modelAndView
	 * @return
	 */
	@RequestMapping(value = "export")
	@ResponseBody
	public ModelAndView exportExcel(@RequestParam(required=false) String tableId,@RequestParam(required = false) String sort,@RequestParam(required = false) String order,@ModelAttribute("tasks") TasksVo tasksVo,ModelAndView modelAndView){
		
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
		//查询条件 名称按模糊查询
		if(StringUtils.isNotEmpty(tasksVo.getName())){
			queryCriteria.addQueryCondition("name","%"+tasksVo.getName()+"%");
		}

		PageResult<Tasks> result=tasksService.doFindByCriteria(queryCriteria);
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
