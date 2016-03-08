/*
 * Copyright (c)  2013, Newtouch
 * All rights reserved. 
 *
 * $id: DataColumnDaoImpl.java 9552 2013-3-27 下午6:03:20 WangLijun$
 */
package com.newtouch.lion.dao.datagrid.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.newtouch.lion.dao.datagrid.DataColumnDao;
import com.newtouch.lion.dao.impl.BaseDaoImpl;
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
@Repository("dataColumnDao")
public class DataColumnDaoImpl extends BaseDaoImpl<DataColumn,Long> implements
		DataColumnDao {

	/**
		 * 
		 */
	private static final long serialVersionUID = 6344152760634570667L;

	/* (non-Javadoc)
	 * @see com.newtouch.lion.dao.datagrid.DataColumnDao#doDeleteById(java.lang.Long)
	 */
	@Override
	public int deleteById(Long id) {
		String hql = "delete from DataColumn p where p.id=:id";
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("id", id);
		return this.updateHQL(hql, params);
	}
}
