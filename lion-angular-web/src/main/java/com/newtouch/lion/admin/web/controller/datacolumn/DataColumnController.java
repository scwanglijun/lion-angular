package com.newtouch.lion.admin.web.controller.datacolumn;

import com.newtouch.lion.admin.web.model.datacolumn.DataColumnGetReq;
import com.newtouch.lion.admin.web.model.datacolumn.DataColumnResp;
import com.newtouch.lion.model.datagrid.DataColumn;
import com.newtouch.lion.page.PageResult;
import com.newtouch.lion.query.QueryCriteria;
import com.newtouch.lion.service.datagrid.DataColumnService;
import com.newtouch.lion.web.page.Page;
import com.newtouch.lion.web.page.PageRequest;
import com.newtouch.lion.webtrans.trans.Trans;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * Title:DataColumn控制器
 * </p>
 * <p>
 * Description:DataColumn控制器
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
public class DataColumnController {
    @Autowired
    private DataColumnService dataColumnService;
    /**设置默认排序方式*/
    private static final String DEFAULT_ORDER_FILED_NAME="id";



    @Trans("system.datacolumn.list")
    public Page<DataColumn> list(DataColumnGetReq req){

        QueryCriteria queryCriteria = new QueryCriteria();
        queryCriteria.setStartIndex(req.getPage().getPageNumber()-1);
        queryCriteria.setPageSize(req.getPage().getPageSize());

        /**设置排序字段*/
        queryCriteria.setOrderField(DEFAULT_ORDER_FILED_NAME);
        /**设置排序方向*/
        queryCriteria.setOrderDirection(QueryCriteria.ASC);

        /**查询条件*/
        if(StringUtils.isNotEmpty(req.getName())){
            queryCriteria.addQueryCondition("name","%"+req.getName()+"%");
        }
        PageResult<DataColumn> pageResult = dataColumnService.doFindByCriteria(queryCriteria);
        List<DataColumnResp> columnRespList = new ArrayList<DataColumnResp>();
        DataColumnResp dataColumnResp ;
        for(DataColumn column:pageResult.getContent()){
            System.out.println("---id:"+column.getId()+"name:"+column.getName()+"--dataGridId:"+column.getDataGridId()+"datagirdtitle:"+column.getDataGrid().getTitle());
            dataColumnResp = new DataColumnResp();
            BeanUtils.copyProperties(column,dataColumnResp);
            dataColumnResp.setDataGridTitle(column.getDataGrid().getTitle());
            columnRespList.add(dataColumnResp);
        }

        PageResult<DataColumnResp> pageResultResp = new PageResult<DataColumnResp>();
        pageResultResp.setTotalPage(pageResult.getTotalPage());
        pageResultResp.setPageSize(pageResult.getPageSize());
        pageResultResp.setCurrentIndex(pageResult.getCurrentIndex());
        pageResultResp.setTotalCount(pageResult.getTotalCount());
        pageResultResp.setCurrentPage(pageResult.getCurrentPage());

        pageResultResp.setContent(columnRespList);
        return new Page(pageResultResp);
    }
}
