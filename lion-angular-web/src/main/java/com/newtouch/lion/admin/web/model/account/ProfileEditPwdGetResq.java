package com.newtouch.lion.admin.web.model.account;

/**
 * Created by Administrator on 2016/4/5.
 */
public class ProfileEditPwdGetResq {
    public final static String SUCCESS_ROLE_EDIT_CODE = "200";
    public final static String SUCCESS_ROLE_EDIT_MESSAGE = "修改成功";
    public final static String FAIL_ROLE_EDIT_CODE = "201";
    public final static String FAIL_ROLE_EDIT_MESSAGE = "修改失败";
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

    public ProfileEditPwdGetResq(String code, String message) {
        this.message = message;
        this.code = code;
    }

}
