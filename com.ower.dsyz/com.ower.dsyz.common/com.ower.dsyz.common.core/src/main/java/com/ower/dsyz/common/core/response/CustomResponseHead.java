/**
 * Copyright(c) Foresee Science & Technology Ltd. 
 */
package com.ower.dsyz.common.core.response;

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

public class CustomResponseHead {

	/**
	 * 错误码
	 */
    private String errorCode;
    /**
     * 错误消息
     */
    private Object errorMsg;
    
    /**
     * 处理线程
     */
    private String threadId;
    
    /**
     * 请求id
     */
    private String requestId;
    
    /**
     * 响应时间
     */
    private String responseTime;
    
    /**
     * 请求时间戳
     */
    private String requestTimeStamp;
    
    
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

    
    public Object getErrorMsg() {
        return errorMsg;
    }

    
    public void setErrorMsg(Object errorMsg) {
        this.errorMsg = errorMsg;
    }

    
    public String getThreadId() {
        return threadId;
    }

    
    public void setThreadId(String threadId) {
        this.threadId = threadId;
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

	public String getRequestTimeStamp() {
		return requestTimeStamp;
	}

	public void setRequestTimeStamp(String requestTimeStamp) {
		this.requestTimeStamp = requestTimeStamp;
	}

	@Override
	public String toString() {
		return "CustomResponseHead [errorCode=" + errorCode + ", errorMsg=" + errorMsg + ", threadId=" + threadId
				+ ", requestId=" + requestId + ", responseTime=" + responseTime + ", requestTimeStamp="
				+ requestTimeStamp + "]";
	}

	
    
    
}
