package com.newtouch.lion.admin.web.model.datagrid;

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
public class DataGridEditorResp {
    public final static String SUCCESS_DATAGRID_EDITOR_CODE = "200";
    public final static String SUCCESS_DATAGRID_EDITOR_MESSAGE = "编辑成功";
    public final static String FAIL_DATAGRID_EDITOR_CODE = "201";
    public final static String FAIL_DATAGRID_EDITOR_MESSAGE = "编辑失败";
//    public final static String NOT_EXIST_EDITOR_CODE="";
//    public final static String NOT_EXIST_EDITOR_MESSAGE="";

    private String code;
    private String message;

    public DataGridEditorResp() {
    }

    public DataGridEditorResp(String code, String message) {
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
