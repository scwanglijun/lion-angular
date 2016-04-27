package com.newtouch.lion.admin.web.model.user;

import com.newtouch.lion.admin.web.model.query.QueryReq;
import com.newtouch.lion.model.system.Department;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * Created by Administrator on 2016/4/5.
 */
public class UserAddReq extends QueryReq {
    /**用户ID*/
    private Long id;
    /**用户编号*/
    @NotNull(message="${sys.user.departement.missing}")
    private Long departmentId;
    @Length(max=30,min=4,message="{sys.user.employeecode.length}")
    private String employeeCode;
    /**用户登入名*/
    @NotNull(message="${sys.user.username.missing.message}")
    @Length(max=30,min=4,message="{sys.user.username.length.message}")
    private String username;
    /**用户名称(中文)*/
    private String realnameZh;
    /**用户名称(英文)*/
    private String realnameEn;

    /**描述*/
    private String description;
    /**可编辑*/
    private Boolean editable;
    /**创建时间*/
    private String createdDateFormatter;
    /**更新时间*/
    private String updatedDateFormatter;
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
    private Boolean accountExpired=Boolean.FALSE;
    /** 账户是否被锁定 */
    private Boolean accountLocked=Boolean.FALSE;
    /** 账户密码是否有效 */
    private Boolean credentialExpired=Boolean.FALSE;
    /** 账户密码有效日期 */
    private Date credentialExpiredDate;
    /** 账户有效日期 */
    private String accountExpiredDate;
    /**用户头像*/
    private String image;
    /** 该用户部门 */
//    private Department department;

    public String getEmployeeCode() {
        return employeeCode;
    }

    public void setEmployeeCode(String employeeCode) {
        this.employeeCode = employeeCode;
    }

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

    public Long getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Long departmentId) {
        this.departmentId = departmentId;
    }

//    public Department getDepartment() {
//        return department;
//    }
//
//    public void setDepartment(Department department) {
//        this.department = department;
//    }

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

    public String getCreatedDateFormatter() {
        return createdDateFormatter;
    }

    public void setCreatedDateFormatter(String createdDateFormatter) {
        this.createdDateFormatter = createdDateFormatter;
    }

    public String getUpdatedDateFormatter() {
        return updatedDateFormatter;
    }

    public void setUpdatedDateFormatter(String updatedDateFormatter) {
        this.updatedDateFormatter = updatedDateFormatter;
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

    public Boolean getAccountExpired() {
        return accountExpired;
    }

    public void setAccountExpired(Boolean accountExpired) {
        this.accountExpired = accountExpired;
    }

    public Boolean getAccountLocked() {
        return accountLocked;
    }

    public void setAccountLocked(Boolean accountLocked) {
        this.accountLocked = accountLocked;
    }

    public Boolean getCredentialExpired() {
        return credentialExpired;
    }

    public void setCredentialExpired(Boolean credentialExpired) {
        this.credentialExpired = credentialExpired;
    }

    public Date getCredentialExpiredDate() {
        return credentialExpiredDate;
    }

    public void setCredentialExpiredDate(Date credentialExpiredDate) {
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
}
