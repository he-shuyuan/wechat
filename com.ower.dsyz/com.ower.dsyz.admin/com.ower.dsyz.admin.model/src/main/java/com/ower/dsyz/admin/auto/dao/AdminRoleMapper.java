package com.ower.dsyz.admin.auto.dao;

import com.ower.dsyz.admin.auto.model.AdminRole;

public interface AdminRoleMapper {
    /**
     * 根据主键删除数据库的记录
     *
     * @param roleId
     */
    int deleteByPrimaryKey(String roleId);

    /**
     * 插入数据库记录
     *
     * @param record
     */
    int insert(AdminRole record);

    /**
     * 插入数据库记录
     *
     * @param record
     */
    int insertSelective(AdminRole record);

    /**
     * 根据主键获取一条数据库记录
     *
     * @param roleId
     */
    AdminRole selectByPrimaryKey(String roleId);

    /**
     * 根据主键来更新部分数据库记录
     *
     * @param record
     */
    int updateByPrimaryKeySelective(AdminRole record);

    /**
     * 根据主键来更新数据库记录
     *
     * @param record
     */
    int updateByPrimaryKey(AdminRole record);
}