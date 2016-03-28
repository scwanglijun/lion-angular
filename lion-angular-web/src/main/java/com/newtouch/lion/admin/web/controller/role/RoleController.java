package com.newtouch.lion.admin.web.controller.role;/**
 * Created by jovi on 3/25/16.
 */

import com.newtouch.lion.admin.web.model.role.*;
import com.newtouch.lion.model.system.Resource;
import com.newtouch.lion.model.system.Role;
import com.newtouch.lion.model.system.User;
import com.newtouch.lion.page.PageResult;
import com.newtouch.lion.query.QueryCriteria;
import com.newtouch.lion.service.system.RoleService;
import com.newtouch.lion.web.page.Page;
import com.newtouch.lion.web.servlet.view.support.BindMessage;
import com.newtouch.lion.web.servlet.view.support.BindResult;
import com.newtouch.lion.webtrans.trans.Trans;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.*;

/**
 * <p>
 * Title:角色控制器
 * </p>
 * <p>
 * Description:角色控制器
 * </p>
 * <p>
 * Copyright: Copyright (c) 2016
 * </p>
 * <p>
 * Company: XiQing
 * </p>
 *
 * @author MaoJiaWei
 * @version 1.0
 */
@Controller
public class RoleController {
    @Autowired
    private RoleService  roleService;

    /**默认排序字段名称*/
    private static final String DEFAULT_ORDER_FILED_NAME="id";

    @Trans("system.role.list")
    public Page<Role> list(RoleGetReq req) {
        QueryCriteria queryCriteria = new QueryCriteria();

        // 设置分页 启始页
        queryCriteria.setStartIndex(req.getPage().getPageNumber()-1);
        // 每页大小
        queryCriteria.setPageSize(req.getPage().getPageSize());
        // 设置排序字段及排序方向

       if(req.getSort()==null){
           queryCriteria.setOrderField(DEFAULT_ORDER_FILED_NAME);
           queryCriteria.setOrderDirection(QueryCriteria.ASC);
       }else{
           if (StringUtils.isNotEmpty(req.getSort().getSort()) && StringUtils.isNotEmpty(req.getSort().getOrder())) {
               queryCriteria.setOrderField(req.getSort().getOrder());
               queryCriteria.setOrderDirection(req.getSort().getSort());
           } else {
               queryCriteria.setOrderField(DEFAULT_ORDER_FILED_NAME);
               queryCriteria.setOrderDirection(QueryCriteria.ASC);
           }
       }
        //查询条件 中文参数名称按模糊查询
        if(StringUtils.isNotEmpty(req.getNameZh())){
            queryCriteria.addQueryCondition("nameZh","%"+req.getNameZh()+"%");
        }

        PageResult<Role> pageResult = roleService.doFindByCriteria(queryCriteria);
        List<Role> list = pageResult.getContent();
        System.out.println(list);
        List<Role> list2 = new ArrayList<>();
        for(int i=0; i<list.size(); i++) {
            Role role = list.get(i);
            role.getResources().clear();
            role.getUsers().clear();
            role.getGroups().clear();
            list2.add(role);
        }
        pageResult.setContent(list2);
        return new Page(pageResult);
    }

    @Trans("system.role.add")
    public RoleAddResp add(@Valid @ModelAttribute("role") RoleAddReq roleVo){

        if (this.isExistByNameEn(roleVo.getNameEn())) {
            return new RoleAddResp(RoleAddResp.FAIL_ROLE_ADD_CODE,RoleAddResp.FAIL_ROLE_ADD_MESSAGE);
        }

        Role role = new Role();

        BeanUtils.copyProperties(roleVo, role);
        roleService.doCreate(role);

        return new RoleAddResp(RoleAddResp.SUCCESS_ROLE_ADD_CODE,RoleAddResp.SUCCESS_ROLE_ADD_MESSAGE);
//        return null;
    }

    /*add by maojiawei*/
    private Boolean isExistByNameEn(String nameEn) {
        Boolean flag = false;
        if (StringUtils.isNotEmpty(nameEn)) {
            flag = roleService.doIsExistByNameEn(nameEn.trim());
        }
        return flag;
    }
    /*add by maojiawei*/
    private Boolean isExistByNameEn(String nameEn, String oldNameEn) {
        Boolean flag = false;
        if (StringUtils.isNotEmpty(nameEn) && !nameEn.equals(oldNameEn)) {
            flag = roleService.doIsExistByNameEn(nameEn.trim());
        }
        return flag;
    }

    @Trans("system.role.delete")
    public RoleDelResp delete(RoleDelReq req){
        Map<String, String> params = new HashMap<String, String>();
        int updateRow = this.roleService.doDeleteById(req.getId());
        if (updateRow > 0) {
           return new RoleDelResp(RoleAddResp.SUCCESS_ROLE_ADD_CODE,RoleAddResp.SUCCESS_ROLE_ADD_MESSAGE);
        } else {
            return new RoleDelResp(RoleAddResp.FAIL_ROLE_ADD_CODE,RoleAddResp.FAIL_ROLE_ADD_MESSAGE);
        }
    }

    @Trans("system.role.edit")
    public RoleEditResp edit(RoleEditReq req){
        Map<String, String> params = new HashMap<String, String>();
        int updateRow = this.roleService.doDeleteById(req.getId());
        if (updateRow > 0) {
            return new RoleEditResp(RoleAddResp.SUCCESS_ROLE_ADD_CODE,RoleAddResp.SUCCESS_ROLE_ADD_MESSAGE);
        } else {
            return new RoleEditResp(RoleAddResp.FAIL_ROLE_ADD_CODE,RoleAddResp.FAIL_ROLE_ADD_MESSAGE);
        }
    }
}
