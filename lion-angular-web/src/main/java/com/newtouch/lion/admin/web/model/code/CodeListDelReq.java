package com.newtouch.lion.admin.web.model.code;

/**
 * Created by Administrator on 2016/4/15.
 */
public class CodeListDelReq {
    /** 通用编码列表id */
    private Long id;

    /** 编码值 */
    private String codeValue;
    /** 编码中文名称 */
    private String nameZh;
    /** 编码英文名称 */
    private String nameEn;
    /** 编码排序 */
    private int sortNo;
    /** 是否可编辑 */
    private Boolean editable;
    /** 编码类型对象 */
    private String codeType;
    /** 默认选择 */
    private Boolean selected;
    /** 编辑类型ID */
    private Long codeTypeId;

    private Long[] ids;

    public Long[] getIds() {
        return ids;
    }

    public void setIds(Long[] ids) {
        this.ids = ids;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCodeValue() {
        return codeValue;
    }

    public void setCodeValue(String codeValue) {
        this.codeValue = codeValue;
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

    public int getSortNo() {
        return sortNo;
    }

    public void setSortNo(int sortNo) {
        this.sortNo = sortNo;
    }

    public Boolean getEditable() {
        return editable;
    }

    public void setEditable(Boolean editable) {
        this.editable = editable;
    }

    public String getCodeType() {
        return codeType;
    }

    public void setCodeType(String codeType) {
        this.codeType = codeType;
    }

    public Boolean getSelected() {
        return selected;
    }

    public void setSelected(Boolean selected) {
        this.selected = selected;
    }

    public Long getCodeTypeId() {
        return codeTypeId;
    }

    public void setCodeTypeId(Long codeTypeId) {
        this.codeTypeId = codeTypeId;
    }
}
