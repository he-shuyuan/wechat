package com.ower.dsyz.common.core.interceptor;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.ower.dsyz.common.core.util.Jackson;
/**
 * 接口监控
 * @author heshuyuan
 *
 */

@Aspect
@Component
@Order(999999)
public class ApiMonitorInterceptor {
	private static final Logger logger = LoggerFactory.getLogger(ApiMonitorInterceptor.class);

	@Pointcut("execution(* com.ower.dsyz..rest..*.*(..)) && @annotation(org.springframework.web.bind.annotation.RequestMapping)")
	public void controllerAspect() {
	}

	@Around("controllerAspect()")
	public Object interceptor(ProceedingJoinPoint pjp) throws Throwable {
		long beginTime = System.currentTimeMillis();
		MethodSignature signature = (MethodSignature) pjp.getSignature();
		String methodName = signature.getDeclaringTypeName() + "." + signature.getMethod().getName();
		String url = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest().getRequestURI();
		Object result = null;
		Object[] args = pjp.getArgs();
		logger.info("Request begin: {}, URL: {}, urlParams: {}, jsonParams: {}", methodName, url,args);
		try{
			result = pjp.proceed();
			logger.info("Request end({}): {}, URL: {}, result: {}",
					new Object[] { Long.valueOf(System.currentTimeMillis() - beginTime), methodName, url,
							Jackson.toJson(result) });
			return result;
		}catch(Exception ex){
			logger.warn("Request end({}): {}, URL: {}, result: {}",
					new Object[] { Long.valueOf(System.currentTimeMillis() - beginTime), methodName, url,
							ex.getMessage() });
			throw ex;
		}
	}

}
