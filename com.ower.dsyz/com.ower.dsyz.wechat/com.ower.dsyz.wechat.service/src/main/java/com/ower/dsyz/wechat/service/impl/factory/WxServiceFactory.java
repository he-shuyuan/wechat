package com.ower.dsyz.wechat.service.impl.factory;

import java.util.HashMap;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.ower.dsyz.wechat.manual.dto.WechatAppDTO;
import com.ower.dsyz.wechat.service.dataBase.IRedisJdbcStorageService;
import com.ower.dsyz.wechat.service.impl.storage.WxMpRedisStorage;
import com.ower.dsyz.wechat.util.ContextUtil;
import me.chanjar.weixin.mp.api.impl.WxMpServiceImpl;

/**
 * 
 * <pre>
 * 微信服务工厂
 * </pre>
 *
 * @author HeShuyuan@foresee.com.cn
 * @date 2018年1月11日
 * @version 1.00.00
 * 
 *          <pre>
 * 修改记录 
 *    修改后版本:     修改人：  修改日期:     修改内容:
 *          </pre>
 */
@Component(WxServiceFactory.BEAN_ID)
public class WxServiceFactory {

    public static final String       BEAN_ID = "WxServiceFactory";

    private final static Logger      log     = LoggerFactory.getLogger(WxServiceFactory.class);

    @Autowired
    private IRedisJdbcStorageService redisJdbcStorageService;


    /**
     * 获取微信服务实例
     * 
     * @param appId 应用id，根据应用id获取微信配置信息，进而生成微信服务接口实例
     * @return
     */
    public synchronized Object getWxService(String appId) {
        String beanId = "WxService-" + appId;
        Object wxService = ContextUtil.getBean(beanId);
        if (wxService == null) {
            log.debug("正在创建应用" + appId + "的微信服务实例");
            Map<String, Object> environmentMap = new HashMap<String, Object>();
            environmentMap.put("wxMpConfigStorage", this.getWxMpConfigStorage(redisJdbcStorageService.getWechatAppByAppId(appId)));
            wxService = ContextUtil.registerBean(beanId, WxMpServiceImpl.class, environmentMap, null);

            log.debug("创建成功！并已注入到spring容器");
        }
        return wxService;
    }

    /**
     * 获取订阅号、服务号配置存储器
     * 
     * @return
     */
    private WxMpRedisStorage getWxMpConfigStorage(WechatAppDTO wxInfo) {
        String beanId = "WxMpConfigStorage-" + wxInfo.getAppId();
        Object configStorage = ContextUtil.getBean(beanId);
        if (configStorage == null) {
            // 创建并注册到spring中
            Map<String, Object> environmentMap = new HashMap<String, Object>();
            environmentMap.put("appId", wxInfo.getAppId());
            environmentMap.put("secret", wxInfo.getSecret());
            environmentMap.put("token", wxInfo.getToken());
            configStorage = ContextUtil.registerBean(beanId, WxMpRedisStorage.class, environmentMap, null);
        }
        return (WxMpRedisStorage) configStorage;
    }
}
