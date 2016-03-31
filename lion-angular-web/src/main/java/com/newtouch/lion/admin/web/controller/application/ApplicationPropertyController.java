package com.newtouch.lion.admin.web.controller.application;
/**
 * Created by Zhangyake on 2016/3/28.
 */

import com.newtouch.lion.admin.web.model.application.ApplicationPropertiesGetReq;
import com.newtouch.lion.admin.web.model.application.ApplicationPropertiesGetResp;
import com.newtouch.lion.admin.web.model.application.ApplicationPropertyAddReq;
import com.newtouch.lion.admin.web.model.application.ApplicationPropertyAddResp;
import com.newtouch.lion.common.date.DateUtil;
import com.newtouch.lion.model.application.ApplicationProperty;
import com.newtouch.lion.page.PageResult;
import com.newtouch.lion.query.QueryCriteria;
import com.newtouch.lion.service.application.ApplicationPropertyService;
import com.newtouch.lion.web.page.Page;
import com.newtouch.lion.web.util.QueryUtils;
import com.newtouch.lion.webtrans.trans.Trans;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.ArrayList;
import java.util.List;
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
@Controller
public class ApplicationPropertyController {
    @Autowired
    private ApplicationPropertyService applicationPropertyService;
    /** 默认排序字段 */
    private static final String DEFAULT_ORDER_FILED_NAME = "id";

    @Trans("system.applicationProperty.list")
    public Page<ApplicationPropertiesGetResp> list(ApplicationPropertiesGetReq req) {

        QueryCriteria queryCriteria = QueryUtils.pageFormat(new QueryCriteria() ,req);

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
        //查询条件 value
        if(StringUtils.isNotEmpty(req.getValue())){
            queryCriteria.addQueryCondition("value","%"+req.getValue()+"%");
        }

        PageResult<ApplicationProperty> pageResult = applicationPropertyService.doFindByCriteria(queryCriteria);

        List<ApplicationPropertiesGetResp> list = new ArrayList<ApplicationPropertiesGetResp>();
        for (ApplicationProperty applicationProperty : pageResult.getContent()) {
            ApplicationPropertiesGetResp applicationPropertiesGetResp = new ApplicationPropertiesGetResp();
            BeanUtils.copyProperties(applicationProperty,applicationPropertiesGetResp);
            String createDate  = DateUtil.formatDate(applicationProperty.getCreatedDate(),DateUtil.FORMAT_DATETIME_YYYY_MM_DD_HH_MM_SS);
            String updateDate    = DateUtil.formatDate(applicationProperty.getUpdatedDate(),DateUtil.FORMAT_DATETIME_YYYY_MM_DD_HH_MM_SS);
            applicationPropertiesGetResp.setCreateDateFormatter(createDate);
            applicationPropertiesGetResp.setUpdateDateFormatter(updateDate);
            list.add(applicationPropertiesGetResp);
        }
        PageResult<ApplicationPropertiesGetResp> pageResultResp = new PageResult<ApplicationPropertiesGetResp>();
        pageResultResp.setTotalPage(pageResult.getTotalPage());
        pageResultResp.setPageSize(pageResult.getPageSize());
        pageResultResp.setCurrentIndex(pageResult.getCurrentIndex());
        pageResultResp.setTotalCount(pageResult.getTotalCount());
        pageResultResp.setCurrentPage(pageResult.getCurrentPage());

        pageResultResp.setContent(list);

        return new Page(pageResultResp);
    }

    @Trans("system.applicationProperty.add")
    public ApplicationPropertyAddResp add(ApplicationPropertyAddReq  req){

        ApplicationProperty applicationProperty = new ApplicationProperty();

        BeanUtils.copyProperties(req, applicationProperty);
        applicationPropertyService.doCreate(applicationProperty);

        return new ApplicationPropertyAddResp(ApplicationPropertyAddResp.SUCCESS_ROLE_ADD_CODE,ApplicationPropertyAddResp.SUCCESS_ROLE_ADD_MESSAGE);
    }
}
