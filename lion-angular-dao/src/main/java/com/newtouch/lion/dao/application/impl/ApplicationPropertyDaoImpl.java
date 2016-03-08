
/*
* Copyright (c)  2014, Newtouch
* All rights reserved. 
*
* $id: ApplicationPropertyDao.java 9552 2014年12月23日 下午3:31:14 WangLijun$
*/
package com.newtouch.lion.dao.application.impl; 

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.newtouch.lion.dao.application.ApplicationPropertyDao;
import com.newtouch.lion.dao.impl.BaseDaoImpl;
import com.newtouch.lion.model.application.ApplicationProperty;

/**
 * <p>
 * Title: 
 * </p>
 * <p>
 * Description: 
 * </p>
 * <p>
 * Copyright: Copyright (c) 2014
 * </p>
 * <p>
 * Company: Newtouch
 * </p>
 * 
 * @author WangLijun
 * @version 1.0
 */
@Repository
public class ApplicationPropertyDaoImpl  extends BaseDaoImpl<ApplicationProperty,Long>  implements ApplicationPropertyDao{

	/**
	 * 
	 */
	private static final long serialVersionUID = -718928038791870080L;

	/* (non-Javadoc)
	 * @see com.newtouch.lion.dao.application.ApplicationPropertyDao#doDeleteById(java.lang.Long)
	 */
	@Override
	public int deleteById(Long id) {
		String hql = "delete from ApplicationProperty p where p.id=:id";
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("id", id);
		return this.updateHQL(hql, params);
	}
	
	

}

	