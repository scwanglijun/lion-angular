package com.newtouch.lion.admin.web.model.user;

/**
 * Created by Administrator on 2016/4/1.
 */
public class UserEditReq {
    /*** USERID */
    private Long id;
    /** 登录用户名 */
    private String username;
    private String employeeCode;
    /** 用户真实姓名－中文 */
    private String realnameZh;
    /** 用户真实姓名－英文 */
    private String realnameEn;
    private String authtype;
    /** 联系电话 */
    private String telephone;
    /** 联系电话－手机 */
    private String mobile;
    /** E-mail */
    private String email;
    /** 是否可编辑 */
    private Boolean editable;
    /** 用户描述 */
    private String description;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmployeeCode() {
        return employeeCode;
    }

    public void setEmployeeCode(String employeeCode) {
        this.employeeCode = employeeCode;
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

    public String getAuthtype() {
        return authtype;
    }

    public void setAuthtype(String authtype) {
        this.authtype = authtype;
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

    public Boolean getEditable() {
        return editable;
    }

    public void setEditable(Boolean editable) {
        this.editable = editable;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
