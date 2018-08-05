package com.xuanwu.xtion.common.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.xuanwu.xtion.common.exception.AppErrorCode;
import com.xuanwu.xtion.common.exception.AppException;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class JsonUtil {
	/**
	 * 
	 * 自定义时间格式
	 * 
	 */
	private static DateFormat format;
	/**
	 * 
	 * ObjectMapper对象
	 * 
	 */
	private static ObjectMapper objectMapper;

	/**
	 * 
	 * 初始化
	 * 
	 */
	static {
		format = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss");
		objectMapper = new ObjectMapper();
		objectMapper.setDateFormat(format);
	}

	public static <T> String getJson(T object) {

		String json;
		try {
			json = objectMapper.writeValueAsString(object);
		} catch (JsonProcessingException e) {
			throw new AppException(AppErrorCode.JSON_PARSE_ERROR, e);
		}
		return json;
	}

	public static <T> T getObject(String json, Class<T> clazz) {
		try {
			return objectMapper.readValue(json, clazz);
		} catch (IOException e) {
			throw new AppException(AppErrorCode.JSON_PARSE_ERROR, e);
		}
	}

	public static <T> List<T> getList(String json, Class<T> clazz) {
		JavaType javaType = objectMapper.getTypeFactory().constructCollectionType(ArrayList.class, clazz);
		try {
			return objectMapper.readValue(json, javaType);
		} catch (IOException e) {
			throw new AppException(AppErrorCode.JSON_PARSE_ERROR, e);
		}
	}

}
