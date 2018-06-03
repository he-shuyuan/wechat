package com.ower.dsyz.gateway.service.impl;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ower.dsyz.common.core.constant.ErrorCodeConstants;
import com.ower.dsyz.common.core.exception.CustomRunTimeException;
import com.ower.dsyz.common.core.util.SpringContextUtil;
import com.ower.dsyz.gateway.model.GatewayRequest;
import com.ower.dsyz.gateway.service.IGatewayLevelService;
import com.ower.dsyz.gateway.service.IGatewayRequestFactory;

/**
 * 
 * @author 独树一帜
 *
 */
@Service
public class GatewayRequestFactoryImpl implements IGatewayRequestFactory {

	private Logger logger = LoggerFactory.getLogger(GatewayRequestFactoryImpl.class);

	private Map<String,IGatewayLevelService> gatewayLevelService;
	
	@Autowired
	private SpringContextUtil springContextUtil;
	
	@Override
	public Object handleRequest(GatewayRequest request) {
		
		return this.handle(request);
	}

	private Object handle(GatewayRequest request) {
		logger.info("网关请求=》{}，{}", request.getUrl(), request.getParam());
		// 记录url请
		// 校验appId合法性
		checkAppId(request.getAppId());
		checkRequestId(request.getRequestId());
		// 处理个层级逻辑
		return this.getHandle(request.getServiceLevel()).handleRequestLevel(request);
	}

	/**
	 * 校验requestId
	 * 
	 * @param requestId
	 */
	private void checkRequestId(String requestId) {
		if (requestId.length() != 32) {
			throw new CustomRunTimeException(ErrorCodeConstants.PARAM_ERROR, "requestId长度必须是32位");
		}
	}

	/**
	 * 检验appId
	 * 
	 * @param appId
	 */
	private void checkAppId(String appId) {
		if (!"10010".equals(appId)) {
			throw new CustomRunTimeException(ErrorCodeConstants.AUTH_ERROR, "非法的appId");
		}
	}

	private Map<String, IGatewayLevelService> getGatewayLevelService() {
		if(gatewayLevelService==null){
			gatewayLevelService  = springContextUtil.getContext().getBeansOfType(IGatewayLevelService.class);
		}
		return gatewayLevelService;
	}
	
	
	private IGatewayLevelService getHandle(String level){
		if(getGatewayLevelService().get(IGatewayLevelService.BEAN_NAME_PRIFIX+level)==null){
			throw new CustomRunTimeException(ErrorCodeConstants.AUTH_ERROR, "非法级别["+level+"]");
		}
		return getGatewayLevelService().get(IGatewayLevelService.BEAN_NAME_PRIFIX+level);
	}
}
