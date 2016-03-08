/*
 * Copyright (c)  2013, Newtouch
 * All rights reserved. 
 *
 * $id: DataColumnServiceImpl.java 9552 2013-3-27 下午7:40:00 WangLijun$
 */
package com.newtouch.lion.service.datagrid.impl;

import com.newtouch.lion.common.Assert;
import com.newtouch.lion.common.sql.HqlUtils;
import com.newtouch.lion.dao.datagrid.DataColumnDao;
import com.newtouch.lion.json.JSONParser;
import com.newtouch.lion.model.datagrid.DataColumn;
import com.newtouch.lion.page.PageResult;
import com.newtouch.lion.query.QueryCriteria;
import com.newtouch.lion.service.AbstractService;
import com.newtouch.lion.service.datagrid.DataColumnService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * <p>
 * Title:DataColumn服务实现类
 * </p>
 * <p>
 * Description:DataColumn服务实现类
 * </p>
 * <p>
 * Copyright: Copyright (c) 2013
 * </p>
 * <p>
 * Company: Newtouch
 * </p>
 * 
 * @author WangLijun
 * @version 1.0
 */
@Service("dataColumnService")
public class DataColumnServiceImpl extends AbstractService implements
		DataColumnService {

	@Autowired
	private DataColumnDao dataColumnDao;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.lion.framework.service.datagrid.DataColumnService#doCreateDataColumn
	 * (com.lion.framework.model.datagrid.DataColumn)
	 */
	@Override
	public void doCreateDataColumn(DataColumn dataColumn) {
		dataColumnDao.save(dataColumn);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.lion.framework.service.datagrid.DataColumnService#doGetById(java.
	 * lang.Long)
	 */
	@Override
	public DataColumn doGetById(Long id) {
		return dataColumnDao.getById(id);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.lion.framework.service.datagrid.DataColumnService#doUpdate(com.lion
	 * .framework.model.datagrid.DataColumn)
	 */
	@Override
	public DataColumn doUpdate(DataColumn dataColumn) {

		dataColumnDao.update(dataColumn);
		return dataColumn;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.lion.framework.service.datagrid.DataColumnService#doDeleteObj(com
	 * .lion.framework.model.datagrid.DataColumn)
	 */
	@Override
	public void doDeleteObj(DataColumn dataColumn) {
		dataColumnDao.remove(dataColumn);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.lion.framework.service.datagrid.DataColumnService#doDeleteById(java
	 * .lang.Long)
	 */
	@Override
	public int doDeleteById(Long id) {
		 return this.doDeleteById(id);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.lion.framework.service.datagrid.DataColumnService#doFindByDataGridId
	 * (java.lang.Long)
	 */
	@Override
	public List<DataColumn> doFindByDataGridId(Long dataGridId) {
		String hql = "from  DataColumn where dataGridId=:dataGridId";
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("dataGridId", dataGridId);
		List<DataColumn> dataColumns = this.dataColumnDao.query(hql, params);
		return dataColumns;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.lion.framework.service.datagrid.DataColumnService#doFindByTableId
	 * (java.lang.String)
	 */
	@Override
	public List<DataColumn> doFindByTableId(String tableId) {
		String hql = "from DataColumn where dataGrid.tableId=:tableId";
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("tableId", tableId);
		List<DataColumn> dataColumns = this.dataColumnDao.query(hql, params);
		return dataColumns;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.lion.framework.service.datagrid.DataColumnService#doFindColumnsByTableId
	 * (java.lang.String)
	 */
	@Override
	public Set<String> doFindColumnsByTableId(String tableId) {
		List<DataColumn> dataColumns = this.doFindByTableId(tableId);
		Set<String> properties = new HashSet<String>();
		for (DataColumn dataColumn : dataColumns) {
			properties.add(dataColumn.getField());
		}
		return properties;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.lion.framework.service.datagrid.DataColumnService#doFindByCriteria
	 * (com.lion.framework.common.QueryCriteria)
	 */
	@Override
	public PageResult<DataColumn> doFindByCriteria(QueryCriteria criteria) {
		String queryEntry = " from DataColumn ";

		String[] whereBodies = { "name like:name", "dataGridId=:dataGridId" };

		String fromJoinSubClause = "";

		Map<String, Object> params = criteria.getQueryCondition();

		String orderField = criteria.getOrderField();

		String orderDirection = criteria.getOrderDirection();

		String hql = HqlUtils.generateHql(queryEntry, fromJoinSubClause,
				whereBodies, orderField, orderDirection, params);

		int pageSize = criteria.getPageSize();

		int startIndex = criteria.getStartIndex();

		PageResult<DataColumn> pageResult = this.dataColumnDao.query(hql,HqlUtils.generateCountHql(hql, null), params, startIndex,pageSize);
		for(DataColumn  dataColumn:pageResult.getContent()){
		    dataColumn.getDataGrid().getTitle();
		}
		return pageResult;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.lion.framework.service.datagrid.DataColumnService#doFindByCriteria
	 * (com.lion.framework.common.QueryCriteria, java.lang.String)
	 */
	@Override
	public String doFindByCriteria(QueryCriteria queryCriteria, String tableId) {
		PageResult<DataColumn> pageResult = this
				.doFindByCriteria(queryCriteria);
		Set<String> properties = this.doFindColumnsByTableId(tableId);
		return JSONParser.toJSONDataGridString(pageResult, properties);
	}

	/* (non-Javadoc)
	 * @see com.newtouch.lion.service.datagrid.DataColumnService#doIsExistByName(java.lang.String)
	 */
	@Override
	public boolean doIsExistByName(String name) {
		Assert.notNull(name);
		DataColumn dataColumn = this.doFindTypeByName(name);
		if (dataColumn != null)
			return true;
		return false;
	}

	/* (non-Javadoc)
	 * @see com.newtouch.lion.service.datagrid.DataColumnService#doFindTypeByName(java.lang.String)
	 */
	@Override
	public DataColumn doFindTypeByName(String name) {
		// TODO Auto-generated method stub
		Assert.notNull(name);
		String hql = "from DataColumn  where name=:name";
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("name", name);
		List<DataColumn> dataColumns = dataColumnDao.query(hql, params);
		if (dataColumns != null && dataColumns.size() > 0) {
			return dataColumns.get(0);
		}
		return null;
	}

	/* (non-Javadoc)
	 * @see com.newtouch.lion.service.datagrid.DataColumnService#doCreate(com.newtouch.lion.model.datagrid.DataColumn)
	 */
	@Override
	public void doCreate(DataColumn dataColumn) {
		Assert.notNull(dataColumn);
		dataColumnDao.save(dataColumn);
	}
	
	/* (non-Javadoc)
	 * @see com.newtouch.lion.service.datagrid.DataColumnService#doFindTypeByName(java.lang.String)
	 */
	@Override
	public DataColumn doFindTypeByNameandDatagridId(String name,Long datagridId) {
		Assert.notNull(name);
		String hql = "from DataColumn  where name=:name and dataGridId=:datagridId";
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("name", name);
		params.put("datagridId", datagridId);
		List<DataColumn> dataColumns = dataColumnDao.query(hql, params);
		if (dataColumns != null && dataColumns.size() > 0) {
			return dataColumns.get(0);
		}
		return null;
	}
	
	/* (non-Javadoc)
	 * @see com.newtouch.lion.service.datagrid.DataColumnService#doIsExistByNameandDataGridId(java.lang.String, java.lang.Long)
	 */
	@Override
	public boolean doIsExistByNameandDataGridId(String name, Long dataGridId) {
		DataColumn dataColumn = this.doFindTypeByNameandDatagridId(name,dataGridId);
		if (dataColumn != null)
			return true;
		return false;
	}
	
	
}
