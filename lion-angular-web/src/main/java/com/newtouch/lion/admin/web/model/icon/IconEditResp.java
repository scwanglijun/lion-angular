package com.newtouch.lion.admin.web.model.icon;

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
public class IconEditResp {
    public final static String SUCCESS_ICON_EDIT_CODE = "200";
    public final static String SUCCESS_ICON_EDIT_MESSAGE = "添加成功";
    public final static String FAIL_ICON_EDIT_CODE = "201";
    public final static String FAIL_ICON_EDIT_MESSAGE = "添加失败";

    public String code;
    public String message;

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

    public IconEditResp(String code, String message) {
        this.code = code;
        this.message = message;
    }
}
