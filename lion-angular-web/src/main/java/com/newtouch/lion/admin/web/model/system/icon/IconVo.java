
/*
* Copyright (c)  2015, NewTouch
* All rights reserved. 
*
* $id: IconVo.java 9552 Mar 4, 2015 1:49:50 PM MaoJiaWei$
*/
package com.newtouch.lion.admin.web.model.system.icon; 
/**
 * <p>
 * Title: 图标管理VO
 * </p>
 * <p>
 * Description: 图标管理VO，用于数据验证、接收页面提交数据
 * </p>
 * <p>
 * Copyright: Copyright (c) 2015
 * </p>
 * <p>
 * Company: NewTouch
 * </p>
 * 
 * @author MaoJiaWei
 * @version 1.0
 */
public class IconVo {
	public static final  String ICON_CLASS="iconClass";
	/***
	 * 图标ID
	 */
	private Long id;
	/** 图标类型 */
	private String iconType;
	/** 图标类名*/
	private String iconClass;
	/** 图标图片 */
	private String iconImage;
	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}
	/**
	 * @return the iconType
	 */
	public String getIconType() {
		return iconType;
	}
	/**
	 * @param iconType the iconType to set
	 */
	public void setIconType(String iconType) {
		this.iconType = iconType;
	}
	/**
	 * @return the iconClass
	 */
	public String getIconClass() {
		return iconClass;
	}
	/**
	 * @param iconClass the iconClass to set
	 */
	public void setIconClass(String iconClass) {
		this.iconClass = iconClass;
	}
	/**
	 * @return the iconImage
	 */
	public String getIconImage() {
		return iconImage;
	}
	/**
	 * @param iconImage the iconImage to set
	 */
	public void setIconImage(String iconImage) {
		this.iconImage = iconImage;
	}
	/**
	 * @param id
	 * @param iconType
	 * @param iconClass
	 * @param iconImage
	 */
	public IconVo(Long id, String iconType, String iconClass, String iconImage) {
		super();
		this.id = id;
		this.iconType = iconType;
		this.iconClass = iconClass;
		this.iconImage = iconImage;
	}
	/**
	 * 
	 */
	public IconVo() {
		super();
	}
	
}

	