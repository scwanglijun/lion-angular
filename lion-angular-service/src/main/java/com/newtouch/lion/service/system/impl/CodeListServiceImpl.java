/*
 * Copyright (c)  2013, Newtouch
 * All rights reserved. 
 *
 * $id: CodeListServiceImpl.java 9552 2013-1-12 下午8:35:09 WangLijun$
 */
package com.newtouch.lion.service.system.impl;

import com.newtouch.lion.common.Assert;
import com.newtouch.lion.dao.system.CodeListDao;
import com.newtouch.lion.model.system.CodeList;
import com.newtouch.lion.page.PageResult;
import com.newtouch.lion.query.QueryCriteria;
import com.newtouch.lion.service.AbstractService;
import com.newtouch.lion.service.system.CodeListService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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
@Service("codeListService")
public class CodeListServiceImpl extends AbstractService implements
		CodeListService {
 

	@Autowired
	private CodeListDao codeListDao;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.lion.framework.service.system.CodeListService#doCreateObj(com.
	 * lion.framework.model.system.CodeList)
	 */
	@Override
	public void doCreateObj(CodeList obj) {
		this.codeListDao.save(obj);
	}
	
	
	
	/* (non-Javadoc)
	 * @see com.lion.framework.service.system.CodeListService#doFindById(java.lang.Long)
	 */
	@Override
	public CodeList doFindById(Long id) {
		return this.codeListDao.findById(id);
	}



	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.lion.framework.service.system.CodeListService#doDeleteById(java
	 * .lang.Long)
	 */
	@Override
	public int doDeleteById(Long id) {
		return this.codeListDao.doDeleteById(id);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.lion.framework.service.system.CodeListService#doDeleteByObj(com
	 * .lion.framework.model.system.CodeList)
	 */
	@Override
	public void doDeleteByObj(CodeList obj) {
		this.codeListDao.remove(obj);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.lion.framework.service.system.CodeListService#doUpdateByParams
	 * (java.lang.Long, java.lang.Long, java.lang.String, java.lang.String, int,
	 * java.lang.Boolean)
	 */
	@Override
	public void doUpdateByParams(Long id, Long codeTypeId,String codeValue,String nameEn,
			String nameZh, int sortNo, Boolean editable) {
		//TODO
	}
	
	
	

	/* (non-Javadoc)
	 * @see com.lion.framework.service.system.CodeListService#doUpdateObj(com.lion.framework.model.system.CodeList)
	 */
	@Override
	public CodeList doUpdateObj(CodeList obj) {
		CodeList codeList=this.codeListDao.getById(obj.getId());
		codeList.setCodeType(obj.getCodeType());
		codeList.setCodeValue(obj.getCodeValue());
		codeList.setNameEn(obj.getNameEn());
		codeList.setNameZh(obj.getNameZh());
		codeList.setSortNo(obj.getSortNo());
		codeList.setEditable(obj.getEditable());
		codeList.setSelected(obj.getSelected());
		this.codeListDao.save(codeList);
		return codeList;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.lion.framework.service.system.CodeListService#doFindByCriteria
	 * (com.lion.framework.common.QueryCriteria)
	 */
	@Override
	public PageResult<CodeList> doFindByCriteria(QueryCriteria criteria) {
		
		return codeListDao.doFindByCriteria(criteria);
	}

	/* (non-Javadoc)
	 * @see com.lion.framework.service.system.CodeListService#doFindCodeListByCodeTypeNameEn(java.lang.String)
	 */
	@Override
	public List<CodeList> doFindCodeListByCodeTypeNameEn(String codeTypeNameEn) {
		Assert.notNull(codeTypeNameEn);
		return codeListDao.doFindCodeListByCodeTypeNameEn(codeTypeNameEn);
	}

	/* (non-Javadoc)
	 * @see com.lion.framework.service.system.CodeListService#doFindCodeListByNameEn(java.lang.String, java.lang.String)
	 */
	@Override
	public List<CodeList> doFindCodeListByNameEn(String codeTypeNameEn,
			String selectedValue) {
		List<CodeList> codeLists=this.doFindCodeListByCodeTypeNameEn(codeTypeNameEn);
		List<CodeList> returnCodeLists=null;
		if(StringUtils.isNotEmpty(selectedValue)){
			returnCodeLists=new ArrayList<CodeList>();
			for(CodeList codeList:codeLists){
				if(codeList.getNameEn().equals(selectedValue)){
					codeList.setSelected(Boolean.TRUE);
				}else{
					codeList.setSelected(Boolean.FALSE);
				}
				returnCodeLists.add(codeList);
			}
		}else{
			returnCodeLists=codeLists;
		}
		return returnCodeLists;
	}
	
	public CodeList doFindCodeListByNameEn(String nameEn) {
		Assert.notNull(nameEn);
		return codeListDao.doFindCodeListByNameEn(nameEn);
	}


	@Override
	public boolean doIsExistByNameEn(String nameEn) {
		Assert.notNull(nameEn);
		CodeList  codelist=this.doFindCodeListByNameEn(nameEn);
		if(codelist!=null)
			return true;
		return false;
	}



	/* (non-Javadoc)
	 * @see com.newtouch.lion.service.system.CodeListService#doCreate(com.newtouch.lion.model.system.CodeList)
	 */
	@Override
	public void doCreate(CodeList codeList) {
		Assert.notNull(codeList);
		codeListDao.save(codeList);
	}
	
	
}
