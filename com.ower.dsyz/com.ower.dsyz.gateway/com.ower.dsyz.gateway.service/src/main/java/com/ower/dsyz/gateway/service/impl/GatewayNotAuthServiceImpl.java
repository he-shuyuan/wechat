/**
 * Copyright(c) Foresee Science & Technology Ltd.
 */
package com.ower.dsyz.gateway.service.impl;

import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ower.dsyz.common.core.response.CustomResponse;
import com.ower.dsyz.common.core.rest.ICustomRestClient;
import com.ower.dsyz.gateway.manual.dto.GatewayRequest;

/**
 * <pre>
 * TODO。
 * </pre>
 *
 * @author HeShuyuan@foresee.com.cn
 * @date 2018年8月14日
 * @version 1.00.00
 * 
 *          <pre>
 * 修改记录 
 *    修改后版本:     修改人：  修改日期:     修改内容:
 *          </pre>
 */
@Service
public class GatewayNotAuthServiceImpl extends AbstractGatewayLevelService {

    private Logger            logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private ICustomRestClient customRestClient;


    @Override
    CustomResponse<?> resultHandle(GatewayRequest request) {
        logger.info("url={}，不需要需要登陆", request.getUrl());
        return customRestClient.postInner(request.getUrl(), request.getParam(), Map.class);
    }

}
