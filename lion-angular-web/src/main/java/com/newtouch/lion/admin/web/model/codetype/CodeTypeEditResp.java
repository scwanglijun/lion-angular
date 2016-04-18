package com.newtouch.lion.admin.web.model.codetype;

/**
 * <p>
 * Title:
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Copyright: Copyright (c) 2016
 * </p>
 * <p>
 * Company: NewTouch
 * </p>
 *
 * @author ZhangYake
 * @version 1.0
 */
public class CodeTypeEditResp {
    public final static String SUCCESS_CODETYPE_EDIT_CODE = "200";
    public final static String SUCCESS_CODETYPE_EDIT_MESSAGE = "编辑成功";
    public final static String FAIL_CODETYPE_EDIT_CODE = "201";
    public final static String FAIL_CODETYPE_EDIT_MESSAGE = "失编辑败";

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

    public CodeTypeEditResp(String code, String message) {
        this.code = code;
        this.message = message;
    }
}
