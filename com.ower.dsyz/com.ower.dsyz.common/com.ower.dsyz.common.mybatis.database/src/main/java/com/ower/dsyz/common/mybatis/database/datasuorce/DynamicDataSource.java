package com.ower.dsyz.common.mybatis.database.datasuorce;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;
import com.ower.dsyz.common.core.holder.DataSourceRouteHolder;


public class DynamicDataSource extends AbstractRoutingDataSource {
    
	
	@Override
	protected Object determineCurrentLookupKey() {
		Object key = DataSourceRouteHolder.getDataSourceKey();
		DataSourceRouteHolder.clearDataSourceKey();
		return key;
	}
  
    }