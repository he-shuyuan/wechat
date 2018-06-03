package com.ower.dsyz.gateway.service;

import com.ower.dsyz.gateway.model.GatewayRequest;

/**
 * 网关层级权限接口
 * @author 独树一帜
 *
 */
public interface IGatewayLevelService {
    /**
     * 实例前缀
     */
	public static final String BEAN_NAME_PRIFIX="gatewayLevelService_";
	
	Object handleRequestLevel(GatewayRequest request);
}
