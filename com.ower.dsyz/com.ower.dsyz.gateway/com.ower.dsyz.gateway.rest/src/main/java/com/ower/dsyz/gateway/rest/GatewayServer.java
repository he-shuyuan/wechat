package com.ower.dsyz.gateway.rest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Hello world!
 *
 */
//@EnableDiscoveryClient(autoRegister=false)//禁用客户端
//@EnableEurekaClient
@SpringBootApplication(scanBasePackages = { "com.ower.dsyz.*.*" })
public class GatewayServer 
{
    public static void main( String[] args )
    {
        SpringApplication application=new SpringApplication(GatewayServer.class);
        application.run(args);
    }
}
