package com.ower.dsyz.logCenter.util;

import java.util.List;
import java.util.Map;

import com.ower.dsyz.common.core.util.Jackson;
import com.ower.dsyz.logCenter.bean.NettyRestMessage;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;

/**
 * 交互工具
 * @author Administrator
 *
 */
public class NettyExchangeUtil {

	/**
	 * 向指定channel发送消息
	 * @param ctx
	 * @param msg
	 */
	public static void fireChannelSend(ChannelHandlerContext ctx,NettyRestMessage<Object> message){
		    String msg = Jackson.toJson(message);
		    ByteBuf encoded = ctx.alloc().buffer(4 * msg.length());  
	        encoded.writeBytes(msg.getBytes());  
	        ctx.write(encoded);  
	        ctx.flush(); 
	}
	
	/**
	 * 解析消息
	 * @param ctx
	 * @param msg
	 */
	@SuppressWarnings({ "unchecked"})
	public  static NettyRestMessage<Object> fireChannelRead(String msg){
        NettyRestMessage<Object> message = Jackson.fromJson(msg, NettyRestMessage.class);
        if(message.getHead().getBodyType()!=null){
        	String json = Jackson.toJson(message.getBody());
        	if(message.getBody() instanceof Map){
        	message.setBody(Jackson.fromJson(json, message.getHead().getBodyType()));
        	}else if(message.getBody() instanceof List){
        		message.setBody(Jackson.fromJsonArray(json, message.getHead().getBodyType()));	
        	}
        }
        return message;
	}
}
