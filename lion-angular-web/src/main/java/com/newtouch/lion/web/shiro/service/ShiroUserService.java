package com.newtouch.lion.web.shiro.service;

import java.util.Map;
import java.util.Set;

/**
 * Created by wanglijun on 16/5/27.
 */
public interface ShiroUserService {

    /**
     * 判断用户是否存在
     * @param userName 用户名
     * @param password 未加密的密码
     * @return 是否存在
     */
    boolean userExist(String userName, String password);

    /**
     * 获取用户的角色名称集合
     * @param userName 用户名
     * @return 用户角色列表
     */
    Set<String> getUserRoleNames(String userName);

    /**
     * 获取用户的权限列表
     * @param userName 用户名
     * @return 用户权限列表
     */
    Set<String> getUserPermission(String userName);

    /**
     * 获取系统需要拦截的URL列表，以及对应的拦截策略。示例以及默认值：
     *  map.put("/login.do","anon"); --这个不需要登录就可以访问
     map.put("/logout.do","anon");
     map.put("/api.do", "authc"); --这个需要登录才能访问
     * @return
     */
    Map<String,String> getDefinitionMap();


    /**
     * 获取用户的额外属性
     * @param userName 用户名
     * @return 用户的额外属性,可以用于用户登录后返回到前端
     */
    Map<String,String> getUserAttr(String userName);
}
