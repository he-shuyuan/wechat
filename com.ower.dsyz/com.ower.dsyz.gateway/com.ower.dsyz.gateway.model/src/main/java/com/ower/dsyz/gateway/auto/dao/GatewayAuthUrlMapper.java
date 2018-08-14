package com.ower.dsyz.gateway.auto.dao;

import com.ower.dsyz.gateway.auto.model.GatewayAuthUrl;

public interface GatewayAuthUrlMapper {
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
    int insert(GatewayAuthUrl record);

    /**
     * 插入数据库记录
     *
     * @param record
     */
    int insertSelective(GatewayAuthUrl record);

    /**
     * 根据主键获取一条数据库记录
     *
     * @param id
     */
    GatewayAuthUrl selectByPrimaryKey(String id);

    /**
     * 根据主键来更新部分数据库记录
     *
     * @param record
     */
    int updateByPrimaryKeySelective(GatewayAuthUrl record);

    /**
     * 根据主键来更新数据库记录
     *
     * @param record
     */
    int updateByPrimaryKey(GatewayAuthUrl record);
}