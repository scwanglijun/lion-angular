/*
* Copyright (c)  2015, Newtouch
* All rights reserved. 
*
* $id: AuthModel.java 9552 2015年2月15日 上午11:45:21 WangLijun$
*/
package com.newtouch.lion.admin.web.model.system.auth; 

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * Title: 授权模型
 * </p>
 * <p>
 * Description:授权模型
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
public class AuthModel{

 
	/**授权对象ID*/
	private Long id;
	/**目标授权IDS对象*/
	private List<Long> auths=new ArrayList<Long>();
	/**已选择授权目标IDS对象*/
	private List<Long> selecteds=new ArrayList<Long>();
	/***
	 * 默认
	 */
	public AuthModel() {
		 super();
	}
	
	
	
	/**
	 * @param id
	 * @param auths
	 * @param selecteds
	 */
	public AuthModel(Long id, List<Long> auths, List<Long> selecteds) {
		super();
		this.id = id;
		this.auths = auths;
		this.selecteds = selecteds;
	}



	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}


	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

 


	/**
	 * @return the auths
	 */
	public List<Long> getAuths() {
		return auths;
	}


	/**
	 * @param auths the auths to set
	 */
	public void setAuths(List<Long> auths) {
		this.auths = auths;
	}


	/**
	 * @return the selecteds
	 */
	public List<Long> getSelecteds() {
		return selecteds;
	}


	/**
	 * @param selecteds the selecteds to set
	 */
	public void setSelecteds(List<Long> selecteds) {
		this.selecteds = selecteds;
	}
}

	