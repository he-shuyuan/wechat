/**
 * Copyright(c) Foresee Science & Technology Ltd. 
 */
package com.ower.dsyz.wechat.service.dataBase;

import java.util.List;
import com.ower.dsyz.wechat.manual.dto.WechatAppDTO;
import com.ower.dsyz.wechat.manual.dto.WechatMessageDTO;

/**
 * <pre>
 * 二级缓存
 * </pre>
 *
 * @author HeShuyuan@foresee.com.cn
 * @date 2018年1月11日
 * @version 1.00.00
 * 
 *          <pre>
 * 修改记录 
 *    修改后版本:     修改人：  修改日期:     修改内容:
 *          </pre>
 */

public interface IRedisJdbcStorageService {

    
    /**
     * 清楚二级缓存
     * @param reg void
     */
    void cleanBatchStorage(String reg);
    
    /**
     * 清楚二级缓存
     * @param reg void
     */
    void cleanSecondStorage();
    
    /**
     * 获取微信配置
     * @param appId
     * @return WechatAppDTO
     */
    WechatAppDTO getWechatAppByAppId(String appId);
    
    
    /**
     * 获取微信消息
     * @param appId
     * @param eventType
     * @return List<WechatMessageDTO>
     */
    List<WechatMessageDTO> getWechatMessageDTOByAppIdAndEventType(String appId,String eventType);
}
