/*
 * Copyright (c)  2014, NewTouch
 * All rights reserved. 
 *
 * $id: ResourceComparator.java 9552 2014-4-7 下午11:31:28 WangLijun$
 */
package com.newtouch.lion.comparator;

import java.util.Comparator;

import com.newtouch.lion.model.system.Resource;

/**
 * <p>
 * Title: 菜单排序
 * </p>
 * <p>
 * Description: 菜单排序实现类，根据指定顺序进行升序排序，如果顺序相等则根据菜单ID升序排序
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
public class ResourceComparator implements Comparator<Resource> {

	@Override
	public int compare(Resource resource1, Resource resource2) {

		if (resource1.getSeqNum() == resource2.getSeqNum()) {
			return resource1.getId() > resource2.getId() ? 1 : -1;
		}
		return resource1.getSeqNum() > resource2.getSeqNum() ? 1 : -1;
	}

}
