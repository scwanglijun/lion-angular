/*
 * Copyright (c)  2012, Newtouch
 * All rights reserved. 
 *
 * $id: ResourceServiceImpl.java 9552 2012-12-31 下午9:56:07 WangLijun$
 */
package com.newtouch.lion.service.system.impl;

import com.newtouch.lion.common.Assert;
import com.newtouch.lion.common.codelist.CodeListConstant;
import com.newtouch.lion.dao.system.ResourceDao;
import com.newtouch.lion.event.PushMessageEvent;
import com.newtouch.lion.json.JSONParser;
import com.newtouch.lion.model.system.Resource;
import com.newtouch.lion.model.system.Role;
import com.newtouch.lion.service.AbstractService;
import com.newtouch.lion.service.datagrid.DataColumnService;
import com.newtouch.lion.service.system.ResourceService;
import com.newtouch.lion.tree.Tree;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Set;

/**
 * <p>
 * Title: 资源管理Service实现类
 * </p>
 * <p>
 * Description: 资源管理Service实现类
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
@Service("resourceService")
public class ResourceServiceImpl extends AbstractService implements
		ResourceService {
 

	@Autowired
	private ResourceDao resourceDao;
	
	@Autowired
	private DataColumnService dataColumnService;
	@Autowired
	private PushMessageEvent pushMessageEvent;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.newtouch.lion.service.system.ResourceService#idSaveTsResource
	 * (com.lion.framework.model.system.Resource)
	 */
	@Override
	public void doCreateResource(Resource resource) {
		if(resource.getParentResourceId()!=null&&resource.getType().equals(CodeListConstant.RESTYPE_MODULE_CATEGORY_ITEM)){
			Resource  parentResource=this.doFindById(resource.getParentResourceId());
			parentResource.setIsLeaf(Boolean.FALSE);
		parentResource.setType(CodeListConstant.RESTYPE_MODULE_MENU_CATEGORY);
		resource.setResource(parentResource);
	}
		resourceDao.save(resource);
		pushMessageEvent.pushResource(resource);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.newtouch.lion.service.system.ResourceService#doDelete(java.
	 * util.Collection)
	 */
	@Override
	public void doDelete(Collection<Long> resourceIds) {
		for (Long resourceId : resourceIds)
			this.doDelete(resourceId);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.newtouch.lion.service.system.ResourceService#doDelete(java.
	 * lang.Long)
	 */
	@Override
	public Resource doDelete(Long resourceId) {
		Resource resource = this.doFindById(resourceId);
		Resource parentResource = resource.getResource();

		if (parentResource != null) {
			boolean empty = parentResource.getResources().size() <= 1?true:false;
			parentResource.setIsLeaf(Boolean.valueOf(empty));
			if(empty){
				if(parentResource.getType().equals(CodeListConstant.RESTYPE_MODULE_MENU_CATEGORY)){
					parentResource.setType(CodeListConstant.RESTYPE_MODULE_CATEGORY_ITEM);
				}
				this.resourceDao.save(parentResource);
			}
		}

		this.resourceDao.remove(resource);
		return resource;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.newtouch.lion.service.system.ResourceService#doFindById(java
	 * .lang.Long)
	 */
	@Override
	public Resource doFindById(Long resourceId) {
		return this.resourceDao.findById(resourceId);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.newtouch.lion.service.system.ResourceService#doFindAll()
	 */
	@Override
	public List<Resource> doFindAll() {
		return resourceDao.doFindAll();
	}

	
	
	
	/* (non-Javadoc)
	 * @see com.newtouch.lion.service.system.ResourceService#doFindAuthAll()
	 */
	@Override
	public List<Resource> doFindAuthAll() {
	    List<Resource> resources=this.doFindAll();
	    for(Resource resource:resources){
	    	for(Role role:resource.getRoles()){
	    		role.getNameEn();
	    	}
	    }
		return resources;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.newtouch.lion.service.system.ResourceService#doFindByParentId
	 * (java.lang.Long)
	 */
	@Override
	public List<Resource> doFindByParentId(Long parentResourceId) {
		Assert.notNull(parentResourceId);
		return resourceDao.doFindByParentId(parentResourceId);
	}	

	
	
	@Override
	public List<Tree> doFindByResourceId(Long resourceId) {
		Assert.notNull(resourceId);
		return resourceDao.doFindByResourceId(resourceId);
	}

	/* (non-Javadoc)
	 * @see com.newtouch.lion.service.system.ResourceService#doFindFirstLevel()
	 */
	@Override
	public List<Resource> doFindFirstLevel() {
		
		return resourceDao.doFindFirstLevel();
	}

	/* (non-Javadoc)
	 * @see com.newtouch.lion.service.system.ResourceService#doFindByType(java.lang.String[])
	 */
	@Override
	public List<Resource> doFindByType(String[] resTypes) {
		return resourceDao.doFindByType(resTypes);
	}

	/* (non-Javadoc)
	 * @see com.newtouch.lion.service.system.ResourceService#doGetById(java.lang.Long)
	 */
	@Override
	public Resource doGetById(Long id) {
		return this.resourceDao.getById(id);
	}

	/* (non-Javadoc)
	 * @see com.newtouch.lion.service.system.ResourceService#doUpdate(com.newtouch.lion.model.system.Resource)
	 */
	@Override
	public Resource doUpdate(Resource resource) {
		this.resourceDao.update(resource);
		pushMessageEvent.pushResource(resource);
		return resource;
	}

	/* (non-Javadoc)
	 * @see com.newtouch.lion.service.system.ResourceService#doFindAllToTree(java.lang.String)
	 */
	@Override
	public String doFindAllToTree(String tableId) {
		Set<String> properties=this.dataColumnService.doFindColumnsByTableId(tableId);
		List<Resource> resources=this.doFindFirstLevel();
		for(Resource resource:resources){
			resource.getResources();
		}
		properties.add("permission");
		String jsonStr=JSONParser.toJSONString(resources, properties);
		return jsonStr.replace("parentResourceId", "_parentId");
	}

 
	/* (non-Javadoc)
	 * @see com.newtouch.lion.service.system.ResourceService#doFindByUserIdAndType(java.lang.Long, java.lang.String[])
	 */
	@Override
	public List<Resource> doFindByUserIdAndType(Long userId,String[] resourceType) {
		Assert.notNull(userId);
	    Assert.notNull(resourceType);
	    return resourceDao.doFindByUserIdAndType(userId,resourceType);
	}
	
	 

	/* (non-Javadoc)
	 * @see com.newtouch.lion.service.system.ResourceService#doFindByUserIdAndType(java.lang.Long, java.lang.Long, java.lang.String[])
	 */
	@Override
	public List<Resource> doFindByUserIdAndType(Long userId,
			Long parentResourceId, String[] resourceType) {
		Assert.notNull(userId);
		Assert.notNull(parentResourceId);
	    Assert.notNull(resourceType);
	   return resourceDao.doFindByUserIdAndType(userId,parentResourceId,resourceType);
	}

	
	
	/* (non-Javadoc)
	 * @see com.newtouch.lion.service.system.ResourceService#doFindByUserIdAndType(java.lang.Long, java.lang.Long, java.lang.String[], java.lang.String[])
	 */
	@Override
	public List<Resource> doFindByUserIdAndType(Long userId,
			Long parentResourceId, String[] parentResourceType,
			String[] childrenResourceType) {
		Assert.notNull(userId);
		Assert.notNull(parentResourceId);
		Assert.notNull(parentResourceType);
	    Assert.notNull(childrenResourceType);
	    return resourceDao.doFindByUserIdAndType(userId,parentResourceId,parentResourceType,childrenResourceType);
	}

	/* (non-Javadoc)
	 * @see com.newtouch.lion.service.system.ResourceService#doFindByUserIdAndType(java.lang.Long, java.lang.String[], java.lang.String[])
	 */
	@Override
	public List<Resource> doFindByUserIdAndType(Long userId,
			String[] parentResourceType, String[] resourceType) {
		return resourceDao.doFindByUserIdAndType(userId,parentResourceType,resourceType);
	}
	
	/* (non-Javadoc)
	 * @see ccom.newtouch.lion.service.system.ResourceService#doFindByUserId(java.lang.Long)
	 */
	@Override
	public List<Resource> doFindByUserId(Long userId) {
		Assert.notNull(userId);
	    return resourceDao.doFindByUserId(userId);
	}
	
}
