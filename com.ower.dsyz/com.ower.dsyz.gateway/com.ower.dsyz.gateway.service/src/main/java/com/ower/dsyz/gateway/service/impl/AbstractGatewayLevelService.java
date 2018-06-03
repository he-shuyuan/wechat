package com.ower.dsyz.gateway.service.impl;

import java.util.Map;


import org.springframework.beans.factory.annotation.Autowired;

import com.ower.dsyz.common.core.response.CustomResponse;
import com.ower.dsyz.common.core.rest.impl.ICustomRestClient;
import com.ower.dsyz.gateway.model.GatewayRequest;
import com.ower.dsyz.gateway.service.IGatewayLevelService;

/**
 * 层级抽象类
 * @author 独树一帜
 *
 */
public abstract class AbstractGatewayLevelService implements IGatewayLevelService{

    @Autowired
    private ICustomRestClient  customRestClient;
    
	@SuppressWarnings("rawtypes")
	@Override
	public Object handleRequestLevel(GatewayRequest request) {
		Map<String,String> extHeader = excute(request);
		CustomResponse<Map> result = customRestClient.postInner(request.getUrl(),request.getParam(),Map.class,extHeader);
		decorateResult(result,request);
		return result;
	}

	
	abstract Map<String,String> excute(GatewayRequest request);
	
	
	@SuppressWarnings("rawtypes")
	abstract void decorateResult(CustomResponse<Map> result,GatewayRequest request);
}
