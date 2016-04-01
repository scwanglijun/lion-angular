package com.newtouch.lion.admin.web.controller.account;

import com.newtouch.lion.admin.web.model.user.LoginUserReq;
import com.newtouch.lion.admin.web.model.user.UserGetReq;
import com.newtouch.lion.model.system.User;
import com.newtouch.lion.service.system.UserService;
import com.newtouch.lion.webtrans.trans.Trans;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class AccountController{
    @Autowired
    private UserService userService;
    @Trans(value = "system.account.profile")
    public UserGetReq Account(LoginUserReq userReq){
        //查找登录的用户
        User user = userService.doFindByUserName(userReq.getUsername());
        UserGetReq userGetReq = new UserGetReq();
        userGetReq.setRealnameZh(user.getRealnameZh());
        userGetReq.setRealnameEn(user.getRealnameEn());
        userGetReq.setTelephone(user.getTelephone());
        userGetReq.setFax(user.getFax());
        userGetReq.setOfficePhone(user.getOfficePhone());
        userGetReq.setPostcode(user.getPostcode());
        userGetReq.setLocation(user.getLocation());
        userGetReq.setDescription(user.getDescription());
        userGetReq.setPassword(user.getPassword());
        return userGetReq;
    }
}
