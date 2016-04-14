package com.newtouch.lion.admin.web.model.usergroup;

/**
 * Created by Administrator on 2016/4/14.
 */
public class UserGroupDelResp {
    public final static String SUCCESS_GROUP_DELETE_CODE = "200";
    public final static String SUCCESS_GROUP_DELETE_MESSAGE = "删除成功";
    public final static String FAIL_GROUP_DELETE_CODE = "201";
    public final static String FAIL_GROUP_DELETE_MESSAGE = "删除失败";

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

    public UserGroupDelResp(String code, String message) {
        this.code = code;
        this.message = message;
    }
}
