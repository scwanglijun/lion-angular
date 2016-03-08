package com.newtouch.lion.model.system;

import com.newtouch.lion.model.VersionEntity;

public class Tasks extends VersionEntity<Long> {
	
	private static final long serialVersionUID = -3068188326261674873L;
	/**任务id*/
	private Long id;
	/** 名称  */
	private String name;
	/** cron 表达式*/
	private String cron;
	/** cron bean name */
	private String beanName;
	/** bean class 类 */
	private String beanClass;
	/** 方法名 */
	private String methodName;
	/** 是否启动  */
	private Integer isStart;
	/**执行时间*/
	private Integer executeTime;
	/** discription */
	private String description;
	@Override
	public Long getId() {
		return this.id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCron() {
		return cron;
	}
	public void setCron(String cron) {
		this.cron = cron;
	}
	public String getBeanName() {
		return beanName;
	}
	public void setBeanName(String beanName) {
		this.beanName = beanName;
	}
	public String getBeanClass() {
		return beanClass;
	}
	public void setBeanClass(String beanClass) {
		this.beanClass = beanClass;
	}
	public String getMethodName() {
		return methodName;
	}
	public void setMethodName(String methodName) {
		this.methodName = methodName;
	}
	
	public Integer getIsStart() {
		return isStart;
	}
	public void setIsStart(Integer isStart) {
		this.isStart = isStart;
	}
	
	public Integer getExecuteTime() {
		return executeTime;
	}
	public void setExecuteTime(Integer executeTime) {
		this.executeTime = executeTime;
	}
	public void setIsStart(int isStart) {
		this.isStart = isStart;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public void setId(Long id) {
		this.id = id;
	}

	

	
}