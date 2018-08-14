package com.ower.dsyz.gateway.service.impl;

import java.util.HashMap;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ower.dsyz.common.core.response.CustomResponse;
import com.ower.dsyz.common.core.rest.ICustomRestClient;
import com.ower.dsyz.gateway.manual.dto.GatewayRequest;

/**
 * 
 * <pre>
 * 前端接口
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
public class GatewayWebServiceImpl extends AbstractGatewayLevelService{

	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private ICustomRestClient customRestClient;
	
    @Override
    CustomResponse<?> resultHandle(GatewayRequest request) {
        logger.info("{} 级别，需要登陆",request.getServiceLevel());
        String userId = checkToken(request.getToken());
        Map<String,String> header = new HashMap<>();
        header.put("userId", userId);
        return  customRestClient.postInner(request.getUrl(),request.getParam(),Map.class,header);
    }


}

