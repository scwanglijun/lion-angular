package com.newtouch.lion.admin.web.model.account;

import com.newtouch.lion.admin.web.model.query.QueryReq;

/**
 * Created by Administrator on 2016/4/5.
 */
public class ProfileEditPwdGetReq extends QueryReq {
    //旧密码
    private String oldpassword;
    //新密码
    private String password;
    //确认密码
    private String confiredpassword;

    public String getOldpassword() {
        return oldpassword;
    }

    public void setOldpassword(String oldpassword) {
        this.oldpassword = oldpassword;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfiredpassword() {
        return confiredpassword;
    }

    public void setConfiredpassword(String confiredpassword) {
        this.confiredpassword = confiredpassword;
    }
}
