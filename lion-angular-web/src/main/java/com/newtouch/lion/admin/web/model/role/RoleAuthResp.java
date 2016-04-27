package com.newtouch.lion.admin.web.model.role;

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
public class RoleAuthResp {
    public final static String SUCCESS_ROLE_AUTH_CODE = "200";
    public final static String SUCCESS_ROLE_AUTH_MESSAGE = "授权成功成功";
    public final static String FAIL_ROLE_AUTH_CODE = "201";
    public final static String FAIL_ROLE_AUTH_MESSAGE = "授权失败";

    private String code;
    private String message;

    public RoleAuthResp() {
    }

    public RoleAuthResp(String code, String message) {
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
