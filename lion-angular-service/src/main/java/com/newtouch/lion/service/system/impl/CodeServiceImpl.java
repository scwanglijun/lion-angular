/*
* Copyright (c)  2015, Newtouch
* All rights reserved. 
*
* $id: CodeServiceImpl.java 9552 2015年1月28日 下午5:12:23 WangLijun$
*/
package com.newtouch.lion.service.system.impl;

import com.newtouch.lion.model.system.CodeList;
import com.newtouch.lion.service.system.CodeListService;
import com.newtouch.lion.service.system.CodeService;
import com.newtouch.lion.service.system.CodeTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * Title:通用代码接口实现 
 * </p>
 * <p>
 * Description: 通用代码接口定义 主要面向外部提供通用服务实现 
 * </p>
 * <p>
 * Copyright: Copyright (c) 2015
 * </p>
 * <p>
 * Company: Newtouch
 * </p>
 * 
 * @author WangLijun
 * @version 1.0
 */
@Service
public class CodeServiceImpl implements CodeService {
	/***编码类型服务*/
	@Autowired
	private CodeTypeService codeTypeService;
	/**编码列表服务*/
	@Autowired
	private CodeListService codeListService;

	/* (non-Javadoc)
	 * @see com.newtouch.lion.service.system.CodeService#doFindByTypeName(java.lang.String)
	 */
	@Override
	public Map<Object, Object> doFindMap(String codeTypeNameEn) {
		List<CodeList> codeLists=codeListService.doFindCodeListByCodeTypeNameEn(codeTypeNameEn);
		Map<Object, Object>  codeMap=new HashMap<Object, Object>();
		if(!CollectionUtils.isEmpty(codeLists)){
			for(CodeList codeList:codeLists){
				codeMap.put(codeList.getCodeValue(),codeList);
			}
		}
		
		return codeMap;
	}
	
	
	

	/* (non-Javadoc)
	 * @see com.newtouch.lion.service.system.CodeService#doFindToMap(java.lang.String)
	 */
	@Override
	public Map<String, String> doFindToMap(String codeTypeNameEn) {
		List<CodeList> codeLists=codeListService.doFindCodeListByCodeTypeNameEn(codeTypeNameEn);
		Map<String,String>  params=new HashMap<String,String>();
		if(!CollectionUtils.isEmpty(codeLists)){
			for(CodeList codeList:codeLists){
				params.put(codeList.getCodeValue(),codeList.getNameZh());
			}
		}
		return params;
	}




	/* (non-Javadoc)
	 * @see com.newtouch.lion.service.system.CodeService#doFindByList(java.lang.String)
	 */
	@Override
	public List<CodeList> doFindList(String codeTypeNameEn) {
		List<CodeList> list=codeListService.doFindCodeListByCodeTypeNameEn(codeTypeNameEn);
		if(CollectionUtils.isEmpty(list)){
			list=new ArrayList<CodeList>(0);
		}
		return list;
	}

}

	