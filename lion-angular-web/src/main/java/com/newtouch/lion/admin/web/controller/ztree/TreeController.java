/*
* Copyright (c)  2015, Newtouch
* All rights reserved. 
*
* $id: TreeController.java 9552 2015年1月29日 下午10:08:08 WangLijun$
*/
package com.newtouch.lion.admin.web.controller.ztree;

import com.newtouch.lion.web.controller.AbstractController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * <p>
 * Title: zTree的Demo
 * </p>
 * <p>
 * Description: zTree的Demo
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
@Controller
@RequestMapping("/ztree/")
public class TreeController extends AbstractController {
	
	private static final String RETURN_INDEX="/ztree/index";
	
	@RequestMapping("/index")
	public String index(){
		return RETURN_INDEX;
	}
}

	