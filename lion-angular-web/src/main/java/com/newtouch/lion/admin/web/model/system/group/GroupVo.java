/*
 * Copyright (c)  2014, NewTouch
 * All rights reserved. 
 *
 * $id: GroupVo.java 9552 2014-3-31 下午12:31:14 WangLijun$
 */
package com.newtouch.lion.admin.web.model.system.group;

/**
 * <p>
 * Title: 用户组管理VO
 * </p>
 * <p>
 * Description: 用户组管理VO，用于数据验证、接收页面提交数据
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
public class GroupVo {
	public static final  String NAMEEN="nameEn";
	/** ID */
	private Long id;
	/** 用户组名称－中文 */
	private String nameZh;
	/** 用户组名称－英文 */
	private String nameEn;
	/** 用户组描述 */
	private String description;
	/** 是否可编辑、默认为False */
	private Boolean editable = Boolean.FALSE;

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
	 * @param id
	 * @param nameZh
	 * @param nameEn
	 * @param description
	 * @param editable
	 */
	public GroupVo(Long id, String nameZh, String nameEn, String description,
			Boolean editable) {
		super();
		this.id = id;
		this.nameZh = nameZh;
		this.nameEn = nameEn;
		this.description = description;
		this.editable = editable;
	}

	/**
	 * default
	 * */
	public GroupVo() {
		super();
	}
	
	/**
	 * @return the nameen
	 */
	public static String getNameen() {
		return NAMEEN;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "GroupVo [id=" + id + ", nameZh=" + nameZh + ", nameEn="
				+ nameEn + ", description=" + description + ", editable="
				+ editable + "]";
	};

}
