package com.ower.dsyz.logCenter.bean;

/**
 * 日志记录
 * @author Administrator
 *
 */
public class LoggerMessageBody{

	/**
	 * 日志体
	 */
    private String body;
    /**
     * 时间戳
     */
    private String timestamp;
    /**
     * 线程名称
     */
    private String threadName;
    /**
     * 类名
     */
    private String className;
    /**
     * 层级
     */
    private String level;

    public LoggerMessageBody(String body, String timestamp, String threadName, String className, String level) {
        this.body = body;
        this.timestamp = timestamp;
        this.threadName = threadName;
        this.className = className;
        this.level = level;
    }

    public LoggerMessageBody() {
    }

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public String getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}

	public String getThreadName() {
		return threadName;
	}

	public void setThreadName(String threadName) {
		this.threadName = threadName;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}

	@Override
	public String toString() {
		return "LoggerMessage [body=" + body + ", timestamp=" + timestamp + ", threadName=" + threadName
				+ ", className=" + className + ", level=" + level + "]";
	}
    
}
