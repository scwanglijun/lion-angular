package com.newtouch.lion.admin.web.model.department;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

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
public class DepartmentReq {
    /*** 部门ID */
    private Long id;
    /** 上一级部门ID */
    //@NotEmpty(message="{sys.department.parentid.missing}")
    private Long parentDepartmentId;
    /** 部门编号 */
    @NotEmpty(message="{sys.department.departmentno.missing}")
    @Length(max=30,min=4,message="{sys.department.departmentno.length}")
    private String departmentNo;
    /** 部门中文名称 */
    @NotEmpty(message="{sys.department.namezh.missing}")
    @Length(max=128,min=4,message="{sys.department.namezh.length}")
    private String nameZh;
    /** 部门英文名称 */
    @NotEmpty(message="{sys.department.nameen.missing}")
    @Length(max=128,min=4,message="{sys.department.nameen.length}")
    private String nameEn;
    /** 部门描述 */
    private String description;
    /** 是否可编辑 */
    private boolean editable;

    public DepartmentReq() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getParentDepartmentId() {
        return parentDepartmentId;
    }

    public void setParentDepartmentId(Long parentDepartmentId) {
        this.parentDepartmentId = parentDepartmentId;
    }

    public String getDepartmentNo() {
        return departmentNo;
    }

    public void setDepartmentNo(String departmentNo) {
        this.departmentNo = departmentNo;
    }

    public String getNameZh() {
        return nameZh;
    }

    public void setNameZh(String nameZh) {
        this.nameZh = nameZh;
    }

    public String getNameEn() {
        return nameEn;
    }

    public void setNameEn(String nameEn) {
        this.nameEn = nameEn;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isEditable() {
        return editable;
    }

    public void setEditable(boolean editable) {
        this.editable = editable;
    }
}
