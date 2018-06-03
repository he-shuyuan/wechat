package com.ower.dsyz.gateway.service;

import com.ower.dsyz.gateway.model.GatewayRequest;

/**
 * 网关层级分派工厂
 * @author 独树一帜
 *
 */
public interface IGatewayRequestFactory {

    /**
     * 
     * @param request
     * @return
     */
	Object handleRequest(GatewayRequest request);


}
