/*
 * Copyright (c)  2013, Newtouch
 * All rights reserved. 
 *
 * $id: DataBaseInfo.java 9552 2013-5-19 下午7:42:02 WangLijun$
 */
package com.newtouch.lion.model.application;

import java.io.Serializable;

/**
 * <p>
 * Title: 应用－数据库信息
 * </p>
 * <p>
 * Description: 应用－数据库信息
 * </p>
 * <p>
 * Copyright: Copyright (c) 2013
 * </p>
 * <p>
 * Company: Newtouch
 * </p>
 * 
 * @author WangLijun
 * @version 1.0
 */
public class DataBaseInfo implements Serializable {

	/**
		 * 
		 */
	private static final long serialVersionUID = -3696887194624642085L;

	/** 数据类型 */
	private String dataBaseType;
	/** 服务器地址 */
	private String serverIP;
	/** 端口 */
	private int port;
	/** SID */
	private String sid;
	/** 数据库用户名 */
	private String username;

	/**
	 * @return the dataBaseType
	 */
	public String getDataBaseType() {
		return dataBaseType;
	}

	/**
	 * @param dataBaseType
	 *            the dataBaseType to set
	 */
	public void setDataBaseType(String dataBaseType) {
		this.dataBaseType = dataBaseType;
	}

	/**
	 * @return the serverIP
	 */
	public String getServerIP() {
		return serverIP;
	}

	/**
	 * @param serverIP
	 *            the serverIP to set
	 */
	public void setServerIP(String serverIP) {
		this.serverIP = serverIP;
	}

	/**
	 * @return the port
	 */
	public int getPort() {
		return port;
	}

	/**
	 * @param port
	 *            the port to set
	 */
	public void setPort(int port) {
		this.port = port;
	}

	/**
	 * @return the sid
	 */
	public String getSid() {
		return sid;
	}

	/**
	 * @param sid
	 *            the sid to set
	 */
	public void setSid(String sid) {
		this.sid = sid;
	}

	/**
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * @param username
	 *            the username to set
	 */
	public void setUsername(String username) {
		this.username = username;
	}
}
