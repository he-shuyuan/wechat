/**
 * Copyright(c) Foresee Science & Technology Ltd.
 */
package com.ower.dsyz.common.core.interceptor;

import java.io.Serializable;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.ower.dsyz.common.core.annotation.RedisStorage;
import com.ower.dsyz.common.core.constant.BeanNameConstant;
import com.ower.dsyz.common.core.redis.IRedisService;
import com.ower.dsyz.common.core.util.Jackson;
/**
 * <pre>
 * 二级缓存切面
 * </pre>
 *
 * @author HeShuyuan@163.com
 * @date 2018年1月29日
 * @version 1.00.00
 * 
 *          <pre>
 * 修改记录 
 *    修改后版本:     修改人：  修改日期:     修改内容:
 *          </pre>
 */
@Aspect
@Component(BeanNameConstant.RedisStorageInterceptor)
public class RedisStorageInterceptor {
  
    private String              CACHE_PREFIX = "cache:";

    @Autowired
    private IRedisService       redisService;

    private int cleanNum = 0;
    
    private static final Logger logger       = LoggerFactory.getLogger(RedisStorageInterceptor.class);


    @Pointcut("@annotation(com.ower.dsyz.common.core.annotation.RedisStorage)")
    protected void startCacheAspect() {}

    @Around("startCacheAspect()")
    protected Object interceptor(ProceedingJoinPoint pjp) throws Throwable {
        MethodSignature signature = (MethodSignature) pjp.getSignature();
        String methodName = signature.getDeclaringTypeName() + "." + signature.getMethod().getName();
        methodName = CACHE_PREFIX + methodName.replace(".", ":") + ":";
        RedisStorage redisStorage = (RedisStorage) signature.getMethod().getAnnotation(RedisStorage.class);
        Object[] args = pjp.getArgs();
        String key = this.buildRedisKey(methodName, args);
        Object jsonOb = redisService.getObject(key);
        if (jsonOb!=null) {
            logger.info("key=[{}],存在redis缓存=[{}]", key, Jackson.toJson(jsonOb));
           return jsonOb;
        }
        Object result = null;
        try {
            result = pjp.proceed(args);
        } catch (Exception e) {
            logger.error("业务代码出错，{}", e);
            return null;
        }
        if(result instanceof Serializable){
        redisService.setObject(key, (Serializable) result, redisStorage.cacheTime() * 60L);
        logger.info("key=[{}],添加redis缓存=[{}]", key, Jackson.toJson(result));
        }
        return result;
    }

    /**
     * 获取缓存key值
     * @param methodName
     * @param args
     * @return String
     */
    private String buildRedisKey(String methodName, Object[] args) {
        StringBuilder sb = new StringBuilder();
        sb.append(methodName);
        StringBuilder tempSb = new StringBuilder();
        for (Object ob : args) {
            tempSb.append(Jackson.toJson(ob));
        }
        sb.append(tempSb.toString().hashCode());

        return sb.toString();
    }
    
    /**
     * 清缓存
     * TODO。 void
     */
    public void cleanCache(){
        redisService.cleanBatchStorage(CACHE_PREFIX+"*");
        logger.info("第{}清除本缓存", ++this.cleanNum);
    }
}
