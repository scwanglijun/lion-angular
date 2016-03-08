/*
 * Copyright (c)  2014, Newtouch
 * All rights reserved. 
 * 
 * $id: UserRealm.java 9552 2014年12月29日 下午5:30:07 WangLijun$
 */
package com.newtouch.lion.web.shiro.realm;

import com.newtouch.lion.common.user.UserInfo;
import com.newtouch.lion.model.system.Resource;
import com.newtouch.lion.model.system.Role;
import com.newtouch.lion.model.system.User;
import com.newtouch.lion.service.system.UserService;
import com.newtouch.lion.web.shiro.authc.CredentialExpiredException;
import com.newtouch.lion.web.shiro.authc.ExpiredAccountException;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.DefaultHashService;
import org.apache.shiro.crypto.hash.HashRequest;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.SimplePrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.apache.shiro.util.SimpleByteSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashSet;
import java.util.Set;

/**
 * <p>
 * Title: 用户登录授权
 * </p>
 * <p>
 * Description: 用户登录授权
 * </p>
 * <p>
 * Copyright: Copyright (c) 2014
 * </p>
 * <p>
 * Company: Newtouch
 * </p>
 * 
 * @author WangLijun
 * @version 1.0
 */
public class UserRealm extends AuthorizingRealm {
	/***
	 * 日志
	 */
	private static final Logger logger = LoggerFactory.getLogger(UserRealm.class);
	/** 用户Service */
	@Autowired
	private UserService userService;
 

	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		// null usernames are invalid
		if (principals == null) {
			throw new AuthorizationException(
					"PrincipalCollection method argument cannot be null.");
		}
		// 获取Session的用户信息
		UserInfo userInfo = (UserInfo) this.getAvailablePrincipal(principals);
		logger.info("username:{},userId:{}", userInfo.getUsername(),
				userInfo.getId());
		// 角色名称列表
		Set<String> roleNames = new HashSet<String>();
		Set<String> permissions = new HashSet<String>();
		// 根据用户ID获取所有的角色信息
		Set<Role> userRoles = userService.doFindRoles(userInfo.getId());
		// 未授权直接返回空给用户
		if (userRoles.isEmpty()) {
			logger.error("current user:{} has not roles ",
					userInfo.getUsername());
			return new SimpleAuthorizationInfo(roleNames);
		}

		for (Role role : userRoles) {
			roleNames.add(role.getNameEn());
			for (Resource resource : role.getResources()) {
				String permission = resource.getPermission();
				if (StringUtils.isNotEmpty(permission) && !permissions.contains(permission)) {
					permissions.add(permission);
				}
			}
		}
		SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo(roleNames);
		authorizationInfo.setStringPermissions(permissions);
		return authorizationInfo;
	}

	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {

		UsernamePasswordToken usernamePasswordToken = (UsernamePasswordToken) token;

		String userName = usernamePasswordToken.getUsername();

		User user = userService.doFindByUserName(userName);
		// 没找到帐号
		if (user == null) {
			throw new UnknownAccountException();
		}
		// 帐号锁定
		if (Boolean.TRUE.equals(user.getAccountLocked())) {
			throw new LockedAccountException();
		}
		// 账户有效期验证
		if (Boolean.FALSE.equals(user.getAccountExpired())) {
			throw new ExpiredAccountException();
		}
		// 密码有效期验证
		if (Boolean.FALSE.equals(user.getCredentialExpired())) {
			throw new CredentialExpiredException();
		}

		UserInfo userInfo = new UserInfo(user.getUsername(), user.getId(),
				user.getRealnameZh(), user.getRealnameEn(),user.getImage());
		// 交给AuthenticatingRealm使用CredentialsMatcher进行密码匹配，如果觉得人家的不好可以自定义实现
		SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(
				userInfo, // 用户名
				user.getPassword(), // 密码
				ByteSource.Util.bytes(userInfo.getUsername()),// salt=username+salt
				getName() // realm name
		);
		return authenticationInfo;
	}

	/***
	 * 更新用户的授权信息缓存
	 * 
	 * @param principal
	 */
	public void clearCachedAuthorizationInfo(Object principal) {
		SimplePrincipalCollection principals = new SimplePrincipalCollection(principal, this.getName());
		super.clearCachedAuthorizationInfo(principals);
	}

	/***
	 * 清除用户的授权信息缓存
	 */
	public void clearAllCachedAuthorizationInfo() {
		this.getAuthorizationCache().clear();
	}

	/***
	 * 更新用户认证信息缓存信息
	 * 
	 * @param principal
	 */
	public void clearCachedAuthenticationInfo(Object principal) {
		SimplePrincipalCollection principals = new SimplePrincipalCollection(principal, this.getName());
		super.clearCachedAuthenticationInfo(principals);
	}

	/***
	 * 更新用户认证信息缓存信息
	 * @param principal
	 */
	public void clearCachedAuthenticationInfo(){
		this.getAuthenticationCache().clear();
	} 
	
	/**清除所有缓存信息*/
	public void clearAllCached(){
		this.clearCachedAuthenticationInfo();
		this.clearAllCachedAuthorizationInfo();
	}
	/****
	 * 退出的清除缓存
	 * @param principals
	 */
	@Override
	protected void doClearCache(PrincipalCollection principals) {
		 this.clearAllCached();
	}

	public static void main(String[] args) {
		DefaultHashService hashService = new DefaultHashService(); // 默认算法SHA-512
		hashService.setHashAlgorithmName("SHA-1");
		hashService.setPrivateSalt(new SimpleByteSource("wanglijun")); // 私盐，默认无
		hashService.setGeneratePublicSalt(true);// 是否生成公盐，默认false
		hashService.setRandomNumberGenerator(new SecureRandomNumberGenerator());// 用于生成公盐。默认就这个
		hashService.setHashIterations(1); // 生成Hash值的迭代次数

		HashRequest request = new HashRequest.Builder()
				.setAlgorithmName("SHA-1")
				.setSource(ByteSource.Util.bytes("111aaa"))
				.setSalt(ByteSource.Util.bytes("wanglijun")).setIterations(2)
				.build();

		String hex = hashService.computeHash(request).toHex();
		System.out.println(hex);
		String simpleHash = new SimpleHash("SHA-1", "111aaa",
				ByteSource.Util.bytes("wanglijun"), 2).toString();
		System.out.println(simpleHash
				.equals("c150916baa97eeccd1d99541aad26170761b41a9"));
		// 14c2f0f861d1bfc7b92bdf78355045f019141ec9

		// c150916baa97eeccd1d99541aad26170761b41a9
	}
}
