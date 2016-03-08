/*
* Copyright (c)  2014, Newtouch
* All rights reserved. 
*
* $id: MenuServiceImpl.java 9552 2014年12月31日 下午5:32:27 WangLijun$
*/
package com.newtouch.lion.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.newtouch.lion.common.codelist.CodeListConstant;
import com.newtouch.lion.model.menu.Menu;
import com.newtouch.lion.model.system.Resource;
import com.newtouch.lion.service.menu.MenuService;
import com.newtouch.lion.service.system.ResourceService;
import com.newtouch.lion.util.MenuTreeUtil;
import com.newtouch.lion.util.ResourceConvertUtil;

/**
 * <p>
 * Title: Menu Service 实现类
 * </p>
 * <p>
 * Description: Menu Service 实现类
 * </p>
 * <p>
 * Copyright: Copyright (c) 2014
 * </p>
 * <p>
 * Company: Newtouch
 * </p>
 * 
 * @author WangLijun
 * @version 1.0
 */
@Service
public class MenuServiceImpl   implements MenuService{

	/**资源*/
	@Autowired
	private ResourceService resourceService;
	

	/* (non-Javadoc)
	 * @see com.newtouch.lion.service.menu.MenuService#doFindByUser(java.lang.String)
	 */
	@Override
	public List<Menu> doFindByUserName(String userName) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see com.newtouch.lion.service.menu.MenuService#doFindByUserId(java.lang.Long)
	 */
	@Override
	public List<Menu> doFindByUserId(Long userId) {
		List<Resource> resources=resourceService.doFindByParentId(1L);
		String[]  menuResourceType={CodeListConstant.RESTYPE_MODULE,CodeListConstant.RESTYPE_MODULE_MENU_CATEGORY,CodeListConstant.RESTYPE_MODULE_CATEGORY_ITEM};
		List<Resource>  userResources=resourceService.doFindByUserIdAndType(userId, menuResourceType);
		Map<Long,Resource>  menuResourcesMap=ResourceConvertUtil.convertListToMap(userResources);
		List<Menu> menus=MenuTreeUtil.treeResource(resources, menuResourcesMap,Boolean.TRUE,0);
		return menus;
	}

	/* (non-Javadoc)
	 * @see com.newtouch.lion.service.menu.MenuService#doFindByUserId(java.lang.Long, java.lang.String, java.lang.String)
	 */
	@Override
	public List<Menu> doFindByUserId(Long userId, String requestURL,String contextPath) {
		List<Resource> resources=resourceService.doFindByParentId(1L);
		String[]  menuResourceType={CodeListConstant.RESTYPE_MODULE,CodeListConstant.RESTYPE_MODULE_MENU_CATEGORY,CodeListConstant.RESTYPE_MODULE_CATEGORY_ITEM};
		List<Resource>  userResources=resourceService.doFindByUserIdAndType(userId, menuResourceType);
		Map<Long,Resource>  menuResourcesMap=ResourceConvertUtil.convertListToMap(userResources);
		List<Menu> menus=MenuTreeUtil.treeResource(resources, menuResourcesMap,Boolean.TRUE,0,requestURL,contextPath);
		return menus;
	}
	
	
	
	
}

	