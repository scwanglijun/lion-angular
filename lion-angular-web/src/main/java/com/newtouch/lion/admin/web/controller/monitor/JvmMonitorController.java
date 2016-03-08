/*
* Copyright (c)  2015, Newtouch
* All rights reserved. 
*
* $id: JvmMontiorController.java 9552 2015年2月28日 下午2:43:48 WangLijun$
*/
package com.newtouch.lion.admin.web.controller.monitor;

import com.newtouch.lion.model.monitor.jvm.MemoryUsage;
import com.newtouch.lion.model.monitor.jvm.OperatingSystemInfo;
import com.newtouch.lion.model.monitor.jvm.RuntimeInfo;
import com.newtouch.lion.model.monitor.jvm.ThreadSummary;
import com.newtouch.lion.service.monitor.JvmMonitorService;
import com.newtouch.lion.web.controller.AbstractController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * <p>
 * Title: JVM监控
 * </p>
 * <p>
 * Description: JVM监控
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
@RequestMapping("/monitor/jvm")
public class JvmMonitorController extends AbstractController{
	
	@Autowired
	private JvmMonitorService jvmMontiorService;	
	/****
	 * JVM监控
	 */
	private static final String INDEX_RETURN="/monitor/jvm/index";
	/**内存监控*/
	private static final String MEMORY_RETURN="/monitor/jvm/memory";
	/**JVM线程监控*/
	private static final String THREAD_RETURN="/monitor/jvm/thread";
	/***
	 * JVM Runtime监控信息及操作信息
	 * @param model
	 * @return
	 */
	@RequestMapping(value="index",method=RequestMethod.GET)
	public String index(Model model){
		RuntimeInfo runtimeModel=jvmMontiorService.getJvmRuntimeInfo();
		model.addAttribute("runtime", runtimeModel);		
		OperatingSystemInfo osInfo=jvmMontiorService.getOperatingSystem();
		model.addAttribute("osInfo",osInfo);
		return INDEX_RETURN;
	}
	/***
	 * JVM 内存信息
	 * @param model 
	 * @return 
	 */
	@RequestMapping(value="memory",method=RequestMethod.GET)
	public String memory(Model model){
		MemoryUsage memoryUsage=jvmMontiorService.getMemory();
		model.addAttribute("memory", memoryUsage);
		return  MEMORY_RETURN; 
	}
	
	/***
	 * JVM 线程信息
	 * @param model 
	 * @return 
	 */
	@RequestMapping(value="thread",method=RequestMethod.GET)
	public String thread(Model model){
		ThreadSummary  thread=jvmMontiorService.getThreadInfo();
		model.addAttribute("thread", thread);
		return  THREAD_RETURN; 
	}
}

	