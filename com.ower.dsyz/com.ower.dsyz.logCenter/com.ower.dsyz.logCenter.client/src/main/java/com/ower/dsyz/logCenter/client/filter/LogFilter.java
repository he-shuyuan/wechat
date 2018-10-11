package com.ower.dsyz.logCenter.client.filter;

import java.text.DateFormat;
import java.util.Date;

import com.ower.dsyz.logCenter.bean.LoggerMessageBody;
import com.ower.dsyz.logCenter.bean.NettyRestMessage;
import com.ower.dsyz.logCenter.client.util.ChannelStore;

import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.classic.spi.StackTraceElementProxy;
import ch.qos.logback.core.filter.Filter;
import ch.qos.logback.core.spi.FilterReply;
import io.netty.channel.ChannelHandlerContext;
/**
 * 对所有log日志拦截
 * @author Administrator
 *
 */
public class LogFilter extends Filter<ILoggingEvent>{
	
	@Override
	public FilterReply decide(ILoggingEvent event) {
		/**
		 * 日志中心打印在客户端的日志不作处理
		 */
		if(event.getLoggerName()!=null && event.getLoggerName().startsWith("com.ower.dsyz.logCenter")){
			return FilterReply.ACCEPT;
		}
		LoggerMessageBody loggerMessage = new LoggerMessageBody(
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
		message.setBody(loggerMessage);
		message.setHead(ChannelStore.getInstance().getAuthHead());
		ChannelHandlerContext ctx = ChannelStore.getInstance().getCtx();
		if(ctx!=null)
			ctx.writeAndFlush(message);
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
