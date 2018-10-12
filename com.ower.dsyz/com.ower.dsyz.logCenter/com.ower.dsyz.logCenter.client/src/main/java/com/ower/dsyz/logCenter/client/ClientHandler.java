package com.ower.dsyz.logCenter.client;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.ower.dsyz.logCenter.bean.NettyRestHead;
import com.ower.dsyz.logCenter.bean.NettyRestMessage;
import com.ower.dsyz.logCenter.client.service.ILogClientService;
import com.ower.dsyz.logCenter.client.service.impl.LogClientServiceImpl;
import com.ower.dsyz.logCenter.constant.NettyMessageType;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.ChannelHandler.Sharable;


@Sharable
public class ClientHandler extends ChannelInboundHandlerAdapter  {

	private Logger log = LoggerFactory.getLogger(this.getClass());
	/**
	 * 应用名
	 */
	private String appName;
	
    /**
     * 重连时间时间
     */
	private long reConnectTime;
	
	private ILogClientService logClientService = new LogClientServiceImpl();
	
	@Override
    public void channelRegistered(ChannelHandlerContext ctx) throws Exception {
      
    }
	public ClientHandler(String appName,long reConnectTime){
		super();
		this.appName = appName;
		this.reConnectTime = reConnectTime;
	}
    @Override
    public void channelUnregistered(ChannelHandlerContext ctx) throws Exception {
  
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
    
    }

    // 连接成功后，向server发送消息
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        NettyRestMessage<Object> msg = new NettyRestMessage<>();
        msg.setBody("");
        msg.setHead(new NettyRestHead());
        msg.getHead().setAppName(appName);
        msg.getHead().setType(NettyMessageType.LOGIN);
        ctx.write(msg);
        ctx.flush();
        LogCenterClient.getInstance().resetStatus(reConnectTime);
    }

    // 接收server端的消息，并打印出来
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
    	logClientService.readNettyMsg((NettyRestMessage<?>) msg, ctx);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
    	 log.debug("服务器连接中断{}",cause);
    	ctx.close();
    }

    
    
    
}

