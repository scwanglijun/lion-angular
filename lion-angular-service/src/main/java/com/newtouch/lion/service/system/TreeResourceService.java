/*
* Copyright (c)  2015, Newtouch
* All rights reserved. 
*
* $id: TreeResourceService.java 9552 2015年2月6日 下午7:00:29 WangLijun$
*/
package com.newtouch.lion.service.system; 

import java.util.List;

import com.newtouch.lion.ztree.TreeNode;

/**
 * <p>
 * Title: 资源树结构Service定义扩展
 * </p>
 * <p>
 * Description: 资源树结构Service定义扩展
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
public interface TreeResourceService extends ResourceService {
	/**
	 * 构建ZTree结构的部门列表
	 * @return List<TreeNode>
	 */
	public List<TreeNode> doFindResourceToTree();
}

	