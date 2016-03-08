package com.newtouch.lion.admin.web.model.monitor;

import java.io.Serializable;

/**
 * Created by wanglijun on 16/1/6.
 */
public class CacheVo   implements Serializable {

    private static final long serialVersionUID = 8872718135672281459L;

    private String ehcacheName;

    private String cacheName;

    public CacheVo() {
        super();
    }

    public CacheVo(String ehcacheName, String cacheName) {
        this.ehcacheName = ehcacheName;
        this.cacheName = cacheName;
    }


    public String getEhcacheName() {
        return ehcacheName;
    }

    public void setEhcacheName(String ehcacheName) {
        this.ehcacheName = ehcacheName;
    }

    public String getCacheName() {
        return cacheName;
    }

    public void setCacheName(String cacheName) {
        this.cacheName = cacheName;
    }

    @Override
    public String toString() {
        return "CacheVo{" +
                "ehcacheName='" + ehcacheName + '\'' +
                ", cacheName='" + cacheName + '\'' +
                '}';
    }
}
