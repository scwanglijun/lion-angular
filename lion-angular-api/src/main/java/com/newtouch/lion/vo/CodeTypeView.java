/*
* Copyright (c)  2015, Newtouch
* All rights reserved. 
*
* $id: CodeTypVo.java 9552 2015年3月9日 下午5:02:18 WangLijun$
*/
package com.newtouch.lion.vo; 

import com.newtouch.lion.model.system.CodeType;

/**
 * <p>
 * Title: 
 * </p>
 * <p>
 * Description: 
 * </p>
 * <p>
 * Copyright: Copyright (c) 2015
 * </p>
 * <p>
 * Company: Newtouch
 * </p>
 * 
 * @author WangLijun
 * @version 1.0
 */
public class CodeTypeView extends CodeType {

	/**
	 * 序列化
	 */
	private static final long serialVersionUID = -5685573855710540205L;
	
	/**编码名称**/
	private String codeName;

	/**
	 * @return 编码名称
	 */
	public String getCodeName() {
		return codeName;
	}

	/**
	 * @param codeName 编码名称
	 */
	public void setCodeName(String codeName) {
		this.codeName = codeName;
	}
	
	

}

	