/*
* Copyright (c)  2015, Newtouch
* All rights reserved. 
*
* $id: SessionVo.java 9552 2015年2月27日 下午5:37:54 WangLijun$
*/
package com.newtouch.lion.admin.web.model.session; 

/**
 * <p>
 * Title: Shiro Session VO
 * </p>
 * <p>
 * Description: Shiro Session VO
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
public class SessionVo {
	/**会话ID*/
	private String id;
	
	public SessionVo() {
		 super();
	}

	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}

}

	