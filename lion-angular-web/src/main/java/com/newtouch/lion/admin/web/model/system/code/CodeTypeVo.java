/*
 * Copyright (c)  2014, NewTouch
 * All rights reserved. 
 *
 * $id: CodeTypeVo.java 9552 2014-4-8 下午01:21:33 WangLijun$
 */
package com.newtouch.lion.admin.web.model.system.code;

import java.io.Serializable;

/**
 * <p>
 * Title: 通用编码类型列表
 * </p>
 * <p>
 * Description: 通用编辑类型列表
 * </p>
 * <p>
 * Copyright: Copyright (c) 2014
 * </p>
 * <p>
 * Company: NewTouch
 * </p>
 * 
 * @author WangLijun
 * @version 1.0
 */
public class CodeTypeVo implements Serializable {

	private static final long serialVersionUID = 7147121427618267071L;
	public static final  String NAMEEN="nameEn";
	/** 类型id */
	private Long id;
	/** 类型 */
	private String type;
	/** 中文名称 */
	private String nameZh;
	/** 英文名称 */
	private String nameEn;
	/** 是否可编辑 */
	private Boolean editable;
	/** 编码的值长度约束 */
	private Integer codeLenLimit;

	public CodeTypeVo() {
		super();
	}

	/**
	 * @param id
	 * @param type
	 * @param nameZh
	 * @param nameEn
	 * @param editable
	 * @param codeLenLimit
	 */
	public CodeTypeVo(Long id, String type, String nameZh, String nameEn,
			Boolean editable, Integer codeLenLimit) {
		super();
		this.id = id;
		this.type = type;
		this.nameZh = nameZh;
		this.nameEn = nameEn;
		this.editable = editable;
		this.codeLenLimit = codeLenLimit;
	}

	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}

	/**
	 * @param type
	 *            the type to set
	 */
	public void setType(String type) {
		this.type = type;
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

	/**
	 * @return the nameEn
	 */
	public String getNameEn() {
		return nameEn;
	}

	/**
	 * @param nameEn
	 *            the nameEn to set
	 */
	public void setNameEn(String nameEn) {
		this.nameEn = nameEn;
	}

	/**
	 * @return the editable
	 */
	public Boolean getEditable() {
		return editable;
	}

	/**
	 * @param editable
	 *            the editable to set
	 */
	public void setEditable(Boolean editable) {
		this.editable = editable;
	}

	/**
	 * @return the codeLenLimit
	 */
	public Integer getCodeLenLimit() {
		return codeLenLimit;
	}

	/**
	 * @param codeLenLimit
	 *            the codeLenLimit to set
	 */
	public void setCodeLenLimit(Integer codeLenLimit) {
		this.codeLenLimit = codeLenLimit;
	}

	/**
	 * @return the nameen
	 */
	public static String getNameen() {
		return NAMEEN;
	}
	
}
