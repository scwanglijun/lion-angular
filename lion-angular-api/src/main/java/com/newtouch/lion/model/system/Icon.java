
/*
* Copyright (c)  2015, NewTouch
* All rights reserved. 
*
* $id: Icon.java 9552 Mar 4, 2015 11:13:38 AM MaoJiaWei$
*/
package com.newtouch.lion.model.system; 

import com.newtouch.lion.model.VersionEntity;

/**
 * <p>
 * Title: 图标Icon
 * </p>
 * <p>
 * Description: 图标Icon
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
public class Icon extends VersionEntity<Long>{
	/**
	 * 序列化
	 */
	private static final long serialVersionUID = -6193827937905005158L;
	
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
	@Override
	public Long getId() {
		// TODO Auto-generated method stub
		return id;
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
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

}

	