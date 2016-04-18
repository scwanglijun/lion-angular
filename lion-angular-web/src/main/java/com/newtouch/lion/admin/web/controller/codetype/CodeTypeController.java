package com.newtouch.lion.admin.web.controller.codetype;

import com.newtouch.lion.admin.web.model.codetype.*;
import com.newtouch.lion.common.date.DateUtil;
import com.newtouch.lion.model.system.CodeType;
import com.newtouch.lion.page.PageResult;
import com.newtouch.lion.query.QueryCriteria;
import com.newtouch.lion.service.system.CodeTypeService;
import com.newtouch.lion.vo.CodeTypeView;
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
public class CodeTypeController {

    /**默认排序字段*/
    private static final String DEFAULT_ORDER_FILED_NAME = "id";

    @Autowired
    private CodeTypeService codeTypeService;

    @Trans("system.codetype.list")
    public Page<CodeTypeGetResp> list(CodeTypeGetReq req){
        QueryCriteria queryCriteria = QueryUtils.pageFormat(new QueryCriteria(),req);
        // 设置排序字段及排序方向
        queryCriteria.setOrderField(DEFAULT_ORDER_FILED_NAME);
        queryCriteria.setOrderDirection(QueryCriteria.ASC);

        // 设置查询条件
        if (StringUtils.isNotEmpty(req.getType())) {
            queryCriteria.addQueryCondition("type", req.getType());
        }

        //查询条件 中文参数名称按模糊查询
        if(StringUtils.isNotEmpty(req.getNameZh())){
            queryCriteria.addQueryCondition("nameZh","%"+req.getNameZh()+"%");
        }

        PageResult<CodeTypeView> pageResult = codeTypeService.doFindVoByCriteria(queryCriteria);
        List<CodeTypeGetResp> list = new ArrayList<>();
        for (CodeTypeView codeType : pageResult.getContent()) {
            CodeTypeGetResp resp = new CodeTypeGetResp();
            BeanUtils.copyProperties(codeType,resp);
            String createDate  = DateUtil.formatDate(codeType.getCreatedDate(),DateUtil.FORMAT_DATETIME_YYYY_MM_DD_HH_MM_SS);
            String updateDate    = DateUtil.formatDate(codeType.getUpdatedDate(),DateUtil.FORMAT_DATETIME_YYYY_MM_DD_HH_MM_SS);
            resp.setCreateDateFormatter(createDate);
            resp.setUpdateDateFormatter(updateDate);
            list.add(resp);
        }
        PageResult<CodeTypeGetResp> pageResultResp = new PageResult<>();
        BeanUtils.copyProperties(pageResult,pageResultResp);
        pageResultResp.setContent(list);

        return new Page(pageResultResp);
    }

    @Trans("system.codetype.add")
    public CodeTypeAddResp add(CodeTypeAddReq req) {
        if(req.getNameEn() == null || this.isExistByNameEn(req.getNameEn())) {
            return new CodeTypeAddResp(CodeTypeAddResp.FAIL_CODETYPE_ADD_CODE,CodeTypeAddResp.FAIL_CODETYPE_ADD_MESSAGE);
        }
        CodeType codeType = new CodeType();
        BeanUtils.copyProperties(req, codeType);
        codeTypeService.doCreate(codeType);
        return new CodeTypeAddResp(CodeTypeAddResp.SUCCESS_CODETYPE_ADD_CODE,CodeTypeAddResp.SUCCESS_CODETYPE_ADD_MESSAGE);
    }
    private Boolean isExistByNameEn(String nameEn) {
        Boolean flag = false;
        if (StringUtils.isNotEmpty(nameEn)) {
            flag = codeTypeService.doIsExistByNameEn(nameEn.trim());
        }
        return flag;
    }

    /** 编辑 */
    @Trans("system.codetype.edit")
    public CodeTypeEditResp edit(CodeTypeEditReq req) {
        if(req.getId()==null){
            return new CodeTypeEditResp(CodeTypeEditResp.FAIL_CODETYPE_EDIT_CODE,CodeTypeEditResp.FAIL_CODETYPE_EDIT_MESSAGE);
        }
        CodeType codeType = codeTypeService.doFindById(req.getId());
        if (codeType == null) {
            return new CodeTypeEditResp(CodeTypeEditResp.FAIL_CODETYPE_EDIT_CODE,CodeTypeEditResp.FAIL_CODETYPE_EDIT_MESSAGE);
        }

        BeanUtils.copyProperties(req, codeType);
        codeTypeService.doUpdate(codeType);

        return new CodeTypeEditResp(CodeTypeEditResp.SUCCESS_CODETYPE_EDIT_CODE,CodeTypeEditResp.SUCCESS_CODETYPE_EDIT_MESSAGE);
    }

    @Trans("system.codetype.delete")
    public CodeTypeDelResp delete(CodeTypeDelReq req) {
        if(req.getId()==null){
            return new CodeTypeDelResp(CodeTypeDelResp.FAIL_CODETYPE_DELETE_CODE,CodeTypeDelResp.FAIL_CODETYPE_DELETE_MESSAGE);
        }
        int updateRow = this.codeTypeService.doDeleteById(req.getId());
        if (updateRow > 0) {
            return new CodeTypeDelResp(CodeTypeDelResp.SUCCESS_CODETYPE_DELETE_CODE,CodeTypeDelResp.SUCCESS_CODETYPE_DELETE_MESSAGE);
        }
        return new CodeTypeDelResp(CodeTypeDelResp.FAIL_CODETYPE_DELETE_CODE,CodeTypeDelResp.FAIL_CODETYPE_DELETE_MESSAGE);
    }

}
