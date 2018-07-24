package com.ower.dsyz.admin.auto.model;

import java.io.Serializable;
import java.util.Date;

public class AdminInsRole implements Serializable {
    /**
     * id
     * 表 : t_admin_ins_role
     * 对应字段 : ins_role_id
     */
    private String insRoleId;

    /**
     * 角色id
     * 表 : t_admin_ins_role
     * 对应字段 : role_id
     */
    private String roleId;

    /**
     * 机构id
     * 表 : t_admin_ins_role
     * 对应字段 : ins_id
     */
    private String insId;

    /**
     * 
     * 表 : t_admin_ins_role
     * 对应字段 : create_date
     */
    private Date createDate;

    /**
     * 
     * 表 : t_admin_ins_role
     * 对应字段 : create_user
     */
    private String createUser;

    /**
     * 
     * 表 : t_admin_ins_role
     * 对应字段 : update_date
     */
    private Date updateDate;

    /**
     * 
     * 表 : t_admin_ins_role
     * 对应字段 : update_user
     */
    private String updateUser;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table t_admin_ins_role
     *
     * @mbggenerated Tue Jul 24 16:13:10 CST 2018
     */
    private static final long serialVersionUID = 1L;

    /**
     * get method 
     *
     * @return t_admin_ins_role.ins_role_id：id
     */
    public String getInsRoleId() {
        return insRoleId;
    }

    /**
     * set method 
     *
     * @param insRoleId  id
     */
    public void setInsRoleId(String insRoleId) {
        this.insRoleId = insRoleId == null ? null : insRoleId.trim();
    }

    /**
     * get method 
     *
     * @return t_admin_ins_role.role_id：角色id
     */
    public String getRoleId() {
        return roleId;
    }

    /**
     * set method 
     *
     * @param roleId  角色id
     */
    public void setRoleId(String roleId) {
        this.roleId = roleId == null ? null : roleId.trim();
    }

    /**
     * get method 
     *
     * @return t_admin_ins_role.ins_id：机构id
     */
    public String getInsId() {
        return insId;
    }

    /**
     * set method 
     *
     * @param insId  机构id
     */
    public void setInsId(String insId) {
        this.insId = insId == null ? null : insId.trim();
    }

    /**
     * get method 
     *
     * @return t_admin_ins_role.create_date：
     */
    public Date getCreateDate() {
        return createDate;
    }

    /**
     * set method 
     *
     * @param createDate  
     */
    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    /**
     * get method 
     *
     * @return t_admin_ins_role.create_user：
     */
    public String getCreateUser() {
        return createUser;
    }

    /**
     * set method 
     *
     * @param createUser  
     */
    public void setCreateUser(String createUser) {
        this.createUser = createUser == null ? null : createUser.trim();
    }

    /**
     * get method 
     *
     * @return t_admin_ins_role.update_date：
     */
    public Date getUpdateDate() {
        return updateDate;
    }

    /**
     * set method 
     *
     * @param updateDate  
     */
    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    /**
     * get method 
     *
     * @return t_admin_ins_role.update_user：
     */
    public String getUpdateUser() {
        return updateUser;
    }

    /**
     * set method 
     *
     * @param updateUser  
     */
    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser == null ? null : updateUser.trim();
    }

    /**
     *
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", insRoleId=").append(insRoleId);
        sb.append(", roleId=").append(roleId);
        sb.append(", insId=").append(insId);
        sb.append(", createDate=").append(createDate);
        sb.append(", createUser=").append(createUser);
        sb.append(", updateDate=").append(updateDate);
        sb.append(", updateUser=").append(updateUser);
        sb.append("]");
        return sb.toString();
    }
}