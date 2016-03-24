package com.newtouch.lion.admin.web.model.user;/**
 * Created by jovi on 3/24/16.
 */

/**
 * <p>
 * Title:用户登录req
 * </p>
 * <p>
 * Description:登录所有信息:用户名,密码,验证码,记住密码
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
public class LoginUserReq {
    private String username;
    private String password;
    private String verifyCode;
    private Boolean rememberMe;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getVerifyCode() {
        return verifyCode;
    }

    public void setVerifyCode(String verifyCode) {
        this.verifyCode = verifyCode;
    }

    public Boolean getRememberMe() {
        return rememberMe;
    }

    public void setRememberMe(Boolean rememberMe) {
        this.rememberMe = rememberMe;
    }
}
