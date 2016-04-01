package com.newtouch.lion.admin.web.model.hibernatemonitor;

import org.hibernate.SessionFactory;
import org.hibernate.stat.Statistics;

import java.util.Map;
import java.util.Properties;

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
public class HibernateMonitorResp {

    private Statistics statistics;
    private Long upSeconds;
    private SessionFactory sessionFactory;
    private double secondLevelCacheHitPercent;
    private Map<String, Object> properties;
    private Long usedMemory;
    private Long maxMemory;
    private int totalMemorySize;
    private int totalMemoryCount;
    private int totalDiskCount;

    public Statistics getStatistics() {
        return statistics;
    }

    public void setStatistics(Statistics statistics) {
        this.statistics = statistics;
    }

    public Long getUpSeconds() {
        return upSeconds;
    }

    public void setUpSeconds(Long upSeconds) {
        this.upSeconds = upSeconds;
    }

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public double getSecondLevelCacheHitPercent() {
        return secondLevelCacheHitPercent;
    }

    public void setSecondLevelCacheHitPercent(double secondLevelCacheHitPercent) {
        this.secondLevelCacheHitPercent = secondLevelCacheHitPercent;
    }

    public Map<String, Object> getProperties() {
        return properties;
    }

    public void setProperties(Map<String, Object> properties) {
        this.properties = properties;
    }

    public Long getUsedMemory() {
        return usedMemory;
    }

    public void setUsedMemory(Long usedMemory) {
        this.usedMemory = usedMemory;
    }

    public Long getMaxMemory() {
        return maxMemory;
    }

    public void setMaxMemory(Long maxMemory) {
        this.maxMemory = maxMemory;
    }

    public int getTotalMemorySize() {
        return totalMemorySize;
    }

    public void setTotalMemorySize(int totalMemorySize) {
        this.totalMemorySize = totalMemorySize;
    }

    public int getTotalMemoryCount() {
        return totalMemoryCount;
    }

    public void setTotalMemoryCount(int totalMemoryCount) {
        this.totalMemoryCount = totalMemoryCount;
    }

    public int getTotalDiskCount() {
        return totalDiskCount;
    }

    public void setTotalDiskCount(int totalDiskCount) {
        this.totalDiskCount = totalDiskCount;
    }
}
