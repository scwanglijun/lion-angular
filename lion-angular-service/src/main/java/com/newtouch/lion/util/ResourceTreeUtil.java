/*
 * Copyright (c)  2014, NewTouch
 * All rights reserved. 
 *
 * $id: ResourceTreeUtil.java 9552 2014-4-7 下午11:31:28 WangLijun$
 */
package com.newtouch.lion.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.newtouch.lion.tree.State;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.newtouch.lion.common.codelist.CodeListConstant;
import com.newtouch.lion.comparator.ResourceComparator;
import com.newtouch.lion.model.system.Attributes;
import com.newtouch.lion.model.system.Resource;
import com.newtouch.lion.tree.TreeNode;






/**
 * <p>
 * Title: 菜单排序实现方式
 * </p>
 * <p>
 * Description: 菜单排序实现类，根据指定顺序进行升序排序，如果顺序相等则根据菜单ID升序排序
 * </p>
 * <p>
 * Copyright: Copyright (c) 2014
 * </p>
 * <p>
 * Company: NewTouch
 * </p>
 * 
 * @author WangLijun
 * @version 1.0
 */
public class ResourceTreeUtil {
	
	
	private  final static Logger  logger=LoggerFactory.getLogger(ResourceTreeUtil.class); 
	
	
	private static Map<String,String>  menusMap=new HashMap<String,String>();
	static{
		menusMap.put(CodeListConstant.RESTYPE_MENU,CodeListConstant.RESTYPE_MENU);
		menusMap.put(CodeListConstant.RESTYPE_MODULE,CodeListConstant.RESTYPE_MODULE);
		menusMap.put(CodeListConstant.RESTYPE_MODULE_MENU_CATEGORY,CodeListConstant.RESTYPE_MODULE_MENU_CATEGORY);
		menusMap.put(CodeListConstant.RESTYPE_APPLICATION,CodeListConstant.RESTYPE_APPLICATION);
		menusMap.put(CodeListConstant.RESTYPE_MODULE_CATEGORY_ITEM,CodeListConstant.RESTYPE_MODULE_CATEGORY_ITEM);
	}
	/***
	 * 将Set集合转换为Map集合
	 * @param resources Set集合
	 * @return Map<Long,Resource>
	 */
	public  static Map<Long,Resource> convertSetToMap(Set<Resource> resources){
		Map<Long,Resource> resourcesMap=new HashMap<Long, Resource>();
		for(Resource resource:resources){
			resourcesMap.put(resource.getId(), resource); 
		}
		return resourcesMap;
	}
	 
	/***
	 * 递归调用过滤已经授权的菜单TreeNode
	 * @param resources  未过滤的菜单
	 * @param menuResourcesMap 已经授权菜单
	 * @param isSort 是否排序
	 * @return
	 */
	public static List<TreeNode> resourceAttr(List<Resource> resources,Map<Long, Resource> menuResourcesMap,Boolean isSort) {
		List<TreeNode>  children=new ArrayList<TreeNode>();
		ResourceComparator resourceComparator=new ResourceComparator();
		if(isSort){
			Collections.sort(resources,resourceComparator);
		}
		
		for(Resource resource:resources){
		 
				Boolean checked=Boolean.TRUE;
				Attributes attributes=new Attributes();			
				attributes.setPath(resource.getPath());
				//attributes.setTarget(resource.getTarget());
				if(resource.getType().equals(CodeListConstant.RESTYPE_MODULE_CATEGORY_ITEM)){
					if(menuResourcesMap.containsKey(resource.getId())){
						checked=Boolean.FALSE;
					}
					if(checked){
						continue;
					}
				}
				List<TreeNode> childrenNext=new ArrayList<TreeNode>();
				//当资源子集大于1时，进入递归调用；
				if(resource.getResources().size()>0){
					List<Resource> resourcesList=new ArrayList<Resource>(resource.getResources());
					childrenNext=resourceAttr(resourcesList,menuResourcesMap,isSort);
					if(childrenNext.size()==0){
						continue;
					}
				}
			 
				Long id=resource.getId();
				String text=resource.getNameZh();
				int  orderId=resource.getSeqNum();
				boolean isLeaf=false;			 
				TreeNode  treeNode=new TreeNode(id,text, State.open,checked,"",orderId,isLeaf,attributes.getPath(),attributes,childrenNext);
				children.add(treeNode);
		
		}
		return children;
	}
	
	/***
	 * 递归调用过滤已经授权的菜单TreeNode
	 * @param resources  未过滤的菜单
	 * @param menuResourcesMap 已经授权菜单
	 * @param isSort 是否排序
	 * @return
	 */
	public static List<TreeNode> resourceAttrUser(List<Resource> resources,Map<Long, Resource> menuResourcesMap,Boolean isSort,int count) {
		List<TreeNode>  children=new ArrayList<TreeNode>();
		ResourceComparator  resourceComparator=new ResourceComparator();
		if(isSort){
			Collections.sort(resources,resourceComparator);
		}
		count++;
		for(Resource resource:resources){
				Boolean checked=Boolean.TRUE;
				Attributes attributes=new Attributes();			
				attributes.setPath(resource.getPath());
				//attributes.setTarget(resource.getTarget());
				if(CodeListConstant.RESTYPE_MODULE_CATEGORY_ITEM.equals(resource.getType())){
					if(menuResourcesMap.containsKey(resource.getId())){
						checked=Boolean.FALSE;
					}
					
					if(checked){
						logger.info("checked:{}",checked);
						continue;
					}
				}
			 
				List<TreeNode> childrenNext=new ArrayList<TreeNode>();
				//当资源子集大于1时，进入递归调用；
				if(resource.getResources().size()>0){
					List<Resource> resourcesList=new ArrayList<Resource>(resource.getResources());
					childrenNext=resourceAttrUser(resourcesList,menuResourcesMap,isSort,count);
					if(childrenNext.size()==0){
						continue;
					}
				}
				
				Long id=resource.getId();
				
				String text=resource.getNameZh();
				
				int  orderId=resource.getSeqNum();
				 
				if(containsKey(resource.getType())&&resource.getResources().size()>0){
					boolean isLeaf=false;			 
					TreeNode  treeNode=new TreeNode(id,text, State.open,checked,"",orderId,isLeaf,attributes.getPath(),attributes,childrenNext);
					children.add(treeNode);
				}else if(resource.getType().equals(CodeListConstant.RESTYPE_MODULE_CATEGORY_ITEM)){
					boolean isLeaf=false;			 
					TreeNode  treeNode=new TreeNode(id,text, State.open,checked,"",orderId,isLeaf,attributes.getPath(),attributes,childrenNext);
					children.add(treeNode);
				}
						
		
		}
		return children;
	}
	
	
	private static boolean containsKey(String key){
		return menusMap.containsKey(key);
	}
	/***
	 * 递归调用过滤已经授权的菜单列表
	 * @param resources 菜单
	 * @param menuResourcesMap
	 * @return List<Resource> 返回过滤后的菜单
	 */
	public static List<Resource> findModuleResource(List<Resource>  resources,Map<Long,Resource>  menuResourcesMap){
		List<Resource>  returnResources=new ArrayList<Resource>();
		for(Resource resource:resources){
			
			if(menuResourcesMap.containsKey(resource.getId())){			    
				returnResources.add(resource);
			}
			List<Resource> childrenNext=new ArrayList<Resource>();			
			//当资源子集大于1时，进入递归调用；
			if(resource.getResources().size()>0){
				List<Resource> resourcesList=new ArrayList<Resource>(resource.getResources());
				childrenNext=ResourceTreeUtil.findModuleResource(resourcesList,menuResourcesMap);
			}
			if(childrenNext.size()>0){
				returnResources.add(resource);
			}
		}
		return returnResources;
	}
}
