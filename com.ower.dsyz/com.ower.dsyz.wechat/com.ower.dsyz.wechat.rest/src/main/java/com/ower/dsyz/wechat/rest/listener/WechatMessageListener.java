/**
 * Copyright(c) Foresee Science & Technology Ltd. 
 */
package com.ower.dsyz.wechat.rest.listener;

import org.springframework.stereotype.Component;
import com.ower.dsyz.common.core.redisListener.service.impl.AbstractMessageListener;

/**
 * <pre>
 * TODO。
 * </pre>
 *
 * @author HeShuyuan@163.com
 * @date 2018年1月31日
 * @version 1.00.00
 * 
 *          <pre>
 * 修改记录 
 *    修改后版本:     修改人：  修改日期:     修改内容:
 *          </pre>
 */
@Component
public class WechatMessageListener extends AbstractMessageListener{

    @Override
    public void onMessageHandler(String channel, String message) {
        System.out.println("lalalalalaallll");
    }

}
