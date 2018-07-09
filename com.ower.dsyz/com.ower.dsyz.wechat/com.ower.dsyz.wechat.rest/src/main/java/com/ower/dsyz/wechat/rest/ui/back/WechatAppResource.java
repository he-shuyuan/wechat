/**
 * Copyright(c) Foresee Science & Technology Ltd. 
 */
package com.ower.dsyz.wechat.rest.ui.back;

import javax.annotation.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ower.dsyz.common.core.page.PageRequestParam;
import com.ower.dsyz.common.core.response.CustomResponse;
import com.ower.dsyz.wechat.auto.model.WechatApp;
import com.ower.dsyz.wechat.enums.EnumOPerate;
import com.ower.dsyz.wechat.manual.dto.WechatAppDTO;
import com.ower.dsyz.wechat.service.IWechatAppResourceService;
/**
 * <pre>
 * 微信app后台配置
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
@RestController
@RequestMapping("/back/wechatAppService")
public class WechatAppResource {

    private Logger               logger = LoggerFactory.getLogger(WechatAppResource.class);
    @Resource
    private IWechatAppResourceService wechatAppResourceServiceImpl;
    
    /**
     * 
     * 新增或修改微信配置信息
     * @param wechatApp
     * @return Object
     */
    @RequestMapping("addOrUpdateWechatApp")
    public Object addOrUpdateWechatApp(@RequestBody WechatApp wechatApp){
        logger.debug("新增或修改微信配置=》{}",wechatApp);
       EnumOPerate op = wechatAppResourceServiceImpl.createOrUpdateWechatApp(wechatApp);
        if(op == EnumOPerate.INSERT){
            return CustomResponse.success("新增成功");
        }else{
            return CustomResponse.success("修改成功");
        }

    }
    
    /**
     * 
     * 分页查询微信app配置
     * @param param
     * @return Object
     */
    @RequestMapping("pageQueryWechatAppList")
    public Object pageQueryWechatAppList(@RequestBody PageRequestParam param){
        logger.debug("分页查询微信公众号信息=》{}",param);
        return wechatAppResourceServiceImpl.pageQueryWechatAppList(param);
    }
    
    @RequestMapping("selectWechatAppList")
    public Object selectWechatAppList(@RequestBody WechatAppDTO wechatAppDTO){
        logger.debug("查询微信公众号列表=》{}",wechatAppDTO);
        return wechatAppResourceServiceImpl.selectWechatAppList(wechatAppDTO);
    }
}
