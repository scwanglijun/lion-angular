/*
* Copyright (c)  2015, Newtouch
* All rights reserved. 
*
* $id: UserInfoVo.java 9552 2015年2月26日 下午3:09:01 WangLijun$
*/
package com.newtouch.lion.admin.web.model.system.user; 


/**
 * <p>
 * Title: 用户基本信息修改
 * </p>
 * <p>
 * Description: 用户基本信息修改
 * </p>
 * <p>
 * Copyright: Copyright (c) 2015
 * </p>
 * <p>
 * Company: Newtouch
 * </p>
 * 
 * @author WangLijun
 * @version 1.0
 */
public class UserInfoVo {
	/** 用户描述 */
	private String description;
	/** 用户真实姓名－中文 */
	private String realnameZh;
	/** 用户真实姓名－英文 */
	private String realnameEn;
	/** 联系电话 */
	private String telephone;
	/** 联系电话－手机 */
	private String mobile;
	/** 联系电话－办公室 */
	private String officePhone;
	/** 性别 */
	private Integer gender;
	/** 办公室位置 */
	private String location;
	/** 传真 */
	private String fax;
	/** 邮编 */
	private String postcode;
	/***
	 * 默认构造函数
	 */
	public UserInfoVo() {
		super();
	}
	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}
	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}
	/**
	 * @return the realnameZh
	 */
	public String getRealnameZh() {
		return realnameZh;
	}
	/**
	 * @param realnameZh the realnameZh to set
	 */
	public void setRealnameZh(String realnameZh) {
		this.realnameZh = realnameZh;
	}
	/**
	 * @return the realnameEn
	 */
	public String getRealnameEn() {
		return realnameEn;
	}
	/**
	 * @param realnameEn the realnameEn to set
	 */
	public void setRealnameEn(String realnameEn) {
		this.realnameEn = realnameEn;
	}
	/**
	 * @return the telephone
	 */
	public String getTelephone() {
		return telephone;
	}
	/**
	 * @param telephone the telephone to set
	 */
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	/**
	 * @return the mobile
	 */
	public String getMobile() {
		return mobile;
	}
	/**
	 * @param mobile the mobile to set
	 */
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	/**
	 * @return the officePhone
	 */
	public String getOfficePhone() {
		return officePhone;
	}
	/**
	 * @param officePhone the officePhone to set
	 */
	public void setOfficePhone(String officePhone) {
		this.officePhone = officePhone;
	}
	/**
	 * @return the gender
	 */
	public Integer getGender() {
		return gender;
	}
	/**
	 * @param gender the gender to set
	 */
	public void setGender(Integer gender) {
		this.gender = gender;
	}
	/**
	 * @return the location
	 */
	public String getLocation() {
		return location;
	}
	/**
	 * @param location the location to set
	 */
	public void setLocation(String location) {
		this.location = location;
	}
	/**
	 * @return the fax
	 */
	public String getFax() {
		return fax;
	}
	/**
	 * @param fax the fax to set
	 */
	public void setFax(String fax) {
		this.fax = fax;
	}
	/**
	 * @return the postcode
	 */
	public String getPostcode() {
		return postcode;
	}
	/**
	 * @param postcode the postcode to set
	 */
	public void setPostcode(String postcode) {
		this.postcode = postcode;
	}
	
	
}

	