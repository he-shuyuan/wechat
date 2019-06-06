package com.ower.dsyz.zuul.filter;
import org.apache.commons.lang3.StringUtils;
import org.bouncycastle.cert.ocsp.Req;
import org.springframework.stereotype.Component;

import com.netflix.zuul.context.RequestContext;
import com.ower.dsyz.zuul.RequestMessage;


/**
 * 通用前置拦截器
 * 
 * @author heshuyuan
 *
 */
@Component
public class CustomPreZuulFilter extends AbstractZuulFilter {

	@Override
	public String filterType() {
		return "pre";
	}

	@Override
	protected void doSomething() {
		RequestMessage request = getRequestMessage();
		//参数检验
		checkParam(request);
        //根据请求执行
		doByRequestMessage(request);
		
		
	}

	private void doByRequestMessage(RequestMessage request) {
		if(request.getLevel().equals("back")){//登陆
			checkToken(request.getToken());
		}
	}

	private void checkToken(String token) {
		if(StringUtils.isBlank(token)){
			endRequest("15401","用户未登录");
		}
		RequestContext.getCurrentContext().addZuulRequestHeader("userId", "XXX");
	}

	private void checkParam(RequestMessage request) {
		if (StringUtils.isBlank(request.getAppId())) {
			endRequest("appId 不能为空");
		}
		if (StringUtils.isBlank(request.getRequestId())) {
			endRequest("request 不能为空");
		}
		if (request.getRequestId().length() != 32) {
			endRequest("requestId 非法");
		}
	}

}
