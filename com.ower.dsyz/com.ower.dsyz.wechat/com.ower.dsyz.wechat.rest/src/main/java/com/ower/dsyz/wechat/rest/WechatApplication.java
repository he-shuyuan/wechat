package com.ower.dsyz.wechat.rest;

import javax.annotation.Resource;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Import;
import com.ower.dsyz.common.core.listener.CustomManagerListener;
import com.ower.dsyz.wechat.util.ContextUtil;
/**
 * 
 * <pre>
 * 微信管理总入口
 * </pre>
 *
 * @author HeShuyuan@foresee.com.cn
 * @date 2018年1月19日
 * @version 1.00.00
 * 
 *          <pre>
 * 修改记录 
 *    修改后版本:     修改人：  修改日期:     修改内容:
 *          </pre>
 */
//@EnableEurekaClient
//@EableRedisListener(channels="/dict/notify")
@SpringBootApplication(scanBasePackages = { "com.ower.dsyz.*.*" })
@Import(value={ContextUtil.class})
public class WechatApplication  implements CommandLineRunner
{
    @Resource
    CustomManagerListener customManagerListener;

    public static void main( String[] args )
    {
        SpringApplication application=new SpringApplication(WechatApplication.class);
        application.run(args);
      //  org.springframework.boot.autoconfigure.context.PropertyPlaceholderAutoConfiguration
    }

    @Override
    public void run(String... args) throws Exception {
        //customManagerListener.addCustomEventListener(CustomEventSourceExcend.class, wechatLister);
    }
}
