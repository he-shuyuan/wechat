package com.ower.dsyz.logCenter.commonpent.coder;
import java.util.List;
import com.ower.dsyz.logCenter.bean.NettyRestMessage;
import com.ower.dsyz.logCenter.util.NettyUtil;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;
 
public class ByteToNettyRestMessageDecoder extends ByteToMessageDecoder {
	
	@Override
	protected void decode(ChannelHandlerContext ctx, ByteBuf in,
			List<Object> out) throws Exception {
		    int length = in.readableBytes();
		    if(length == 0){
		    	return;
		    }
		    byte[] bytes = new byte[length];
		    for(int i=0;i<length && in.isReadable();i++){
		    	bytes[i] = in.readByte();
		    }
			NettyRestMessage<?> msg =NettyUtil.toObject(bytes);
			out.add(msg);
	}
}
