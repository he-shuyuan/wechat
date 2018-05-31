package com.ower.hsy.gateway.rest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * Hello world!
 *
 */
@EnableEurekaClient
@SpringBootApplication(scanBasePackages = { "com.ower.hsy.*.*" })
public class GatewayService 
{
    public static void main( String[] args )
    {
        SpringApplication application=new SpringApplication(GatewayService.class);
        application.run(args);
    }
}
