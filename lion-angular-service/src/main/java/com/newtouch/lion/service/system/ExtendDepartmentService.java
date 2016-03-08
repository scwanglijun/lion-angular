/*
* Copyright (c)  2015, Newtouch
* All rights reserved. 
*
* $id: ExtendDepartmentService.java 9552 2015年2月2日 下午5:47:52 WangLijun$
*/
package com.newtouch.lion.service.system; 

import java.util.List;

import com.newtouch.lion.ztree.TreeNode;

/**
 * <p>
 * Title: 扩展DepartmentService
 * </p>
 * <p>
 * Description: 扩展DepartmentService,支持ZTree树型结构
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
public interface ExtendDepartmentService extends DepartmentService{
	/**
	 * 构建ZTree结构的部门列表
	 * @return List<TreeNode>
	 */
	public List<TreeNode> doFindDepartmentToTree();
}

	