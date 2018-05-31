/**
 * Copyright(c) Foresee Science & Technology Ltd. 
 */
package com.ower.hsy.gateway.rest.controller;

import java.util.Map;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import com.ower.hsy.common.core.request.CustomRequestParam;
import com.ower.hsy.common.core.response.CustomResponse;
import com.ower.hsy.common.core.rest.impl.ICustomRestClient;

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
    private ICustomRestClient  customRestClient;
    
    private Logger               logger = LoggerFactory.getLogger(GatewayController.class);
    
    @RequestMapping(value={"/{appName}/{serviceLevel}/{serviceName}/{serviceMethod}"}, method={RequestMethod.POST})
    @ResponseBody
    public Object doPost(HttpServletRequest request, 
            HttpServletResponse response,
            @PathVariable("appName") String appName, 
            @PathVariable("serviceLevel") String serviceLevel,
            @PathVariable("serviceName") String serviceName, 
            @PathVariable("serviceMethod") String serviceMethod,
            @RequestBody CustomRequestParam param)
    {
      String url = request.getRequestURI().substring(1);
      return this.service(url,param);
    }
    
    
    @RequestMapping(value={"/{appName}/{serviceLevel}/{serviceName}/{serviceMethod}/{extMenthod}"}, method={RequestMethod.POST})
    @ResponseBody
    public Object doPost(HttpServletRequest request, 
            HttpServletResponse response,
            @PathVariable("appName") String appName, 
            @PathVariable("serviceLevel") String serviceLevel,
            @PathVariable("serviceName") String serviceName, 
            @PathVariable("serviceMethod") String serviceMethod,
            @PathVariable("serviceMethod") String extMenthod,
            @RequestBody CustomRequestParam param)
    {
        String url = request.getRequestURI().substring(1);
        return this.service(url,param);
    }
    
    
    
    private Object service(String url,Object data){
        logger.debug("网关请求=》{}，{}",url,data);
        @SuppressWarnings("rawtypes") 
        CustomResponse<Map> result = customRestClient.postInner(url,data,Map.class);
       
        return result;
    }
}
