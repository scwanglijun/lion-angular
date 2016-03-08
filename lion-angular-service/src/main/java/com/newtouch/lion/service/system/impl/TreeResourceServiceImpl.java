/*
* Copyright (c)  2015, Newtouch
* All rights reserved. 
*
* $id: TreeResourceServiceImpl.java 9552 2015年2月6日 下午7:03:01 WangLijun$
*/
package com.newtouch.lion.service.system.impl; 

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.newtouch.lion.model.system.Resource;
import com.newtouch.lion.service.system.TreeResourceService;
import com.newtouch.lion.ztree.TreeNode;

/**
 * <p>
 * Title: 资源树结构Service定义扩展
 * </p>
 * <p>
 * Description: 资源树结构Service定义扩展，支持树型结构
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
public class TreeResourceServiceImpl extends ResourceServiceImpl implements
		TreeResourceService {

	/* (non-Javadoc)
	 * @see com.newtouch.lion.service.system.TreeResourceService#doFindResourceToTree()
	 */
	@Override
	public List<TreeNode> doFindResourceToTree() {
		List<TreeNode> list=new ArrayList<TreeNode>();
		List<Resource>  resources=this.doFindAll();
		for(Resource resource:resources){
			TreeNode node=null;
			if(resource.getParentResourceId()==null||resource.getParentResourceId().equals(0L)){
				node=new TreeNode(resource.getId(),0L,resource.getNameZh(),Boolean.TRUE);
			}else{
				node=new TreeNode(resource.getId(),resource.getParentResourceId(),resource.getNameZh(),Boolean.FALSE);
			}	
			list.add(node);
		}
		return list;
	}

}

	