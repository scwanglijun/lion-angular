package com.newtouch.lion.admin.web.controller.datagrid;

import com.newtouch.lion.admin.web.model.datagrid.DataGridGetReq;
import com.newtouch.lion.model.datagrid.DataGrid;
import com.newtouch.lion.page.PageResult;
import com.newtouch.lion.query.QueryCriteria;
import com.newtouch.lion.service.datagrid.DataGridService;
import com.newtouch.lion.web.page.Page;
import com.newtouch.lion.webtrans.trans.Trans;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/**
 * <p>
 * Title:DataGrid控制器
 * </p>
 * <p>
 * Description:DataGrid控制器
 * </p>
 * <p>
 * Copyright: Copyright (c) 2016
 * </p>
 * <p>
 * Company: NewTouch
 * </p>
 *
 * @author LiXiaoHao
 * @version 1.0
 */
@Controller
public class DataGridController {
    /**默认排序字段*/
    private static final String DEFAULT_ORDER_FILED_NAME = "id";
    @Autowired
    private DataGridService dataGridService;

    @Trans("system.datagrid.list")
    public Page<DataGrid> list(DataGridGetReq req){

        QueryCriteria queryCriteria = new QueryCriteria();
        queryCriteria.setStartIndex(req.getPage().getPageNumber()-1);
        queryCriteria.setPageSize(req.getPage().getPageSize());
        /**设置排序字段*/
        queryCriteria.setOrderField(DEFAULT_ORDER_FILED_NAME);
        /**设置排序方向*/
        queryCriteria.setOrderDirection(QueryCriteria.ASC);

        /**设置查询条件*/
        if(StringUtils.isNotEmpty(req.getTitle())){
            queryCriteria.addQueryCondition("title","%"+req.getTitle()+"%");
        }

        PageResult<DataGrid> pageResult = dataGridService.doFindByCriteria(queryCriteria);
        for(DataGrid grid:pageResult.getContent()){
            System.out.println("--------:"+grid.getTitle());
        }
        return new Page(pageResult);
    }

}
