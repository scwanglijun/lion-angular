/*
* Copyright (c)  2013, Newtouch
* All rights reserved. 
*
* $id: LoginLogService.java 9552 2013-1-12 下午1:48:23 WangLijun$
*/
package com.newtouch.lion.service.system; 

import com.newtouch.lion.model.system.LoginLog;
import com.newtouch.lion.model.system.LoginLogGroup;
import com.newtouch.lion.page.PageResult;
import com.newtouch.lion.query.QueryCriteria;


/**
 * <p>
 * Title: 用户登录日志
 * </p>
 * <p>
 * Description:  用户登录日志
 * </p>
 * <p>
 * Copyright: Copyright (c) 2013
 * </p>
 * <p>
 * Company: Newtouch
 * </p>
 * 
 * @author WangLijun
 * @version 1.0
 */
public interface LoginLogService  {

	
	PageResult<LoginLogGroup> doFindByCriteria(QueryCriteria queryCriteria);
	
	void save(LoginLog loginLog);

}

	