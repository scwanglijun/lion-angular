package com.newtouch.lion.admin.web.controller.account;

import ch.qos.logback.core.db.dialect.DBUtil;
import com.newtouch.lion.admin.web.model.account.ProfileReq;
import com.newtouch.lion.admin.web.model.account.ProfileResq;
import com.newtouch.lion.model.system.User;
import com.newtouch.lion.service.system.UserService;
import com.newtouch.lion.web.shiro.model.LoginUser;
import com.newtouch.lion.webtrans.trans.Trans;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class AccountController{
    @Autowired
    private UserService userService;
    @Trans("system.account.profile")
    public ProfileResq Account(ProfileReq profileReq){
        //通过id查找账户的个人资料
        User user = userService.doFindById(profileReq.getId());
        ProfileResq profileResq = new ProfileResq();
        profileResq.setRealnameZh(user.getRealnameZh());
        profileResq.setRealnameEn(user.getRealnameEn());
        profileResq.setMobile(user.getMobile());
        profileResq.setTelephone(user.getTelephone());
        profileResq.setFax(user.getFax());
        profileResq.setOfficePhone(user.getOfficePhone());
        profileResq.setPostcode(user.getPostcode());
        profileResq.setLocation(user.getLocation());
        profileResq.setDescription(user.getDescription());
        profileResq.setPassword(user.getPassword());
        return profileResq;
    }
    /*@Trans("system.account.profile.edit")
    public ProfileResq edit(){
        return null;
    }*/
}
