package com.bda.anlyz.core.common;

import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;


@SuppressWarnings("rawtypes")
@ControllerAdvice("com.bda.anlyz.core")
public class CustomResponseBodyAdvice implements ResponseBodyAdvice {

	public boolean supports(MethodParameter returnType, Class converterType) {
		System.err.println(returnType);
		System.err.println(converterType);
		return true;
	}

	public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType,
			Class selectedConverterType, ServerHttpRequest request, ServerHttpResponse response) {
		System.err.println(body);
		System.err.println(selectedContentType);
		return body;
	}





}
