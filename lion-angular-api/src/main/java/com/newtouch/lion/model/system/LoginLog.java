/*
 * Copyright (c)  2012, Newtouch
 * All rights reserved. 
 *
 * $id: TsLoginLogs.java 9552 2012-12-30 下午8:17:38 WangLijun$
 */
package com.newtouch.lion.model.system;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.newtouch.lion.model.AuditEntity;

/**
 * <p>
 * Title: 用户登录日志Model
 * </p>
 * <p>
 * Description: 用户登录日志Model
 * </p>
 * <p>
 * Copyright: Copyright (c) 2012
 * </p>
 * <p>
 * Company: lion
 * </p>
 * 
 * @author WangLijun
 * @version 1.0
 */
public class LoginLog extends AuditEntity<Long> {

	private static final long serialVersionUID = -8978760822915952711L;
	/** 用户登录Id */
	private Long id;
	/** 用户登录Id */
	private Long userId;
	/** 用户登录IP地址 */
	private String loginIP;
	/** 用户登录MAC地址 */
	private String loginMAC;
	/** 用户登录浏览器 名称 版本信息 */
	private String browserName;
	/** Session Id */
	private String sessionId;
	/** 用户登录结果 */
	private String loginResult;
	/** 用户登录时间 */
	private Date loginTime;
	/** 用户退出时间 */
	private Date logoutTime;
	/** 用户操作系统INFO */
	private String osInfo;
	/** 是否登录成功 */
	private Long isSuccess;
	/**登陆类型*/
	private String loginType;
	/**用户*/
	private User user;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.lion.framework.model.BaseEntity#getId()
	 */
	@Override
	public Long getId() {
		return this.id;
	}

	/**
	 * @return the userId
	 */
	@Column(name = "BAS_USER_ID", nullable = false, precision = 11, scale = 0)
	public Long getUserId() {
		return userId;
	}

	/**
	 * @return the loginIP
	 */
	@Column(name = "LOGIN_IP", length = 20)
	public String getLoginIP() {
		return loginIP;
	}

	/**
	 * @return the loginMAC
	 */
	@Column(name = "LOGIN_MAC", length = 20)
	public String getLoginMAC() {
		return loginMAC;
	}

	/**
	 * @return the browserName
	 */
	@Column(name = "BROWSER_NAME", length = 100)
	public String getBrowserName() {
		return browserName;
	}

	/**
	 * @return the sessionId
	 */
	@Column(name = "SESSION_ID", length = 60)
	public String getSessionId() {
		return sessionId;
	}

	/**
	 * @return the loginResult
	 */
	@Column(name = "LOGIN_RESULT", length = 120)
	public String getLoginResult() {
		return loginResult;
	}

	/**
	 * @return the loginTime
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "LOGIN_TIME")
	public Date getLoginTime() {
		return loginTime;
	}

	/**
	 * @return the logoutTime
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "LOGOUT_TIME")
	public Date getLogoutTime() {
		return logoutTime;
	}

	/**
	 * @return the osInfo
	 */
	@Column(name = "OS_INFO", length = 120)
	public String getOsInfo() {
		return osInfo;
	}

	/**
	 * @return the isSuccess
	 */
	@Column(name = "IS_SUCCESS")
	public Long getIsSuccess() {
		return isSuccess;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @param userId
	 *            the userId to set
	 */
	public void setUserId(Long userId) {
		this.userId = userId;
	}

	/**
	 * @param loginIP
	 *            the loginIP to set
	 */
	public void setLoginIP(String loginIP) {
		this.loginIP = loginIP;
	}

	/**
	 * @param loginMAC
	 *            the loginMAC to set
	 */
	public void setLoginMAC(String loginMAC) {
		this.loginMAC = loginMAC;
	}

	/**
	 * @param browserName
	 *            the browserName to set
	 */
	public void setBrowserName(String browserName) {
		this.browserName = browserName;
	}

	/**
	 * @param sessionId
	 *            the sessionId to set
	 */
	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}

	/**
	 * @param loginResult
	 *            the loginResult to set
	 */
	public void setLoginResult(String loginResult) {
		this.loginResult = loginResult;
	}

	/**
	 * @param loginTime
	 *            the loginTime to set
	 */
	public void setLoginTime(Date loginTime) {
		this.loginTime = loginTime;
	}

	/**
	 * @param logoutTime
	 *            the logoutTime to set
	 */
	public void setLogoutTime(Date logoutTime) {
		this.logoutTime = logoutTime;
	}

	/**
	 * @param osInfo
	 *            the osInfo to set
	 */
	public void setOsInfo(String osInfo) {
		this.osInfo = osInfo;
	}

	/**
	 * @param isSuccess
	 *            the isSuccess to set
	 */
	public void setIsSuccess(Long isSuccess) {
		this.isSuccess = isSuccess;
	}

	public String getLoginType() {
		return loginType;
	}

	public void setLoginType(String loginType) {
		this.loginType = loginType;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
}
