package com.ower.dsyz.logCenter.server;

import java.net.InetSocketAddress;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ower.dsyz.logCenter.auto.model.LogDetail;
import com.ower.dsyz.logCenter.bean.LoggerMessage;
import com.ower.dsyz.logCenter.bean.NettyRestMessage;
import com.ower.dsyz.logCenter.service.ILogDetailService;
import com.ower.dsyz.logCenter.util.NettyExchangeUtil;

import io.netty.channel.ChannelHandlerContext;

@Service
public class MessageServerHandler extends AbstractServerHandler{

	@Autowired
	ILogDetailService logDetailService;
	
	@Override
	void messageHandle(ChannelHandlerContext ctx,NettyRestMessage<Object> message) {
	    InetSocketAddress insocket = (InetSocketAddress) ctx.channel().remoteAddress();
        String clientIP = insocket.getAddress().getHostAddress();
		switch(message.getHead().getType()){
		case LOGIN://验证成功，记录channel
			this.saveLoginState(ctx,message,clientIP);
			break;
		case LOGOUT://退出
			break;
		case MESS://消息
			this.saveLogInfo(message,clientIP);
			break;
	    default:break;
		}
	}

	/**
	 * 保存channel状态
	 * @param ctx
	 * @param message
	 */
	private void saveLoginState(ChannelHandlerContext ctx, NettyRestMessage<Object> message,String ip) {
	     ChannelHelper.addAppChannel(message.getHead().getAppName(), ctx);
	     message.getHead().setAppToken("001");
		 NettyExchangeUtil.fireChannelSend(ctx, message);
	}

	/**
	 * 记录日志信息
	 * @param message
	 */
	private void saveLogInfo(NettyRestMessage<Object> message,String ip) {
		LoggerMessage loggerMessage = (LoggerMessage) message.getBody();
		LogDetail logDetail = new LogDetail();
		logDetail.setAppName(message.getHead().getAppName());
		logDetail.setClassName(loggerMessage.getClassName());
		logDetail.setLevel(loggerMessage.getLevel());
		logDetail.setTimestamp(loggerMessage.getTimestamp());
		logDetail.setBody(loggerMessage.getBody());
		logDetail.setIp(ip);
		logDetailService.saveLogDetail(logDetail);
	}
}
