package com.newtouch.lion.admin.web.model.hibernatemonitor;

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
public class HibernateMonitorReq extends QueryReq {
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
