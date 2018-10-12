package com.ower.dsyz.logCenter.client.service.impl;

import org.springframework.stereotype.Service;

import com.ower.dsyz.logCenter.bean.NettyRestMessage;
import com.ower.dsyz.logCenter.client.service.ILogClientService;
import com.ower.dsyz.logCenter.client.util.LogChannelStore;

import io.netty.channel.ChannelHandlerContext;

@Service
public class LogClientServiceImpl implements ILogClientService{

	@Override
	public void readNettyMsg(NettyRestMessage<?> message, ChannelHandlerContext ctx) {
    	switch(message.getHead().getType()){
          case LOGIN: LogChannelStore.getInstance().setAuthHead(message.getHead());
               LogChannelStore.getInstance().setCtx(ctx);
                       break;
          case LOGOUT: break;
          case MESS:
            break;
          default: break;
          }
		
	}

}
