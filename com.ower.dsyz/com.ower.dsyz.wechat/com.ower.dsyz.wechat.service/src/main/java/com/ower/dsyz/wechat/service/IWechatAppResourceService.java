/**
 * Copyright(c) Foresee Science & Technology Ltd. 
 */
package com.ower.dsyz.wechat.service;

import java.util.List;
import com.ower.dsyz.common.core.page.PageQueryResult;
import com.ower.dsyz.common.core.page.PageRequestParam;
import com.ower.dsyz.wechat.auto.model.WechatApp;
import com.ower.dsyz.wechat.enums.EnumOPerate;
import com.ower.dsyz.wechat.manual.dto.WechatAppDTO;

/**
 * <pre>
 * 微信app配置接口
 * </pre>
 *
 * @author HeShuyuan@163.com
 * @date 2018年1月15日
 * @version 1.00.00
 * 
 *          <pre>
 * 修改记录 
 *    修改后版本:     修改人：  修改日期:     修改内容:
 *          </pre>
 */

public interface IWechatAppResourceService {

    /**
     * 更新或新增公众号配置
     * @param wechatApp
     * @return int
     */
    EnumOPerate createOrUpdateWechatApp(WechatApp wechatApp);
    
    
    /**
     * 通过id删除微信公众号配置
     * @param id
     * @return int
     */
    int deleteWechatAppById(String id);
    
    
    /**
     * 
     * 分页查询微信号配置
     * @param param
     * @return PageQueryResult<WechatAppDTO>
     */
    PageQueryResult<WechatAppDTO> pageQueryWechatAppList(PageRequestParam param);
    
    
    /**
     * 查询微信app配置
     * @param wechatAppDTO
     * @return List<WechatAppDTO>
     */
    List<WechatAppDTO> selectWechatAppList(WechatAppDTO wechatAppDTO);
}
