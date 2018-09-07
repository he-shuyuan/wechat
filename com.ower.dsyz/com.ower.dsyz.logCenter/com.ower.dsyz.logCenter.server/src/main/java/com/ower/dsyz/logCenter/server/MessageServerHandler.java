package com.ower.dsyz.logCenter.server;

import org.springframework.stereotype.Service;

import com.ower.dsyz.logCenter.bean.NettyRestMessage;
import com.ower.dsyz.logCenter.util.NettyExchangeUtil;

import io.netty.channel.ChannelHandlerContext;

@Service
public class MessageServerHandler extends AbstractServerHandler{

	@Override
	void messageHandle(ChannelHandlerContext ctx,NettyRestMessage<Object> message) {
		switch(message.getHead().getType()){
		case LOGIN://验证成功，记录channel
			this.saveLoginState(ctx,message);
			break;
		case LOGOUT://退出
			break;
		case MESS://消息
			this.saveLogInfo(message);
			break;
	    default:break;
		}
	}

	/**
	 * 保存channel状态
	 * @param ctx
	 * @param message
	 */
	private void saveLoginState(ChannelHandlerContext ctx, NettyRestMessage<Object> message) {
	     ChannelHelper.addAppChannel(message.getHead().getAppName(), ctx);
	     message.getHead().setAppToken("001");
		 NettyExchangeUtil.fireChannelSend(ctx, message);
	}

	/**
	 * 记录日志信息
	 * @param message
	 */
	private void saveLogInfo(NettyRestMessage<Object> message) {
		System.err.println(message);
		
	}
}
