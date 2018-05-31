/**
 * Copyright(c) Foresee Science & Technology Ltd. 
 */
package com.ower.dsyz.wechat.service.impl.handler;

import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.ower.dsyz.wechat.service.IWechatSendMessageService;
import me.chanjar.weixin.mp.api.impl.WxMpServiceImpl;
import me.chanjar.weixin.mp.bean.kefu.WxMpKefuMessage;
import me.chanjar.weixin.mp.bean.message.WxMpXmlMessage;
import me.chanjar.weixin.mp.bean.message.WxMpXmlOutMessage;
/**
 * <pre>
 *  默认消息处理
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
@Component
public class CustomMsgHandler extends AbstractHandler {

    @Autowired
    private IWechatSendMessageService wechatSendMessageService;
    
    @Override
    public WxMpXmlOutMessage handle(WxMpXmlMessage wxMessage, Map<String, Object> context, WxMpServiceImpl weixinService) {
        wechatSendMessageService.sendMultiDomainMessage(
                WxMpKefuMessage.TEXT().toUser(wxMessage.getFromUser()).content(wxMessage.getContent()).build(), weixinService);
        return null;
    }

}
