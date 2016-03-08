/*
 * Copyright (c)  2013, Newtouch
 * All rights reserved. 
 *
 * $id: DataColumnDao.java 9552 2013-3-27 下午5:55:56 WangLijun$
 */
package com.newtouch.lion.dao.datagrid;

import com.newtouch.lion.dao.BaseDao;
import com.newtouch.lion.model.datagrid.DataColumn;

/**
 * <p>
 * Title:
 * </p>
 * <p>
 * Description:
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
public interface DataColumnDao extends BaseDao<DataColumn,Long> {
	/***
	 * 根据ID删除记录
	 * @param id
	 * @return
	 */
	public int deleteById(Long id);
}
