package com.ower.hsy.common.core.redis.impl;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import com.ower.hsy.common.core.redis.IRedisService;



/**
 * RedisServiceImpl
 */
@Service(IRedisService.BEAN_ID)
public class RedisServiceImpl implements IRedisService {
	
	@Resource(name = "redisTemplate")
	private RedisTemplate<String, ?> redisTemplate;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.foresee.base.redis.IRedisService#setString(java.lang.String,
	 * java.lang.String, int)
	 */
	@Override
	public void setString(String key, String value, final Long expiredSeconds) {
		final String keyf = (String) key;
		final String valuef = value;

		redisTemplate.execute(new RedisCallback<Long>() {
			public Long doInRedis(RedisConnection connection) throws DataAccessException {
				byte[] keyb = keyf.getBytes();
				byte[] valueb = valuef.getBytes();
				connection.set(keyb, valueb);
				if (expiredSeconds > 0) {
					connection.expire(keyb, expiredSeconds);
				}
				return 1L;
			}
		});
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.foresee.base.redis.IRedisService#setObject(java.lang.String,
	 * java.lang.Object, int)
	 */
	@Override
	public void setObject(String key, Serializable value, final Long expiredSeconds) {
		final String keyf = (String) key;
		final Object valuef = value;

		redisTemplate.execute(new RedisCallback<Long>() {
			public Long doInRedis(RedisConnection connection) throws DataAccessException {
				byte[] keyb = keyf.getBytes();
				byte[] valueb = toByteArray(valuef);
				connection.set(keyb, valueb);
				if (expiredSeconds > 0) {
					connection.expire(keyb, expiredSeconds);
				}
				return 1L;
			}
		});
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.foresee.base.redis.IRedisService#getString(java.lang.String)
	 */
	@Override
	public String getString(String key) {
		return this.getString(key, 0L);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.foresee.base.redis.IRedisService#getObject(java.lang.String)
	 */
	@Override
	public Object getObject(String key) {
		return this.getObject(key, 0L);
	}

	/**
	 * 描述 : <Object转byte[]>. <br>
	 * <p>
	 * <使用方法说明>
	 * </p>
	 * 
	 * @param obj
	 * @return
	 */
	private byte[] toByteArray(Object obj) {
		byte[] bytes = null;
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		try {
			ObjectOutputStream oos = new ObjectOutputStream(bos);
			oos.writeObject(obj);
			oos.flush();
			bytes = bos.toByteArray();
			oos.close();
			bos.close();
		} catch (IOException ex) {
			ex.printStackTrace();
		}
		return bytes;
	}

	/**
	 * 描述 : <byte[]转Object>. <br>
	 * <p>
	 * <使用方法说明>
	 * </p>
	 * 
	 * @param bytes
	 * @return
	 */
	private Object toObject(byte[] bytes) {
		Object obj = null;
		try {
			ByteArrayInputStream bis = new ByteArrayInputStream(bytes);
			ObjectInputStream ois = new ObjectInputStream(bis);
			obj = ois.readObject();
			ois.close();
			bis.close();
		} catch (IOException ex) {
			ex.printStackTrace();
			throw new RuntimeException(ex);
		} catch (ClassNotFoundException ex) {
			ex.printStackTrace();
			throw new RuntimeException(ex);
		}
		return obj;
	}

	public void evict(Object key) {
		final String keyf = (String) key;
		redisTemplate.execute(new RedisCallback<Long>() {
			public Long doInRedis(RedisConnection connection) throws DataAccessException {
				return connection.del(keyf.getBytes());
			}
		});
	}

	public void clear() {
		redisTemplate.execute(new RedisCallback<String>() {
			public String doInRedis(RedisConnection connection) throws DataAccessException {
				connection.flushDb();
				return "ok";
			}
		});
	}

	@Override
	public String getString(String key, final Long expiredSeconds) {
		final String keyf = (String) key;
		String object = null;
		object = (String) redisTemplate.execute(new RedisCallback<String>() {
			public String doInRedis(RedisConnection connection) throws DataAccessException {

				byte[] key = keyf.getBytes();
				byte[] value = connection.get(key);
				if (value == null) {
					return null;
				}
				if (expiredSeconds > 0) {
					connection.expire(key, expiredSeconds);
				}
				return new String(value);

			}
		});
		return object;
	}

	@Override
	public Object getObject(String key, final Long expiredSeconds) {
		final String keyf = (String) key;
		Object object = null;
		object = redisTemplate.execute(new RedisCallback<Object>() {
			public Object doInRedis(RedisConnection connection) throws DataAccessException {

				byte[] key = keyf.getBytes();
				byte[] value = connection.get(key);
				if (value == null) {
					return null;
				}
				if (expiredSeconds > 0) {
					connection.expire(key, expiredSeconds);
				}
				return toObject(value);

			}
		});
		return object;
	}

	@Override
	public void delete(String key) {
		final String keyf = (String) key;
		redisTemplate.execute(new RedisCallback<Long>() {
			public Long doInRedis(RedisConnection connection) throws DataAccessException {
				byte[] keyb = keyf.getBytes();
				connection.del(keyb);
				return 1L;
			}
		});
	}

	@Override
	public void expire(String key, final Long expiredSeconds) {
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
	public long getRedisSize() {
		long rediseSize = 0;
		rediseSize = (Long) redisTemplate.execute(new RedisCallback<Object>() {
			public Long doInRedis(RedisConnection connection) throws DataAccessException {
				return connection.dbSize();
			}
		});
		return rediseSize / 2;
	}

	@Override
	public boolean setNx(String key,long timeOut) {
		final String lock_key = key;
		Boolean isUnLock = redisTemplate.execute(new RedisCallback<Boolean>() {
			public Boolean doInRedis(RedisConnection connection) throws DataAccessException {
				boolean isUnLock = connection.setNX(lock_key.getBytes(), "lock".getBytes());
				connection.expire(lock_key.getBytes(), timeOut);
				return isUnLock;
			}
		});
		return isUnLock;
	}
	   @Override
	    public boolean setNx(String key,String value,long timeOut) {
	        final String lock_key = key;
	        Boolean isUnLock = redisTemplate.execute(new RedisCallback<Boolean>() {

            public Boolean doInRedis(RedisConnection connection) throws DataAccessException {
                boolean isUnLock = connection.setNX(lock_key.getBytes(), value.getBytes());
                connection.expire(lock_key.getBytes(), timeOut);
                return isUnLock;
            }
	        });
	        return isUnLock;
	    }
	   
	@Override
	public Long ttl(String key) {
		final String keyf = (String) key;
		long ttl = 0;
		ttl = (Long) redisTemplate.execute(new RedisCallback<Object>() {
			public Long doInRedis(RedisConnection connection) throws DataAccessException {
				byte[] key = keyf.getBytes();
				return connection.ttl(key);
			}
		});
		return ttl;
	}

    @Override
    public Set<String> cleanBatchStorage(String reg) { 
        if(StringUtils.isBlank(reg)){
            return new HashSet<String>();
        }
        Set<String> set = redisTemplate.keys(reg);
        Iterator<String> it = set.iterator ();  
        while (it.hasNext ()) {  
            String key = it.next ();  
            redisTemplate.delete(key);  
        } 
        return set;
    }
    
}
