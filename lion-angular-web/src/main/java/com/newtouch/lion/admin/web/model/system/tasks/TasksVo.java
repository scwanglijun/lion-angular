package com.newtouch.lion.admin.web.model.system.tasks;


public class TasksVo {
	
	
	public static final  String BEAN_CLASS="beanCLass";
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
	
	
	public Long getId() {
		return id;
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
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Integer getExecuteTime() {
		return executeTime;
	}
	public void setExecuteTime(Integer executeTime) {
		this.executeTime = executeTime;
	}

	

	
}