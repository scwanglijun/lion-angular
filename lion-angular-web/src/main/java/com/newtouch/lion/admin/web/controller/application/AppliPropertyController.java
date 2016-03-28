package com.newtouch.lion.admin.web.controller.application;
/**
 * Created by Zhangyake on 2016/3/28.
 */

/**
 * <p>
 * Title:项目属性控制器
 * </p>
 * <p>
 * Description:项目属性控制器
 * </p>
 * <p>
 * Copyright: Copyright (c) 2016
 * </p>
 * <p>
 * Company: XiQing
 * </p>
 *
 * @author Zhangyake
 * @version 1.0
 */

import com.newtouch.lion.admin.web.model.application.ApplicationPropertiesGetReq;
import com.newtouch.lion.model.application.ApplicationProperty;
import com.newtouch.lion.page.PageResult;
import com.newtouch.lion.query.QueryCriteria;
import com.newtouch.lion.service.application.ApplicationPropertyService;
import com.newtouch.lion.web.page.Page;
import com.newtouch.lion.webtrans.trans.Trans;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.ArrayList;
import java.util.List;

@Controller
public class AppliPropertyController {
    @Autowired
    private ApplicationPropertyService applicationPropertyService;
    /** 默认排序字段 */
    private static final String DEFAULT_ORDER_FILED_NAME = "id";

    @Trans("system.applicationProperty.list")
    public Page<ApplicationProperty> list(ApplicationPropertiesGetReq req) {
        QueryCriteria queryCriteria = new QueryCriteria();

        // 设置分页 启始页
        queryCriteria.setStartIndex(req.getPage().getPageNumber()-1);
        // 每页大小
        queryCriteria.setPageSize(req.getPage().getPageSize());

        // 设置排序字段及排序方向
//        if (StringUtils.isNotEmpty(req.getSort()) && StringUtils.isNotEmpty(req.getOrder())) {
//            queryCriteria.setOrderField(req.getSort());
//            queryCriteria.setOrderDirection(req.getOrder());
//        } else {
//            queryCriteria.setOrderField(DEFAULT_ORDER_FILED_NAME);
//            queryCriteria.setOrderDirection(QueryCriteria.ASC);
//        }

        // 查询条件 appId
        if (StringUtils.isNotEmpty(req.getAppId())) {
            queryCriteria.addQueryCondition("appId", "%"+req.getAppId()+"%");
        }

        PageResult<ApplicationProperty> pageResult = applicationPropertyService.doFindByCriteria(queryCriteria);
        System.out.print(pageResult.getContent());
        return new Page(pageResult);
    }
}
