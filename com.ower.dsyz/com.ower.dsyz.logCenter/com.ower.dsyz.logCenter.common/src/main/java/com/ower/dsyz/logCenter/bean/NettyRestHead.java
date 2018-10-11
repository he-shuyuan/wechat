package com.ower.dsyz.logCenter.bean;

import java.io.Serializable;

import com.ower.dsyz.logCenter.constant.NettyMessageType;


public class NettyRestHead implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 应用名称
	 */
	private  String appName;
	
	/**
	 * 权限token
	 */
	private String appToken;
	
	/**
	 * ip
	 */
	private String ip;
	
	/**
	 * 消息类型
	 */
	private NettyMessageType  type;
	
	/**
	 * 消息体类型
	 */
	private Class<?> bodyType;
	

	public String getAppName() {
		return appName;
	}

	public void setAppName(String appName) {
		this.appName = appName;
	}

	public String getAppToken() {
		return appToken;
	}

	public void setAppToken(String appToken) {
		this.appToken = appToken;
	}

	public Class<?> getBodyType() {
		return bodyType;
	}

	public void setBodyType(Class<?> bodyType) {
		this.bodyType = bodyType;
	}


	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public NettyMessageType getType() {
		return type;
	}

	public void setType(NettyMessageType type) {
		this.type = type;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "NettyRestHead [appName=" + appName + ", appToken=" + appToken + ", ip=" + ip + ", type=" + type
				+ ", bodyType=" + bodyType + "]";
	}

	
	
	
}
