/**
 * Copyright(c) Foresee Science & Technology Ltd.
 */
package com.ower.dsyz.wechat.rest.outter;

import java.util.Enumeration;
import java.util.HashMap;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import com.ower.dsyz.wechat.service.impl.factory.WxMpMsgRouterFactory;
import com.ower.dsyz.wechat.service.impl.factory.WxServiceFactory;
import com.ower.dsyz.wechat.util.ThreadLocalUtil;
import me.chanjar.weixin.mp.bean.message.WxMpXmlMessage;
import me.chanjar.weixin.mp.bean.message.WxMpXmlOutMessage;
import me.chanjar.weixin.mp.api.impl.WxMpServiceImpl;

/**
 * 
 * <pre>
 * 微信企业号回调类
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
@RestController
@RequestMapping("/wechatCallBackService/api")
public class WechatCallBackController {

    private Logger               logger = LoggerFactory.getLogger(WechatCallBackController.class);

    @Resource
    private WxServiceFactory     wxServiceFactory;

    @Autowired
    private WxMpMsgRouterFactory wxMpMsgRouterFactory;


    @ResponseBody
    @GetMapping
    public Object authGet(@RequestParam(name = "signature", required = false) String signature,
            @RequestParam(name = "timestamp", required = false) String timestamp,
            @RequestParam(name = "nonce", required = false) String nonce,
            @RequestParam(name = "echostr", required = false) String echostr, @RequestParam(name = "appid") String appId) {

        logger.info("\n接收到来自微信服务器的认证消息：[{}, {}, {}, {}]", signature, timestamp, nonce, echostr);

        if (StringUtils.isAnyBlank(signature, timestamp, nonce, echostr)) {
            throw new IllegalArgumentException("请求参数非法，请核实!");
        }
        WxMpServiceImpl wxMpServiceImpl = (WxMpServiceImpl) (wxServiceFactory.getWxService(appId));
        if (wxMpServiceImpl.checkSignature(timestamp, nonce, signature)) {
            return echostr;
        }

        return "非法请求";
    }

    @ResponseBody
    @PostMapping
    public Object post(@RequestBody String requestBody, @RequestParam("signature") String signature,
            @RequestParam("timestamp") String timestamp, @RequestParam("nonce") String nonce,
            @RequestParam(name = "encrypt_type", required = false) String encType,
            @RequestParam(name = "msg_signature", required = false) String msgSignature, @RequestParam(name = "appid") String appId,
            HttpServletRequest request) {
        WxMpServiceImpl wxMpServiceImpl = (WxMpServiceImpl) (wxServiceFactory.getWxService(appId));
        if (!wxMpServiceImpl.checkSignature(timestamp, nonce, signature)) {
            throw new IllegalArgumentException("非法请求，可能属于伪造的请求！");
        }

        ThreadLocalUtil.wxParams.set(new HashMap<String, String>());
        Enumeration<String> paramrEnum = request.getParameterNames();
        while (paramrEnum.hasMoreElements()) {
            String name = paramrEnum.nextElement();
            ThreadLocalUtil.wxParams.get().put(name, request.getParameter(name));
        }
        ThreadLocalUtil.wxParams.get().put("requestBody", requestBody);
        ThreadLocalUtil.wxParams.get().put("appId", appId);
        try {
            String out = null;
            if (encType == null) {
                // 明文传输的消息
                WxMpXmlMessage inMessage = WxMpXmlMessage.fromXml(requestBody);
               // logger.debug("\n接收微信请求：\n{} ", inMessage.toString());
                WxMpXmlOutMessage outMessage = this.wxMpMsgRouterFactory.getWxMpMessageRouter(appId).route(inMessage);
                if (outMessage == null) {
                    return "";
                }
                out = outMessage.toXml();
            } else if ("aes".equals(encType)) {

            }
            logger.debug("\n组装回复信息：{}", out);
            return out;
        } catch (Throwable e) {
            logger.error(e.getMessage(), e);
            return "";
        } finally {
            ThreadLocalUtil.clean();
        }
    }

}
