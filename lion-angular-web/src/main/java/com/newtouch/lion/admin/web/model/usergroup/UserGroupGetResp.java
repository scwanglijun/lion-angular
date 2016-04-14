package com.newtouch.lion.admin.web.model.usergroup;

import com.newtouch.lion.admin.web.model.query.QueryReq;

/**
 * Created by Administrator on 2016/4/14.
 */
public class UserGroupGetResp extends QueryReq{
    /**用户ID*/
    private long id;

    /**用户组名称(中文)*/
    private String realnameZh;
    /**用户组名称(英文)*/
    private String realnameEn;
    /**描述*/
    private String description;
    /**可编辑*/
    private Boolean editable;
    /**创建时间*/
    private String createdDate;
    /**更新时间*/
    private String updatedDate;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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
