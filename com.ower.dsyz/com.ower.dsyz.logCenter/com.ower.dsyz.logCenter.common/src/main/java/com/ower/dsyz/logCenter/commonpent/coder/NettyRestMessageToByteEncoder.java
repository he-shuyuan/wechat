package com.ower.dsyz.logCenter.commonpent.coder;
import com.ower.dsyz.logCenter.bean.NettyRestMessage;
import com.ower.dsyz.logCenter.constant.NettyConstant;
import com.ower.dsyz.logCenter.util.NettyUtil;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;
 
public class NettyRestMessageToByteEncoder extends MessageToByteEncoder<NettyRestMessage<?>> {
 
	@Override
	protected void encode(ChannelHandlerContext ctx, NettyRestMessage<?> msg, ByteBuf out)
			throws Exception {
		String ip = NettyUtil.getClientIPIp(ctx);
		msg.getHead().setIp(ip);
		byte[] bytes = NettyUtil.toByteArray(msg,NettyConstant.DELIMITER);
		out.writeBytes(bytes);
	}
}
