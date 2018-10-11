package com.ower.dsyz.logCenter.constant;

public enum NettyMessageType {
   LOGIN("登录授权"),LOGOUT("退出登录"),MESS("正常通讯");
	
	private String desc;
	private NettyMessageType(String desc){
		this.desc = desc;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	
}
