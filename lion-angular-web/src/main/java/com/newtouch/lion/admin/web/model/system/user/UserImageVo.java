
/*
* Copyright (c)  2015, NewTouch
* All rights reserved. 
*
* $id: UserImageVo.java 9552 Feb 28, 2015 10:15:01 PM MaoJiaWei$
*/
package com.newtouch.lion.admin.web.model.system.user;

import org.springframework.web.multipart.MultipartFile;

import java.io.Serializable;

/**
 * <p>
 * Title: 修改头像Vo
 * </p>
 * <p>
 * Description: 修改头像Vo
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
public class UserImageVo implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6551519811792180866L;
	
	private MultipartFile image;
	private String x;
    private String y;
    private String h;
    private String w;
	/**
	 * @return the image
	 */
	public MultipartFile getImage() {
		return image;
	}
	/**
	 * @param image the image to set
	 */
	public void setImage(MultipartFile image) {
		this.image = image;
	}
	/**
	 * @return the x
	 */
	public String getX() {
		return x;
	}
	/**
	 * @param x the x to set
	 */
	public void setX(String x) {
		this.x = x;
	}
	/**
	 * @return the y
	 */
	public String getY() {
		return y;
	}
	/**
	 * @param y the y to set
	 */
	public void setY(String y) {
		this.y = y;
	}
	/**
	 * @return the h
	 */
	public String getH() {
		return h;
	}
	/**
	 * @param h the h to set
	 */
	public void setH(String h) {
		this.h = h;
	}
	/**
	 * @return the w
	 */
	public String getW() {
		return w;
	}
	/**
	 * @param w the w to set
	 */
	public void setW(String w) {
		this.w = w;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "UserImageVo [image=" + image + ", x=" + x + ", y=" + y + ", h="
				+ h + ", w=" + w + "]";
	}
    
	
}

	