package com.ower.dsyz.common.core.proxy.request;

import java.util.Map;


/**
 * 抽象远程请求
 * @author heshuyuan
 *
 */
public abstract class AbstractRemoteRequest {

	
	
	/**
	 * post请求
	 * @return
	 */
	public abstract Object doPost(Map<String,Object> param);
	
	
	/**
	 * post请求
	 * @param param
	 * @return
	 */
	public abstract Object doGet(Map<String,Object> param);
	

}
