package com.ower.dsyz.common.core.advice;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ower.dsyz.common.core.exception.BusinessException;
import com.ower.dsyz.common.core.response.CustomResponse;

@ControllerAdvice("com.ower")
public class ExceptionAdvice {
	
	/**
	 * 系统未知异常
	 * @param e
	 * @return
	 */
	@ResponseBody
	@ExceptionHandler(value = { Exception.class })
	public Object exceptionHandler(Exception e) {	
		return CustomResponse.error("未知错误");
	}
	
	/**
	 * 检验异常
	 * @param e
	 * @return
	 */
	@ResponseBody
	@ExceptionHandler(value = { MethodArgumentNotValidException.class })
	public Object exceptionHandler(MethodArgumentNotValidException e) {
		for(ObjectError error:e.getBindingResult().getAllErrors()){
			return CustomResponse.error(error.getDefaultMessage());
		}
		return CustomResponse.error("参数异常");
	}
	

	/**
	 * 系统位置异常
	 * @param e
	 * @return
	 */
	@ResponseBody
	@ExceptionHandler(value = { BusinessException.class })
	public Object BusinessExceptionHandler(BusinessException e) {
		return CustomResponse.error(e.getErrorCode(),e.getErrorMsg());
	}
}