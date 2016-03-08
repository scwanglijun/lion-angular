/*
 * Copyright (c)  2014, NewTouch
 * All rights reserved. 
 *
 * $id: ApplicationInitializedListener.java 9552 2014-4-11 下午07:49:00 WangLijun$
 */
package com.newtouch.lion.listener;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;

import com.newtouch.lion.cache.load.AbstractInitializedCacheService;

/**
 * <p>
 * Title: 初始化加载缓存数据
 * </p>
 * <p>
 * Description: 初始化加载缓存数据
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
public class ApplicationInitializedListenerService implements
		ApplicationListener<ApplicationEvent> {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	private static boolean initialized = Boolean.TRUE;

	private List<AbstractInitializedCacheService> abstractInitializedCacheServices;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.springframework.context.ApplicationListener#onApplicationEvent(org
	 * .springframework.context.ApplicationEvent)
	 */
	@Override
	public void onApplicationEvent(ApplicationEvent event) {
		if (initialized) {
			logger.info("init start...");
			initialized = Boolean.FALSE;
			this.doInitializedData();
			logger.info("init end...");
		}
	}

	private void doInitializedData() {
		for (AbstractInitializedCacheService abstractInitializedCacheService : abstractInitializedCacheServices) {
			if (abstractInitializedCacheService != null) {
				abstractInitializedCacheService.doFindInitializedData();
			}
		}
	}

	/**
	 * @return the abstractInitializedCacheServices
	 */
	public List<AbstractInitializedCacheService> getAbstractInitializedCacheServices() {
		return abstractInitializedCacheServices;
	}

	/**
	 * @param abstractInitializedCacheServices
	 *            the abstractInitializedCacheServices to set
	 */
	public void setAbstractInitializedCacheServices(
			List<AbstractInitializedCacheService> abstractInitializedCacheServices) {
		this.abstractInitializedCacheServices = abstractInitializedCacheServices;
	}

}
