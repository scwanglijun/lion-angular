/**
 * 
 */
package com.newtouch.lion.excpetion.sample;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.newtouch.lion.common.util.LoggerUtil;
import com.newtouch.lion.exception.BusinessException;
import com.newtouch.lion.exception.SystemException;

/**
 * @author wanglijun
 * 
 */

public class ExceptionSample {
	
	public static Logger logger = LoggerFactory.getLogger(ExceptionSample.class);

	public void doSomeBusiness(String param) {
		if ("".equals(param)) {
			LoggerUtil.info(logger, "Some business message");
			throw new BusinessException("Some business message ");
		}

		if ("".equals(param)) {
			LoggerUtil.info(logger, "Some business message");
			throw new BusinessException("CODE", param);
		}
	}

	public void doSomeFrameCode(String param) {
		if ("".equals(param)) {
			LoggerUtil.info(logger, "Some System message");
			throw new SystemException("Some System message ");
		}

		if ("".equals(param)) {
			LoggerUtil.info(logger, "Some System message");
			throw new SystemException("CODE", param);
		}
	}
}