/*
 * Copyright (c)  2015, Newtouch
 * All rights reserved. 
 *
 * $id: ShiroResouceServiceImpl.java 9552 2015年3月3日 下午6:21:42 WangLijun$
 */
package com.newtouch.lion.web.shiro.service;

import com.newtouch.lion.model.system.Resource;
import com.newtouch.lion.model.system.Role;
import com.newtouch.lion.service.system.ResourceService;
import com.newtouch.lion.web.shiro.model.AuthorityModel;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * Title: Shiro角色权限资源管理
 * </p>
 * <p>
 * Description: Shiro角色权限资源管理
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
public class ShiroResourceManagerImpl  implements ShiroResourceManager {
	/** 资源读取类 */
	@Autowired
	private ResourceService resourceService; 
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.newtouch.lion.web.shiro.service.ShiroResourceService#findAll()
	 */ 
	@Override
	public List<AuthorityModel> doFindAll() {
		List<Resource> resources = resourceService.doFindAuthAll();
		List<AuthorityModel> authorityModels = new ArrayList<AuthorityModel>();
		for (Resource resource : resources) {
			if(StringUtils.isEmpty(resource.getPath())){
				continue;
			}
			AuthorityModel authorityModel = new AuthorityModel();
			authorityModel.setId(resource.getId());
			authorityModel.setUrl(resource.getPath());
			// 添加角色
			for (Role role : resource.getRoles()) {
				authorityModel.addRole(role.getNameEn());
			}
			// 添加权限模型
			if(!StringUtils.isEmpty(resource.getPermission())){
				authorityModel.addPermission(resource.getPermission());
			}
			authorityModels.add(authorityModel);
		}
		return authorityModels;
	}
}
