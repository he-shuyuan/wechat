/**
 * Copyright(c) Foresee Science & Technology Ltd. 
 */
package com.ower.dsyz.wechat.service.dataBase.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ower.dsyz.common.core.page.PageQueryResult;
import com.ower.dsyz.common.core.page.PageRequestParam;
import com.ower.dsyz.wechat.auto.dao.WechatMessageMapper;
import com.ower.dsyz.wechat.auto.model.WechatMessage;
import com.ower.dsyz.wechat.manual.dao.WechatMessageExtMapper;
import com.ower.dsyz.wechat.manual.dto.WechatMessageDTO;
import com.ower.dsyz.wechat.service.dataBase.IWechatMessageService;

/**
 * <pre>
 * TODO。
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
@Service
public class WechatMessageServiceImpl implements IWechatMessageService {

    @Autowired
    private WechatMessageMapper wechatMessageMapper;
    
    @Autowired
    private WechatMessageExtMapper wechatMessageExtMapper;
    
    @Override
    public int deleteByPrimaryKey(String id) {
        return wechatMessageMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insertSelective(WechatMessage record) {
        
        return wechatMessageMapper.insertSelective(record);
    }

    @Override
    public WechatMessage selectByPrimaryKey(String id) {
        
        return wechatMessageMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(WechatMessage record) {
        
        return wechatMessageMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public PageQueryResult<WechatMessageDTO> pageQueryWechatMessageList(PageRequestParam param) {
        
        return wechatMessageExtMapper.pageQueryWechatMessageList(param);
    }

    @Override
    public List<WechatMessageDTO> selectWechatMessageList(WechatMessageDTO wechatMessageDTO) {
        
        return wechatMessageExtMapper.selectWechatMessageList(wechatMessageDTO);
    }

}
