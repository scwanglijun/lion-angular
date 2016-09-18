package com.newtouch.lion.web.shiro.service;

import com.newtouch.lion.model.system.User;
import com.newtouch.lion.service.system.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Created by wanglijun on 16/5/27.
 */
@Component
public class ShiroUserServiceImpl implements ShiroUserService{


    @Autowired
    private UserService userService;


    /**
     * 判断用户是否存在
     *
     * @param userName 用户名
     * @param password 未加密的密码
     * @return 是否存在
     */
    @Override
    public boolean userExist(String userName, String password) {
        return false;
    }

    /**
     * 获取用户的角色名称集合
     *
     * @param userName 用户名
     * @return 用户角色列表
     */
    @Override
    public Set<String> getUserRoleNames(String userName) {
        return null;
    }

    /**
     * 获取用户的权限列表
     *
     * @param userName 用户名
     * @return 用户权限列表
     */
    @Override
    public Set<String> getUserPermission(String userName) {
        return null;
    }

    /**
     * 获取系统需要拦截的URL列表，以及对应的拦截策略。示例以及默认值：
     * map.put("/login.do","anon"); --这个不需要登录就可以访问
     * map.put("/logout.do","anon");
     * map.put("/api.do", "authc"); --这个需要登录才能访问
     *
     * @return
     */
    @Override
    public Map<String, String> getDefinitionMap() {
        Map<String, String> maps = new HashMap<String, String>();
        return maps;
    }

    /**
     * 获取用户的额外属性
     *
     * @param userName 用户名
     * @return 用户的额外属性, 可以用于用户登录后返回到前端
     */
    @Override
    public Map<String, String> getUserAttr(String userName) {
        User user=userService.doFindByUserName(userName);
        Map<String,String> userMap=new HashMap<>();
        userMap.put("username",user.getUsername());
        userMap.put("realName",user.getRealnameEn());
        userMap.put("image",user.getImage());
        userMap.put("departmentId",String.valueOf(user.getDepartment().getDepartment().getId()));
        userMap.put("departmentName",String.valueOf(user.getDepartment().getNameZh()));
        return userMap;
    }
}
