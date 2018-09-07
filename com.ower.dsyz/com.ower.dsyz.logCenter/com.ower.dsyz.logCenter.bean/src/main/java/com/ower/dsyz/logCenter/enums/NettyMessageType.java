package com.ower.dsyz.logCenter.enums;

public enum NettyMessageType {
   LOGIN("接通链接"),LOGOUT("退出"),MESS("消息");
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