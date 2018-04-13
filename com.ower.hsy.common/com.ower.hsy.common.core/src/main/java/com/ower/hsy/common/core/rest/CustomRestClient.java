/**
 * Copyright(c) Foresee Science & Technology Ltd. 
 */
package com.ower.hsy.common.core.rest;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import com.ower.hsy.common.core.response.CustomResponse;
import com.ower.hsy.common.core.rest.impl.ICustomRestClient;
import com.ower.hsy.common.core.util.Jackson;

/**
 * <pre>
 * 实现类
 * </pre>
 *
 * @author HeShuyuan@foresee.com.cn
 * @date 2018年1月23日
 * @version 1.00.00
 * 
 *          <pre>
 * 修改记录 
 *    修改后版本:     修改人：  修改日期:     修改内容:
 *          </pre>
 */
@Service
public class CustomRestClient implements ICustomRestClient {

    @Resource
    private RestTemplate restTemplate; 
    
    private Logger log=LoggerFactory.getLogger(CustomRestClient.class);
    
    private static final String APPLICATION_JSON = "application/json;charset=UTF-8";
    
    @Override
    public <T> CustomResponse<T> postInner(String url, Object data, String contentType,Class<T> responseClass) {

        return this.postInner(url, Jackson.toJson(data),contentType,responseClass);
    }

    @Override
    public <T> CustomResponse<T> postInner(String url, Object data,Class<T> responseClass) {

        return this.postInner(url, Jackson.toJson(data),responseClass);
    }

    @Override
    public <T> CustomResponse<T> postInner(String url, String data,Class<T> responseClass) {

        return this.postInner(url, data,APPLICATION_JSON,responseClass);
    }

    @SuppressWarnings({ "unchecked", "rawtypes" })
    @Override
    public <T> CustomResponse<T> postInner(String url, String data, String contentType,Class<T> responseClass) {
        if(!url.startsWith("http://")){
           url = "http://" + url;  
        }
        MultiValueMap<String, String> header = new HttpHeaders();
        header.add("Content-Type", APPLICATION_JSON);
        header.add("Accept", APPLICATION_JSON);
        header.add("Accept-Charset", "UTF-8");

        HttpEntity<String> httpEnty = new HttpEntity<String>(data,header);
        log.debug("\n【内部请求】=》url=>>>{}\n"
                + "【内部请求】=》header=>>>{}\n"
                + "【内部请求】=》param=>>>{}\n"
                ,url,httpEnty,data);
        ResponseEntity<CustomResponse> res = restTemplate
                .exchange(url, HttpMethod.POST, httpEnty, CustomResponse.class, "");
        if(res.getStatusCodeValue() == 200){
            log.debug("\n【内部请求】=》response=>>>{}\n"
                    + "【内部请求】=》body=>>>{}\n",res,res.getBody());
            Object ob = res.getBody().getBody();
            String json = Jackson.toJson(ob);
            if(ob instanceof ArrayList){
                List<T> t = Jackson.fromJsonArray(json, responseClass);
                res.getBody().setBody(t);
            }else{
                T t =Jackson.fromJson(json, responseClass);
                res.getBody().setBody(t);
            }
            return (CustomResponse<T>)res.getBody();
        }
        return CustomResponse.error(res.getStatusCodeValue()+"","url=["+url+"]请求错误");
    }
}
