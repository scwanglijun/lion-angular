package com.newtouch.lion.admin.web.model.usergroup;

/**
 * Created by Administrator on 2016/4/14.
 */
public class UserGroupAddResp {
    public final static String SUCCESS_GROUP_ADD_CODE = "200";
    public final static String SUCCESS_GROUP_ADD_MESSAGE = "添加成功";
    public final static String FAIL_GROUP_ADD_CODE = "201";
    public final static String FAIL_GROUP_ADD_MESSAGE = "添加失败";

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

    public UserGroupAddResp(String code, String message) {
        this.code = code;
        this.message = message;
    }
}
