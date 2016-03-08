/**
 * 
 */
package com.newtouch.lion.model.logger;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;

import com.newtouch.lion.model.AuditEntity;

/**
 * @author wanglijun
 * 
 */

 
public class SysUserAccLogger extends AuditEntity<Long> implements Serializable {
	public static String LOGIN_RESULT_SUCCESS = "SUCCESS";
	public static String LOGIN_RESULT_FAILS = "FAILS";
	private static final long serialVersionUID = 2786565391974951457L;
	private Long id;
	private Long tmSysUserId;
	private String remoteAddr;
	private String sessionId;
	private String username;
	private String loginResult;
	private Long tryCount;
	private Date loginTime;
	private Date logoutTime;

	public SysUserAccLogger() {
	}

	public SysUserAccLogger(Long tmSysUserId, String remoteAddr,
			String sessionId, String username, Date loginTime, Date logoutTime) {
		this.tmSysUserId = tmSysUserId;
		this.remoteAddr = remoteAddr;
		this.sessionId = sessionId;
		this.username = username;
		this.loginTime = loginTime;
		this.logoutTime = logoutTime;
	}

 
 
	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column(name = "TS_USER_ID")
	public Long getTmSysUserId() {
		return this.tmSysUserId;
	}

	public void setTmSysUserId(Long tmSysUserId) {
		this.tmSysUserId = tmSysUserId;
	}

	@Column(name = "REMOTE_ADDR", length = 256)
	public String getRemoteAddr() {
		return this.remoteAddr;
	}

	public void setRemoteAddr(String remoteAddr) {
		this.remoteAddr = remoteAddr;
	}

	@Column(name = "SESSION_ID", length = 40)
	public String getSessionId() {
		return this.sessionId;
	}

	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}

	@Column(name = "USERNAME", length = 20)
	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	@Column(name = "LOGIN_TIME")
	public Date getLoginTime() {
		return this.loginTime;
	}

	public void setLoginTime(Date loginTime) {
		this.loginTime = loginTime;
	}

	@Column(name = "LOGOUT_TIME")
	public Date getLogoutTime() {
		return this.logoutTime;
	}

	public void setLogoutTime(Date logoutTime) {
		this.logoutTime = logoutTime;
	}

	@Column(name = "LOGIN_RESULT", length = 300)
	public String getLoginResult() {
		return this.loginResult;
	}

	public void setLoginResult(String loginResult) {
		this.loginResult = loginResult;
	}

	@Column(name = "TRY_COUNT")
	public Long getTryCount() {
		return this.tryCount;
	}

	public void setTryCount(Long tryCount) {
		this.tryCount = tryCount;
	}

}
