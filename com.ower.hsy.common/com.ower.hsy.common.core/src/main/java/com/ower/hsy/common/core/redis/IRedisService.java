/**
 * Copyright(c) Foresee Science & Technology Ltd.
 */
package com.ower.hsy.common.core.redis;

/**
 * <pre>
 * 分布式缓存数据库
 * </pre>
 *
 * @author HeShuyuan@foresee.com.cn
 * @date 2018年1月4日
 * @version 1.00.00
 * 
 *          <pre>
 * 修改记录 
 *    修改后版本:     修改人：  修改日期:     修改内容:
 *          </pre>
 */

public abstract interface IRedisService {

    public abstract boolean setNx(String paramString1, String paramString2, long paramLong);

    public abstract void set(String paramString1, String paramString2);

    public abstract void set(String paramString1, String paramString2, long paramLong);

    public abstract String get(String paramString);

    public abstract void delete(String paramString);
    
    public abstract void deleteBatch(String reg);
    /**
     * 获取指定key值的有效时间
     * @param key
     * @return
     */
    public Long ttl(String key);
    
    /**
     * 设置redis中指定key的超时时间，单位（秒）
     * @param key
     * @param expiredSeconds
     */
    public void expire(String key, int expiredSeconds);
}
