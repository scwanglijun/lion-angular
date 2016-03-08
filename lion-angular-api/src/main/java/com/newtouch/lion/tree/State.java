/*
 * Copyright (c)  2013, 
 * All rights reserved. 
 *
 * $id: State.java 9552 2013-4-11 下午11:59:13 WangLijun$
 */
package com.newtouch.lion.tree;

/**
 * <p>
 * Title:树形节点状态-与EasyUI树绑定
 * </p>
 * <p>
 * Description:树形节点状态-与EasyUI树绑定
 * </p>
 * <p>
 * Copyright: Copyright (c) 2013
 * </p>
 * <p>
 * Company: Honbang
 * </p>
 * 
 * @author WangLijun
 * @version 1.0
 */
public class State {

	public static final String closed = "closed";

	public static final String open = "open";


	/**是否选择中*/
	private Boolean selected;
	/**是否展开*/
	private Boolean opened;
	/**是否不可用*/
	private Boolean disabled;

	/***
	 *
	 * @param selected
	 * @param opened
	 * @param disabled
	 */
	public State(Boolean selected, Boolean opened, Boolean disabled) {
		this.selected = selected;
		this.opened = opened;
		this.disabled = disabled;
	}

	/***
	 *
	 * @param selected
	 * @param disabled
	 */
	public State(Boolean selected, Boolean disabled) {
		this.selected = selected;
		this.disabled = disabled;
	}

	/***
	 *
	 */
	public State() {
		super();
	}

	/***
	 *
	 * @param selected
	 */
	public State(Boolean selected) {
		this.selected = selected;
	}


	public Boolean getDisabled() {
		return disabled;
	}

	public void setDisabled(Boolean disabled) {
		this.disabled = disabled;
	}

	public Boolean getOpened() {
		return opened;
	}

	public void setOpened(Boolean opened) {
		this.opened = opened;
	}

	public Boolean getSelected() {
		return selected;
	}

	public void setSelected(Boolean selected) {
		this.selected = selected;
	}


	@Override
	public String toString() {
		return "TreeState{" +
				"disabled=" + disabled +
				", selected=" + selected +
				", opened=" + opened +
				'}';
	}
}
