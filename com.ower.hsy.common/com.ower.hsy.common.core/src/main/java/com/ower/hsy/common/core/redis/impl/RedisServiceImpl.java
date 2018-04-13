/**
 * Copyright(c) Foresee Science & Technology Ltd.
 */
package com.ower.hsy.common.core.redis.impl;

import java.util.Iterator;
import java.util.concurrent.TimeUnit;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import com.ower.hsy.common.core.redis.IRedisService;
import redis.clients.jedis.Jedis;

/**
 * <pre>
 * 分布式缓存实现类
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
@Component
public class RedisServiceImpl implements IRedisService {

    @Autowired
    RedisTemplate<String, String> redisTemplate;


    private Jedis getJedis() {
        return (Jedis) this.redisTemplate.getConnectionFactory().getConnection().getNativeConnection();
    }

    @Override
    public boolean setNx(String key, String value, long timeoutSeconds) {
        Jedis jedis = getJedis();
        Throwable localThrowable3 = null;
        try {
            String status = jedis.set(key, value, "NX", "EX", timeoutSeconds);
            return "OK".equals(status);
        } catch (Throwable localThrowable1) {
            localThrowable3 = localThrowable1;
            throw localThrowable1;
        } finally {
            if (jedis != null) {
                if (localThrowable3 != null) {
                    try {
                        jedis.close();
                    } catch (Throwable localThrowable2) {
                        localThrowable3.addSuppressed(localThrowable2);
                    }
                } else {
                    jedis.close();
                }
            }
        }
    }

    @Override
    public void set(String key, String value) {

        this.redisTemplate.opsForValue().set(key, value);
    }

    @Override
    public void set(String key, String value, long paramLong) {
        this.redisTemplate.opsForValue().set(key, value, paramLong, TimeUnit.SECONDS);

    }

    @Override
    public String get(String key) {

        return (String) this.redisTemplate.opsForValue().get(key);
    }

    @Override
    public void delete(String key) {
        this.redisTemplate.delete(key);
    }

    @Override
    public Long ttl(String key) {
        final String keyf = (String) key;
        long ttl = 0;
        ttl = (long) redisTemplate.execute(new RedisCallback<Object>() {
            public Long doInRedis(RedisConnection connection) throws DataAccessException {
                byte[] key = keyf.getBytes();
                return connection.ttl(key);
            }
        });
        return ttl;
    }

    @Override
    public void expire(String key, int expiredSeconds) {
        final String keyf = (String) key;
        redisTemplate.execute(new RedisCallback<Object>() {
            public Long doInRedis(RedisConnection connection) throws DataAccessException {
                byte[] key = keyf.getBytes();
                connection.expire(key, expiredSeconds);
                return 1L;
            }
        });
        
    }

    @Override
    public void deleteBatch(String reg) {
        if(StringUtils.isBlank(reg)){
            return;
        }
        Iterator<String> it = redisTemplate.keys (reg).iterator ();  
        while (it.hasNext ()) {  
            String key = it.next ();  
            redisTemplate.delete(key);  
        }  
    }

}
