/*
 * Copyright (c)  2013, Newtouch
 * All rights reserved. 
 *
 * $id: RoleServiceImpl.java 9552 2013-1-12 下午8:37:58 WangLijun$
 */
package com.newtouch.lion.service.system.impl;

import com.newtouch.lion.common.Assert;
import com.newtouch.lion.dao.system.GroupDao;
import com.newtouch.lion.dao.system.RoleDao;
import com.newtouch.lion.dao.system.RoleGroupDao;
import com.newtouch.lion.dao.system.UserDao;
import com.newtouch.lion.json.JSONParser;
import com.newtouch.lion.model.system.*;
import com.newtouch.lion.page.PageResult;
import com.newtouch.lion.query.QueryCriteria;
import com.newtouch.lion.service.AbstractService;
import com.newtouch.lion.service.datagrid.DataColumnService;
import com.newtouch.lion.service.system.ResourceService;
import com.newtouch.lion.service.system.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.*;

/**
 * <p>
 * Title:
 * </p>
 * <p>
 * Description:
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
@Service("roleService")
public class RoleServiceImpl extends AbstractService implements RoleService {

	@Autowired
	private RoleDao roleDao;
	/**用户及用户组查询*/
	@Autowired
	private RoleGroupDao  roleGroupDao;
	
	@Autowired
	private UserDao userDao;
	@Autowired
	private GroupDao groupDao;
	@Autowired
	private ResourceService resourceService;
	@Autowired
	private DataColumnService dataColumnService;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.lion.framework.service.system.RoleService#doCreateRole(com.lion.framework
	 * .model.system.Role)
	 */
	@Override
	public void doCreateRole(Role role) {
		this.roleDao.save(role);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.lion.framework.service.system.RoleService#doDelete(com.lion.framework
	 * .model.system.Role)
	 */
	@Override
	public void doDelete(Role role) {
		this.roleDao.remove(role);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.lion.framework.service.system.RoleService#doDeleteById(java.lang.
	 * Long)
	 */
	@Override
	public int doDeleteById(Long id) {
		return roleDao.doDeleteById(id);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.lion.framework.service.system.RoleService#doFindAll()
	 */
	@Override
	public List<Role> doFindAll() {
		return this.roleDao.findAll();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.lion.framework.service.system.RoleService#doFindById(java.lang.Long)
	 */
	@Override
	public Role doFindById(Long id) {
		return this.roleDao.findById(id);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.lion.framework.service.system.RoleService#doFindById(java.lang.Long,
	 * java.lang.String)
	 */
	@Override
	public String doFindAuthUsersById(Long id, String tableId) {
		Role role = this.doFindById(id);
		Set<String> properties = dataColumnService
				.doFindColumnsByTableId(tableId);
		return JSONParser.toJSONDataGridString(role.getUsers(), properties);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.lion.framework.service.system.RoleService#doFindAuthUserGroupsById
	 * (java.lang.Long, java.lang.String)
	 */
	@Override
	public String doFindAuthUserGroupsById(Long id, String tableId) {
		Role role = this.doFindById(id);
		Set<String> properties = dataColumnService
				.doFindColumnsByTableId(tableId);
		return JSONParser.toJSONDataGridString(role.getGroups(), properties);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.lion.framework.service.system.RoleService#doGetById(java.lang.Long)
	 */
	@Override
	public Role doGetById(Long id) {
		return this.roleDao.getById(id);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.lion.framework.service.system.RoleService#doUpdate(com.lion.framework
	 * .model.system.Role)
	 */
	@Override
	public void doUpdate(Role role) {
		this.roleDao.update(role);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.lion.framework.service.system.RoleService#doFindByCriteria(com.lion
	 * .framework.common.QueryCriteria)
	 */
	@Override
	public PageResult<Role> doFindByCriteria(QueryCriteria criteria) {

		return roleDao.doFindByCriteria(criteria);
	}
	
	
	
	

	/* (non-Javadoc)
	 * @see com.newtouch.lion.service.system.RoleService#doFindRoleUserByCriteria(com.newtouch.lion.query.QueryCriteria, java.lang.Long)
	 */
	@Override
	public PageResult<RoleGroup> doFindRoleUserByCriteria(QueryCriteria queryCriteria, Long userId) {

		PageResult<RoleGroup> pageResult = roleGroupDao.doFindRoleUserByCriteria(queryCriteria);
		
		//如果查询为空，则直接返回数据
		if(CollectionUtils.isEmpty(pageResult.getContent())){
			return pageResult;
		}
		//以下代码检查是否已授权到用户组的角色
		queryCriteria=new QueryCriteria();
		queryCriteria.setStartIndex(0);
		queryCriteria.setPageSize(pageResult.getPageSize());
		//当前ID集合
		List<RoleGroup> roleGroups=pageResult.getContent();
		List<Long> roleIds=new ArrayList<Long>();
		for(RoleGroup roleGroup:roleGroups){
			roleIds.add(roleGroup.getId());
		}
		// 查询条件 参数类型 用户名
		if (userId!=null&&userId>0) {
			queryCriteria.addQueryCondition("userId",userId);
		}
		//查询所有空的
		if(!CollectionUtils.isEmpty(roleIds)){
			queryCriteria.addQueryCondition("roleIds",roleIds);
		}
		
		PageResult<Role> result=this.doFindByCriteriaAndUser(queryCriteria);
		
		Map<Long,Long> userIdsMap=new HashMap<Long,Long>();
		
		for(Role role:result.getContent()){
			userIdsMap.put(role.getId(), role.getId());
		}
		
		List<RoleGroup> contents=new ArrayList<RoleGroup>();
		
		for(RoleGroup roleGroup:roleGroups){
			if(userIdsMap.containsKey(roleGroup.getId())){
				roleGroup.setUserId(userId);
			}
			contents.add(roleGroup);
		}
		pageResult.setContent(contents);
		return pageResult;
	}

	/* (non-Javadoc)
	 * @see com.newtouch.lion.service.system.RoleService#doFindRoleGroupByCriteria(com.newtouch.lion.query.QueryCriteria, java.lang.Long)
	 */
	@Override
	public PageResult<RoleGroup> doFindRoleGroupByCriteria(QueryCriteria criteria, Long groupId) {
		
		PageResult<RoleGroup> pageResult = roleGroupDao.doFindRoleUserByCriteria(criteria);
		
		//如果查询为空，则直接返回数据
		if(CollectionUtils.isEmpty(pageResult.getContent())){
			return pageResult;
		}
		//以下代码检查是否已授权到用户组的角色
		criteria=new QueryCriteria();
		criteria.setStartIndex(0);
		criteria.setPageSize(pageResult.getPageSize());
		//当前ID集合
		List<RoleGroup> roleGroups=pageResult.getContent();
		List<Long> roleIds=new ArrayList<Long>();
		for(RoleGroup roleGroup:roleGroups){
			roleIds.add(roleGroup.getId());
		}
		// 查询条件 参数类型 用户名
		if (groupId!=null&&groupId>0) {
			criteria.addQueryCondition("groupId",groupId);
		}
		//查询所有空的
		if(!CollectionUtils.isEmpty(roleIds)){
			criteria.addQueryCondition("roleIds",roleIds);
		}
		
		PageResult<Role> result=this.doFindByCriteriaAndGroup(criteria);
		
		Map<Long,Long> userIdsMap=new HashMap<Long,Long>();
		
		for(Role user:result.getContent()){
			userIdsMap.put(user.getId(), user.getId());
		}
		
		List<RoleGroup> contents=new ArrayList<RoleGroup>();
		
		for(RoleGroup roleGroup:roleGroups){
			if(userIdsMap.containsKey(roleGroup.getId())){
				roleGroup.setGroupId(groupId);
			}
			contents.add(roleGroup);
		}
		pageResult.setContent(contents);
		return pageResult;
	}

	/* (non-Javadoc)
	 * @see com.newtouch.lion.service.system.RoleService#doFindByCriteriaAndGroup(com.newtouch.lion.query.QueryCriteria)
	 */
	@Override
	public PageResult<Role> doFindByCriteriaAndGroup(QueryCriteria criteria) {
		
		return roleDao.doFindByCriteriaAndGroup(criteria);
	}

	
	
	/* (non-Javadoc)
	 * @see com.newtouch.lion.service.system.RoleService#doFindByCriteriaAndUser(com.newtouch.lion.query.QueryCriteria)
	 */
	@Override
	public PageResult<Role> doFindByCriteriaAndUser(QueryCriteria queryCriteria) {
		return roleDao.doFindByCriteriaAndUser(queryCriteria);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.lion.framework.service.system.RoleService#doFindByCriteria(com.lion
	 * .framework.common.QueryCriteria, java.lang.String)
	 */
	@Override
	public String doFindByCriteria(QueryCriteria criteria, String tableId) {
		PageResult<Role> pageResult = this.doFindByCriteria(criteria);
		Set<String> properties = dataColumnService
				.doFindColumnsByTableId(tableId);
		return JSONParser.toJSONDataGridString(pageResult, properties);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.lion.framework.service.system.RoleService#doAddRolesToUser(java.util
	 * .List, com.lion.framework.model.system.User)
	 */
	@Override
	public void doAddRolesToUser(List<Long> roleIds, User user) {
		if (roleIds == null || roleIds.size() == 0 || user == null) {
			return;
		}
		Set<Role> roles = new HashSet<Role>();
		for (Long roleId : roleIds) {
			Role role = this.doFindById(roleId);
			Set<User> users = role.getUsers();
			if (users == null) {
				users = new HashSet<User>();
				role.setUsers(users);
			}
			role.getUsers().add(user);
			roles.add(role);
		}
		// user.getRoles().addAll(roles);
		this.roleDao.updateObjects(roles);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.lion.framework.service.system.RoleService#doAddRolesToGroup(java.
	 * util.List, com.lion.framework.model.system.Group)
	 */
	@Override
	public void doAddRolesToGroup(List<Long> roldeIds, Group group) {
		
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.lion.framework.service.system.RoleService#doAddUsersToRole(java.util
	 * .List, com.lion.framework.model.system.Role)
	 */
	@Override
	public void doAddUsersToRole(List<Long> userIds, Role role) {
		if (userIds == null || userIds.size() == 0 || role == null) {
			return;
		}
		for (Long userId : userIds) {
			User user = this.userDao.findById(userId);
			Set<User> users = role.getUsers();
			if (users == null) {
				users = new HashSet<User>();
				role.setUsers(users);
			}
			role.getUsers().add(user);
		}
		this.roleDao.update(role);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.lion.framework.service.system.RoleService#doAddGroupsToRole(java.
	 * util.List, com.lion.framework.model.system.Role)
	 */
	@Override
	public void doAddGroupsToRole(List<Long> groupIds, Role role) {
		if (groupIds == null || groupIds.size() == 0 || role == null) {
			return;
		}
		for (Long groupId : groupIds) {
			Group group = groupDao.findById(groupId);
			Set<Group> groups = role.getGroups();
			if (groups == null) {
				groups = new HashSet<Group>();
				role.setGroups(groups);
			}
			role.getGroups().add(group);
		}
		this.roleDao.update(role);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.lion.framework.service.system.RoleService#doDeleteUsersFromRole(java
	 * .util.List, com.lion.framework.model.system.Group)
	 */
	@Override
	public void doDeleteUsersFromRole(List<Long> userIds, Role role) {
		if (userIds == null || userIds.size() == 0 || role == null) {
			return;
		}
		for (Long userId : userIds) {
			User user = this.userDao.findById(userId);
			if (user == null)
				continue;
			role.getUsers().remove(user);
		}
		this.roleDao.update(role);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.lion.framework.service.system.RoleService#doDeleteGroupsFromRole(
	 * java.util.List, com.lion.framework.model.system.Role)
	 */
	@Override
	public void doDeleteGroupsFromRole(List<Long> groupIds, Role role) {
		if (groupIds == null || groupIds.size() == 0 || role == null) {
			return;
		}
		for (Long groupId : groupIds) {
			Group group = groupDao.findById(groupId);
			if (group == null)
				continue;
			role.getGroups().remove(group);
		}
		this.roleDao.update(role);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.lion.framework.service.system.RoleService#doAuthUsersToRole(java.
	 * util.List, java.util.List, com.lion.framework.model.system.Role)
	 */
	@Override
	public void doAuthUsersToRole(List<Long> targetUserIds,
			List<Long> deleteUserIds, Role role) {
		this.doDeleteUsersFromRole(deleteUserIds, role);
		this.doAddUsersToRole(targetUserIds, role);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.lion.framework.service.system.RoleService#doAuthGroupsToRole(java
	 * .util.List, java.util.List, com.lion.framework.model.system.Role)
	 */
	@Override
	public void doAuthGroupsToRole(List<Long> targetGroupIds,
			List<Long> deleteGroupIds, Role role) {
		this.doDeleteGroupsFromRole(deleteGroupIds, role);
		this.doAddGroupsToRole(targetGroupIds, role);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.lion.framework.service.system.RoleService#doAuthResourceToRole(java
	 * .util.List, java.util.List, com.lion.framework.model.system.Role)
	 */
	@Override
	public void doAuthResourceToRole(List<Long> targetResourceIds,
			List<Long> deleteResourceIds, Role role) {
		// 如果角色为空，则直接返回
		if (role == null) {
			return;
		}

		Set<Resource> resources = null;

		// 将资源集合从角色中删除
		if (deleteResourceIds != null && deleteResourceIds.size() > 0) {
			for (Long resourceId : deleteResourceIds) {
				Resource resource = this.resourceService.doGetById(resourceId);
				if (resource == null)
					continue;
				role.getResources().remove(resource);
			}
		}
		
		// 将资源集合授权给角色中
		if (targetResourceIds != null && targetResourceIds.size() > 0) {
			for (Long resourceId : targetResourceIds) {
				Resource resource = this.resourceService.doGetById(resourceId);
				resources = role.getResources();
				if (resources == null) {
					resources = new HashSet<Resource>();
					role.setResources(resources);
				}
				role.getResources().add(resource);
			}
		}
		this.roleDao.update(role);
		this.roleDao.flush();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.lion.framework.service.system.RoleService#doFindByUserId(java.lang
	 * .Long)
	 */
	@Override
	public List<Role> doFindByUserId(Long userId) {
		return roleDao.doFindByUserId(userId);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.lion.framework.service.system.RoleService#doFindByGroupId(java.lang
	 * .Long)
	 */
	@Override
	public List<Role> doFindByGroupId(Long groupId) {
		return null;
	}
	
	/* (non-Javadoc)
	 * @see com.newtouch.lion.service.system.RoleService#doFindTypeByNameEn(java.lang.String)
	 * add by maojiawei
	 */
	@Override
	public Role doFindTypeByNameEn(String nameEn) {
		Assert.notNull(nameEn);
		return roleDao.doFindTypeByNameEn(nameEn);
	}

	/* (non-Javadoc)
	 * @see com.newtouch.lion.service.system.RoleService#doIsExistByNameEn(java.lang.String)
	 * add by maojiawei
	 */
	@Override
	public boolean doIsExistByNameEn(String nameEn) {
		Assert.notNull(nameEn);
		Role role = this.doFindTypeByNameEn(nameEn);
		if (role != null)
			return true;
		return false;
	}

	/* (non-Javadoc)
	 * @see com.newtouch.lion.service.system.RoleService#doCreate(com.newtouch.lion.model.system.Role)
	 */
	@Override
	public void doCreate(Role role) {
		Assert.notNull(role);
		roleDao.save(role);
	}

	/* (non-Javadoc)
	 * @see com.newtouch.lion.service.system.RoleService#idoAuthUserToRole(java.util.List, java.util.List, com.newtouch.lion.model.system.Role)
	 */
	@Override
	public void idoAuthUserToRole(List<Long> targetUserIds,
			List<Long> deleteUserIds, Role role) {
		this.doDeleteUsersFromRole(deleteUserIds, role);
		this.doAddUsersToRole(targetUserIds, role);
	}
	
	/* (non-Javadoc)
	 * @see com.newtouch.lion.service.system.RoleService#idoAuthUserToRole(java.util.List, java.util.List, com.newtouch.lion.model.system.Role)
	 */
	@Override
	public void idoAuthGroupToRole(List<Long> targetGroupIds,
			List<Long> deleteGroupIds, Role role) {
		this.doDeleteGroupsFromRole(deleteGroupIds, role);
		this.doAddGroupsToRole(targetGroupIds, role);
	}
	
}
