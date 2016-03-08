
/*
 * Copyright (c)  2013, Newtouch
 * All rights reserved. 
 *
 * $id: GroupServiceImpl.java 9552 2013-1-12 下午8:36:38 WangLijun$
 */
package com.newtouch.lion.service.system.impl;

import com.newtouch.lion.common.Assert;
import com.newtouch.lion.dao.system.GroupDao;
import com.newtouch.lion.dao.system.GroupRoleDao;
import com.newtouch.lion.dao.system.RoleDao;
import com.newtouch.lion.dao.system.UserDao;
import com.newtouch.lion.json.JSONParser;
import com.newtouch.lion.model.datagrid.DataColumn;
import com.newtouch.lion.model.system.Group;
import com.newtouch.lion.model.system.GroupRole;
import com.newtouch.lion.model.system.Role;
import com.newtouch.lion.model.system.User;
import com.newtouch.lion.page.PageResult;
import com.newtouch.lion.query.QueryCriteria;
import com.newtouch.lion.service.AbstractService;
import com.newtouch.lion.service.datagrid.DataColumnService;
import com.newtouch.lion.service.system.GroupService;
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
@Service
public class GroupServiceImpl extends AbstractService implements GroupService {

	@Autowired
	private GroupDao groupDao;

	@Autowired
	private DataColumnService dataColumnService;

	@Autowired
	private UserDao userDao;

	@Autowired
	private RoleDao roleDao;
	
	@Autowired
	private GroupRoleDao groupRoleDao;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.lion.framework.service.system.GroupService#doCreateGroup(com.lion
	 * .framework.model.system.Group)
	 */
	@Override
	public void doCreateGroup(Group group) {
		this.groupDao.save(group);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.lion.framework.service.system.GroupService#doFindByTableId(java.lang
	 * .String)
	 */
	@Override
	public List<DataColumn> doFindByTableId(String tableId) {
		return this.dataColumnService.doFindByTableId(tableId);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.lion.framework.service.system.GroupService#doAddGroupsToUser(java
	 * .util.List, com.lion.framework.model.system.User)
	 */
	@Override
	public void doAddGroupsToUser(List<Long> groupIds, User user) {
		if (groupIds == null || groupIds.size() == 0 || user == null) {
			return;
		}
		Set<Group> groups = new HashSet<Group>();
		for (Long groupId : groupIds) {
			Group group = this.doFindById(groupId);
			Set<User> users = group.getUsers();
			if (users == null) {
				users = new HashSet<User>();
				group.setUsers(users);
			}
			group.getUsers().add(user);
			groups.add(group);
		}
		// user.getGroups().addAll(groups);
		this.groupDao.updateObjects(groups);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.lion.framework.service.system.GroupService#doAddUsersToGroup(java
	 * .util.List, com.lion.framework.model.system.Group)
	 */
	@Override
	public void doAddUsersToGroup(List<Long> userIds, Group group) {
		if (userIds == null || userIds.size() == 0 || group == null) {
			return;
		}

		for (Long userId : userIds) {
			User user = this.userDao.findById(userId);
			group.getUsers().add(user);
		}
		this.doUpdate(group);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.lion.framework.service.system.GroupService#doDeleteUsersFromGroup
	 * (java.util.List, com.lion.framework.model.system.Group)
	 */
	@Override
	public void doDeleteUsersFromGroup(List<Long> userIds, Group group) {
		if (userIds == null || userIds.size() == 0 || group == null) {
			return;
		}
		Set<User> users = group.getUsers();

		Set<User> deleteUsers = new HashSet<User>();

		for (User user : users) {
			if (userIds.contains(user.getId())) {
				user.getGroups().remove(group);
				deleteUsers.add(user);
			}
		}
		group.getUsers().remove(deleteUsers);
		this.doUpdate(group);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.lion.framework.service.system.GroupService#doAddRolesToGroup(java
	 * .util.List, com.lion.framework.model.system.Group)
	 */
	@Override
	public void doAddRolesToGroup(List<Long> roleIds, Group group) {
		if (roleIds == null || roleIds.size() == 0 || group == null) {
			return;
		}
		for (Long roldeId : roleIds) {
			Role role = this.roleDao.findById(roldeId);
			group.getRoles().add(role);
		}
		this.doUpdate(group);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.lion.framework.service.system.GroupService#idoAuthUserToGroup(java
	 * .util.List, java.util.List, com.lion.framework.model.system.Group)
	 */
	@Override
	public void idoAuthUserToGroup(List<Long> targetUserIds,
			List<Long> deleteUserIds, Group group) {
		this.doDeleteUsersFromGroup(deleteUserIds, group);
		this.doAddUsersToGroup(targetUserIds, group);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.lion.framework.service.system.GroupService#doDeleteRolesFromGroup
	 * (java.util.List, com.lion.framework.model.system.Group)
	 */
	@Override
	public void doDeleteRolesFromGroup(List<Long> roleIds, Group group) {
		if (roleIds == null || roleIds.size() == 0 || group == null) {
			return;
		}
		Set<Role> users = group.getRoles();

		Set<Role> deleteUsers = new HashSet<Role>();

		for (Role role : users) {
			if (roleIds.contains(role.getId())) {
				role.getGroups().remove(group);
				deleteUsers.add(role);
			}
		}
		group.getRoles().removeAll(deleteUsers);
		this.doUpdate(group);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.lion.framework.service.system.GroupService#doAuthRoleToGroup(java
	 * .util.List, java.util.List, com.lion.framework.model.system.Group)
	 */
	@Override
	public void doAuthRoleToGroup(List<Long> targetRoleIds,
			List<Long> deleteRoleIds, Group group) {
		this.doDeleteRolesFromGroup(deleteRoleIds, group);
		this.doAddRolesToGroup(targetRoleIds, group);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.lion.framework.service.system.GroupService#doFindAll()
	 */
	@Override
	public List<Group> doFindAll() {
		return this.groupDao.findAll();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.lion.framework.service.system.GroupService#doFindByUserId(java.lang
	 * .Long)
	 */
	@Override
	public List<Group> doFindByUserId(Long userId) {
		return groupDao.doFindByUserId(userId);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.lion.framework.service.system.GroupService#doFindByRoleId(java.lang
	 * .Long)
	 */
	@Override
	public List<Group> doFindByRoleId(Long roleId) {
		return groupDao.doFindByRoleId(roleId);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.lion.framework.service.system.GroupService#doFindById(java.lang.Long)
	 */
	@Override
	public Group doFindById(Long id) {
		return this.groupDao.findById(id);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.lion.framework.service.system.GroupService#doDelete(com.lion.framework
	 * .model.system.Group)
	 */
	@Override
	public void doDelete(Group group) {
		this.groupDao.remove(group);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.lion.framework.service.system.GroupService#doDeleteById(java.lang
	 * .Long)
	 */
	@Override
	public int doDeleteById(Long id) {
		return groupDao.doDeleteById(id);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.lion.framework.service.system.GroupService#doUpdate(com.lion.framework
	 * .model.system.Group)
	 */
	@Override
	public Group doUpdate(Group group) {
		this.groupDao.update(group);
		return group;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.lion.framework.service.system.GroupService#doGetById(java.lang.Long)
	 */
	@Override
	public Group doGetById(Long id) {
		return groupDao.getById(id);
	}

	
	
	/* (non-Javadoc)
	 * @see com.newtouch.lion.service.system.GroupService#doFindByCriteriaAndUser(com.newtouch.lion.query.QueryCriteria)
	 */
	@Override
	public PageResult<Group> doFindByCriteriaAndUser(QueryCriteria queryCriteria) {
		return groupDao.doFindByCriteriaAndUser(queryCriteria);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.lion.framework.service.system.GroupService#doFindByCriteria(com.lion
	 * .framework.common.QueryCriteria)
	 */
	@Override
	public PageResult<Group> doFindByCriteria(QueryCriteria criteria) {
		return groupDao.doFindByCriteria(criteria);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.lion.framework.service.system.GroupService#doFindAuthGroups(com.lion
	 * .framework.common.QueryCriteria, java.lang.String)
	 */
	@Override
	public String doFindAuthGroups(QueryCriteria queryCriteria, String tableId) {
		List<Group> groups = this.doFindByCriteria(queryCriteria).getContent();
		Set<String> properties = this.dataColumnService
				.doFindColumnsByTableId(tableId);
		return JSONParser.toJSONDataGridString(groups, properties);
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.lion.framework.service.system.GroupService#doFindAuthUsersById(java
	 * .lang.Long, java.lang.String)
	 */
	@Override
	public String doFindAuthUsersById(Long groupId, String tableId) {
		Group group = this.doFindById(groupId);
		Set<String> properties = this.dataColumnService
				.doFindColumnsByTableId(tableId);
		return JSONParser.toJSONDataGridString(group.getUsers(), properties);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.lion.framework.service.system.GroupService#doFindAuthRolesById(java
	 * .lang.Long, java.lang.String)
	 */
	@Override
	public String doFindAuthRolesById(Long groupId, String tableId) {
		Group group = this.doFindById(groupId);
		Set<String> properties = this.dataColumnService
				.doFindColumnsByTableId(tableId);
		return JSONParser.toJSONDataGridString(group.getRoles(), properties);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.lion.framework.service.system.GroupService#doFindGroups()
	 */
	@Override
	public List<Group> doFindGroups() {
		QueryCriteria queryCriteria = new QueryCriteria();
		queryCriteria.setPageSize(99999999);
		return this.doFindByCriteria(queryCriteria).getContent();
	}

	/* (non-Javadoc)
	 * @see com.newtouch.lion.service.system.GroupService#doIsExistByNameEn(java.lang.String)
	 */
	@Override
	public boolean doIsExistByNameEn(String nameEn) {
		Assert.notNull(nameEn);
		Group group = this.doFindTypeByNameEn(nameEn);
		if (group != null)
			return true;
		return false;
	}

	/* (non-Javadoc)
	 * @see com.newtouch.lion.service.system.GroupService#doFindTypeByNameEn(java.lang.String)
	 */
	@Override
	public Group doFindTypeByNameEn(String nameEn) {
		Assert.notNull(nameEn);
		return groupDao.doFindTypeByNameEn(nameEn);
	}

	/* (non-Javadoc)
	 * @see com.newtouch.lion.service.system.GroupService#doCreate(com.newtouch.lion.model.system.Group)
	 */
	@Override
	public void doCreate(Group group) {
		Assert.notNull(group);
		groupDao.save(group);
	}

	/* (non-Javadoc)
	 * @see com.newtouch.lion.service.system.GroupService#doFindGroupRoleByCriteria(com.newtouch.lion.query.QueryCriteria)
	 */
	@Override
	public PageResult<GroupRole> doFindGroupRoleByCriteria(
			QueryCriteria criteria,Long roleId) {
 
		PageResult<GroupRole> pageResult = this.groupRoleDao.doFindGroupRoleByCriteria(criteria);
		
		//如果查询为空，则直接返回数据
		if(CollectionUtils.isEmpty(pageResult.getContent())){
			return pageResult;
		}
		//以下代码检查是否已授权到用户组的角色
		criteria=new QueryCriteria();
		criteria.setStartIndex(0);
		criteria.setPageSize(pageResult.getPageSize());
		//当前ID集合
		List<GroupRole> groupRoles=pageResult.getContent();
		List<Long> groupIds=new ArrayList<Long>();
		for(GroupRole groupRole:groupRoles){
			groupIds.add(groupRole.getId());
		}
		// 查询条件 参数类型 用户名
		if (roleId!=null&&roleId>0) {
			criteria.addQueryCondition("roleId",roleId);
		}
		//查询所有空的
		if(!CollectionUtils.isEmpty(groupIds)){
			criteria.addQueryCondition("groupIds",groupIds);
		}
		
		PageResult<Group> result=this.doFindByCriteriaAndRole(criteria);
		
		Map<Long,Long> userIdsMap=new HashMap<Long,Long>();
		
		for(Group user:result.getContent()){
			userIdsMap.put(user.getId(), user.getId());
		}
		
		List<GroupRole> contents=new ArrayList<GroupRole>();
		
		for(GroupRole groupRole:groupRoles){
			if(userIdsMap.containsKey(groupRole.getId())){
				groupRole.setRoleId(roleId);
			}
			contents.add(groupRole);
		}
		pageResult.setContent(contents);
		return pageResult;
	}
	
	/* (non-Javadoc)
	 * @see com.newtouch.lion.service.system.GroupService#doFindGroupRoleByCriteria(com.newtouch.lion.query.QueryCriteria)
	 */
	@Override
	public PageResult<GroupRole> doFindGroupUserByCriteria(QueryCriteria queryCriteria,Long userId) {
		
		PageResult<GroupRole> pageResult = this.groupRoleDao.doFindGroupRoleByCriteria(queryCriteria);
		
		//如果查询为空，则直接返回数据
		if(CollectionUtils.isEmpty(pageResult.getContent())){
			return pageResult;
		}
		//以下代码检查是否已授权到用户组的角色
		queryCriteria=new QueryCriteria();
		queryCriteria.setStartIndex(0);
		queryCriteria.setPageSize(pageResult.getPageSize());
		//当前ID集合
		List<GroupRole> groupRoles=pageResult.getContent();
		List<Long> roleIds=new ArrayList<Long>();
		for(GroupRole groupRole:groupRoles){
			roleIds.add(groupRole.getId());
		}
		// 查询条件 参数类型 用户名
		if (userId!=null&&userId>0) {
			queryCriteria.addQueryCondition("userId",userId);
		}
		//查询所有空的
		if(!CollectionUtils.isEmpty(roleIds)){
			queryCriteria.addQueryCondition("groupIds",roleIds);
		}
		
		PageResult<Group> result=this.doFindByCriteriaAndUser(queryCriteria);
		
		Map<Long,Long> userIdsMap=new HashMap<Long,Long>();
		
		for(Group group:result.getContent()){
			userIdsMap.put(group.getId(), group.getId());
		}
		
		List<GroupRole> contents=new ArrayList<GroupRole>();
		
		for(GroupRole roleGroup:groupRoles){
			if(userIdsMap.containsKey(roleGroup.getId())){
				roleGroup.setUserId(userId);
			}
			contents.add(roleGroup);
		}
		pageResult.setContent(contents);
		return pageResult;
	}

	/* (non-Javadoc)
	 * @see com.newtouch.lion.service.system.GroupService#doFindByCriteriaAndRole(com.newtouch.lion.query.QueryCriteria)
	 */
	@Override
	public PageResult<Group> doFindByCriteriaAndRole(QueryCriteria criteria) {
		return groupDao.doFindByCriteriaAndRole(criteria);
		
	}
	
}
