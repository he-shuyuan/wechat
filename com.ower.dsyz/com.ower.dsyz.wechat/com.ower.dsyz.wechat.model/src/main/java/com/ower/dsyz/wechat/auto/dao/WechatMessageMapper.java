package com.ower.dsyz.wechat.auto.dao;

import com.ower.dsyz.wechat.auto.model.WechatMessage;

public interface WechatMessageMapper {
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
    int insert(WechatMessage record);

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
     * 根据主键来更新数据库记录
     *
     * @param record
     */
    int updateByPrimaryKey(WechatMessage record);
}