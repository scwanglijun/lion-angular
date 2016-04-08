package com.newtouch.lion.admin.web.controller.datagrid;

import com.newtouch.lion.admin.web.model.datagrid.*;
import com.newtouch.lion.model.datagrid.DataGrid;
import com.newtouch.lion.page.PageResult;
import com.newtouch.lion.query.QueryCriteria;
import com.newtouch.lion.service.datagrid.DataGridService;
import com.newtouch.lion.service.monitor.impl.HibernateMonitorServiceImpl;
import com.newtouch.lion.web.page.Page;
import com.newtouch.lion.web.util.QueryUtils;
import com.newtouch.lion.webtrans.trans.Trans;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;

import javax.validation.Valid;
import java.util.List;

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
    /***
     * lixiaohao
     * @param req
     * @return
     */
    @Trans("system.datagrid.list")
    public Page<DataGrid> list(DataGridGetReq req){

//        QueryCriteria queryCriteria = new QueryCriteria();
//        queryCriteria.setStartIndex(req.getPage().getPageNumber()-1);
//        queryCriteria.setPageSize(req.getPage().getPageSize());
        QueryCriteria queryCriteria = QueryUtils.pageFormat(new QueryCriteria(),req);
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

    /**添加*/
    @Trans("system.datagrid.add")
    public DataGridAddResp add(@Valid @ModelAttribute("dataGrid")DataGridAddReq dataGridVo){

        if(this.isExistByTableId(dataGridVo.getTableId())){
            return new DataGridAddResp(DataGridAddResp.FAIL_DATAGRID_ADD_CODE,DataGridAddResp.FAIL_DATAGRID_ADD_MESSAGE);
        }

        DataGridAddResp resp = new DataGridAddResp();
        DataGrid dataGrid = new DataGrid();
        BeanUtils.copyProperties(dataGridVo,dataGrid);

        dataGridService.doCreate(dataGrid);
        return  new DataGridAddResp(DataGridAddResp.SUCCESS_DATAGRID_ADD_CODE,DataGridAddResp.SUCCESS_DATAGRID_ADD_MESSAGE);
    }

    /*add by lixiaohao*/
    private Boolean isExistByTableId(String tableId) {
        Boolean flag = false;
        if (StringUtils.isNotEmpty(tableId)) {
            flag = dataGridService.doIsExistByTableId(tableId.trim());
        }
        return flag;
    }

    private Boolean isExistByTableId(String tableId, String oldName) {
        Boolean flag = false;
        if (StringUtils.isNotEmpty(tableId) && !tableId.equals(oldName)) {
            flag = dataGridService.doIsExistByTableId(tableId.trim());
        }
        return flag;
    }
    /***
     * add by lixiaohao
     * @param dataGridVo
     * @return
     */
    @Trans("system.datagrid.editor")
    public DataGridEditorResp editor(@Valid @ModelAttribute("dataGrid")DataGridEditorReq dataGridVo){
        DataGridEditorResp resp = new DataGridEditorResp();
        if(dataGridVo.getId()==null||dataGridVo.getTableId()==null||!this.isExistByTableId(dataGridVo.getTableId())){
            return new DataGridEditorResp(DataGridEditorResp.FAIL_DATAGRID_EDITOR_CODE,DataGridEditorResp.FAIL_DATAGRID_EDITOR_MESSAGE);
        }
        DataGrid dataGrid = dataGridService.doGetById(dataGridVo.getId());
        if(dataGrid==null){
            return new DataGridEditorResp(DataGridEditorResp.FAIL_DATAGRID_EDITOR_CODE,DataGridEditorResp.FAIL_DATAGRID_EDITOR_MESSAGE);
        }
        if(this.isExistByTableId(dataGridVo.getTableId(),dataGrid.getTableId())){
            return new DataGridEditorResp(DataGridEditorResp.FAIL_DATAGRID_EDITOR_CODE,DataGridEditorResp.FAIL_DATAGRID_EDITOR_MESSAGE);
        }
        BeanUtils.copyProperties(dataGridVo,dataGrid);
        dataGridService.doUpdate(dataGrid);
        return new DataGridEditorResp(DataGridEditorResp.SUCCESS_DATAGRID_EDITOR_CODE,DataGridEditorResp.SUCCESS_DATAGRID_EDITOR_MESSAGE);
    }


    /***
     * 删除
     * @param dataGridVo
     * @return
     */
    @Trans("system.datagird.delete")
    public DataGridDelResp dedete(DataGridDelReq dataGridVo){
        if(dataGridVo.getId()==null){
            return new DataGridDelResp(DataGridDelResp.FAIL_DATAGRID_DELETE_CODE,DataGridDelResp.FAIL_DATAGRID_DELETE_MESSAGE);
        }
        DataGrid dataGrid = dataGridService.doGetById(dataGridVo.getId());
        if(dataGrid==null){
            return new DataGridDelResp(DataGridDelResp.FAIL_DATAGRID_DELETE_CODE,DataGridDelResp.FAIL_DATAGRID_DELETE_MESSAGE);
        }
        int updateRow = this.dataGridService.doDeleteById(dataGridVo.getId());
        if(updateRow>0){
            return new DataGridDelResp(DataGridDelResp.SUCCESS_DATAGRID_DELETE_CODE,DataGridDelResp.SUCCESS_DATAGRID_DELETE_MESSAGE);
        }
         return new DataGridDelResp(DataGridDelResp.FAIL_DATAGRID_DELETE_CODE,DataGridDelResp.FAIL_DATAGRID_DELETE_MESSAGE);
    }


    /** DataGrid列表 */
   @Trans("system.datagird.combox")
    public List<DataGrid> comobx(DataGridComboxReq req) {
        return this.dataGridService.doFindAll();
    }

}
