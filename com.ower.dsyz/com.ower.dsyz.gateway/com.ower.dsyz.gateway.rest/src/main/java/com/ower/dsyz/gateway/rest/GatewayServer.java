package com.ower.dsyz.gateway.rest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * Hello world!
 *
 */
@EnableDiscoveryClient(autoRegister=false)//禁用客户端
@EnableEurekaClient
@SpringBootApplication(scanBasePackages = { "com.ower.dsyz.*.*" })
public class GatewayServer 
{
    public static void main( String[] args )
    {
        SpringApplication application=new SpringApplication(GatewayServer.class);
        application.run(args);
    }
}
