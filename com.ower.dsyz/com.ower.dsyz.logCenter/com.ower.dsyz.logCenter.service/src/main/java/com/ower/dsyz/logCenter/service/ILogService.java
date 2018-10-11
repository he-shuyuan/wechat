package com.ower.dsyz.logCenter.service;

import com.ower.dsyz.logCenter.bean.LoggerMessageBody;
import com.ower.dsyz.logCenter.bean.LoggerRestMessage;
import com.ower.dsyz.logCenter.bean.NettyRestMessage;

import io.netty.channel.ChannelHandlerContext;

/**
 * 
 * @author Administrator
 *
 */
public interface ILogService {

	/**
	 * 记录日志
	 * @param loggerMess
	 * @return
	 */
	Boolean save(LoggerRestMessage<LoggerMessageBody> loggerMess,String ip);
	
	/**
	 * 读取netty消息
	 * @param message
	 */
	void readNettyMsg(NettyRestMessage<?> message,ChannelHandlerContext ctx);
}
