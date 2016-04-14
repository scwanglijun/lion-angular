package com.newtouch.lion.admin.web.controller.usergroup;

import com.newtouch.lion.admin.web.model.usergroup.*;
import com.newtouch.lion.common.date.DateUtil;
import com.newtouch.lion.model.system.Group;
import com.newtouch.lion.page.PageResult;
import com.newtouch.lion.query.QueryCriteria;
import com.newtouch.lion.service.system.GroupService;
import com.newtouch.lion.web.page.Page;
import com.newtouch.lion.web.util.QueryUtils;
import com.newtouch.lion.webtrans.trans.Trans;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/4/14.
 */
@Controller
public class UserGroupController {

    @Autowired
    private GroupService groupService;

    //默认排序字段
    private final static String DEFAULT_ORDER_FILED_NAME="id";
    /**
     * 用户列表
     * */
    @Trans("system.usergroup.list")
    public Page<Group> list(UserGroupGetReq req){

        //排序
        QueryCriteria queryCriteria = QueryUtils.pageFormat(new QueryCriteria() ,req);

        //查询条件
        if (StringUtils.isNotEmpty(req.getRealnameZh())) {
            queryCriteria.addQueryCondition("RealnameZh", "%"+req.getRealnameZh()+"%");
        }

        PageResult<Group> pageResult = groupService.doFindByCriteria(queryCriteria);
        List<UserGroupGetResp> list = new ArrayList<UserGroupGetResp>();

        for (Group group : pageResult.getContent()){
            UserGroupGetResp userGroupGetResp = new UserGroupGetResp();
            BeanUtils.copyProperties(group,userGroupGetResp);

            String createDate = DateUtil.formatDate(group.getCreatedDate(),DateUtil.FORMAT_DATE_SLASH_YYYY_MM_DD);
            String updateDate = DateUtil.formatDate(group.getUpdatedDate(),DateUtil.FORMAT_DATE_SLASH_YYYY_MM_DD);
            userGroupGetResp.setCreatedDate(createDate);
            userGroupGetResp.setUpdatedDate(updateDate);

            list.add(userGroupGetResp);
        }
        PageResult<UserGroupGetResp> pageResultResp = new PageResult<UserGroupGetResp>();
        BeanUtils.copyProperties(pageResult,pageResultResp);
        pageResultResp.setContent(list);

        return new Page(pageResultResp);
    }

    /**
     * 用户组添加
     * */
    @Trans("system.usergroup.add")
    public UserGroupAddResp add(UserGroupAddReq req){
        Group group = new Group();
        BeanUtils.copyProperties(req,group);
        groupService.doCreate(group);
        return new UserGroupAddResp(UserGroupAddResp.SUCCESS_GROUP_ADD_CODE,UserGroupAddResp.FAIL_GROUP_ADD_MESSAGE);
    }
    /**
     * 用户组编辑
     * */
    @Trans("system.usergroup.edit")
    public UserGroupEditResp edit(UserGroupEditReq req){
        Group group = groupService.doFindById(req.getId());

        if (group == null){
            return new UserGroupEditResp(UserGroupEditResp.FAIL_GROUP_EDIT_CODE,UserGroupEditResp.FAIL_GROUP_EDIT_MESSAGE);
        }
        BeanUtils.copyProperties(req,group);
        groupService.doUpdate(group);
        return new UserGroupEditResp(UserGroupEditResp.SUCCESS_GROUP_EDIT_CODE,UserGroupEditResp.SUCCESS_GROUP_EDIT_MESSAGE);
    }
    /**
     * 用户组删除
     * */
    @Trans("system.usergroup.delete")
    public UserGroupDelResp delete(UserGroupDelReq req){
        int updateRow = this.groupService.doDeleteById(req.getId());
        if (updateRow > 0){
            return new UserGroupDelResp(UserGroupDelResp.SUCCESS_GROUP_DELETE_CODE,UserGroupDelResp.SUCCESS_GROUP_DELETE_MESSAGE);
        }else {
            return new UserGroupDelResp(UserGroupDelResp.FAIL_GROUP_DELETE_CODE,UserGroupDelResp.FAIL_GROUP_DELETE_MESSAGE);
        }
    }
}
