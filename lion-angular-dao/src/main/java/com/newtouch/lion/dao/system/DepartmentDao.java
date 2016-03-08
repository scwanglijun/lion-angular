/*
* Copyright (c)  2012, Newtouch
* All rights reserved. 
*
* $id: TsDepartmentDao.java 9552 2012-12-16 下午8:38:01 WangLijun$
*/
package com.newtouch.lion.dao.system; 

import java.util.List;

import com.newtouch.lion.dao.BaseDao;
import com.newtouch.lion.model.system.Department;
	/**
 * <p>
 * Title: 
 * </p>
 * <p>
 * Description: 
 * </p>
 * <p>
 * Copyright: Copyright (c) 2012
 * </p>
 * <p>
 * Company: lion
 * </p>
 * 
 * @author WangLijun
 * @version 1.0
 */
public interface DepartmentDao extends BaseDao<Department,Long>{
	
	public int doDeleteById(Long id);
	
	public List<Department> doFindFirstLevel();

}

	