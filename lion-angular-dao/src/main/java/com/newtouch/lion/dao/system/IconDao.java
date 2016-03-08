
/*
* Copyright (c)  2015, NewTouch
* All rights reserved. 
*
* $id: IconDao.java 9552 Mar 4, 2015 11:29:43 AM MaoJiaWei$
*/
package com.newtouch.lion.dao.system; 

import java.util.List;

import com.newtouch.lion.dao.BaseDao;
import com.newtouch.lion.model.system.Icon;

/**
 * <p>
 * Title: 图标icon的Dao类
 * </p>
 * <p>
 * Description: 图标icon的Dao类
 * </p>
 * <p>
 * Copyright: Copyright (c) 2015
 * </p>
 * <p>
 * Company: NewTouch
 * </p>
 * 
 * @author MaoJiaWei
 * @version 1.0
 */
public interface IconDao extends BaseDao<Icon,Long>{
	
	public int doDeleteById(Long id);
	
	public Icon doFindTypeByIconClass(String iconClass);
	
	public List<Icon> doFindByType(String iconType);
}

	