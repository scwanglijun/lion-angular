package com.newtouch.lion.dao.system;

import com.newtouch.lion.dao.BaseDao;
import com.newtouch.lion.model.system.Tasks;
import com.newtouch.lion.page.PageResult;
import com.newtouch.lion.query.QueryCriteria;

public interface TasksDao extends BaseDao<Tasks, Long> {
	
	public int doDeleteById(Long id);
	
	public PageResult<Tasks> doFindByCriteria(QueryCriteria criteria);
}
