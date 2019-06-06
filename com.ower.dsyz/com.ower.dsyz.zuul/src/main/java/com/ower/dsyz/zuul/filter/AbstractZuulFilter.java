package com.ower.dsyz.zuul.filter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import com.ower.dsyz.common.core.exception.BusinessException;
import com.ower.dsyz.common.core.response.CustomResponse;
import com.ower.dsyz.common.core.util.Jackson;
import com.ower.dsyz.zuul.RequestMessage;




public abstract class AbstractZuulFilter extends ZuulFilter {

	private String ZUUL_ERROR_CODE = "1501";
	
	
	@Override
	public int filterOrder() {
		return 0;
	}

	@Override
	public boolean shouldFilter() {
		return true;
	}
	
	@Override
	public Object run() throws ZuulException{
	     try{
	    	 doSomething();
	     }catch(Exception ex){
	    	 if(!(ex instanceof BusinessException)){
	    		 throw ex;
	    	 }
	     }
	     return null;
		
	}
	
	/**
	 * 操作
	 */
	protected abstract void doSomething();
	
	protected HttpServletRequest getRequest() {
		RequestContext ctx = RequestContext.getCurrentContext();
		return ctx.getRequest();
	}

	protected HttpServletResponse getResponse() {
		RequestContext ctx = RequestContext.getCurrentContext();
		return ctx.getResponse();
	}

	
	/**
	 * 中断请求
	 */
	protected void endRequest() {
		endRequest("系统异常");
	}

	
	/**
	 * 中断请求
	 */
	protected void endRequest(String msg) {
		endRequest(ZUUL_ERROR_CODE, msg);
	}
	
	protected void endRequest(String code, String errmsg) {
		// json
		getResponse().setContentType("application/json; charset=utf-8");
		RequestContext.getCurrentContext().setSendZuulResponse(false);
		CustomResponse<?> customResponse = CustomResponse.error(code, errmsg);
		customResponse.getHead().setRequestId(getParameter("requestId"));
		RequestContext.getCurrentContext().setResponseBody(Jackson.toJson(customResponse));
		throw new BusinessException("");
	}
	
	
    protected String getParameter(String key){
    	return getRequest().getParameter(key);
    }
    
    protected RequestMessage getRequestMessage(){
    	RequestMessage request = new RequestMessage();
    	request.setUri(trim("/",getRequest().getRequestURI()));
    	String[] uri = request.getUri().split("/");
    	if(uri.length<4){
    		endRequest("非法请求");
    	}
    	request.setAppName(uri[0]);
    	request.setLevel(uri[1]);
    	request.setServer(uri[2]);
    	request.setMethod(uri[3]);
    	request.setAppId(getParameter("appId"));
    	request.setToken(getParameter("token"));
    	request.setRequestId(getParameter("requestId"));
    	return request;
    }
    
    
    private String trim(String str,String aimStr){
    	if(!aimStr.startsWith(str) && !aimStr.endsWith(str)){
    		return aimStr;
    	}
    	if(aimStr.startsWith(str)){
    		aimStr=aimStr.substring(str.length(),aimStr.length());
    	}
    	if(aimStr.endsWith(str)){
    		aimStr=aimStr.substring(0,aimStr.length()-str.length());
    	}
    	return trim(str,aimStr);
    }
}
