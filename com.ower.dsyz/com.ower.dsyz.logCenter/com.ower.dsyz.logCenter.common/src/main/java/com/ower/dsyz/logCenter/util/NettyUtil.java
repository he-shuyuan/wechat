package com.ower.dsyz.logCenter.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetSocketAddress;

import io.netty.channel.ChannelHandlerContext;

public class NettyUtil {
	/**
	 * object转byte
	 * @param obj
	 * @return
	 */
	public static byte[] toByteArray(Object obj,String delimiter) {
		
		byte[] bytes = null;
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		try {
			ObjectOutputStream oos = new ObjectOutputStream(bos);
			oos.writeObject(obj);
			oos.writeBytes(delimiter);
			oos.flush();
			bytes = bos.toByteArray();
			oos.close();
			bos.close();
		} catch (IOException ex) {
			ex.printStackTrace();
		}
		return bytes;
	}
	
	/**
	 * 描述 : <byte[]转Object>. <br>
	 * <p>
	 * <使用方法说明>
	 * </p>
	 * 
	 * @param bytes
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static  <T> T toObject(byte[] bytes) {
		T obj = null;
		try {
			ByteArrayInputStream bis = new ByteArrayInputStream(bytes);
			ObjectInputStream ois = new ObjectInputStream(bis);
			obj = (T) ois.readObject();
			ois.close();
			bis.close();
		} catch (IOException ex) {
			ex.printStackTrace();
			throw new RuntimeException(ex);
		} catch (ClassNotFoundException ex) {
			ex.printStackTrace();
			throw new RuntimeException(ex);
		}
		return obj;
	}
	
	public static String getClientIPIp(ChannelHandlerContext ctx){
		InetSocketAddress insocket = (InetSocketAddress) ctx.channel().remoteAddress();
        String clientIP = insocket.getAddress().getHostAddress();
        return clientIP;
	}
}
