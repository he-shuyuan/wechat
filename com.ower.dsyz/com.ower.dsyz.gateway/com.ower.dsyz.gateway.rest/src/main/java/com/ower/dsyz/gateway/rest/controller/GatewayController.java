/**
 * Copyright(c) Foresee Science & Technology Ltd. 
 */
package com.ower.dsyz.gateway.rest.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import com.ower.dsyz.common.core.request.CustomRequestParam;
import com.ower.dsyz.gateway.model.GatewayRequest;
import com.ower.dsyz.gateway.service.IGatewayRequestFactory;

/**
 * <pre>
 * 网关统一配置
 * </pre>
 *
 * @author HeShuyuan@foresee.com.cn
 * @date 2018年1月22日
 * @version 1.00.00
 * 
 *          <pre>
 * 修改记录 
 *    修改后版本:     修改人：  修改日期:     修改内容:
 *          </pre>
 */

@RestController
@RequestMapping({"/"})
public class GatewayController {

    @Resource
    IGatewayRequestFactory gatewayRequestFactory;
    
    @RequestMapping(value={"/{appName}/{serviceLevel}/{serviceName}/{serviceMethod}"}, method={RequestMethod.POST})
    @ResponseBody
    public Object doPost(HttpServletRequest request, 
            HttpServletResponse response,
            @PathVariable("appName") String appName, 
            @PathVariable("serviceLevel") String serviceLevel,
            @PathVariable("serviceName") String serviceName, 
            @PathVariable("serviceMethod") String serviceMethod,
            @RequestParam(value="token",required = false) String token,
            @RequestParam(value="appId",required = false) String appId,
            @RequestParam(value="requestId",required = false) String requestId,
            @RequestParam(value="sign",required = false) String sign,
            @RequestBody CustomRequestParam param)
    {
    	GatewayRequest gatewayRequest= new GatewayRequest();
    	String url = request.getRequestURI().substring(1);
    	gatewayRequest.setAppId(appId);
    	gatewayRequest.setAppName(appName);
    	gatewayRequest.setParam(param);
    	gatewayRequest.setRequestId(requestId);
    	gatewayRequest.setServiceLevel(serviceLevel);
    	gatewayRequest.setServiceMethod(serviceMethod);
    	gatewayRequest.setServiceName(serviceName);
    	gatewayRequest.setSign(sign);
    	gatewayRequest.setToken(token);
    	gatewayRequest.setUrl(url);
      
      return gatewayRequestFactory.handleRequest(gatewayRequest);
    }
    
    
    @RequestMapping(value={"/{appName}/{serviceLevel}/{serviceName}/{serviceMethod}/{extMenthod}"}, method={RequestMethod.POST})
    @ResponseBody
    public Object doPost(HttpServletRequest request, 
            HttpServletResponse response,
            @PathVariable("appName") String appName, 
            @PathVariable("serviceLevel") String serviceLevel,
            @PathVariable("serviceName") String serviceName, 
            @PathVariable("serviceMethod") String serviceMethod,
            @PathVariable("extMenthod") String extMenthod,
            @RequestParam(value="token",required = false) String token,
            @RequestParam(value="appId",required = false) String appId,
            @RequestParam(value="requestId",required = false) String requestId,
            @RequestParam(value="sign",required = false) String sign,
            @RequestBody CustomRequestParam param)
    {
    	GatewayRequest gatewayRequest= new GatewayRequest();
    	String url = request.getRequestURI().substring(1);
    	gatewayRequest.setAppId(appId);
    	gatewayRequest.setAppName(appName);
    	gatewayRequest.setParam(param);
    	gatewayRequest.setRequestId(requestId);
    	gatewayRequest.setServiceLevel(serviceLevel);
    	gatewayRequest.setServiceMethod(serviceMethod);
    	gatewayRequest.setExtMenthod(extMenthod);
    	gatewayRequest.setServiceName(serviceName);
    	gatewayRequest.setSign(sign);
    	gatewayRequest.setToken(token);
    	gatewayRequest.setUrl(url);
      
      return gatewayRequestFactory.handleRequest(gatewayRequest);
    }
    
}
