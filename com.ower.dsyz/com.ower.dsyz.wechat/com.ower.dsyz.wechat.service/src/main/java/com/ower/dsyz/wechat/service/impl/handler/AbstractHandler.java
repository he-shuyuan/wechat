package com.ower.dsyz.wechat.service.impl.handler;

import java.util.Map;
import me.chanjar.weixin.common.exception.WxErrorException;
import me.chanjar.weixin.common.session.WxSessionManager;
import me.chanjar.weixin.mp.api.WxMpMessageHandler;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.api.impl.WxMpServiceImpl;
import me.chanjar.weixin.mp.bean.message.WxMpXmlMessage;
import me.chanjar.weixin.mp.bean.message.WxMpXmlOutMessage;

/**
 * 
 * <pre>
 * 抽象处理微信事件类
 * </pre>
 *
 * @author HeShuyuan@163.com
 * @date 2018年1月11日
 * @version 1.00.00
 * 
 *          <pre>
 * 修改记录 
 *    修改后版本:     修改人：  修改日期:     修改内容:
 *          </pre>
 */
public abstract class AbstractHandler implements WxMpMessageHandler {

    /**
     * @param wxMessage
     * @param context 上下文，如果handler或interceptor之间有信息要传递，可以用这个
     * @param wxMpService
     * @param sessionManager
     * @return xml格式的消息，如果在异步规则里处理的话，可以返回null
     */
    @Override
    public WxMpXmlOutMessage handle(WxMpXmlMessage wxMessage, Map<String, Object> context, WxMpService weixinService,
            WxSessionManager sessionManager) throws WxErrorException {
        try {
            return this.handle(wxMessage, context, (WxMpServiceImpl) weixinService);
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    /**
     * @param wxMessage
     * @param context 上下文，如果handler或interceptor之间有信息要传递，可以用这个
     * @param wxMpService
     * @return xml格式的消息，如果在异步规则里处理的话，可以返回null 如果想要获取微信会话，使用this.getSession();
     */
    public abstract WxMpXmlOutMessage handle(WxMpXmlMessage wxMessage, Map<String, Object> context, WxMpServiceImpl weixinService);
}
