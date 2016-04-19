package com.newtouch.lion.admin.web.model.icon;

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
public class IconGetResp {
    /***
     * 图标ID
     */
    private Long id;
    /** 图标类型 */
    private String iconType;
    /** 图标类名*/
    private String iconClass;
    /** 图标图片 */
    private String iconImage;
    //创建时间
    private String createdDataFormatter;
    //更新时间
    private String updatedDataFormatter;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getIconType() {
        return iconType;
    }

    public void setIconType(String iconType) {
        this.iconType = iconType;
    }

    public String getIconClass() {
        return iconClass;
    }

    public void setIconClass(String iconClass) {
        this.iconClass = iconClass;
    }

    public String getIconImage() {
        return iconImage;
    }

    public void setIconImage(String iconImage) {
        this.iconImage = iconImage;
    }

    public String getCreatedDataFormatter() {
        return createdDataFormatter;
    }

    public void setCreatedDataFormatter(String createdDataFormatter) {
        this.createdDataFormatter = createdDataFormatter;
    }

    public String getUpdatedDataFormatter() {
        return updatedDataFormatter;
    }

    public void setUpdatedDataFormatter(String updatedDataFormatter) {
        this.updatedDataFormatter = updatedDataFormatter;
    }
}
