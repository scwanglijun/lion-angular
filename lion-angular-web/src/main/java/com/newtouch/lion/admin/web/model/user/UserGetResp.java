package com.newtouch.lion.admin.web.model.user;

import com.newtouch.lion.admin.web.model.query.QueryReq;
import com.newtouch.lion.model.system.Department;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * Created by Administrator on 2016/4/5.
 */
public class UserGetResp {
    /**用户ID*/
    private long id;
    /**用户编号*/
    private String employeeCode;
    /**用户登入名*/
    private String username;
    /**用户名称(中文)*/
    private String realnameZh;
    /**用户名称(英文)*/
    private String realnameEn;
    /**描述*/
    private String description;
    /**可编辑*/
    private Boolean editable;
    /** 登录密码 */
    private String password;
    /** 密码提示语 */
    private String passwordHint;
    /** 性别 */
    private int gender;
    /** 用户类型EMPLOYEE, SUPPLIER, DEALER可自行扩展 */
    private String usertype;
    /** 认证类型 LDAP, DB, DUMMY */
    private String authtype;
    /** 联系电话 */
    private String telephone;
    /** 联系电话－手机 */
    private String mobile;
    /** E-mail */
    private String email;
    /** 联系电话－办公室 */
    private String officePhone;
    /** 传真 */
    private String fax;
    /** 邮编 */
    private String postcode;
    /** 办公室位置 */
    private String location;
    /** 账户是否有效 */
    private String accountExpired;
    /** 账户是否被锁定 */
    private String accountLocked;
    /** 账户密码是否有效 */
    private Boolean credentialExpired;
    /** 账户密码有效日期 */
    private String credentialExpiredDate;
    /** 账户有效日期 */
    private String accountExpiredDate;
    /**用户头像*/
    private String image;
    /** 该用户部门 */
    private String department;

    public String getEmployeeCode() {
        return employeeCode;
    }

    public void setEmployeeCode(String employeeCode) {
        this.employeeCode = employeeCode;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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

    public Boolean getEditable() {
        return editable;
    }

    public void setEditable(Boolean editable) {
        this.editable = editable;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPasswordHint() {
        return passwordHint;
    }

    public void setPasswordHint(String passwordHint) {
        this.passwordHint = passwordHint;
    }

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public String getUsertype() {
        return usertype;
    }

    public void setUsertype(String usertype) {
        this.usertype = usertype;
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

    public String getOfficePhone() {
        return officePhone;
    }

    public void setOfficePhone(String officePhone) {
        this.officePhone = officePhone;
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public String getPostcode() {
        return postcode;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getAccountExpired() {
        return accountExpired;
    }

    public void setAccountExpired(String accountExpired) {
        this.accountExpired = accountExpired;
    }

    public String getAccountLocked() {
        return accountLocked;
    }

    public void setAccountLocked(String accountLocked) {
        this.accountLocked = accountLocked;
    }

    public Boolean getCredentialExpired() {
        return credentialExpired;
    }

    public void setCredentialExpired(Boolean credentialExpired) {
        this.credentialExpired = credentialExpired;
    }

    public String getCredentialExpiredDate() {
        return credentialExpiredDate;
    }

    public void setCredentialExpiredDate(String credentialExpiredDate) {
        this.credentialExpiredDate = credentialExpiredDate;
    }

    public String getAccountExpiredDate() {
        return accountExpiredDate;
    }

    public void setAccountExpiredDate(String accountExpiredDate) {
        this.accountExpiredDate = accountExpiredDate;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }
}
