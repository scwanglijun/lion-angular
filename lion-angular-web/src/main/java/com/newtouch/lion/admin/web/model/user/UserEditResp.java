package com.newtouch.lion.admin.web.model.user;

/**
 * Created by Administrator on 2016/4/1.
 */
public class UserEditResp {
    public final static String SUCCESS_USER_EDIT_CODE = "200";
    public final static String SUCCESS_USER_EDIT_MESSAGE = "编辑成功";
    public final static String FAIL_USER_EDIT_CODE = "201";
    public final static String FAIL_USER_EDIT_MESSAGE = "编辑失败";

    private String code;
    private String message;

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

    public UserEditResp(String code, String message) {
        this.code = code;
        this.message = message;
    }
}
