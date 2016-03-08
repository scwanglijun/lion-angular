/*
	* Copyright (c)  2013, Newtouch
	* All rights reserved. 
	*
	* $id: DataGridDaoImpl.java 9552 2013-3-27 下午6:03:33 WangLijun$
	*/
package com.newtouch.lion.dao.datagrid.impl; 

import org.springframework.stereotype.Repository;

import com.newtouch.lion.dao.datagrid.DataGridDao;
import com.newtouch.lion.dao.impl.BaseDaoImpl;
import com.newtouch.lion.model.datagrid.DataGrid;
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
@Repository("dataGridDao")
public class DataGridDaoImpl extends BaseDaoImpl<DataGrid,Long>  implements DataGridDao{

		/**
		 * 
		 */
		private static final long serialVersionUID = 1034578873715065160L;
	
}

	