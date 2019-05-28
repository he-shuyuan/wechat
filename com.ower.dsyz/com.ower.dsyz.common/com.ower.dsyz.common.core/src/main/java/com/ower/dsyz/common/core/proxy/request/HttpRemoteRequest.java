package com.bda.chongqing.core.proxy.request;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.alibaba.fastjson.JSON;

public class HttpRemoteRequest extends AbstractRemoteRequest {

	@SuppressWarnings("unchecked")
	@Override
	public Object doPost(Map<String, Object> map) {

		String httpUrl = (String) map.get("url");
		Map<String, Object> paramMap = (Map<String, Object>) map.get("param");
		String param = "";
		String charsetName = (String) map.get("charsetName");
		String contentType = (String) map.get("contentType");
		HttpURLConnection connection = null;
		InputStream is = null;
		OutputStream os = null;
		BufferedReader br = null;
		String result = null;
		if (paramMap != null && StringUtils.isNotBlank(contentType) && !"application/json".equalsIgnoreCase(contentType)) {
			for (String key : paramMap.keySet()) {
				param += key+"=" + paramMap.get(key)+"&";
			}
			if(param.length()>0){
				param = param.substring(0, param.length()-1);
			}
		}else if(paramMap != null){
			param = JSON.toJSONString(paramMap);
		}
		try {
			URL url = new URL(httpUrl);
			// 通过远程url连接对象打开连接
			connection = (HttpURLConnection) url.openConnection();
			// 设置连接请求方式
			connection.setRequestMethod("POST");
			// 设置连接主机服务器超时时间：15000毫秒
			connection.setConnectTimeout(15000);
			// 设置读取主机服务器返回数据超时时间：60000毫秒
			connection.setReadTimeout(60000);

			// 默认值为：false，当向远程服务器传送数据/写数据时，需要设置为true
			connection.setDoOutput(true);
			// 默认值为：true，当前向远程服务读取数据时，设置为true，该参数可有可无
			connection.setDoInput(true);
			// 设置传入参数的格式:请求参数应该是 name1=value1&name2=value2 的形式。
			connection.setRequestProperty("Content-Type", StringUtils.isNotBlank(contentType)?contentType:"application/json");
			// 设置鉴权信息：Authorization: XXXXXX
			connection.setRequestProperty("Authorization", "XXXXXX");
			// 通过连接对象获取一个输出流
			os = connection.getOutputStream();
			// 通过输出流对象将参数写出去/传输出去,它是通过字节数组写出的
			os.write(param.getBytes());
			// 通过连接对象获取一个输入流，向远程读取
			if (connection.getResponseCode() == 200) {

				is = connection.getInputStream();
				// 对输入流对象进行包装:charset根据工作项目组的要求来设置
				br = new BufferedReader(new InputStreamReader(is,  StringUtils.isNotBlank(charsetName)?charsetName:"UTF-8"));

				StringBuffer sbf = new StringBuffer();
				String temp = null;
				// 循环遍历一行一行读取数据
				while ((temp = br.readLine()) != null) {
					sbf.append(temp);
					sbf.append("\r\n");
				}
				result = sbf.toString();
			}
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			// 关闭资源
			if (null != br) {
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (null != os) {
				try {
					os.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (null != is) {
				try {
					is.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			// 断开与远程地址url的连接
			connection.disconnect();
		}
		return result;
	}

	@SuppressWarnings("unchecked")
	public Object doGet(Map<String, Object> map) {
		String url = (String) map.get("url");
		Map<String, Object> param = (Map<String, Object>) map.get("param");
		String charsetName = (String) map.get("charsetName");
		HttpURLConnection conn = null;
		BufferedReader rd = null;
		StringBuilder sb = new StringBuilder();
		String line = null;
		String response = null;
		if (param != null) {
			for (String key : param.keySet()) {
				if (url.indexOf("?") < 0) {
					url += "?" + key + "=" + param.get(key);
				} else {
					url += "&" + key + "=" + param.get(key);
				}
			}
		}
		try {
			conn = (HttpURLConnection) new URL(url).openConnection();
			conn.setRequestMethod("GET");
			conn.setDoInput(true);
			conn.setReadTimeout(20000);
			conn.setConnectTimeout(20000);
			conn.setUseCaches(false);
			conn.setRequestProperty("Authorization", "XXXXXX");
			conn.connect();
			if (conn.getResponseCode() == 200) {
				rd = new BufferedReader(new InputStreamReader(conn.getInputStream(), StringUtils.isNotBlank(charsetName)?charsetName:"UTF-8"));
				while ((line = rd.readLine()) != null) {
					sb.append(line);
				}
				response = sb.toString();
			}
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (rd != null) {
					rd.close();
				}
				if (conn != null) {
					conn.disconnect();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return response;
	}

}
