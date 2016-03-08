package com.newtouch.lion.admin.web.model.system.loginlog;

import java.io.Serializable;
import java.util.Date;

public class LoginLogGroupVo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3974177953044281146L;

	/** 用户登录Id */
	private Long id;
	/** 用户登录Id */
	private Long userId;
	/**用户登陆名称*/
	private String userName;
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
	private Boolean isSuccess;
	/**登陆类型*/
	private String loginType;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getLoginIP() {
		return loginIP;
	}

	public void setLoginIP(String loginIP) {
		this.loginIP = loginIP;
	}

	public String getLoginMAC() {
		return loginMAC;
	}

	public void setLoginMAC(String loginMAC) {
		this.loginMAC = loginMAC;
	}

	public String getBrowserName() {
		return browserName;
	}

	public void setBrowserName(String browserName) {
		this.browserName = browserName;
	}

	public String getSessionId() {
		return sessionId;
	}

	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}

	public String getLoginResult() {
		return loginResult;
	}

	public void setLoginResult(String loginResult) {
		this.loginResult = loginResult;
	}

	public Date getLoginTime() {
		return loginTime;
	}

	public void setLoginTime(Date loginTime) {
		this.loginTime = loginTime;
	}

	public Date getLogoutTime() {
		return logoutTime;
	}

	public void setLogoutTime(Date logoutTime) {
		this.logoutTime = logoutTime;
	}

	public String getOsInfo() {
		return osInfo;
	}

	public void setOsInfo(String osInfo) {
		this.osInfo = osInfo;
	}

	public Boolean getIsSuccess() {
		return isSuccess;
	}

	public void setIsSuccess(Boolean isSuccess) {
		this.isSuccess = isSuccess;
	}

	public String getLoginType() {
		return loginType;
	}

	public void setLoginType(String loginType) {
		this.loginType = loginType;
	}

}
