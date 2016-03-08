
/*
* Copyright (c)  2015, NewTouch
* All rights reserved. 
*
* $id: IconService.java 9552 Mar 4, 2015 11:41:05 AM MaoJiaWei$
*/
package com.newtouch.lion.service.system; 

import java.util.List;

import com.newtouch.lion.model.system.Icon;
import com.newtouch.lion.page.PageResult;
import com.newtouch.lion.query.QueryCriteria;

/**
 * <p>
 * Title: 图标icon的Service层
 * </p>
 * <p>
 * Description: 图标icon的Service层
 * </p>
 * <p>
 * Copyright: Copyright (c) 2015
 * </p>
 * <p>
 * Company: NewTouch
 * </p>
 * 
 * @author MaoJiaWei
 * @version 1.0
 */
public interface IconService {
	/***
	 * 根据ID查询Icon对象，并返回Icon对象
	 * @param id 参数ID
	 * @return icon
	 */
	public Icon doFindById(long id);
	/**
	 * 根据id删除Icon对象
	 * */
	public int doDeleteById(Long id);
	
	/**删除参数对象
	 * @param icon
	 * */
	public void  doDelete(Icon icon);
	/***
	 * 根据不同查询条件查询系统参数，并返回分页对象
	 * @param queryCriteria
	 * @return PageResult<Icon>
	 */
	public PageResult<Icon>  doFindByCriteria(QueryCriteria queryCriteria);
	
	/***
	 * 保存Icon对象
	 * @param icon
	 */
	public void doCreate(Icon icon);
	
	/***
	 * 更新参数对象
	 * @param icon
	 * @return Icon
	 */
	public Icon doUpdate(Icon icon);
	/***
	 * 根据图标类名获取图标
	 * @param iconClass
	 * @author maojiawei
	 * @return {@link Icon}
	 */
	public Icon doFindTypeByIconClass(String iconClass);
	/***
	 * 根据类型查询ICON的
	 * @param iconType ICON的图标的类型
	 * @return   List<Icon> 列表图标
	 */
	public List<Icon> doFindByType(String iconType);
	
	/**
	 * 判断图标的类名是否已存在，
	 * @param iconClass
	 * @return  boolean
	 * @author maojiawei
	 * */
	public boolean doIsExistByIconClass(String iconClass); 
}

	