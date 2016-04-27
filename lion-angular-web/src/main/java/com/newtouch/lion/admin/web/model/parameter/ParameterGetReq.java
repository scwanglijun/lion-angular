package com.newtouch.lion.admin.web.model.parameter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import com.newtouch.lion.admin.web.model.query.QueryReq;

/**
 * <p>
 * Title:
 * </p>
 * <p>
 * Description:Parameter
 * </p>
 * <p>
 * Copyright: Copyright (c) 2016
 * </p>
 * <p>
 * Company:NewTouch
 * </p>
 *
 * @author LiXiaoHao
 * @version 1.0
 */
public class ParameterGetReq extends QueryReq {

    public static final  String NAMEEN="nameEn";
    /**
     * @Fields id:参数序号
     */
    private Long id;
    /**
     * @Fields type：参数类型
     */
    @NotEmpty(message="{sys.parameter.form.type.missing.message}")
    private String type;

    /**@Fileds editable 是否可编辑*/
    private Boolean editable=Boolean.FALSE;
    /**
     * @Fields nameEn：英文名称
     */
    @NotNull(message="{sys.parameter.form.nameen.missing.message}")
    @Length(max=128,min=4,message="{sys.parameter.form.nameen.length.message}")
    private String nameEn;
    /**
     * @Fields nameZh：中文名称
     */
    @NotNull(message="{sys.parameter.form.namezh.missing.message}")
    @Length(max=128,min=4,message="{sys.parameter.form.namezh.length.message}")
    private String nameZh;
    /**
     * @Fields value:参数值
     */
    @NotNull(message="{sys.parameter.form.value.missing.message}")
    @Size(max=256,min=1,message="{sys.parameter.form.value.length.message}")
    private String value;
    /**
     * @Fields description:参数描述
     */
    @Length(min=0,max=512,message="{sys.parameter.form.description.length.message}")
    private String description;

    public ParameterGetReq() {
    }

    public static String getNAMEEN() {
        return NAMEEN;
    }

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

    public Boolean getEditable() {
        return editable;
    }

    public void setEditable(Boolean editable) {
        this.editable = editable;
    }

    public String getNameEn() {
        return nameEn;
    }

    public void setNameEn(String nameEn) {
        this.nameEn = nameEn;
    }

    public String getNameZh() {
        return nameZh;
    }

    public void setNameZh(String nameZh) {
        this.nameZh = nameZh;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
