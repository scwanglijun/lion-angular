/*
* Copyright (c)  2015, Newtouch
* All rights reserved. 
*
* $id: SessionService.java 9552 2015年2月27日 上午10:56:20 WangLijun$
*/
package com.newtouch.lion.service.session; 


import java.util.List;

import com.newtouch.lion.model.session.SessionModel;

/**
 * <p>
 * Title: 用户会话管理服务接口定义
 * </p>
 * <p>
 * Description: 用用户会话管理服务接口定义（获取用户在线会话列表、强制退出会话）
 * </p>
 * <p>
 * Copyright: Copyright (c) 2015
 * </p>
 * <p>
 * Company: Newtouch
 * </p>
 * 
 * @author WangLijun
 * @version 1.0
 */
public interface SessionService {
	/***
	 * 强制退出的KEY
	 */
	public static final String SESSION_FORCE_LOGOUT_KEY=SessionService.class.getName()+".forceLogout";
	
	/***
	 * 获取当前在线用户列表信息
	 * @return  List<SessionModel>  用户会话列表
	 */
	public List<SessionModel> getActiveSessions();
	/***
	 * 强制结束用户会话
	 * @param sessionId 会话ID
	 * @return true ,强制退出成功，false  强制退出失败
	 */
	public boolean forceLogout(String sessionId);
	/***
	 * 根据会话ID读取用户会话
	 * @param sessionId sessionId
	 * @return SessionModel
	 */
	public SessionModel getSession(String sessionId);
}

	