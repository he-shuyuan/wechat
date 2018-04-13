package com.ower.hsy.wechat.service.impl.storage;

import org.springframework.beans.factory.annotation.Autowired;
import com.ower.hsy.common.core.redis.IRedisService;
import me.chanjar.weixin.mp.api.WxMpInMemoryConfigStorage;

/**
 * 
 * <pre>
 * 基于redis的缓存
 * </pre>
 *
 * @author HeShuyuan@foresee.com.cn
 * @date 2018年1月11日
 * @version 1.00.00
 * 
 *          <pre>
 * 修改记录 
 *    修改后版本:     修改人：  修改日期:     修改内容:
 *          </pre>
 */
public class WxMpRedisStorage extends WxMpInMemoryConfigStorage {

	@Autowired
	private IRedisService redisService;

	private static final String ACCESS_TOKEN_KEY = "wx:common:token:";
	private static final String JSAPI_TICKET_KEY = "wx:common:jsdk:ticket:";
	private static final String CARDAPI_TICKET_KEY = "wx:common:jsdk:cardTicket";
	private static final int delay = 200;

	protected volatile String smrzSrc;//微信实名认证 src参数
	protected volatile String smrzKey;//微信实名认证 key参数

	public String getSmrzSrc() {
		return smrzSrc;
	}

	public void setSmrzSrc(String smrzSrc) {
		this.smrzSrc = smrzSrc;
	}

	public String getSmrzKey() {
		return smrzKey;
	}

	public void setSmrzKey(String smrzKey) {
		this.smrzKey = smrzKey;
	}

	@Override
	public String getAccessToken() {
		return redisService.get(ACCESS_TOKEN_KEY.concat(appId));
	}

	@Override
	public boolean isAccessTokenExpired() {
		return redisService.ttl(ACCESS_TOKEN_KEY.concat(appId)) < delay;
	}

	@Override
	public long getExpiresTime() {
		long ttl = redisService.ttl(ACCESS_TOKEN_KEY.concat(this.appId));
		return ttl < delay ? 0L : ttl;
	}
	
	@Override
	public synchronized void updateAccessToken(String accessToken, int expiresInSeconds) {
		redisService.set(ACCESS_TOKEN_KEY.concat(appId), accessToken, expiresInSeconds - delay);
	}

	@Override
	public void expireAccessToken() {
		redisService.expire(ACCESS_TOKEN_KEY.concat(appId), 0);
	}

	@Override
	public String getJsapiTicket() {
		return redisService.get(JSAPI_TICKET_KEY.concat(appId));
	}

	@Override
	public boolean isJsapiTicketExpired() {
		return redisService.ttl(JSAPI_TICKET_KEY.concat(appId)) < delay;
	}

	@Override
	public synchronized void updateJsapiTicket(String jsapiTicket, int expiresInSeconds) {
		redisService.set(JSAPI_TICKET_KEY.concat(appId), jsapiTicket, expiresInSeconds - delay);
	}

	@Override
	public void expireJsapiTicket() {
		redisService.expire(JSAPI_TICKET_KEY.concat(appId), 0);
	}

	/**
	 * 卡券api_ticket
	 */
	@Override
	public String getCardApiTicket() {
		return redisService.get(CARDAPI_TICKET_KEY.concat(appId));
	}

	@Override
	public boolean isCardApiTicketExpired() {
		return redisService.ttl(CARDAPI_TICKET_KEY.concat(appId)) < delay;
	}

	@Override
	public synchronized void updateCardApiTicket(String cardApiTicket, int expiresInSeconds) {
		redisService.set(CARDAPI_TICKET_KEY.concat(appId), cardApiTicket, expiresInSeconds - delay);
	}

	@Override
	public void expireCardApiTicket() {
		redisService.expire(CARDAPI_TICKET_KEY.concat(appId), 0);
	}
}