/**
 * Copyright(c) Foresee Science & Technology Ltd. 
 */
package com.ower.dsyz.wechat.rest.inner;

import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
/*import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.PageImpl;*/
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.ower.dsyz.common.core.constant.BeanNameConstant;
import com.ower.dsyz.common.core.interceptor.RedisStorageInterceptor;
import com.ower.dsyz.common.core.listener.CustomManagerListener;
import com.ower.dsyz.common.core.page.PageRequestParam;
import com.ower.dsyz.common.core.rest.CustomRestClient;
import com.ower.dsyz.common.core.rest.impl.ICustomRestClient;
import com.ower.dsyz.wechat.manual.dto.WechatAppDTO;
import com.ower.dsyz.wechat.service.dataBase.IRedisJdbcStorageService;
import com.ower.dsyz.wechat.service.dataBase.IWechatAppService;
import com.ower.dsyz.wechat.util.ContextUtil;
/**
 * <pre>
 * </pre>
 *
 * @author HeShuyuan@163.com
 * @date 2017年12月26日
 * @version 1.00.00
 * 
 *          <pre>
 * 修改记录 
 *    修改后版本:     修改人：  修改日期:     修改内容:
 *          </pre>
 */
@RestController
@RequestMapping("/inner/helloService")
public class HelloController {
    
   // private Logger logger=LoggerFactory.getLogger(HelloController.class);
  
    @Resource
    CustomManagerListener customManagerListener;
    
    @Resource
    ICustomRestClient customRestClient;
    
    @Resource
    IRedisJdbcStorageService redisJdbcStorageService;
    
    @Resource
    IWechatAppService wechatAppService;
    //@NotInAspect
    @RequestMapping(value = "/hello", method ={ RequestMethod.POST,RequestMethod.GET})
    public Object testRequest(@RequestBody PageRequestParam param) /*throws Exception*/{
        //customManagerListener.fireCustomEventListener(new CustomEvent(new CustomEventSourceExcend(), "lalala"));
        //return wechatSendMessageService.sendMultiDomainMessage(WxMpKefuMessage.TEXT().toUser("oce-9wK5E0tIbGNTJyXCTdtFOJb8").content("mdzz").build(), "wxf4165bbc217ec388");
      //  List<WechatAppDTO> list = wechatAppService.selectWechatAppList(new WechatAppDTO());
        return customRestClient.postInner("http://gateway/blockChain/inner/blockChainService/createBlock", "{}", Map.class);
       // return list.get(0).getAppId();
       // return pageInfo;
    }
    
    @RequestMapping(value = "/dell", method ={ RequestMethod.POST,RequestMethod.GET})
    public Object dell(@RequestBody PageRequestParam param) /*throws Exception*/{
        //customManagerListener.fireCustomEventListener(new CustomEvent(new CustomEventSourceExcend(), "lalala"));
        //return wechatSendMessageService.sendMultiDomainMessage(WxMpKefuMessage.TEXT().toUser("oce-9wK5E0tIbGNTJyXCTdtFOJb8").content("mdzz").build(), "wxf4165bbc217ec388");
//        RedisStorageInterceptor RedisStorageInterceptor=
//        (RedisStorageInterceptor)ContextUtil.getBean(BeanNameConstant.RedisStorageInterceptor);
//        RedisStorageInterceptor.cleanCache();

        return "success";
       // return pageInfo;
    }
    
}
