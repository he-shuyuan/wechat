package com.ower.dsyz.common.core.advice;

import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;
import com.ower.dsyz.common.core.annotation.NotInAspect;
import com.ower.dsyz.common.core.holder.CurrentThreadHolder;
import com.ower.dsyz.common.core.response.CustomResponse;


@SuppressWarnings("rawtypes")
@ControllerAdvice("com.ower")
public class CustomResponseBodyAdvice implements ResponseBodyAdvice {

	public boolean supports(MethodParameter returnType, Class converterType) {
		return !returnType.getMethod().isAnnotationPresent(NotInAspect.class);
	}

	@ResponseBody
	public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType,
			Class selectedConverterType, ServerHttpRequest request, ServerHttpResponse response) {
        if(!(body instanceof CustomResponse)){
        	body = CustomResponse.success(body);
        }
        response.getHeaders().setContentType(MediaType.APPLICATION_JSON);
        ((CustomResponse)body).getHead().setRequestId(CurrentThreadHolder.getRequestId());
        return body;
	}





}
