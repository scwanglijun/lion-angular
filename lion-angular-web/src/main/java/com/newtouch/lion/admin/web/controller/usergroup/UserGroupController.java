package com.newtouch.lion.admin.web.controller.usergroup;

import com.newtouch.lion.admin.web.model.role.AuthModel;
import com.newtouch.lion.admin.web.model.role.RoleGetReq;
import com.newtouch.lion.admin.web.model.usergroup.*;
import com.newtouch.lion.common.date.DateUtil;
import com.newtouch.lion.model.system.*;
import com.newtouch.lion.page.PageResult;
import com.newtouch.lion.query.QueryCriteria;
import com.newtouch.lion.service.system.GroupService;
import com.newtouch.lion.service.system.RoleService;
import com.newtouch.lion.service.system.UserService;
import com.newtouch.lion.session.AppContext;
import com.newtouch.lion.web.page.Page;
import com.newtouch.lion.web.util.QueryUtils;
import com.newtouch.lion.webtrans.trans.Trans;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016/4/14.
 */
@Controller
public class UserGroupController {

    @Autowired
    private GroupService groupService;

    @Autowired
    private RoleService roleService;
    @Autowired
    private UserService userService;
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
        if (StringUtils.isNotEmpty(req.getNameZh())) {
            queryCriteria.addQueryCondition("nameZh", "%"+req.getNameZh()+"%");
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

        if(this.isExistByNameEn(req.getNameEn())){
            return new UserGroupAddResp(UserGroupAddResp.FAIL_GROUP_ADD_CODE,UserGroupAddResp.FAIL_GROUP_ADD_MESSAGE);
        }

        Group group = new Group();
        BeanUtils.copyProperties(req,group);
        groupService.doCreate(group);
        return new UserGroupAddResp(UserGroupAddResp.SUCCESS_GROUP_ADD_CODE,UserGroupAddResp.SUCCESS_GROUP_ADD_MESSAGE);
    }

    private Boolean isExistByNameEn(String nameEn) {
        Boolean flag = false;
        if (StringUtils.isNotEmpty(nameEn)) {
            flag = groupService.doIsExistByNameEn(nameEn.trim());
        }
        return flag;
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
        /**
         * 根据id获取值
         * */
        int updateRow = this.groupService.doDeleteByIds(req.getIds());
        if (updateRow > 0){
            return new UserGroupDelResp(UserGroupDelResp.SUCCESS_GROUP_DELETE_CODE,UserGroupDelResp.SUCCESS_GROUP_DELETE_MESSAGE);
        }else {
            return new UserGroupDelResp(UserGroupDelResp.FAIL_GROUP_DELETE_CODE,UserGroupDelResp.FAIL_GROUP_DELETE_MESSAGE);
        }
    }

    /***
     * 已关联角色
     * @param req
     * @return
     */
    @Trans("system.usergroup.authrole")
    public Page<Role> authRole(GroupGetReq req){

        QueryCriteria queryCriteria = QueryUtils.pageFormat(new QueryCriteria(),req);
        //设置排序字段
        queryCriteria.setOrderField("role." + DEFAULT_ORDER_FILED_NAME);
        queryCriteria.addQueryCondition("groupId",req.getId());
        PageResult<Role> pageResult = this.roleService.doFindByCriteriaAndGroup(queryCriteria);
        for(Role role:pageResult.getContent()){
            role.setUsers(null);
            role.setResources(null);
            role.setGroups(null);
        }
        return new Page<Role>(pageResult);
    }


    /***
     * 已关联用户
     * @param req
     * @return
     */
    @Trans("system.usergroup.authuser")
    public Page<User> authUsers(RoleGetReq req) {
        QueryCriteria queryCriteria = QueryUtils.pageFormat( new QueryCriteria(),req);

        // 设置排序字段及排序方向
        queryCriteria.setOrderField("user."+DEFAULT_ORDER_FILED_NAME);

        // 查询条件 参数类型 用户名
        if (req.getId()==null) {
            return null;
        }

        queryCriteria.addQueryCondition("groupId",req.getId());

        PageResult<User> pageResult = this.userService.doFindByCriteriaAndGroup(queryCriteria);

        for(User user:pageResult.getContent()){
            user.setRoles(null);
            user.setGroups(null);
            user.setDepartment(null);
        }

        return new Page<User>(pageResult);
    }

    /***
     *关联角色
     * @param req
     * @return
     */
    @Trans("system.usergroup.authRoleCtrl")
    public Page<RoleGroup> roles(RoleGetReq req) {
        QueryCriteria queryCriteria = QueryUtils.pageFormat(new QueryCriteria(),req);

        // 设置排序字段及排序方向
        queryCriteria.setOrderField(DEFAULT_ORDER_FILED_NAME);

        PageResult<RoleGroup> pageResult = this.roleService
                .doFindRoleGroupByCriteria(queryCriteria, req.getId());

        return new Page<RoleGroup>(pageResult);
    }

    /***
     * 关联用户
     * @param req
     * @return
     */
    @Trans("system.usergroup.authUserCtrl")
    public Page<UserGroup> users(RoleGetReq req) {
        QueryCriteria queryCriteria = QueryUtils.pageFormat( new QueryCriteria(),req);

        // 设置排序字段及排序方向
            queryCriteria.setOrderField(DEFAULT_ORDER_FILED_NAME);

        PageResult<UserGroup> pageResult = this.userService
                .doFindUserGroupByCriteria(queryCriteria);

        if (CollectionUtils.isEmpty(pageResult.getContent())) {
            return new Page<UserGroup>(pageResult);
        }
        List<UserGroup> userGroups = pageResult.getContent();
        List<Long> userIds = new ArrayList<Long>();
        for (UserGroup userGroup : userGroups) {
            userIds.add(userGroup.getId());
        }
        queryCriteria = new QueryCriteria();
        // 设置分页 启始页
        queryCriteria.setStartIndex(0);
        // 每页大小
        queryCriteria.setPageSize(req.getPage().getPageSize());
        // 查询条件 参数类型 用户名
        if (req.getId() != null && req.getId() > 0) {
            queryCriteria.addQueryCondition("groupId", req.getId());
        }
        // 查询所有空的
        if (!CollectionUtils.isEmpty(userIds)) {
            queryCriteria.addQueryCondition("userIds", userIds);
        }
        PageResult<User> userPageResult = this.userService
                .doFindByCriteriaAndGroup(queryCriteria);

        Map<Long, Long> userIdsMap = new HashMap<Long, Long>();

        for (User user : userPageResult.getContent()) {
            userIdsMap.put(user.getId(), user.getId());
        }

        List<UserGroup> contents = new ArrayList<UserGroup>();

        for (UserGroup userGroup : userGroups) {
            if (userIdsMap.containsKey(userGroup.getId())) {
                userGroup.setGroupId(req.getId());
            }
            contents.add(userGroup);
        }

        pageResult.setContent(contents);

        return new Page<UserGroup>(pageResult);

    }


    /***
     * 授权到角色
     * @param authModel
     * @return
     */
    @Trans("system.usergroup.authRoleGroupCtrl")
    public GroupGetResp addRoles(AuthModel authModel) {
        // 查询角色列表
        QueryCriteria queryCriteria = new QueryCriteria(0, 9999);
        // 查询条件用户组ID
        if (authModel.getId() > 0) {
            queryCriteria.addQueryCondition("groupId", authModel.getId());
        }
        // 查询所有空的
        if (!CollectionUtils.isEmpty(authModel.getAuths())) {
            queryCriteria.addQueryCondition("roleIds", authModel.getAuths());
        }
        PageResult<Role> pageResult = this.roleService
                .doFindByCriteriaAndGroup(queryCriteria);
        // 验证输入

        List<Long> targetRoleIds = authModel.getSelecteds();
        List<Long> deleteRoleIds = new ArrayList<Long>();
        for (Role role : pageResult.getContent()) {
            // 过滤已授权的列表
            if (targetRoleIds.contains(role.getId())) {
                targetRoleIds.remove(role.getId());
            } else {
                // 删除未授权
                deleteRoleIds.add(role.getId());
            }
        }
        Group group = this.groupService.doFindById(authModel.getId());
        this.groupService
                .doAuthRoleToGroup(targetRoleIds, deleteRoleIds, group);

        return new GroupGetResp(GroupGetResp.SUCCESS_GROUP_CODE,GroupGetResp.SUCCESS_GROUP_MESSAGE);
    }


    /**
     * 授权到用户
     * @param authModel
     * @return
     */
    @Trans("system.usergroup.authRoleRoleCtrl")
    public GroupGetResp addUsers( AuthModel authModel) {
        // 查询用户列表
        QueryCriteria queryCriteria = new QueryCriteria(0, 9999);
        // 查询条件 参数类型 用户名
        if (authModel.getId() > 0) {
            queryCriteria.addQueryCondition("groupId", authModel.getId());
        }
        // 查询所有空的
        if (!CollectionUtils.isEmpty(authModel.getAuths())) {
            queryCriteria.addQueryCondition("userIds", authModel.getAuths());
        }
        PageResult<User> userPageResult = this.userService
                .doFindByCriteriaAndGroup(queryCriteria);

        Group group = this.groupService.doFindById(authModel.getId());
        List<Long> targetUserIds = authModel.getSelecteds();
        List<Long> deleteUserIds = new ArrayList<Long>();
        for (User user : userPageResult.getContent()) {
            if (targetUserIds.contains(user.getId())) {
                targetUserIds.remove(user.getId());
            } else {
                deleteUserIds.add(user.getId());
            }
        }

        this.groupService.idoAuthUserToGroup(targetUserIds, deleteUserIds,
                group);

        return new GroupGetResp(GroupGetResp.SUCCESS_GROUP_CODE,GroupGetResp.SUCCESS_GROUP_MESSAGE);
    }
}
