/*
* Copyright (c)  2015, Newtouch
* All rights reserved. 
*
* $id: ParameterUtil.java 9552 2015年3月2日 下午1:40:45 WangLijun$
*/
package com.newtouch.lion.cache.system; 

import org.springframework.beans.factory.annotation.Autowired;

import com.newtouch.lion.common.SpringContextUtil;

/**
 * <p>
 * Title: 参数获取工具
 * </p>
 * <p>
 * Description: 参数获取工具，从缓存获取
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
public class ParameterUtil {
	
	/**消息代码*/
	@Autowired
	private ParameterCacheService  parameterCacheService;
	/***
	 * 根据KEY获取数据
	 * @param key
	 * @return Value
	 */
	public static String getValue(String key){
		ParameterCacheService parameterCacheService=(ParameterCacheService) SpringContextUtil.getBean("parameterCacheService");
		return parameterCacheService.getValue(key);
	}
}

	