package com.ower.dsyz.admin.auto.dao;

import com.ower.dsyz.admin.auto.model.AdminInstitution;

public interface AdminInstitutionMapper {
    /**
     * 根据主键删除数据库的记录
     *
     * @param insId
     */
    int deleteByPrimaryKey(String insId);

    /**
     * 插入数据库记录
     *
     * @param record
     */
    int insert(AdminInstitution record);

    /**
     * 插入数据库记录
     *
     * @param record
     */
    int insertSelective(AdminInstitution record);

    /**
     * 根据主键获取一条数据库记录
     *
     * @param insId
     */
    AdminInstitution selectByPrimaryKey(String insId);

    /**
     * 根据主键来更新部分数据库记录
     *
     * @param record
     */
    int updateByPrimaryKeySelective(AdminInstitution record);

    /**
     * 根据主键来更新数据库记录
     *
     * @param record
     */
    int updateByPrimaryKey(AdminInstitution record);
}