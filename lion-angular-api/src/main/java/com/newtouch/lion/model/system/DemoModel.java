/*
 * Copyright (c)  2013, Newtouch
 * All rights reserved. 
 *
 * $id: DemoModel.java 9552 2013-3-22 上午9:01:29 WangLijun$
 */
package com.newtouch.lion.model.system;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import com.newtouch.lion.model.AuditEntity;

/**
 * <p>
 * Title:
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Copyright: Copyright (c) 2013
 * </p>
 * <p>
 * Company: Newtouch
 * </p>
 * 
 * @author WangLijun
 * @version 1.0
 */

public class DemoModel extends AuditEntity<Long> implements Serializable {

	/**
		 * 
		 */
	private static final long serialVersionUID = -1043181369109288955L;

	private Long id;

	private String nameZh;

	private DemoModel demoModel;

	private Set<DemoModel> demoModels = new HashSet<DemoModel>();

	/**
	 * @return the demoModel
	 */

	public DemoModel getDemoModel() {
		return demoModel;
	}

	/**
	 * @param demoModel
	 *            the demoModel to set
	 */
	public void setDemoModel(DemoModel demoModel) {
		this.demoModel = demoModel;
	}

	/**
	 * @return the demoModels
	 */

	public Set<DemoModel> getDemoModels() {
		return demoModels;
	}

	/**
	 * @param demoModels
	 *            the demoModels to set
	 */
	public void setDemoModels(Set<DemoModel> demoModels) {
		this.demoModels = demoModels;
	}

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
	 * @param id
	 *            the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @return the nameZh
	 */

	public String getNameZh() {
		return nameZh;
	}

	/**
	 * @param nameZh
	 *            the nameZh to set
	 */
	public void setNameZh(String nameZh) {
		this.nameZh = nameZh;
	}

	@Override
	public String toString() {
		return "DemoModel [id=" + id + ", nameZh=" + nameZh + ", demoModel="
				+ demoModel.toString() + ", demoModels=" + demoModels + "]";
	}
}
