/**
 * Copyright(c) Foresee Science & Technology Ltd. 
 */
package com.ower.dsyz.wechat.rest.ui.back;

import javax.annotation.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import com.ower.dsyz.common.core.page.PageRequestParam;
import com.ower.dsyz.common.core.response.CustomResponse;
import com.ower.dsyz.wechat.auto.model.WechatMessage;
import com.ower.dsyz.wechat.enums.EnumOPerate;
import com.ower.dsyz.wechat.manual.dto.WechatMessageDTO;
import com.ower.dsyz.wechat.service.IWechatMessageResourceService;

/**
 * <pre>
 * 微信回调消息处理
 * </pre>
 *
 * @author HeShuyuan@foresee.com.cn
 * @date 2018年1月22日
 * @version 1.00.00
 * 
 *          <pre>
 * 修改记录 
 *    修改后版本:     修改人：  修改日期:     修改内容:
 *          </pre>
 */
@RestController
@RequestMapping("/back/wechatMessageService")
public class WechatMessageResource {

    private Logger               logger = LoggerFactory.getLogger(WechatMessageResource.class);

    @Resource
    private IWechatMessageResourceService wechatMessageResourceService;
    
    @ResponseBody
    @RequestMapping("addOrUpdateMessage")
    public Object addOrUpdateMessage(@RequestBody WechatMessage wechatMessage){
       logger.debug("新增或修改微信回调消息=》{}",wechatMessage);
       EnumOPerate op = wechatMessageResourceService.addOrUpdateMessage(wechatMessage);
       if(op == EnumOPerate.INSERT){
           return CustomResponse.success("新增成功");
       }else{
           return CustomResponse.success("修改成功");
       }
    }
    
    @ResponseBody
    @RequestMapping("pageQueryMessage")
    public Object pageQueryMessage(@RequestBody PageRequestParam param){
        logger.debug("分页查询微信回调消息=》{}",param);
        return wechatMessageResourceService.pageQueryMessage(param);
    }
    
    @ResponseBody
    @RequestMapping("queryMessageList")
    public Object queryMessageList(@RequestBody WechatMessageDTO wechatMessageDTO){
        logger.debug("查询消息列表=》{}",wechatMessageDTO);
         return wechatMessageResourceService.selectMessageList(wechatMessageDTO);
    }
}
