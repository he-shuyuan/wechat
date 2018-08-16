package com.ower.dsyz.admin.rest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;


/**
 * Hello world!
 *
 */
@EnableEurekaClient
//@EnableDiscoveryClient(autoRegister=false)//禁用客户端
@SpringBootApplication(scanBasePackages = { "com.ower.dsyz" })
public class AdminApplication 
{
    public static void main( String[] args )
    {
        SpringApplication application=new SpringApplication(AdminApplication.class);
        application.run(args); 
    }
}
