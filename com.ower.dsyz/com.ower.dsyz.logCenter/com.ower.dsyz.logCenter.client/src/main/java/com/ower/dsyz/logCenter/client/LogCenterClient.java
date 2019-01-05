package com.ower.dsyz.logCenter.client;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.ower.dsyz.logCenter.commonpent.coder.ByteToNettyRestMessageDecoder;
import com.ower.dsyz.logCenter.commonpent.coder.NettyRestMessageToByteEncoder;
import com.ower.dsyz.logCenter.constant.NettyConstant;
import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.DelimiterBasedFrameDecoder;

public class LogCenterClient {
	/**
	 * 单例
	 */
	private static LogCenterClient instance;
	
	/**
	 * 重连时间
	 */
	private long reConnectTime = 2L;
    /**
     * 错误重连次数
     */
	private int num=0;
	
	private Logger log = LoggerFactory.getLogger(this.getClass());
	
	private ClientHandler clientHandler;
	
	private LogCenterClient(String appName,long reConnectTime){
		this.reConnectTime = reConnectTime;
		this.clientHandler = new ClientHandler(appName,reConnectTime);
	}
	
	public synchronized static LogCenterClient getInstance(String appName,long reConnectTime){
		if(instance == null){
			instance = new LogCenterClient(appName,reConnectTime);
		}
		return instance;
	}
	
	public synchronized static LogCenterClient getInstance(){
		return instance;
	}
	/**
	 * 连接服务器
	 * @param reConnectTime 单位 s
	 * @param port
	 * @param host
	 * @throws Exception
	 */
	public synchronized void  connect(String host,int port){
		// 配置客户端NIO线程组
		EventLoopGroup group = new NioEventLoopGroup();
		try {
			// 客户端辅助启动类 对客户端配置
			Bootstrap b = new Bootstrap();
			b.group(group)//
					.channel(NioSocketChannel.class)//
					.option(ChannelOption.TCP_NODELAY, true)//
					.handler(new ClientChannelHandler());//
			// 异步链接服务器 同步等待链接成功
			ChannelFuture f = b.connect(host, port).sync();
			// 等待链接关闭
			f.channel().closeFuture().sync();
		}catch(Exception ex){
		}finally {
			group.shutdownGracefully();
			reConnect(host,port);
		}
 
	}
	
	
	/**
	 * 重连
	 * @param reConnetTime
	 */
	private synchronized void  reConnect(String host,int port){
		new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					log.debug("【连接服务器错误，第{}次重连在({}s)后开始】",++num,reConnectTime);
					Thread.sleep(reConnectTime*1000);
					reConnectTime *=2;
					log.debug("【连接服务器错误，第{}次开始尝试重连】",num);
					if(num>=12){
						reConnectTime/=(long) Math.pow(2*1.0, num*1.0);
					}
					LogCenterClient.this.connect(host,port);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				
			}
		}).start();
	}
 
	/**
	 * 网络事件处理器
	 */
	private class ClientChannelHandler extends
			ChannelInitializer<SocketChannel> {
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
			// 客户端的处理器
			ch.pipeline().addLast(clientHandler);
		}
 
	}
     public void resetStatus(long reConnectTime){
    	 this.num = 0;
    	 this.reConnectTime = reConnectTime;
     }
}

