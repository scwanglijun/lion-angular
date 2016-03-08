/*
 * Copyright (c)  2012, Newtouch
 * All rights reserved. 
 *
 * $id: TsDepartmentDaoImpl.java 9552 2012-12-16 下午8:39:25 WangLijun$
 */
package com.newtouch.lion.dao.system.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.newtouch.lion.dao.impl.BaseDaoImpl;
import com.newtouch.lion.dao.system.DepartmentDao;
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
 * Company: Newtouch
 * </p>
 * 
 * @author WangLijun
 * @version 1.0
 */
@Repository("departmentDao")
public class DepartmentDaoImpl extends BaseDaoImpl<Department,Long> implements
		DepartmentDao {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8194460110365083678L;

	@Override
	public int doDeleteById(Long id) {
		String hql = "delete from Department p where p.id=:id";
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("id", id);
		return this.updateHQL(hql, params);
	}
	
	@Override
	public List<Department> doFindFirstLevel() {
		String hql = "from Department where  parentDepartmentId is null order by departmentNO DESC";

		Map<String, Object> params = new HashMap<String, Object>();

		List<Department> departments = this.query(hql, params);
		
		return departments;
	}
	
	

}
