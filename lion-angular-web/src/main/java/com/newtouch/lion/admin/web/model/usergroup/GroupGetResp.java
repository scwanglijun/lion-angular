package com.newtouch.lion.admin.web.model.usergroup;

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
public class GroupGetResp {
    public final static String SUCCESS_GROUP_CODE = "200";
    public final static String SUCCESS_GROUP_MESSAGE = "添加成功";
    public final static String FAIL_GROUP_CODE = "201";
    public final static String FAIL_GROUP_MESSAGE = "添加失败";

    private String code;
    private String message;

    public GroupGetResp() {
    }

    public GroupGetResp(String code, String message) {
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
