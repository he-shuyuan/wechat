package com.ower.hsy.wechat.manual.dao;

import java.util.List;
import com.ower.hsy.common.core.page.PageQueryResult;
import com.ower.hsy.common.core.page.PageRequestParam;
import com.ower.hsy.wechat.manual.dto.WechatMessageDTO;

public interface WechatMessageExtMapper {


    /**
     * 
     * 分页查询app列表
     * @param param
     * @return PageQueryResult<WechatMessageDTO>
     */
    PageQueryResult<WechatMessageDTO> pageQueryWechatMessageList(PageRequestParam param);
    
    
    /**
     * 
     * 条件查询
     * @param WechatMessageDTO
     * @return PageQueryResult<WechatMessageDTO>
     */
    List<WechatMessageDTO> selectWechatMessageList(WechatMessageDTO wechatMessageDTO);
    
}