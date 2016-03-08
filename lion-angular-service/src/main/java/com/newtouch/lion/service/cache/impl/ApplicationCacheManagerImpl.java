package com.newtouch.lion.service.cache.impl;

import com.newtouch.lion.model.cache.CacheManagerModel;
import com.newtouch.lion.model.system.CodeList;
import com.newtouch.lion.service.cache.AbstractApplicationCacheManager;
import com.newtouch.lion.service.system.CodeListService;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * 缓存查询类,可删除缓存
 * Created by wanglijun on 16/1/5.
 */
@Component
public class ApplicationCacheManagerImpl  extends AbstractApplicationCacheManager{

    private static  final String 	CAHCE_LIST="CAHCE_LIST";

    private static final Logger logger= LoggerFactory.getLogger(ApplicationCacheManagerImpl.class);

    @Autowired
    private CodeListService codeListService;


    public List<CacheManagerModel> findCacheMangers() {
       List<CodeList> codeLists=codeListService.doFindCodeListByCodeTypeNameEn(CAHCE_LIST);
       List<CacheManagerModel> list=new ArrayList<CacheManagerModel>();
       for(CodeList codeList:codeLists) {
           logger.info("{}",codeList.getCodeValue());
           list.add(this.findCacheManager(codeList.getCodeValue()));
       }
        return list;
    }


    public void clear(String ehcacheName) {
        logger.warn("clear cache:{}",ehcacheName);
        CacheManager cacheManager =CacheManager.getCacheManager(ehcacheName);
        cacheManager.clearAll();
    }

    public void clear(String ehcacheName,String cacheName) {
        logger.warn("clear:{},{}",ehcacheName,cacheName);
        CacheManager cacheManager =CacheManager.getCacheManager(ehcacheName);
        Cache cache=cacheManager.getCache(cacheName);
        cache.removeAll();
    }
}
