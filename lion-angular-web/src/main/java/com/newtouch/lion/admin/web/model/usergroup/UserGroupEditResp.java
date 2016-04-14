package com.newtouch.lion.admin.web.model.usergroup;

/**
 * Created by Administrator on 2016/4/14.
 */
public class UserGroupEditResp {
    public final static String SUCCESS_GROUP_EDIT_CODE = "200";
    public final static String SUCCESS_GROUP_EDIT_MESSAGE = "编辑成功";
    public final static String FAIL_GROUP_EDIT_CODE = "201";
    public final static String FAIL_GROUP_EDIT_MESSAGE = "失编辑败";

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

    public UserGroupEditResp(String code, String message) {
        this.code = code;
        this.message = message;
    }
}
