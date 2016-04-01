
/*
* Copyright (c)  2016, Newtouch
* All rights reserved. 
*
* $id: UserController.java 9552 2016年3月30日 上午9:54:07 LiJie$
*/
package com.newtouch.lion.admin.web.controller.user; 

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.newtouch.lion.admin.web.model.user.UserGetReq;
import com.newtouch.lion.model.system.User;
import com.newtouch.lion.page.PageResult;
import com.newtouch.lion.query.QueryCriteria;
import com.newtouch.lion.service.system.UserService;
import com.newtouch.lion.web.page.Page;
import com.newtouch.lion.webtrans.trans.Trans;

/**
 * <p>
 * Title: 
 * </p>
 * <p>
 * Description: 
 * </p>
 * <p>
 * Copyright: Copyright (c) 2016
 * </p>
 * <p>
 * Company: Newtouch
 * </p>
 * 
 * @author LiJie
 * @version 1.0
 */
@Controller
public class UserController {
	@Autowired
	private UserService userService;

	/** 默认排序字段*/
	private static final String DEFAULT_ORDER_FILED_NAME="id";
	 
	    @Trans("system.user.list")
	    public Page<User> list(UserGetReq req) {
	    	System.out.println("------------------------");
	        QueryCriteria queryCriteria = new QueryCriteria();

	        // 设置分页 启始页
	        queryCriteria.setStartIndex(req.getPage().getPageNumber()-1);
	        // 每页大小
	        queryCriteria.setPageSize(req.getPage().getPageSize());

	        // 查询条件 
	        if (StringUtils.isNotEmpty(req.getEmployeeCode())) {
				queryCriteria.addQueryCondition("id", "%"+req.getEmployeeCode()+"%");
			}
			if (StringUtils.isNotEmpty(req.getEmployeeCode())) {
				queryCriteria.addQueryCondition("username", "%"+req.getEmployeeCode()+"%");
			}

	        PageResult<User> pageResult = userService.doFindByCriteria(queryCriteria);
	        System.out.print(pageResult.getContent());
	        return new Page(pageResult);
	    }
	}
	