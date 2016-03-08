/*
 * Copyright (c)  2014, NewTouch
 * All rights reserved. 
 *
 * $id: DataColumnComparator.java 9552 2014-4-7 下午11:31:28 WangLijun$
 */
package com.newtouch.lion.comparator;

import java.util.Comparator;

import com.newtouch.lion.model.datagrid.DataColumn;

/**
 * <p>
 * Title: DataGrid Column 排序实现方式
 * </p>
 * <p>
 * Description: DataGrid Column 排序实现方式 ,是根据指定顺序进行升序排序，如果顺序相等则根据菜单ID升序排序
 * </p>
 * <p>
 * Copyright: Copyright (c) 2014
 * </p>
 * <p>
 * Company: NewTouch
 * </p>
 * 
 * @author WangLijun
 * @version 1.0
 */
public class DataColumnComparator  implements Comparator<DataColumn> {

	@Override
	public int compare(DataColumn dataColumn1, DataColumn dataColumn2) {
		if(dataColumn1.getShowOrder()==dataColumn2.getShowOrder()){
			return dataColumn1.getId()>dataColumn2.getId()?1:-1;
		}
		return dataColumn1.getShowOrder()>dataColumn2.getShowOrder()?1:-1;
	}
	
}
