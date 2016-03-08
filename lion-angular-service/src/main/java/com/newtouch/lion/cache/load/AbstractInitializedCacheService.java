/*
 * Copyright (c)  2014, NewTouch
 * All rights reserved. 
 *
 * $id: AbsarctLoadCache.java 9552 2014-4-11 下午07:51:42 WangLijun$
 */
package com.newtouch.lion.cache.load;

/**
 * <p>
 * Title: 缓存初始加载抽象接口定义
 * </p>
 * <p>
 * Description: 缓存初始加载抽象接口定义
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
public abstract interface AbstractInitializedCacheService {
	/** 初始加载方法 */
	public void doFindInitializedData();
}
