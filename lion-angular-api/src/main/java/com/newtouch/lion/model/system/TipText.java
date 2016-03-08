/*
 * Copyright (c)  2012, Newtouch
 * All rights reserved. 
 *
 * $id: TipText.java 9552 2012-12-30 下午8:37:24 WangLijun$
 */
package com.newtouch.lion.model.system;

import javax.persistence.Column;

import com.newtouch.lion.model.VersionEntity;

/**
 * <p>
 * Title: 提示信息Model
 * </p>
 * <p>
 * Description: 提示信息Model
 * </p>
 * <p>
 * Copyright: Copyright (c) 2012
 * </p>
 * <p>
 * Company: Newtouch
 * </p>
 * 
 * @author WangLijun
 * @version 1.0
 */
public class TipText extends VersionEntity<Long> {

	private static final long serialVersionUID = -811735670043845507L;
	/** 提示信息ID */
	private Long id;
	/** 提示信息KEY */
	private String tipTextKey;
	/** 提示信息－中文 */
	private String textZh;
	/** 提示信息－英文 */
	private String textEn;

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
	 * @return the tipTextKey
	 */
	@Column(name = "TIP_TEXT_KEY", length = 50)
	public String getTipTextKey() {
		return tipTextKey;
	}

	/**
	 * @return the nameZh
	 */
	@Column(name = "TEXT_ZH", unique = true, length = 400)
	public String getTextZh() {
		return textZh;
	}

	/**
	 * @return the nameEn
	 */
	@Column(name = "TEXT_EN", unique = true, length = 400)
	public String getTextEn() {
		return textEn;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @param tipTextKey
	 *            the tipTextKey to set
	 */
	public void setTipTextKey(String tipTextKey) {
		this.tipTextKey = tipTextKey;
	}

	/**
	 * @param textZh
	 *            the textZh to set
	 */
	public void setTextZh(String textZh) {
		this.textZh = textZh;
	}

	/**
	 * @param textEn
	 *            the textEn to set
	 */
	public void setTextEn(String textEn) {
		this.textEn = textEn;
	}
}
