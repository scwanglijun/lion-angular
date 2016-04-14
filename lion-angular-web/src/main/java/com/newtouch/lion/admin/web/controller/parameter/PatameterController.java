package com.newtouch.lion.admin.web.controller.parameter;

import com.newtouch.lion.admin.web.model.parameter.*;
import com.newtouch.lion.model.system.Parameter;
import com.newtouch.lion.page.PageResult;
import com.newtouch.lion.query.QueryCriteria;
import com.newtouch.lion.service.system.ParameterService;
import com.newtouch.lion.web.page.Page;
import com.newtouch.lion.web.util.QueryUtils;
import com.newtouch.lion.webtrans.trans.Trans;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

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
public class PatameterController {

    /**默认排序字段*/
    private static final String DEFAULT_ORDER_FILED_NAME = "id";
    @Autowired
    private ParameterService parameterService;

    @Trans("system.parameter.list")
    public Page<Parameter> list(ParameterGetReq req){
        QueryCriteria queryCriteria = QueryUtils.pageFormat(new QueryCriteria(),req);
        queryCriteria.setOrderField(DEFAULT_ORDER_FILED_NAME);
        queryCriteria.setOrderDirection(QueryCriteria.ASC);

        if(StringUtils.isNotEmpty(req.getNameZh())){
            queryCriteria.addQueryCondition("nameZh","%"+req.getNameZh()+"%");
        }

        // 查询条件 参数类型
        if (StringUtils.isNotEmpty(req.getType())) {
            queryCriteria.addQueryCondition("type", req.getType());
        }
       PageResult<Parameter> pageResult = parameterService.doFindByCriteria(queryCriteria);

        return new Page<Parameter>(pageResult);
    }

    /***
     * 添加
     * @param req
     * @return
     */
    @Trans("system.parameter.add")
    public ParameterResp add(ParameterAddReq req){
        if(req.getNameEn()==null || this.isExistByNameEn(req.getNameEn())){
            return new ParameterResp(ParameterResp.FAIL_PARAMETER_CODE,ParameterResp.FAIL_PARAMETER_MESSAGE);
        }
        Parameter parameter = new Parameter();
        BeanUtils.copyProperties(req,parameter);
        parameterService.doCreate(parameter);
        return new ParameterResp(ParameterResp.SUCCESS_PARAMETER_CODE,ParameterResp.SUCCESS_PARAMETER_MESSAGE);
    }

    /***
     * 编辑
     * @param req
     * @return
     */
    @Trans("system.parameter.editor")
    public ParameterResp editor(ParameterEditorReq req){
        if(req.getId()==null){
            return new ParameterResp(ParameterResp.FAIL_PARAMETER_CODE,ParameterResp.FAIL_PARAMETER_MESSAGE);
        }
        Parameter parameter =  parameterService.doFindById(req.getId());
        if(parameter==null){
            return new ParameterResp(ParameterResp.FAIL_PARAMETER_CODE,ParameterResp.FAIL_PARAMETER_MESSAGE);
        }
        if(this.isExistByNameEn(req.getNameEn(),parameter.getNameEn())){
            return new ParameterResp(ParameterResp.FAIL_PARAMETER_CODE,ParameterResp.FAIL_PARAMETER_MESSAGE);
        }
        BeanUtils.copyProperties(req,parameter);
        parameterService.doUpdate(parameter);
        return new ParameterResp(ParameterResp.SUCCESS_PARAMETER_CODE,ParameterResp.SUCCESS_PARAMETER_MESSAGE);
    }


    private Boolean isExistByNameEn(String nameEn) {
        Boolean flag = false;
        if (StringUtils.isNotEmpty(nameEn)) {
            flag = parameterService.doIsExistByNameEn(nameEn.trim());
        }
        return flag;
    }

    private Boolean isExistByNameEn(String nameEn, String oldNameEn) {
        Boolean flag = false;
        if (StringUtils.isNotEmpty(nameEn) && !nameEn.equals(oldNameEn)) {
            flag = parameterService.doIsExistByNameEn(nameEn.trim());
        }
        return flag;
    }


    /***
     * 删除
     * @param req
     * @return
     */
    @Trans("system.parameter.delete")
    public ParameterResp delete(ParameterDelReq req){
        if(req.getId()==null){
            return new ParameterResp(ParameterResp.FAIL_PARAMETER_CODE,ParameterResp.FAIL_PARAMETER_MESSAGE);
        }
        int updateRow = parameterService.doDeleteById(req.getId());
        if(updateRow>0){
            return new ParameterResp(ParameterResp.SUCCESS_PARAMETER_CODE,ParameterResp.SUCCESS_PARAMETER_MESSAGE);
        }
        return new ParameterResp(ParameterResp.FAIL_PARAMETER_CODE,ParameterResp.FAIL_PARAMETER_MESSAGE);
    }


}
