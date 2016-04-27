package com.newtouch.lion.admin.web.controller.account;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.newtouch.lion.admin.web.model.account.ProfileEditGetReq;
import com.newtouch.lion.admin.web.model.account.ProfileEditGetResq;
import com.newtouch.lion.admin.web.model.account.ProfileEditImageReq;
import com.newtouch.lion.admin.web.model.account.ProfileEditImageResq;
import com.newtouch.lion.admin.web.model.account.ProfileEditPwdGetReq;
import com.newtouch.lion.admin.web.model.account.ProfileEditPwdGetResq;
import com.newtouch.lion.admin.web.model.account.ProfileReq;
import com.newtouch.lion.admin.web.model.account.ProfileResq;
import com.newtouch.lion.common.user.UserInfo;
import com.newtouch.lion.model.system.User;
import com.newtouch.lion.service.system.PasswordEncoderService;
import com.newtouch.lion.service.system.UserService;
import com.newtouch.lion.web.shiro.session.LoginSecurityUtil;
import com.newtouch.lion.webtrans.trans.Trans;

@Controller
public class AccountController{
    @Autowired
    private UserService userService;
    @Autowired
    private PasswordEncoderService passwordEncoderService;
    @Trans("system.account.profile")
    public ProfileResq Account(ProfileReq profileReq){
        UserInfo userInfo = LoginSecurityUtil.getUser();
        //通过id查找账户的个人资料
//        User user = userService.doFindById(userInfo.getId());
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
    /**修改基本信息*/
    @Trans("system.account.edit")
    public ProfileEditGetResq changeInfo(ProfileEditGetReq req){
        UserInfo userInfo = LoginSecurityUtil.getUser();
        // 获取用户登录的IP地址
//        User user = userService.doFindById(userInfo.getId());
        User user = this.userService.doFindById((long) 1);
        user.setRealnameEn(req.getRealnameEn());
        user.setRealnameZh(req.getRealnameZh());
        user.setMobile(req.getMobile());
        user.setTelephone(req.getTelephone());
        user.setOfficePhone(req.getOfficePhone());
        user.setFax(req.getFax());
        user.setPostcode(req.getPostcode());
        user.setLocation(req.getLocation());
        user.setDescription(req.getDescription());
        this.userService.doUpdate(user);
        return new ProfileEditGetResq(ProfileEditGetResq.SUCCESS_ROLE_EDIT_CODE,ProfileEditGetResq.SUCCESS_ROLE_EDIT_MESSAGE);
    }
    /*修改密码*/
    @Trans("system.account.editPassword")
    public ProfileEditPwdGetResq changePassword(ProfileEditPwdGetReq req){
        User user = this.userService.doFindById((long) 1);
        String oldpassword = req.getOldpassword();
        String passwordEncoder = passwordEncoderService.encodePassword(oldpassword,user.getEmail());
        String password = passwordEncoderService.encodePassword(req.getPassword(),user.getEmail());
        if(passwordEncoder == user.getPassword()){
            user.setPassword(password);
            this.userService.doUpdate(user);
            return new ProfileEditPwdGetResq(ProfileEditPwdGetResq.SUCCESS_ROLE_EDIT_CODE,ProfileEditPwdGetResq.SUCCESS_ROLE_EDIT_MESSAGE);
        }else{
            return new ProfileEditPwdGetResq(ProfileEditPwdGetResq.FAIL_ROLE_EDIT_CODE,ProfileEditPwdGetResq.FAIL_ROLE_EDIT_MESSAGE);
        }
    }
    @Trans("system.account.editImage")
    public ProfileEditImageResq editImage(ProfileEditImageReq req){
        if(req.getIcon()==null){
            return new ProfileEditImageResq(ProfileEditImageResq.FAIL_ROLE_EDIT_CODE,ProfileEditImageResq.FAIL_ROLE_EDIT_MESSAGE);
        }
        User user = this.userService.doFindById((long) 1);
        String image = req.getIcon();
        user.setImage(image);
        this.userService.doUpdate(user);
        return new ProfileEditImageResq(ProfileEditImageResq.SUCCESS_ROLE_EDIT_CODE,ProfileEditImageResq.SUCCESS_ROLE_EDIT_MESSAGE);
    }
}
