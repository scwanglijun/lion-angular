/*
 * Copyright (c)  2013, Newtouch
 * All rights reserved. 
 *
 * $id: DemoDaoImpl.java 9552 2013-3-22 上午9:12:53 WangLijun$
 */
package com.newtouch.lion.dao.system.impl;

import org.springframework.stereotype.Repository;

import com.newtouch.lion.dao.impl.BaseDaoImpl;
import com.newtouch.lion.dao.system.DemoDao;
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
 * Company: lion
 * </p>
 * 
 * @author WangLijun
 * @version 1.0
 */
@Repository("demoDao")
public class DemoDaoImpl extends BaseDaoImpl<DemoModel,Long> implements DemoDao {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5079588447729915516L;

}
