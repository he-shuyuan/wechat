package com.ower.dsyz.logCenter.client;

import org.springframework.beans.BeanUtils;

import com.ower.dsyz.logCenter.bean.NettyRestHead;

import io.netty.channel.ChannelHandlerContext;

public class ChannelHelper {
	/**
	 * 当前channel
	 */
	private static ChannelHandlerContext ctx;

	/**
	 * 验证码
	 */
	private static NettyRestHead head;

	public static ChannelHandlerContext getCtx() {
		return ctx;
	}

	public static synchronized void setCtx(ChannelHandlerContext ctxx) {
		if (ctx == null) {
			ctx = ctxx;
		}
	}

	public static NettyRestHead getHead() {
		NettyRestHead copyHead = new NettyRestHead();
		BeanUtils.copyProperties(head, copyHead);
		return copyHead;
	}

	public static synchronized void setHead(NettyRestHead head) {
		if (ChannelHelper.head == null) {
		ChannelHelper.head = head;
		}
	}

	public static void removeAll(){
		ctx = null;
		head = null;
	}

}
