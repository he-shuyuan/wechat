package com.ower.dsyz.common.core.valid.util;

import org.apache.commons.lang3.StringUtils;
import com.ower.dsyz.common.core.constant.ErrorCodeConstants;
import com.ower.dsyz.common.core.exception.BusinessException;
public abstract class ParamCheckUtil {

	private static final String DEFAULT_MESSAGE = "参数空异常";

	//字符串判空
	public static void checkEmpty(String param, String callMess) {
		if (StringUtils.isBlank(param)) {
			throw new BusinessException(ErrorCodeConstants.PARAM_ERROR, callMess);
		}
	}
    
	//字符串判空
	public static void checkEmpty(String param) {
		checkEmpty(param, DEFAULT_MESSAGE);
	}
	
	/**
	 * 是手机号，true
	 * 不是手机号，false
	 * @param param
	 * @return Boolean
	 */
	public static Boolean checkPhone(String param){
	    if(StringUtils.isNumeric(param)){//数字
	        if(param.length()==11){//长度
	            if(param.startsWith("1")){//开头
	                return true;
	            }
	        }
	    }
	    return false; 
	}
}
