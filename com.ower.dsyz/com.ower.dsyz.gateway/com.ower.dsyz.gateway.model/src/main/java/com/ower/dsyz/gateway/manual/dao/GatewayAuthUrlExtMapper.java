package com.ower.dsyz.gateway.manual.dao;

import java.util.List;
import com.ower.dsyz.gateway.manual.dto.GatewayAuthUrlDTO;

public interface GatewayAuthUrlExtMapper {

    /**
     * 通过url获取权限url
     * TODO。
     * @param urlList
     * @return List<GatewayAuthUrlDTO>
     */
    List<GatewayAuthUrlDTO> queryGatewayAuthUrlDTOList(List<String> list);
}