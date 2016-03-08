package com.newtouch.lion.model.system;

import java.util.Date;

import com.newtouch.lion.model.BaseEntity;

public class LoginLogGroup extends BaseEntity<Long> {
	/**
	 * 
	 */
	private static final long serialVersionUID = 9176092542944595229L;
	/** 用户登录Id */
	private Long id;
	/** 用户登录Id */
	private Long userId;
	/** 用户登录名称 */
	private String username;
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
	/** 登陆类型 */
	private String loginType;
	/**默认构造函数*/
	public LoginLogGroup(){super();}
	/**无参*/
	public LoginLogGroup(String loginType,Date loginTime,Date logoutTime,String username,String osInfo){
		super();
		this.loginType=loginType;
		this.loginTime=loginTime;
		this.logoutTime=logoutTime;
		this.username=username;
		this.osInfo=osInfo;
	}
	@Override
	public Long getId() {
		// TODO Auto-generated method stub
		return this.id;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
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

	public void setId(Long id) {
		this.id = id;
	}

}
