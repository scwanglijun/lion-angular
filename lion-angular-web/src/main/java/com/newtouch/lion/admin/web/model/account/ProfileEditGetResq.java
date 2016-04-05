package com.newtouch.lion.admin.web.model.account;

import com.newtouch.lion.admin.web.model.query.QueryReq;
import com.newtouch.lion.model.system.Department;

import java.util.Date;

/**
 * Created by Administrator on 2016/4/1.
 */
public class ProfileEditGetResq {
    public final static String SUCCESS_ROLE_EDIT_CODE = "200";
    public final static String SUCCESS_ROLE_EDIT_MESSAGE = "编辑成功";
    public final static String FAIL_ROLE_EDIT_CODE = "201";
    public final static String FAIL_ROLE_EDIT_MESSAGE = "编辑失败";

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

    public ProfileEditGetResq(String code, String message) {
        this.message = message;
        this.code = code;
    }
}
