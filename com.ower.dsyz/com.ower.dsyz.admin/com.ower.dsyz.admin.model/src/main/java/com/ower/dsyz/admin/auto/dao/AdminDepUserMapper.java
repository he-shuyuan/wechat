package com.ower.dsyz.admin.auto.dao;

import com.ower.dsyz.admin.auto.model.AdminDepUser;

public interface AdminDepUserMapper {
    /**
     * 根据主键删除数据库的记录
     *
     * @param depUserId
     */
    int deleteByPrimaryKey(String depUserId);

    /**
     * 插入数据库记录
     *
     * @param record
     */
    int insert(AdminDepUser record);

    /**
     * 插入数据库记录
     *
     * @param record
     */
    int insertSelective(AdminDepUser record);

    /**
     * 根据主键获取一条数据库记录
     *
     * @param depUserId
     */
    AdminDepUser selectByPrimaryKey(String depUserId);

    /**
     * 根据主键来更新部分数据库记录
     *
     * @param record
     */
    int updateByPrimaryKeySelective(AdminDepUser record);

    /**
     * 根据主键来更新数据库记录
     *
     * @param record
     */
    int updateByPrimaryKey(AdminDepUser record);
}