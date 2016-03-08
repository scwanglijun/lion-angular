/*
 * Copyright (c)  2013, Newtouch
 * All rights reserved. 
 *
 * $id: CodeTypeServiceImpl.java 9552 2013-1-12 下午8:35:50 WangLijun$
 */
package com.newtouch.lion.service.system.impl;

import com.newtouch.lion.common.Assert;
import com.newtouch.lion.dao.system.CodeTypeDao;
import com.newtouch.lion.json.JSONParser;
import com.newtouch.lion.model.system.CodeType;
import com.newtouch.lion.page.PageResult;
import com.newtouch.lion.query.QueryCriteria;
import com.newtouch.lion.service.AbstractService;
import com.newtouch.lion.service.datagrid.DataColumnService;
import com.newtouch.lion.service.system.CodeService;
import com.newtouch.lion.service.system.CodeTypeService;
import com.newtouch.lion.vo.CodeTypeView;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * <p>
 * Title:
 * </p>
 * <p>
 * Description:
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
@Service("codeTypeService")
public class CodeTypeServiceImpl extends AbstractService implements  CodeTypeService {

	@Autowired
	private CodeTypeDao codeTypeDao;
	
	@Autowired
	private  DataColumnService dataColumnService;
	@Autowired
	private CodeService codeService;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.lion.framework.service.system.CodeTypeService#doCreateCodeType
	 * (com.lion.framework.model.system.CodeType)
	 */
	@Override
	public void doCreateCodeType(CodeType codeType) {
		codeTypeDao.save(codeType);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.lion.framework.service.system.CodeTypeService#doFindAll()
	 */
	@Override
	public List<CodeType> doFindAll() {
		return codeTypeDao.findAll();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.lion.framework.service.system.CodeTypeService#doFindById(java.
	 * lang.Long)
	 */
	@Override
	public CodeType doFindById(Long id) {
		return this.codeTypeDao.findById(id);
	}
	
	

	/* (non-Javadoc)
	 * @see com.lion.framework.service.system.CodeTypeService#doGetById(java.lang.Long)
	 */
	@Override
	public CodeType doGetById(Long id) {
		return this.codeTypeDao.getById(id);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.lion.framework.service.system.CodeTypeService#doDelete(com.lion
	 * .framework.model.system.CodeType)
	 */
	@Override
	public void doDelete(CodeType codeType) {
		this.codeTypeDao.remove(codeType);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.lion.framework.service.system.CodeTypeService#doDeleteById(java
	 * .lang.Long)
	 */
	@Override
	public int doDeleteById(Long id) {
		CodeType codeType = this.doFindById(id);
		if (codeType == null)
			return 0;
		this.doDelete(codeType);
		return 1;

	}

	
	
	/* (non-Javadoc)
	 * @see com.newtouch.lion.service.system.CodeTypeService#doFindVoByCriteria(com.newtouch.lion.query.QueryCriteria)
	 */
	@Override
	public PageResult<CodeTypeView> doFindVoByCriteria(QueryCriteria criteria) {
		PageResult<CodeType> pageResult=this.doFindByCriteria(criteria);
		PageResult<CodeTypeView> result=new PageResult<CodeTypeView>();
		result.setCurrentIndex(pageResult.getCurrentIndex());
		result.setCurrentPage(pageResult.getCurrentPage());
		result.setPageSize(pageResult.getPageSize());
		result.setTotalCount(pageResult.getTotalCount());
		result.setTotalPage(pageResult.getTotalPage());
		
		Map<String,String> codes=codeService.doFindToMap("codeTypes");
		List<CodeTypeView> content=new ArrayList<CodeTypeView>();
		for(CodeType codeType:pageResult.getContent()){
			CodeTypeView  codeTypVo=new CodeTypeView();
			BeanUtils.copyProperties(codeType,codeTypVo);
			codeTypVo.setCodeName(codes.get(codeTypVo.getType()));
			content.add(codeTypVo);
		}
		result.setContent(content);
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.lion.framework.service.system.CodeTypeService#doFindByCriteria
	 * (com.lion.framework.common.QueryCriteria)
	 */
	@Override
	public PageResult<CodeType> doFindByCriteria(QueryCriteria criteria) {
		return codeTypeDao.doFindByCriteria(criteria);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.lion.framework.service.system.CodeTypeService#doUpdate(com.lion
	 * .framework.model.system.CodeType)
	 */
	@Override
	public CodeType doUpdate(CodeType codeType) {
		this.codeTypeDao.save(codeType);
		return codeType;
	}
	
	
	

	/* (non-Javadoc)
	 * @see com.lion.framework.service.system.CodeTypeService#doUpdateByParams(java.lang.Long, java.lang.String, java.lang.String, java.lang.String, java.lang.Integer, java.lang.Boolean)
	 */
	@Override
	public CodeType doUpdateByParams(Long id, String type, String nameEn,
			String nameZh, Integer codeLenLimit, Boolean editable) {
		CodeType codeType=this.codeTypeDao.findById(id);
		codeType.setType(type);
		codeType.setCodeLenLimit(codeLenLimit);
		codeType.setNameEn(nameEn);
		codeType.setNameZh(nameZh);
		codeType.setEditable(editable);
		this.codeTypeDao.save(codeType);
		return codeType;
	}
	
	
	

	/* (non-Javadoc)
	 * @see com.lion.framework.service.system.CodeTypeService#doFindByCriteria(com.lion.framework.common.QueryCriteria, java.lang.String)
	 */
	@Override
	public String doFindByCriteria(QueryCriteria criteria, String tableId) {
		PageResult<CodeType> pageResult=this.doFindByCriteria(criteria);
		Set<String> properties=this.dataColumnService.doFindColumnsByTableId(tableId);
		return JSONParser.toJSONDataGridString(pageResult, properties);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.lion.framework.service.system.CodeTypeService#doLogicalDelete(
	 * java.lang.Long)
	 */
	@Override
	public void doLogicalDelete(Long id) {
		CodeType codeType = this.doFindById(id);
		codeType.setDeleteDate(new Date());
		codeType.setIsDeleted(Boolean.TRUE);
		this.doUpdate(codeType);
	}

	/* (non-Javadoc)
	 * @see com.newtouch.lion.service.system.CodeTypeService#doIsExistByNameEn(java.lang.String)
	 */
	@Override
	public boolean doIsExistByNameEn(String nameEn) {
		Assert.notNull(nameEn);
		CodeType codeType = this.doFindTypeByNameEn(nameEn);
		if (codeType != null)
			return true;
		return false;
	}

	/* (non-Javadoc)
	 * @see com.newtouch.lion.service.system.CodeTypeService#doFindTypeByNameEn(java.lang.String)
	 */
	@Override
	public CodeType doFindTypeByNameEn(String nameEn) {
		Assert.notNull(nameEn);
		codeTypeDao.doFindTypeByNameEn(nameEn);
		return null;
	}

	/* (non-Javadoc)
	 * @see com.newtouch.lion.service.system.CodeTypeService#doCreate(com.newtouch.lion.model.system.Group)
	 */
	@Override
	public void doCreate(CodeType codeType) {
		Assert.notNull(codeType);
		codeTypeDao.save(codeType);
	}
	
}
