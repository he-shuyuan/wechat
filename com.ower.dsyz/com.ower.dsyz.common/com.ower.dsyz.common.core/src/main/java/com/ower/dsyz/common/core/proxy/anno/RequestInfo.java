package com.bda.chongqing.core.proxy.anno;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Documented
@Inherited
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface RequestInfo {

	/**
	 * url
	 * @return
	 */
	public String value();
	
	/**
	 * 方法
	 * @return
	 */
	public String method() default "post";
	
	/**
	 * 请求方式
	 * @return
	 */
	public String contentType() default "";
	
}
