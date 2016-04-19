package com.newtouch.lion.admin.web.model.application;/**
 * Created by jovi on 3/28/16.
 */

import com.newtouch.lion.admin.web.model.query.QueryReq;
import org.hibernate.validator.constraints.Length;

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
 * Company: XiQing
 * </p>
 *
 * @author ZhangYake
 * @version 1.0
 */
public class ApplicationPropertiesDelReq extends QueryReq{
    /** 系统应用ID */
    private Long id;

    /** 应用名称 */
    @Length(max = 20,min = 0,message = "{sys.applicationProperty.form.appId.length.message}")
    private String appId;
    /** 配置项-key */
    @Length(max = 60,min = 2, message = "{sys.applicationProperty.form.key.length.message}")
    private String key;
    /** 配置项-value */
    @Length(max = 120, min = 2, message = "{sys.applicationProperty.form.value.length.message}")
    private String value;
    /** 配置项描述 */
    @Length(min = 0, max = 120, message = "{sys.applicationProperty.form.description.length.message}")
    private String description;
    private Long[] ids;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
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

    public Long[] getIds() {
        return ids;
    }

    public void setIds(Long[] ids) {
        this.ids = ids;
    }
}
