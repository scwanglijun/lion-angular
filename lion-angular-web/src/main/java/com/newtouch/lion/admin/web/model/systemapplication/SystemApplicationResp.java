package com.newtouch.lion.admin.web.model.systemapplication;

import com.newtouch.lion.model.application.ApplicationInfo;
import com.newtouch.lion.model.application.DataBaseInfo;

/**
 * <p>
 * Title:systemApplicationResp
 * </p>
 * <p>
 * Description:系统配置信息
 * </p>
 * <p>
 * Copyright: Copyright (c) 2016
 * </p>
 * <p>
 * Company:NewTouch
 * </p>
 *
 * @author LiXiaoHao
 * @version 1.0
 */
public class SystemApplicationResp {
    /**系统应用*/
    private ApplicationInfo applicationInfo;
    /**数据库信息*/
    private DataBaseInfo dataBaseInfo;

    public SystemApplicationResp() {
    }

    public ApplicationInfo getApplicationInfo() {
        return applicationInfo;
    }

    public void setApplicationInfo(ApplicationInfo applicationInfo) {
        this.applicationInfo = applicationInfo;
    }

    public DataBaseInfo getDataBaseInfo() {
        return dataBaseInfo;
    }

    public void setDataBaseInfo(DataBaseInfo dataBaseInfo) {
        this.dataBaseInfo = dataBaseInfo;
    }
}
