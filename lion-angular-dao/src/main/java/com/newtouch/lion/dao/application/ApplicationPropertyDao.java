
/*
* Copyright (c)  2014, Newtouch
* All rights reserved. 
*
* $id: ApplicationPropertyDao.java 9552 2014年12月23日 下午3:29:57 WangLijun$
*/
package com.newtouch.lion.dao.application; 

import com.newtouch.lion.dao.BaseDao;
import com.newtouch.lion.model.application.ApplicationProperty;

/**
 * <p>
 * Title: 项目应用配置Dao层接口定义
 * </p>
 * <p>
 * Description: 项目应用配置Dao层接口定义
 * </p>
 * <p>
 * Copyright: Copyright (c) 2014
 * </p>
 * <p>
 * Company: Newtouch
 * </p>
 * 
 * @author WangLijun
 * @version 1.0
 */
public interface ApplicationPropertyDao extends BaseDao<ApplicationProperty,Long> {
	/***
	 * 根据主键删除ID
	 * @param id
	 * @return
	 */
	public int deleteById(Long id);
}

	