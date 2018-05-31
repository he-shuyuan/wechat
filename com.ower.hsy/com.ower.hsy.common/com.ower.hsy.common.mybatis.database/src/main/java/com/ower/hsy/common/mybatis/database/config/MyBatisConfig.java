/**
 * Copyright(c) Foresee Science & Technology Ltd. 
 */
package com.ower.hsy.common.mybatis.database.config;

import java.util.Properties;
import javax.annotation.Resource;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import com.alibaba.druid.pool.DruidDataSource;
import com.github.pagehelper.PageHelper;
import com.ower.hsy.common.mybatis.database.page.CustomSqlSessionTemplate;

/**
 * 
 * <pre>
 * MyBatis基础配置
 * </pre>
 *
 * @author HeShuyuan@foresee.com.cn
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
@MapperScan(basePackages={"com.ower.hsy.*.*.dao"}, sqlSessionTemplateRef="customSqlSessionTemplate")
public class MyBatisConfig{
    
    @Resource
    private DruidDataSource dataSource;

    @Bean(name = "sqlSessionFactory")
    public SqlSessionFactory sqlSessionFactoryBean(@Autowired
            DruidDataSource dataSource) {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(dataSource);
        bean.setTypeAliasesPackage("com.ower.hsy.*.*");

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
            bean.setMapperLocations(resolver.getResources("classpath*:com/ower/hsy/**/mapper/*.xml"));
            return bean.getObject();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

   /* @Bean(name = "sqlSessionTemplate")
    public SqlSessionTemplate sqlSessionTemplate(SqlSessionFactory sqlSessionFactory) {
       // SqlSessionTemplate sql = new SqlSessionTemplate(sqlSessionFactory);
        return new SqlSessionTemplate(sqlSessionFactory);
    }*/

    @Bean(name = "customSqlSessionTemplate")
    public CustomSqlSessionTemplate sqlSessionTemplate(SqlSessionFactory sqlSessionFactory) {
       // SqlSessionTemplate sql = new SqlSessionTemplate(sqlSessionFactory);
        return new CustomSqlSessionTemplate(sqlSessionFactory);
    }
    
    @Bean
    public PlatformTransactionManager TransactionManager(@Autowired
            DruidDataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }
/*    
    @Bean(name = "pageHelper")
    public PageHelper createPageHelper(){
        //分页插件
        PageHelper pageHelper = new PageHelper();
        Properties properties = new Properties();
        properties.setProperty("reasonable", "true");
        properties.setProperty("supportMethodsArguments", "true");
        properties.setProperty("returnPageInfo", "check");
        properties.setProperty("params", "count=countSql");
        pageHelper.setProperties(properties);
        return pageHelper;
    }*/
}
