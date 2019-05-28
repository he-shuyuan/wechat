package com.bda.anlyz.core.common;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import com.bda.common.core.helper.ResultHelper;
import com.bda.portal.core.BizException;

@ControllerAdvice("com.bda.anlyz.core")
public class ExceptionAdvice {
	
	/**
	 * 系统位置异常
	 * @param e
	 * @return
	 */
	@ResponseBody
	@ExceptionHandler(value = { Exception.class })
	public Object exceptionHandler(Exception e) {
		return ResultHelper.fail();
	}
	
	/**
	 * 业务异常
	 * @param e
	 * @return
	 */
	@ResponseBody
	@ExceptionHandler(value = { BizException.class })
	public Object bizExceptionHandler(BizException e) {
		return ResultHelper.fail("003");
	}

}