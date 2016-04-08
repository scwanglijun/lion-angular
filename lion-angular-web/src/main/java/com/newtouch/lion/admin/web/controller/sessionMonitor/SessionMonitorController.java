package com.newtouch.lion.admin.web.controller.sessionMonitor;

import com.newtouch.lion.admin.web.model.query.QueryReq;
import com.newtouch.lion.model.application.ApplicationProperty;
import com.newtouch.lion.model.session.SessionModel;
import com.newtouch.lion.page.PageResult;
import com.newtouch.lion.query.QueryCriteria;
import com.newtouch.lion.service.session.SessionService;
import com.newtouch.lion.web.page.Page;
import com.newtouch.lion.web.util.QueryUtils;
import com.newtouch.lion.webtrans.trans.Trans;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.ArrayList;
import java.util.List;

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
 * @author ZhangYake
 * @version 1.0
 */
@Controller
public class SessionMonitorController {

    @Autowired
    private SessionService sessionService;
    /** 默认排序字段 */
    private static final String DEFAULT_ORDER_FILED_NAME = "id";

    @Trans("system.session.list")
    public Page<SessionModel> list(QueryReq query){
        List<SessionModel> list = sessionService.getActiveSessions();
        return new Page(list);
    }
}
