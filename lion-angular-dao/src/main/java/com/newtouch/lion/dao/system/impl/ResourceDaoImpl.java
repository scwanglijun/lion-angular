/*
 * Copyright (c)  2012, Newtouch
 * All rights reserved. 
 *
 * $id: TsResourceDaoImpl.java 9552 2012-12-31 下午6:55:36 WangLijun$
 */
package com.newtouch.lion.dao.system.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.newtouch.lion.common.Assert;
import com.newtouch.lion.common.codelist.CodeListConstant;
import com.newtouch.lion.dao.impl.BaseDaoImpl;
import com.newtouch.lion.dao.system.ResourceDao;
import com.newtouch.lion.model.system.Resource;
import com.newtouch.lion.tree.Tree;

/**
 * <p>
 * Title: 资源管理数据存储实现类
 * </p>
 * <p>
 * Description: 资源管理数据存储实现类
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
@Repository("resourceDao")
public class ResourceDaoImpl extends BaseDaoImpl<Resource,Long> implements
		ResourceDao {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5311893761504147007L;

	@Override
	public List<Resource> doFindAll() {
		String hql = "from Resource";
		return this.query(hql, null);
	}
	
	@Override
	public List<Resource> doFindByParentId(Long parentResourceId) {
		String   hql="from Resource r where r.resource.id=:parentResourceId  order by r.seqNum";
		Map<String,Object> params = new HashMap<String,Object>();
		params.put("parentResourceId", parentResourceId);
		return this.query(hql, params);
	}	
	
	@Override
	public List<Tree> doFindByResourceId(Long resourceId) {
		Assert.notNull(resourceId);
		String   hql="from Resource r where r.resource.id=:parentResourceId  order by r.seqNum";
		Map<String,Object> params = new HashMap<String,Object>();
		params.put("parentResourceId", resourceId);
		List<Resource>  resources=this.query(hql, params);
		List<Tree> list=new ArrayList<Tree>();
		for(Resource resource:resources){
			Tree tree = new Tree();
			tree.setChecked(false);
			if (resource.getResource() != null) {
				tree.setPid(resource.getResource().getId().toString());
			}
			tree.setText(resource.getNameZh());
			//tree.setIconCls(resource.getIcon());
			Map<String, Object> attr = new HashMap<String, Object>();
			attr.put("url",resource.getAttributes().getPath());
			tree.setAttributes(attr);
			list.add(tree);
		}

		return  list;	
	}

	@Override
	public List<Resource> doFindFirstLevel() {
		String hql = "from Resource where  parentResourceId is null order by seqNum asc";

		Map<String, Object> params = new HashMap<String, Object>();

		List<Resource> resources = this.query(hql, params);

		return resources;
	}
	
	@Override
	public List<Resource> doFindByType(String[] resTypes) {
		String hql = "from Resource where type in(:resTypes) order by seqNum asc";

		Map<String, Object> params = new HashMap<String, Object>();
		params.put("resTypes", resTypes);
		List<Resource> resources = this.query(hql, params);

		return resources;
	}

	@Override
	public List<Resource> doFindByUserIdAndType(Long userId,
			String[] resourceType) {
		String hql = "select distinct r from User user join user.roles role join role.resources r  where user.id=:userId and r.type in (:resourceType) order by r.seqNum asc,r.parentResourceId asc";
	    Map<String,Object> params = new HashMap<String,Object>();
	    params.put("userId", userId);
	    params.put("resourceType", Arrays.asList(resourceType));
		return this.query(hql, params);
	}

	@Override
	public List<Resource> doFindByUserIdAndType(Long userId,
		Long parentResourceId, String[] resourceType) {
	 	String hql = "select distinct r from User user join user.roles role join role.resources r  where (r.id in(select distinct r.parentResourceId from User user join user.roles role join role.resources r  where user.id=:childrenUserId and r.type in (:childrenResourceType)) and r.type in(:parentResourceType)  and r.parentResourceId=:parentResourceId ) or (r.parentResourceId=:parentResourceId and  user.id=:userId and r.type in (:resourceType)) order by r.seqNum asc,r.parentResourceId asc";
	    Map<String,Object> params = new HashMap<String,Object>();
	    params.put("userId", userId);
	    params.put("childrenUserId", userId);
	    params.put("parentResourceType", CodeListConstant.RESTYPE_MODULE_MENU_CATEGORY);	    
	    params.put("childrenResourceType",CodeListConstant.RESTYPE_MODULE_CATEGORY_ITEM);
	    params.put("parentResourceId", parentResourceId);
	    params.put("resourceType", resourceType);
		return this.query(hql, params);
	}

	@Override
	public List<Resource> doFindByUserIdAndType(Long userId,
			Long parentResourceId, String[] parentResourceType,
			String[] childrenResourceType) {
		String hql = "select distinct r from User user join user.roles role join role.resources r  " +
	    		"   where user.id=:userId and r.type in (:childrenResourceType)  and r.parentResourceId in(select distinct r.id from User user join user.roles role join role.resources r  " +
	    		"   where   r.type in (:parentResourceType) and r.parentResourceId=:parentResourceId )   order by r.seqNum asc,r.parentResourceId asc";
	    Map<String,Object> params = new HashMap<String,Object>();
	    params.put("userId", userId);
	    params.put("parentResourceType",parentResourceType);	    
	    params.put("childrenResourceType",childrenResourceType);
	    params.put("parentResourceId", parentResourceId);	 
		return this.query(hql, params);
	}

	@Override
	public List<Resource> doFindByUserIdAndType(Long userId,
			String[] parentResourceType, String[] resourceType) {
		 String hql = "select distinct pr from  Resource pr where pr.type in(:parentResourceType) and  pr.id in (select distinct  r.parentResourceId from User user join user.roles role join role.resources r " +
			 		" where user.id=:userId and r.type in (:resourceType) )";
		 Map<String,Object> params = new HashMap<String,Object>();
		 params.put("userId", userId);
		 params.put("resourceType", resourceType);
		 params.put("parentResourceType", parentResourceType);
	     return this.query(hql, params);
	}

	@Override
	public List<Resource> doFindByUserId(Long userId) {
		String hql = "select distinct r from User user join user.roles role join role.resources r  where user.id=:userId";
	    Map<String,Object> params = new HashMap<String,Object>();
	    params.put("userId", userId);
		return this.query(hql, params);
	}
	
}
