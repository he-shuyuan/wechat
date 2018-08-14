/**
 * Copyright(c) Foresee Science & Technology Ltd. 
 */
package com.ower.dsyz.gateway.service;

import java.util.List;
import com.ower.dsyz.gateway.manual.dto.GatewayAuthUrlDTO;

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

public interface IGatewayAuthUrlService {

    /**
     * 通过url获取权限url
     * TODO。
     * @param urlList
     * @return List<GatewayAuthUrlDTO>
     */
    List<GatewayAuthUrlDTO> queryGatewayAuthUrlDTOList(List<String> list);
}
