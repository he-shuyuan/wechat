/**
 * Copyright(c) Foresee Science & Technology Ltd. 
 */
package com.ower.dsyz.wechat.service;

import java.util.List;
import com.ower.dsyz.common.core.page.PageQueryResult;
import com.ower.dsyz.common.core.page.PageRequestParam;
import com.ower.dsyz.wechat.auto.model.WechatMessage;
import com.ower.dsyz.wechat.enums.EnumOPerate;
import com.ower.dsyz.wechat.manual.dto.WechatMessageDTO;

/**
 * <pre>
 * 微信回调消息管理接口
 * </pre>
 *
 * @author HeShuyuan@163.com
 * @date 2018年1月22日
 * @version 1.00.00
 * 
 *          <pre>
 * 修改记录 
 *    修改后版本:     修改人：  修改日期:     修改内容:
 *          </pre>
 */

public interface IWechatMessageResourceService {

    /**
     * 新增或修改消息
     * @param wechatMessage
     * @return EnumOPerate
     */
    public EnumOPerate addOrUpdateMessage(WechatMessage wechatMessage);
    
    
    /**
     * 分页查询消息
     * @param param
     * @return PageQueryResult<WechatMessageDTO>
     */
    public PageQueryResult<WechatMessageDTO> pageQueryMessage(PageRequestParam param);
    
    
    /**
     * 选择消息列表
     * @param wechatMessageDTO
     * @return List<WechatMessageDTO>
     */
    public List<WechatMessageDTO> selectMessageList(WechatMessageDTO wechatMessageDTO);
}
