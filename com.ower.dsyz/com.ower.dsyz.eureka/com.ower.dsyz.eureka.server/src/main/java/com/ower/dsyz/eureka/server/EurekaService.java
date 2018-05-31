package com.ower.dsyz.eureka.server;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * Hello world!
 *
 */
@EnableEurekaServer
@SpringBootApplication
public class EurekaService 
{
    public static void main( String[] args )
    {
        new SpringApplicationBuilder(EurekaService.class).web(true).run(args);
    }
}
