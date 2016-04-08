package com.newtouch.lion.admin.web.controller.cachemonitor;

import com.newtouch.lion.admin.web.model.cachemonitor.CacheMonitorReq;
import com.newtouch.lion.admin.web.model.cachemonitor.CacheMonitorResp;
import com.newtouch.lion.model.cache.CacheManagerModel;
import com.newtouch.lion.service.cache.ApplicationCacheManager;
import com.newtouch.lion.webtrans.trans.Trans;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

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
 * Company: NewTouch
 * </p>
 *
 *
 * @author ZhangYake
 * @version 1.0
 */
@Controller
public class CacheMonitorController {
    @Autowired
    private ApplicationCacheManager applicationCacheManager;

    @Trans("system.monitor.cache")
    public CacheMonitorResp index(CacheMonitorReq req){
        CacheManagerModel managerModel=applicationCacheManager.getCaches();
        CacheMonitorResp resp = new CacheMonitorResp();
        resp.setManagerModel(managerModel);
        return resp;
    }
}
