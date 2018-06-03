/**
 * Copyright(c) Foresee Science & Technology Ltd. 
 */
package com.ower.dsyz.common.core.rest;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import com.ower.dsyz.common.core.holder.CustomLoginUserIdHolder;
import com.ower.dsyz.common.core.holder.CustomUrlParamHolder;
import com.ower.dsyz.common.core.response.CustomResponse;
import com.ower.dsyz.common.core.rest.impl.ICustomRestClient;
import com.ower.dsyz.common.core.util.Jackson;

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

	private Logger log = LoggerFactory.getLogger(CustomRestClient.class);

	private static final String APPLICATION_JSON = "application/json;charset=UTF-8";

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public <T> CustomResponse<T> postInner(String url, Object data, String contentType, Class<T> responseClass,
			Map<String, String> extHeader) {
		if (!url.startsWith("http://")) {
			url = "http://" + url;
		}
		String requestJson = null;
		if (data instanceof String) {
			requestJson = (String) data;
		} else {
			requestJson = Jackson.toJson(data);
		}
		MultiValueMap<String, String> header = new HttpHeaders();
		header.add("Content-Type", contentType);
		header.add("Accept", contentType);
		header.add("Accept-Charset", "UTF-8");
		if (StringUtils.isNotBlank(CustomLoginUserIdHolder.get())) {
			header.add("userId", CustomLoginUserIdHolder.get());
		}
		if (extHeader != null && !extHeader.isEmpty()) {
			this.mergeHeader(extHeader, header);
		}
		HttpEntity<String> httpEnty = new HttpEntity<String>(requestJson, header);
		log.info("\n【内部请求】=》url=>>>{}\n" + "【内部请求】=》header=>>>{}\n" + "【内部请求】=》param=>>>{}\n", url, header, data);
		ResponseEntity<CustomResponse> res = restTemplate.exchange(url, HttpMethod.POST, httpEnty, CustomResponse.class,
				CustomUrlParamHolder.get());
		if (res.getStatusCodeValue() == 200) {
			log.info("\n【内部请求】=》response=>>>{}\n" + "【内部请求】=》body=>>>{}\n", res, res.getBody());
			Object ob = res.getBody().getBody();
			String json = Jackson.toJson(ob);
			if (ob instanceof ArrayList) {
				List<T> t = Jackson.fromJsonArray(json, responseClass);
				res.getBody().setBody(t);
			} else {
				T t = Jackson.fromJson(json, responseClass);
				res.getBody().setBody(t);
			}
			return (CustomResponse<T>) res.getBody();
		}
		return CustomResponse.error(res.getStatusCodeValue() + "", "url=[" + url + "]请求错误");
	}

	@Override
	public <T> CustomResponse<T> postInner(String url, Object data, Class<T> responseClass,
			Map<String, String> extHeader) {
		return this.postInner(url, data, APPLICATION_JSON, responseClass, extHeader);
	}

	@Override
	public <T> CustomResponse<T> postInner(String url, Object data, Class<T> responseClass) {
		return this.postInner(url, data, APPLICATION_JSON, responseClass);
	}

	@Override
	public <T> CustomResponse<T> postInner(String url, Object data, String contentType, Class<T> responseClass) {
		return this.postInner(url, data, contentType, responseClass, null);
	}

	/**
	 * 合并头部
	 * 
	 * @param extHeader
	 * @param header
	 */
	private void mergeHeader(Map<String, String> extHeader, MultiValueMap<String, String> header) {
		for (Map.Entry<String, String> entry : extHeader.entrySet()) {
			if (header.get(entry.getKey()) != null && header.get(entry.getKey()).indexOf(entry.getValue()) > -1) {
				header.remove(entry.getKey());
			}
			header.add(entry.getKey(), entry.getValue());
		}

	}

}
