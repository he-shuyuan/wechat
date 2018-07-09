package com.ower.dsyz.wechat.service;

import com.ower.dsyz.common.core.model.ResultMsg;
import me.chanjar.weixin.cp.bean.WxCpMessage;
import me.chanjar.weixin.mp.api.impl.WxMpServiceImpl;
import me.chanjar.weixin.mp.bean.kefu.WxMpKefuMessage;
import me.chanjar.weixin.mp.bean.template.WxMpTemplateMessage;

/**
 * <pre>
 * 统一推送消息接口
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

public interface IWechatSendMessageService {

    /**
     * 推送服务号消息
     * @param message
     * @param appId
     * @return ResultMsg
     */
    ResultMsg sendMultiDomainMessage(WxMpKefuMessage message,String appId);
    
    /**
     * 
     * 推送服务号消息
     * @param message
     * @param wxService
     * @return ResultMsg
     */
    ResultMsg sendMultiDomainMessage(WxMpKefuMessage message,WxMpServiceImpl wxService);
    /**
     * 
     * 推送企业号消息
     * @param message
     * @param appId void
     */
    ResultMsg sendMultiDomainMessage(WxCpMessage message,String appId);
    
    
   
    /**
     * 
     * 发送模板消息
     * @param templateMessage
     * @param appId
     * @return ResultMsg
     */
    ResultMsg sendMultiDomainTemplateMessage(WxMpTemplateMessage templateMessage,String appId);
    
   /**
    * 
    * 发送模板消息
    * @param templateMessage
    * @param wxService
    * @return ResultMsg
    */
    ResultMsg sendMultiDomainTemplateMessage(WxMpTemplateMessage templateMessage,WxMpServiceImpl wxService);
}
