/*
 * Copyright (c)  2014, NewTouch
 * All rights reserved. 
 *
 * $id: CodeListController.java 9552 2014-2-18 下午04:35:02 WangLijun$
 */
package com.newtouch.lion.admin.web.controller.system.code;

import com.newtouch.lion.json.JSONParser;
import com.newtouch.lion.model.system.CodeList;
import com.newtouch.lion.model.system.CodeType;
import com.newtouch.lion.model.system.Icon;
import com.newtouch.lion.service.system.CodeListService;
import com.newtouch.lion.service.system.CodeTypeService;
import com.newtouch.lion.service.system.IconService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * <p>
 * Title:Code控制器
 * </p>
 * <p>
 * Description:Code控制器 下拉列表（Combox）
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
@Controller(value = "sysCodeController")
@RequestMapping("/system/code/")
public class CodeController {

	@Autowired
	private CodeListService codeListService;
	@Autowired
	private CodeTypeService codeTypeService;
	@Autowired
	private IconService  iconService;

	@RequestMapping("combox")
	@ResponseBody
	public List<CodeList> combox(@RequestParam String nameEn) {
		List<CodeList> codeLists = codeListService.doFindCodeListByCodeTypeNameEn(nameEn);
		return codeLists;
	}
	
	@RequestMapping("typecombox")
	@ResponseBody
	public List<CodeType> typecombox() {
		List<CodeType> codeTypes = codeTypeService.doFindAll();
		return codeTypes;
	}

	@RequestMapping("comboxselected")
	@ResponseBody
	public String comboxDefaultSelectedValue(@RequestParam String nameEn,
			@RequestParam(required = false) String selectedValue) {
		List<CodeList> codeLists = codeListService.doFindCodeListByNameEn(
				nameEn, selectedValue);
		Set<String> filterColumn = new HashSet<String>();
		filterColumn.add("id");
		filterColumn.add("nameEn");
		filterColumn.add("nameZh");
		filterColumn.add("codeValue");
		filterColumn.add("sortNo");
		filterColumn.add("selected");
		return JSONParser.toJSONString(codeLists, filterColumn, true);
	}
	
	/***
	 * 图标列表
	 * @param iconType
	 * @param selectedValue
	 * @return
	 */
	@RequestMapping("icon")
	@ResponseBody
	public List<Icon> comboxIcon(@RequestParam String iconType){
		 
		List<Icon> list=iconService.doFindByType(iconType);
		if(CollectionUtils.isEmpty(list)){
			return list=new ArrayList<Icon>();
		}
		return  list;
	}
}
