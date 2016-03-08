package com.newtouch.lion.model.logger;

import java.io.Serializable;
import java.util.Date;

import com.newtouch.lion.model.AuditEntity;


public class SysOperationLogger extends AuditEntity<Long> implements Serializable {
	
	public static Integer IS_OPERATION_SUCCESS_SUCCESS = new Integer(1);
	
	public static Integer IS_OPERATION_SUCCESS_FALSE = new Integer(0);
	
	private static final long serialVersionUID = -542265886635277704L;
	
	private Long id;
	
	private Long tmSysErrorCodeId;
	
	private String username;
	
	private String resName;
	
	private Date operationTime;
	
	private Integer isSuccess;
	
	private String errorImpact;

	public SysOperationLogger() {
		super();
	}

	public SysOperationLogger(Long tmSysErrorCodeId, String username,
			String resName, Date operationTime, Integer isSuccess,
			String errorImpact) {
		super();
		this.tmSysErrorCodeId = tmSysErrorCodeId;
		this.username = username;
		this.resName = resName;
		this.operationTime = operationTime;
		this.isSuccess = isSuccess;
		this.errorImpact = errorImpact;
	}


	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}


	public Long getTmSysErrorCodeId() {
		return this.tmSysErrorCodeId;
	}

	public void setTmSysErrorCodeId(Long tmSysErrorCodeId) {
		this.tmSysErrorCodeId = tmSysErrorCodeId;
	}

	
	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}


	public String getResName() {
		return this.resName;
	}

	public void setResName(String resName) {
		this.resName = resName;
	}


	public Date getOperationTime() {
		return this.operationTime;
	}

	public void setOperationTime(Date operationTime) {
		this.operationTime = operationTime;
	}


	public Integer getIsSuccess() {
		return this.isSuccess;
	}

	public void setIsSuccess(Integer isSuccess) {
		this.isSuccess = isSuccess;
	}


	public String getErrorImpact() {
		return this.errorImpact;
	}

	public void setErrorImpact(String errorImpact) {
		this.errorImpact = errorImpact;
	}

}
