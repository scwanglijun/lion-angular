package com.newtouch.lion.dao.system.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.newtouch.lion.common.sql.HqlUtils;
import com.newtouch.lion.dao.impl.BaseDaoImpl;
import com.newtouch.lion.dao.system.TasksDao;
import com.newtouch.lion.model.system.Tasks;
import com.newtouch.lion.page.PageResult;
import com.newtouch.lion.query.QueryCriteria;

@Repository("tasksDao")
public class TasksDaoImpl extends BaseDaoImpl<Tasks, Long> implements TasksDao {

	
	/**
	 * 序列化
	 */
	private static final long serialVersionUID = -1494476134711376183L;

	@Override
	public int doDeleteById(Long id) {
		String hql = "delete from Tasks t where t.id=:id";
		Map<String,Object> params = new HashMap<String, Object>();
		params.put("id", id);
		return this.updateHQL(hql, params);
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
		
		PageResult<Tasks> pageResult = this.query(hql, HqlUtils.generateCountHql(hql, null), params, startIndex, pageSize);
		
		return pageResult;
	}
	
	
	
}
