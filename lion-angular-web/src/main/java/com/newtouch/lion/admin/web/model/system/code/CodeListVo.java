/*
 * Copyright (c)  2012, Newtouch
 * All rights reserved. 
 *
 * $id: TsParameter.java 9552 2012-7-8 上午12:36:16 WangLijun$
 */
package com.newtouch.lion.admin.web.model.system.code;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

/**
 * <p>
 * Title:TsParameter
 * </p>
 * <p>
 * Description:页面控制对象系统参数表
 * </p>
 * <p>
 * Copyright: Copyright (c) 2012
 * </p>
 * <p>
 * Company: Newtouch
 * </p>
 * 
 * @author dengkang
 * @version 1.0
 */
public class CodeListVo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8154073699946460340L;

	public static final String NAMEEN = "nameEn";
	/**
	 * @Fields id:参数序号
	 */
	private Long id;
	/**
	 * @Fields type：编码类型
	 */
	@NotNull(message = "{sys.codeList.form.type.missing.message}")
	private Long codeTypeId;

	/** @Fileds editable 是否可编辑 */
	private Boolean editable=Boolean.FALSE;

	/**
	 * @Fields nameEn：英文名称
	 */
	@Length(max = 100, min = 2, message = "{sys.codeList.form.nameen.length.message}")
	private String nameEn;
	/**
	 * @Fields nameZh：中文名称
	 */
	@Length(max = 100, min = 2, message = "{sys.codeList.form.namezh.length.message}")
	private String nameZh;
	
	/**编码排序*/
	private int sortNo;
	/**
	 * @Fields value:编码值
	 */
	@Size(max = 40, min = 1, message = "{sys.codeList.form.value.length.message}")
	private String codeValue;
	/**
	 * @Fields description:编码描述
	 */
	@Length(min = 0, max = 512, message = "{sys.codeList.form.description.length.message}")
	private String description;
	
	/**默认选择*/
	private Boolean  selected=Boolean.FALSE;

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
	 * @return the codeTypeId
	 */
	public Long getCodeTypeId() {
		return codeTypeId;
	}

	/**
	 * @param codeTypeId the codeTypeId to set
	 */
	public void setCodeTypeId(Long codeTypeId) {
		this.codeTypeId = codeTypeId;
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
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description
	 *            the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @return the codeValue
	 */
	public String getCodeValue() {
		return codeValue;
	}

	/**
	 * @param codeValue the codeValue to set
	 */
	public void setCodeValue(String codeValue) {
		this.codeValue = codeValue;
	}

	/**
	 * @return the sortNo
	 */
	public int getSortNo() {
		return sortNo;
	}

	/**
	 * @param sortNo the sortNo to set
	 */
	public void setSortNo(int sortNo) {
		this.sortNo = sortNo;
	}

	/**
	 * @return the selected
	 */
	public Boolean getSelected() {
		return selected;
	}

	/**
	 * @param selected the selected to set
	 */
	public void setSelected(Boolean selected) {
		this.selected = selected;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "CodeListVo [id=" + id + ", codeTypeId=" + codeTypeId + ", editable="
				+ editable + ", nameEn=" + nameEn + ", nameZh=" + nameZh
				+ ", sortNo=" + sortNo + ", codeValue=" + codeValue
				+ ", description=" + description + ", selected=" + selected
				+ "]";
	}
 
	
}