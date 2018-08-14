/**
 * Copyright(c) Foresee Science & Technology Ltd. 
 */
package com.ower.dsyz.gateway.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ower.dsyz.gateway.manual.dao.GatewayAuthUrlExtMapper;
import com.ower.dsyz.gateway.manual.dto.GatewayAuthUrlDTO;
import com.ower.dsyz.gateway.service.IGatewayAuthUrlService;


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
public class GatewayAuthUrlServiceImpl implements IGatewayAuthUrlService {

    @Autowired
    private GatewayAuthUrlExtMapper gatewayAuthUrlExtMapper;
    
    @Override
    public List<GatewayAuthUrlDTO> queryGatewayAuthUrlDTOList(List<String> list) {

        return gatewayAuthUrlExtMapper.queryGatewayAuthUrlDTOList(list);
    }

}
