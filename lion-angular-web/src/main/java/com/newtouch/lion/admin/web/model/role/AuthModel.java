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
public class AuthModel {

    /**授权对象ID*/
    private Long id;
    /**目标授权IDS对象*/
    private List<Long> auths=new ArrayList<Long>();
    /**已选择授权目标IDS对象*/
    private List<Long> selecteds=new ArrayList<Long>();
    /***
     * 默认
     */
    public AuthModel() {
        super();
    }



    /**
     * @param id
     * @param auths
     * @param selecteds
     */
    public AuthModel(Long id, List<Long> auths, List<Long> selecteds) {
        super();
        this.id = id;
        this.auths = auths;
        this.selecteds = selecteds;
    }



    /**
     * @return the id
     */
    public Long getId() {
        return id;
    }


    /**
     * @param id the id to set
     */
    public void setId(Long id) {
        this.id = id;
    }




    /**
     * @return the auths
     */
    public List<Long> getAuths() {
        return auths;
    }


    /**
     * @param auths the auths to set
     */
    public void setAuths(List<Long> auths) {
        this.auths = auths;
    }


    /**
     * @return the selecteds
     */
    public List<Long> getSelecteds() {
        return selecteds;
    }


    /**
     * @param selecteds the selecteds to set
     */
    public void setSelecteds(List<Long> selecteds) {
        this.selecteds = selecteds;
    }
}
