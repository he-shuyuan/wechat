package com.ower.dsyz.logCenter.bean;

import java.io.Serializable;

public class NettyRestMessage<T> implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
    
	/**
	 * 消息头部
	 */
	private NettyRestHead head;
	
	/**
	 * 消息体
	 */
	private T body;


	public NettyRestHead getHead() {
		return head;
	}


	public void setHead(NettyRestHead head) {
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
		return "NettyRestMessage [head=" + head + ", body=" + body + "]";
	}
	
	
}

