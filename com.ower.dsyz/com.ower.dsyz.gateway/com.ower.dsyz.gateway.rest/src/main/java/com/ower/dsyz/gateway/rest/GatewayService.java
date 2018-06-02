package com.ower.dsyz.gateway.rest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * Hello world!
 *
 */
@EnableEurekaClient
@SpringBootApplication(scanBasePackages = { "com.ower.dsyz.*.*" })
public class GatewayService 
{
    public static void main( String[] args )
    {
        SpringApplication application=new SpringApplication(GatewayService.class);
        application.run(args);
    }
}
