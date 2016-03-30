package com.newtouch.lion.admin.web.model.jvmmonitor;

import com.newtouch.lion.model.monitor.jvm.*;

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
public class JvmMonitorResp {

    private MemoryUsage memoryUsage;
    private OperatingSystemInfo operatingSystemInfo;
    private RuntimeInfo runtimeInfo;
    private ThreadSummary threadSummary;
    private ThreadDetail threadDetail;

    public MemoryUsage getMemoryUsage() {
        return memoryUsage;
    }

    public void setMemoryUsage(MemoryUsage memoryUsage) {
        this.memoryUsage = memoryUsage;
    }

    public OperatingSystemInfo getOperatingSystemInfo() {
        return operatingSystemInfo;
    }

    public void setOperatingSystemInfo(OperatingSystemInfo operatingSystemInfo) {
        this.operatingSystemInfo = operatingSystemInfo;
    }

    public RuntimeInfo getRuntimeInfo() {
        return runtimeInfo;
    }

    public void setRuntimeInfo(RuntimeInfo runtimeInfo) {
        this.runtimeInfo = runtimeInfo;
    }

    public ThreadSummary getThreadSummary() {
        return threadSummary;
    }

    public void setThreadSummary(ThreadSummary threadSummary) {
        this.threadSummary = threadSummary;
    }

    public ThreadDetail getThreadDetail() {
        return threadDetail;
    }

    public void setThreadDetail(ThreadDetail threadDetail) {
        this.threadDetail = threadDetail;
    }
}
