/*
* Copyright (c)  2015, Newtouch
* All rights reserved. 
*
* $id: ComboBoxTag.java 9552 2015年1月12日 上午9:41:37 WangLijun$
*/
package com.newtouch.lion.web.tags.combo;

import com.newtouch.lion.model.system.CodeList;
import com.newtouch.lion.service.system.CodeListService;
import com.newtouch.lion.web.freemarker.DirectiveUtils;
import com.newtouch.lion.web.tags.AbstractTag;
import freemarker.core.Environment;
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModel;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static freemarker.template.ObjectWrapper.DEFAULT_WRAPPER;

/**
 * <p>
 * Title: 下拉选择列表标签定义
 * </p>
 * <p>
 * Description: 下拉选择列表标签定义，用于获取字典数据
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
public class ComboBoxTag extends AbstractTag {
	
	/**日志*/
	private final Logger logger = LoggerFactory.getLogger(super.getClass());
	/**输入参数名称*/
	private static final String CODE_NAME="codeName";
	/**返回参数名称*/
	private static final String CODE_LIST = "codes";
	/**IM字典类*/
	@Autowired
	private CodeListService codeListService;
	
	/* (non-Javadoc)
	 * @see freemarker.template.TemplateDirectiveModel#execute(freemarker.core.Environment, java.util.Map, freemarker.template.TemplateModel[], freemarker.template.TemplateDirectiveBody)
	 */
	@SuppressWarnings({ "rawtypes", "unchecked"})
	@Override
	public void execute(Environment env, Map params, TemplateModel[] loopVars,
			TemplateDirectiveBody body) throws TemplateException, IOException {
	 
		//IM的英文名称
		String codeName = DirectiveUtils.getString(CODE_NAME,params);
		logger.debug("codeName:{}",codeName);
		List<CodeList> codeLists;
		if(StringUtils.isNotEmpty(codeName)){
			codeLists = codeListService.doFindCodeListByCodeTypeNameEn(codeName);
		}else{
			codeLists=new ArrayList<CodeList>();
		}

		Map<String, TemplateModel> paramWrap = new HashMap<String, TemplateModel>(params);

		paramWrap.put(CODE_LIST, DEFAULT_WRAPPER.wrap(codeLists));

		Map<String, TemplateModel> origMap = DirectiveUtils.addParamsToVariable(env, paramWrap);

		body.render(env.getOut());

		DirectiveUtils.removeParamsFromVariable(env, paramWrap, origMap);
		 
	}

}

	