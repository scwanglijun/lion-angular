package com.newtouch.lion.admin.web.controller.hibernatemonitor;

import com.newtouch.lion.admin.web.model.hibernatemonitor.HibernateMonitorReq;
import com.newtouch.lion.admin.web.model.hibernatemonitor.HibernateMonitorResp;
import com.newtouch.lion.hibernate.HibernateUtils;
import com.newtouch.lion.model.monitor.jvm.MemoryUsage;
import com.newtouch.lion.service.monitor.HibernateMonitorService;
import com.newtouch.lion.service.monitor.JvmMonitorService;
import com.newtouch.lion.webtrans.trans.Trans;
import org.hibernate.stat.SecondLevelCacheStatistics;
import org.hibernate.stat.Statistics;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import javax.persistence.EntityManager;
import java.util.Date;
import java.util.Map;
import java.util.TreeMap;

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
 * @author ZhangYake
 * @version 1.0
 */
@Controller
public class HibernateMonitorController {

    /***
     * Hibernate 监控服务类
     */
    @Autowired
    private HibernateMonitorService hibernateMontiorService;
    /**JVM监控服务*/
    @Autowired
    private JvmMonitorService jvmMontiorService;

    @Trans("system.monitor.hibernatemonitor")
    public HibernateMonitorResp index(HibernateMonitorReq req){
        EntityManager em = hibernateMontiorService.getEntityManager();
        Statistics statistics = HibernateUtils.getSessionFactory(em).getStatistics();
        HibernateMonitorResp resp = new HibernateMonitorResp();
        resp.setStatistics(statistics);

        Date startDate = new Date(statistics.getStartTime());
        Date nowDate = new Date();
        long upSeconds = (nowDate.getTime() - startDate.getTime()) / 1000;
        resp.setUpSeconds(upSeconds);
       /* resp.setSessionFactory(HibernateUtils.getSessionFactory(em));

        //配置信息
        Map<String, Object> properties = new TreeMap<String, Object>(em.getEntityManagerFactory().getProperties());
        resp.setProperties(properties);*/

        //二级缓存总命中率
        long secondLevelCacheCount=statistics.getSecondLevelCacheHitCount()+statistics.getSecondLevelCacheMissCount();
        secondLevelCacheCount=secondLevelCacheCount>0?secondLevelCacheCount:1;
        double  secondLevelCacheHitPercent=(statistics.getSecondLevelCacheHitCount()*1.0)/secondLevelCacheCount;
        resp.setSecondLevelCacheHitPercent(secondLevelCacheHitPercent);
        this.setMemoryInfo(resp,statistics);
        return null;
    }
    private void setMemoryInfo(HibernateMonitorResp resp,Statistics statistics) {
        //系统的
        MemoryUsage heapMemoryUsage = jvmMontiorService.getMemory();
        resp.setUsedMemory(heapMemoryUsage.getUsed());
        resp.setMaxMemory(heapMemoryUsage.getMax());

        //二级缓存的
        String[] secondLevelCacheRegionNames = statistics.getSecondLevelCacheRegionNames();

        int totalMemorySize = 0;
        int totalMemoryCount = 0;
        int totalDiskCount = 0;

        for(String secondLevelCacheRegionName : secondLevelCacheRegionNames) {
            SecondLevelCacheStatistics secondLevelCacheStatistics=statistics.getSecondLevelCacheStatistics(secondLevelCacheRegionName);
            totalMemorySize += secondLevelCacheStatistics.getSizeInMemory();
            totalMemoryCount += secondLevelCacheStatistics.getElementCountInMemory();
            totalDiskCount += secondLevelCacheStatistics.getElementCountOnDisk();
        }
        resp.setTotalMemorySize(totalMemorySize);
        resp.setTotalMemoryCount(totalMemoryCount);
        resp.setTotalDiskCount(totalDiskCount);
    }
}
