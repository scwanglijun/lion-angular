/*
 * Copyright (c)  2012, Newtouch
 * All rights reserved. 
 *
 * $id: TsParameterServiceImpl.java 9552 2012-7-8 下午09:26:37 WangLijun$
 */
package com.newtouch.lion.service.system.impl;

import com.newtouch.lion.common.Assert;
import com.newtouch.lion.dao.system.ParameterDao;
import com.newtouch.lion.model.system.CodeList;
import com.newtouch.lion.model.system.Parameter;
import com.newtouch.lion.page.PageResult;
import com.newtouch.lion.query.QueryCriteria;
import com.newtouch.lion.service.AbstractService;
import com.newtouch.lion.service.system.CodeListService;
import com.newtouch.lion.service.system.ParameterService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * Title:系统参数Service层实现
 * </p>
 * <p>
 * Description:系统参数Service层实现
 * </p>
 * <p>
 * Copyright: Copyright (c) 2012
 * </p>
 * <p>
 * Company: lion
 * </p>
 * 
 * @author WangLijun
 * @version 1.0
 */
@Service("parameterService")
public class ParameterServiceImpl extends AbstractService implements
		ParameterService {
	@Autowired
	private ParameterDao parameterDao;
	@Autowired
	private CodeListService codeListService;

	/*
	 * <p>Title: doFindById</p> <p>Description: </p>
	 * 
	 * @param id
	 * 
	 * @return
	 * 
	 * @see
	 * com.lion.framework.service.system.TsParameterService#doFindById(long)
	 */

	public Parameter doFindById(long id) {
		Assert.notNull(id);
		return parameterDao.findById(id);
	}

	/*
	 * <p>Title: idoCreate</p> <p>Description: </p>
	 * 
	 * @param tsParameter
	 * 
	 * @see com.lion.framework.service.system.TsParameterService#idoCreate(com
	 * .lion.framework.model.system.)
	 */

	public void doCreate(Parameter parameter) {
		Assert.notNull(parameter);
		parameterDao.save(parameter);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.lion.framework.service.system.ParameterService#doIsExistByNameEn(
	 * java.lang.String)
	 */
	@Override
	public boolean doIsExistByNameEn(String nameEn) {
		Assert.notNull(nameEn);
		Parameter parameter = this.doFindTypeByNameEn(nameEn);
		if (parameter != null)
			return true;
		return false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.lion.framework.service.system.TsParameterService#doFindType(java
	 * .lang.String, java.lang.String)
	 */
	@Override
	public Parameter doFindTypeByNameEn(String nameEn) {
		Assert.notNull(nameEn);
		return parameterDao.doFindTypeByNameEn(nameEn);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.lion.framework.service.system.ParameterService#doDelele(com.lion
	 * .framework.model.system.Parameter)
	 */
	@Override
	public void doDelete(Parameter parameter) {
		this.parameterDao.remove(parameter);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.lion.framework.service.system.ParameterService#doUpdate(com.lion.
	 * framework.model.system.Parameter)
	 */
	@Override
	public Parameter doUpdate(Parameter parameter) {
		this.parameterDao.update(parameter);
		return parameter;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.lion.framework.service.system.ParameterService#doDeleteById(java.
	 * lang.Long)
	 */
	@Override
	public int doDeleteById(Long id) {
		return parameterDao.doDeleteById(id);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.lion.framework.service.system.ParameterService#doFindAll()
	 */
	@Override
	public List<Parameter> doFindAll() {
		return this.parameterDao.findAll();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.lion.framework.service.system.ParameterService#doFindByCriteria
	 * (com.lion.framework.common.QueryCriteria)
	 */
	@Override
	public PageResult<Parameter> doFindByCriteria(QueryCriteria queryCriteria) {
		
		return parameterDao.doFindByCriteria(queryCriteria);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.lion.framework.service.system.ParameterService#doUpdate(java.lang
	 * .Long, java.lang.String, java.lang.String, java.lang.String,
	 * java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public void doUpdate(Long id, String type, String nameEn, String nameZh,
			String value, String description, Boolean editable) {
		Parameter parameter = this.doFindById(id);
		parameter.setType(type);
		parameter.setNameEn(nameEn);
		parameter.setNameZh(nameZh);
		parameter.setValue(value);
		parameter.setDescription(description);
		parameter.setEditable(editable);
		this.parameterDao.save(parameter);

	}

	 

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.lion.framework.service.system.ParameterService#doFindCodeListByNameEn
	 * (java.lang.String, java.lang.String)
	 */
	@Override
	public List<CodeList> doFindCodeListByNameEn(String codeTypeNameEn,
			String selectedValue) {
		List<CodeList> codeLists = codeListService.doFindCodeListByCodeTypeNameEn(codeTypeNameEn);
		List<CodeList> returnCodeLists = null;
		if (StringUtils.isNotEmpty(selectedValue)) {
			returnCodeLists = new ArrayList<CodeList>();
			for (CodeList codeList : codeLists) {
				if (codeList.getNameEn().equals(selectedValue)) {
					codeList.setSelected(Boolean.TRUE);
				} else {
					codeList.setSelected(Boolean.FALSE);
				}
				returnCodeLists.add(codeList);
			}
		} else {
			returnCodeLists = codeLists;
		}
		return returnCodeLists;
	}

}
