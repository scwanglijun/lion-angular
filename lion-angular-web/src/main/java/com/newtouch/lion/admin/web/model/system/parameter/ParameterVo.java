/*
 * Copyright (c)  2012, Newtouch
 * All rights reserved. 
 *
 * $id: TsParameter.java 9552 2012-7-8 上午12:36:16 WangLijun$
 */
package com.newtouch.lion.admin.web.model.system.parameter;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

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
 * @author WangLijun
 * @version 1.0
 */
public class ParameterVo implements Serializable{

 
	private static final long serialVersionUID = -1573409277875254236L;
	
	public static final  String NAMEEN="nameEn";
	/**
	 * @Fields id:参数序号
	 */
	private Long id;
	/**
	 * @Fields type：参数类型
	 */
	@NotEmpty(message="{sys.parameter.form.type.missing.message}")
	private String type;
	
	/**@Fileds editable 是否可编辑*/
	private Boolean editable=Boolean.FALSE;
	/**
	 * @Fields nameEn：英文名称
	 */
	@NotNull(message="{sys.parameter.form.nameen.missing.message}")
	@Length(max=128,min=4,message="{sys.parameter.form.nameen.length.message}")
	private String nameEn;
	/**
	 * @Fields nameZh：中文名称
	 */
	@NotNull(message="{sys.parameter.form.namezh.missing.message}")
	@Length(max=128,min=4,message="{sys.parameter.form.namezh.length.message}")
	private String nameZh;
	/**
	 * @Fields value:参数值
	 */
	@NotNull(message="{sys.parameter.form.value.missing.message}")
	@Size(max=256,min=1,message="{sys.parameter.form.value.length.message}")
	private String value;
	/**
	 * @Fields description:参数描述
	 */
	@Length(min=0,max=512,message="{sys.parameter.form.description.length.message}")
	private String description;

	 
	
	
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
	 * @return the type
	 */
	public String getType() {
		return type;
	}




	/**
	 * @param type the type to set
	 */
	public void setType(String type) {
		this.type = type;
	}




	/**
	 * @return the editable
	 */
	public Boolean getEditable() {
		return editable;
	}




	/**
	 * @param editable the editable to set
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
	 * @param nameEn the nameEn to set
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
	 * @param nameZh the nameZh to set
	 */
	public void setNameZh(String nameZh) {
		this.nameZh = nameZh;
	}




	/**
	 * @return the value
	 */
	public String getValue() {
		return value;
	}




	/**
	 * @param value the value to set
	 */
	public void setValue(String value) {
		this.value = value;
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
	 * @return the serialversionuid
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}




	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Parameter [id=" + id + ", type=" + type + ", editable="
				+ editable + ", nameEn=" + nameEn + ", nameZh=" + nameZh
				+ ", value=" + value + ", description=" + description + "]";
	}
	
	
}
