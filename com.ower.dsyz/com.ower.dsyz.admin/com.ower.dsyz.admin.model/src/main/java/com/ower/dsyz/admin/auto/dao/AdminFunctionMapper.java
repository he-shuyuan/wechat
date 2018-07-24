package com.ower.dsyz.admin.auto.dao;

import com.ower.dsyz.admin.auto.model.AdminFunction;

public interface AdminFunctionMapper {
    /**
     * 根据主键删除数据库的记录
     *
     * @param functionId
     */
    int deleteByPrimaryKey(String functionId);

    /**
     * 插入数据库记录
     *
     * @param record
     */
    int insert(AdminFunction record);

    /**
     * 插入数据库记录
     *
     * @param record
     */
    int insertSelective(AdminFunction record);

    /**
     * 根据主键获取一条数据库记录
     *
     * @param functionId
     */
    AdminFunction selectByPrimaryKey(String functionId);

    /**
     * 根据主键来更新部分数据库记录
     *
     * @param record
     */
    int updateByPrimaryKeySelective(AdminFunction record);

    /**
     * 根据主键来更新数据库记录
     *
     * @param record
     */
    int updateByPrimaryKey(AdminFunction record);
}