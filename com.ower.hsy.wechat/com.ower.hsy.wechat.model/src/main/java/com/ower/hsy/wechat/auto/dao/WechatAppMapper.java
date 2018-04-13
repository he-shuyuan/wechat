package com.ower.hsy.wechat.auto.dao;

import com.ower.hsy.wechat.auto.model.WechatApp;

public interface WechatAppMapper {
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
    int insert(WechatApp record);

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
     * 根据主键来更新数据库记录
     *
     * @param record
     */
    int updateByPrimaryKey(WechatApp record);
}