/*
 * Copyright (c)  2013, Newtouch
 * All rights reserved. 
 *
 * $id: DemoServiceImpl.java 9552 2013-3-22 上午9:16:23 WangLijun$
 */
package com.newtouch.lion.service.system.impl;

import com.newtouch.lion.dao.system.DemoDao;
import com.newtouch.lion.model.system.DemoModel;
import com.newtouch.lion.service.AbstractService;
import com.newtouch.lion.service.system.DemoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * Title:示例类
 * </p>
 * <p>
 * Description:示例类
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
@Service("demoService")
public class DemoServiceImpl extends AbstractService implements DemoService {

	@Autowired
	private DemoDao demoDao;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.lion.framework.service.system.DemoService#doFindByParentId()
	 */
	@Override
	public List<DemoModel> doFindByParentId() {
		String hql = "from DemoModel where  id=:id";
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("id", 4L);
		List<DemoModel> demoModels = demoDao.query(hql, params);	  
		return demoModels;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.lion.framework.service.system.DemoService#doCreateDemoModel(com
	 * .lion.framework.model.system.DemoModel)
	 */
	@Override
	public void doCreateDemoModel(DemoModel demoModel) {
		this.demoDao.save(demoModel);
	}

}
