/**
 * Copyright(c) Foresee Science & Technology Ltd. 
 */
package com.ower.hsy.wechat.service.impl.handler;

import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import me.chanjar.weixin.mp.api.impl.WxMpServiceImpl;
import me.chanjar.weixin.mp.bean.message.WxMpXmlMessage;
import me.chanjar.weixin.mp.bean.message.WxMpXmlOutMessage;

/**
 * <pre>
 * 日志记录
 * </pre>
 *
 * @author HeShuyuan@foresee.com.cn
 * @date 2018年1月11日
 * @version 1.00.00
 * 
 *          <pre>
 * 修改记录 
 *    修改后版本:     修改人：  修改日期:     修改内容:
 *          </pre>
 */
@Component
public class LogHandler extends AbstractHandler{

    private final static Logger log = LoggerFactory.getLogger(LogHandler.class);
    
    @Override
    public WxMpXmlOutMessage handle(WxMpXmlMessage wxMessage, Map<String, Object> context, WxMpServiceImpl weixinService) {
        
        log.info("\n接收微信消息:\n{}",wxMessage);
        return null;
    }

}
