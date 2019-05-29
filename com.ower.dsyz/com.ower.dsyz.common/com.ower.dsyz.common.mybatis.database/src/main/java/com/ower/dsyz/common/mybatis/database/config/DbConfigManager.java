package com.ower.dsyz.common.mybatis.database.config;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.ower.dsyz.common.base.util.SpringContextUtil;
import com.ower.dsyz.common.mybatis.database.annotation.DataSource;
/**
 * 配置管理工具
 * @author Administrator
 *
 */
@Component
public class DbConfigManager implements InitializingBean {

	@Autowired
	private SpringContextUtil context;
	
	private  Map<String,AbstractDBConfig> configMap = new HashMap<>();
	
	@Override
	public void afterPropertiesSet() throws Exception {
				Map<String, Object> map =  context.getContext().getBeansWithAnnotation(DataSource.class);
				for(Object dbc:map.values()){	
					configMap.put(dbc.getClass().getAnnotation(DataSource.class).name(),(AbstractDBConfig) dbc);
				}
				System.err.println("数据源列表"+configMap);
		
	}

	public Map<String, AbstractDBConfig> getConfigMap() {
		return configMap;
	}

}
