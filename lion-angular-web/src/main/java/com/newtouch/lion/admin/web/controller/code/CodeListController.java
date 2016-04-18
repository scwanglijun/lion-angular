package com.newtouch.lion.admin.web.controller.code;

import com.newtouch.lion.admin.web.model.code.*;
import com.newtouch.lion.model.system.CodeList;
import com.newtouch.lion.page.PageResult;
import com.newtouch.lion.query.QueryCriteria;
import com.newtouch.lion.service.system.CodeListService;
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
 * Created by Administrator on 2016/4/15.
 */
@Controller
public class CodeListController {

    //默认排序字段
    private static final String DEFAULT_ORDER_FILED_NAME = "id";
    @Autowired
    private CodeListService codeListService;

    //编码列表
    @Trans("system.codelist.list")
    public Page<CodeList> list(CodeListGetReq req){
        //排序
        QueryCriteria queryCriteria = QueryUtils.pageFormat(new QueryCriteria(),req);
        queryCriteria.setOrderField(DEFAULT_ORDER_FILED_NAME);
        queryCriteria.setOrderDirection(QueryCriteria.ASC);

        //查询条件 中文参数名称按模糊查询
        if(StringUtils.isNotEmpty(req.getNameZh())){
            queryCriteria.addQueryCondition("nameZh","%"+req.getNameZh()+"%");
        }

        // 设置查询条件
        if (StringUtils.isNotEmpty(req.getCodeType())) {
            queryCriteria.addQueryCondition("type", req.getCodeType());
        }

        PageResult<CodeList> pageResult = codeListService.doFindByCriteria(queryCriteria);
        List<CodeListGetResp> list = new ArrayList<CodeListGetResp>();

        for (CodeList codeList : pageResult.getContent()){
            CodeListGetResp codeListGetResp = new CodeListGetResp();
            BeanUtils.copyProperties(codeList,codeListGetResp);
            codeListGetResp.setCodeType(codeList.getCodeType().getNameZh());

            list.add(codeListGetResp);
        }
        PageResult<CodeListGetResp> pageResult1 = new PageResult<CodeListGetResp>();
        BeanUtils.copyProperties(pageResult,pageResult1);
        pageResult1.setContent(list);

        return new Page(pageResult1);
    }

    /**
     * 编码添加
     * */
    @Trans("system.codelist.add")
    public CodeListAddResp add(CodeListAddReq req){
        if (req.getNameEn() == null || this.isExistByNameEn(req.getNameEn())){
            return new CodeListAddResp(CodeListAddResp.FAIL_CODELIST_ADD_CODE,CodeListAddResp.FAIL_CODELIST_ADD_MESSAGE);
        }
        CodeList codeList = new CodeList();
        BeanUtils.copyProperties(req,codeList);
        codeListService.doCreate(codeList);
        return new CodeListAddResp(CodeListAddResp.SUCCESS_CODELIST_ADD_CODE,CodeListAddResp.SUCCESS_CODELIST_ADD_MESSAGE);
    }

    public Boolean isExistByNameEn(String nameEn) {
        Boolean f = false;
        if (StringUtils.isNotEmpty(nameEn)){
            f = codeListService.doIsExistByNameEn(nameEn.trim());
        }
        return f;
    }

    /**
     * 编码编辑
     * */
    @Trans("system.codelist.edit")
    public CodeListEditResp edit(CodeListEditReq req){
        if (req.getId()==null){
            return new CodeListEditResp(CodeListEditResp.FAIL_CODELIST_EDIT_CODE,CodeListEditResp.FAIL_CODELIST_EDIT_MESSAGE);
        }
        CodeList codeList = codeListService.doFindById(req.getId());
        if (codeList==null){
            return new CodeListEditResp(CodeListEditResp.FAIL_CODELIST_EDIT_CODE,CodeListEditResp.FAIL_CODELIST_EDIT_MESSAGE);
        }
        BeanUtils.copyProperties(req,codeList);
        codeListService.doUpdateObj(codeList);
        return new CodeListEditResp(CodeListEditResp.SUCCESS_CODELIST_EDIT_CODE,CodeListEditResp.SUCCESS_CODELIST_EDIT_MESSAGE);
    }

    /**
     * 编码删除
     * */
    @Trans("system.codelist.delete")
    public CodeListDelResp delete(CodeListDelReq req){
        if (req.getId()==null){
            return new CodeListDelResp(CodeListDelResp.FAIL_CODELIST_DELETE_CODE,CodeListDelResp.FAIL_CODELIST_DELETE_MESSAGE);
        }
        int updateRow = codeListService.doDeleteById(req.getId());
        if (updateRow > 0){
            return new CodeListDelResp(CodeListDelResp.SUCCESS_CODELIST_DELETE_CODE,CodeListDelResp.SUCCESS_CODELIST_DELETE_MESSAGE);
        }
        return new CodeListDelResp(CodeListDelResp.FAIL_CODELIST_DELETE_CODE,CodeListDelResp.FAIL_CODELIST_DELETE_MESSAGE);
    }
}
