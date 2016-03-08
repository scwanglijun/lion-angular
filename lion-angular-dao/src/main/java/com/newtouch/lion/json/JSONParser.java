/*
 * Copyright (c)  2013, Newtouch
 * All rights reserved. 
 *
 * $id: JSONParser.java 9552 2013-3-25 下午9:43:46 WangLijun$
 */
package com.newtouch.lion.json;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang.StringUtils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.JSONSerializer;
import com.alibaba.fastjson.serializer.PropertyPreFilter;
import com.alibaba.fastjson.serializer.SerializeConfig;
import com.alibaba.fastjson.serializer.SerializeWriter;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.serializer.SimpleDateFormatSerializer;
import com.alibaba.fastjson.serializer.SimplePropertyPreFilter;
import com.newtouch.lion.common.date.DateUtil;
import com.newtouch.lion.page.PageResult;

/**
 * <p>
 * Title: JSON解析工具类
 * </p>
 * <p>
 * Description:JSON解析工具类
 * </p>
 * <p>
 * Copyright: Copyright (c) 2013
 * </p>
 * <p>
 * Company: Newtouch
 * </p>
 * 
 * @author WangLijun
 * @version 1.0
 */
public class JSONParser implements Serializable {

	/**
		 * 
		 */
	private static final long serialVersionUID = -4897117665103949997L;

	private static SerializeConfig mapping = new SerializeConfig();

	static {
		mapping.put(Date.class, new SimpleDateFormatSerializer("yyyy-MM-dd"));
	}

	/**
	 * 根据
	 * @param datePattern 日期格式
	 * */
	private static SerializeConfig config(String datePattern) {
		SerializeConfig mapping = new SerializeConfig();
		mapping.put(Date.class, new SimpleDateFormatSerializer(datePattern));
		mapping.put(java.sql.Date.class, new SimpleDateFormatSerializer(datePattern));
		mapping.put(Timestamp.class,new SimpleDateFormatSerializer(datePattern));
		return mapping;
	}

	/**
	 * 根据解析JSON字符
	 * 
	 * @param object
	 * */
	public static String toJSONString(Object object) {
		return JSON.toJSONString(object);
	}

	/***
	 * 将对象解析为JSON
	 * 
	 * @param object
	 * @param datePattern
	 * @return String
	 * */
	public static String toJSONString(Object object, String datePattern) {
		return JSON.toJSONString(object, config(datePattern));
	}

	/***
	 * 
	 * @param properties
	 * @param includeProperties
	 * @return PropertyFilter
	 */
	private static PropertyPreFilter configPropertyFilter(
			Set<String> properties, boolean includeProperties) {

		SimplePropertyPreFilter simplePropertyPreFilter = new SimplePropertyPreFilter();

		if (includeProperties) {
			simplePropertyPreFilter.getIncludes().addAll(properties);
		} else {
			simplePropertyPreFilter.getExcludes().addAll(properties);
		}
		return simplePropertyPreFilter;
	}

	/***
	 * 将对象解析为JSON
	 * 
	 * @param object
	 *            JSON解析对象
	 * @param datePattern
	 *            日期格式
	 * @param properties
	 *            字段
	 * @param isIncludeProperties
	 *            true 包含字段，False 则排除字段
	 * @return String 返回JSON字符串
	 */
	public static String toJSONString(Object object, String datePattern,
			Set<String> properties, boolean isIncludeProperties) {
		// 设置
		SerializerFeature[] features = { SerializerFeature.QuoteFieldNames,
				SerializerFeature.DisableCircularReferenceDetect };
		SerializeWriter out = new SerializeWriter(features);
		JSONSerializer serializer = new JSONSerializer(out, config(datePattern));
		serializer.getPropertyPreFilters().add(configPropertyFilter(properties, isIncludeProperties));
		serializer.write(object);
		return out.toString();
	}

	/***
	 * datePattern默认格式为：yyyy-MM-dd
	 * 
	 * @param objectJSON解析对象
	 * @param properties
	 *            字段
	 * @param isIncludeProperties
	 *            true 包含字段，False 则排除字段
	 * @return String JSON字符串
	 */
	public static String toJSONString(Object object, Set<String> properties,
			boolean isIncludeProperties) {
		return JSONParser.toJSONString(object, DateUtil.FORMAT_DATE_DEFAULT,
				properties, isIncludeProperties);
	}

	/***
	 * 根据分页对象、字段、 日期格式默认为：yyyy-MM-dd HH:mm:ss及默认包括列字段 生成DataGrid的JSON字符串
	 * 
	 * @param pageResult
	 *            分页对象
	 * @return String JSON字符串
	 */
	public static String toJSONDataGridString(PageResult<?> pageResult,
			Set<String> properties) {
		return JSONParser.toJSONDataGridString(pageResult, properties, null,
				Boolean.TRUE);
	}

	/***
	 * 根据集合对象和字段生成JSON字符串，其中日期格式默认为：yyyy-MM-dd HH:mm:ss 字段列表默认为包含：true
	 * 
	 * @param objects
	 *            列表对象
	 * @param properties
	 *            列表所显示字段
	 * @return 返回JSON字符串
	 */
	public static String toJSONDataGridString(Set<?> objects,
			Set<String> properties) {
		return JSONParser.toJSONDataGridString(objects, properties,
				Boolean.TRUE);
	}

	/***
	 * 根据集合对象和字段生成JSON字符串，其中日期格式默认为：yyyy-MM-dd HH:mm:ss
	 * 
	 * @param objects
	 *            列表对象
	 * @param properties
	 *            列表所显示字段
	 * @param includeProperties
	 *            是否包含字段
	 * @return 返回JSON字符串
	 */
	public static String toJSONDataGridString(Set<?> objects,
			Set<String> properties, Boolean includeProperties) {
		return JSONParser.toJSONDataGridString(objects, properties, null,
				includeProperties);
	}

	/***
	 * 根据集合对象和字段生成JSON字符串，可指定集合的生成日期格式和是否包括字段
	 * 
	 * @param objects
	 *            集合对象
	 * @param properties
	 *            列表所显示字段
	 * @param datePattern
	 *            日期格式，如为空，默认格式为：yyyy-MM-dd HH:mm:ss
	 * @param includeProperties
	 *            是否包含字段
	 * @return 返回JSON字符串
	 */
	public static String toJSONDataGridString(Set<?> objects,
			Set<String> properties, String datePattern,
			Boolean includeProperties) {
		StringBuffer sb = new StringBuffer();
		sb.append("{\"total\":");
		sb.append(objects.size());
		sb.append(",\"rows\":");
		if (StringUtils.isEmpty(datePattern)) {
			datePattern = DateUtil.FORMAT_DATETIME_YYYY_MM_DD_HH_MM_SS;
		}
		String objectJSONStr = JSONParser.toJSONString(objects, datePattern,
				properties, includeProperties);
		sb.append(objectJSONStr);
		sb.append("}");
		return sb.toString();
	}

	/***
	 * 根据集合对象和字段生成JSON字符串，其中日期格式默认为：yyyy-MM-dd HH:mm:ss 字段列表默认为包含：true
	 * 
	 * @param objects
	 *            列表对象
	 * @param properties
	 *            列表所显示字段
	 * @return 返回JSON字符串
	 */
	public static String toJSONDataGridString(List<?> objects,
			Set<String> properties) {
		return JSONParser.toJSONDataGridString(objects, properties,
				Boolean.TRUE);
	}

	/***
	 * 根据集合对象和字段生成JSON字符串，其中日期格式默认为：yyyy-MM-dd HH:mm:ss
	 * 
	 * @param objects
	 *            列表对象
	 * @param properties
	 *            列表所显示字段
	 * @param includeProperties
	 *            是否包含字段
	 * @return 返回JSON字符串
	 */
	public static String toJSONDataGridString(List<?> objects,
			Set<String> properties, Boolean includeProperties) {
		return JSONParser.toJSONDataGridString(objects, properties, null,
				includeProperties);
	}

	/***
	 * 根据集合对象和字段生成JSON字符串，可指定集合的生成日期格式和是否包括字段
	 * 
	 * @param objects
	 *            集合对象
	 * @param properties
	 *            列表所显示字段
	 * @param datePattern
	 *            日期格式，如为空，默认格式为：yyyy-MM-dd HH:mm:ss
	 * @param includeProperties
	 *            是否包含字段
	 * @return 返回JSON字符串
	 */
	public static String toJSONDataGridString(List<?> objects,
			Set<String> properties, String datePattern,
			Boolean includeProperties) {
		StringBuffer sb = new StringBuffer();
		sb.append("{\"total\":");
		sb.append(objects.size());
		sb.append(",\"rows\":");
		if (StringUtils.isEmpty(datePattern)) {
			datePattern = DateUtil.FORMAT_DATETIME_YYYY_MM_DD_HH_MM_SS;
		}
		String objectJSONStr = JSONParser.toJSONString(objects, datePattern,
				properties, includeProperties);
		sb.append(objectJSONStr);
		sb.append("}");
		return sb.toString();
	}

	/***
	 * 根据分页对象、字段、包括还是排除字段 日期格式默认为：yyyy-MM-dd HH:mm:ss 生成DataGrid的JSON字符串
	 * 
	 * @param pageResult
	 *            分页对象
	 * @param properties
	 *            字段
	 * @param includeProperties
	 *            true 包含字段，False 则排除字段
	 * @return String JSON字符串
	 */
	public static String toJSONDataGridString(PageResult<?> pageResult,
			Set<String> properties, Boolean includeProperties) {
		return JSONParser.toJSONDataGridString(pageResult, properties, null,
				includeProperties);
	}

	/***
	 * 根据分页对象、字段、日期格式、包括还是排除字段生成DataGrid的JSON字符串
	 * 
	 * @param pageResult
	 *            分页对象
	 * @param properties
	 *            字段
	 * @param datePattern
	 *            日期格式 为空时 日期格式默认格式为：yyyy-MM-dd HH:mm:ss
	 * @param includeProperties
	 *            true 包含字段，False 则排除字段
	 * @return String JSON字符串
	 */
	public static String toJSONDataGridString(PageResult<?> pageResult,
			Set<String> properties, String datePattern,
			Boolean includeProperties) {
		StringBuffer sb = new StringBuffer();
		sb.append("{\"total\":");
		sb.append(pageResult.getTotalCount());
		sb.append(",\"rows\":");
		if (StringUtils.isEmpty(datePattern)) {
			datePattern = DateUtil.FORMAT_DATETIME_YYYY_MM_DD_HH_MM_SS;
		}
		String objectJSONStr = JSONParser.toJSONString(pageResult.getContent(),
				datePattern, properties, includeProperties);
		sb.append(objectJSONStr);
		sb.append("}");
		return sb.toString();
	}

	/***
	 * isIncludeProperties 默认为:TRUE
	 * 
	 * @param object
	 * @param properties
	 * @return
	 */
	public static String toJSONString(Object object, Set<String> properties) {
		return JSONParser.toJSONString(object, properties, Boolean.TRUE);
	}
}
