/*
 * Copyright (c)  2014, NewTouch
 * All rights reserved. 
 *
 * $id: PasswordVo.java 9552 2014-4-6 下午09:48:54 WangLijun$
 */
package com.newtouch.lion.admin.web.model.system.user;

import java.io.Serializable;

/**
 * <p>
 * Title:密码修改Vo
 * </p>
 * <p>
 * Description:密码修改Vo
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
public class PasswordVo implements Serializable {

	/**
		 * 
		 */
	private static final long serialVersionUID = 881413555539766568L;
	/** 旧密码 */
	private String oldpassword;
	/** 新密码 */
	private String password;
	/** 确认密码 */
	private String confirmpassword;
	/***
	 * 默认函数
	 */
	public PasswordVo() {
		 super();
	}
	/**
	 * @return the oldpassword 旧密码
	 */
	public String getOldpassword() {
		return oldpassword;
	}
	/**
	 * @param oldpassword 旧密码
	 */
	public void setOldpassword(String oldpassword) {
		this.oldpassword = oldpassword;
	}
	/**
	 * @return the password 密码
	 */
	public String getPassword() {
		return password;
	}
	/**
	 * @param password  密码
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	/**
	 * @return the 确认密码
	 */
	public String getConfirmpassword() {
		return confirmpassword;
	}
	/**
	 * @param confirmpassword 确认密码
	 */
	public void setConfirmpassword(String confirmpassword) {
		this.confirmpassword = confirmpassword;
	}
	
	
}
