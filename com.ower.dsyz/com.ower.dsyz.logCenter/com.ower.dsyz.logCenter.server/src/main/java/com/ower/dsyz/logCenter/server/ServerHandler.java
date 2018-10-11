package com.ower.dsyz.logCenter.server;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ower.dsyz.logCenter.bean.NettyRestMessage;
import com.ower.dsyz.logCenter.service.ILogService;
import io.netty.channel.ChannelHandler.Sharable;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
@Service
@Sharable
public class ServerHandler extends ChannelInboundHandlerAdapter  {
 
	private Logger log = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	ILogService logService;
	
	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg)
			throws Exception {
		log.debug("【成功接收客户端消息】=》{}",msg);
		logService.readNettyMsg((NettyRestMessage<?>) msg,ctx);
	}
    
	/**
	 * 新连接
	 */
	 @Override
	 public void channelActive(ChannelHandlerContext ctx) throws Exception {
		 log.debug("【连接{}接入】",ctx.hashCode());

	}
	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause)
			throws Exception {
		log.debug("【连接{}异常,执行关闭】",ctx.hashCode());
		ctx.close();
	}
 
	@Override
	public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
		ctx.flush();
	}
	
    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
    	log.debug("【连接{}断开】",ctx.hashCode());
    }

}
