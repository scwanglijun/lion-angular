/*
* Copyright (c)  2015, Newtouch
* All rights reserved. 
*
* $id: ExtendDepartmentServiceImpl.java 9552 2015年2月2日 下午5:50:03 WangLijun$
*/
package com.newtouch.lion.service.system.impl; 

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.newtouch.lion.model.system.Department;
import com.newtouch.lion.service.system.ExtendDepartmentService;
import com.newtouch.lion.ztree.TreeNode;

/**
 * <p>
 * Title:  扩展DepartmentService
 * </p>
 * <p>
 * Description:  扩展DepartmentService 实现，支持ZTree
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
@Service
public class ExtendDepartmentServiceImpl extends DepartmentServiceImpl implements ExtendDepartmentService{
	/* (non-Javadoc)
	 * @see com.newtouch.lion.service.system.DepartmentService#doFindDepartmentToTree()
	 */
	@Override
	public List<TreeNode> doFindDepartmentToTree() {		
		
		List<TreeNode> list=new ArrayList<TreeNode>();
		
		List<Department>  departmentsAll=this.doFindAll();
		for(Department department:departmentsAll){
			TreeNode node=null;
			if(department.getParentDepartmentId()==null||department.getParentDepartmentId().equals(0L)){
				node=new TreeNode(department.getId(),0L,department.getNameZh(),Boolean.TRUE);
			}else{
				node=new TreeNode(department.getId(),department.getParentDepartmentId(),department.getNameZh(),Boolean.FALSE);
			}	
			list.add(node);
		}
		return list;
	}
}

	