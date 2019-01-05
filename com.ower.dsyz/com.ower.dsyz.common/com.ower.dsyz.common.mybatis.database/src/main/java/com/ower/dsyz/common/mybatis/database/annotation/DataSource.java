package com.ower.dsyz.common.mybatis.database.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import com.ower.dsyz.common.core.holder.DataSourceRouteHolder;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface DataSource {

	 String name() default DataSourceRouteHolder.DEFAULT_DATA_SOURCE;
}
