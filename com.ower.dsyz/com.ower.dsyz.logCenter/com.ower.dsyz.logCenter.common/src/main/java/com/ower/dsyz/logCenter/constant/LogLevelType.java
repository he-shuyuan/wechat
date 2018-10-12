package com.ower.dsyz.logCenter.constant;

public enum LogLevelType {
	OFF("关闭",11), ERROR("错误",9), WARN("警告",7), INFO("信息",5), DEBUG("调试",3), TRACE("追踪",1);

	private String desc;
	
    private int weight;
    
	private LogLevelType(String desc,int weight) {
		this.desc = desc;
		this.weight = weight;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public int getWeight() {
		return weight;
	}

	public void setWeight(int weight) {
		this.weight = weight;
	}
	
}
