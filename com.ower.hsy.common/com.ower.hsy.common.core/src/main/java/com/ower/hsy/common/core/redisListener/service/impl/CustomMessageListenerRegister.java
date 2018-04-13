/**


 * Copyright(c) Foresee Science & Technology Ltd. 
 */
package com.ower.hsy.common.core.redisListener.service.impl;

import java.util.ArrayList;
import java.util.Collection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.listener.PatternTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.listener.Topic;
import org.springframework.stereotype.Component;
import com.ower.hsy.common.core.redisListener.service.ICustomMessageListener;
import com.ower.hsy.common.core.redisListener.service.ICustomMessageListererRegister;

/**
 * <pre>
 * redisMessage注册器
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
@Component
public class CustomMessageListenerRegister implements ICustomMessageListererRegister{

    private Logger                            logger    = LoggerFactory.getLogger(CustomMessageListenerRegister.class);
    
    @Autowired
    RedisMessageListenerContainer redisMessageListenerContainer;
    
    @Override
    public void register(String channels, ICustomMessageListener listener) {
        String[] channelArray = channels.split(",");
        Collection<Topic> channelList = new ArrayList<>();
        for (String channel : channelArray) {
          channelList.add(new PatternTopic(channel));
        }
        redisMessageListenerContainer.addMessageListener(new CustomRedisMessageListener(listener), channelList);
        logger.info("\n【redisMessage】 注册新的监听器成功，订阅频道--->{}",channelList);
    }

}
