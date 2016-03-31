package com.newtouch.lion.admin.web.model.jvmmonitor;

import com.newtouch.lion.admin.web.model.query.QueryReq;

/**
 * <p>
 * Title:
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Copyright: Copyright (c) 2014
 * </p>
 * <p>
 * Company: NewTouch
 * </p>
 *
 * @author ZhangYaKe
 * @version 1.0
 */
public class JvmMonitorReq extends QueryReq{
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
