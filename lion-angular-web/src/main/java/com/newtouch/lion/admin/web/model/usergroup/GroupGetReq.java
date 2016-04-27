package com.newtouch.lion.admin.web.model.usergroup;

import com.newtouch.lion.admin.web.model.query.QueryReq;

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
public class GroupGetReq extends QueryReq {
    /***
     * 用户组ID
     */
    private Long id;
    /** 用户组名称－中文 */
    private String nameZh;
    /** 用户组名称－英文 */
    private String nameEn;
    /** 是否可编辑 */
    private Boolean editable;
    /** 用户组描述 */
    private String description;

    public GroupGetReq() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNameZh() {
        return nameZh;
    }

    public void setNameZh(String nameZh) {
        this.nameZh = nameZh;
    }

    public String getNameEn() {
        return nameEn;
    }

    public void setNameEn(String nameEn) {
        this.nameEn = nameEn;
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
