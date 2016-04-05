package com.newtouch.lion.admin.web.model.user;

/**
 * Created by Administrator on 2016/4/5.
 */
public class UserGetResp {
    /**用户ID*/
    private String id;
    /**用户登入名*/
    private String username;
    /**用户登入密码*/
    private String password;
    /**用户联系方式*/
    private String telephone;
    /**用户联系方式（手机）*/
    private String mobile;
    /**用户E-mail*/
    private String email;
    /**用户名称(中文)*/
    private String realnameZh;
    /**用户名称(英文)*/
    private String realnameEn;
    /**描述*/
    private String description;
    /**可编辑*/
    private String editable;
    /**创建时间*/
    private String createdDate;
    /**更新时间*/
    private String updatedDate;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

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

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRealnameZh() {
        return realnameZh;
    }

    public void setRealnameZh(String realnameZh) {
        this.realnameZh = realnameZh;
    }

    public String getRealnameEn() {
        return realnameEn;
    }

    public void setRealnameEn(String realnameEn) {
        this.realnameEn = realnameEn;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getEditable() {
        return editable;
    }

    public void setEditable(String editable) {
        this.editable = editable;
    }

    public String getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }

    public String getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(String updatedDate) {
        this.updatedDate = updatedDate;
    }
}
