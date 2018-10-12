package com.ower.dsyz.logCenter.client.service;

import com.ower.dsyz.logCenter.bean.NettyRestMessage;

import io.netty.channel.ChannelHandlerContext;

public interface ILogClientService {

	/**
	 * 读取netty消息
	 * @param message
	 */
	void readNettyMsg(NettyRestMessage<?> message,ChannelHandlerContext ctx);
}
