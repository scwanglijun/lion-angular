package com.newtouch.lion.web.shiro.listener;

import com.google.common.eventbus.Subscribe;
import com.newtouch.lion.model.system.Resource;
import com.newtouch.lion.web.shiro.chain.ShiroFilterChainDefinitionManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class ShiroResourceListener {

    private static final Logger logger = LoggerFactory.getLogger(ShiroResourceListener.class);


    private ShiroFilterChainDefinitionManager shiroFilterChainDefinitionManager;

    @Subscribe
    public void listenerResouce(Resource resource) {
        logger.info("更新权限列表.......");
        shiroFilterChainDefinitionManager.updatePermission();
        logger.info("更新权限列表.......");
    }

    public void setShiroFilterChainDefinitionManager(ShiroFilterChainDefinitionManager shiroFilterChainDefinitionManager) {
        this.shiroFilterChainDefinitionManager = shiroFilterChainDefinitionManager;
    }
}
