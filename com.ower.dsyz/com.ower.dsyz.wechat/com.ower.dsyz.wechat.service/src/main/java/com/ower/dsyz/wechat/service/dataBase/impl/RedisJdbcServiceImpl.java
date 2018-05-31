/**
 * Copyright(c) Foresee Science & Technology Ltd.
 */
package com.ower.dsyz.wechat.service.dataBase.impl;

import java.util.List;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ower.dsyz.common.core.exception.CustomRunTimeException;
import com.ower.dsyz.common.core.redis.IRedisService;
import com.ower.dsyz.common.core.util.Jackson;
import com.ower.dsyz.wechat.constant.RedisExpires;
import com.ower.dsyz.wechat.constant.WechatConstant;
import com.ower.dsyz.wechat.manual.dto.WechatAppDTO;
import com.ower.dsyz.wechat.manual.dto.WechatMessageDTO;
import com.ower.dsyz.wechat.service.dataBase.IRedisJdbcStorageService;
import com.ower.dsyz.wechat.service.dataBase.IWechatAppService;
import com.ower.dsyz.wechat.service.dataBase.IWechatMessageService;

/**
 * <pre>
 * 二级缓存实现类
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
@Service
public class RedisJdbcServiceImpl implements IRedisJdbcStorageService {

    private final static String      SECOND_STROGE_PREFIX = "wx:common:second:";
    
    private final static String APP_CONFIG = "app:";

    private final static String MESS_CONFIG = "mess:";
    @Autowired
    private IRedisService     redisService;

    @Autowired
    private IWechatAppService wechatAppService;

    @Autowired
    private IWechatMessageService wechatMessageService;
    
    private final static Logger log = LoggerFactory.getLogger(RedisJdbcServiceImpl.class);

    @Override
    public void cleanBatchStorage(String reg) {
       // redisService.deleteBatch(SECOND_STROGE_PREFIX + reg);
    }

    @Override
    public void cleanSecondStorage() {
        //redisService.deleteBatch(SECOND_STROGE_PREFIX);
    }

    @Override
    public WechatAppDTO getWechatAppByAppId(String appId) {
        if(StringUtils.isBlank(appId)){
            throw new CustomRunTimeException("查询公众号配置信息时，appId不能为空");
        }
        WechatAppDTO wechatAppDTO = new WechatAppDTO();
        wechatAppDTO.setAppId(appId);
        String json = redisService.getString(SECOND_STROGE_PREFIX + APP_CONFIG + appId);
        if (StringUtils.isEmpty(json)) {
            log.info("appId={}的配置信息不在缓存中,从数据库中获取",appId);
            List<WechatAppDTO> list = wechatAppService.selectWechatAppList(wechatAppDTO);
            if (list.size() > 1 || list.size() == 0) {
                throw new CustomRunTimeException("通过[appId=" + appId + "]只能找到一条配置信息，但现在找到了(" + list.size() + ")条配置");
            }
            json = Jackson.toJson(list.get(0));
            redisService.setString(SECOND_STROGE_PREFIX + APP_CONFIG + appId, json, RedisExpires.WEEK);
            log.info("appId={}的配置信息{}写入缓存{}",appId,list.get(0),RedisExpires.WEEK);
        }
        wechatAppDTO = Jackson.fromJson(json, WechatAppDTO.class);
        return wechatAppDTO;
    }

    @Override
    public List<WechatMessageDTO> getWechatMessageDTOByAppIdAndEventType(String appId, String eventType) {
        List<WechatMessageDTO> list = null;
        String json = redisService.getString(SECOND_STROGE_PREFIX + MESS_CONFIG + appId+"-"+eventType);
        if(StringUtils.isEmpty(json)){
            log.info("[appId={},eventType={}]的配置信息不在缓存中,从数据库中获取",appId,eventType);
            WechatMessageDTO wechatMessageDTO = new WechatMessageDTO();
            wechatMessageDTO.setAppId(appId);
            wechatMessageDTO.setEventType(eventType);
            wechatMessageDTO.setParentId(WechatConstant.Default.PARENT_ID);
            list = wechatMessageService.selectWechatMessageList(wechatMessageDTO);
            if(list.size() == 0){
                log.warn("[appId={},eventType={}]的配置信息暂未配置，请检查配置",appId,eventType);
                return list;
            }
            for(WechatMessageDTO temp:list){
                if(temp.getHasChild()>0 || WechatConstant.Message.NEWS.equals(temp.getMessType())){
                    wechatMessageDTO.setParentId(temp.getId());
                    temp.setChildrenList(wechatMessageService.selectWechatMessageList(wechatMessageDTO));
                }
            }
            json = Jackson.toJson(list);
            redisService.setString(SECOND_STROGE_PREFIX + MESS_CONFIG + appId+"-"+eventType, json, RedisExpires.WEEK);
            log.info("[appId={},eventType={}]的配置信息{}写入缓存{}",appId,eventType,list,RedisExpires.WEEK);
        }
        list = Jackson.fromJsonArray(json, WechatMessageDTO.class);
        return list;
    }

    
}
