/**
 * Copyright(c) Foresee Science & Technology Ltd. 
 */
package com.ower.dsyz.wechat.service.dataBase;

import java.util.List;
import com.ower.dsyz.common.core.page.PageQueryResult;
import com.ower.dsyz.common.core.page.PageRequestParam;
import com.ower.dsyz.wechat.auto.model.WechatMessage;
import com.ower.dsyz.wechat.manual.dto.WechatMessageDTO;

/**
 * <pre>
 * 微信消息数据库接口
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

public interface IWechatMessageService {

    /**
     * 根据主键删除数据库的记录
     *
     * @param id
     */
    int deleteByPrimaryKey(String id);


    /**
     * 插入数据库记录
     *
     * @param record
     */
    int insertSelective(WechatMessage record);

    /**
     * 根据主键获取一条数据库记录
     *
     * @param id
     */
    WechatMessage selectByPrimaryKey(String id);

    /**
     * 根据主键来更新部分数据库记录
     *
     * @param record
     */
    int updateByPrimaryKeySelective(WechatMessage record);


    /**
     * 
     * 分页查询app列表
     * @param param
     * @return PageQueryResult<WechatMessageDTO>
     */
    PageQueryResult<WechatMessageDTO> pageQueryWechatMessageList(PageRequestParam param);
    
    
    /**
     * 
     * 条件查询
     * @param WechatMessageDTO
     * @return PageQueryResult<WechatMessageDTO>
     */
    List<WechatMessageDTO> selectWechatMessageList(WechatMessageDTO wechatMessageDTO);
}
