/*
 * Copyright (c)  2014, Newtouch
 * All rights reserved. 
 *
 * $id: Menu.java 9552 2014年12月31日 下午5:41:02 WangLijun$
 */
package com.newtouch.lion.model.menu;

import java.io.Serializable;
import java.util.List;

/**
 * <p>
 * Title: 菜单类
 * </p>
 * <p>
 * Description: 菜单类
 * </p>
 * <p>
 * Copyright: Copyright (c) 2014
 * </p>
 * <p>
 * Company: Newtouch
 * </p>
 * 
 * @author WangLijun
 * @version 1.0
 */
public class Menu implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 256235328678729806L;

	/***
	 * 资源ID
	 */
	private Long id;
	/** 资源父ID */
	private Long parentId;
	/** 资源类型 */
	private String type;
	/** 资源路径 URL Class.Method */
	private String path;
	/** 资源名称－中文 */
	private String nameZh;
	/** 资源名称－英文 */
	private String nameEn;
	/** 资源排序 */
	private int seqNum;
	/** 资源是否叶节点，其下没有子资源 默认为：true */
	private Boolean isLeaf;
	/** 资源目标 指HTML链接的target属性 */
	private String target;
	/**资源图标*/
	private String icon;
	/** 子资源关联父资源对象 */
	private Menu menu;
	/** 父资源关联父资源对象 */
	private List<Menu> menus;
	/**是否被选择**/
	private Boolean selected;
	
	
	
	/***
	 * 默认构造函数
	 */
	public Menu() {
		super();
	}

	
	/***
	 * 
	 * @param id   ID
	 * @param parentId  
	 * @param type    菜单类型
	 * @param path    URL路径
	 * @param nameZh  中文名称
	 * @param nameEn  英文名称
	 * @param seqNum  显示顺序
	 * @param isLeaf  是否是子节点
	 * @param target HTML目标
	 * @param icon 图标
	 * @param menus  下级菜单集合
	 */
	public Menu(Long id, Long parentId, String type, String path,
			String nameZh, String nameEn, int seqNum, Boolean isLeaf, String target,String icon, List<Menu> menus) {
		super();
		this.id = id;
		this.parentId = parentId;
		this.type = type;
		this.path = path;
		this.nameZh = nameZh;
		this.nameEn = nameEn;
		this.seqNum = seqNum;
		this.isLeaf = isLeaf;
		this.target = target;
		this.icon=icon;
		this.menus = menus;
	}

	
	/***
	 * 
	 * @param id   ID
	 * @param parentId  
	 * @param type    菜单类型
	 * @param path    URL路径
	 * @param nameZh  中文名称
	 * @param nameEn  英文名称
	 * @param seqNum  显示顺序
	 * @param isLeaf  是否是子节点
	 * @param target HTML目标
	 * @param icon 图标
	 * @param menus  下级菜单集合
	 */
	public Menu(Long id, Long parentId, String type, String path,
			String nameZh, String nameEn, int seqNum, Boolean isLeaf, String target,String icon,Boolean selected, List<Menu> menus) {
		super();
		this.id = id;
		this.parentId = parentId;
		this.type = type;
		this.path = path;
		this.nameZh = nameZh;
		this.nameEn = nameEn;
		this.seqNum = seqNum;
		this.isLeaf = isLeaf;
		this.target = target;
		this.icon=icon;
		this.selected=selected;
		this.menus = menus;
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
	 * @return the parentId
	 */
	public Long getParentId() {
		return parentId;
	}

	/**
	 * @param parentId
	 *            the parentId to set
	 */
	public void setParentId(Long parentId) {
		this.parentId = parentId;
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
	 * @return the path
	 */
	public String getPath() {
		return path;
	}

	/**
	 * @param path
	 *            the path to set
	 */
	public void setPath(String path) {
		this.path = path;
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
	 * @return the seqNum
	 */
	public int getSeqNum() {
		return seqNum;
	}

	/**
	 * @param seqNum
	 *            the seqNum to set
	 */
	public void setSeqNum(int seqNum) {
		this.seqNum = seqNum;
	}

	/**
	 * @return the isLeaf
	 */
	public Boolean getIsLeaf() {
		return isLeaf;
	}

	/**
	 * @param isLeaf
	 *            the isLeaf to set
	 */
	public void setIsLeaf(Boolean isLeaf) {
		this.isLeaf = isLeaf;
	}

	 

	/**
	 * @return the target
	 */
	public String getTarget() {
		return target;
	}

	/**
	 * @param target
	 *            the target to set
	 */
	public void setTarget(String target) {
		this.target = target;
	}

	/**
	 * @return the menu
	 */
	public Menu getMenu() {
		return menu;
	}

	/**
	 * @param menu
	 *            the menu to set
	 */
	public void setMenu(Menu menu) {
		this.menu = menu;
	}

	/**
	 * @return the menus
	 */
	public List<Menu> getMenus() {
		return menus;
	}

	/**
	 * @param menus
	 *            the menus to set
	 */
	public void setMenus(List<Menu> menus) {
		this.menus = menus;
	}


	/**
	 * @return the icon
	 */
	public String getIcon() {
		return icon;
	}


	/**
	 * @param icon the icon to set
	 */
	public void setIcon(String icon) {
		this.icon = icon;
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
	
	
}
