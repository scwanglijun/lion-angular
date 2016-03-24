package com.newtouch.lion.admin.web.model.user;/**
 * Created by jovi on 3/24/16.
 */

/**
 * <p>
 * Title:登录用户返回信息
 * </p>
 * <p>
 * Description:登录用户返回信息
 * </p>
 * <p>
 * Copyright: Copyright (c) 2016
 * </p>
 * <p>
 * Company: XiQing
 * </p>
 *
 * @author MaoJiaWei
 * @version 1.0
 */
public class LoginUserResp {
    private String code;
    private String message;

    public final static String SUCCESS_CODE = "200";
    public final static String SUCCESS_MESSAGE = "登录成功";
    //账号不存在
    public final static String UNKNOWN_ACCOUNT_CODE = "201";
    public final static String UNKNOWN_ACCOUNT_MESSAGE = "账号不存在";
    //密码错误
    public final static String INCORRECT_CREDENTIALS_CODE = "202";
    public final static String INCORRECT_CREDENTIALS_MESSAGE = "密码错误";
    //用户锁定
    public final static String LOCKED_ACCOUNT_CODE = "203";
    public final static String LOCKED_ACCOUNT_MESSAGE = "该用户已被锁定";
    //账号到期
    public final static String EXPIRE_ACCOUNT_CODE = "204";
    public final static String EXPIRE_ACCOUNT_MESSAGE = "该账号已到期";
    //证书到期
    public final static String CREDENTIAL_EXPIRED_CODE = "205";
    public final static String CREDENTIAL_EXPIRED_MESSAGE = "该证书已到期";
    //授权异常
    public final static String AUTHENTICATION_EXCEPTION_CODE = "206";
    public final static String AUTHENTICATION_EXCEPTION_MESSAGE = "该账号授权异常";
    //未授权
    public final static String UNAUTHENTICATION_CODE = "207";
    public final static String UNAUTHENTICATION_MESSAGE = "该账号尚未授权";

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

    public LoginUserResp(String code, String message) {
        this.code = code;
        this.message = message;
    }
}
