package com.ower.dsyz.admin.auto.dao;

import com.ower.dsyz.admin.auto.model.AdminUserLogin;

public interface AdminUserLoginMapper {
    /**
     * 根据主键删除数据库的记录
     *
     * @param userId
     */
    int deleteByPrimaryKey(String userId);

    /**
     * 插入数据库记录
     *
     * @param record
     */
    int insert(AdminUserLogin record);

    /**
     * 插入数据库记录
     *
     * @param record
     */
    int insertSelective(AdminUserLogin record);

    /**
     * 根据主键获取一条数据库记录
     *
     * @param userId
     */
    AdminUserLogin selectByPrimaryKey(String userId);

    /**
     * 根据主键来更新部分数据库记录
     *
     * @param record
     */
    int updateByPrimaryKeySelective(AdminUserLogin record);

    /**
     * 根据主键来更新数据库记录
     *
     * @param record
     */
    int updateByPrimaryKey(AdminUserLogin record);
}