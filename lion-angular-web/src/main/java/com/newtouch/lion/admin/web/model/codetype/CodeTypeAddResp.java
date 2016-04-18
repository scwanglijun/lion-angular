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
public class CodeTypeAddResp {
    public final static String SUCCESS_CODETYPE_ADD_CODE = "200";
    public final static String SUCCESS_CODETYPE_ADD_MESSAGE = "添加成功";
    public final static String FAIL_CODETYPE_ADD_CODE = "201";
    public final static String FAIL_CODETYPE_ADD_MESSAGE = "添加失败";

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

    public CodeTypeAddResp(String code, String message) {
        this.code = code;
        this.message = message;
    }
}
