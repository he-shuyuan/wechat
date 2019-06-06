package com.ower.dsyz.common.core.interceptor;

import java.lang.reflect.Method;
import java.util.Date;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.ower.dsyz.common.core.holder.CurrentThreadHolder;

@Aspect
@Component
@SuppressWarnings("rawtypes")
public class DaoInterceptor {

    private static final Logger logger = LoggerFactory.getLogger(DaoInterceptor.class);


    @Before("execution(* com.ower.dsyz..dao..*.insert*(..))")
    public void beforeInsert(JoinPoint jp) {
        for (Object obj : jp.getArgs()) {
            if (obj.getClass().isAssignableFrom(Map.class)) {
                Map map = (Map) obj;
                trySetMap(map, "createDate", new Date());
                trySetMap(map,"createUser",this.getUserId());
                trySetMap(map, "isValid", "Y");
            } else {
                trySetProperty(obj, "IsValid","Y");
                trySetProperty(obj, "CreateUser",this.getUserId());
                trySetProperty(obj, "CreateDate", new Date());
            }
        }

    }

    @SuppressWarnings("unchecked")
    private void trySetMap(Map map, String key, Object value) {
        if (map.get(key) != null) {
            map.put(key, value);
        }
    }

    @Before("execution(* com.ower.dsyz..dao..*.update*(..))")
    public void beforeUpdate(JoinPoint jp) {
        for (Object obj : jp.getArgs()) {
            if (obj.getClass().isAssignableFrom(Map.class)) {
                Map map = (Map) obj;
                trySetMap(map, "updateDate", new Date());
                trySetMap(map,"updateUser",this.getUserId());
            } else {
                trySetProperty(obj, "UpdateDate", new Date());
                trySetProperty(obj, "UpdateUser",this.getUserId());
            }
        }
    }

    private void trySetProperty(Object obj, String propertyName, Object propertyValue) {
        try {
            Method getMethod;
            try {
                getMethod = obj.getClass().getMethod("get" + propertyName, new Class[0]);
            } catch (NoSuchMethodException e) {
                return;
            }

            if (getMethod != null) {
                Object value = getMethod.invoke(obj, new Object[0]);
                if (value == null ||((value instanceof String) && StringUtils.isBlank((String)value))) {
                    Method setMethod = obj.getClass().getMethod("set" + propertyName, new Class[] { propertyValue.getClass() });
                    setMethod.invoke(obj, new Object[] { propertyValue });
                    if (logger.isDebugEnabled()) {
                        logger.debug(String.format("Set %s to %s for %s.",
                                new Object[] { propertyValue, propertyName, obj.getClass().getName() }));
                    }
                }
            }
        } catch (Exception e) {
            logger.warn(
                    String.format("Error set %s to %s for %s.", new Object[] { propertyValue, propertyName, obj.getClass().getName() }),
                    e);
        }
    }
    
    /**
     * 获取userId
     * @return
     */
    private String getUserId(){
    	return StringUtils.isNotBlank(CurrentThreadHolder.getUserId())?CurrentThreadHolder.getUserId():"not login or not need login";
    }
}
