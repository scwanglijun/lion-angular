package com.newtouch.lion.admin.web.model.code;

/**
 * Created by Administrator on 2016/4/15.
 */
public class CodeListDelResp {
    public final static String SUCCESS_CODELIST_DELETE_CODE = "200";
    public final static String SUCCESS_CODELIST_DELETE_MESSAGE = "删除成功";
    public final static String FAIL_CODELIST_DELETE_CODE = "201";
    public final static String FAIL_CODELIST_DELETE_MESSAGE = "删除失败";

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

    public CodeListDelResp(String code, String message) {
        this.code = code;
        this.message = message;
    }
}
