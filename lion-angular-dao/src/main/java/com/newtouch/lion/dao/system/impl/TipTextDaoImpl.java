/*
 * Copyright (c)  2012, Newtouch
 * All rights reserved. 
 *
 * $id: TipTextDaoImpl.java 9552 2012-12-31 下午5:23:05 WangLijun$
 */
package com.newtouch.lion.dao.system.impl;

import org.springframework.stereotype.Repository;

import com.newtouch.lion.dao.impl.BaseDaoImpl;
import com.newtouch.lion.dao.system.TipTextDao;
import com.newtouch.lion.model.system.TipText;

/**
 * <p>
 * Title:
 * </p>
 * <p>
 * Description:
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
@Repository("tipTextDao")
public class TipTextDaoImpl extends BaseDaoImpl<TipText,Long> implements TipTextDao {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3077101476638066450L;

}
