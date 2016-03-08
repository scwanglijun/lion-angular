/*
* Copyright (c)  2015, Newtouch
* All rights reserved. 
*
* $id: ResourceConvertUtil.java 9552 2015年1月4日 下午5:48:18 WangLijun$
*/
package com.newtouch.lion.util; 

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.newtouch.lion.model.system.Resource;

/**
 * <p>
 * Title: 资源转换工具类
 * </p>
 * <p>
 * Description: 资源转换工具类
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
public class ResourceConvertUtil {
	
	/***
	 * 将Set集合转换为Map集合
	 * @param resources Set集合
	 * @return Map<Long,Resource>
	 */
	public  static Map<Long,Resource> convertSetToMap(Set<Resource> resources){
		Map<Long,Resource> resourcesMap=new HashMap<Long, Resource>();
		for(Resource resource:resources){
			resourcesMap.put(resource.getId(), resource); 
		}
		return resourcesMap;
	}
	/***
	 * 将List集合转换为Map集合
	 * @param resources List集合
	 * @return Map<Long,Resource>
	 */
	public  static Map<Long,Resource> convertListToMap(List<Resource> resources){
		Map<Long,Resource> resourcesMap=new HashMap<Long, Resource>();
		for(Resource resource:resources){
			resourcesMap.put(resource.getId(), resource); 
		}
		return resourcesMap;
	}
}

	