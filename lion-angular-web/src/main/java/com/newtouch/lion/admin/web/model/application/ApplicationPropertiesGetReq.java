package com.newtouch.lion.admin.web.model.application;/**
 * Created by Zhangyake on 3/25/16.
 */

import com.newtouch.lion.admin.web.model.query.QueryReq;
import com.newtouch.lion.model.system.Group;
import com.newtouch.lion.model.system.Resource;
import com.newtouch.lion.model.system.User;
import org.hibernate.validator.constraints.Length;

import java.util.HashSet;
import java.util.Set;

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
public class ApplicationPropertiesGetReq extends QueryReq{
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
    /**创建时间*/
    private String createDateFormatter;
    /**更新时间*/
    private String updateDateFormatter;

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

    public String getCreateDateFormatter() {
        return createDateFormatter;
    }

    public void setCreateDateFormatter(String createDateFormatter) {
        this.createDateFormatter = createDateFormatter;
    }

    public String getUpdateDateFormatter() {
        return updateDateFormatter;
    }

    public void setUpdateDateFormatter(String updateDateFormatter) {
        this.updateDateFormatter = updateDateFormatter;
    }
}
