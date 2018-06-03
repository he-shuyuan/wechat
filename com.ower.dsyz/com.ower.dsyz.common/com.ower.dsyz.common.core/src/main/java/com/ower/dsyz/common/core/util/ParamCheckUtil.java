package com.ower.dsyz.common.core.util;

import org.apache.commons.lang3.StringUtils;

import com.ower.dsyz.common.core.constant.ErrorCodeConstants;
import com.ower.dsyz.common.core.exception.CustomRunTimeException;

public abstract class ParamCheckUtil {

	private static final String DEFAULT_MESSAGE = "参数异常";

	public static void checkEmpty(String param, String callMess) {
		if (StringUtils.isBlank(param)) {
			throw new CustomRunTimeException(ErrorCodeConstants.PARAM_ERROR, callMess);
		}
	}

	public static void checkEmpty(String param) {
		checkEmpty(param, DEFAULT_MESSAGE);
	}
}
