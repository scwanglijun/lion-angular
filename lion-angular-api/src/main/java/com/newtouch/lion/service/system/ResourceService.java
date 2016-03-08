/*
 * Copyright (c)  2012, Newtouch
 * All rights reserved. 
 *
 * $id: TsResourceService.java 9552 2012-12-31 下午9:57:44 WangLijun$
 */
package com.newtouch.lion.service.system;

import java.util.Collection;
import java.util.List;

import com.newtouch.lion.model.system.Resource;
import com.newtouch.lion.tree.Tree;

/**
 * <p>
 * Title: TsResourceService
 * </p>
 * <p>
 * Description: TsResourceService 资源管理类
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
public interface ResourceService {
	
	/***
	 * 保存资源对象
	 * 
	 * @param tsResource 资源对象
	 */
	public void doCreateResource(Resource resource);
	
	
	/**根据资源ID查询资源对象*/
	 public Resource doFindById(Long resourceId);
	 /**根据资源ID查询资源对象*/
	 public Resource doGetById(Long id);
	 
	 public Resource doUpdate(Resource resource);
	
	/***
	 *根据资源ID集合对象批量删除资源对象 
	 * @param resourceIds
	 */
	public void doDelete(Collection<Long> resourceIds);
	/***
	 * 根据资源ID删除资源对象
	 * @param resourceId
	 */
	public Resource doDelete(Long resourceId);
	
	/***
	 * 查询所有资源信息,并进行排序
	 * 排序规则：parentResourceId asc, seqNum asc
	 * 
	 */
	public List<Resource> doFindAll();
	
	/**doFindAuthAll*/
	public List<Resource> doFindAuthAll();

	/***
	 * 根据父资源Id查询资源列表
	 * @param parentResourceId
	 * @return List<TsResource>
	 */
	public List<Resource> doFindByParentId(Long parentResourceId);
	
	
	public List<Tree> doFindByResourceId(Long resourceId);
 	
	
	/***
	 * 根据资源类型查询资源列表
	 * @param resTypes 资源类型
	 * @return 返回资源列表 
	 */
	public List<Resource> doFindByType(String[] resTypes);
	
	
	/**第一级菜单*/
	public List<Resource> doFindFirstLevel();
	
	/***
	 * 查询所有资源类型资源列表并根据
	 * @param tableId 表格名称
	 * @return String 返回 JSON字符串
	 */
	public String doFindAllToTree(String tableId);
	
	/***
	 * 根据用户ID、资源类型查询已授权资源
	 * @param userId 用户ID
	 * @param resourceType 资源类型
	 * @return List<Resource>
	 */
	public List<Resource> doFindByUserIdAndType(Long userId, String[] resourceType);
	/***
	 * 根据用户ID、资源类型查询已授权资源
	 * @param userId 用户ID
	 * @param parentResourceId  父级资源ID
	 * @param resourceType 资源类型
	 * @return List<Resource>
	 */
	public List<Resource> doFindByUserIdAndType(Long userId, Long parentResourceId, String[] resourceType);
	
	/***
	 * 根据用户ID、资源类型查询已授权资源
	 * @param userId 用户ID
	 * @param parentResourceId  父级资源ID
	 * @param resourceType 资源类型
	 * @return List<Resource>
	 */
	public List<Resource> doFindByUserIdAndType(Long userId, Long parentResourceId, String[] parentResourceType, String[] childrenResourceType);
	/***
	 * 根据用户ID、父级资源类型、子级类型查询已授权父级资源
	 * @param userId  用户ID
	 * @param parentResourceType 父资源类型
	 * @param resourceType  子资源类型
	 * @return List<Resource> 
	 */
	public List<Resource> doFindByUserIdAndType(Long userId, String[] parentResourceType, String[] resourceType);
	
	/***
	 * 根据用户ID查询用户所有已授权资源
	 * @param userId 用户Id
	 * @return List<Resource>
	 */
	public List<Resource> doFindByUserId(Long userId);
	

		
}
