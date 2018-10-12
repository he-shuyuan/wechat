package com.ower.dsyz.logCenter.client.util;

import com.ower.dsyz.logCenter.bean.NettyRestHead;
import com.ower.dsyz.logCenter.client.appender.MyAppender;
import com.ower.dsyz.logCenter.constant.NettyMessageType;

import io.netty.channel.ChannelHandlerContext;

public class LogChannelStore {

	private MyAppender myAppender;
	/**
	 * 单例
	 */
	private static LogChannelStore instance = new LogChannelStore();
	/**
	 * 通道
	 */
	private ChannelHandlerContext ctx;

	/**
	 * 有效头部
	 */
	private NettyRestHead authHead;

	private LogChannelStore() {
	}

	public static LogChannelStore getInstance() {
		return instance;
	}

	public ChannelHandlerContext getCtx() {
		return ctx;
	}

	public void setCtx(ChannelHandlerContext ctx) {
		this.ctx = ctx;
	}

	public NettyRestHead getAuthHead() {
		return authHead;
	}

	public void setAuthHead(NettyRestHead authHead) {
		if (authHead != null) {
			authHead.setType(NettyMessageType.MESS);
		}
		this.authHead = authHead;
	}

	public MyAppender getMyAppender() {
		return myAppender;
	}

	public void setMyAppender(MyAppender myAppender) {
		this.myAppender = myAppender;
	}

}
