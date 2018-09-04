package com.ower.dsyz.logCenter.client;

import com.ower.dsyz.logCenter.bean.NettyRestMessage;

public class MessageClientHandler extends AbstractClientHandler{

	@Override
	void messageHandle(NettyRestMessage<Object> message) {
		System.err.println("客户端收到消息，"+message);
		
	}

}
