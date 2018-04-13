/**
 * Copyright(c) Foresee Science & Technology Ltd. 
 */
package com.ower.hsy.common.core.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * <pre>
 * 分布式锁
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
@Target({java.lang.annotation.ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface SyncRedisLock {

    /**
     * 
     * 锁id
     * @return String
     */
    public String id() default "";
    
    /**
     * 
     * 超时时间
     * @return long
     */
    public long timeOut() default 2;
    
    
    /**
     * 属性值
     * @return String
     */
    public String value() default "";
}
