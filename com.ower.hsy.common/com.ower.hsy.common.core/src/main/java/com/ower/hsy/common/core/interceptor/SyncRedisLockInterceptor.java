/**
 * Copyright(c) Foresee Science & Technology Ltd.
 */
package com.ower.hsy.common.core.interceptor;

/*import java.lang.reflect.Method;*/
import javax.annotation.Resource;
import org.apache.commons.lang.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import com.ower.hsy.common.core.annotation.SyncRedisLock;
import com.ower.hsy.common.core.redis.impl.SyncLockUtil;

/**
 * <pre>
 * 分布式锁切面
 * </pre>
 *
 * @author HeShuyuan@foresee.com.cn
 * @date 2018年1月15日
 * @version 1.00.00
 * 
 *          <pre>
 * 修改记录 
 *    修改后版本:     修改人：  修改日期:     修改内容:
 *          </pre>
 */
@Aspect
@Component
public class SyncRedisLockInterceptor {

    private static final Logger logger = LoggerFactory.getLogger(SyncRedisLockInterceptor.class);

    @Resource
    private SyncLockUtil        syncLockUtil;


    @Pointcut("@annotation(com.ower.hsy.common.core.annotation.SyncRedisLock)")
    public void distributedLockAspect() {}

    @Around("distributedLockAspect()")
    public Object interceptor(ProceedingJoinPoint pjp) throws Throwable {
        MethodSignature signature = (MethodSignature) pjp.getSignature();
        String methodName = signature.getDeclaringTypeName() + "." + signature.getMethod().getName();
        Object[] args = pjp.getArgs();
        SyncRedisLock syncRedisLock = null;
       /* Class<?>[] classList = signature.getMethod().getDeclaringClass().getInterfaces();
        Method realMethod = isHasInterfacesOfAnnotation(SyncRedisLock.class,classList,signature);
        if(realMethod!=null){
            syncRedisLock = realMethod.getAnnotation(SyncRedisLock.class);
        }else{*/
            syncRedisLock = (SyncRedisLock) signature.getMethod().getAnnotation(SyncRedisLock.class);
       // }
        logger.info("分布式同步锁初始化开始==>key={},value={},timeOut={}", syncRedisLock.id(), syncRedisLock.value(), syncRedisLock.timeOut());
        String id = syncRedisLock.id();
        if (StringUtils.isBlank(id)) {
            id = methodName;
        }
        String value = syncRedisLock.value();
        if (StringUtils.isBlank(value)) {
            value = "interAspect";
        }
        Long timeOut = syncRedisLock.timeOut();
        if (timeOut == null || timeOut == 0) {
            timeOut = (long) 2;
        }
        logger.info("分布式同步锁初始化完成==>key={},value={},timeOut={}", id, value, timeOut);
        timeOut = syncLockUtil.requireLock(id, value, timeOut);
        Object result = null;
        try {
            result = pjp.proceed(args);
        } finally {
            syncLockUtil.releaseLock(id, value, timeOut);
        }
        return result;
    }

    
 /*   private Method isHasInterfacesOfAnnotation(Class<SyncRedisLock> SyncRedisLockAnnotation,Class<?>[] classList,MethodSignature signature){
        if(classList.length<1){
            return null;
        }
        for(Class<?> Interfaces:classList){
            try {
                if(Interfaces.getDeclaredMethod(signature.getMethod().getName(), signature.getMethod().getParameterTypes()).isAnnotationPresent(SyncRedisLockAnnotation)){
                    return Interfaces.getDeclaredMethod(signature.getMethod().getName(), signature.getMethod().getParameterTypes());
                }
            } catch (Exception e) {
                 logger.debug("在接口里找不到该方法");
            }
        }
        return null;
    }*/
}
