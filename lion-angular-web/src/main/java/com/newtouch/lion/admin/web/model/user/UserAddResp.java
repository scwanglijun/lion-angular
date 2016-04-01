package com.newtouch.lion.admin.web.model.user;

/**
 * Created by Administrator on 2016/4/1.
 */
public class UserAddResp {
    public final static String SUCCESS_ROLE_ADD_CODE = "200";
    public final static String SUCCESS_ROLE_ADD_MESSAGE = "添加成功";
    public final static String FAIL_ROLE_ADD_CODE = "201";
    public final static String FAIL_ROLE_ADD_MESSAGE = "添加失败";

    public String code;
    public String message;

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

    public UserAddResp(String code, String message) {
        this.code = code;
        this.message = message;
    }
}
