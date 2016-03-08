package com.newtouch.lion.model.system;

import com.newtouch.lion.model.AuditEntity;

public class OperationLog extends AuditEntity<Long>{

	/**
	 * 
	 */
	private static final long serialVersionUID = -989520056652483180L;

	private Long id;
	private String branch_company_code;
	private Long employee_id;
	private Long resource_id;
	@Override
	public Long getId() {
		// TODO Auto-generated method stub
		return this.id;
	}
	public String getBranch_company_code() {
		return branch_company_code;
	}
	public void setBranch_company_code(String branch_company_code) {
		this.branch_company_code = branch_company_code;
	}
	public Long getEmployee_id() {
		return employee_id;
	}
	public void setEmployee_id(Long employee_id) {
		this.employee_id = employee_id;
	}
	public Long getResource_id() {
		return resource_id;
	}
	public void setResource_id(Long resource_id) {
		this.resource_id = resource_id;
	}
	public void setId(Long id) {
		this.id = id;
	}

}
