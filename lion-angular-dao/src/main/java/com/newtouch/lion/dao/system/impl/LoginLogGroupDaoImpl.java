package com.newtouch.lion.dao.system.impl;

import org.springframework.stereotype.Repository;

import com.newtouch.lion.dao.impl.BaseDaoImpl;
import com.newtouch.lion.dao.system.LoginLogGroupDao;
import com.newtouch.lion.model.system.LoginLogGroup;
@Repository("loginLogGroup")
public class LoginLogGroupDaoImpl extends BaseDaoImpl<LoginLogGroup,Long> implements LoginLogGroupDao{

	/**
	 * 
	 */
	private static final long serialVersionUID = 8876085402458765709L;

}
