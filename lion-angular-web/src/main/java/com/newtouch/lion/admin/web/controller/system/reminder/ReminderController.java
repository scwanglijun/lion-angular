
/*
* Copyright (c)  2015, NewTouch
* All rights reserved. 
*
* $id: reminderController.java 9552 2015年3月31日 下午12:11:55 ZhongMengDie$
*/
package com.newtouch.lion.admin.web.controller.system.reminder;

import com.newtouch.lion.admin.web.model.system.reminder.ReminderBodyVo;
import com.newtouch.lion.data.DataTable;
import com.newtouch.lion.model.system.ReminderBody;
import com.newtouch.lion.page.PageResult;
import com.newtouch.lion.query.QueryCriteria;
import com.newtouch.lion.service.datagrid.DataGridService;
import com.newtouch.lion.service.system.ReminderBodyService;
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
import java.util.Map;


/**
 * <p>
 * Title: 消息管理类
 * </p>
 * <p>
 * Description: 消息管理类
 * </p>
 * <p>
 * Copyright: Copyright (c) 2015
 * </p>
 * <p>
 * Company: NewTouch
 * </p>
 * 
 * @author ZhongMengDie
 * @version 1.0
 */
@Controller
@RequestMapping("/system/reminder/")
public class ReminderController extends AbstractController{
	
	private final Logger logger = LoggerFactory.getLogger(super.getClass());
	/** 默认排序字段名称 */
	private static final String DEFAULT_ORDER_FILED_NAME = "id";
	/** 默认列表 */
	public static final String INDEX_LISTS_TB = "sys_reminder_tb";
	/** 添加页面 */
	public static final String ADD_DIALOG_RETURN = "/system/group/adddialog";
	/** 编辑页面 */
	public static final String EDIT_DIALOG_RETURN = "/system/group/editdialog";
	/** 消息管理首页 */
	public static final String INDEX_RETURN = "/system/reminder/index";
	@Autowired
	private DataGridService dataGridService;
	
	@Autowired
	private ReminderBodyService reminderService;

	@RequestMapping(value = "index")
	public String index() {
		return INDEX_RETURN;
	}
	/** 消息添加对话框 */
	@RequestMapping(value = "adddialog")
	public String addDialog() {
		return ADD_DIALOG_RETURN;
	}
	/**消息编辑对话框*/
	@RequestMapping(value = "edit")
	@ResponseBody
	public ModelAndView edit(
			@Valid @ModelAttribute("reminder") ReminderBodyVo reminderVo,
			Errors errors,ModelAndView modelAndView){
		modelAndView=this.getJsonView(modelAndView);
		//判断传入的ID是否为空
		if (!errors.hasErrors() && reminderVo.getId() == null) {
			errors.reject("sys.reminder.form.id.empty");
			modelAndView.addObject(BindMessage.ERRORS_MODEL_KEY, errors);
			return modelAndView;
		}
		//判断传入的实体类是否已经存在
		ReminderBody reminder = reminderService.doFindById(reminderVo.getId());
		if (reminder == null) {
			errors.reject("sys.reminder.form.id.empty");
			return modelAndView;
		}
		
		if (errors.hasErrors()) {
			modelAndView.addObject(BindMessage.ERRORS_MODEL_KEY, errors);
			return  this.getJsonView(modelAndView);
		}

		BeanUtils.copyProperties(reminderVo, reminder);
		reminderService.doUpdate(reminder);

		Map<String, String> params = new HashMap<String, String>();
		params.put(BindResult.SUCCESS, "sys.reminder.edit.success");
		modelAndView.addObject(BindMessage.SUCCESS, params);
		return this.getJsonView(modelAndView);
	}
	
	@RequestMapping(value = "checkisexitnameen")
	@ResponseBody
	public String checkIsExistByNameEn(HttpServletRequest servletRequest,
			@RequestParam(required = false) String reminderClass,@RequestParam(required=false) Long id) {
		Boolean flag=Boolean.FALSE;
		
		if(id==null){
			flag = this.isExistByReminderClass(reminderClass)? false : true;
		}else{
			ReminderBody reminder = reminderService.doFindById(id);
			if(reminder==null){
				flag = this.isExistByReminderClass(reminderClass)? false : true;
			}else{
				flag=this.isExistByReminderClass(reminderClass, reminder.getTitle())?false:true;
			}
		}
		return flag.toString();
	}
	private Boolean isExistByReminderClass(String reminderClass) {
		Boolean flag = false;
		if (StringUtils.isNotEmpty(reminderClass)) {
			flag = reminderService.doIsExistByReminderBodyClass(reminderClass.trim());
		}
		return flag;
	}
	private Boolean isExistByReminderClass(String reminderClass, String oldClass) {
		Boolean flag = false;
		if (StringUtils.isNotEmpty(reminderClass) && !reminderClass.equals(oldClass)) {
			flag = reminderService.doIsExistByReminderBodyClass(reminderClass.trim());
		}
		return flag;
	}
	/** 消息数据添加保存 */
	@RequestMapping(value = "add")
	@ResponseBody
	public ModelAndView add(@Valid @ModelAttribute("reminder") ReminderBodyVo remidnerVo,
			Errors errors, ModelAndView modelAndView) {

//		if (!errors.hasErrors()&& this.isExistByReminderClass(remidnerVo.getTitle())) {
//			errors.rejectValue(ReminderBodyVo.ReminderBody_Class,
//					"sys.reminder.form.namen.existed.message",
//					new Object[] { remidnerVo.getTitle() }, null);
//		}
		//是否错误消息
		if (errors.hasErrors()) {
			modelAndView.addObject(BindMessage.ERRORS_MODEL_KEY, errors);
			return this.getJsonView(modelAndView);
		}
		ReminderBody reminder = new ReminderBody();

		BeanUtils.copyProperties(remidnerVo, reminder);
		
		reminderService.doCreate(reminder);

		Map<String, String> params = new HashMap<String, String>();
		params.put(BindResult.SUCCESS, "sys.reminder.add.success");
		modelAndView.addObject(BindMessage.SUCCESS, params);
		return this.getJsonView(modelAndView);
	}
	/** 消息编辑对话框 */
	@RequestMapping(value = "editdialog")
	public String editDialog(@RequestParam Long id, Model model) {
		if (id != null) {
			ReminderBody reminder = reminderService.doFindById(id);
			model.addAttribute("reminder", reminder);
		} else {
			logger.error("Edit Object id is not null!");
		}
		return EDIT_DIALOG_RETURN;
	}
	/** 删除消息 */
	@RequestMapping(value = "delete")
	@ResponseBody
	public ModelAndView delete(@RequestParam Long id, ModelAndView modelAndView) {
		Map<String, String> params = new HashMap<String, String>();
		int updateRow = this.reminderService.doDeleteById(id);
		if (updateRow > 0) {
			params.put(BindResult.SUCCESS,"sys.reminder.delete.success");
		} else {
			params.put(BindResult.SUCCESS,"sys.reminder.delete.fail");
		}
		modelAndView.addObject(BindMessage.SUCCESS, params);
		return this.getJsonView(modelAndView);
	}
	/**
	 * 分页
	 * 首页
	 */
	@RequestMapping(value = "list")
	@ResponseBody
	public DataTable<ReminderBody> list(QueryDt query,@ModelAttribute("reminder") ReminderBodyVo reminderVo) {
		QueryCriteria queryCriteria = new QueryCriteria();

		
		// 设置分页 启始页
		queryCriteria.setStartIndex(query.getPage());
		// 每页大小
		
		queryCriteria.setPageSize(query.getRows());
		// 设置排序字段及排序方向
		if (StringUtils.isNotEmpty(query.getSort()) && StringUtils.isNotEmpty(query.getOrder())) {
			queryCriteria.setOrderField(query.getSort());//query.getSort() 得到的值是 title
			queryCriteria.setOrderDirection(query.getOrder());//desc
		} else {
			queryCriteria.setOrderField(DEFAULT_ORDER_FILED_NAME);
			queryCriteria.setOrderDirection(QueryCriteria.ASC);
		}
		
		//查询条件 消息标题
		if(StringUtils.isNotEmpty(reminderVo.getTitle())){
			queryCriteria.addQueryCondition("title","%"+reminderVo.getTitle()+"%");
		}
//		System.out.println("title:============="+reminderVo.getTitle());

		//查询条件 消息类型
		if(StringUtils.isNotEmpty(reminderVo.getTitle())){
		
			queryCriteria.addQueryCondition("reminderType","%"+reminderVo.getReminderType()+"%");
		}

		PageResult<ReminderBody> pageResult = reminderService.doFindByCriteria(queryCriteria);
		return pageResult.getDataTable(query.getRequestId());
	}
		

}

	