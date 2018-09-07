package com.ower.dsyz.logCenter.server;
import com.ower.dsyz.logCenter.bean.NettyRestMessage;
import com.ower.dsyz.logCenter.util.NettyExchangeUtil;

import io.netty.buffer.ByteBuf;  
import io.netty.channel.ChannelHandlerContext;  
import io.netty.channel.ChannelInboundHandlerAdapter;  
  
public abstract class AbstractServerHandler extends ChannelInboundHandlerAdapter {  
  
    @Override  
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {  
        ByteBuf result = (ByteBuf) msg;  
        byte[] result1 = new byte[result.readableBytes()];  
        // msg中存储的是ByteBuf类型的数据，把数据读取到byte[]中  
        result.readBytes(result1);  
        String resultStr = new String(result1); 
       // 释放资源，这行很关键  
        result.release();  
        NettyRestMessage<Object> message = NettyExchangeUtil.fireChannelRead(resultStr);
        this.messageHandle(ctx,message);
        //NettyExchangeUtil.fireChannelSend(ctx, message);
    }  
  
    @Override  
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {  
        // 当出现异常就关闭连接  
        cause.printStackTrace(); 
        ChannelHelper.removeChannel(ctx);
        ctx.close();  
    }  
  
    @Override  
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {  
        ctx.flush();  
    }  
  
    /**
     * 消息处理
     * @param message
     */
    abstract void messageHandle(ChannelHandlerContext ctx,NettyRestMessage<Object> message);
}  