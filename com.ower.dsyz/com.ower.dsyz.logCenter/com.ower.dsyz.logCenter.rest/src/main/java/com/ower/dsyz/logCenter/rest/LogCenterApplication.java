package com.ower.dsyz.logCenter.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
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
public class LogCenterApplication  implements CommandLineRunner
{

	@Autowired
	com.ower.dsyz.logCenter.server.LogCenterNettyServer LogCenterNettyServer;
	
    public static void main( String[] args )
    {
        SpringApplication application=new SpringApplication(LogCenterApplication.class);
        application.run(args); 
    }

	@Override
	public void run(String... arg0) throws Exception {
		LogCenterNettyServer.bind(9998);
		
	}

}
