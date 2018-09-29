package com.ower.dsyz.logCenter.client.filter;

import java.text.DateFormat;
import java.util.Date;

import com.ower.dsyz.common.core.util.SpringContextUtil;
import com.ower.dsyz.logCenter.bean.LoggerMessageBody;
import com.ower.dsyz.logCenter.bean.LoggerRestHead;
import com.ower.dsyz.logCenter.bean.LoggerRestMessage;
import com.ower.dsyz.logCenter.client.util.LogSendHandler;
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

	
	private LogSendHandler logSendHandler;
	
	@Override
	public FilterReply decide(ILoggingEvent event) {
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
		LoggerRestMessage<Object> message = new LoggerRestMessage<>();
		LoggerRestHead head = new LoggerRestHead();
		head.setAppName("name");
		head.setBodyType(LoggerMessageBody.class);
		message.setBody(loggerMessage);
		message.setHead(head);
		getLogService().save(message);
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
	
	private synchronized LogSendHandler getLogService(){
		if(logSendHandler == null){
		     logSendHandler =  (LogSendHandler) SpringContextUtil.getBeansByClass(LogSendHandler.class).get(0);
		}
		return logSendHandler;
	}
}  
