/*
* Copyright (c)  2015, Newtouch
* All rights reserved. 
*
* $id: SessionInfo.java 9552 2015年2月27日 上午10:41:44 WangLijun$
*/
package com.newtouch.lion.model.session; 

import java.util.Date;

/**
 * <p>
 * Title: 用户会话Model类型
 * </p>
 * <p>
 * Description:用户会话Model类型
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
public class SessionModel {
	/**会话ID*/
	private String id;
	/**会话用户登录IP或Host*/
	private String host;
	/**会话是否有效*/
	private boolean valid;
	/**会话超时时间*/
	private long timeout;
	/**最后访问的时间*/
	private Date lastAccessTime;
	/**会话开始时间*/
	private Date startTimestamp;
	/**会话是否过期*/
	private boolean expired;
	/**用户名*/
	private String username;
	/**用户ID*/
	private Long userId;
	 
	
	/**默认构造函数*/
	public SessionModel() {
		 super();
	}

	/**
	 * @return  会话ID
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param id 会话ID
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * @return 会话用户登录IP或Host
	 */
	public String getHost() {
		return host;
	}

	/**
	 * @param host 会话用户登录IP或Host
	 */
	public void setHost(String host) {
		this.host = host;
	}

	/**
	 * @return 会话是否有效
	 */
	public boolean isValid() {
		return valid;
	}

	/**
	 * @param valid 会话是否有效
	 */
	public void setValid(boolean valid) {
		this.valid = valid;
	}

	/**
	 * @return  会话超时时间
	 */
	public long getTimeout() {
		return timeout;
	}

	/**
	 * @param timeout 会话超时时间
	 */
	public void setTimeout(long timeout) {
		this.timeout = timeout;
	}

	/**
	 * @return 最后访问的时间
	 */
	public Date getLastAccessTime() {
		return lastAccessTime;
	}

	/**
	 * @param lastAccessTime 最后访问的时间
	 */
	public void setLastAccessTime(Date lastAccessTime) {
		this.lastAccessTime = lastAccessTime;
	}

	/**
	 * @return 会话开始时间
	 */
	public Date getStartTimestamp() {
		return startTimestamp;
	}

	/**
	 * @param startTimestamp 会话开始时间
	 */
	public void setStartTimestamp(Date startTimestamp) {
		this.startTimestamp = startTimestamp;
	}

	/**
	 * @return  会话是否过期
	 */
	public boolean isExpired() {
		return expired;
	}

	/**
	 * @param expired 会话是否过期
	 */
	public void setExpired(boolean expired) {
		this.expired = expired;
	}

	/**
	 * @return the  用户名
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * @param username 用户名
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * @return  用户ID
	 */
	public Long getUserId() {
		return userId;
	}

	/**
	 * @param userId  用户ID
	 */
	public void setUserId(Long userId) {
		this.userId = userId;
	} 
	
}

	