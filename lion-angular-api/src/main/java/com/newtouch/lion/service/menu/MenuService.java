/*
 * Copyright (c)  2014, Newtouch
 * All rights reserved. 
 *
 * $id: MenuService.java 9552 2014年12月31日 下午5:17:30 WangLijun$
 */
package com.newtouch.lion.service.menu;

import java.util.List;

import com.newtouch.lion.model.menu.Menu;

/**
 * <p>
 * Title: Menu Service 定义
 * </p>
 * <p>
 * Description: Menu Service 定义
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
public interface MenuService {

	/***
	 * 根据用户名查询该用户所有菜单列表
	 * 
	 * @param userName  用户名
	 * @return Menus 菜单集合对象
	 */
	public List<Menu> doFindByUserName(String userName);
	
	/***
	 * 根据用户ID查询该用户所有菜单列表
	 * @param userName  用户ID
	 * @return Menus 菜单集合对象
	 */
	public List<Menu> doFindByUserId(Long userId);
	/***
	 * 根据用户ID查询该用户所有菜单列表，根据用户当前请求路径并匹配用户所有选择菜单
	 * @param userId  用户ID
	 * @param requestURL 请求URL
	 * @param contextPath 请求上下文路径
	 * @return List<Menu> 菜单集合对象
	 */
	public List<Menu> doFindByUserId(Long userId, String requestURL, String contextPath);

}
