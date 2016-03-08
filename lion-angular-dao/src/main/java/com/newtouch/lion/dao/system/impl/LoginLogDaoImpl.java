/*
 * Copyright (c)  2012, Newtouch
 * All rights reserved. 
 *
 * $id: TsLoginLogDaoImpl.java 9552 2012-12-31 下午6:59:53 WangLijun$
 */
package com.newtouch.lion.dao.system.impl;

import org.springframework.stereotype.Repository;

import com.newtouch.lion.dao.impl.BaseDaoImpl;
import com.newtouch.lion.dao.system.LoginLogDao;
import com.newtouch.lion.model.system.LoginLog;

/**
 * <p>
 * Title: 用户登录日志处理Dao实现类
 * </p>
 * <p>
 * Description: 用户登录日志处理Dao实现类
 * </p>
 * <p>
 * Copyright: Copyright (c) 2012
 * </p>
 * <p>
 * Company: Newtouch
 * </p>
 * 
 * @author WangLijun
 * @version 1.0
 */
@Repository("loginLogDao")
public class LoginLogDaoImpl extends BaseDaoImpl<LoginLog, Long> implements
		LoginLogDao {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7966076903473711503L;

}
