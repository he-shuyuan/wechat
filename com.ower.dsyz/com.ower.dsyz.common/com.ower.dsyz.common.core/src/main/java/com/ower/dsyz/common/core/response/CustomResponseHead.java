/**
 * Copyright(c) Foresee Science & Technology Ltd. 
 */
package com.ower.dsyz.common.core.response;

/**
 * <pre>
 * 回参头部
 * </pre>
 *
 * @author HeShuyuan@foresee.com.cn
 * @date 2017年12月28日
 * @version 1.00.00
 * 
 *          <pre>
 * 修改记录 
 *    修改后版本:     修改人：  修改日期:     修改内容:
 *          </pre>
 */

public class CustomResponseHead {

    private String errorCode;
    
    private Object errorMsg;
    
    private String threadId;
    
    private String responseTime;
    
    
    public CustomResponseHead(){
        
    }
    
    public CustomResponseHead(Boolean success){
        if(success){
            this.errorCode = "0";
            this.errorMsg = "请求成功";
        }else{
            this.errorCode = "1";
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


    @Override
    public String toString() {
        return "CustomResponseHead [errorCode=" + errorCode + ", errorMsg=" + errorMsg + ", threadId=" + threadId + ", responseTime="
                + responseTime + "]";
    }
    
    
}
