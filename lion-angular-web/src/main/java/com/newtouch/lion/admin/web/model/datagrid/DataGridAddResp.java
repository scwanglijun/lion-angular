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
public class DataGridAddResp {
    public final static String SUCCESS_DATAGRID_ADD_CODE = "200";
    public final static String SUCCESS_DATAGRID_ADD_MESSAGE = "添加成功";
    public final static String FAIL_DATAGRID_ADD_CODE = "201";
    public final static String FAIL_DATAGRID_ADD_MESSAGE = "添加失败";

    private String code;
    private String message;

    public DataGridAddResp() {
    }

    public DataGridAddResp(String code, String message) {
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
