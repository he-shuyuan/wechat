package com.ower.dsyz.admin.auto.dao;

import com.ower.dsyz.admin.auto.model.AdminUser;

public interface AdminUserMapper {
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
    int insert(AdminUser record);

    /**
     * 插入数据库记录
     *
     * @param record
     */
    int insertSelective(AdminUser record);

    /**
     * 根据主键获取一条数据库记录
     *
     * @param userId
     */
    AdminUser selectByPrimaryKey(String userId);

    /**
     * 根据主键来更新部分数据库记录
     *
     * @param record
     */
    int updateByPrimaryKeySelective(AdminUser record);

    /**
     * 根据主键来更新数据库记录
     *
     * @param record
     */
    int updateByPrimaryKey(AdminUser record);
}