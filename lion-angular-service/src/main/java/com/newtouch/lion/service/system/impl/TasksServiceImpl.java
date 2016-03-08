package com.newtouch.lion.service.system.impl;

import com.newtouch.lion.common.sql.HqlUtils;
import com.newtouch.lion.dao.system.TasksDao;
import com.newtouch.lion.model.system.Tasks;
import com.newtouch.lion.page.PageResult;
import com.newtouch.lion.query.QueryCriteria;
import com.newtouch.lion.service.AbstractService;
import com.newtouch.lion.service.system.TasksService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.HashMap;
import java.util.Map;

@Service("tasksService")
public class TasksServiceImpl extends AbstractService implements TasksService {

	@Autowired
	TasksDao tasksDao;
	@Override
	public Tasks doFindById(long id) {
		return this.tasksDao.findById(id);
	}

	@Override
	public int doDeleteById(Long id) {
		return tasksDao.doDeleteById(id);
	}

	@Override
	public void doDelete(Tasks tasks) {
		this.tasksDao.remove(tasks);
	}

	@Override
	public PageResult<Tasks> doFindByCriteria(QueryCriteria criteria) {
		String queryEntry = "from Tasks";
		
		String[] whereBodies = {"name like:name","beanClass =:beanClass"};
		
		String fromJoinSubClause = "";
		
		Map<String, Object> params = criteria.getQueryCondition();
		
		String orderField = criteria.getOrderField();
		
		String orderDirection = criteria.getOrderDirection();
		
		String hql = HqlUtils.generateHql(queryEntry, fromJoinSubClause, whereBodies, orderField, orderDirection, params);
		
		int pageSize = criteria.getPageSize();
		
		int startIndex = criteria.getStartIndex();
		
		PageResult<Tasks> pageResult = this.tasksDao.query(hql, HqlUtils.generateCountHql(hql, null), params, startIndex, pageSize);
		
		return pageResult;
	}

	@Override
	public void doCreate(Tasks tasks) {
		Assert.notNull(tasks);
		this.tasksDao.save(tasks);
	}

	@Override
	public Tasks doUpdate(Tasks tasks) {
		Assert.notNull(tasks);
		this.tasksDao.update(tasks);
		return tasks;
	}

	@Override
	public Tasks doFindTypeByTasksBeanClass(String beanClass) {
		Assert.notNull(beanClass);
		String hql = "from Tasks where beanClass=:beanClass";
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("beanClass", beanClass);
		java.util.List<Tasks> tasks = tasksDao.query(hql, params);
		if (tasks != null && tasks.size() > 0) {
			return tasks.get(0);
		}
		return null;
	}

	@Override
	public boolean doIsExistByTasksBeanClass(String beanClass) {
		Assert.notNull(beanClass);
		Tasks tasks = this.doFindTypeByTasksBeanClass(beanClass);
		if (tasks != null)
			return true;
		return false;
	}

}
