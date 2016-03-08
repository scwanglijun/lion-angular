/*
* Copyright (c)  2015, Newtouch
* All rights reserved. 
*
* $id: HibernateMontiorController.java 9552 2015年2月28日 下午5:26:51 WangLijun$
*/
package com.newtouch.lion.admin.web.controller.monitor;

import com.newtouch.lion.hibernate.HibernateUtils;
import com.newtouch.lion.model.monitor.jvm.MemoryUsage;
import com.newtouch.lion.service.monitor.HibernateMonitorService;
import com.newtouch.lion.service.monitor.JvmMonitorService;
import com.newtouch.lion.web.controller.AbstractController;
import org.hibernate.stat.SecondLevelCacheStatistics;
import org.hibernate.stat.Statistics;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.persistence.EntityManager;
import java.util.Date;
import java.util.Map;
import java.util.TreeMap;

/**
 * <p>
 * Title: Hibernate监控信息
 * </p>
 * <p>
 * Description: Hibernate监控信息
 * </p>
 * <p>
 * Copyright: Copyright (c) 2015
 * </p>
 * <p>
 * Company: Newtouch
 * </p>
 * 
 * @author WangLijun
 * @version 1.0
 */
@Controller
@RequestMapping("/monitor/hibernate")
public class HibernateMonitorController extends AbstractController{
	
	/***
	 * Hibernate 监控服务类
	 */
	@Autowired
	private HibernateMonitorService hibernateMontiorService;
	/**JVM监控服务*/
	@Autowired 
	private JvmMonitorService jvmMontiorService;
	/***
	 * Hibernate监控首页
	 */
	private static final String INDEX_RETRUN="/monitor/hibernate/index";
	
	@RequestMapping(value="/index",method=RequestMethod.GET)
	public String index(Model model){
		EntityManager em=hibernateMontiorService.getEntityManager();
		Statistics statistics = HibernateUtils.getSessionFactory(em).getStatistics();
		
	    model.addAttribute("statistics", statistics);
	   
	    Date startDate = new Date(statistics.getStartTime());
        Date nowDate = new Date();
        long upSeconds = (nowDate.getTime() - startDate.getTime()) / 1000;
        model.addAttribute("upSeconds", upSeconds);
        //Session的信息
        model.addAttribute("sessionFactory",HibernateUtils.getSessionFactory(em));
        //配置信息
        Map<String, Object> properties = new TreeMap<String, Object>(em.getEntityManagerFactory().getProperties());
        
        //二级缓存总命中率
        long secondLevelCacheCount=statistics.getSecondLevelCacheHitCount()+statistics.getSecondLevelCacheMissCount();
        secondLevelCacheCount=secondLevelCacheCount>0?secondLevelCacheCount:1;
        double  secondLevelCacheHitPercent=(statistics.getSecondLevelCacheHitCount()*1.0)/secondLevelCacheCount;
        model.addAttribute("secondLevelCacheHitPercent",secondLevelCacheHitPercent);
        //JPA配置
        model.addAttribute("properties", properties);
        //设置二级缓存内存使用情况
        this.setMemoryInfo(model, statistics);
        
		return INDEX_RETRUN;
	}
	
	 private void setMemoryInfo(Model model,Statistics statistics) {
	        //系统的
		    MemoryUsage heapMemoryUsage = jvmMontiorService.getMemory();
	  
	        model.addAttribute("usedMemory", heapMemoryUsage.getUsed());
	        model.addAttribute("maxMemory", heapMemoryUsage.getMax());

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

	        model.addAttribute("totalMemorySize", totalMemorySize);
	        model.addAttribute("totalMemoryCount", totalMemoryCount);
	        model.addAttribute("totalDiskCount", totalDiskCount);
	    }
}

	