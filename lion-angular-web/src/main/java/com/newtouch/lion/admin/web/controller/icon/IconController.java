package com.newtouch.lion.admin.web.controller.icon;

import com.newtouch.lion.admin.web.model.icon.*;
import com.newtouch.lion.common.date.DateUtil;
import com.newtouch.lion.model.system.Icon;
import com.newtouch.lion.page.PageResult;
import com.newtouch.lion.query.QueryCriteria;
import com.newtouch.lion.service.datagrid.DataGridService;
import com.newtouch.lion.service.excel.ExcelExportService;
import com.newtouch.lion.service.system.IconService;
import com.newtouch.lion.web.page.Page;
import com.newtouch.lion.web.util.QueryUtils;
import com.newtouch.lion.webtrans.trans.Trans;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
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
 * Copyright: Copyright (c) 2016
 * </p>
 * <p>
 * Company: NewTouch
 * </p>
 *
 * @author ZhangYake
 * @version 1.0
 */
@Controller
public class IconController {
    /** 默认排序字段名称 */
    private static final String DEFAULT_ORDER_FILED_NAME = "id";
    @Autowired
    private IconService iconService;

    @Trans("system.icon.list")
    public Page<IconGetResp> list(IconGetReq req) {
        QueryCriteria queryCriteria = QueryUtils.pageFormat(new QueryCriteria() ,req);
        // 设置排序字段及排序方向
        queryCriteria.setOrderField(DEFAULT_ORDER_FILED_NAME);
        queryCriteria.setOrderDirection(QueryCriteria.ASC);

        //查询条件 图标类名按模糊查询
        if(StringUtils.isNotEmpty(req.getIconClass())){
            queryCriteria.addQueryCondition("iconClass","%"+req.getIconClass()+"%");
        }

        //查询条件 图标类型
        if(StringUtils.isNotEmpty(req.getIconType())){
            queryCriteria.addQueryCondition("iconType",req.getIconType());
        }

        PageResult<Icon> pageResult = iconService.doFindByCriteria(queryCriteria);
        List<IconGetResp> list = new ArrayList<IconGetResp>();
        for (Icon icon : pageResult.getContent()) {
            IconGetResp iconGetResp = new IconGetResp();
            BeanUtils.copyProperties(icon, iconGetResp);
            String createData = DateUtil.formatDate(icon.getCreatedDate(), DateUtil.FORMAT_DATETIME_YYYY_MM_DD_HH_MM_SS);
            String updateData = DateUtil.formatDate(icon.getUpdatedDate(), DateUtil.FORMAT_DATETIME_YYYY_MM_DD_HH_MM_SS);
            iconGetResp.setCreatedDataFormatter(createData);
            iconGetResp.setUpdatedDataFormatter(updateData);
            list.add(iconGetResp);
        }
        System.out.println(pageResult.getContent());
        PageResult<IconGetResp> pageResultResp = new PageResult<IconGetResp>();
        BeanUtils.copyProperties(pageResult,pageResultResp);
        pageResultResp.setContent(list);
        return new Page(pageResultResp);
    }
    /** 图标数据添加保存 */
    @Trans("system.icon.add")
    public IconAddResp add(IconAddReq req) {
        if (this.isExistByIconClass(req.getIconClass())) {
            return new IconAddResp(IconAddResp.FAIL_ICON_ADD_CODE,IconAddResp.FAIL_ICON_ADD_MESSAGE);
        }
        Icon icon = new Icon();
        BeanUtils.copyProperties(req, icon);
        iconService.doCreate(icon);
        return new IconAddResp(IconAddResp.SUCCESS_ICON_ADD_CODE,IconAddResp.SUCCESS_ICON_ADD_MESSAGE);
    }

    /** 编辑图标 */
    @Trans("system.icon.edit")
    public IconEditResp edit(IconEditReq req) {
        if (req.getId() == null) {
            return new IconEditResp(IconEditResp.FAIL_ICON_EDIT_CODE,IconEditResp.FAIL_ICON_EDIT_MESSAGE);
        }
        Icon icon = iconService.doFindById(req.getId());
        if (icon == null) {
            return new IconEditResp(IconEditResp.FAIL_ICON_EDIT_CODE,IconEditResp.FAIL_ICON_EDIT_MESSAGE);
        }

        if (this.isExistByIconClass(req.getIconClass(),icon.getIconClass())) {
            return new IconEditResp(IconEditResp.FAIL_ICON_EDIT_CODE,IconEditResp.FAIL_ICON_EDIT_MESSAGE);
        }
        BeanUtils.copyProperties(req, icon);
        iconService.doUpdate(icon);
        return new IconEditResp(IconEditResp.SUCCESS_ICON_EDIT_CODE,IconEditResp.SUCCESS_ICON_EDIT_MESSAGE);
    }

    private Boolean isExistByIconClass(String iconClass) {
        Boolean flag = false;
        if (StringUtils.isNotEmpty(iconClass)) {
            flag = iconService.doIsExistByIconClass(iconClass.trim());
        }
        return flag;
    }
    private Boolean isExistByIconClass(String iconClass, String oldClass) {
        Boolean flag = false;
        if (StringUtils.isNotEmpty(iconClass) && !iconClass.equals(oldClass)) {
            flag = iconService.doIsExistByIconClass(iconClass.trim());
        }
        return flag;
    }

    /** 删除图标 */
    @Trans("system.icon.delete")
    public IconDelResp delete(IconDelReq req) {
        int updateRow = this.iconService.doDeleteById(req.getId());
        if (updateRow > 0) {
            return new IconDelResp(IconDelResp.SUCCESS_ICON_DELETE_CODE,IconDelResp.SUCCESS_ICON_DELETE_MESSAGE);
        }
        return new IconDelResp(IconDelResp.FAIL_ICON_DELETE_CODE,IconDelResp.FAIL_ICON_DELETE_MESSAGE);
    }

}
