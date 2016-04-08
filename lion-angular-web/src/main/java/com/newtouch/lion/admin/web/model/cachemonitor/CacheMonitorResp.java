package com.newtouch.lion.admin.web.model.cachemonitor;

import com.newtouch.lion.model.cache.CacheManagerModel;

/**
 * <p>
 * Title:
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Copyright: Copyright (c) 2016
 * </p>
 * <p>
 * Company: NewTouch
 * </p>
 *
 * @author ZhangYake
 * @version 1.0
 */
public class CacheMonitorResp {

    private CacheManagerModel managerModel;

    public CacheManagerModel getManagerModel() {
        return managerModel;
    }

    public void setManagerModel(CacheManagerModel managerModel) {
        this.managerModel = managerModel;
    }
}
