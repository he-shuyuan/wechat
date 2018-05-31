/**
 * Copyright(c) Foresee Science & Technology Ltd. 
 */
package com.ower.dsyz.common.core.redisListener.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;

/**
 * <pre>
 * 注册redis监听器（订阅功能）
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
@Configuration
public class RedisMessageListenerStart {

    @Bean
    RedisMessageListenerContainer container(RedisConnectionFactory connectionFactory)
    {
      RedisMessageListenerContainer container = new RedisMessageListenerContainer();
      container.setConnectionFactory(connectionFactory);
      return container;
    }
}
