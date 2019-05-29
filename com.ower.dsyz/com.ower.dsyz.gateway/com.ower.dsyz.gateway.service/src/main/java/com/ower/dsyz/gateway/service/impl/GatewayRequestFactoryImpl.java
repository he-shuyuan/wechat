package com.ower.dsyz.gateway.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang3.StringUtils;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ower.dsyz.common.base.util.SpringContextUtil;
import com.ower.dsyz.common.core.exception.BusinessException;
import com.ower.dsyz.gateway.manual.dto.GatewayAuthUrlDTO;
import com.ower.dsyz.gateway.manual.dto.GatewayRequest;
import com.ower.dsyz.gateway.service.IGatewayAuthUrlService;
import com.ower.dsyz.gateway.service.IGatewayLevelService;
import com.ower.dsyz.gateway.service.IGatewayRequestFactory;

/**
 * 
 * @author 独树一帜
 *
 */
@Service
public class GatewayRequestFactoryImpl implements IGatewayRequestFactory {


    private Map<String, IGatewayLevelService> gatewayLevelService;

    @Autowired
    private SpringContextUtil                 springContextUtil;

    @Autowired
    private IGatewayAuthUrlService gatewayAuthUrlService;
    
    private synchronized Map<String, IGatewayLevelService> getGatewayLevelService() {
        if (gatewayLevelService == null) {
              gatewayLevelService = new HashMap<>();
              gatewayLevelService.put("back", springContextUtil.getContext().getBean(GatewayWebServiceImpl.class));
              gatewayLevelService.put("front", springContextUtil.getContext().getBean(GatewayWebServiceImpl.class));
              gatewayLevelService.put("outer", springContextUtil.getContext().getBean(GatewayOuterServiceImpl.class));
        }
        return gatewayLevelService;
    }

    @Override
    public IGatewayLevelService createRequestHandle(GatewayRequest request) {
        List<String> list = new ArrayList<>();
        list.add("/"+request.getAppName());
        list.add(list.get(list.size()-1)+"/"+request.getServiceLevel());
        list.add(list.get(list.size()-1)+"/"+request.getServiceName());
        list.add(list.get(list.size()-1)+"/"+request.getServiceMethod());
        if(StringUtils.isNotBlank(request.getExtMenthod())){
           list.add(list.get(list.size()-1)+"/"+request.getExtMenthod());
        }
        List<GatewayAuthUrlDTO> result = gatewayAuthUrlService.queryGatewayAuthUrlDTOList(list);
        if(!result.isEmpty()){
            try {
               return (IGatewayLevelService) springContextUtil.getContext().getBean(Class.forName(result.get(0).getClassName()));
            } catch (BeansException e) {
                e.printStackTrace();
                throw new BusinessException("007404","找不到对应的bean");
            } catch (ClassNotFoundException e) { 
                e.printStackTrace();
                throw new BusinessException("007404","找不到对应的Class");
            } 
        }
        IGatewayLevelService impl = this.getGatewayLevelService().get(request.getServiceLevel());
        if(impl==null){
            throw new BusinessException("007404","找不到对应的bean,非法级别["+request.getServiceLevel()+"]");
        }
        return impl;
        
    }
}
