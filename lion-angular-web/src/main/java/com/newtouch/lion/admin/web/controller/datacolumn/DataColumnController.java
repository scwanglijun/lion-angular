package com.newtouch.lion.admin.web.controller.datacolumn;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.newtouch.lion.admin.web.model.datacolumn.*;
import com.newtouch.lion.common.file.FileUtil;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.servlet.ModelAndView;

import com.newtouch.lion.common.date.DateUtil;
import com.newtouch.lion.model.datagrid.DataColumn;
import com.newtouch.lion.model.datagrid.DataGrid;
import com.newtouch.lion.model.system.CodeList;
import com.newtouch.lion.page.PageResult;
import com.newtouch.lion.query.QueryCriteria;
import com.newtouch.lion.service.datagrid.DataColumnService;
import com.newtouch.lion.service.datagrid.DataGridService;
import com.newtouch.lion.service.excel.ExcelExportService;
import com.newtouch.lion.service.system.CodeListService;
import com.newtouch.lion.service.system.CodeTypeService;
import com.newtouch.lion.web.controller.AbstractController;
import com.newtouch.lion.web.page.Page;
import com.newtouch.lion.web.util.QueryUtils;
import com.newtouch.lion.webtrans.trans.Trans;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

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
public class DataColumnController extends AbstractController {
	private final Logger logger = LoggerFactory.getLogger(super.getClass());
    @Autowired
    private DataColumnService dataColumnService;
    /**设置默认排序方式*/
    private static final String DEFAULT_ORDER_FILED_NAME="id";


    @Autowired
    private CodeListService codeListService;
    @Autowired
    private CodeTypeService codeTypeService;

    @Autowired
    private DataGridService dataGridService;
    @Autowired
    private ExcelExportService excelExportService;
    @Trans("system.datacolumn.list")
    public Page<DataColumn> list(DataColumnGetReq req){

//        QueryCriteria queryCriteria = new QueryCriteria();
//        queryCriteria.setStartIndex(req.getPage().getPageNumber()-1);
//        queryCriteria.setPageSize(req.getPage().getPageSize());

        QueryCriteria queryCriteria = QueryUtils.pageFormat(new QueryCriteria(),req);

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

    /**添加*/
    @Trans("system.datacolumn.add")
    public DataColumnAddResp add(@Valid @ModelAttribute("dataColumn")DataColumnAddReq dataColumnVo){
        if(dataColumnVo.getDataGridId()==null || dataGridService.doGetById(dataColumnVo.getDataGridId()) ==null){
            return new DataColumnAddResp(DataColumnAddResp.FAIL_DATACOLUMN_ADD_CODE,DataColumnAddResp.FAIL_DATACOLUMN_ADD_MESSAGE);
        }

        DataColumnAddResp resp = new DataColumnAddResp();
        DataColumn dataColumn = new DataColumn();
        BeanUtils.copyProperties(dataColumnVo,dataColumn);

        dataColumn.setSortable(Boolean.TRUE);
        dataColumnService.doCreate(dataColumn);
        return new DataColumnAddResp(DataColumnAddResp.SUCCESS_DATACOLUMN_ADD_CODE,DataColumnAddResp.SUCCESS_DDATACOLUMN_ADD_MESSAGE);
    }

    /**添加*/
    @Trans("system.datacolumn.editor")
    public DataColumnAddResp editor(@Valid @ModelAttribute("dataColumn")DataColumnAddReq dataColumnVo){
        if(dataColumnVo.getDataGrid()==null || dataGridService.doGetById(dataColumnVo.getDataGridId()) ==null){
            return new DataColumnAddResp(DataColumnAddResp.FAIL_DATACOLUMN_ADD_CODE,DataColumnAddResp.FAIL_DATACOLUMN_ADD_MESSAGE);
        }

        DataColumnAddResp resp = new DataColumnAddResp();
        DataColumn dataColumn = new DataColumn();
        BeanUtils.copyProperties(dataColumnVo,dataColumn);

        dataColumn.setSortable(Boolean.TRUE);
        dataColumnService.doUpdate(dataColumn);
        return new DataColumnAddResp(DataColumnAddResp.SUCCESS_DATACOLUMN_ADD_CODE,DataColumnAddResp.SUCCESS_DDATACOLUMN_ADD_MESSAGE);
    }

    /***
     * 删除
     * @param dataColumnVo
     * @return
     */
    @Trans("system.datacolumn.delete")
    public DataColumnDelResp dedete(DataColumnDelReq dataColumnVo){
        if(dataColumnVo.getId()==null){
            return new DataColumnDelResp(DataColumnDelResp.FAIL_DATACOLUMN_DELETE_CODE,DataColumnDelResp.FAIL_DATACOLUMN_DELETE_MESSAGE);
        }
        DataColumn dataColumn = dataColumnService.doGetById(dataColumnVo.getId());
        if(dataColumn==null){
            return new DataColumnDelResp(DataColumnDelResp.FAIL_DATACOLUMN_DELETE_CODE,DataColumnDelResp.FAIL_DATACOLUMN_DELETE_MESSAGE);
        }
        int updateRow = dataColumnService.doDeleteById(dataColumn.getId());
        if(updateRow>0){
            return new DataColumnDelResp(DataColumnDelResp.SUCCESS_DATACOLUMN_DELETE_CODE,DataColumnDelResp.SUCCESS_DDATACOLUMN_DELETE_MESSAGE);
        }
        return new DataColumnDelResp(DataColumnDelResp.FAIL_DATACOLUMN_DELETE_CODE,DataColumnDelResp.FAIL_DATACOLUMN_DELETE_MESSAGE);
    }

    /***数据字典*/

    @Trans("system.datacolumn.combox")
    public DataColumnComboxResp combox(DataColumnComboxReq req) {
        DataColumnComboxResp resp = new DataColumnComboxResp();
        List<CodeList>  codeLists = codeListService.doFindCodeListByCodeTypeNameEn(req.getNameEn());
        resp.setCodeLists(codeLists);
        return resp;
    }

    @Trans("system.datacolumn.export")
    public ModelAndView exportExcel(DataColumnExportReq req){

    	System.out.println("--------------------------------------");
    	
    	ModelAndView modelAndView = new ModelAndView();
        DataGrid dataGrid=dataGridService.doFindByTableIdAndSort(req.getTableId());
        QueryCriteria queryCriteria=new QueryCriteria();
        queryCriteria.setPageSize(10000);
        // 设置排序字段及排序方向

            queryCriteria.setOrderField(DEFAULT_ORDER_FILED_NAME);
            queryCriteria.setOrderDirection("ASC");

        // 查询条件 参数类型
//        if (codeListVo.getCodeTypeId() != null) {
//            queryCriteria.addQueryCondition("codeTypeId", codeListVo.getCodeTypeId());
//        }
        //查询条件 中文参数名称按模糊查询
//        if(StringUtils.isNotEmpty(codeListVo.getNameZh())){
//            queryCriteria.addQueryCondition("nameZh","%"+codeListVo.getNameZh()+"%");
//        }

        PageResult<CodeList> result=codeListService.doFindByCriteria(queryCriteria);
        Map<String, Map<Object, Object>> fieldCodeTypes = new HashMap<String, Map<Object, Object>>();

        Map<String, String> dataFormats = new HashMap<String, String>();
        dataFormats.put("birthday", DateUtil.FORMAT_DATE_YYYY_MM_DD);
        //创建.xls的文件名
        String fileName=this.createFileName(FileUtil.EXCEL_EXTENSION);

        modelAndView.addObject("title", dataGrid.getTitle());

        Long startTime=System.currentTimeMillis();

        fileName=excelExportService.export(dataGrid, result.getContent(), fileName, fieldCodeTypes, dataFormats);

        logger.info("fileName:{}",fileName);

        Long costTime=System.currentTimeMillis()-startTime;

        modelAndView.addObject(FILENAME,fileName);

        logger.info("export Excel {} cost:{} time,fileName:{}",dataGrid.getTitle(),costTime,fileName);
        logger.info("out Excel导出");
        return this.getExcelView(modelAndView);
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
}
