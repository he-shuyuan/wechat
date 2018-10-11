package com.ower.dsyz.logCenter.client.util;

import com.ower.dsyz.logCenter.bean.NettyRestHead;
import com.ower.dsyz.logCenter.constant.NettyMessageType;

import io.netty.channel.ChannelHandlerContext;

public class ChannelStore {

	/**
	 * 单例
	 */
	private static ChannelStore instance = new ChannelStore();
	/**
	 * 通道
	 */
	private ChannelHandlerContext ctx;

	/**
	 * 有效头部
	 */
	private NettyRestHead authHead;

	private ChannelStore() {
	}

	public static ChannelStore getInstance() {
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

}
