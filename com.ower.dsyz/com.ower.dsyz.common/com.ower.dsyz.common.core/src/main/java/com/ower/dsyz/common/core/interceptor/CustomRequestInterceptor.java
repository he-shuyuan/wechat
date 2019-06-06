package com.ower.dsyz.common.core.interceptor;

import java.lang.annotation.Annotation;
import java.util.Enumeration;
import java.util.LinkedHashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import com.ower.dsyz.common.core.annotation.EnableValid;
import com.ower.dsyz.common.core.annotation.NotInAspect;
import com.ower.dsyz.common.core.constant.ErrorCodeConstants;
import com.ower.dsyz.common.core.constant.UrlParamType;
import com.ower.dsyz.common.core.exception.BusinessException;
import com.ower.dsyz.common.core.holder.CustomRequestContextHolder;
import com.ower.dsyz.common.core.holder.CustomUrlParamHolder;
import com.ower.dsyz.common.core.model.ResultMsg;
import com.ower.dsyz.common.core.request.CustomLoginUserId;
import com.ower.dsyz.common.core.response.CustomResponse;
import com.ower.dsyz.common.core.util.IDUtil;
import com.ower.dsyz.common.core.util.Jackson;
import com.ower.dsyz.common.core.valid.service.ICustomValidService;

/**
 * 
 * <pre>
 * controller层切面控制
 * </pre>
 *
 * @author HeShuyuan@163.com
 * @date 2018年1月2日
 * @version 1.00.00
 * 
 *          <pre>
 * 修改记录 
 *    修改后版本:     修改人：  修改日期:     修改内容:
 *          </pre>
 */
@Deprecated
//@Aspect
//@Component
//@Order(999999)
public class CustomRequestInterceptor {

	private static final String USER_ID = "userId";

	private static final Logger logger = LoggerFactory.getLogger(CustomRequestInterceptor.class);

	private final String ERROR_MSG = "系统出现未知异常";

	@Autowired
	private ICustomValidService customValidService;

	@Pointcut("execution(* com.ower.dsyz..rest..*.*(..)) && @annotation(org.springframework.web.bind.annotation.RequestMapping)")
	public void controllerAspect() {
	}

	@Around("controllerAspect()")
	public Object interceptor(ProceedingJoinPoint pjp) throws Throwable {
		HttpServletRequest request = null;
		HttpServletResponse response = null;
		Boolean notInAspect = ((MethodSignature) pjp.getSignature()).getMethod().isAnnotationPresent(NotInAspect.class);
		if (RequestContextHolder.getRequestAttributes() != null) {
			request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
			response = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getResponse();
			CustomRequestContextHolder.initRequestAttributes(request, response);
		}
		long beginTime = System.currentTimeMillis();
		MethodSignature signature = (MethodSignature) pjp.getSignature();
		String methodName = signature.getDeclaringTypeName() + "." + signature.getMethod().getName();
		String url = null;
		if (request != null) {
			url = request.getRequestURL().toString();
		}
		Map<String, String> allParams = new LinkedHashMap<>();
		Enumeration<String> paramNames = request.getParameterNames();

		while (paramNames.hasMoreElements()) {
			String name = paramNames.nextElement();
			allParams.put(name, request.getParameter(name));
		}
		Object result = null;
		Object[] args = pjp.getArgs();
		setCustomLoginUserId(args, request, signature);

		logger.info("Request begin: {}, URL: {}, urlParams: {}, jsonParams: {}", methodName, url, allParams, args);
		CustomResponse<?> customResponse = null;
		String requestId = allParams.get(UrlParamType.REQUESTID.getName());
		try {
			try {
				// appId 和 requestId 检验
				CustomUrlParamHolder.put(UrlParamType.APPID, allParams.get(UrlParamType.APPID.getName()), !notInAspect);
				CustomUrlParamHolder.put(UrlParamType.REQUESTID, requestId, !notInAspect);
				// 参数校验
				Annotation[][] list = signature.getMethod().getParameterAnnotations();
				int index = checkHasEnableValid(list);
				if (index != -1) {
					customValidService.validator(args[index]);
				}
				// 业务代码
				result = pjp.proceed(args);

			} catch (Throwable e) {
				String errorCode = ErrorCodeConstants.DEFAULT_ERROR;
				String errorMsg = ERROR_MSG;
				if (e instanceof BusinessException) {
					errorCode = ((BusinessException) e).getErrorCode();
					errorMsg = ((BusinessException) e).getMessage();
				} else {
					logger.error("{}", e);
					errorMsg = e.toString();
				}
				customResponse = CustomResponse.error(errorCode, errorMsg);
				return customResponse;
			}
			// 返回参数不拦截
			if (notInAspect) {
				return result;
			}
			// http请求
			if (CustomRequestContextHolder.existsRequestAttributes()) {
				if ((result instanceof CustomResponse)) {
					customResponse = (CustomResponse<?>) result;
				} else if (result instanceof ResultMsg) {
					if (((ResultMsg) result).isResult()) {
						customResponse = CustomResponse.success(((ResultMsg) result).getOb());
					} else {
						customResponse = CustomResponse.error(((ResultMsg) result).getCode(),
								((ResultMsg) result).getMsg());
					}
				} else {
					customResponse = CustomResponse.success(result);
				}

			}
			return customResponse;
		} finally {
			if (customResponse != null) {
			//	customResponse.getHead().setThreadId(Thread.currentThread().getName());
				//customResponse.getHead().setRequestTimeStamp(beginTime + "");
				customResponse.getHead().setRequestId(StringUtils.isNotBlank(requestId) ? requestId : IDUtil.getUUID());
				customResponse.getHead().setResponseTime((System.currentTimeMillis() - beginTime) + "");
				logger.info("Request end({}): {}, URL: {}, result: {}",
						new Object[] { Long.valueOf(System.currentTimeMillis() - beginTime), methodName, url,
								Jackson.toJson(customResponse) });
			}
		}

	}

	/**
	 * 设置id
	 * 
	 * @param args
	 * @param request
	 */
	private void setCustomLoginUserId(Object[] args, HttpServletRequest request, MethodSignature signature) {
		if (request != null && StringUtils.isNotBlank(request.getHeader(USER_ID))) {
		 //   CustomLoginUserIdHolder.set(request.getHeader(USER_ID));
			Class<?>[] classList = signature.getMethod().getParameterTypes();
			int i = 0;
			for (Class<?> clazz : classList) {
				if (CustomLoginUserId.class.equals(clazz)) {
					CustomLoginUserId customLoginUserId = new CustomLoginUserId();
					customLoginUserId.setUserId(request.getHeader(USER_ID));
					args[i] = customLoginUserId;
					break;
				}
				i++;
			}
		}

	}

	/**
	 * 
	 * 参数验证开启
	 * 
	 * @param list
	 * @return int
	 */
	private int checkHasEnableValid(Annotation[][] list) {
		for (int i = 0; i < list.length; i++) {
			for (int j = 0; j < list[i].length; j++) {
				if (list[i][j] instanceof EnableValid) {
					return i;
				}
			}
		}
		return -1;
	}

}
