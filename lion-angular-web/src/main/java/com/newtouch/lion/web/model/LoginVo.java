package com.newtouch.lion.web.model;

/**
 * Created by wanglijun on 16/5/27.
 */
public class LoginVo {

    /**用户名*/
    private String username;
    /**密码*/
    private String password;
    /**验证码*/
    private String captchaCode;
    /**是否记录登录状态*/
    private Boolean rememberMe=Boolean.FALSE;

    public LoginVo() {
        super();
    }


    public String getCaptchaCode() {
        return captchaCode;
    }

    public void setCaptchaCode(String captchaCode) {
        this.captchaCode = captchaCode;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Boolean getRememberMe() {
        return rememberMe;
    }

    public void setRememberMe(Boolean rememberMe) {
        this.rememberMe = rememberMe;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }


    @Override
    public String toString() {
        return "LoginVo{" +
                "captchaCode='" + captchaCode + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", rememberMe=" + rememberMe +
                '}';
    }
}
