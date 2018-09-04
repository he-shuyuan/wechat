package com.ower.dsyz.logCenter.service;

import com.ower.dsyz.logCenter.bean.NettyRestMessage;
public class MessageServerHandler extends AbstractServerHandler{

	@Override
	void messageHandle(NettyRestMessage<Object> message) {
		System.err.println("服务器收到消息，"+message);
	}

}
