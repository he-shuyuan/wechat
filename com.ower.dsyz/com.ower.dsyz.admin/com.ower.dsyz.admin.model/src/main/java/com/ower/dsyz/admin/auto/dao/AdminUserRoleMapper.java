package com.ower.dsyz.admin.auto.dao;

import com.ower.dsyz.admin.auto.model.AdminUserRole;

public interface AdminUserRoleMapper {
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
    int insert(AdminUserRole record);

    /**
     * 插入数据库记录
     *
     * @param record
     */
    int insertSelective(AdminUserRole record);

    /**
     * 根据主键获取一条数据库记录
     *
     * @param id
     */
    AdminUserRole selectByPrimaryKey(String id);

    /**
     * 根据主键来更新部分数据库记录
     *
     * @param record
     */
    int updateByPrimaryKeySelective(AdminUserRole record);

    /**
     * 根据主键来更新数据库记录
     *
     * @param record
     */
    int updateByPrimaryKey(AdminUserRole record);
}