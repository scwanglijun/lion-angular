package com.newtouch.lion.web.controller;

import com.newtouch.lion.common.user.UserInfo;
import com.newtouch.lion.web.captcha.CaptchaUtils;
import com.newtouch.lion.web.model.LoginVo;
import com.newtouch.lion.web.shiro.authc.CredentialExpiredException;
import com.newtouch.lion.web.shiro.authc.ExpiredAccountException;
import com.newtouch.lion.web.shiro.constant.Constants;
import com.newtouch.lion.web.shiro.service.ShiroUserService;
import com.newtouch.lion.web.shiro.session.LoginSecurityUtil;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by wanglijun on 16/5/27.
 */
@Controller
public class LoginController {

    /**日志*/
    protected final Logger logger= LoggerFactory.getLogger(super.getClass());


    public  static final String ERROR_MSG="errorMsg";

    @Autowired
    private ShiroUserService shiroUserService;

    /***
     * 登录
     * @param loginVo
     * @param request
     * @return
     */
    @RequestMapping(value ="login",method = RequestMethod.POST)
    @ResponseBody
    public Map<String,String> login(@RequestBody LoginVo loginVo, HttpServletRequest request){

        Map<String,String> loginResult = new HashMap<String,String>();

        String errorMsg= StringUtils.EMPTY;

//        if(StringUtils.isEmpty(loginVo.getCaptchaCode())){
//            loginResult.put(Constants.LOGIN_ERROR_MSG,"验证码不能为空!");
//            return loginResult;
//        }

        HttpSession session=request.getSession(Boolean.TRUE);
        String captchaCode=(String)session.getAttribute(CaptchaUtils.CAPTCHA_KEY);
        //判断验证码不能
//        if(captchaCode==null || !loginVo.getCaptchaCode().toLowerCase().equals(captchaCode.toLowerCase())){
//
//            loginResult.put(Constants.LOGIN_ERROR_MSG,"验证码不正确!");
//            //清空已有的验证码,防止暴力破解
//            session.setAttribute(CaptchaUtils.CAPTCHA_KEY,StringUtils.EMPTY);
//            return loginResult;
//        }
        //清空已有的验证码,防止暴力破解
        session.setAttribute(CaptchaUtils.CAPTCHA_KEY,StringUtils.EMPTY);


        //用户名密码验证
        //获取当前的Subject
        UsernamePasswordToken token=new UsernamePasswordToken(loginVo.getUsername(), loginVo.getPassword(),loginVo.getRememberMe());
        //token.setRememberMe();
        //获取当前的Subject
        Subject currentUser= SecurityUtils.getSubject();
        try{
            logger.info("验证用户和密码开始...");
            currentUser.login(token);
            logger.info("验证用户和密码结束...");
            //
        }catch(UnknownAccountException e){
            loginResult.put(Constants.LOGIN_ERROR_MSG,"用户名或密码不正确");
            logger.error(e.getMessage(),e);
        }catch(IncorrectCredentialsException e){
            loginResult.put(Constants.LOGIN_ERROR_MSG,"用户名或密码不正确");
            logger.error(e.getMessage(),e);
        }catch(LockedAccountException e){
            logger.error(e.getMessage(),e);
            loginResult.put(Constants.LOGIN_ERROR_MSG,"用户已锁定");
        }catch(ExpiredAccountException e){
            logger.error(e.getMessage(),e);
            loginResult.put(Constants.LOGIN_ERROR_MSG,"用户已过期，请联系管理员");
        }catch(CredentialExpiredException e){
            logger.error(e.getMessage(),e);
            loginResult.put(Constants.LOGIN_ERROR_MSG,"密码已过期，请联系管理员");
        }catch(AuthenticationException e){
            loginResult.put(Constants.LOGIN_ERROR_MSG,"用户名或密码不正确");
            logger.error(e.getMessage(),e);
        }

        //看是否用户名或密码不正确
        if(!CollectionUtils.isEmpty(loginResult)){
            return loginResult;
        }

        if(currentUser.isAuthenticated()){
            loginResult.putAll(shiroUserService.getUserAttr(loginVo.getUsername()));
        }else{
            loginResult.put(Constants.LOGIN_ERROR_MSG,"用户名或密码不正确");
        }

        return loginResult;
    }

    @RequestMapping(value = "/logout",method=RequestMethod.POST)
    @ResponseBody
    public void logout(){
        logger.info("退出系统");
        Subject subject = SecurityUtils.getSubject();
        if (subject.isAuthenticated()) {
            UserInfo userInfo = LoginSecurityUtil.getUser();
            //退出
            //saveLoginLog(userInfo,"success","logout");
            // session 会销毁，在SessionListener监听session销毁，清理权限缓存
            subject.logout();

            logger.info("退出系统成功");
            //清除缓存
            //sessionCacheManager.removeSessionController(Constants.CACHE_SESSION_NAME,userInfo.getUsername());
        }
    }
}
