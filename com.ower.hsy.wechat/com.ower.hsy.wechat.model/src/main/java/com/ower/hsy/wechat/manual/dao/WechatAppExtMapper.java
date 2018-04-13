package com.ower.hsy.wechat.manual.dao;

import java.util.List;
import com.ower.hsy.common.core.page.PageQueryResult;
import com.ower.hsy.common.core.page.PageRequestParam;
import com.ower.hsy.wechat.auto.model.WechatApp;
import com.ower.hsy.wechat.manual.dto.WechatAppDTO;

public interface WechatAppExtMapper {

    /**
     * 
     * 分页查询app列表
     * @param param
     * @return PageQueryResult<WechatAppDTO>
     */
    PageQueryResult<WechatAppDTO> pageQueryWechatAppList(PageRequestParam param);
    
    
    /**
     * 
     * 条件查询
     * @param wechatAppDTO
     * @return PageQueryResult<WechatAppDTO>
     */
    List<WechatAppDTO> selectWechatAppList(WechatAppDTO wechatAppDTO);
    
    

    /**
     * 或查询
     * @param wechatApp
     * @return List<WechatApp>
     */
    List<WechatApp> selectIsExitWechatAppList(WechatApp wechatApp);
    
}