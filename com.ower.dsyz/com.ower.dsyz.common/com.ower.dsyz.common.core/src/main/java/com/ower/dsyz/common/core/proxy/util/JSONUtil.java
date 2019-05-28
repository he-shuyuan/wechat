package com.bda.chongqing.core.proxy.util;

import java.util.Map;

import com.alibaba.fastjson.JSON;

public class JSONUtil{
	
	/**
	 * 对象转map
	 * @param ob
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static Map<String,Object> ObjectToMap(Object ob){
		String str = JSON.toJSONString(ob);
		return JSON.parseObject(str, Map.class);
	}
}
