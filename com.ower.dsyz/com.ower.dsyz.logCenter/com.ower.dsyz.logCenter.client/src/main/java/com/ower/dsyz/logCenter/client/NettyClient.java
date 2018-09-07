package com.ower.dsyz.logCenter.client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import io.netty.bootstrap.Bootstrap;  
import io.netty.channel.ChannelFuture;  
import io.netty.channel.ChannelInitializer;  
import io.netty.channel.ChannelOption;  
import io.netty.channel.EventLoopGroup;  
import io.netty.channel.nio.NioEventLoopGroup;  
import io.netty.channel.socket.SocketChannel;  
import io.netty.channel.socket.nio.NioSocketChannel;  

@Service
public class NettyClient {  
    /**
     * 
     */
	@Autowired
	MessageClientHandler messageClientHandler;
	
	@Value("${log.center.netty.ip:127.0.0.1}")
	private String ip;
	
	@Value("${log.center.netty.port:9999}")
	private String port;
	

	
    private void connect(String host, int port) throws Exception {  
        EventLoopGroup workerGroup = new NioEventLoopGroup();  
  
        try {  
            Bootstrap b = new Bootstrap();  
            b.group(workerGroup);  
            b.channel(NioSocketChannel.class);  
            b.option(ChannelOption.SO_KEEPALIVE, true);  
            b.handler(new ChannelInitializer<SocketChannel>() {  
                @Override  
                public void initChannel(SocketChannel ch) throws Exception {  
                    ch.pipeline().addLast(messageClientHandler);  
                }  
            });  
            // Start the client.  
            ChannelFuture f = b.connect(host, port).sync();  
  
            f.channel().closeFuture().sync();  
        } finally {  
            workerGroup.shutdownGracefully();  
        }  
    }  
    
    public void connect() throws NumberFormatException, Exception{
    	this.connect(ip, Integer.parseInt(port));
    }
    
    public static void main(String[] args) throws Exception {  
    	NettyClient client=new NettyClient();  
        client.connect("127.0.0.1", 9999);  
    }  
      
} 