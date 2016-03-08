/*
* Copyright (c)  2014, Newtouch
* All rights reserved. 
*
* $id: ApplicationPropertyService.java 9552 2014年12月23日 下午3:13:07 WangLijun$
*/
package com.newtouch.lion.service.application; 

import com.newtouch.lion.model.application.ApplicationProperty;
import com.newtouch.lion.page.PageResult;
import com.newtouch.lion.query.QueryCriteria;

/**
 * <p>
 * Title: 项目属性配置服务接口定义
 * </p>
 * <p>
 * Description: 项目属性配置服务接口定义主要修改增、删、改、查功能；
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
public interface ApplicationPropertyService {
	/***
	 * 根据记录ID查询该记录
	 * @param id 记录ID
	 * @return ApplicationProperty 配置项
	 */
	public  ApplicationProperty doFindById(Long id);
	/***
	 * 将对象保存到到数据库
	 * @param applicationProperty 项目应用配置项ID
	 * @return 返回持久化后的对象
	 */
	public  void  doCreateObj(ApplicationProperty applicationProperty);
	
	/**通用类型编码查询，并返回分页对象*/
	public PageResult<ApplicationProperty> doFindByCriteria(QueryCriteria criteria);
	
	/**
	 * 根据记录Id删除ApplicationProperty对象 
	 * @param id
	 * @return int
	 * */
	public int doDeleteById(Long id);
	/***
	 * 保存ApplicationProperty对象
	 * @author maojiawei
	 * @param ApplicationProperty
	 */
	public void doCreate(ApplicationProperty applicationProperty);
	/***
	 * 修改ApplicationProperty对象
	 * @author maojiawei
	 * @param ApplicationProperty
	 */
	public void doUpdate(ApplicationProperty applicationProperty);
}

	