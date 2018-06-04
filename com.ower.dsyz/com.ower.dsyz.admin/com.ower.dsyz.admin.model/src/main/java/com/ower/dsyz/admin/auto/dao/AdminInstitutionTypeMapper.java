package com.ower.dsyz.admin.auto.dao;

import com.ower.dsyz.admin.auto.model.AdminInstitutionType;

public interface AdminInstitutionTypeMapper {
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
    int insert(AdminInstitutionType record);

    /**
     * 插入数据库记录
     *
     * @param record
     */
    int insertSelective(AdminInstitutionType record);

    /**
     * 根据主键获取一条数据库记录
     *
     * @param id
     */
    AdminInstitutionType selectByPrimaryKey(String id);

    /**
     * 根据主键来更新部分数据库记录
     *
     * @param record
     */
    int updateByPrimaryKeySelective(AdminInstitutionType record);

    /**
     * 根据主键来更新数据库记录
     *
     * @param record
     */
    int updateByPrimaryKey(AdminInstitutionType record);
}