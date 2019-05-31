package com.ower.dsyz.common.core.exception;

public enum SystemErrorCodeEnum implements ErrorCodeEnum{
	/**
	 * 系统繁忙
	 */
	UNKNOW_ERROE("1","系统繁忙");
	
	private String code;
	
	private String msg;
	
	SystemErrorCodeEnum(String code,String msg){
		this.code = code;
		this.msg = msg;
	}
	
	@Override
	public String getCode() {
		return code;
	}

	@Override
	public String getMsg() {
		return msg;
	}

	
}
