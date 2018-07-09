package com.ower.dsyz.wechat.service.impl.factory;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.Map;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConstructorArgumentValues;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ower.dsyz.wechat.service.impl.handler.CustomMsgHandler;
import com.ower.dsyz.wechat.service.impl.handler.LogHandler;
import com.ower.dsyz.wechat.service.impl.handler.SubscribeHandler;
import com.ower.dsyz.wechat.util.ContextUtil;
import me.chanjar.weixin.common.api.WxConsts;
import me.chanjar.weixin.mp.api.WxMpMessageHandler;
import me.chanjar.weixin.mp.api.WxMpMessageRouter;
import me.chanjar.weixin.mp.api.WxMpService;

/**
 * 
 * <pre>
 * 订阅号、服务号微信消息路由工厂
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
@Component
public class WxMpMsgRouterFactory {

    private final static Logger log = LoggerFactory.getLogger(WxMpMsgRouterFactory.class);

    @Autowired
    private Environment         env;

    @Autowired
    private WxServiceFactory    wxServiceFactory;

    @Autowired
    private LogHandler logHandler;
    
    @Autowired
    private CustomMsgHandler customMsgHandler;
    
    @Autowired
    private SubscribeHandler subscribeHandler;
    
    /**
     * 
     * 根据应用id获取该应用对应微信的消息路由器实例
     * @param appId
     * @return
     */
    public synchronized WxMpMessageRouter getWxMpMessageRouter(String appId) {
        String beanId = "WxMpMessageRouter-" + appId;
        WxMpMessageRouter wxMpMessageRouter = (WxMpMessageRouter) ContextUtil.getBean(beanId);
        if (wxMpMessageRouter == null) {
            log.debug("正在创建应用" + appId + "的微信消息路由器");
            WxMpService wxMpService = (WxMpService) this.wxServiceFactory.getWxService(appId);
            ConstructorArgumentValues constructorArgumentValues = new ConstructorArgumentValues();
            constructorArgumentValues.addIndexedArgumentValue(0, wxMpService);
            wxMpMessageRouter = (WxMpMessageRouter) ContextUtil.registerBean(beanId, WxMpMessageRouter.class, null,
                    constructorArgumentValues);
            this.individHandler(appId);
            this.defineRules(wxMpMessageRouter);
            log.debug("创建成功！并且已注入spring容器中");
        }
        return wxMpMessageRouter;
    }

    /**
     * 
     * 根据各个应用的handler个性化配置重新设置各个handler
     * @param appId void
     */
    private void individHandler(String appId) {
        String wxGxhHandler = env.getProperty("wx.handler." + appId);
        if (StringUtils.isBlank(wxGxhHandler)) {
            return;
        }
        try {
            @SuppressWarnings("unchecked") 
            Map<String, String> handlerConf = new ObjectMapper().readValue(wxGxhHandler, Map.class);
            for (String handlerName : handlerConf.keySet()) {
                for (Field field : this.getClass().getDeclaredFields()) {
                    if (WxMpMessageHandler.class.isAssignableFrom(field.getType())
                            && field.getType().getSimpleName().equals(handlerName)) {
                        log.debug("匹配到个性化handler配置：" + handlerName);
                        Object handler = ContextUtil.getBean(handlerConf.get(handlerName));
                        if (handler != null) {
                            try {
                                field.set(this, handler);
                                log.debug(handlerName + "实例替换成功！新实例为：" + handler.getClass().getName());
                            } catch (IllegalArgumentException | IllegalAccessException e) {
                                log.debug(handlerName + "实例替换失败！" + e.getMessage());
                                e.printStackTrace();
                            }
                        } else {
                            log.debug(handlerName + "实例替换失败！找不到bean：" + handlerConf.get(handlerName));
                        }
                        break;
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 
     * 定义规则
     * @param wxMpMessageRouter void
     */
    private void defineRules(WxMpMessageRouter wxMpMessageRouter) {
        // 记录所有事件的日志 （异步执行）
        wxMpMessageRouter.rule().async(false).handler(this.logHandler).next();
        //关注消息
        wxMpMessageRouter.rule().async(false).msgType(WxConsts.XML_MSG_EVENT)
          .event(WxConsts.EVT_SUBSCRIBE).handler(this.subscribeHandler).end();
        //文本消息操作
        wxMpMessageRouter.rule().async(false).msgType(WxConsts.XML_MSG_TEXT).handler(this.customMsgHandler).end();
    }
}
