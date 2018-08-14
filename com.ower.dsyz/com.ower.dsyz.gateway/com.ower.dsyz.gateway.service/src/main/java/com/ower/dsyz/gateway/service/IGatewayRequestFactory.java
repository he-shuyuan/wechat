package com.ower.dsyz.gateway.service;

import com.ower.dsyz.gateway.manual.dto.GatewayRequest;

/**
 * 网关层级分派工厂
 * @author 独树一帜
 *
 */
public interface IGatewayRequestFactory {

    /**
     * 处理请求
     * @param request
     * @return
     */
    IGatewayLevelService createRequestHandle(GatewayRequest request);


}
