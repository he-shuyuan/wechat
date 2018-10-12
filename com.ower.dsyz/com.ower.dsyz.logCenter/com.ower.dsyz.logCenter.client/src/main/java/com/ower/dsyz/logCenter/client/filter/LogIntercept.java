package com.ower.dsyz.logCenter.client.filter;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import com.ower.dsyz.logCenter.constant.LogLevelType;

/**
 * 对所有log日志拦截
 * 
 * @author Administrator
 *
 */
public class LogIntercept {

	private Map<String, LogLevelType> filter = new HashMap<>();

	public Map<String, LogLevelType> getFilter() {
		return filter;
	}

	public void setFilter(String filter) {
		StringToMap(filter, this.filter);
	}

	public void setFilter(Map<String, LogLevelType> filter) {
		this.filter = filter;
	}
    
	public synchronized void addFilter(String key,LogLevelType type){
		this.filter.put(key, type);
	}
	
	private void StringToMap(String str, Map<String, LogLevelType> filter) {
		String[] strs = str.replaceAll("[\n\t\r]","").trim().split(",");
		for (String s : strs) {
			if (StringUtils.isNotBlank(s) && s.indexOf("=") > -1) {
				this.filter.put(s.split("=")[0].trim(), LogLevelType.valueOf(s.split("=")[1].toUpperCase().trim()));
			}
		}
	}

	@Override
	public String toString() {
		return "LogFilter [filter=" + filter + "]";
	}

}
