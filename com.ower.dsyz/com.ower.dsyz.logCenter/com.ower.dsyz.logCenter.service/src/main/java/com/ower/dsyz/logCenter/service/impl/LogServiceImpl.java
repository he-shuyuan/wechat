package com.ower.dsyz.logCenter.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ower.dsyz.logCenter.auto.model.LogDetail;
import com.ower.dsyz.logCenter.bean.LoggerMessageBody;
import com.ower.dsyz.logCenter.bean.LoggerRestMessage;
import com.ower.dsyz.logCenter.bean.NettyRestMessage;
import com.ower.dsyz.logCenter.service.ILogAuthService;
import com.ower.dsyz.logCenter.service.ILogDetailService;
import com.ower.dsyz.logCenter.service.ILogService;

import io.netty.channel.ChannelHandlerContext;

@Service
public class LogServiceImpl implements ILogService{

	@Autowired
	ILogDetailService logDetailService;
	
	@Autowired
	ILogAuthService logAuthService;
	
	@Override
	public Boolean save(LoggerRestMessage<LoggerMessageBody> loggerMess, String ip) {
		LogDetail logDetail = new LogDetail();
		logDetail.setAppName(loggerMess.getHead().getAppName());
		logDetail.setIp(ip);
		logDetail.setClassName(loggerMess.getBody().getClassName());
		logDetail.setBody(loggerMess.getBody().getBody());
		logDetail.setLevel(loggerMess.getBody().getLevel());
		logDetail.setThreadName(loggerMess.getBody().getThreadName());
		logDetail.setTimestamp(loggerMess.getBody().getTimestamp());
		logDetailService.saveLogDetail(logDetail);
		return true;
	}

	@SuppressWarnings("unchecked")
	@Override
	public void readNettyMsg(NettyRestMessage<?> message,ChannelHandlerContext ctx) {
        switch(message.getHead().getType()){
        case LOGIN: loginAuth(message,ctx);break;
        case LOGOUT: break;
        case MESS:this.saveMess((NettyRestMessage<LoggerMessageBody>) message);
          break;
        default: break;
        }
		
	}

	/**
	 * 保存信息
	 * @param loggerMess
	 */
	private void saveMess(NettyRestMessage<LoggerMessageBody> loggerMess){
		LogDetail logDetail = new LogDetail();
		logDetail.setAppName(loggerMess.getHead().getAppName());
		logDetail.setIp(loggerMess.getHead().getIp());
		logDetail.setClassName(loggerMess.getBody().getClassName());
		logDetail.setBody(loggerMess.getBody().getBody());
		logDetail.setLevel(loggerMess.getBody().getLevel());
		logDetail.setThreadName(loggerMess.getBody().getThreadName());
		logDetail.setTimestamp(loggerMess.getBody().getTimestamp());
		logDetailService.saveLogDetail(logDetail);
	}
	
	
	/**
	 * 消息回写确认
	 * @param loggerMess
	 * @param ctx
	 */
	private void loginAuth(NettyRestMessage<?> loggerMess,ChannelHandlerContext ctx){
		ctx.writeAndFlush(loggerMess);
	}
}
