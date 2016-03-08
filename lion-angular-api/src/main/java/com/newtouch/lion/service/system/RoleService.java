/*
 * Copyright (c)  2013, Newtouch
 * All rights reserved. 
 *
 * $id: RoleService.java 9552 2013-1-12 下午1:49:24 WangLijun$
 */
package com.newtouch.lion.service.system;

import java.util.List;

import com.newtouch.lion.model.system.Group;
import com.newtouch.lion.model.system.Role;
import com.newtouch.lion.model.system.RoleGroup;
import com.newtouch.lion.model.system.User;
import com.newtouch.lion.page.PageResult;
import com.newtouch.lion.query.QueryCriteria;

/**
 * <p>
 * Title: 用户角色Service
 * </p>
 * <p>
 * Description: 用户角色Service
 * </p>
 * <p>
 * Copyright: Copyright (c) 2013
 * </p>
 * <p>
 * Company: Newtouch
 * </p>
 * 
 * @author WangLijun
 * @version 1.0
 */
public interface RoleService {

	public void doCreateRole(Role role);

	/**
	 * 将角色集合添加到用户中
	 * 
	 * @param roleIds
	 *            角色集合
	 * @param user
	 *            用户
	 * */
	public void doAddRolesToUser(List<Long> roleIds, User user);

	/**
	 * 将角色集合添加到用户组中
	 * 
	 * @param roldeIds
	 * @param group
	 *            ;
	 * */
	public void doAddRolesToGroup(List<Long> roldeIds, Group group);

	/**
	 * 将角色授权给用户集合（新增、删除关联关系）
	 * 
	 * @param targetUserIds
	 * @param deleteUserIds
	 * @param role
	 * */
	public void doAuthUsersToRole(List<Long> targetUserIds,
								  List<Long> deleteUserIds, Role role);

	/**
	 * 将角色授权给用户组集合(新增、删除关联关系)
	 * 
	 * @param targetGroupIds
	 * @param deleteGroupIds
	 * @param role
	 * */
	public void doAuthGroupsToRole(List<Long> targetGroupIds,
								   List<Long> deleteGroupIds, Role role);

	/**
	 * 将用户集合添加到角色中
	 * 
	 * @param userIds
	 * @param role
	 * */
	public void doAddUsersToRole(List<Long> userIds, Role role);

	/**
	 * 将用户集合从角色中删除
	 * 
	 * @param userIds
	 * @param role
	 * */
	public void doDeleteUsersFromRole(List<Long> userIds, Role role);

	/**
	 * 将用户组集合添加到角色中
	 * 
	 * @param groupIds
	 * @param role
	 * */
	public void doAddGroupsToRole(List<Long> groupIds, Role role);

	/**
	 * 将用户集合从角色中删除
	 * 
	 * @param userIds
	 * @param role
	 * */
	public void doDeleteGroupsFromRole(List<Long> groupIds, Role role);

	/**
	 * 将授权资源给角色()
	 * 
	 * @param targetResourceIds
	 * @param deleteResourceIds
	 * @param role
	 * */
	public void doAuthResourceToRole(List<Long> targetResourceIds,
									 List<Long> deleteResourceIds, Role role);

	/**
	 * 根据用户ID查询用户角色集合
	 * 
	 * @param userId
	 * @return List<Role>
	 * */
	public List<Role> doFindByUserId(Long userId);

	/**
	 * 根据用户组Id,查询用户组所关联角色集合
	 * 
	 * @param groupId
	 * @return List<Role>
	 * */
	public List<Role> doFindByGroupId(Long groupId);

	public void doDelete(Role role);

	public int doDeleteById(Long id);

	public List<Role> doFindAll();

	public Role doFindById(Long id);

	/**
	 * 显示该角色所关联的用户列表
	 * 
	 * @param id
	 *            角色ID
	 * @param tableId
	 *            表格式名称
	 * @return json 字符串
	 * */
	public String doFindAuthUsersById(Long id, String tableId);

	/**
	 * 显示该角色所关联的用户组列表
	 * 
	 * @param id
	 *            角色ID
	 * @param tableId
	 *            表格式名称
	 * @return JSON 字符串
	 * */
	public String doFindAuthUserGroupsById(Long id, String tableId);

	public Role doGetById(Long id);

	public void doUpdate(Role role);
	/***
	 * 多条件组合查询，并返回分页对象
	 * @param criteria 查询条件
	 * @return  PageResult<Role>
	 */
	public PageResult<Role> doFindByCriteria(QueryCriteria criteria);
	/***
	 * 查询角色列表，并关联GroupId
	 * @param queryCriteria
	 * @return PageResult<RoleGroup>
	 */
	public PageResult<RoleGroup> doFindRoleGroupByCriteria(QueryCriteria queryCriteria, Long groupId);
	
	/***
	 * 查询角色列表，并关联userId
	 * @param queryCriteria
	 * @return PageResult<RoleGroup>
	 */
	public PageResult<RoleGroup> doFindRoleUserByCriteria(QueryCriteria queryCriteria, Long userId);
	
	/***
	 * 多条件组合查询，并返回分页对象,关联Group对象
	 * @param queryCriteria 查询条件
	 * @return  PageResult<Role>
	 */
	public PageResult<Role> doFindByCriteriaAndGroup(QueryCriteria queryCriteria);
	
	/***
	 * 多条件组合查询，并返回分页对象,关联User对象
	 * @param queryCriteria 查询条件
	 * @return  PageResult<Role>
	 */
	public PageResult<Role> doFindByCriteriaAndUser(QueryCriteria queryCriteria);
	

	public String doFindByCriteria(QueryCriteria criteria, String tableId);
	/**
	 * 判断角色的英文名是否已存在，
	 * @param nameEn
	 * @return  boolean
	 * @author maojiawei
	 * */
	public boolean doIsExistByNameEn(String nameEn); 
	/***
	 * 根据角色英文名称获取用户角色列表
	 * @param type
	 * @param nameEn
	 * @author maojiawei
	 * @return {@link Role}
	 */
	public Role  doFindTypeByNameEn(String nameEn);
	/***
	 * 保存Role对象
	 * @author maojiawei
	 * @param role
	 */
	public void doCreate(Role role);
	/** 将角色授权给用户 */
	public void idoAuthUserToRole(List<Long> targetUserIds,
								  List<Long> deleteUserIds, Role role);
	
	/** 将角色授权给用户组 */
	public void idoAuthGroupToRole(List<Long> targetGroupIds,
								   List<Long> deleteGroupIds, Role role);
	
}
