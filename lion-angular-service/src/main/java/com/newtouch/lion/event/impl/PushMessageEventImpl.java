package com.newtouch.lion.event.impl;

import com.google.common.eventbus.EventBus;
import com.newtouch.lion.event.PushMessageEvent;
import com.newtouch.lion.model.system.Resource;
import com.newtouch.lion.web.shiro.chain.ShiroFilterChainDefinitionManager;
import com.newtouch.lion.web.shiro.listener.ShiroResourceListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by wanglijun on 16/1/6.
 */
@Service
public class PushMessageEventImpl implements InitializingBean,PushMessageEvent{

    private static final Logger logger= LoggerFactory.getLogger(PushMessageEvent.class);


    private EventBus  bus=new EventBus("lion.admin");


    @Autowired
    private ShiroFilterChainDefinitionManager shiroFilterChainDefinitionManager;

    @Override
    public void afterPropertiesSet() throws Exception {
        logger.info("EventBus 启动.....");
        ShiroResourceListener resourceListener=new ShiroResourceListener();
        resourceListener.setShiroFilterChainDefinitionManager(shiroFilterChainDefinitionManager);
        this.bus.register(resourceListener);
    }

    @Override
    public void pushResource(Resource resource) {
         this.bus.post(resource);
    }


}
