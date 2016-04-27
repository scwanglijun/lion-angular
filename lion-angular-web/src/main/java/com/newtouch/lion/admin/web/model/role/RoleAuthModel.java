package com.newtouch.lion.admin.web.model.role;

import java.util.ArrayList;
import java.util.List;

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
public class RoleAuthModel {

    /**授权对象ID*/
    private Long id;
    /**目标授权IDS对象*/
    private List<Long> auths=new ArrayList<Long>();
    /**已选择授权目标IDS对象*/
    private List<Long> selecteds=new ArrayList<Long>();

    public RoleAuthModel() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Long> getAuths() {
        return auths;
    }

    public void setAuths(List<Long> auths) {
        this.auths = auths;
    }

    public List<Long> getSelecteds() {
        return selecteds;
    }

    public void setSelecteds(List<Long> selecteds) {
        this.selecteds = selecteds;
    }
}
