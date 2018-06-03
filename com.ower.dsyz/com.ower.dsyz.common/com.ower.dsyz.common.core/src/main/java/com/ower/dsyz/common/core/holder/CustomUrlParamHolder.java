package com.ower.dsyz.common.core.holder;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.ower.dsyz.common.core.constant.ErrorCodeConstants;
import com.ower.dsyz.common.core.constant.UrlParamType;
import com.ower.dsyz.common.core.exception.CustomRunTimeException;

public class CustomUrlParamHolder {
	private static ThreadLocal<Map<String, String>> urlParam = new ThreadLocal<>();

	public static Map<String, String> get() {
		if(urlParam.get()==null){
			set(new HashMap<>());
		}
		return urlParam.get();
	}

	public static void set(Map<String, String> map) {
		urlParam.set(map);
	}

	public static void put(UrlParamType urlParamType, String value,Boolean checkEmpty) {
		  if(StringUtils.isNotBlank(value)){
			  get().put(urlParamType.getName(), value);
		  }else if(checkEmpty){
			  throw new CustomRunTimeException(ErrorCodeConstants.PARAM_ERROR,urlParamType.getMess());
		  }
		
	}
	
}
