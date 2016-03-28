package com.newtouch.lion.admin.web.model.application;/**
 * Created by Zhangyake on 3/25/16.
 */

import com.newtouch.lion.admin.web.model.query.QueryReq;
import com.newtouch.lion.model.system.Group;
import com.newtouch.lion.model.system.Resource;
import com.newtouch.lion.model.system.User;

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
 * @author Zhangyake
 * @version 1.0
 */
public class ApplicationPropertiesGetReq extends QueryReq{
    /** 系统应用ID */
    private Long id;

    /** 应用名称 */
    private String appId;
    /** 配置项-key */
    private String key;
    /** 配置项-value */
    private String value;
    /** 配置项描述 */
    private String description;

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
}
