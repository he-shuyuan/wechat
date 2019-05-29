package com.ower.dsyz.common.mybatis.database.holder;

import com.github.pagehelper.SqlUtil;

/**
 * 数据源路由操作杆
 * @author Administrator
 *
 */
public class DataSourceRouteHolder {
	/**
	 * 默认数据源
	 */
	public static final String DEFAULT_DATA_SOURCE = "default";
	
	private static final ThreadLocal<String> DATA_SOURCE_HOLDER = new ThreadLocal<>();
    
	public static void setDataSourceKey(String customType) {
		DATA_SOURCE_HOLDER.set(customType);
	}

	public static String getDataSourceKey() {
		 if(DATA_SOURCE_HOLDER.get()==null){
			  return DEFAULT_DATA_SOURCE;
		 }
	      return DATA_SOURCE_HOLDER.get();
	}

	public static void clearDataSourceKey() {
		if(SqlUtil.getLocalPage() == null){
		   DATA_SOURCE_HOLDER.remove();
		}
	}
}