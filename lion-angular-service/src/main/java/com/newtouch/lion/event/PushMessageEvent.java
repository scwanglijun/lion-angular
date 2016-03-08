package com.newtouch.lion.event;


import com.newtouch.lion.model.system.Resource;

/**
 * Created by wanglijun on 16/1/6.
 */
public interface PushMessageEvent {

    /**
     * 更新Shiro的资源信息
     * @param resource
     */
    public void  pushResource(Resource resource);

}
