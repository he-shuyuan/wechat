package com.ower.dsyz.logCenter.rest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;


/**
 * Hello world!
 *
 */
@EnableEurekaClient
@EnableDiscoveryClient(autoRegister=false)//禁用客户端
@SpringBootApplication(scanBasePackages = { "com.ower.dsyz" })
public class LogCenterApplication 
{

    public static void main( String[] args )
    {
        SpringApplication application=new SpringApplication(LogCenterApplication.class);
        application.run(args); 
    }

}
