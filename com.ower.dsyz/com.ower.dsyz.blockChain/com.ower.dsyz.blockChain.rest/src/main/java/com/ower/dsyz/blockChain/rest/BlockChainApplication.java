package com.ower.dsyz.blockChain.rest;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 
 * <pre>
 * 微信管理总入口
 * </pre>
 *
 * @author HeShuyuan@163.com
 * @date 2018年1月19日
 * @version 1.00.00
 * 
 *          <pre>
 * 修改记录 
 *    修改后版本:     修改人：  修改日期:     修改内容:
 *          </pre>
 */
//@EnableEurekaClient
@SpringBootApplication(scanBasePackages = { "com.ower.dsyz.*.*" })
public class BlockChainApplication  implements CommandLineRunner
{
  //  @Resource
  //  CustomManagerListener customManagerListener;
    
    public static void main( String[] args )
    {
        SpringApplication application=new SpringApplication(BlockChainApplication.class);
        application.run(args);
    }

    @Override
    public void run(String... args) throws Exception {
        //customManagerListener.addCustomEventListener(CustomEventSourceExcend.class, wechatLister);
    }
}
