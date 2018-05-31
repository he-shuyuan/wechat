/**
 * Copyright(c) Foresee Science & Technology Ltd. 
 */
package com.ower.dsyz.common.core.redisListener.service.impl;

import java.util.Map;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import com.ower.dsyz.common.core.annotation.EableRedisListener;
import com.ower.dsyz.common.core.redisListener.service.ICustomMessageListener;
import com.ower.dsyz.common.core.redisListener.service.ICustomMessageListererRegister;

/**
 * <pre>
 * TODO。
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
//@Component
public abstract class AbstractMessageListener implements ICustomMessageListener , ApplicationContextAware , InitializingBean{

    private Logger    logger    = LoggerFactory.getLogger(AbstractMessageListener.class);
    
    @Autowired
    private ICustomMessageListererRegister register;
    
    private final static String APP_SUFFIX = "Application";
    
    private static ApplicationContext applicationContext;
    
    @Value("${ower.application.channel}")
    public String channel;
    
    @Value("${spring.application.name}")
    private String appName;
    
    @Override
    public void onMessage(String channel, String message) {
        logger.info("\n【redisMessage】 接收消息---》{},{}",channel,message);
        this.onMessageHandler(channel,message);
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        Map<String, Object> beans = applicationContext.getBeansWithAnnotation(EableRedisListener.class);
        if(beans == null || !beans.keySet().contains(this.appName + APP_SUFFIX)){
            logger.info("\n【redisMessage】 应用[{}]未开启redisMessage监听",this.appName); 
            return;
        }
        if (this.register != null && StringUtils.isNotBlank(this.channel)) {
            this.register.register(this.channel, this);
        }else{
            logger.warn("\n【redisMessage】 channel为空，不存在频道订阅---》{}",this.appName); 
        }
    }
    @Override
    public void setApplicationContext(ApplicationContext context) throws BeansException {
        applicationContext = context;
    }

    
    public abstract void onMessageHandler(String channel, String message);
}
