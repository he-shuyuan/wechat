/**
 * Copyright(c) Foresee Science & Technology Ltd. 
 */
package com.ower.dsyz.wechat.service.impl;

import java.util.List;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ower.dsyz.common.core.exception.CustomRunTimeException;
import com.ower.dsyz.common.core.page.PageQueryResult;
import com.ower.dsyz.common.core.page.PageRequestParam;
import com.ower.dsyz.common.core.util.IDUtil;
import com.ower.dsyz.wechat.auto.model.WechatMessage;
import com.ower.dsyz.wechat.constant.WechatConstant;
import com.ower.dsyz.wechat.enums.EnumOPerate;
import com.ower.dsyz.wechat.manual.dto.WechatMessageDTO;
import com.ower.dsyz.wechat.service.IWechatMessageResourceService;
import com.ower.dsyz.wechat.service.dataBase.IWechatMessageService;

/**
 * <pre>
 * TODO。
 * </pre>
 *
 * @author HeShuyuan@163.com
 * @date 2018年1月22日
 * @version 1.00.00
 * 
 *          <pre>
 * 修改记录 
 *    修改后版本:     修改人：  修改日期:     修改内容:
 *          </pre>
 */
@Service
public class WechatMessageResourceImpl implements IWechatMessageResourceService {

    @Autowired
    private IWechatMessageService wechatMessageService;
    
    @Override
    public EnumOPerate addOrUpdateMessage(WechatMessage wechatMessage) {
       if(StringUtils.isBlank(wechatMessage.getId())){
           return this.addMessage(wechatMessage);
       }else{
           return this.updateMessage(wechatMessage);
       }
    }

    @Override
    public PageQueryResult<WechatMessageDTO> pageQueryMessage(PageRequestParam param) {

        return wechatMessageService.pageQueryWechatMessageList(param);
    }

    @Override
    public List<WechatMessageDTO> selectMessageList(WechatMessageDTO wechatMessageDTO) {

        return wechatMessageService.selectWechatMessageList(wechatMessageDTO);
    }

    /**
     * 
     * 新增消息
     * @param wechatMessage
     * @return EnumOPerate
     */
    private EnumOPerate addMessage(WechatMessage wechatMessage){
        wechatMessage.setId(IDUtil.getUUID());
        if(StringUtils.isBlank(wechatMessage.getParentId())){
            wechatMessage.setParentId(WechatConstant.Default.PARENT_ID);
        }
        wechatMessageService.insertSelective(wechatMessage);
        return EnumOPerate.INSERT;
    }
    
    /**
     * 
     * 修改消息
     * @param wechatMessage
     * @return EnumOPerate
     */
    private EnumOPerate updateMessage(WechatMessage wechatMessage){
        int num = wechatMessageService.updateByPrimaryKeySelective(wechatMessage);
        if(num == 0){
            throw new CustomRunTimeException("找不到对应的id=["+wechatMessage.getId()+"]，修改失败");
        }
        return EnumOPerate.UPDATE;
    }
}
