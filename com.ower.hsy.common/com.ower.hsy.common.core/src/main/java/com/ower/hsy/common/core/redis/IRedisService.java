package com.ower.hsy.common.core.redis;

import java.io.Serializable;
import java.util.Set;

/**
 * redis 服务
 */
public interface IRedisService {

	public static String BEAN_ID = "redisService";

	/**
	 * 把字符串插入到redis服务器，指定超时时间，单位（秒）
	 * @param key
	 * @param value
	 * @param expiredSeconds
	 */
	public void setString(String key, String value, Long expiredSeconds);

	/**
	 * 把对象插入到redis服务器，指定超时时间，单位（秒）
	 * @param key
	 * @param value
	 * @param expiredSeconds
	 */
	public void setObject(String key, Serializable value, Long expiredSeconds);

	/**
	 * 从redis中获取字符串
	 * @param key
	 * @return
	 */
	public String getString(String key);

	/**
	 * 从redis中获取字符串，并重新设置超时时间，单位（秒）
	 * @param key
	 * @param expiredSeconds
	 * @return
	 */
	public String getString(String key, Long expiredSeconds);

	/**
	 * 从redis中获取对象
	 * @param key
	 * @return
	 */
	public Object getObject(String key);

	/**
	 * 从redis中获取对象，并重新设置超时时间，单位（秒）
	 * @param key
	 * @param expiredSeconds
	 * @return
	 */
	public Object getObject(String key, Long expiredSeconds);

	/**
	 * 从redis中删除指定key
	 * @param key
	 */
	public void delete(String key);

	/**
	 * 设置redis中指定key的超时时间，单位（秒）
	 * @param key
	 * @param expiredSeconds
	 */
	public void expire(String key, Long expiredSeconds);

	/**
	 * 获取redis中所有数据的数量
	 * @return
	 */
	public long getRedisSize();
	
	
	/**
	 * 判断指定key值是否存在，存在则返回false，不存在则增加key，同时返回true
	 * @param key
	 * @param timeOut
	 * @return
	 */
	public boolean setNx(String key,long timeOut);
	
	/**
     * 判断指定key值是否存在，存在则返回false，不存在则增加key，同时返回true
     * @param key
     * @param timeOut
     * @return
     */
    public boolean setNx(String key,String value,long timeOut);
    
	/**
	 * 获取指定key值的有效时间
	 * @param key
	 * @return
	 */
	public Long ttl(String key);
	
	
	/**
	 * 批量删除缓存
	 * @param reg void
	 */
	public Set<String> cleanBatchStorage(String reg);
}
