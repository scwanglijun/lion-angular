
/*
* Copyright (c)  2015, NewTouch
* All rights reserved. 
*
* $id: IconController.java 9552 Mar 4, 2015 1:38:57 PM MaoJiaWei$
*/
package com.newtouch.lion.admin.web.controller.system.icon;

import com.newtouch.lion.admin.web.model.system.icon.IconVo;
import com.newtouch.lion.data.DataTable;
import com.newtouch.lion.model.system.Icon;
import com.newtouch.lion.page.PageResult;
import com.newtouch.lion.query.QueryCriteria;
import com.newtouch.lion.service.datagrid.DataGridService;
import com.newtouch.lion.service.excel.ExcelExportService;
import com.newtouch.lion.service.system.IconService;
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
 * Title: 图标管理
 * </p>
 * <p>
 * Description: 图标管理包含添加、删除、修改及用户组授权等
 * </p>
 * <p>
 * Copyright: Copyright (c) 2015
 * </p>
 * <p>
 * Company: NewTouch
 * </p>
 * 
 * @author MaoJiaWei
 * @version 1.0
 */
@Controller
@RequestMapping("/system/icon/")
public class IconController extends AbstractController{
	private final Logger logger = LoggerFactory.getLogger(super.getClass());
	/** 默认排序字段名称 */
	private static final String DEFAULT_ORDER_FILED_NAME = "id";
	/** 默认列表 */
	public static final String INDEX_LISTS_TB = "sys_icon_tb";
	/** 图标管理首页 */
	public static final String INDEX_RETURN = "/system/icon/index";
	/** 添加页面 */
	public static final String ADD_DIALOG_RETURN = "/system/group/adddialog";
	/** 编辑页面 */
	public static final String EDIT_DIALOG_RETURN = "/system/group/editdialog";
	@Autowired
	private IconService iconService;
	/**DataGrid表格*/
	@Autowired
	private DataGridService dataGridService;
	/**Excel通用导出*/
	@Autowired
	private ExcelExportService excelExportService;
	
	@RequestMapping(value = "index")
	public String index() {
		return INDEX_RETURN;
	}
	@RequestMapping(value = "icon")
	public String icon() {
		return "system/icon/icon";
	}
	/** 图标数据添加对话框 */
	@RequestMapping(value = "adddialog")
	public String addDialog() {
		return ADD_DIALOG_RETURN;
	}
	/*add by maojiawei*/
	private Boolean isExistByIconClass(String iconClass) {
		Boolean flag = false;
		if (StringUtils.isNotEmpty(iconClass)) {
			flag = iconService.doIsExistByIconClass(iconClass.trim());
		}
		return flag;
	}
	/*add by maojiawei*/
	private Boolean isExistByIconClass(String iconClass, String oldClass) {
		Boolean flag = false;
		if (StringUtils.isNotEmpty(iconClass) && !iconClass.equals(oldClass)) {
			flag = iconService.doIsExistByIconClass(iconClass.trim());
		}
		return flag;
	}
	/*add by maojiawei*/
	@RequestMapping(value = "checkisexitnameen")
	@ResponseBody
	public String checkIsExistByNameEn(HttpServletRequest servletRequest,
			@RequestParam(required = false) String iconClass,@RequestParam(required=false) Long id) {
		Boolean flag=Boolean.FALSE;
		
		if(id==null){
			flag = this.isExistByIconClass(iconClass)? false : true;
		}else{
			Icon icon = iconService.doFindById(id);
			if(icon==null){
				flag = this.isExistByIconClass(iconClass)? false : true;
			}else{
				flag=this.isExistByIconClass(iconClass, icon.getIconClass())?false:true;
			}
		}
		return flag.toString();
	}
	
	/** 图标数据添加保存 */
	@RequestMapping(value = "add")
	@ResponseBody
	public ModelAndView add(@Valid @ModelAttribute("icon") IconVo iconVo,
			Errors errors, ModelAndView modelAndView) {

		if (!errors.hasErrors()&& this.isExistByIconClass(iconVo.getIconClass())) {
			errors.rejectValue(IconVo.ICON_CLASS,
					"sys.icon.form.nameen.existed.message",
					new Object[] { iconVo.getIconClass() }, null);
		}
		//是否错误消息
		if (errors.hasErrors()) {
			modelAndView.addObject(BindMessage.ERRORS_MODEL_KEY, errors);
			return this.getJsonView(modelAndView);
		}
		Icon icon = new Icon();

		BeanUtils.copyProperties(iconVo, icon);
		iconService.doCreate(icon);
		Map<String, String> params = new HashMap<String, String>();
		params.put(BindResult.SUCCESS, "sys.icon.add.success");
		modelAndView.addObject(BindMessage.SUCCESS, params);
		return this.getJsonView(modelAndView);
	}

	/** 图标编辑对话框 */
	@RequestMapping(value = "editdialog")
	public String editDialog(@RequestParam Long id, Model model) {
		if (id != null) {
			Icon icon = iconService.doFindById(id);
			model.addAttribute("icon", icon);
		} else {
			logger.error("Edit Object id is not null!");
		}
		return EDIT_DIALOG_RETURN;
	}

	/** 编辑图标 */
	@RequestMapping(value = "edit")
	@ResponseBody
	public ModelAndView edit(@Valid @ModelAttribute("icon") IconVo iconVo,
			Errors errors, ModelAndView modelAndView) {

		modelAndView=this.getJsonView(modelAndView);
		if (!errors.hasErrors() && iconVo.getId() == null) {
			errors.reject("sys.icon.form.id.empty");
			modelAndView.addObject(BindMessage.ERRORS_MODEL_KEY, errors);
			return modelAndView; 
		} 
		Icon icon = iconService.doFindById(iconVo.getId());
		if (icon == null) {
			errors.reject("sys.icon.form.id.empty");
			return modelAndView;
		}
		
		if (!errors.hasErrors()
			&& this.isExistByIconClass(iconVo.getIconClass(),icon.getIconClass())) {errors.rejectValue(IconVo.ICON_CLASS,	"sys.icon.form.nameen.existed.message",new Object[] { iconVo.getIconClass() }, null);

		}

		if (errors.hasErrors()) {
			modelAndView.addObject(BindMessage.ERRORS_MODEL_KEY, errors);
			return modelAndView;
		}

		BeanUtils.copyProperties(iconVo, icon);
		iconService.doUpdate(icon);

		Map<String, String> params = new HashMap<String, String>();
		params.put(BindResult.SUCCESS, "sys.icon.edit.success");
		modelAndView.addObject(BindMessage.SUCCESS, params);
		return modelAndView;
	}
	
	/** 删除图标 */
	@RequestMapping(value = "delete")
	@ResponseBody
	public ModelAndView delete(@RequestParam Long id, ModelAndView modelAndView) {
		Map<String, String> params = new HashMap<String, String>();
		int updateRow = this.iconService.doDeleteById(id);
		if (updateRow > 0) {
			params.put(BindResult.SUCCESS,"sys.icon.delete.success");
		} else {
			params.put(BindResult.SUCCESS,"sys.icon.delete.fail");
		}
		modelAndView.addObject(BindMessage.SUCCESS, params);
		return this.getJsonView(modelAndView);
	}

	@RequestMapping(value = "list")
	@ResponseBody
	public DataTable<Icon> list(QueryDt query,@ModelAttribute("icon") IconVo iconVo) {
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
		
		//查询条件 图标类名按模糊查询
		if(StringUtils.isNotEmpty(iconVo.getIconClass())){
			queryCriteria.addQueryCondition("iconClass","%"+iconVo.getIconClass()+"%");
		}
		
		//查询条件 图标类型
		if(StringUtils.isNotEmpty(iconVo.getIconType())){
			queryCriteria.addQueryCondition("iconType",iconVo.getIconType());
		}

		PageResult<Icon> pageResult = iconService.doFindByCriteria(queryCriteria);
		return pageResult.getDataTable(query.getRequestId());
	}
	
}

	