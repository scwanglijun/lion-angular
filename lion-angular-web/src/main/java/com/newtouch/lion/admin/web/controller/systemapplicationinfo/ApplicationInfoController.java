package com.newtouch.lion.admin.web.controller.systemapplicationinfo;

import com.alibaba.druid.support.logging.Log;
import com.newtouch.lion.admin.web.model.systemapplication.ApplicationInfoReq;
import com.newtouch.lion.admin.web.model.systemapplication.SystemApplicationResp;
import com.newtouch.lion.model.application.DataBaseInfo;
import com.newtouch.lion.service.application.ApplicationInfoService;
import com.newtouch.lion.webtrans.trans.Trans;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

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
 * Company:
 * </p>
 *
 * @author LiXiaoHao
 * @version 1.0
 */
@Controller
public class ApplicationInfoController {

    @Autowired
    private ApplicationInfoService aplicationInfoService;

    /***
     * 数据库配置信息
     * @param
     * @return
     */
    @Trans("system.applicationinfo")
    public SystemApplicationResp applicationInfo(ApplicationInfoReq req){

        /** 应用基础信息 */
        com.newtouch.lion.model.application.ApplicationInfo applicationInfo = aplicationInfoService
                .getApplicationInfo();
        /** 应用数据库基础信息 */
        DataBaseInfo dataBaseInfo = aplicationInfoService
                .getDataBaseInfo();
        SystemApplicationResp systemApplicationResp = new SystemApplicationResp();
        systemApplicationResp.setApplicationInfo(applicationInfo);
        systemApplicationResp.setDataBaseInfo(dataBaseInfo);
        return systemApplicationResp;
    }
}
