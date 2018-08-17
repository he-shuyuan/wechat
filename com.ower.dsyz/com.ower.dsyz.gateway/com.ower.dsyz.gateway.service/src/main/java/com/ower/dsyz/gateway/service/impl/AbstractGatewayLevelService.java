package com.ower.dsyz.gateway.service.impl;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import com.ower.dsyz.common.core.exception.CustomRunTimeException;
import com.ower.dsyz.common.core.redis.IRedisService;
import com.ower.dsyz.common.core.response.CustomResponse;
import com.ower.dsyz.common.core.util.HashUtil;
import com.ower.dsyz.gateway.manual.dto.GatewayRequest;
import com.ower.dsyz.gateway.service.IGatewayLevelService;

/**
 * 层级抽象类
 * @author 独树一帜
 *
 */
public abstract class AbstractGatewayLevelService implements IGatewayLevelService{
    
    /**
     * token key
     */
    public static final String TOKEN_PRIFIXED= "user:token:";
   
    @Autowired
    private IRedisService redisService;
    
	@Override
	public Object handleRequestLevel(GatewayRequest request) {
	    this.checkAppId(request.getAppId());
	    this.checkRequestId(request.getRequestId());
		return this.resultHandle(request);
	}

	/**
	 * 校验requestId
	 * TODO。
	 * @param requestId void
	 */
	private void checkRequestId(String requestId) {
	    if(StringUtils.isBlank(requestId)){
	        throw new CustomRunTimeException("001405","requestId不能为空");
	    }
	    if(requestId.length()!=32){
            throw new CustomRunTimeException("001405","requestId应为32位");
        }
	}

    /**
	 * 校验appId  
	 * TODO。
	 * @param appId void
	 */
    private void checkAppId(String appId) {
        
    }
    
    /**
     * 检查token
     * @param token
     * @return String
     */
    public String checkToken(String token){
        String userId = redisService.getString(TOKEN_PRIFIXED+token);
        if(StringUtils.isBlank(userId)){
            throw new CustomRunTimeException("001001", "用户未登录或登录已超时");
        }
        this.redisService.expire(TOKEN_PRIFIXED+token, 30*60L);
        return userId;
    }
    
    /**
     * 生成token
     * @param userId
     * @return String
     */
    public String createToken(String userId){
        String token = HashUtil.md5Hex(userId+System.currentTimeMillis());
        redisService.setString(TOKEN_PRIFIXED+token,userId,30*60L);
        return token;
    }
	
    
    /**
     * 生成token
     * @param userId
     * @return String
     */
    public void destroyToken(String token){
        redisService.delete(TOKEN_PRIFIXED+token);
    }
    
    /**
     * 内部请求
     * TODO。
     * @param request
     * @return CustomResponse<?>
     */
	abstract CustomResponse<?> resultHandle(GatewayRequest request);
	
}
