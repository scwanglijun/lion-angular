/*
 * Copyright (c)  2014, NewTouch
 * All rights reserved. 
 *
 * $id: ResourcecController.java 9552 2014-2-17 下午03:38:18 WangLijun$
 */
package com.newtouch.lion.admin.web.controller.system.resource;

import com.newtouch.lion.admin.web.model.system.resource.ResourceVo;
import com.newtouch.lion.common.codelist.CodeListConstant;
import com.newtouch.lion.common.date.DateUtil;
import com.newtouch.lion.common.file.FileUtil;
import com.newtouch.lion.common.user.UserInfo;
import com.newtouch.lion.json.JSONParser;
import com.newtouch.lion.model.datagrid.DataGrid;
import com.newtouch.lion.model.system.Resource;
import com.newtouch.lion.query.QueryCriteria;
import com.newtouch.lion.service.datagrid.DataGridService;
import com.newtouch.lion.service.excel.ExcelExportService;
import com.newtouch.lion.service.system.TreeResourceService;
import com.newtouch.lion.tree.TreeNode;
import com.newtouch.lion.util.ResourceConvertUtil;
import com.newtouch.lion.util.ResourceTreeUtil;
import com.newtouch.lion.web.constant.ConstantMessage;
import com.newtouch.lion.web.controller.AbstractController;
import com.newtouch.lion.web.model.QueryVo;
import com.newtouch.lion.web.servlet.view.support.BindMessage;
import com.newtouch.lion.web.servlet.view.support.BindResult;
import com.newtouch.lion.web.shiro.chain.FilterChainDefinitions;
import com.newtouch.lion.web.shiro.session.LoginSecurityUtil;
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
 * Title: 资源控制类
 * </p>
 * <p>
 * Description: 资源控制类
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
@Controller(value = "sysResourceController")
@RequestMapping("/system/resource/")
public class ResourceController extends AbstractController{
	/**日志*/
	private final Logger logger = LoggerFactory.getLogger(super.getClass());
	/**首页*/
	private static final String INDEX_RETURN = "/system/resource/index";
	/**Excel导出表*/
	private static final String INDEX_TREE_TB = "sys_resource_lists_tb";
 
	/**资源Service*/
	@Autowired
	private TreeResourceService resourceService;
	/**Excel通用导出*/
	@Autowired
	private ExcelExportService excelExportService;
	/**DataGrid表格*/
	@Autowired
	private DataGridService dataGridService;
	/***权限拦截更新*/
	@Autowired
	private FilterChainDefinitions filterChainDefinitions; 
	

	@RequestMapping(value = "add")
	@ResponseBody
	public ModelAndView add(
			@Valid @ModelAttribute("resourceVo") ResourceVo resourceVo,
			Errors errors, ModelAndView modelAndView) {
		if (errors.hasErrors()) {
			modelAndView.addObject(BindMessage.ERRORS_MODEL_KEY, errors);
			return this.getJsonView(modelAndView);
		}
		Resource resource = new Resource();
		BeanUtils.copyProperties(resourceVo, resource);
		this.resourceService.doCreateResource(resource);
		Map<String, String> params = new HashMap<String, String>();
		params.put(BindResult.SUCCESS, ConstantMessage.ADD_SUCCESS_MESSAGE_CODE);
		modelAndView.addObject(BindMessage.SUCCESS, params);
		this.updatePermission();
		return this.getJsonView(modelAndView);
	}
	/***
	 * 更新资源拦截器
	 */
	private void updatePermission(){
		//TODO 集群更新 (单台更新)
		filterChainDefinitions.updatePermission();
	}
	
	@RequestMapping(value = "edit")
	@ResponseBody
	public ModelAndView edit(
			@Valid @ModelAttribute("resourceVo") ResourceVo resourceVo,
			Errors errors, ModelAndView modelAndView) {
		Resource resource = null;
		if (resourceVo.getId() != null) {
			resource = this.resourceService.doFindById(resourceVo.getId());
			if (resource == null) {
				errors.reject(ConstantMessage.EDIT_ISEMPTY_FAIL_MESSAGE_CODE);
			}
		} else {
			errors.reject(ConstantMessage.EDIT_ISEMPTY_FAIL_MESSAGE_CODE);
		}

		if (errors.hasErrors()) {
			modelAndView.addObject(BindMessage.ERRORS_MODEL_KEY, errors);
			return this.getJsonView(modelAndView);
		}
		BeanUtils.copyProperties(resourceVo, resource);
		this.resourceService.doUpdate(resource);
		Map<String, String> params = new HashMap<String, String>();
		params.put(BindResult.SUCCESS,ConstantMessage.EDIT_SUCCESS_MESSAGE_CODE);
		modelAndView.addObject(BindMessage.SUCCESS, params);
		this.updatePermission();
		return this.getJsonView(modelAndView);
	}

	@RequestMapping(value = "delete")
	@ResponseBody
	public ModelAndView delete(@RequestParam Long id, ModelAndView modelAndView) {
		Map<String, String> params = new HashMap<String, String>();
		Resource resource = this.resourceService.doDelete(id);
		if (resource != null) {
			params.put(BindResult.SUCCESS,ConstantMessage.DELETE_SUCCESS_MESSAGE_CODE);
		} else {
			params.put(BindResult.SUCCESS,ConstantMessage.DELETE_FAIL_MESSAGE_CODE);
		}
		modelAndView.addObject(BindMessage.SUCCESS, params);
		return this.getJsonView(modelAndView);
	}

	
	@RequestMapping(value = "menutree")
	@ResponseBody
	public String menutree(HttpServletRequest request,@RequestParam Long resourceId, Model model) {
		UserInfo userInfo =LoginSecurityUtil.getUser();
		List<Resource> resources=resourceService.doFindByParentId(resourceId);
		String[]  menuResourceType={CodeListConstant.RESTYPE_MODULE_MENU_CATEGORY,CodeListConstant.RESTYPE_MODULE_CATEGORY_ITEM};
		List<Resource>  userResources=resourceService.doFindByUserIdAndType(userInfo.getId(), menuResourceType);
		Map<Long,Resource>  menuResourcesMap=ResourceConvertUtil.convertListToMap(userResources);
		List<TreeNode> children=ResourceTreeUtil.resourceAttr(resources, menuResourcesMap,Boolean.TRUE);
		Set<String>  properties=new HashSet<String>();		
		properties.add("id");		
		properties.add("text");		
		properties.add("checked");	
		properties.add("state");		
		properties.add("path");
		properties.add("permission");	
		properties.add("attributes");		
		properties.add("children");
		String result=JSONParser.toJSONString(children,properties);
		return result;
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
	public ModelAndView exportExcel(@ModelAttribute("queryVo")QueryVo queryVo,@ModelAttribute("resource") ResourceVo resourceVo,Errors errors, ModelAndView modelAndView){
				
		DataGrid dataGrid=dataGridService.doFindByTableIdAndSort(queryVo.getTableId());
		QueryCriteria queryCriteria=new QueryCriteria();
		queryCriteria.setPageSize(10000);
	 
		// 设置分页 启始页
		queryCriteria.setStartIndex(queryVo.getRows() * (queryVo.getPage() - 1));
		// 每页大小
		queryCriteria.setPageSize(queryVo.getRows());	 
	 

	    List<Resource> result=this.resourceService.doFindAll();
		Map<String, Map<Object, Object>> fieldDepartment= new HashMap<String, Map<Object, Object>>();
		fieldDepartment.put("resource",null);

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
	
	/***
	 * 树结构
	 * @return
	 */
	@RequestMapping(value = "combotree")
	@ResponseBody
	public List<com.newtouch.lion.ztree.TreeNode> comboxTree() {
		return  this.resourceService.doFindResourceToTree();
	}

	@RequestMapping(value = "index")
	public String index(HttpServletRequest request) {
		return INDEX_RETURN;
	}
	
	@RequestMapping(value = "list")
	@ResponseBody
	public ModelAndView lists(ModelAndView modelAndView) {		 
		String str=resourceService.doFindAllToTree(INDEX_TREE_TB);
		str=str.replace("resources","children").replace("parentResourceId", "_parentId");
		return this.getStrJsonView(str, modelAndView);
	}

}
