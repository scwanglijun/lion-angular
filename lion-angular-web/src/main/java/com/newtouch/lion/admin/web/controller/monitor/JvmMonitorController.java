package com.newtouch.lion.admin.web.controller.monitor;

import com.newtouch.lion.admin.web.model.jvmmonitor.JvmMonitorReq;
import com.newtouch.lion.admin.web.model.jvmmonitor.JvmMonitorResp;
import com.newtouch.lion.service.monitor.JvmMonitorService;
import com.newtouch.lion.webtrans.trans.Trans;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

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
@Controller
public class JvmMonitorController {

    @Autowired
    private JvmMonitorService jvmMontiorService;

    /***
     * JVM Runtime监控信息及操作信息
     * @param req
     * @return
     */
    @Trans("system.jvm")
    public JvmMonitorResp jvmmonitor(JvmMonitorReq req){
        JvmMonitorResp jvmMonitorResp = new JvmMonitorResp();
        jvmMonitorResp.setRuntimeInfo(jvmMontiorService.getJvmRuntimeInfo());
        jvmMonitorResp.setOperatingSystemInfo(jvmMontiorService.getOperatingSystem());
        return jvmMonitorResp;
    }
    /***
     * JVM 内存信息
     * @param req
     * @return
     */
    @Trans("system.jvm.memory")
    public JvmMonitorResp jvmmemory(JvmMonitorReq req){
        JvmMonitorResp jvmMonitorResp = new JvmMonitorResp();
        jvmMonitorResp.setMemoryUsage(jvmMontiorService.getMemory());
        return jvmMonitorResp;
    }

    /***
     * JVM 线程信息
     * @param req
     * @return
     */
    @Trans("system.jvm.thread")
    public JvmMonitorResp jvmthread(JvmMonitorReq req){
        JvmMonitorResp jvmMonitorResp = new JvmMonitorResp();
        jvmMonitorResp.setThreadSummary(jvmMontiorService.getThreadInfo());
        return jvmMonitorResp;
    }

}
