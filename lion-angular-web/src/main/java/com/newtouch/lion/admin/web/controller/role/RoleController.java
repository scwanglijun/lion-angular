package com.newtouch.lion.admin.web.controller.role;/**
 * Created by jovi on 3/25/16.
 */

import com.newtouch.lion.admin.web.model.resource.ResourceGetReq;
import com.newtouch.lion.admin.web.model.resource.TempResourceResp;
import com.newtouch.lion.admin.web.model.role.*;
import com.newtouch.lion.model.system.*;
import com.newtouch.lion.page.PageResult;
import com.newtouch.lion.query.QueryCriteria;
import com.newtouch.lion.service.system.GroupService;
import com.newtouch.lion.service.system.ResourceService;
import com.newtouch.lion.service.system.RoleService;
import com.newtouch.lion.service.system.UserService;
import com.newtouch.lion.web.page.Page;
import com.newtouch.lion.web.servlet.view.support.BindMessage;
import com.newtouch.lion.web.servlet.view.support.BindResult;
import com.newtouch.lion.web.util.QueryUtils;
import com.newtouch.lion.webtrans.trans.Trans;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
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

    @Autowired
    private GroupService groupService;
    @Autowired
    private UserService userService;
    @Autowired
    private ResourceService resourceService;
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
        int updateRow = this.roleService.doDeleteRolesByIds(req.getIds());
        if (updateRow > 0) {
           return new RoleDelResp(RoleDelResp.SUCCESS_ROLE_DELETE_CODE,RoleDelResp.SUCCESS_ROLE_DELETE_MESSAGE);
        } else {
            return new RoleDelResp(RoleDelResp.FAIL_ROLE_DELETE_CODE,RoleDelResp.FAIL_ROLE_DELETE_MESSAGE);
        }
    }

    @Trans("system.role.edit")
    public RoleEditResp edit(RoleEditReq req){
        Map<String, String> params = new HashMap<String, String>();
        Role role = roleService.doFindById(req.getId());
        if (role == null) {
            return new RoleEditResp(RoleEditResp.FAIL_ROLE_EDIT_CODE,RoleEditResp.FAIL_ROLE_EDIT_MESSAGE);
        }
        BeanUtils.copyProperties(req, role);
        roleService.doUpdate(role);
        return new RoleEditResp(RoleEditResp.SUCCESS_ROLE_EDIT_CODE,RoleEditResp.SUCCESS_ROLE_EDIT_MESSAGE);
    }

    /***
     * 已关联用户组
     * @param req
     * @return
     */
    @Trans("system.role.roleGroupCtrl")
    public Page<Group> authGroups(RoleGetReq req) {
        QueryCriteria queryCriteria = QueryUtils.pageFormat(new QueryCriteria(),req);

        // 设置排序字段及排序方向
            queryCriteria.setOrderField("groups."+DEFAULT_ORDER_FILED_NAME);

        // 查询条件 参数类型 用户名
        if (req.getId()==null) {
            return null;
        }

        queryCriteria.addQueryCondition("roleId",req.getId());
        PageResult<Group> pageResult = this.groupService.doFindByCriteriaAndRole(queryCriteria);
        for(Group group:pageResult.getContent()){
        	group.setUsers(null);
        	group.setRoles(null);
        }
        return new Page<Group>(pageResult);
    }


    /***
     * 已关联用户
     * @param req
     * @return
     */
    @Trans("system.role.roleUserCtrl")
    public Page<User> authUsers(RoleGetReq req) {
        QueryCriteria queryCriteria = QueryUtils.pageFormat( new QueryCriteria(),req);

        // 设置排序字段及排序方向
            queryCriteria.setOrderField("user."+DEFAULT_ORDER_FILED_NAME);

        // 查询条件 参数类型 用户名
        if (req.getId()==null) {
            return null;
        }

        queryCriteria.addQueryCondition("roleId",req.getId());

        PageResult<User> pageResult = this.userService.doFindByCriteriaAndRole(queryCriteria);

        for(User user:pageResult.getContent()){
        	user.setRoles(null);
        	user.setGroups(null);
        	user.setDepartment(null);
        }
        
        return new Page<User>(pageResult);
    }


    /***
     * 关联用户组
     * @param req
     * @return
     */
    @Trans("system.role.authGroupCtrl")
    public Page<GroupRole> groups(RoleGetReq req) {
        QueryCriteria queryCriteria = QueryUtils.pageFormat( new QueryCriteria(),req);

        // 设置排序字段及排序方向
        queryCriteria.setOrderField(DEFAULT_ORDER_FILED_NAME);

        // 查询条件 参数类型 用户名
        if (req.getId()==null) {
            return null;
        }


        PageResult<GroupRole> pageResult = this.groupService.doFindGroupRoleByCriteria(queryCriteria,req.getId());

        
        
        return new Page<GroupRole>(pageResult);
    }


    /***
     * 关联用户
     * @param req
     * @return
     */
    @Trans("system.role.authUserCtrl")
    public Page<UserRole> users(RoleGetReq req) {
        QueryCriteria queryCriteria = QueryUtils.pageFormat( new QueryCriteria(),req);

        // 设置排序字段及排序方向
        queryCriteria.setOrderField(DEFAULT_ORDER_FILED_NAME);
        queryCriteria.setOrderDirection(QueryCriteria.ASC);

        // 查询条件 参数类型 用户名
        if (req.getId()==null) {
            return null;
        }

        PageResult<UserRole> pageResult = this.userService.doFindUserRoleByCriteria(queryCriteria,req.getId());

        return new Page<UserRole>(pageResult);

    }

    /***
     * 加载资源树
     * @param req
     * @return
     */
    @Trans("system.role.authResourceRole")
    public List<AuthResourceResp> lists(RoleGetReq req) {
        List<Resource> result=this.resourceService.doFindAll();

        List<AuthResourceResp> re = new ArrayList<AuthResourceResp>();

        for(Resource res:result){
        	res.setResource(null);
        	res.setResources(null);
        	
            AuthResourceResp tempReturn  =  new AuthResourceResp();
            BeanUtils.copyProperties(res,tempReturn);
            tempReturn.setRoles(res.getRoles());
            Boolean checked = Boolean.FALSE;
            for(Role role:res.getRoles()){
            	if(role.getId().equals(req.getId())){
            		checked = Boolean.TRUE;
            	}
            }
            tempReturn.setChecked(checked);
            re.add(tempReturn);
        }
        return re;
    }
    

    //** 授权到角色 *//

    @Trans("system.role.authGroup")
    public RoleAuthResp addRoles(AuthModel authModel) {
        //查询角色列表
        QueryCriteria queryCriteria = new QueryCriteria(0,9999);
        // 查询条件用户组ID
        if (authModel.getId()>0) {
            queryCriteria.addQueryCondition("roleId",authModel.getId());
        }
        //查询所有空的
        if(!CollectionUtils.isEmpty(authModel.getAuths())){
            queryCriteria.addQueryCondition("groupIds",authModel.getAuths());
        }
        PageResult<Group> pageResult=this.groupService.doFindByCriteriaAndRole(queryCriteria);
        //验证输入

        List<Long> targetGroupIds=authModel.getSelecteds();
        List<Long> deleteGroupIds=new ArrayList<Long>();
        for (Group group : pageResult.getContent()) {
            //过滤已授权的列表
            if (targetGroupIds.contains(group.getId())) {
                targetGroupIds.remove(group.getId());
            } else {
                //删除未授权
                deleteGroupIds.add(group.getId());
            }
        }
        
        Role role = this.roleService.doFindById(authModel.getId());
        this.roleService.idoAuthGroupToRole(targetGroupIds, deleteGroupIds, role);
       return new RoleAuthResp(RoleAuthResp.SUCCESS_ROLE_AUTH_CODE,RoleAuthResp.SUCCESS_ROLE_AUTH_MESSAGE);
    }
    
    /** 授权到用户 */
    @Trans("system.role.authUser")
	public RoleAuthResp addUsers(AuthModel authModel) {
		//查询用户列表
		QueryCriteria queryCriteria = new QueryCriteria(0,9999);
		// 查询条件 参数类型 用户名
		if (authModel.getId()>0) {
			queryCriteria.addQueryCondition("roleId",authModel.getId());
		}
		//查询所有空的
		if(!CollectionUtils.isEmpty(authModel.getAuths())){
			queryCriteria.addQueryCondition("userIds",authModel.getAuths());
		}
		PageResult<User> userPageResult=this.userService.doFindByCriteriaAndRole(queryCriteria);
		
		
		Role role = this.roleService.doFindById(authModel.getId());
		List<Long> targetUserIds =authModel.getSelecteds();
		List<Long> deleteUserIds = new ArrayList<Long>();
		for (User user :userPageResult.getContent()) {
			if (targetUserIds.contains(user.getId())) {
				targetUserIds.remove(user.getId());
			} else {
				deleteUserIds.add(user.getId());
			}
		}

		this.roleService.idoAuthUserToRole(targetUserIds, deleteUserIds,role);

       return new RoleAuthResp(RoleAuthResp.SUCCESS_ROLE_AUTH_CODE,RoleAuthResp.SUCCESS_ROLE_AUTH_MESSAGE);
	}

    /***
     * 资源授权
     * @param authModel
     * @return
     */
    @Trans("system.role.addResourceRole")
    public RoleAuthResp addresources(AuthModel authModel){
        //验证输入
        Role role=this.roleService.doGetById(authModel.getId());
        List<Long> targetResourceIds=authModel.getSelecteds();
        List<Long> deleteResourceIds = new ArrayList<Long>();
        for(Resource resource:role.getResources()){

            if(targetResourceIds.contains(resource.getId())){
                targetResourceIds.remove(resource.getId());
            }else{
                deleteResourceIds.add(resource.getId());
            }
        }
        this.roleService.doAuthResourceToRole(targetResourceIds, deleteResourceIds, role);

        return new RoleAuthResp(RoleAuthResp.SUCCESS_ROLE_AUTH_CODE,RoleAuthResp.SUCCESS_ROLE_AUTH_MESSAGE);
    }
   
}
