package com.newtouch.lion.admin.web.model.code;

/**
 * Created by Administrator on 2016/4/15.
 */
public class CodeListAddResp {
    public final static String SUCCESS_CODELIST_ADD_CODE = "200";
    public final static String SUCCESS_CODELIST_ADD_MESSAGE = "添加成功";
    public final static String FAIL_CODELIST_ADD_CODE = "201";
    public final static String FAIL_CODELIST_ADD_MESSAGE = "添加失败";

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

    public CodeListAddResp(String code, String message) {
        this.code = code;
        this.message = message;
    }
}

