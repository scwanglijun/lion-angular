/*
* Copyright (c)  2015, Newtouch
* All rights reserved. 
*
* $id: DataTableController.java 9552 2015年2月8日 下午8:43:15 WangLijun$
*/
package com.newtouch.lion.admin.web.controller.datatable;

import com.newtouch.lion.admin.web.model.system.user.UserVo;
import com.newtouch.lion.data.DataTable;
import com.newtouch.lion.model.system.User;
import com.newtouch.lion.page.PageResult;
import com.newtouch.lion.query.QueryCriteria;
import com.newtouch.lion.service.system.UserService;
import com.newtouch.lion.web.controller.AbstractController;
import com.newtouch.lion.web.model.Columns;
import com.newtouch.lion.web.model.Order;
import com.newtouch.lion.web.model.QueryDt;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;

/**
 * <p>
 * Title: DataTable Demo
 * </p>
 * <p>
 * Description: DataTable Demo
 * </p>
 * <p>
 * Copyright: Copyright (c) 2015
 * </p>
 * <p>
 * Company: Newtouch
 * </p>
 * 
 * @author WangLijun
 * @version 1.0
 */
@Controller("dataTableController")
@RequestMapping("/sys/dt/")
public class DataTableController extends AbstractController {
	
	/** 默认排序字段 */
	private static final String DEFAULT_ORDER_FILED_NAME = "id";
	
	private static final String INDEX_RETURN="/datatable/index";
	
	private static final String DEMO_RETURN="/datatable/demo";
	
	@Autowired
	private UserService userService;
	
	@RequestMapping("index")
	public String index(){
		 return INDEX_RETURN;
	}
	
	
	@RequestMapping("demo")
	public String demo(){
		 return DEMO_RETURN;
	}
	@RequestMapping("list")
	@ResponseBody
	public DataTable<User> list(QueryDt query,ArrayList<Columns> columns,@ModelAttribute("user") UserVo userVo,HttpServletRequest request){
		
		
		
		logger.info(":{}",query.toString());
		
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
		}
		 
		// 查询条件 参数类型 用户名
		if (StringUtils.isNotEmpty(userVo.getUsername())) {
			queryCriteria.addQueryCondition("username", "%" + userVo.getUsername() + "%");
		}
		
		// 查询条件 参数类型 员工号
		if (StringUtils.isNotEmpty(userVo.getEmployeeCode())) {
			queryCriteria.addQueryCondition("employeeCode", "%" + userVo.getEmployeeCode() + "%");
		}
		// 查询条件 参数类型 邮箱
		if (StringUtils.isNotEmpty(userVo.getEmail())) {
			queryCriteria.addQueryCondition("email", "%" + userVo.getEmail()+ "%");
		}
		
		PageResult<User> pageResult = this.userService.doFindByCriteria(queryCriteria);
		
		for(User user:pageResult.getContent()){
				user.getDepartment().getNameZh();
		}
		return pageResult.getDataTable(query.getRequestId());
	}
	@RequestMapping("/test")
	@ResponseBody
	public  DataTable<User>  test(ArrayList<Order> order){
		QueryCriteria queryCriteria = new QueryCriteria();
		// 设置分页 启始页
		queryCriteria.setStartIndex(1);
		// 每页大小
		queryCriteria.setPageSize(10);
		 
		queryCriteria.setOrderField(DEFAULT_ORDER_FILED_NAME);		
		
		PageResult<User> pageResult = this.userService.doFindByCriteria(queryCriteria);
		for(User user:pageResult.getContent()){
				user.getDepartment().getNameZh();
		}
		return pageResult.getDataTable();
		
	} 
}

	