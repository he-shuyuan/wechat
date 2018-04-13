/**
 * Copyright(c) Foresee Science & Technology Ltd.
 */
package com.ower.hsy.common.core.interceptor;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import org.apache.commons.lang.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.ower.hsy.common.core.annotation.RedisStorage;
import com.ower.hsy.common.core.redis.IRedisService;
import com.ower.hsy.common.core.util.Jackson;
import com.ower.hsy.common.core.constant.BeanNameConstant;
/**
 * <pre>
 * 二级缓存切面
 * </pre>
 *
 * @author HeShuyuan@foresee.com.cn
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

    private  ConcurrentMap<String, Object> cacheMap = new ConcurrentHashMap<>();
    
    private int cleanNum = 0;
    
    private static final Logger logger       = LoggerFactory.getLogger(RedisStorageInterceptor.class);


    @Pointcut("@annotation(com.ower.hsy.common.core.annotation.RedisStorage)")
    protected void startCacheAspect() {}

    @Around("startCacheAspect()")
    protected Object interceptor(ProceedingJoinPoint pjp) throws Throwable {
        MethodSignature signature = (MethodSignature) pjp.getSignature();
        String methodName = signature.getDeclaringTypeName() + "." + signature.getMethod().getName();
        methodName = CACHE_PREFIX + methodName.replace(".", ":") + ":";
        RedisStorage redisStorage = (RedisStorage) signature.getMethod().getAnnotation(RedisStorage.class);
        Object[] args = pjp.getArgs();
        String key = this.buildRedisKey(methodName, args);
        Object cacheOb = cacheMap.get(key);
        if(cacheOb!=null){
            logger.info("【1】key=[{}],存在本地缓存=[{}]", key, cacheOb);
            return cacheOb;
        }
        Class<?> returnType = signature.getReturnType();
        String json = redisService.get(key);
        if (StringUtils.isNotBlank(json)) {
            logger.info("【1】key=[{}],存在redis缓存=[{}]", key, json);
            if(returnType.equals(List.class) || returnType.equals(ArrayList.class)){
                return Jackson.fromJsonArray(json, redisStorage.arrayType());
            }
           Object redisOb = Jackson.fromJson(json, returnType);
           logger.info("【1】key=[{}],刷新本地缓存=[{}]", key, redisOb);
           return redisOb;
        }
        Object result = null;
        try {
            result = pjp.proceed(args);
        } catch (Exception e) {
            logger.error("业务代码出错，{}", e);
            return null;
        }
        json = Jackson.toJson(result);
        redisService.set(key, json, redisStorage.cacheTime() * 60);
        logger.info("【2】key=[{}],刷新redis缓存=[{}]", key, json);
        cacheMap.putIfAbsent(key, result);
        logger.info("【2】key=[{}],刷新本地缓存=[{}]", key, result);
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
        cacheMap.clear();
        redisService.deleteBatch(CACHE_PREFIX+"*");
        logger.info("第{}清除本缓存", ++this.cleanNum);
    }
}
