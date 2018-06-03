package com.ower.dsyz.common.core.constant;


public enum UrlParamType {
	APPID("appId", "appId不能为空"), REQUESTID("requestId", "requestId不能为空");

	private String name;

	private String mess;

	private UrlParamType(String name, String mess) {
		this.name = name;
		this.mess = mess;
	}

	public String getName() {
		return name;
	}

	public String getMess() {
		return mess;
	}

}