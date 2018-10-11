package com.ower.dsyz.logCenter.client;

import org.springframework.stereotype.Service;

import com.ower.dsyz.logCenter.bean.NettyRestMessage;
import com.ower.dsyz.logCenter.client.util.ChannelStore;

import io.netty.channel.ChannelHandlerContext;

@Service
public class LogClientServiceImpl implements ILogClientService{

	@Override
	public void readNettyMsg(NettyRestMessage<?> message, ChannelHandlerContext ctx) {
    	switch(message.getHead().getType()){
          case LOGIN: ChannelStore.getInstance().setAuthHead(message.getHead());
                      ChannelStore.getInstance().setCtx(ctx);
                       break;
          case LOGOUT: break;
          case MESS:
            break;
          default: break;
          }
		
	}

}
