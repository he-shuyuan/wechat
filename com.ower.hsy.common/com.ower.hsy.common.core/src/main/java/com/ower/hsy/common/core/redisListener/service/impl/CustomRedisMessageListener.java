/**
 * Copyright(c) Foresee Science & Technology Ltd. 
 */
package com.ower.hsy.common.core.redisListener.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import com.ower.hsy.common.core.redisListener.service.ICustomMessageListener;

/**
 * <pre>
 * 实现redisMessage接口
 * </pre>
 *
 * @author HeShuyuan@foresee.com.cn
 * @date 2018年1月31日
 * @version 1.00.00
 * 
 *          <pre>
 * 修改记录 
 *    修改后版本:     修改人：  修改日期:     修改内容:
 *          </pre>
 */

public class CustomRedisMessageListener implements MessageListener{

    private Logger                            logger    = LoggerFactory.getLogger(CustomRedisMessageListener.class);
    
    private ICustomMessageListener listener;
    
    public CustomRedisMessageListener(ICustomMessageListener listener)
    {
      this.listener = listener;
    }
    
    @Override
    public void onMessage(Message message, byte[] arg1) {
        
        logger.info("\n【redisMessage】 接收消息 --- [{}]:[{}]", new String(arg1), message.toString());
        
        this.listener.onMessage(new String(message.getChannel()), new String(message.getBody()));
    }

}
