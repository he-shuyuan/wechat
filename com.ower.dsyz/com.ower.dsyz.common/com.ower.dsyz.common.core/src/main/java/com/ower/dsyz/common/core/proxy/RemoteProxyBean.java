package com.bda.chongqing.core.proxy;
import java.util.HashMap;
import java.util.Map;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.beans.factory.FactoryBean;
import com.alibaba.fastjson.JSON;
import com.bda.chongqing.core.proxy.anno.RequestInfo;
import com.bda.chongqing.core.proxy.request.HttpResp;
import com.bda.chongqing.core.proxy.request.RemoteRequestHolder;
import com.bda.chongqing.core.proxy.util.JSONUtil;

/**
 * 远程调用代理类
 * @author heshuyuan
 *
 */
public  class RemoteProxyBean  implements MethodInterceptor,FactoryBean<Object> {

	private static final Logger log = LoggerFactory.getLogger(RemoteProxyBean.class);
	
	public final static String BEAN_NAME_PREFIX = "remoteProxy@";
	/**
	 * 被代理的接口
	 */
	private Class<? extends IVirtualProxyService> proxyClass;
	
	/**
	 * bean对象
	 */
	private Object proxyObject; 
	
	public RemoteProxyBean(Class<? extends IVirtualProxyService> proxyClass){
		this.proxyClass = proxyClass; 
	}
	
	public Object getObject() throws Exception {
		if(proxyObject == null){
			proxyObject = new ProxyFactory(proxyClass, this).getProxy();
		}
		return  proxyObject;
	}

	public Class<?> getObjectType() {
		return this.proxyClass;
	}

	public boolean isSingleton() {
		return true;
	}
	
	public Object invoke(MethodInvocation invocation) throws Throwable {
		return excute(invocation);
	}
	

	private Object excute(MethodInvocation invocation) throws InstantiationException, IllegalAccessException{
		System.err.print(invocation.getMethod().getAnnotations().length);
		if(!invocation.getMethod().isAnnotationPresent(RequestInfo.class)){
			log.error("无效的请求,{}",invocation.getMethod().getName());
			return null;
		}
		RequestInfo requestInfo = invocation.getMethod().getAnnotation(RequestInfo.class);
		Map<String,Object> data = buildParam(invocation.getArguments());
		Map<String,Object> param = new HashMap<String,Object>();
		param.put("param", data);
		param.put("url", requestInfo.value());
		param.put("contentType", requestInfo.contentType());
		Object obj = null;
 		if("post".equalsIgnoreCase(requestInfo.method())){
		   obj = RemoteRequestHolder.get().doPost(param);
		}else if ("get".equalsIgnoreCase(requestInfo.method())){
		   obj = RemoteRequestHolder.get().doGet(param);
		}else{
			log.error("非法的请求,{}",requestInfo.method());
		}
 	    if(obj == null){
 	    	  return  invocation.getMethod().getReturnType().newInstance();
 	    }else{
 	    	HttpResp httpResp = JSON.parseObject(obj.toString(), HttpResp.class);
 	    	if(invocation.getMethod().getReturnType().isArray()){
 	    		return JSON.parseArray(httpResp.getData(), (Class<?>)(invocation.getMethod().getReturnType().getGenericInterfaces()[0]));
 	    	}else{
 	    		return JSON.parseObject(httpResp.getData(), invocation.getMethod().getReturnType());
 	    	}
 	    }
	}
	
	
	public Map<String,Object> buildParam(Object... params){
		Map<String,Object> map = new HashMap<String,Object>();
		for(Object param:params){
			try{
			  map.putAll(JSONUtil.ObjectToMap(param));
			}catch(Exception ex){
				log.error("参数错误,{},{}",param,ex);
			}
		}
		return map;
	}
}
