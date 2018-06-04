package com.ower.dsyz.admin.auto.model;

import java.io.Serializable;
import java.util.Date;

public class AdminUser implements Serializable {
    /**
     * id
     * 表 : t_admin_user
     * 对应字段 : user_id
     */
    private String userId;

    /**
     * 用户名
     * 表 : t_admin_user
     * 对应字段 : user_name
     */
    private String userName;

    /**
     * 性别
     * 表 : t_admin_user
     * 对应字段 : user_sex
     */
    private String userSex;

    /**
     * 头像
     * 表 : t_admin_user
     * 对应字段 : user_head
     */
    private String userHead;

    /**
     * 出生年月
     * 表 : t_admin_user
     * 对应字段 : user_birtd
     */
    private Date userBirtd;

    /**
     * 是否有效
     * 表 : t_admin_user
     * 对应字段 : is_valid
     */
    private String isValid;

    /**
     * 备注
     * 表 : t_admin_user
     * 对应字段 : remark
     */
    private String remark;

    /**
     * 
     * 表 : t_admin_user
     * 对应字段 : create_user
     */
    private String createUser;

    /**
     * 
     * 表 : t_admin_user
     * 对应字段 : create_date
     */
    private Date createDate;

    /**
     * 
     * 表 : t_admin_user
     * 对应字段 : update_user
     */
    private String updateUser;

    /**
     * 
     * 表 : t_admin_user
     * 对应字段 : update_date
     */
    private Date updateDate;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table t_admin_user
     *
     * @mbggenerated Mon Jun 04 19:45:45 CST 2018
     */
    private static final long serialVersionUID = 1L;

    /**
     * get method 
     *
     * @return t_admin_user.user_id：id
     */
    public String getUserId() {
        return userId;
    }

    /**
     * set method 
     *
     * @param userId  id
     */
    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
    }

    /**
     * get method 
     *
     * @return t_admin_user.user_name：用户名
     */
    public String getUserName() {
        return userName;
    }

    /**
     * set method 
     *
     * @param userName  用户名
     */
    public void setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
    }

    /**
     * get method 
     *
     * @return t_admin_user.user_sex：性别
     */
    public String getUserSex() {
        return userSex;
    }

    /**
     * set method 
     *
     * @param userSex  性别
     */
    public void setUserSex(String userSex) {
        this.userSex = userSex == null ? null : userSex.trim();
    }

    /**
     * get method 
     *
     * @return t_admin_user.user_head：头像
     */
    public String getUserHead() {
        return userHead;
    }

    /**
     * set method 
     *
     * @param userHead  头像
     */
    public void setUserHead(String userHead) {
        this.userHead = userHead == null ? null : userHead.trim();
    }

    /**
     * get method 
     *
     * @return t_admin_user.user_birtd：出生年月
     */
    public Date getUserBirtd() {
        return userBirtd;
    }

    /**
     * set method 
     *
     * @param userBirtd  出生年月
     */
    public void setUserBirtd(Date userBirtd) {
        this.userBirtd = userBirtd;
    }

    /**
     * get method 
     *
     * @return t_admin_user.is_valid：是否有效
     */
    public String getIsValid() {
        return isValid;
    }

    /**
     * set method 
     *
     * @param isValid  是否有效
     */
    public void setIsValid(String isValid) {
        this.isValid = isValid == null ? null : isValid.trim();
    }

    /**
     * get method 
     *
     * @return t_admin_user.remark：备注
     */
    public String getRemark() {
        return remark;
    }

    /**
     * set method 
     *
     * @param remark  备注
     */
    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    /**
     * get method 
     *
     * @return t_admin_user.create_user：
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
     * @return t_admin_user.create_date：
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
     * @return t_admin_user.update_user：
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
     * get method 
     *
     * @return t_admin_user.update_date：
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
     *
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", userId=").append(userId);
        sb.append(", userName=").append(userName);
        sb.append(", userSex=").append(userSex);
        sb.append(", userHead=").append(userHead);
        sb.append(", userBirtd=").append(userBirtd);
        sb.append(", isValid=").append(isValid);
        sb.append(", remark=").append(remark);
        sb.append(", createUser=").append(createUser);
        sb.append(", createDate=").append(createDate);
        sb.append(", updateUser=").append(updateUser);
        sb.append(", updateDate=").append(updateDate);
        sb.append("]");
        return sb.toString();
    }
}