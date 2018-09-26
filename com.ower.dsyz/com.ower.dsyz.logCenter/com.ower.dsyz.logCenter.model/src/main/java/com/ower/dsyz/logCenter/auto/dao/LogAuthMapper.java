package com.ower.dsyz.logCenter.auto.dao;

import com.ower.dsyz.logCenter.auto.model.LogAuth;

public interface LogAuthMapper {
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
    int insert(LogAuth record);

    /**
     * 插入数据库记录
     *
     * @param record
     */
    int insertSelective(LogAuth record);

    /**
     * 根据主键获取一条数据库记录
     *
     * @param id
     */
    LogAuth selectByPrimaryKey(String id);

    /**
     * 根据主键来更新部分数据库记录
     *
     * @param record
     */
    int updateByPrimaryKeySelective(LogAuth record);

    /**
     * 根据主键来更新数据库记录
     *
     * @param record
     */
    int updateByPrimaryKey(LogAuth record);
}