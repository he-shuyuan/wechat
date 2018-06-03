package com.ower.dsyz.gateway.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.ower.dsyz.common.core.response.CustomResponse;
import com.ower.dsyz.common.core.util.ParamCheckUtil;
import com.ower.dsyz.gateway.model.GatewayRequest;
import com.ower.dsyz.gateway.service.IGatewayLevelService;

@Service(IGatewayLevelService.BEAN_NAME_PRIFIX + "outer")
public class GatewayOuterServiceImpl extends AbstractGatewayLevelService {

	private Logger logger = LoggerFactory.getLogger(GatewayRequestFactoryImpl.class);

	@Override
	Map<String, String> excute(GatewayRequest request) {
		logger.info("{} 级别，不能需要登陆", request.getServiceLevel());
		ParamCheckUtil.checkEmpty(request.getSign(), "签名不能为空");
		Map<String, String> header = new HashMap<>();
		//header.put("userId", userId);
		return header;
	}

	@SuppressWarnings("rawtypes")
	@Override
	void decorateResult(CustomResponse<Map> result, GatewayRequest request) {
		// TODO Auto-generated method stub
		
	}



}
