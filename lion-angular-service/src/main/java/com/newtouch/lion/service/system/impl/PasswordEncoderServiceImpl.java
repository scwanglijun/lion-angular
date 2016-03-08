/*
 * Copyright (c)  2014, Newtouch
 * All rights reserved. 
 *
 * $id: PasswordEncoderServiceImpl.java 9552 2014-4-5 下午12:10:50 WangLijun$
 */
package com.newtouch.lion.service.system.impl;

import com.newtouch.lion.service.system.PasswordEncoderService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * Title: 密码管理实现类
 * </p>
 * <p>
 * Description: 密码管理实现类
 * </p>
 * <p>
 * Copyright: Copyright (c) 2014
 * </p>
 * <p>
 * Company: Newtouch
 * </p>
 * 
 * @author WangLijun
 * @version 1.0
 */
@Service("passwordEncoderService")
public class PasswordEncoderServiceImpl implements PasswordEncoderService {
	//@Autowired
	//private ShaPasswordEncoder passwordEncoder;

	/* (non-Javadoc)
	 * @see com.lion.framework.service.system.PasswordEncoderService#mergePasswordAndSalt(java.lang.String, java.lang.Object, boolean)
	 */
	@Override
	public String encodePassword(String password, Object salt) {
		//return  passwordEncoder.encodePassword(password, salt);
		//TODO
		return null;
	}

	/* (non-Javadoc)
	 * @see com.lion.framework.service.system.PasswordEncoderService#isPasswordValid(java.lang.String, java.lang.String, java.lang.Object)
	 */
	@Override
	public boolean isPasswordValid(String encPass, String rawPass, Object salt) {
		 //return passwordEncoder.isPasswordValid(encPass, rawPass, salt);
		//TODO
		return Boolean.TRUE;
	}
	

	
	
}

	