package com.ower.dsyz.common.core.interceptor;

import java.lang.annotation.Annotation;
import java.util.Enumeration;
import java.util.LinkedHashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.lang.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import com.ower.dsyz.common.core.annotation.EnableValid;
import com.ower.dsyz.common.core.annotation.NotInAspect;
import com.ower.dsyz.common.core.model.ResultMsg;
import com.ower.dsyz.common.core.request.CustomRequestContextHolder;
import com.ower.dsyz.common.core.response.CustomResponse;
import com.ower.dsyz.common.core.util.Jackson;
import com.ower.dsyz.common.core.valid.service.ICustomValidService;

/**
 * 
 * <pre>
 * controller层切面控制
 * </pre>
 *
 * @author HeShuyuan@foresee.com.cn
 * @date 2018年1月2日
 * @version 1.00.00
 * 
 *          <pre>
 * 修改记录 
 *    修改后版本:     修改人：  修改日期:     修改内容:
 *          </pre>
 */
@Aspect
@Component
public class CustomRequestInterceptor {

    private static final Logger logger     = LoggerFactory.getLogger(CustomRequestInterceptor.class);

    private final String        ERROR_CODE = "1";

    private final String        ERROR_MSG  = "系统出现未知异常";

    @Autowired
    private ICustomValidService customValidService;

    
    @Pointcut("execution(* com.ower.hsy..rest..*.*(..)) && @annotation(org.springframework.web.bind.annotation.RequestMapping)")
    public void controllerAspect() {}

    @Around("controllerAspect()")
    public Object interceptor(ProceedingJoinPoint pjp) throws Throwable {
        HttpServletRequest request = null;
        HttpServletResponse response = null;
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
        Map<String, Object> allParams = new LinkedHashMap<>();
        Enumeration<String> paramNames = request.getParameterNames();

        while (paramNames.hasMoreElements()) {
            String name = paramNames.nextElement();
            allParams.put(name, request.getParameter(name));
        }
        Object result = null;
        Object[] args = pjp.getArgs();
        logger.debug("Request begin: {}, URL: {}, urlParams: {}, jsonParams: {}", methodName, url, allParams, args);
        Annotation[][] list = signature.getMethod().getParameterAnnotations();
        int index = checkHasEnableValid(list);
        if(index != -1){
            ResultMsg resultMsg = customValidService.validator(args[index]);
            if(!resultMsg.isResult()){
                CustomResponse<?> customResponse = CustomResponse.error(resultMsg.getCode(), resultMsg.getMsg());;
                customResponse.getHead().setThreadId(Thread.currentThread().getName());
                customResponse.getHead().setResponseTime((System.currentTimeMillis() - beginTime) + "");
                logger.debug("Request end({}): {}, URL: {}, result: {}", new Object[] { Long.valueOf(System.currentTimeMillis() - beginTime),
                        methodName, url, Jackson.toJson(customResponse) });
                return  customResponse;
            }
        }
        
        CustomResponse<?> customResponse = null;
        try {
            try {
                result = pjp.proceed(args);
            } catch (Throwable e) {
                String errorCode = ERROR_CODE;
                Object errorMsg = ERROR_MSG;
                if (StringUtils.isNotEmpty(e.getMessage()) && e.getMessage().indexOf(":") > 0) {
                    String[] strList = e.getMessage().split(":");
                    if (strList.length >= 2 && StringUtils.isNotEmpty(strList[0])) {
                        errorCode = strList[0];
                    }
                    if (strList.length >= 2 && StringUtils.isNotEmpty(strList[1])) {
                        errorMsg = strList[1];
                    }
                } else {
                    logger.error("{}", e);
                    errorMsg = e.toString();
                }
                customResponse = CustomResponse.error(errorCode, errorMsg);
                return customResponse;
            }
            if (((MethodSignature) pjp.getSignature()).getMethod().isAnnotationPresent(NotInAspect.class)) {
                return result;
            }
            if (CustomRequestContextHolder.existsRequestAttributes()) {
                if ((result instanceof CustomResponse)) {
                    customResponse = (CustomResponse<?>) result;
                } else if (result instanceof ResultMsg) {
                    if (((ResultMsg) result).isResult()) {
                        customResponse = CustomResponse.success(((ResultMsg) result).getOb());
                    } else {
                        customResponse = CustomResponse.error(((ResultMsg) result).getCode(), ((ResultMsg) result).getMsg());
                    }
                } else {
                    customResponse = CustomResponse.success(result);
                }

            }
            return customResponse;
        } finally {
            customResponse.getHead().setThreadId(Thread.currentThread().getName());
            customResponse.getHead().setResponseTime((System.currentTimeMillis() - beginTime) + "");
            logger.debug("Request end({}): {}, URL: {}, result: {}", new Object[] { Long.valueOf(System.currentTimeMillis() - beginTime),
                    methodName, url, Jackson.toJson(customResponse) });
        }

    }

    /**
     * 
     * 参数验证开启
     * @param list
     * @return int
     */
    private int checkHasEnableValid(Annotation[][] list) {
       for(int i=0;i<list.length;i++){
           for(int j=0;j<list[i].length;j++){
               if(list[i][j] instanceof EnableValid){
                   return i;
               }
           }
       }
       return -1;
    }

}
