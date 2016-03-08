/*
 * Copyright (c)  2015, Newtouch
 * All rights reserved. 
 *
 * $id: SessionServiceImpl.java 9552 2015年2月27日 上午11:06:52 WangLijun$
 */
package com.newtouch.lion.service.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.mgt.SimpleSession;
import org.apache.shiro.session.mgt.eis.SessionDAO;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.support.DefaultSubjectContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.newtouch.lion.common.user.UserInfo;
import com.newtouch.lion.model.session.SessionModel;
import com.newtouch.lion.service.AbstractService;
import com.newtouch.lion.service.session.SessionService;

/**
 * <p>
 * Title: 用户会话管理服务实现
 * </p>
 * <p>
 * Description: 用户会话管理服务实现（获取用户在线会话列表、强制退出会话）
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
@Service
public class SessionServiceImpl extends AbstractService implements
		SessionService {
	

	/** SessionDao */
	@Autowired
	private SessionDAO sessionDAO;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.newtouch.lion.service.session.SessionService#getActiveSessions()
	 */
	@Override
	public List<SessionModel> getActiveSessions() {
		Collection<Session> sessions = sessionDAO.getActiveSessions();
		List<SessionModel> models = new ArrayList<SessionModel>();
		for (Session session : sessions) {
			SessionModel sessionModel = this.convertModel(session);
		    if(StringUtils.isNotEmpty(sessionModel.getUsername())){
		    	models.add(sessionModel);
		    }
		}
		return models;
	}
	
	
	
	/* (non-Javadoc)
	 * @see com.newtouch.lion.service.session.SessionService#forceLogout(java.lang.String)
	 */
	@Override
	public boolean forceLogout(String sessionId) {
		 Session session = sessionDAO.readSession(sessionId);
		 session.setAttribute(SESSION_FORCE_LOGOUT_KEY, Boolean.TRUE);
		 return true;
	}



	/* (non-Javadoc)
	 * @see com.newtouch.lion.service.session.SessionService#getSession(java.lang.String)
	 */
	@Override
	public SessionModel getSession(String sessionId) {
		 Session session = sessionDAO.readSession(sessionId);
		 return this.convertModel(session);
	}



	/***
	 * 会话转换服务
	 * @param session
	 * @return   SessionModel
	 */
	private SessionModel convertModel(Session session) {
		SessionModel model = new SessionModel();
		//会话ID
		model.setId((String) session.getId());
		//访问IP
		model.setHost(session.getHost());
		//最后访问时间
		model.setLastAccessTime(session.getLastAccessTime());
		//会话开始时间
		model.setStartTimestamp(session.getStartTimestamp());
		//会话有效时间
		model.setTimeout(session.getTimeout());
		
	    if(session instanceof  SimpleSession){
		   SimpleSession  simpleSession=(SimpleSession) session;
		  //是否有效
		   model.setValid(simpleSession.isValid());
		   //是否过期
		   model.setExpired(simpleSession.isExpired());
	    }
		//获取用户名及用户ID
	    PrincipalCollection principalMap=(PrincipalCollection) session.getAttribute(DefaultSubjectContext.PRINCIPALS_SESSION_KEY);
	    if(principalMap!=null){
	    	UserInfo userInfo = (UserInfo)principalMap.getPrimaryPrincipal();
	    	model.setUserId(userInfo.getId());
	    	model.setUsername(userInfo.getUsername());
	    }
		return model;
	}
}
