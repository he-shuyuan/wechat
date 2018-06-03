package com.ower.dsyz.gateway.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.ower.dsyz.common.core.response.CustomResponse;
import com.ower.dsyz.gateway.model.GatewayRequest;
import com.ower.dsyz.gateway.service.IGatewayLevelService;

@Service(IGatewayLevelService.BEAN_NAME_PRIFIX+"back")
public class GatewayBackServiceImpl extends AbstractGatewayLevelService{

	private Logger logger = LoggerFactory.getLogger(GatewayRequestFactoryImpl.class);
	
	@Override
	Map<String, String> excute(GatewayRequest request) {
		logger.info("{} 级别，需要登陆",request.getServiceLevel());
		String userId = checkToken(request.getToken());
		Map<String,String> header = new HashMap<>();
		header.put("userId", userId);
		return header;
	}
	
	private String checkToken(String token){
		return "1";
	}

	@SuppressWarnings("rawtypes")
	@Override
	void decorateResult(CustomResponse<Map> result, GatewayRequest request) {
		// TODO Auto-generated method stub
		
	}


}

