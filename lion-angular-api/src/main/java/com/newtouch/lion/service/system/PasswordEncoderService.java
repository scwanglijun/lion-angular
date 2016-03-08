/*
 * Copyright (c)  2014, Newtouch
 * All rights reserved. 
 *
 * $id: PasswordEncoderService.java 9552 2014-4-5 下午12:10:19 WangLijun$
 */
package com.newtouch.lion.service.system;

/**
 * <p>
 * Title: 密码加密码类
 * </p>
 * <p>
 * Description: 密码加密码类 包括加密方法、验证密码方法
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
public interface PasswordEncoderService {
	/***
	 * 根据密码值、加盐值，返回加密内容
	 * 
	 * @param password
	 * @param salt
	 * @return String 加密字符串
	 */
	public String encodePassword(String password, Object salt);

	/***
	 * 验证密码是有效 条件是：根据已经加密的密码、验证密码、盐值
	 * 
	 * @param encPass
	 *            已加密的密码
	 * @param rawPass
	 *            验证密码
	 * @param salt
	 *            盐值
	 * @return 是否验证通过
	 */
	public boolean isPasswordValid(String encPass, String rawPass, Object salt);
}
