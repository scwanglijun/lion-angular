
/*
* Copyright (c)  2014, Newtouch
* All rights reserved. 
*
* $id: PositionServiceImpl.java 9552 2014年12月25日 上午11:25:25 WangLijun$
*/
package com.newtouch.lion.service.system.impl;

import com.newtouch.lion.common.Assert;
import com.newtouch.lion.common.sql.HqlUtils;
import com.newtouch.lion.dao.system.PositionDao;
import com.newtouch.lion.model.system.Position;
import com.newtouch.lion.page.PageResult;
import com.newtouch.lion.query.QueryCriteria;
import com.newtouch.lion.service.AbstractService;
import com.newtouch.lion.service.system.PositionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * Title: 
 * </p>
 * <p>
 * Description: 
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
@Service
public class PositionServiceImpl extends AbstractService implements PositionService{

	/**岗位信息Dao*/
	@Autowired
	private PositionDao positionDao;

	@Override
	public PageResult<Position> doFindByCriteria(QueryCriteria criteria) {

		String queryEntry = " from Position ";

		String[] whereBodies = { "nameZh like :nameZh"};

		String fromJoinSubClause = "";

		Map<String, Object> params = criteria.getQueryCondition();

		String orderField = criteria.getOrderField();

		String orderDirection = criteria.getOrderDirection();

		String hql = HqlUtils.generateHql(queryEntry, fromJoinSubClause,
				whereBodies, orderField, orderDirection, params);

		int pageSize = criteria.getPageSize();

		int startIndex = criteria.getStartIndex();

		PageResult<Position> pageResult = this.positionDao.query(hql,HqlUtils.generateCountHql(hql, null), params, startIndex,pageSize);

		return pageResult;
	}

	@Override
	public void doCreatePosition(Position position) {
		// TODO Auto-generated method stub
		this.positionDao.save(position);
	}

	@Override
	public void doDelete(Position position) {
		// TODO Auto-generated method stub
		this.positionDao.remove(position);
	}

	@Override
	public int doDeleteById(Long id) {
		// TODO Auto-generated method stub
		String hql = "delete from Position p where p.id=:id";
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("id", id);
		return this.positionDao.updateHQL(hql, params);
	}

	@Override
	public Position doFindById(Long id) {
		// TODO Auto-generated method stub
		return this.positionDao.findById(id);
	}

	@Override
	public void doUpdate(Position position) {
		// TODO Auto-generated method stub
		this.positionDao.update(position);
	}

	@Override
	public boolean doIsExistByNameEn(String nameEn) {
		// TODO Auto-generated method stub
		Assert.notNull(nameEn);
		Position position = this.doFindTypeByNameEn(nameEn);
		if (position != null)
			return true;
		return false;
	}

	@Override
	public Position doFindTypeByNameEn(String nameEn) {
		// TODO Auto-generated method stub
		Assert.notNull(nameEn);
		String hql = "from Position  where nameEn=:nameEn";
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("nameEn", nameEn);
		java.util.List<Position> positions = positionDao.query(hql, params);
		if (positions != null && positions.size() > 0) {
			return positions.get(0);
		}
		return null;
	}
}

	