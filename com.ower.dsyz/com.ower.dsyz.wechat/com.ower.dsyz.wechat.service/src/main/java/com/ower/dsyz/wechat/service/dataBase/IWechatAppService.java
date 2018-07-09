/**
 * Copyright(c) Foresee Science & Technology Ltd. 
 */
package com.ower.dsyz.wechat.service.dataBase;

import java.util.List;
import com.ower.dsyz.common.core.page.PageQueryResult;
import com.ower.dsyz.common.core.page.PageRequestParam;
import com.ower.dsyz.wechat.auto.model.WechatApp;
import com.ower.dsyz.wechat.manual.dto.WechatAppDTO;

/**
 * <pre>
 * 微信公众号管理接口
 * </pre>
 *
 * @author HeShuyuan@163.com
 * @date 2018年1月11日
 * @version 1.00.00
 * 
 *          <pre>
 * 修改记录 
 *    修改后版本:     修改人：  修改日期:     修改内容:
 *          </pre>
 */

public interface IWechatAppService {

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
    int insertSelective(WechatApp record);

    /**
     * 根据主键获取一条数据库记录
     *
     * @param id
     */
    WechatApp selectByPrimaryKey(String id);

    /**
     * 根据主键来更新部分数据库记录
     *
     * @param record
     */
    int updateByPrimaryKeySelective(WechatApp record);
    
    /**
     * 
     * 分页查询app列表
     * @param param
     * @return PageQueryResult<WechatAppDTO>
     */
    PageQueryResult<WechatAppDTO> pageQueryWechatAppList(PageRequestParam param);
    
    
    /**
     * 
     * 条件查询
     * @param wechatAppDTO
     * @return PageQueryResult<WechatAppDTO>
     */
    List<WechatAppDTO> selectWechatAppList(WechatAppDTO wechatAppDTO);
    
    
    /**
     * 或查询
     * @param wechatAppDTO
     * @return List<WechatAppDTO>
     */
    List<WechatApp> selectIsExitWechatAppList(WechatApp wechatApp);
}
