package com.ower.dsyz.common.core.holder;

public class CustomLoginUserIdHolder {

	private static ThreadLocal<String> userIdHolder = new ThreadLocal<>();
	public static String get(){
		return  userIdHolder.get();
	}
	
	public static void set(String userId){
		userIdHolder.set(userId);
	}
}
