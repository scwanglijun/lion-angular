package com.newtouch.lion.admin.web.controller.department;

import com.newtouch.lion.admin.web.model.department.DepartmentReq;
import com.newtouch.lion.admin.web.model.department.DepartmentResp;
import com.newtouch.lion.model.system.Department;
import com.newtouch.lion.service.system.DepartmentService;
import com.newtouch.lion.webtrans.trans.Trans;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

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
 * Company:
 * </p>
 *
 * @author LiXiaoHao
 * @version 1.0
 */
@Controller
public class DepartmentController {

    @Autowired
    private DepartmentService departmentService;
    /***
     * 加载部门树
     * @param req
     * @return
     */
    @Trans("system.department.list")
    public List<Department> findDepartmentToTree(DepartmentReq req){
        List<Department> departments = this.departmentService.doFindAll();
        for(Department department:departments){
            department.setUsers(null);
            department.setDepartment(null);
            department.setSortedChildDepartment(null);
            department.setDepartments(null);
        }
        return departments;
    }

    /***
     * 获取上一级部门
     * @param req
     * @return
     */
    @Trans("system.department.departmentGet")
    public Department resourceGet(DepartmentReq req) {

        if(req.getId()==null){
            return null;
        }
        Department department = departmentService.doGetById(req.getId());

        if(department==null){
            return null;
        }

        return department;
    }

    /***
     * 编辑
     * @param req
     * @return
     */
    @Trans("system.department.editor")
    public DepartmentResp editor(DepartmentReq req) {
        if(req.getId()==null || req.getParentDepartmentId()==null){
            return new DepartmentResp(DepartmentResp.FAIL_DEPARTMENT_CODE,DepartmentResp.FAIL_DEPARTMENT_MESSAGE);
        }
        Department parentDepartment = departmentService.doGetById(req.getParentDepartmentId());
        if(parentDepartment==null){
            return new DepartmentResp(DepartmentResp.FAIL_DEPARTMENT_CODE,DepartmentResp.FAIL_DEPARTMENT_MESSAGE);
        }
        Department department = departmentService.doGetById(req.getId());
        if(department != null){
            BeanUtils.copyProperties(req,department);
           departmentService.doUpdate(department);
            return new DepartmentResp(DepartmentResp.SUCCESS_DEPARTMENT_CODE,DepartmentResp.SUCCESS_DEPARTMENT_MESSAGE);
        }


        return new DepartmentResp(DepartmentResp.FAIL_DEPARTMENT_CODE,DepartmentResp.FAIL_DEPARTMENT_MESSAGE);
    }


    /***
     * 添加
     * @param req
     * @return
     */
    @Trans("system.department.add")
    public DepartmentResp add(DepartmentReq req) {
        Department department = new Department();
        BeanUtils.copyProperties(req, department);
        this.departmentService.doCreateDepartment(department);
        return new DepartmentResp(DepartmentResp.SUCCESS_DEPARTMENT_CODE,DepartmentResp.SUCCESS_DEPARTMENT_MESSAGE);
    }

}
