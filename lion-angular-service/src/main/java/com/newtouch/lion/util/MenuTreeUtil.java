/*
 * Copyright (c)  2015, Newtouch
 * All rights reserved. 
 *
 * $id: MenuTreeUtil.java 9552 2015年1月4日 下午4:39:53 WangLijun$
 */
package com.newtouch.lion.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import com.newtouch.lion.common.codelist.CodeListConstant;
import com.newtouch.lion.common.constant.Constants;
import com.newtouch.lion.comparator.ResourceComparator;
import com.newtouch.lion.model.menu.Menu;
import com.newtouch.lion.model.system.Attributes;
import com.newtouch.lion.model.system.Resource;

/**
 * <p>
 * Title: 菜单树生成工具类
 * </p>
 * <p>
 * Description: 菜单树生成工具类，主要实现树递归生成树形结构的菜单
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
public class MenuTreeUtil {
	/** 日志 */
	private final static Logger logger = LoggerFactory.getLogger(ResourceTreeUtil.class);
	/** 菜单列表 */
	private static Map<String, String> menus = new HashMap<String, String>();
	/** 菜单列表 */
	static {
		menus.put(CodeListConstant.RESTYPE_MODULE_MENU_CATEGORY,CodeListConstant.RESTYPE_MODULE_MENU_CATEGORY);
		menus.put(CodeListConstant.RESTYPE_MODULE_CATEGORY_ITEM,CodeListConstant.RESTYPE_MODULE_CATEGORY_ITEM);
	}

	/***
	 * 根据资源列表和用户所属资源生成菜单列表
	 * @param resources 资源列表
	 * @param menuResourcesMap 用户所属资源列表
	 * @param isSort 是否排序
	 * @param count  
	 * @return   List<Menu> 菜单集合
	 */
	public static List<Menu> treeResource(List<Resource> resources,
			Map<Long, Resource> menuResourcesMap, Boolean isSort, int count) {
		// 子类菜单
		List<Menu> childrenMenu = new ArrayList<Menu>();
		ResourceComparator resourceComparator = new ResourceComparator();
		if (isSort) {
			Collections.sort(resources, resourceComparator);
		}
		count++;
		for (Resource resource : resources) {
			Boolean checked = Boolean.TRUE;
			Attributes attributes = new Attributes();
			attributes.setPath(resource.getPath());
 
		 
			if (CodeListConstant.RESTYPE_MODULE_CATEGORY_ITEM.equals(resource
					.getType())){
				if(menuResourcesMap.containsKey(resource.getId())){
					checked=Boolean.FALSE;
				}
				if(checked){
					continue;
				}

			}
			List<Menu> childrenNext = new ArrayList<Menu>();
			// 当资源子集大于1时，进入递归调用；
			if (resource.getResources().size() > 0) {
				List<Resource> resourcesList = new ArrayList<Resource>(
						resource.getResources());
				childrenNext = treeResource(resourcesList, menuResourcesMap,
						isSort, count);
				if (childrenNext.size() == 0) {
					continue;
				}
			}

			Boolean isLeaf = Boolean.TRUE;
			if (containsKey(resource.getType())
					&& resource.getResources().size() > 0) {
				isLeaf = Boolean.FALSE;
			}
			Menu menu = new Menu(resource.getId(),
					resource.getParentResourceId(), resource.getType(),
					resource.getPath(), resource.getNameZh(),
					resource.getNameEn(), resource.getSeqNum(), isLeaf,
					resource.getTarget(),resource.getIcon(),childrenNext);
			childrenMenu.add(menu);

		}
		return childrenMenu;
	}

	
	/***
	 * 根据资源列表和用户所属资源生成菜单列表
	 * @param resources 资源列表
	 * @param menuResourcesMap 用户所属资源列表
	 * @param isSort 是否排序
	 * @param count  资源生成
	 * @param 用户请求URL路径
	 * @param 用户请求上下文路径
	 * @return   List<Menu> 菜单集合
	 */
	public static List<Menu> treeResource(List<Resource> resources,
			Map<Long, Resource> menuResourcesMap, Boolean isSort, int count,String requestURL,String contextPath) {
		// 子类菜单
		List<Menu> childrenMenu = new ArrayList<Menu>();
		ResourceComparator resourceComparator = new ResourceComparator();
		if (isSort) {
			Collections.sort(resources, resourceComparator);
		}
		count++;
		for (Resource resource : resources) {
			Boolean checked = Boolean.TRUE;
			Boolean selected=Boolean.FALSE;
			Attributes attributes = new Attributes();
			attributes.setPath(resource.getPath());
			//attributes.setTarget(resource.getTarget());
		 
			if (CodeListConstant.RESTYPE_MODULE_CATEGORY_ITEM.equals(resource.getType())){
				if(menuResourcesMap.containsKey(resource.getId())){
					checked=Boolean.FALSE;
					if(equalsURL(requestURL, contextPath, resource.getPath())){
						logger.debug("requestURL:{}",requestURL);
						selected=Boolean.TRUE;
					}
				}
				if(checked){
					continue;
				}
				
			}
			logger.debug("count:{}",count);
			List<Menu> childrenNext = new ArrayList<Menu>();
			// 当资源子集大于1时，进入递归调用；
			if (resource.getResources().size() > 0&&!CodeListConstant.RESTYPE_MODULE_CATEGORY_ITEM.equals(resource.getType())) {
				List<Resource> resourcesList = new ArrayList<Resource>(
						resource.getResources());
				childrenNext = treeResource(resourcesList, menuResourcesMap,isSort, count,requestURL,contextPath);
				if (childrenNext.size() == 0) {
					continue;
				}
			}
			count--;
			logger.debug("count:{}:selected:{}",count,selected);
			Boolean isLeaf = Boolean.TRUE;
			if (containsKey(resource.getType())
					&& resource.getResources().size() > 0) {
				isLeaf = Boolean.FALSE;
			}
			if(!selected&&childrenNext.size()>0){
				selected=isHasSelectedMenu(childrenNext);
			}
			
			
			Menu menu = new Menu(resource.getId(),
					resource.getParentResourceId(), resource.getType(),
					resource.getPath(), resource.getNameZh(),
					resource.getNameEn(), resource.getSeqNum(), isLeaf,
					resource.getTarget(),resource.getIcon(),selected,childrenNext);
			childrenMenu.add(menu);

		}
		return childrenMenu;
	}
	/***
	 * 判断是否有子菜选中有菜单
	 * @param menus
	 * @return true false
	 */
	public static Boolean isHasSelectedMenu(List<Menu> menus){
		Boolean selected=Boolean.FALSE;
		for(Menu menu:menus){
			if(menu.getSelected()){
				selected=Boolean.TRUE;
				break;
			}
		}
		return selected;
	}
	
	/***
	 * 判断当前请求路径是否菜单路径一致
	 * @param requestURL 请求URL路径
	 * @param contextPath 请求上下文路径
	 * @param path 菜单路径
	 * @return 
	 */
	private static Boolean equalsURL(String requestURL,String contextPath,String path){
		if(StringUtils.isEmpty(path)){
			return Boolean.FALSE;
		}
		StringBuilder sb=new StringBuilder();
		sb.append(contextPath);
		if(!path.startsWith(Constants.PATH_SPLITTER)){
			sb.append(Constants.PATH_SPLITTER);
		}
		sb.append(path);
		return sb.toString().equals(requestURL)?Boolean.TRUE:Boolean.FALSE;
	}
	
	/**
	 * 是根据菜单类型判断是否该菜单资源
	 * 
	 * @param key
	 *            菜单类型的KEY
	 * @return 包括
	 */
	private static boolean containsKey(String key) {
		return menus.containsKey(key);
	}
	
	public static void main(String[] args) {
		String path="/system/application/index.htm";
		System.out.println(path.startsWith("/"));
		
	}

}
