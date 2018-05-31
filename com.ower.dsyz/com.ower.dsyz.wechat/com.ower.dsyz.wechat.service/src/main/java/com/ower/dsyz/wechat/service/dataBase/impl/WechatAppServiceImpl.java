/**
 * Copyright(c) Foresee Science & Technology Ltd.
 */
package com.ower.dsyz.wechat.service.dataBase.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ower.dsyz.common.core.annotation.RedisStorage;
import com.ower.dsyz.common.core.page.PageQueryResult;
import com.ower.dsyz.common.core.page.PageRequestParam;
import com.ower.dsyz.wechat.auto.dao.WechatAppMapper;
import com.ower.dsyz.wechat.auto.model.WechatApp;
import com.ower.dsyz.wechat.manual.dao.WechatAppExtMapper;
import com.ower.dsyz.wechat.manual.dto.WechatAppDTO;
import com.ower.dsyz.wechat.service.dataBase.IWechatAppService;

/**
 * <pre>
 * 微信公众号配置实现
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
public class WechatAppServiceImpl implements IWechatAppService {

    @Autowired
    private WechatAppMapper    wechatAppMapper;

    @Autowired
    private WechatAppExtMapper wechatAppExtMapper;


    @Override
    public int deleteByPrimaryKey(String id) {
        return wechatAppMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insertSelective(WechatApp record) {

        return wechatAppMapper.insert(record);
    }

    @Override
    public WechatApp selectByPrimaryKey(String id) {

        return wechatAppMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(WechatApp record) {

        return wechatAppMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public PageQueryResult<WechatAppDTO> pageQueryWechatAppList(PageRequestParam param) {

        return wechatAppExtMapper.pageQueryWechatAppList(param);
    }

    @Override
    @RedisStorage
    public List<WechatAppDTO> selectWechatAppList(WechatAppDTO wechatAppDTO) {

        return wechatAppExtMapper.selectWechatAppList(wechatAppDTO);
    }

    @Override
    public List<WechatApp> selectIsExitWechatAppList(WechatApp wechatApp) {
        
        return wechatAppExtMapper.selectIsExitWechatAppList(wechatApp);
    }

}
