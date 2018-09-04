package com.ower.dsyz.logCenter.client;

import java.util.Date;

import com.ower.dsyz.logCenter.bean.LoggerMessage;
import com.ower.dsyz.logCenter.bean.NettyRestHead;
import com.ower.dsyz.logCenter.bean.NettyRestMessage;
import com.ower.dsyz.logCenter.enums.NettyMessageType;
import com.ower.dsyz.logCenter.util.NettyExchangeUtil;

import io.netty.buffer.ByteBuf;  
import io.netty.channel.ChannelHandlerContext;  
import io.netty.channel.ChannelInboundHandlerAdapter;  

public abstract class AbstractClientHandler extends ChannelInboundHandlerAdapter {  

  @Override  
  public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {  
      ByteBuf result = (ByteBuf) msg;
      byte[] result1 = new byte[result.readableBytes()];    
      result.readBytes(result1);       
      result.release();  
      NettyRestMessage<Object> message = NettyExchangeUtil.fireChannelRead(new String(result1));
      this.messageHandle(message);
  }  

  @Override  
  public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {  
      // 当出现异常就关闭连接  
      cause.printStackTrace();  
      ctx.close();  
  }  

    
  // 连接成功后，向server发送消息    
  @Override    
  public void channelActive(ChannelHandlerContext ctx) throws Exception {    
	  NettyRestMessage<Object> message = new NettyRestMessage<>();
	  NettyRestHead head = new NettyRestHead();
	  head.setAppName("test");
	  head.setAppToken("432432");
	  head.setBodyType(LoggerMessage.class);
	  head.setType(NettyMessageType.MESS);
	  message.setHead(head);
	  LoggerMessage loggerM = new LoggerMessage();
	  loggerM.setBody("gfdgfdg");
	  loggerM.setClassName(LoggerMessage.class.toString());
	  loggerM.setLevel("debug");
	  loggerM.setTimestamp(new Date().toString());
	  message.setBody(loggerM);
	  NettyExchangeUtil.fireChannelSend(ctx, message);
  }  
  
  /**
   * 消息处理
   * @param message
   */
  abstract void messageHandle(NettyRestMessage<Object> message);
} 