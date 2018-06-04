package com.ower.dsyz.admin.auto.dao;

import com.ower.dsyz.admin.auto.model.AdminBusiness;

public interface AdminBusinessMapper {
    /**
     * 根据主键删除数据库的记录
     *
     * @param busTypeId
     */
    int deleteByPrimaryKey(String busTypeId);

    /**
     * 插入数据库记录
     *
     * @param record
     */
    int insert(AdminBusiness record);

    /**
     * 插入数据库记录
     *
     * @param record
     */
    int insertSelective(AdminBusiness record);

    /**
     * 根据主键获取一条数据库记录
     *
     * @param busTypeId
     */
    AdminBusiness selectByPrimaryKey(String busTypeId);

    /**
     * 根据主键来更新部分数据库记录
     *
     * @param record
     */
    int updateByPrimaryKeySelective(AdminBusiness record);

    /**
     * 根据主键来更新数据库记录
     *
     * @param record
     */
    int updateByPrimaryKey(AdminBusiness record);
}