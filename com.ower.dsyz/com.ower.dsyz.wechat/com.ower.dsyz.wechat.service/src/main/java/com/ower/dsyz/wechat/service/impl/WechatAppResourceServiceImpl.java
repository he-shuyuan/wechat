/**
 * Copyright(c) Foresee Science & Technology Ltd. 
 */
package com.ower.dsyz.wechat.service.impl;

import java.util.List;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ower.dsyz.common.core.annotation.SyncRedisLock;
import com.ower.dsyz.common.core.exception.CustomRunTimeException;
import com.ower.dsyz.common.core.page.PageQueryResult;
import com.ower.dsyz.common.core.page.PageRequestParam;
import com.ower.dsyz.common.core.util.IDUtil;
import com.ower.dsyz.wechat.auto.model.WechatApp;
import com.ower.dsyz.wechat.enums.EnumOPerate;
import com.ower.dsyz.wechat.manual.dto.WechatAppDTO;
import com.ower.dsyz.wechat.service.IWechatAppResourceService;
import com.ower.dsyz.wechat.service.dataBase.IWechatAppService;


/**
 * <pre>
 * 微信公众号管理配置
 * </pre>
 *
 * @author HeShuyuan@foresee.com.cn
 * @date 2018年1月15日
 * @version 1.00.00
 * 
 *          <pre>
 * 修改记录 
 *    修改后版本:     修改人：  修改日期:     修改内容:
 *          </pre>
 */
@Service
public class WechatAppResourceServiceImpl implements IWechatAppResourceService {

    @Autowired
    private IWechatAppService wechatAppServiceImpl;
    
    @Override
    @SyncRedisLock
    public EnumOPerate createOrUpdateWechatApp(WechatApp wechatApp) {
        if(StringUtils.isBlank(wechatApp.getId())){
          return  this.createWechatApp(wechatApp);
        }else{
          return this.updateWechatApp(wechatApp);
        }
    }



    @Override
    public int deleteWechatAppById(String id) {

        return wechatAppServiceImpl.deleteByPrimaryKey(id);
    }

    @Override
    public PageQueryResult<WechatAppDTO> pageQueryWechatAppList(PageRequestParam param) {

        return wechatAppServiceImpl.pageQueryWechatAppList(param);
    }

    /**
     * 
     * 创建wechatapp
     * @param wechatApp
     * @return int
     */
    private EnumOPerate createWechatApp(WechatApp wechatApp) {
        if(this.isExistWechatApp(wechatApp)){
            throw new CustomRunTimeException("微信配置参数已存在，请认真检查参数");
        }
         wechatApp.setId(IDUtil.getUUID());
         wechatAppServiceImpl.insertSelective(wechatApp);
         return EnumOPerate.INSERT;
    }
    
    /**
     * 
     * 修改wechatapp
     * @param wechatApp
     * @return int
     */
    private EnumOPerate updateWechatApp(WechatApp wechatApp) {
        if(this.isExistWechatApp(wechatApp)){
            throw new CustomRunTimeException("微信配置参数已存在，请认真检查参数");
        }
        int num = wechatAppServiceImpl.updateByPrimaryKeySelective(wechatApp);
        if(num == 0){
            throw new CustomRunTimeException("不存在当前id=["+wechatApp.getId()+"],修改失败");
        }
        return EnumOPerate.UPDATE;
    }
    
    /**
     * 判断配置是否重复
     * @param wechatApp
     * @return boolean
     */
    private boolean isExistWechatApp(WechatApp wechatApp){
        List<WechatApp> list = wechatAppServiceImpl.selectIsExitWechatAppList(wechatApp);
        if(list.isEmpty()){
            return false;
        }
        if(list.size() == 1 && list.get(0).getId().equals(wechatApp.getId())){
            return false;
        }
        return true;
    }



    @Override
    public List<WechatAppDTO> selectWechatAppList(WechatAppDTO wechatAppDTO) {
        
        return wechatAppServiceImpl.selectWechatAppList(wechatAppDTO);
    }
}
