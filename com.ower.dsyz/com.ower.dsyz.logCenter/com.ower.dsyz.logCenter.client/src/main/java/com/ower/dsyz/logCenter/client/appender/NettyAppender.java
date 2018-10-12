package com.ower.dsyz.logCenter.client.appender;

import java.text.DateFormat;
import java.util.Date;
import java.util.Set;
import com.ower.dsyz.logCenter.bean.LoggerMessageBody;
import com.ower.dsyz.logCenter.bean.NettyRestMessage;
import com.ower.dsyz.logCenter.client.LogCenterClient;
import com.ower.dsyz.logCenter.client.filter.LogIntercept;
import com.ower.dsyz.logCenter.client.filter.OrderArrayList;
import com.ower.dsyz.logCenter.client.util.LogChannelStore;
import com.ower.dsyz.logCenter.client.util.LogSendThreadPoolUtil;
import com.ower.dsyz.logCenter.constant.LogLevelType;
import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.classic.spi.StackTraceElementProxy;
import ch.qos.logback.core.AppenderBase;
import io.netty.channel.ChannelHandlerContext;

public class NettyAppender extends AppenderBase<ILoggingEvent> {

	/**
	 * 服务器ip
	 */
	private String host;

	/**
	 * 端口
	 */
	private int port;

	/**
	 * 层级
	 */
	private LogLevelType level;
	/**
	 * 重连时间
	 */
	private long reConnectTime = 2L;

	/**
	 * 应用名
	 */
	private String appName;
    
	/**
	 * 拦截器
	 */
	private LogIntercept intercept;

	@Override
	public void start() {
		/**
		 * 开启连接
		 */
		new Thread(new Runnable() {
			@Override
			public void run() {
				LogCenterClient.getInstance(appName, reConnectTime).connect(host, port);
			}
		}).start();
		super.start();
	}

	@Override
	public void stop() {
		super.stop();
	}

	@Override
	public void append(ILoggingEvent event) {
		if (event.getLoggerName() != null && event.getLoggerName().startsWith("com.ower.dsyz.logCenter")) {
			return;
		}
		if (this.isIntercept(event.getLevel().levelStr,event.getLoggerName())) {
			return;
		}
		LogSendThreadPoolUtil.poolExecutor.execute(new Runnable() {
			@Override
			public void run() {
				NettyAppender.this.sycLogToLoggerCenter(event);
			}
		});
	}

	/**
	 * 数据同步
	 * 
	 * @param event
	 */
	private void sycLogToLoggerCenter(ILoggingEvent event) {
		LoggerMessageBody loggerMessage = new LoggerMessageBody(event.getMessage(),
				DateFormat.getDateTimeInstance().format(new Date(event.getTimeStamp())), event.getThreadName(),
				event.getLoggerName(), event.getLevel().levelStr);
		if (event.getThrowableProxy() != null) {
			String errorMsg = "\r\t" + event.getThrowableProxy().getClassName() + " : "
					+ event.getThrowableProxy().getMessage();
			errorMsg += this.resourceFix(event.getThrowableProxy().getStackTraceElementProxyArray());
			loggerMessage.setBody(loggerMessage.getBody() + errorMsg);
		}
		NettyRestMessage<Object> message = new NettyRestMessage<>();
		message.setBody(loggerMessage);
		message.setHead(LogChannelStore.getInstance().getAuthHead());
		ChannelHandlerContext ctx = LogChannelStore.getInstance().getCtx();
		if (ctx != null)
			ctx.writeAndFlush(message);
	}

	/**
	 * 递归获取异常
	 * 
	 * @param throwableProxy
	 * @return
	 */
	private String resourceFix(StackTraceElementProxy[] stackTraceElementProxy) {
		String errorMsg = "";
		if (stackTraceElementProxy != null) {
			for (StackTraceElementProxy stackTraceElement : stackTraceElementProxy) {
				errorMsg += "\r\t" + stackTraceElement.getSTEAsString();
			}
		}
		return errorMsg;
	}

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public long getReConnectTime() {
		return reConnectTime;
	}

	public void setReConnectTime(long reConnectTime) {
		this.reConnectTime = reConnectTime;
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

	public String getAppName() {
		return appName;
	}

	public void setAppName(String appName) {
		this.appName = appName;
	}

	public LogIntercept getIntercept() {
		return intercept;
	}

	public void setIntercept(LogIntercept intercept) {
		this.intercept = intercept;
	}

	private Boolean isIntercept(String level, String className) {
		if(this.intercept ==null)
			return false;
		Set<String> set = this.intercept.getFilter().keySet();
		OrderArrayList keys = new OrderArrayList();
		//选择最优匹配(key值最长)
		for(String key:set){
			if(className.startsWith(key)){
				keys.add(key);
			}
		}
		//个例优先判断
		if(!keys.isEmpty()){
			if(LogLevelType.valueOf(level.toUpperCase()).getWeight()>=this.intercept.getFilter().get(keys.get(0)).getWeight()){
				return false;
			}else if(this.level == null){
				return true;
			}
		}
		//全局判断
		if(this.level!=null && LogLevelType.valueOf(level.toUpperCase()).getWeight()<this.level.getWeight()){
			return true;
		}else{
			return false;
		}
	}
	
}
