/*
 * Copyright (c)  2012, Newtouch
 * All rights reserved. 
 *
 * $id: TsUserService.java 9552 2012-12-31 下午7:26:11 WangLijun$
 */
package com.newtouch.lion.service.system;

import java.util.List;
import java.util.Set;

import com.newtouch.lion.model.system.Role;
import com.newtouch.lion.model.system.User;
import com.newtouch.lion.model.system.UserGroup;
import com.newtouch.lion.model.system.UserRole;
import com.newtouch.lion.page.PageResult;
import com.newtouch.lion.query.QueryCriteria;

/**
 * <p>
 * Title: 用户管理Service接口
 * </p>
 * <p>
 * Description: 用于处理用户登录验证、新增、查询、保存
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
public interface UserService {
	/***
	 * 根据用户名获取User对象
	 * 
	 * @param username
	 *            用户名
	 * @return  用户对象
	 */
	public User doFindByUserName(String username);
	
	/****
	 * 检查用户名是否已存在
	 * @param username
	 * @return 存在则返回true,不存在则返回false
	 */
	public Boolean checkUsername(String username);
	
	/**
	 * 检查员工号是否已经存在
	 * @param employeeCode 员工号
	 * @return  存在则返回true,不存在则返回false
	 */
	public Boolean checkEmployeeCode(String employeeCode);
	
	/**
	 * 检查员邮箱是否已经存在
	 * @param employeeCode 员工号
	 * @return  存在则返回true,不存在则返回false
	 */
	public Boolean checkEmail(String email);
	
	/****
	 * 检查用户名是否已存在
	 * @param username
	 * @param id 用户ID
	 * @return 存在则返回true,不存在则返回false
	 */
	public Boolean checkUsername(String username, Long id);
	
	/**
	 * 检查员工号是否已经存在
	 * @param employeeCode 员工号
	 * @param id 用户ID
	 * @return  存在则返回true,不存在则返回false
	 */
	public Boolean checkEmployeeCode(String employeeCode, Long id);
	
	/**
	 * 检查员邮箱是否已经存在
	 * @param employeeCode 员工号
	 * @param id 用户ID
	 * @return  存在则返回true,不存在则返回false
	 */
	public Boolean checkEmail(String email, Long id);
	
	/***
	 * 根据员工号查找用户
	 * @param String employeeCode  员工号
	 * @return
	 */
	public User doFindByEmpolyeeCode(String employeeCode);
	/***
	 * 根据用户邮箱查找用户
	 * @param String email 邮箱
	 * @return User 用户对象
	 */ 
	public User doFindByEmail(String email);

	/***
	 * 
	 */
	public void doCreateUser(User user);

	/**
	 * 根据用户及已关联的用户，删除关联的用户组
	 * 
	 * @param deleteGroupIds
	 *            删除关联的用户组
	 * @param user
	 *            用户对象
	 * */
	public void doDeleteGroupsForUser(List<Long> deleteGroupIds, User user);

	/**
	 * 根据用户及已关联的用户角色，删除已关联角色
	 * 
	 * @param deleteRolesIds
	 * @param user
	 * */
	public void doDeleteRoleSForUser(List<Long> deleteRolesIds, User user);

	/***
	 * 将用户组授权给用户
	 * 
	 * @param targetGroupIds
	 * @param deleteGroupIds
	 * @param user
	 */
	public void doAuthGroupToUser(List<Long> targetGroupIds,
								  List<Long> deleteGroupIds, User user);

	/***
	 * 将角色授权给用户
	 * 
	 * @param targetRoleIds
	 * @param deleteRoleIds
	 * @param user
	 */
	public void doAuthRoleToUser(List<Long> targetRoleIds, List<Long> deleteRoleIds, User user);
	
	/***
	 * 获取系统超级用户名
	 * @return String 系统超级用户名
	 */
	public String getSuperUsername();
	/***
	 * 根据用户ID检查是否超级用户名
	 * @param id 用户名
	 * @return  是则返回true,否则返回false
	 */
	public boolean checkSuperUserById(Long id);
	/***
	 * 根据用户ID检查是否超级用户名
	 * @param id 用户名
	 * @return  是则返回true,否则返回false
	 */
	public boolean checkSuperUserByUserName(String username);

	public void doDelete(User user);
	/****
	 * 根据ID删除用户记录数，并返回删除记录
	 * @param id
	 * @return int 删除记录
	 */
	public int doDeleteById(Long id);

	public User doFindById(Long id);

	public User doGetById(Long id);

	/** 根据经理Id查询该经理管理所有用户 */
	public List<User> doFindByManageId(Long manageId);

	/** 查询所有的用户列表 */
	public List<User> doFindAll();

	/** 更新对象 */
	public User doUpdate(User user);
	
	/** 用户与用户组查询查询，并返回分页对象 */
	public PageResult<UserGroup> doFindUserGroupByCriteria(QueryCriteria criteria);
	
	/** 用户与角色查询查询，并返回分页对象 */
	public PageResult<UserRole> doFindUserRoleByCriteria(QueryCriteria criteria, Long roleId);
	
	/** 用户关联用户组查询，并返回分页对象 */
	public PageResult<User> doFindByCriteriaAndGroup(QueryCriteria criteria);
	
	/** 用户关联角色查询，并返回分页对象 */
	public PageResult<User> doFindByCriteriaAndRole(QueryCriteria criteria);
	/** 用户查询，并返回分页对象 */
	public PageResult<User> doFindByCriteria(QueryCriteria criteria);

	/**
	 * 根据查询条件和字段列表显示结果返回用户的JSON格式的列表信息
	 * 
	 * @param criteria
	 * @param tableId
	 * @return String
	 * */
	public String doFindByCriteria(QueryCriteria criteria, String tableId);

	/**
	 * 根据查询条件和字段列表显示结果返回用户的JSON格式的列表信息
	 * 
	 * @param criteria
	 * @param tableId
	 * @return String
	 * */
	public String doFindAllByCriteria(QueryCriteria criteria, String tableId);

	/**
	 * 关联已授权用户组， 根据列表显示结果返回用户的JSON格式的列表信息
	 * 
	 * @param userId
	 *            用户ID
	 * @param tableId
	 *            显示列表格式的名称
	 * @retun String 返回JSON
	 * */
	public String doFindAllAuthGroupsById(Long userId, String tableId);

	/**
	 * 关联已授权角色， 根据列表显示结果返回用户的JSON格式的列表信息
	 * 
	 * @param userId
	 *            用户ID
	 * @param tableId
	 *            显示列表格式的名称
	 * @retun String 返回JSON
	 * */
	public String doFindAllAuthRole(Long userId, String tableId);
	
	/***
	 * 根据用户名查询所有的角色
	 * @param userName
	 * @return List<Role>  角色列表
	 */
	public List<Role> doFindRoles(String userName);
	
	/**根据用户ID查询所有的角色*/
	public Set<Role> doFindRoles(Long id);
	
	
}
