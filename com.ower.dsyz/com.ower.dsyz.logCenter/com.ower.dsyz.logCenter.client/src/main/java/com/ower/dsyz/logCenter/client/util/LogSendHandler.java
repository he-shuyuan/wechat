package com.ower.dsyz.logCenter.client.util;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import com.ower.dsyz.common.core.util.Jackson;
import com.ower.dsyz.logCenter.bean.LoggerRestMessage;

/**
 * 日志保存
 * @author Administrator
 *
 */
@Service
public class LogSendHandler {
    
    @Value("${log.center.save:http://127.0.0.1:8384/logReceiveService/save}")
	private String httpUrl;
	
	private int failCount = 0;
	
	
	public void save(LoggerRestMessage<?> message){
		LogSendThreadPoolUtil.poolExecutor.execute(new Runnable() {
			@Override
			public void run() {
				try {
					LogSendHandler.this.post(httpUrl, Jackson.toJson(message));
				} catch (Exception e) {
					LogSendHandler.this.updateFail();
					e.printStackTrace();
				}
			}
		});
	     
	}
	
	private void post(String httpUrl, String requestData) throws Exception{
		  URL url = null;
	        try {
	            url = new URL(httpUrl);
	        } catch (MalformedURLException e) {
	            throw new Exception("访问地址错误！", e);
	        }
	        URLConnection conn = url.openConnection();
	        conn.setDoOutput(true);
	        conn.setRequestProperty("Content-Type", "application/json;charset=UTF-8");
	        conn.setRequestProperty("Accept-Charset", "UTF-8");
	        conn.setRequestProperty("Accept-Language", "zh-cn");
	        OutputStreamWriter out = new OutputStreamWriter(conn.getOutputStream(), "UTF-8");
	        if (requestData != null) {
	            try {
	                out.write(requestData);
	                out.flush();
	            } finally {
	                out.close();
	            }
	        }
	        BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
	        try {
	            while ((reader.readLine()) != null) {}
	        } finally {
	            reader.close();
	        }
	}
	
	
	private synchronized void  updateFail(){
		failCount++;
		if(failCount>5){
			new Thread(){
				public void start(){
					try {
						sleep(1000*60*30L);
						failCount = 0;
					} catch (InterruptedException e) {
						
					}
				}
			}.start();
		}
	}
}
