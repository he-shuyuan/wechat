/**
 * Copyright(c) Foresee Science & Technology Ltd. 
 */
package com.ower.dsyz.wechat.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ower.dsyz.common.core.model.ResultMsg;
import com.ower.dsyz.wechat.service.IWechatSendMessageService;
import com.ower.dsyz.wechat.service.impl.factory.WxServiceFactory;
import com.ower.dsyz.wechat.util.WechatApiLogUtil;
import com.ower.dsyz.wechat.util.WechatApiLogUtil.IWxApiInvoke;
import me.chanjar.weixin.common.exception.WxErrorException;
import me.chanjar.weixin.cp.bean.WxCpMessage;
import me.chanjar.weixin.mp.api.impl.WxMpServiceImpl;
import me.chanjar.weixin.mp.bean.kefu.WxMpKefuMessage;
import me.chanjar.weixin.mp.bean.template.WxMpTemplateMessage;

/**
 * <pre>
 * 消息实现
 * </pre>
 *
 * @author HeShuyuan@foresee.com.cn
 * @date 2018年1月12日
 * @version 1.00.00
 * 
 *          <pre>
 * 修改记录 
 *    修改后版本:     修改人：  修改日期:     修改内容:
 *          </pre>
 */
@Service
public class WechatSendMessageServiceImpl implements IWechatSendMessageService {
    
    @Autowired
    WxServiceFactory wxServiceFactory;
    
    @Override
    public ResultMsg sendMultiDomainMessage(WxMpKefuMessage message, String appId) {
       WxMpServiceImpl wxService = (WxMpServiceImpl) wxServiceFactory.getWxService(appId);
       return this.sendMultiDomainMessage(message, wxService);
    }

    @Override
    public ResultMsg sendMultiDomainMessage(WxMpKefuMessage message, WxMpServiceImpl wxService) {
       return WechatApiLogUtil.execute("发送客服消息", new IWxApiInvoke<Boolean>(){
        @Override
        public Boolean invoke() throws WxErrorException {
            return wxService.getKefuService().sendKefuMessage(message);
        }  
       });
    }
    
    
    @Override
    public ResultMsg sendMultiDomainMessage(WxCpMessage message, String appId) {

        return null;
    }

    @Override
    public ResultMsg sendMultiDomainTemplateMessage(WxMpTemplateMessage templateMessage, String appId) {
        WxMpServiceImpl wxService = (WxMpServiceImpl) wxServiceFactory.getWxService(appId);
        return this.sendMultiDomainTemplateMessage(templateMessage, wxService);
    }
    
    @Override
    public ResultMsg sendMultiDomainTemplateMessage(WxMpTemplateMessage templateMessage, WxMpServiceImpl wxService) {
        return WechatApiLogUtil.execute("发送模板消息", new IWxApiInvoke<String>(){
         @Override
         public String invoke() throws WxErrorException {
             return wxService.getTemplateMsgService().sendTemplateMsg(templateMessage);
         }  
        });
    }
}
