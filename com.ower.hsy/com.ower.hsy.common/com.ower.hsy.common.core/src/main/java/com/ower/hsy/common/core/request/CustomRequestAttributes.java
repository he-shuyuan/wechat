/**
 * Copyright(c) Foresee Science & Technology Ltd.
 */
package com.ower.hsy.common.core.request;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.ower.hsy.common.core.util.IDUtil;

/**
 * 
 * <pre>
 * request参数
 * </pre>
 *
 * @author HeShuyuan@foresee.com.cn
 * @date 2017年12月29日
 * @version 1.00.00
 * 
 *          <pre>
 * 修改记录 
 *    修改后版本:     修改人：  修改日期:     修改内容:
 *          </pre>
 */
public class CustomRequestAttributes {

    private HttpServletRequest  request;

    private HttpServletResponse response;

    private String              requestId;

    public CustomRequestAttributes(HttpServletRequest request, HttpServletResponse response,String requestId) {
        this.request = request;
        this.response = response;
        this.requestId = requestId;
    }
    
    public CustomRequestAttributes(HttpServletRequest request, HttpServletResponse response) {
        this(request,response,IDUtil.getUUID());
    }


    public CustomRequestAttributes() {}

    public HttpServletRequest getRequest() {
        return this.request;
    }

    public HttpServletResponse getResponse() {
        return this.response;
    }

    public String getRequestId() {
        return requestId;
    }

    public void setRequest(HttpServletRequest request) {
        this.request = request;
    }

    public void setResponse(HttpServletResponse response) {
        this.response = response;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }

}
