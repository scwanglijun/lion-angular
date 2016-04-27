package com.newtouch.lion.admin.web.model.department;

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
public class DepartmentResp {
    public final static String SUCCESS_DEPARTMENT_CODE = "200";
    public final static String SUCCESS_DEPARTMENT_MESSAGE = "操作成功";
    public final static String FAIL_DEPARTMENT_CODE = "201";
    public final static String FAIL_DEPARTMENT_MESSAGE = "操作失败";

    public String code;
    public String message;

    public DepartmentResp() {
    }

    public DepartmentResp(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
