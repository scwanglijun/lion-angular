package com.newtouch.lion.admin.web.model.codetype;

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
 * Company: NewTouch
 * </p>
 *
 * @author ZhangYake
 * @version 1.0
 */
public class CodeTypeDelReq extends QueryReq {
    /** 类型id */
    private Long id;
    /** 类型 */
    private String type;
    /** 中文名称 */
    private String nameZh;
    /** 英文名称 */
    private String nameEn;
    /** 是否可编辑 */
    private Boolean editable;
    /** 编码的值长度约束 */
    private Integer codeLenLimit;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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

    public Integer getCodeLenLimit() {
        return codeLenLimit;
    }

    public void setCodeLenLimit(Integer codeLenLimit) {
        this.codeLenLimit = codeLenLimit;
    }
}
