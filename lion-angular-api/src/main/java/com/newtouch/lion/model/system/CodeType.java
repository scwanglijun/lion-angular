/**
 * Copyright (c)  2012, Newtouch
 * All rights reserved. 
 *
 * $id: TsCodeType.java 9552 2012-12-30 下午8:45:38 WangLijun$
 */
package com.newtouch.lion.model.system;

import java.util.HashSet;
import java.util.Set;

import com.newtouch.lion.model.VersionEntity;

/**
 * <p>
 * Title: 通用编码类型定义Model
 * </p>
 * <p>
 * Description: 通用编码类型定义Model
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
public class CodeType extends VersionEntity<Long> {

	private static final long serialVersionUID = -5566664366790451165L;
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
	/** 通用编码列表集合 */
	private Set<CodeList> codeLists = new HashSet<CodeList>();

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.lion.framework.model.BaseEntity#getId()
	 */

	public Long getId() {
		return this.id;
	}

	/**
	 * @return the codeType
	 */
 
	public String getType() {
		return type;
	}

	/**
	 * @return the nameZh
	 */
	 
	public String getNameZh() {
		return nameZh;
	}

	/**
	 * @return the nameEn
	 */
	 
	public String getNameEn() {
		return nameEn;
	}

	/**
	 * @return the editable
	 */
 
	public Boolean getEditable() {
		return editable;
	}

	/**
	 * @return the codeLenLimit
	 */
	 
	public Integer getCodeLenLimit() {
		return codeLenLimit;
	}

	/**
	 * @return the CodeLists
	 */
	public Set<CodeList> getCodeLists() {
		return codeLists;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @param codeType
	 *            the codeType to set
	 */
	public void setType(String type) {
		this.type = type;
	}

	/**
	 * @param nameZh
	 *            the nameZh to set
	 */
	public void setNameZh(String nameZh) {
		this.nameZh = nameZh;
	}

	/**
	 * @param nameEn
	 *            the nameEn to set
	 */
	public void setNameEn(String nameEn) {
		this.nameEn = nameEn;
	}

	/**
	 * @param editable
	 *            the editable to set
	 */
	public void setEditable(Boolean editable) {
		this.editable = editable;
	}

	/**
	 * @param codeLenLimit
	 *            the codeLenLimit to set
	 */
	public void setCodeLenLimit(Integer codeLenLimit) {
		this.codeLenLimit = codeLenLimit;
	}

	/**
	 * @param tsCodeLists
	 *            the tsCodeLists to set
	 */
	public void setCodeLists(Set<CodeList> codeLists) {
		this.codeLists = codeLists;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "CodeType [id=" + id + ", type=" + type + ", nameZh=" + nameZh
				+ ", nameEn=" + nameEn + ", editable=" + editable
				+ ", codeLenLimit=" + codeLenLimit + ", codeLists=" + codeLists
				+ "]";
	}

}
