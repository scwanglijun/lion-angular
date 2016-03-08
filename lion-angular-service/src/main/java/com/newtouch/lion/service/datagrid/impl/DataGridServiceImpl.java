/*
 * Copyright (c)  2013, Newtouch
 * All rights reserved. 
 *
 * $id: DataGridServiceImpl.java 9552 2013-3-27 下午7:43:08 WangLijun$
 */
package com.newtouch.lion.service.datagrid.impl;

import com.newtouch.lion.common.Assert;
import com.newtouch.lion.common.sql.HqlUtils;
import com.newtouch.lion.comparator.DataColumnComparator;
import com.newtouch.lion.dao.datagrid.DataGridDao;
import com.newtouch.lion.json.JSONParser;
import com.newtouch.lion.model.datagrid.DataColumn;
import com.newtouch.lion.model.datagrid.DataGrid;
import com.newtouch.lion.page.PageResult;
import com.newtouch.lion.query.QueryCriteria;
import com.newtouch.lion.service.AbstractService;
import com.newtouch.lion.service.datagrid.DataColumnService;
import com.newtouch.lion.service.datagrid.DataGridService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.*;

/**
 * <p>
 * Title:DataGrid服务类
 * </p>
 * <p>
 * Description:DataGrid服务类
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
@Service("dataGridService")
public class DataGridServiceImpl extends AbstractService implements DataGridService {

	
	@Autowired
	private DataGridDao dataGridDao;
	
	@Autowired
	private DataColumnService dataColumnService;

	/* (non-Javadoc)
	 * @see com.lion.framework.service.datagrid.DataGridService#doCreateDataGrid(com.lion.framework.model.datagrid.DataGrid)
	 */
	@Override
	public void doCreateDataGrid(DataGrid dataGrid) {
		this.dataGridDao.save(dataGrid);
	}
	
	

	/* (non-Javadoc)
	 * @see com.lion.framework.service.datagrid.DataGridService#doFindByTableId(java.lang.String)
	 */
	@Override
	public DataGrid doFindByTableId(String tableId) {
		 
		String hql="from  DataGrid where tableId=:tableId";
		Map<String,Object> params=new HashMap<String, Object>();
		params.put("tableId",tableId);
		List<DataGrid>  dataGrids=this.dataGridDao.query(hql, params);
		if(!CollectionUtils.isEmpty(dataGrids))
			return dataGrids.get(0);
		return null;
	}
	
	

	/* (non-Javadoc)
	 * @see com.newtouch.lion.service.datagrid.DataGridService#doFindByTableIdAndSort(java.lang.String)
	 */
	@Override
	public DataGrid doFindByTableIdAndSort(String tableId) {
		String hql="from  DataGrid where tableId=:tableId";
		Map<String,Object> params=new HashMap<String, Object>();
		params.put("tableId",tableId);
		List<DataGrid>  dataGrids=this.dataGridDao.query(hql, params);
		if(!CollectionUtils.isEmpty(dataGrids)){
			DataGrid dataGrid=dataGrids.get(0);
			List<DataColumn> dataColumns = new ArrayList<DataColumn>(dataGrid.getColumns());			
			Collections.sort(dataColumns, new DataColumnComparator());			
			dataGrid.setSortColumns(dataColumns);
			return dataGrid;
		}
		return null;
	}



	/* (non-Javadoc)
	 * @see com.lion.framework.service.datagrid.DataGridService#doGetById(java.lang.Long)
	 */
	@Override
	public DataGrid doGetById(Long id) {
		return  this.dataGridDao.getById(id);
	}
	
	

	/* (non-Javadoc)
	 * @see com.lion.framework.service.datagrid.DataGridService#doFindAll()
	 */
	@Override
	public List<DataGrid> doFindAll() {
		 return this.dataGridDao.findAll();
	}

	/* (non-Javadoc)
	 * @see com.lion.framework.service.datagrid.DataGridService#doUpdate(com.lion.framework.model.datagrid.DataGrid)
	 */
	@Override
	public DataGrid doUpdate(DataGrid dataGrid) {
		 this.dataGridDao.update(dataGrid);
		 return dataGrid;
	}

	/* (non-Javadoc)
	 * @see com.lion.framework.service.datagrid.DataGridService#doDelete(com.lion.framework.model.datagrid.DataGrid)
	 */
	@Override
	public void doDelete(DataGrid dataGrid) {
		dataGridDao.remove(dataGrid);
	}

	/* (non-Javadoc)
	 * @see com.lion.framework.service.datagrid.DataGridService#doDeleteById(java.lang.Long)
	 */
	@Override
	public int doDeleteById(Long id) {
		String hql="delete from DataGrid p where p.id=:id";
		Map<String,Object> params=new  HashMap<String, Object>();
		params.put("id",id);
		return this.dataGridDao.updateHQL(hql, params);
	}
	
	

	/* (non-Javadoc)
	 * @see com.lion.framework.service.datagrid.DataGridService#doFindByCriteria(com.lion.framework.common.QueryCriteria)
	 */
	@Override
	public PageResult<DataGrid> doFindByCriteria(QueryCriteria criteria) {
		String queryEntry = " from DataGrid ";

		String[] whereBodies = {" tableId  like :tableId","type=:type","title like :title"};

		String fromJoinSubClause = "";

		Map<String, Object> params = criteria.getQueryCondition();

		String orderField =criteria.getOrderField();

		String orderDirection = criteria.getOrderDirection();

		String hql = HqlUtils.generateHql(queryEntry, fromJoinSubClause,whereBodies, orderField, orderDirection, params);

		int pageSize = criteria.getPageSize();

		int startIndex = criteria.getStartIndex();

		PageResult<DataGrid> pageResult = this.dataGridDao.query(hql,HqlUtils.generateCountHql(hql, null), params, startIndex,pageSize);
		
		return pageResult;
	}



	/* (non-Javadoc)
	 * @see com.lion.framework.service.datagrid.DataGridService#doFindByCriteriaById(com.lion.framework.common.QueryCriteria, java.lang.String)
	 */
	@Override
	public String doFindByCriteria(QueryCriteria queryCriteria,
			String tableId) {
		PageResult<DataGrid> pageResult=this.doFindByCriteria(queryCriteria);
		Set<String> properties=this.dataColumnService.doFindColumnsByTableId(tableId);
		return JSONParser.toJSONDataGridString(pageResult, properties);
	}


	
	/* (non-Javadoc)
	 * @see com.lion.framework.service.datagrid.DataGridService#doFindAllForCombox()
	 */
	@Override
	public String doFindAllForCombox() {
		List<DataGrid> dataGrids=this.doFindAll();
		
		Set<String> properties=new HashSet<String>();
		properties.add("id");
		properties.add("tableId");
		return JSONParser.toJSONString(dataGrids,properties);
	}



	/* (non-Javadoc)
	 * @see com.lion.framework.service.datagrid.DataGridService#doFindByType(java.lang.String)
	 */
	@Override
	public List<DataGrid> doFindByType(String type) {
		String hql = " from DataGrid d where d.type=:type";
		Map<String,Object> params=new  HashMap<String, Object>();
		params.put("type", type);
		return this.dataGridDao.query(hql, params);
	}



	/* (non-Javadoc)
	 * @see com.lion.framework.service.datagrid.DataGridService#doFindComboxByType(java.lang.String)
	 */
	@Override
	public String doFindComboxByType(String type) {
		
		Set<String> properties=new HashSet<String>();
		properties.add("id");
		properties.add("tableId");
		if(StringUtils.isEmpty(type)){
			return this.doFindAllForCombox();
		}
		List<DataGrid> dataGrids=this.doFindByType(type);
		return JSONParser.toJSONString(dataGrids,properties);
	}



	/* (non-Javadoc)
	 * @see com.newtouch.lion.service.datagrid.DataGridService#doIsExistByTableId(java.lang.String)
	 */
	@Override
	public boolean doIsExistByTableId(String tableId) {
		// TODO Auto-generated method stub
		Assert.notNull(tableId);
		DataGrid dataGrid = this.doFindTypeByTableId(tableId);
		if (dataGrid != null)
			return true;
		return false;
	}



	/* (non-Javadoc)
	 * @see com.newtouch.lion.service.datagrid.DataGridService#doFindTypeByTableId(java.lang.String)
	 */
	@Override
	public DataGrid doFindTypeByTableId(String tableId) {
		// TODO Auto-generated method stub
		Assert.notNull(tableId);
		String hql = "from DataGrid  where tableId=:tableId";
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("tableId", tableId);
		List<DataGrid> dataGrids = dataGridDao.query(hql, params);
		if (dataGrids != null && dataGrids.size() > 0) {
			return dataGrids.get(0);
		}
		return null;
	}



	/* (non-Javadoc)
	 * @see com.newtouch.lion.service.datagrid.DataGridService#doCreate(com.newtouch.lion.model.datagrid.DataGrid)
	 */
	@Override
	public void doCreate(DataGrid dataGrid) {
		// TODO Auto-generated method stub
		Assert.notNull(dataGrid);
		dataGridDao.save(dataGrid);
	}
	
	
}
