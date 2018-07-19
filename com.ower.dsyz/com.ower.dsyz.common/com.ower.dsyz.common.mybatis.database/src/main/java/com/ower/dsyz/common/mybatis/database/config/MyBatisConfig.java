/**
 * Copyright(c) Foresee Science & Technology Ltd. 
 */
package com.ower.dsyz.common.mybatis.database.config;

import java.sql.SQLException;
import java.util.Properties;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import com.alibaba.druid.pool.DruidDataSource;
import com.github.pagehelper.PageHelper;
import com.ower.dsyz.common.mybatis.database.page.CustomSqlSessionTemplate;

/**
 * 
 * <pre>
 * MyBatis基础配置
 * </pre>
 *
 * @author HeShuyuan@163.com
 * @date 2017年12月28日
 * @version 1.00.00
 * 
 *          <pre>
 * 修改记录 
 *    修改后版本:     修改人：  修改日期:     修改内容:
 *          </pre>
 */
@Configuration
@EnableTransactionManagement
@EnableConfigurationProperties({DruidDBConfig.class})
@MapperScan(basePackages={"com.ower.dsyz.*.*.dao"}, sqlSessionTemplateRef="customSqlSessionTemplate")
public class MyBatisConfig{
    
    private Logger log = LoggerFactory.getLogger(this.getClass());
    
    @Autowired
    private DruidDBConfig druidDBConfig;
    

    @Bean(name="customDataDource",destroyMethod = "close")   
    public DruidDataSource dataSource() {
        DruidDataSource datasource = new DruidDataSource();

        datasource.setUrl(druidDBConfig.getUrl());
        datasource.setUsername(druidDBConfig.getUsername());
        datasource.setPassword(druidDBConfig.getPassword());
        datasource.setDriverClassName(druidDBConfig.getDriverClassName());

        //configuration
        datasource.setInitialSize(druidDBConfig.getInitialSize());
        datasource.setMinIdle(druidDBConfig.getMinIdle());
        datasource.setMaxActive(druidDBConfig.getMaxActive());
        datasource.setMaxWait(druidDBConfig.getMaxWait());
        datasource.setTimeBetweenEvictionRunsMillis(druidDBConfig.getTimeBetweenEvictionRunsMillis());
        datasource.setMinEvictableIdleTimeMillis(druidDBConfig.getMinEvictableIdleTimeMillis());
        datasource.setValidationQuery(druidDBConfig.getValidationQuery());
        datasource.setTestWhileIdle(druidDBConfig.isTestWhileIdle());
        datasource.setTestOnBorrow(druidDBConfig.isTestOnBorrow());
        datasource.setTestOnReturn(druidDBConfig.isTestOnReturn());
        datasource.setPoolPreparedStatements(druidDBConfig.isPoolPreparedStatements());
        datasource.setMaxPoolPreparedStatementPerConnectionSize(druidDBConfig.getMaxPoolPreparedStatementPerConnectionSize());
        try {
            datasource.setFilters(druidDBConfig.getFilters());
        } catch (SQLException e) {
            log.error("druid configuration initialization filter", e);
        }
        datasource.setConnectionProperties(druidDBConfig.getConnectionProperties());
        datasource.setUseGlobalDataSourceStat(druidDBConfig.isUseGlobalDataSourceStat());
        return datasource;
    }
    
    @Bean(name = "sqlSessionFactory")
    public SqlSessionFactory sqlSessionFactoryBean(@Autowired @Qualifier(value="customDataDource")
            DruidDataSource dataSource) {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(dataSource);
        bean.setTypeAliasesPackage("com.ower.dsyz.*.*");

        //PageHelper pageHelper = createPageHelper();
        PageHelper pageHelper = new PageHelper();
        Properties properties = new Properties();
        properties.setProperty("reasonable", "true");
        properties.setProperty("supportMethodsArguments", "true");
        properties.setProperty("returnPageInfo", "check");
        properties.setProperty("params", "count=countSql");
        pageHelper.setProperties(properties);

        //添加插件
        bean.setPlugins(new Interceptor[]{(Interceptor) pageHelper});

        //添加XML目录
        ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        try {
            bean.setMapperLocations(resolver.getResources("classpath*:com/ower/dsyz/**/mapper/*.xml"));
            return bean.getObject();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }


    @Bean(name = "customSqlSessionTemplate")
    public CustomSqlSessionTemplate sqlSessionTemplate(SqlSessionFactory sqlSessionFactory) {
        return new CustomSqlSessionTemplate(sqlSessionFactory);
    }
    
    @Bean
    public PlatformTransactionManager TransactionManager(@Autowired @Qualifier(value="customDataDource")
            DruidDataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }
    
    
}
