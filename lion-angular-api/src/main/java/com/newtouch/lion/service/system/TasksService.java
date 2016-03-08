package com.newtouch.lion.service.system;

import com.newtouch.lion.model.system.Tasks;
import com.newtouch.lion.page.PageResult;
import com.newtouch.lion.query.QueryCriteria;

/**
 * title :任务的services
 * 
 * @author lixiaohao
 *
 *description :对任务进行曾、删、改、查
 */

public interface TasksService {
	
	/***
	 * 根据ID查询Tasks对象，并返回Tasks对象
	 * @param id 参数ID
	 * @return tasks
	 */
	public Tasks doFindById(long id);
	/**
	 * 根据id删除Tasks对象
	 * */
	public int doDeleteById(Long id);
	
	/**删除参数对象
	 * @param tasks
	 * */
	public void  doDelete(Tasks Tasks);
	/***
	 * 根据不同查询条件查询系统参数，并返回分页对象
	 * @param queryCriteria
	 * @return PageResult<Tasks>
	 */
	public PageResult<Tasks>  doFindByCriteria(QueryCriteria queryCriteria);
	
	/***
	 * 保存Tasks对象
	 * @param tasks
	 */
	public void doCreate(Tasks tasks);
	
	/***
	 * 更新参数对象
	 * @param tasks
	 * @return Tasks
	 */
	public Tasks doUpdate(Tasks tasks);
	/***
	 * 根据图标类名获取图标
	 * @param beanClass
	 * @author lixiaohao
	 * @return {@link Tasks}
	 */
	public Tasks doFindTypeByTasksBeanClass(String beanClass);
	/**
	 * 判断图标的类名是否已存在，
	 * @param beanClass
	 * @return  boolean
	 * @author lixiaohao
	 * */
	public boolean doIsExistByTasksBeanClass(String beanClass); 
}
