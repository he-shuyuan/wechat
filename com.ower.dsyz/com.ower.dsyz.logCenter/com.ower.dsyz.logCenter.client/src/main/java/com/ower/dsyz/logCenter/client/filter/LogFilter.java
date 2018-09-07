package com.ower.dsyz.logCenter.client.filter;

import java.text.DateFormat;
import java.util.Date;

import com.ower.dsyz.logCenter.bean.LoggerMessage;
import com.ower.dsyz.logCenter.bean.NettyRestHead;
import com.ower.dsyz.logCenter.bean.NettyRestMessage;
import com.ower.dsyz.logCenter.client.ChannelHelper;
import com.ower.dsyz.logCenter.enums.NettyMessageType;
import com.ower.dsyz.logCenter.util.NettyExchangeUtil;

import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.classic.spi.StackTraceElementProxy;
import ch.qos.logback.core.filter.Filter;
import ch.qos.logback.core.spi.FilterReply;
/**
 * 对所有log日志拦截
 * @author Administrator
 *
 */
public class LogFilter extends Filter<ILoggingEvent>{

	@Override
	public FilterReply decide(ILoggingEvent event) {
		
		LoggerMessage loggerMessage = new LoggerMessage(
				event.getMessage()
                , DateFormat.getDateTimeInstance().format(new Date(event.getTimeStamp())),
                event.getThreadName(),
                event.getLoggerName(),
                event.getLevel().levelStr
        );
		if(event.getThrowableProxy()!=null){
			String errorMsg = "\r\t"+event.getThrowableProxy().getClassName()+" : "+event.getThrowableProxy().getMessage();		
			errorMsg += this.resourceFix(event.getThrowableProxy().getStackTraceElementProxyArray());
			loggerMessage.setBody(loggerMessage.getBody()+errorMsg);
		}
		NettyRestMessage<Object> message = new NettyRestMessage<>();
		NettyRestHead head = ChannelHelper.getHead();
		head.setType(NettyMessageType.MESS);
		head.setBodyType(LoggerMessage.class);
		message.setBody(loggerMessage);
		message.setHead(head);
		NettyExchangeUtil.fireChannelSend(ChannelHelper.getCtx(), message);
        return FilterReply.ACCEPT;
	}
    
	
	
	/**
	 * 递归获取异常
	 * @param throwableProxy
	 * @return
	 */
	private String resourceFix(StackTraceElementProxy[] stackTraceElementProxy) {
		String errorMsg = "";
		if(stackTraceElementProxy!=null){
			for(StackTraceElementProxy stackTraceElement:stackTraceElementProxy){
				errorMsg+=
						"\r\t"+stackTraceElement.getSTEAsString();
			}
		}
		return errorMsg;
	}
}  
