/**
 * Copyright(c) Foresee Science & Technology Ltd. 
 */
package com.ower.dsyz.common.core.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.ower.dsyz.common.core.constant.ErrorCodeConstants;

/**
 * <pre>
 * 回参头部
 * </pre>
 *
 * @author HeShuyuan@163.com
 * @date 2017年12月28日
 * @version 1.00.00
 * 
 *          <pre>
 * 修改记录 
 *    修改后版本:     修改人：  修改日期:     修改内容:
 *          </pre>
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CustomResponseHead {

	/**
	 * 错误码
	 */
    private String errorCode;
    /**
     * 错误消息
     */
    private String errorMsg;
    
    /**
     * 请求id
     */
    private String requestId;
    
    /**
     * 响应时间
     */
    private String responseTime = System.currentTimeMillis()+"";
    
    
    public CustomResponseHead(){
      
    }
    
    public CustomResponseHead(Boolean success){
        if(success){
            this.errorCode = ErrorCodeConstants.SUCCESS;
            this.errorMsg = "请求成功";
        }else{
            this.errorCode = ErrorCodeConstants.DEFAULT_ERROR;
            this.errorMsg = "请求失败";
        }
    }
    public String getErrorCode() {
        return errorCode;
    }

    
    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    
    public String getErrorMsg() {
        return errorMsg;
    }

    
    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }


    
    public String getResponseTime() {
        return responseTime;
    }

    
    public void setResponseTime(String responseTime) {
        this.responseTime = responseTime;
    }


    public String getRequestId() {
		return requestId;
	}

	public void setRequestId(String requestId) {
		this.requestId = requestId;
	}


	public Boolean success(){
	    return "0".equals(this.errorCode);
	}


	@Override
	public String toString() {
		return "CustomResponseHead [errorCode=" + errorCode + ", errorMsg=" + errorMsg 
				+ ", requestId=" + requestId + ", responseTime=" + responseTime +
				"]";
	}

	
    
    
}
