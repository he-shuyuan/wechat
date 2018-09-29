package com.ower.dsyz.logCenter.bean;

import java.io.Serializable;

public class LoggerRestMessage<T> implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
    
	/**
	 * 消息头部
	 */
	private LoggerRestHead head;
	
	/**
	 * 消息体
	 */
	private T body;


	public LoggerRestHead getHead() {
		return head;
	}


	public void setHead(LoggerRestHead head) {
		this.head = head;
	}


	public T getBody() {
		return body;
	}


	public void setBody(T body) {
		this.body = body;
	}


	@Override
	public String toString() {
		return "LoggerRestMessage [head=" + head + ", body=" + body + "]";
	}
	
	
}
