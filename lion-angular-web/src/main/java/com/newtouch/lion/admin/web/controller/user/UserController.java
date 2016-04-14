
/*
* Copyright (c)  2016, Newtouch
* All rights reserved. 
*
* $id: UserController.java 9552 2016年3月30日 上午9:54:07 LiJie$
*/
package com.newtouch.lion.admin.web.controller.user; 

import com.newtouch.lion.admin.web.model.user.*;
import com.newtouch.lion.common.date.DateUtil;
import com.newtouch.lion.model.system.User;
import com.newtouch.lion.page.PageResult;
import com.newtouch.lion.query.QueryCriteria;
import com.newtouch.lion.service.system.UserService;
import com.newtouch.lion.web.page.Page;
import com.newtouch.lion.web.util.QueryUtils;
import com.newtouch.lion.webtrans.trans.Trans;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.ArrayList;
import java.util.List;

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

			QueryCriteria queryCriteria = QueryUtils.pageFormat(new QueryCriteria() ,req);

//	        QueryCriteria queryCriteria = new QueryCriteria();
//
//	        // 设置分页 启始页
//	        queryCriteria.setStartIndex(req.getPage().getPageNumber()-1);
//	        // 每页大小
//	        queryCriteria.setPageSize(req.getPage().getPageSize());
//			// 设置排序字段及排序方向
//			if (req.getSort()==null){
//				queryCriteria.setOrderField(DEFAULT_ORDER_FILED_NAME);
//				queryCriteria.setOrderDirection(QueryCriteria.ASC);
//			}else {
//				if (StringUtils.isNotEmpty(req.getSort().getSort()) && StringUtils.isNotEmpty(req.getSort().getOrder())){
//					queryCriteria.setOrderField(req.getSort().getOrder());
//					queryCriteria.setOrderDirection(req.getSort().getSort());
//				}else {
//					queryCriteria.setOrderField(DEFAULT_ORDER_FILED_NAME);
//					queryCriteria.setOrderDirection(QueryCriteria.ASC);
//				}
//			}
	        // 查询条件
	        if (StringUtils.isNotEmpty(req.getRealnameZh())) {
				queryCriteria.addQueryCondition("RealnameZh", "%"+req.getRealnameZh()+"%");
			}
//			if (StringUtils.isNotEmpty(req.getUsername())) {
//				queryCriteria.addQueryCondition("username", "%"+req.getUsername()+"%");
//			}

	        PageResult<User> pageResult = userService.doFindByCriteria(queryCriteria);

			List<UserGetResp> list = new ArrayList<UserGetResp>();
			for (User user : pageResult.getContent()){
				UserGetResp userGetResp = new UserGetResp();
				BeanUtils.copyProperties(user,userGetResp);
				String createDate = DateUtil.formatDate(user.getCreatedDate(),DateUtil.FORMAT_DATE_SLASH_YYYY_MM_DD);
				String updateDate = DateUtil.formatDate(user.getUpdatedDate(),DateUtil.FORMAT_DATE_SLASH_YYYY_MM_DD);
//				userGetResp.get
				userGetResp.setCreatedDate(createDate);
//				userGetResp.setUpdatedDate(updateDate);
				list.add(userGetResp);
			}
			PageResult<UserGetResp> pageResultResp = new PageResult<UserGetResp>();
			BeanUtils.copyProperties(pageResult,pageResultResp);
			pageResultResp.setContent(list);

	        return new Page(pageResultResp);

	    }
	//添加用户信息
	/** add*/
	@Trans("system.user.add")
	public UserAddResp add(UserAddReq req){
		User user = new User();
		BeanUtils.copyProperties(user, req);
		userService.doCreateUser(user);
		return new UserAddResp(UserAddResp.SUCCESS_USER_ADD_CODE,UserAddResp.SUCCESS_USER_ADD_MESSAGE);
	}

     //删除用户信息

	@Override
	public String toString() {
		return super.toString();
	}

	/** delete*/
	@Trans("system.user.delete")
	public UserDelResp del(UserDelReq req){
		int updateRow = this.userService.doDeleteById(req.getId());
		if (updateRow>0) {
			return new UserDelResp(UserDelResp.SUCCESS_USER_DELETE_CODE, UserDelResp.SUCCESS_USER_DELETE_MESSAGE);
		}else {
			return new UserDelResp(UserDelResp.FAIL_USER_DELETE_CODE, UserDelResp.FAIL_USER_DELETE_MESSAGE);
		}
	}

	//编辑用户信息
	/**edit*/
	@Trans("system.user.edit")
	public UserEditResp edit(UserAddReq req){

		User user = userService.doFindByEmpolyeeCode(req.getEmployeeCode());
		if(user==null){
			return new UserEditResp(UserEditResp.FAIL_USER_EDIT_CODE,UserEditResp.FAIL_USER_EDIT_MESSAGE);
		}
		BeanUtils.copyProperties(req, user);
		userService.doUpdate(user);
		return new UserEditResp(UserEditResp.SUCCESS_USER_EDIT_CODE,UserEditResp.SUCCESS_USER_EDIT_MESSAGE);
	}
}
