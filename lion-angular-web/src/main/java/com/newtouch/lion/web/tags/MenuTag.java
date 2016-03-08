/*
 * Copyright (c)  2014, Newtouch
 * All rights reserved. 
 *
 * $id: MenuTag.java 9552 2014年12月31日 下午4:42:30 WangLijun$
 */
package com.newtouch.lion.web.tags;

import com.newtouch.lion.common.user.UserInfo;
import com.newtouch.lion.model.menu.Menu;
import com.newtouch.lion.service.menu.MenuService;
import com.newtouch.lion.web.freemarker.DirectiveUtils;
import com.newtouch.lion.web.shiro.session.LoginSecurityUtil;
import freemarker.core.Environment;
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static freemarker.template.ObjectWrapper.DEFAULT_WRAPPER;

/**
 * <p>
 * Title: 菜单Tag
 * </p>
 * <p>
 * Description: 菜单Tag
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
public class MenuTag  extends AbstractTag{

	/** 日志 */
	private static final Logger logger = LoggerFactory.getLogger(MenuTag.class);
	/**菜单列表 */
	private static final String MENUS="menus";
	/** 菜单Service */
	@Autowired
	private MenuService menuService;

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public void execute(Environment env, Map params, TemplateModel[] loopVars,
			TemplateDirectiveBody body) throws TemplateException, IOException {
		// 获取登录信息
		UserInfo user = LoginSecurityUtil.getUser();
		if (user == null) {
			logger.error("用户未登录，无法获取用户菜单信息的");
			return;
		}
		List<Menu> menus = menuService.doFindByUserId(user.getId(),this.getRequestURL(),this.getContextPath());
		Map<String, TemplateModel> paramWrap = new HashMap<String, TemplateModel>(params);
		paramWrap.put(MENUS, DEFAULT_WRAPPER.wrap(menus));
		Map<String, TemplateModel> origMap = DirectiveUtils.addParamsToVariable(env, paramWrap);
		body.render(env.getOut());
		DirectiveUtils.removeParamsFromVariable(env, paramWrap, origMap);
	}

}
