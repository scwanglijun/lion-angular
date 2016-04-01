
/*
* Copyright (c)  2016, Newtouch
* All rights reserved. 
*
* $id: UserController.java 9552 2016年3月30日 上午9:54:07 LiJie$
*/
package com.newtouch.lion.admin.web.controller.user; 

import com.newtouch.lion.admin.web.model.user.*;
import com.newtouch.lion.model.system.User;
import com.newtouch.lion.page.PageResult;
import com.newtouch.lion.query.QueryCriteria;
import com.newtouch.lion.service.system.UserService;
import com.newtouch.lion.web.page.Page;
import com.newtouch.lion.webtrans.trans.Trans;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.HashMap;
import java.util.Map;

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
	//添加用户信息
	/** add*/
	public UserAddResp add(UserAddReq req){
		User user = new User();
		BeanUtils.copyProperties(req, user);
		userService.doCreateUser(user);
		return new UserAddResp(UserAddResp.SUCCESS_ROLE_ADD_CODE,UserAddResp.SUCCESS_ROLE_ADD_MESSAGE);
	}


     //删除用户信息
    /** delete*/
	@Trans("system.user.delete")
	public UserDelResp del(UserDelReq req){
		int updateRow = this.userService.doDeleteById(req.getId());
		if (updateRow>0) {
			return new UserDelResp(UserDelResp.SUCCESS_ROLE_DELETE_CODE, UserDelResp.SUCCESS_ROLE_DELETE_MESSAGE);
		}else {
			return new UserDelResp(UserDelResp.FAIL_ROLE_DELETE_CODE, UserDelResp.FAIL_ROLE_DELETE_MESSAGE);

		}
	}
	//编辑用户信息
	/**edit*/
	@Trans("system.user.edit")
	public UserEditResp edit(UserEditReq req){
		Map<String, String> params = new HashMap<String, String>();
		User user = userService.doFindById(req.getId());
		if(user==null){
			return new UserEditResp(UserEditResp.FAIL_ROLE_EDIT_CODE,UserEditResp.FAIL_ROLE_EDIT_MESSAGE);
		}
		BeanUtils.copyProperties(req, user);
		userService.doUpdate(user);
		return new UserEditResp(UserEditResp.SUCCESS_ROLE_EDIT_CODE,UserEditResp.SUCCESS_ROLE_EDIT_MESSAGE);
	}
}
	