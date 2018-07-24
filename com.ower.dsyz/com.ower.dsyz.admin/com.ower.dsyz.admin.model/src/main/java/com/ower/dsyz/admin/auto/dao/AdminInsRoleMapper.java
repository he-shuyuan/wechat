package com.ower.dsyz.admin.auto.dao;

import com.ower.dsyz.admin.auto.model.AdminInsRole;

public interface AdminInsRoleMapper {
    /**
     * 根据主键删除数据库的记录
     *
     * @param insRoleId
     */
    int deleteByPrimaryKey(String insRoleId);

    /**
     * 插入数据库记录
     *
     * @param record
     */
    int insert(AdminInsRole record);

    /**
     * 插入数据库记录
     *
     * @param record
     */
    int insertSelective(AdminInsRole record);

    /**
     * 根据主键获取一条数据库记录
     *
     * @param insRoleId
     */
    AdminInsRole selectByPrimaryKey(String insRoleId);

    /**
     * 根据主键来更新部分数据库记录
     *
     * @param record
     */
    int updateByPrimaryKeySelective(AdminInsRole record);

    /**
     * 根据主键来更新数据库记录
     *
     * @param record
     */
    int updateByPrimaryKey(AdminInsRole record);
}