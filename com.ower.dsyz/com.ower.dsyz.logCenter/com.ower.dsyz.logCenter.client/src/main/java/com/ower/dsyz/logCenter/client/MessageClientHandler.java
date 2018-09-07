package com.ower.dsyz.logCenter.client;

import org.springframework.stereotype.Service;

import com.ower.dsyz.logCenter.bean.NettyRestMessage;

import io.netty.channel.ChannelHandlerContext;

@Service
public class MessageClientHandler extends AbstractClientHandler{

	@Override
	void messageHandle(ChannelHandlerContext ctx,NettyRestMessage<Object> message) {
		switch(message.getHead().getType()){
		case LOGIN://验证成功，记录channel
			this.saveLoginState(ctx,message);
			break;
		case LOGOUT://退出
			break;
		case MESS://消息
			break;
	    default:break;
		}
		
	}

	/**
	 * 记录channel信息
	 * @param ctx
	 * @param message
	 */
	private void saveLoginState(ChannelHandlerContext ctx, NettyRestMessage<Object> message) {
		ChannelHelper.setCtx(ctx);
		ChannelHelper.setHead(message.getHead());
	}

}
