package com.newtouch.lion.admin.web.controller.role;/**
 * Created by jovi on 3/25/16.
 */

import com.newtouch.lion.admin.web.model.role.RoleGetReq;
import com.newtouch.lion.model.system.Resource;
import com.newtouch.lion.model.system.Role;
import com.newtouch.lion.model.system.User;
import com.newtouch.lion.page.PageResult;
import com.newtouch.lion.query.QueryCriteria;
import com.newtouch.lion.service.system.RoleService;
import com.newtouch.lion.web.page.Page;
import com.newtouch.lion.webtrans.trans.Trans;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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

    @Trans("role.list")
    public Page<Role> list(RoleGetReq req) {
        QueryCriteria queryCriteria = new QueryCriteria();

        // 设置分页 启始页
        queryCriteria.setStartIndex(req.getPage().getPageNumber()-1);
        // 每页大小
        queryCriteria.setPageSize(req.getPage().getPageSize());
        // 设置排序字段及排序方向
//        if (StringUtils.isNotEmpty(req.getSort()) && StringUtils.isNotEmpty(req.getOrder())) {
//            queryCriteria.setOrderField(req.getSort());
//            queryCriteria.setOrderDirection(req.getOrder());
//        } else {
//            queryCriteria.setOrderField(DEFAULT_ORDER_FILED_NAME);
//            queryCriteria.setOrderDirection(QueryCriteria.ASC);
//        }
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
        System.out.print(pageResult.getContent());
        return new Page(pageResult);
    }

}
