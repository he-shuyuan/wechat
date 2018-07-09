/**
 * Copyright(c) Foresee Science & Technology Ltd. 
 */
package com.ower.dsyz.wechat.service.impl.handler;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.ower.dsyz.wechat.constant.WechatConstant;
import com.ower.dsyz.wechat.manual.dto.WechatMessageDTO;
import com.ower.dsyz.wechat.service.IWechatSendMessageService;
import com.ower.dsyz.wechat.service.dataBase.IRedisJdbcStorageService;
import com.ower.dsyz.wechat.util.ThreadLocalUtil;
import me.chanjar.weixin.mp.api.impl.WxMpServiceImpl;
import me.chanjar.weixin.mp.bean.kefu.WxMpKefuMessage;
import me.chanjar.weixin.mp.bean.kefu.WxMpKefuMessage.WxArticle;
import me.chanjar.weixin.mp.bean.message.WxMpXmlMessage;
import me.chanjar.weixin.mp.bean.message.WxMpXmlOutMessage;

/**
 * <pre>
 * 订阅关注事件处理
 * </pre>
 *
 * @author HeShuyuan@163.com
 * @date 2018年1月19日
 * @version 1.00.00
 * 
 *          <pre>
 * 修改记录 
 *    修改后版本:     修改人：  修改日期:     修改内容:
 *          </pre>
 */
@Component
public class SubscribeHandler extends AbstractHandler{

    @Autowired
    private IRedisJdbcStorageService redisJdbcStorageService;
    
    @Autowired
    private IWechatSendMessageService wechatSendMessageService;
    
    private final static Logger log = LoggerFactory.getLogger(SubscribeHandler.class);
    
    @Override
    public WxMpXmlOutMessage handle(WxMpXmlMessage wxMessage, Map<String, Object> context, WxMpServiceImpl weixinService) {
        log.info("公众号[{}]新关注用户 OPENID:{}",wxMessage.getToUser(),wxMessage.getFromUser());
        String appId = ThreadLocalUtil.wxParams.get().get("appId");
        List<WechatMessageDTO> list = redisJdbcStorageService.getWechatMessageDTOByAppIdAndEventType(appId, "subscribe");
        for(WechatMessageDTO wechatMessageDTO:list){
           if(WechatConstant.Message.TEXT.equals(wechatMessageDTO.getMessType())) {
               wechatSendMessageService.sendMultiDomainMessage(
                        WxMpKefuMessage.TEXT()
                       .toUser(wxMessage.getFromUser())
                       .content(wechatMessageDTO.getDescription())
                       .build(), weixinService); 
           }else if(WechatConstant.Message.NEWS.equals(wechatMessageDTO.getMessType())){
               List<WxArticle> articleList = new ArrayList<>();
               this.buildNewsMessage(articleList,wechatMessageDTO);
               wechatSendMessageService.sendMultiDomainMessage(
                       WxMpKefuMessage.NEWS()
                      .toUser(wxMessage.getFromUser())
                      .articles(articleList)
                      .build(), weixinService); 
           }else{
               log.warn("关注事件站不处理该消息类型=》{}",wechatMessageDTO.getMessType()); 
           }
        }
        return null;
    }

    /**
     * 组装报文
     * @param list
     * @param wechatMessageDTO void
     */
    private void buildNewsMessage(List<WxArticle> list,WechatMessageDTO wechatMessageDTO){
        WxArticle art = new WxArticle();
        art.setTitle(wechatMessageDTO.getTitle());
        art.setPicUrl(wechatMessageDTO.getImgUrl());
        art.setDescription(wechatMessageDTO.getDescription());
        art.setUrl(wechatMessageDTO.getUrl());
        list.add(art);
        if(wechatMessageDTO.getHasChild()>0 && wechatMessageDTO.getChildrenList()!=null){
            for(WechatMessageDTO dto:wechatMessageDTO.getChildrenList()){
                art = new WxArticle();
                art.setTitle(dto.getTitle());
                art.setPicUrl(dto.getImgUrl());
                art.setDescription(dto.getDescription());
                art.setUrl(dto.getUrl());
                list.add(art);
            }
        }
    }
}
