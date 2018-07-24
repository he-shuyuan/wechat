package com.ower.dsyz.admin.auto.dao;

import com.ower.dsyz.admin.auto.model.AdminDepartment;

public interface AdminDepartmentMapper {
    /**
     * 根据主键删除数据库的记录
     *
     * @param depId
     */
    int deleteByPrimaryKey(String depId);

    /**
     * 插入数据库记录
     *
     * @param record
     */
    int insert(AdminDepartment record);

    /**
     * 插入数据库记录
     *
     * @param record
     */
    int insertSelective(AdminDepartment record);

    /**
     * 根据主键获取一条数据库记录
     *
     * @param depId
     */
    AdminDepartment selectByPrimaryKey(String depId);

    /**
     * 根据主键来更新部分数据库记录
     *
     * @param record
     */
    int updateByPrimaryKeySelective(AdminDepartment record);

    /**
     * 根据主键来更新数据库记录
     *
     * @param record
     */
    int updateByPrimaryKey(AdminDepartment record);
}