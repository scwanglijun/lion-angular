package com.newtouch.lion.admin.web.model.resource;

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
 * Company:
 * </p>
 *
 * @author LiXiaoHao
 * @version 1.0
 */
public class ResourceResp {
    public final static String SUCCESS_RESOURCE_CODE = "200";
    public final static String SUCCESS_RESOURCE_MESSAGE = "操作成功";
    public final static String FAIL_RESOURCE_CODE = "201";
    public final static String FAIL_RESOURCE_MESSAGE = "操作失败";

    private String code;
    private String message;

    public ResourceResp() {
    }

    public ResourceResp(String code, String message) {
        this.code = code;
        this.message = message;
    }

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
}
