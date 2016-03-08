/*
 * Copyright (c)  2014, NewTouch
 * All rights reserved. 
 *
 * $id: DataGridDirective.java 9552 2014-2-18 下午06:14:49 WangLijun$
 */
package com.newtouch.lion.web.tags.datagrid;

import com.newtouch.lion.comparator.DataColumnComparator;
import com.newtouch.lion.model.datagrid.DataColumn;
import com.newtouch.lion.model.datagrid.DataGrid;
import com.newtouch.lion.service.datagrid.DataGridService;
import com.newtouch.lion.web.freemarker.DirectiveUtils;
import com.newtouch.lion.web.tags.AbstractTag;
import freemarker.core.Environment;
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.util.*;

import static freemarker.template.ObjectWrapper.DEFAULT_WRAPPER;

/**
 * <p>
 * Title:DataGrid标签
 * </p>
 * <p>
 * Description:DataGrid标签，标准DataGrid框架D
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
public class DataGridTag  extends AbstractTag{
	/**日志*/
	private final Logger logger = LoggerFactory.getLogger(super.getClass());
	/**DataGrid的ID*/
	private static final String DATAGRID_ID = "id";
	/**参数名称*/
	private static final String DATAGRID = "datagrids";
	/**DataGrid服务*/
	@Autowired
	private DataGridService dataGridService;

	/*
	 * (non-Javadoc)
	 * 
	 * @see freemarker.template.TemplateDirectiveModel#execute(freemarker.core.
	 * Environment, java.util.Map, freemarker.template.TemplateModel[],
	 * freemarker.template.TemplateDirectiveBody)
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public void execute(Environment env, Map params, TemplateModel[] loopVars,
			TemplateDirectiveBody body) throws TemplateException, IOException {
		
		String dataGridId = DirectiveUtils.getString(DATAGRID_ID, params);
		logger.debug("dataGridId:{}",dataGridId);
		DataGrid dataGrid = dataGridService.doFindByTableId(dataGridId);
		if(dataGrid!=null){
			List<DataColumn> dataColumns = new ArrayList<DataColumn>(dataGrid.getColumns());
			Collections.sort(dataColumns, new DataColumnComparator());
			dataGrid.setSortColumns(dataColumns);
		}else{
			logger.error("未查询到ID为：{}数据表格的配置信息",dataGridId);
			dataGrid=new DataGrid();
		}

		Map<String, TemplateModel> paramWrap = new HashMap<String, TemplateModel>(params);

		paramWrap.put(DATAGRID, DEFAULT_WRAPPER.wrap(dataGrid));

		Map<String, TemplateModel> origMap = DirectiveUtils
				.addParamsToVariable(env, paramWrap);

		body.render(env.getOut());

		DirectiveUtils.removeParamsFromVariable(env, paramWrap, origMap);
	}
}
