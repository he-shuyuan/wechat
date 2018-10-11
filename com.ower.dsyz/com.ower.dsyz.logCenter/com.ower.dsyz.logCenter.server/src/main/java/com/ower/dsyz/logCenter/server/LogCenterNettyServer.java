package com.ower.dsyz.logCenter.server;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ower.dsyz.logCenter.commonpent.coder.ByteToNettyRestMessageDecoder;
import com.ower.dsyz.logCenter.commonpent.coder.NettyRestMessageToByteEncoder;
import com.ower.dsyz.logCenter.constant.NettyConstant;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.DelimiterBasedFrameDecoder;
 
@Service
public class LogCenterNettyServer {
	
	private Logger log = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	ServerHandler serverHandler;
	
	public void bind(int port) throws Exception {
		// 服务器线程组 用于网络事件的处理 一个用于服务器接收客户端的连接
		// 另一个线程组用于处理SocketChannel的网络读写
		EventLoopGroup bossGroup = new NioEventLoopGroup();
		EventLoopGroup workerGroup = new NioEventLoopGroup();
		try {
			// NIO服务器端的辅助启动类 降低服务器开发难度
			ServerBootstrap serverBootstrap = new ServerBootstrap();
			serverBootstrap.group(bossGroup, workerGroup)
					.channel(NioServerSocketChannel.class)// 类似NIO中serverSocketChannel
					.option(ChannelOption.SO_BACKLOG, 1024)// 配置TCP参数
					.option(ChannelOption.SO_BACKLOG, 1024) // 设置tcp缓冲区
					.option(ChannelOption.SO_SNDBUF, 32 * 1024) // 设置发送缓冲大小
					.option(ChannelOption.SO_RCVBUF, 32 * 1024) // 这是接收缓冲大小
					.option(ChannelOption.SO_KEEPALIVE, true) // 保持连接
					.childHandler(new ChildChannelHandler());// 最后绑定I/O事件的处理类
																// 处理网络IO事件
 
			// 服务器启动后 绑定监听端口 同步等待成功 主要用于异步操作的通知回调 回调处理用的ChildChannelHandler
			ChannelFuture f = serverBootstrap.bind(port).sync();
			log.debug("netty服务器启动成功");
			// 等待服务端监听端口关闭
			f.channel().closeFuture().sync();
 
		} finally {
			// 优雅退出 释放线程池资源
			bossGroup.shutdownGracefully();
			workerGroup.shutdownGracefully();
			log.debug("服务器优雅的释放了线程资源...");
		}
 
	}
 
	/**
	 * 网络事件处理器
	 */
	private class ChildChannelHandler extends ChannelInitializer<SocketChannel> {
		@Override
		protected void initChannel(SocketChannel ch) throws Exception {
			ByteBuf delimiter=Unpooled.copiedBuffer(NettyConstant.DELIMITER.getBytes());				  
			//创建DelimiterBasedFrameDecoder对象，加入到ChannelPipeline中，它有两个参数，第一个参数表示单条消息的				 
			//最大长度，当达到该长度的后仍然没有查找到分隔符，就抛出TooLongFrameException异常，防止由于异常码流缺失分隔符导致的内存溢出				  
			//这是Netty解码器的可靠性保护；第二个参数就是分隔符缓冲对象					
			ch.pipeline().addLast(new DelimiterBasedFrameDecoder(10*1024, delimiter));
			// 增加自定义的编码器和解码器
			ch.pipeline().addLast(new NettyRestMessageToByteEncoder());
			ch.pipeline().addLast(new ByteToNettyRestMessageDecoder());
			// 服务端的处理器
			ch.pipeline().addLast(serverHandler);
		}
	}
 
/*	public static void main(String[] args) throws Exception {
		int port = 9998;
		new LogCenterNettyServer().bind(port);
	}*/
}
