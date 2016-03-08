/**
 * 
 */
package com.newtouch.lion.model.logger;

import javax.persistence.Transient;

import com.newtouch.lion.model.AuditEntity;

/**
 * @author wanglijun
 * 
 */

public class SysBusinessLogger extends AuditEntity<Long> implements Cloneable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5666680893073003335L;
	private Long id;
	private String appName;
	private String moduleName;
	private String actionType;
	private String shortMessage;
	private Long tcRecTypeId;
	private Long recId;
	private Long tcSubRecTypeId;
	private Long subRecId;
	private Long operatorId;
	private String logTemplateKey;

	public SysBusinessLogger() {
	}

	public SysBusinessLogger(String appName, String moduleName, String actionType,
			String shortMessage, Long tcRecTypeId, Long recId,
			Long tcSubRecTypeId, Long subRecId, Long operatorId) {
		this.appName = appName;
		this.moduleName = moduleName;
		this.actionType = actionType;
		this.shortMessage = shortMessage;
		this.tcRecTypeId = tcRecTypeId;
		this.recId = recId;
		this.tcSubRecTypeId = tcSubRecTypeId;
		this.subRecId = subRecId;
		this.operatorId = operatorId;
	}

 
	public String getAppName() {
		return this.appName;
	}

 
 
	public Long getId() {
		return this.id;
	}

 
	public String getModuleName() {
		return this.moduleName;
	}

 
	public Long getOperatorId() {
		return this.operatorId;
	}
 
	public Long getRecId() {
		return this.recId;
	}

 
	public String getShortMessage() {
		return this.shortMessage;
	}

 
	public Long getSubRecId() {
		return this.subRecId;
	}
 
	public Long getTcRecTypeId() {
		return this.tcRecTypeId;
	}

 
	public Long getTcSubRecTypeId() {
		return this.tcSubRecTypeId;
	}

 
	public String getActionType() {
		return this.actionType;
	}

	public void setActionType(String actionType) {
		this.actionType = actionType;
	}

	public void setAppName(String appName) {
		this.appName = appName;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setModuleName(String moduleName) {
		this.moduleName = moduleName;
	}

	public void setOperatorId(Long operatorId) {
		this.operatorId = operatorId;
	}

	public void setRecId(Long recId) {
		this.recId = recId;
	}

	public void setShortMessage(String shortMessage) {
		this.shortMessage = shortMessage;
	}

	public void setSubRecId(Long subRecId) {
		this.subRecId = subRecId;
	}

	public void setTcRecTypeId(Long tcRecTypeId) {
		this.tcRecTypeId = tcRecTypeId;
	}

	public void setTcSubRecTypeId(Long tcSubRecTypeId) {
		this.tcSubRecTypeId = tcSubRecTypeId;
	}

	@Transient
	public String getLogTemplateKey() {
		return this.logTemplateKey;
	}

	public void setLogTemplateKey(String logTemplateKey) {
		this.logTemplateKey = logTemplateKey;
	}

	public SysBusinessLogger clone() throws CloneNotSupportedException {
		return ((SysBusinessLogger) super.clone());
	}
}
