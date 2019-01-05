package com.ower.dsyz.common.mybatis.database.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import com.ower.dsyz.common.mybatis.database.annotation.DataSource;

@DataSource
@Component
@ConfigurationProperties(prefix = DefaultDBConfig.FD, ignoreUnknownFields = false)
public class DefaultDBConfig  extends AbstractDBConfig{

    /** 前缀 */
    public final static String FD = "custom.druid.datasource";
}
