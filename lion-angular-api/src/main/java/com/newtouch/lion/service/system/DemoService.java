/*
 * Copyright (c)  2013, Newtouch
 * All rights reserved. 
 *
 * $id: DemoService.java 9552 2013-3-22 上午9:14:14 WangLijun$
 */
package com.newtouch.lion.service.system;

import java.util.List;

import com.newtouch.lion.model.system.DemoModel;

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
public interface DemoService {
	/***
	 */
	public List<DemoModel> doFindByParentId();

	public void doCreateDemoModel(DemoModel demoModel);
	
}
