package com.ower.dsyz.common.core.holder;

public class CurrentThreadHolder {

	private static ThreadLocal<String> userId = new ThreadLocal<>();
	private static ThreadLocal<String> requestId = new ThreadLocal<>();
	private static ThreadLocal<String> appId = new ThreadLocal<>();
	
	public static String getUserId(){
		return  userId.get();
	}
	
	public static void setUserId(String id){
		userId.set(id);
	}
	
	
	public static String getRequestId(){
		return  requestId.get();
	}
	
	public static void setRequestId(String id){
		requestId.set(id);
	}
	public static String getAppId(){
		return  appId.get();
	}
	
	public static void setAppId(String id){
		appId.set(id);
	}
}
