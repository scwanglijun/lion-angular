/*
 * Copyright (c)  2013, Newtouch
 * All rights reserved. 
 *
 * $id: AuthorizeInfo.java 9552 2013-5-19 下午8:02:13 WangLijun$
 */
package com.newtouch.lion.model.application;

import java.io.Serializable;

/**
 * <p>
 * Title: 产品授权信息
 * </p>
 * <p>
 * Description: 产品授权信息
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
public class AuthorizeInfo implements Serializable {

	/**
		 * 
		 */
	private static final long serialVersionUID = -9028903344239205020L;
	/** 授权给相关组织名称 */
	private String authrizeOrgName;
	/** 授权有效期 */
	private String validDate;
	/** 授权用户数 */
	private Long userCount;
	/** 产品代码 */
	private String productCode;
	/*** 授权MAC地址 */
	private String authMAC;
	/** Licence */
	private Licence licence;

	/**
	 * @return the authrizeOrgName
	 */
	public String getAuthrizeOrgName() {
		return authrizeOrgName;
	}

	/**
	 * @param authrizeOrgName
	 *            the authrizeOrgName to set
	 */
	public void setAuthrizeOrgName(String authrizeOrgName) {
		this.authrizeOrgName = authrizeOrgName;
	}

	/**
	 * @return the validDate
	 */
	public String getValidDate() {
		return validDate;
	}

	/**
	 * @param validDate
	 *            the validDate to set
	 */
	public void setValidDate(String validDate) {
		this.validDate = validDate;
	}

	/**
	 * @return the userCount
	 */
	public Long getUserCount() {
		return userCount;
	}

	/**
	 * @param userCount
	 *            the userCount to set
	 */
	public void setUserCount(Long userCount) {
		this.userCount = userCount;
	}

	/**
	 * @return the productCode
	 */
	public String getProductCode() {
		return productCode;
	}

	/**
	 * @param productCode
	 *            the productCode to set
	 */
	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}

	/**
	 * @return the authMAC
	 */
	public String getAuthMAC() {
		return authMAC;
	}

	/**
	 * @param authMAC
	 *            the authMAC to set
	 */
	public void setAuthMAC(String authMAC) {
		this.authMAC = authMAC;
	}

	/**
	 * @return the licence
	 */
	public Licence getLicence() {
		return licence;
	}

	/**
	 * @param licence
	 *            the licence to set
	 */
	public void setLicence(Licence licence) {
		this.licence = licence;
	}
}
