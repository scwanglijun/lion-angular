/*
 * Copyright (c)  2012, Newtouch
 * All rights reserved. 
 *
 * $id: TsDepartmentServiceImpl.java 9552 2012-12-31 下午7:20:38 WangLijun$
 */
package com.newtouch.lion.service.system.impl;

import com.newtouch.lion.dao.system.DepartmentDao;
import com.newtouch.lion.json.JSONParser;
import com.newtouch.lion.model.system.Department;
import com.newtouch.lion.service.AbstractService;
import com.newtouch.lion.service.datagrid.DataColumnService;
import com.newtouch.lion.service.system.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * <p>
 * Title: 部门管理Service实现类
 * </p>
 * <p>
 * Description: 用于处理部门信息查询、更新、新增
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
@Service("departmentService")
public class DepartmentServiceImpl extends AbstractService implements
		DepartmentService {

	@Autowired
	private DepartmentDao departmentDao;

	@Autowired
	private DataColumnService dataColumnService;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.lion.framework.service.system.TsDepartmentService#doSaveDepartment
	 * (com.lion.framework.model.system.TsDepartment)
	 */
	@Override
	public void doCreateDepartment(Department department) {
		departmentDao.save(department);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.lion.framework.service.system.TsDepartmentService#idoSaveDepartment
	 * (com.lion.framework.model.system.TsDepartment, java.lang.Long)
	 */
	@Override
	public void idoSaveDepartment(Department department, Long parentDeptId) {
		Department parentDepartment = this.doFindDepartmentById(parentDeptId);
		department.setDepartment(parentDepartment);
		departmentDao.save(department);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.lion.framework.service.system.DepartmentService#delete(com.lion
	 * .framework.model.system.Department)
	 */
	@Override
	public void doDelete(Department department) {
		this.departmentDao.remove(department);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.lion.framework.service.system.DepartmentService#doDelete(java
	 * .lang.Long)
	 */
	@Override
	public Department doDelete(Long id) {
		Department department = this.departmentDao.findById(id);
		this.departmentDao.remove(department);
		return department;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.lion.framework.service.system.DepartmentService#deleteById(java
	 * .lang.Long)
	 */
	@Override
	public int doDeleteById(Long id) {
		return departmentDao.doDeleteById(id);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.lion.framework.service.system.TsDepartmentService#doFindDepartmentById
	 * (java.lang.Long)
	 */
	@Override
	public Department doFindDepartmentById(Long id) {
		return departmentDao.findById(id);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.lion.framework.service.system.DepartmentService#doGetById(java
	 * .lang.Long)
	 */
	@Override
	public Department doGetById(Long id) {
		return this.departmentDao.getById(id);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.lion.framework.service.system.DepartmentService#doUpdate(com.lion
	 * .framework.model.system.Department)
	 */
	@Override
	public Department doUpdate(Department department) {
		this.doCreateDepartment(department);
		return department;
	}

	@Override
	public List<Department> doFindFirstLevel() {
		return departmentDao.doFindFirstLevel();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.lion.framework.service.system.DepartmentService#makeTreeDepartment
	 * ()
	 */
	@Override
	public Department makeTreeDepartment(Department department) {
		for (Department departmentTemp : department.getDepartments()) {
			if (departmentTemp.getDepartments().iterator().hasNext()) {
				departmentTemp.getDepartments().iterator().hasNext();
			}
			departmentTemp.getNameEn();
		}
		return department;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.lion.framework.service.system.DepartmentService#doFindAll()
	 */
	@Override
	public List<Department> doFindAll() {
		return departmentDao.findAll();
	}

	/** 访问下一级结构 */
	@SuppressWarnings("unused")
	private void makeTreeChildren(Department department) {
		for (Department departmentTemp : department.getDepartments()) {
			if (departmentTemp.getDepartments().iterator().hasNext()) {
				this.makeTreeChildren(departmentTemp);
			}
			departmentTemp.getNameEn();
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.lion.framework.service.system.DepartmentService#
	 * doFindFirstLevelToTree()
	 */
	@Override
	public String doFindFirstLevelToTree() {
		List<Department> departments = this.doFindFirstLevel();
		Set<String> properties = new HashSet<String>();
		properties.add("id");
		properties.add("parentDepartmentId");
		properties.add("nameEn");
		properties.add("nameZh");
		properties.add("description");
		properties.add("departmentNo");
		properties.add("departments");
		properties.add("editable");
		return JSONParser.toJSONString(departments, properties);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.lion.framework.service.system.DepartmentService#doFindAll(java
	 * .lang.String)
	 */
	@Override
	public String doFindAll(String tableId) {
		Set<String> properties = this.dataColumnService
				.doFindColumnsByTableId(tableId);
		List<Department> departments = this.doFindFirstLevel();
		String jsonStr = JSONParser.toJSONDataGridString(departments,properties);
		return jsonStr.replace("parentDepartmentId", "_parentId");
	}
	
	
	

	
	/***
	 * 将List<Department>转换为Map<Long,Department>
	 * @param departments List
	 * @return Map<Long,Department>
	 */
	@SuppressWarnings("unused")
	private Map<Long,Department>  convertToMap(List<Department> departments){
		Map<Long,Department> departmentsMap=new HashMap<Long, Department>();
		for(Department department:departments){
			departmentsMap.put(department.getId(),department);
		}
		return departmentsMap;
	}
	
	
	
	
	
	/* (non-Javadoc)
	 * @see com.newtouch.lion.service.system.DepartmentService#doFindDapartmentAll()
	 */
	@Override
	public Map<Object, Object> doFindDapartmentAll() {
		List<Department>  list=this.doFindAll();
		Map<Object,Object> departmentsMap=new HashMap<Object, Object>();
		for(Department department:list){
			departmentsMap.put(department.getId(),department);
		}
		return departmentsMap;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.lion.framework.service.system.DepartmentService#makeTree(java.
	 * util.List, java.util.List)
	 */
	@Override
	public List<Department> makeTree(List<Department> elements,
			List<Department> structures) {
		List<Department> root = new ArrayList<Department>();

		if (elements.size() == 0) {
			return root;
		}

		for (Department child : elements) {
			if ((child.getDepartment() == null)
					|| (!(structures.contains(child.getDepartment())))) {
				root.add(child);
			} else {
				int index = structures.indexOf(child.getDepartment());

				Department parent = (Department) structures.get(index);

				List<Department> parentChilds = parent
						.getSortedChildDepartment();

				if (parentChilds == null) {
					parentChilds = new ArrayList<Department>();
					parent.setSortedChildDepartment(parentChilds);
				}

				parentChilds.add(child);
			}
		}
		return root;
	}

}
