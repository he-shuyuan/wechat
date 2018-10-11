package com.ower.dsyz.admin.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

import com.ower.dsyz.logCenter.client.LogCenterClient;



/**
 * Hello world!
 *
 */
@EnableEurekaClient
@EnableDiscoveryClient(autoRegister=false)//禁用客户端
@SpringBootApplication(scanBasePackages = { "com.ower.dsyz" })
public class AdminApplication implements CommandLineRunner
{
    @Autowired
    LogCenterClient logCenterClient;
    
    public static void main( String[] args ) 
    {
        SpringApplication application=new SpringApplication(AdminApplication.class);
        application.run(args); 
    }

	@Override
	public void run(String... arg0) throws Exception {
		logCenterClient.connect(1L);
		
	}

}
