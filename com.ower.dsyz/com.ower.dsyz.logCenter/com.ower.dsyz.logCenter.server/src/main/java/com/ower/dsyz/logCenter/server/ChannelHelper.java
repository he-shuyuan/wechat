package com.ower.dsyz.logCenter.server;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import io.netty.channel.ChannelHandlerContext;

public class ChannelHelper {
    
	/**
	 * 所有接入应用
	 */
	private static Map<String,List<ChannelHandlerContext>> appMap = new ConcurrentHashMap<>();
	
	
	
	/**
	 * 添加应用
	 * @param ctx
	 */
	public static void addAppChannel(String app,ChannelHandlerContext ctx){
		if(!appMap.containsKey(app)){
		    appMap.put(app,new ArrayList<>());
		}
		appMap.get(app).add(ctx);
	}
	
	/**
	 * 通过appName获取channel
	 * @param app
	 * @return
	 */
	public static List<ChannelHandlerContext> getChannelByApp(String app){
		if(!appMap.containsKey(app)){
		    return new ArrayList<>();
		}
		return appMap.get(app);
	}
	
	/**
	 * 获取所有channel
	 * @return
	 */
	public static List<ChannelHandlerContext> getAllChannel(){
		List<ChannelHandlerContext> list = new ArrayList<>();
		for(Map.Entry<String, List<ChannelHandlerContext>> entry:appMap.entrySet()){
			list.addAll(entry.getValue());
		}
		return list;
	}
	/**
	 * 清空channel
	 */
	public static void removeAll(){
		appMap.clear();
	}
	
	/**
	 * 移除应用
	 * @param app
	 */
	public static void removeApp(String app){
		if(appMap.containsKey(app)){
		     appMap.remove(app);
		}
	}
	
	/**
	 * 移除指定channel
	 * @param ctx
	 */
	public static void removeChannel(ChannelHandlerContext ctx){
		for(Map.Entry<String, List<ChannelHandlerContext>> entry:appMap.entrySet()){
			if(entry.getValue().contains(ctx)){
				entry.getValue().remove(ctx);
			}
		}
	}
}
