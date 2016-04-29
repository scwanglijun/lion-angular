package com.newtouch.lion.admin.web.model.user;

/**
 * Created by LiXiaoHao on 2016/4/1.
 */
public class UserAuthResp {
    public final static String SUCCESS_USER_AUTH_CODE = "200";
    public final static String SUCCESS_USER_AUTH_MESSAGE = "添加成功";
    public final static String FAIL_USER_AUTH_CODE = "201";
    public final static String FAIL_USER_AUTH_MESSAGE = "添加失败";

    public String code;
    public String message;

    public UserAuthResp() {
    }

    public UserAuthResp(String code, String message) {
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
