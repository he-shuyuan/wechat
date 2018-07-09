/**
 * Copyright(c) Foresee Science & Technology Ltd.
 */
package com.ower.dsyz.common.core.redis.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ower.dsyz.common.core.redis.IRedisService;

/**
 * <pre>
 * redis 同步锁
 * </pre>
 *
 * @author HeShuyuan@163.com
 * @date 2017年11月13日
 * @version 1.00.00
 * 
 *          <pre>
 * 修改记录 
 *    修改后版本:     修改人：  修改日期:     修改内容:
 *          </pre>
 */
@Service
public class SyncLockUtil {

    @Autowired
    private IRedisService        redisClient;

    private final long                LOCK_TIME_OUT = 1;               //默认1s 

    private final static Logger LOG           = LoggerFactory.getLogger(SyncLockUtil.class);

    private final String PREFIX_KEY = "lock:sync:";

    public long requireLock(String key) {
         return this.requireLock(key, "default", LOCK_TIME_OUT);
    }

    public long requireLock(String key, String value) {
        return this.requireLock(key, value, LOCK_TIME_OUT);
    }

    public long requireLock(String key, long timeOut) {
        return this.requireLock(key, "default", timeOut);
    }

    /**
     * 请求锁
     * @param key
     * @param value
     * @param timeOut void
     */
    public long requireLock(String key, String value, long timeOut) {
        boolean lock = false;
        do {
            lock = redisClient.setNx(PREFIX_KEY+key, value, timeOut);
           // lockValue = value;
            if (lock) {
                LOG.info("\n【分布式锁】=》[{}]获取了锁[{}]", value,key);
                return System.currentTimeMillis() - 1 + timeOut * 1000;
            } else {
                try {
                    Thread.sleep(10);//阻塞0.01s
                } catch (InterruptedException e) {
                    LOG.error("线程中断出错:{}", e);
                }
            }
        } while (!lock);
        
            return 0;
    }

    /**
     * 
     * 释放锁
     * @param key void
     */
    public void releaseLock(String key,String value,long timeOut) {
        long currentTime = System.currentTimeMillis();
        if (currentTime <= timeOut) {
            redisClient.delete(PREFIX_KEY+key);
            LOG.info("\n【分布式锁】=》[{}]释放了锁[{}]", value, key);
        }else{
            LOG.info("\n【分布式锁】=》[{}]超时，锁[{}]已被暂用或销毁", value, key);
        }
    }
}
