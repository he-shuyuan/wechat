/**
 * Copyright(c) Foresee Science & Technology Ltd. 
 */
package com.ower.dsyz.admin.manual.dto;

import java.io.Serializable;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * <pre>
 * TODO。
 * </pre>
 *
 * @author HeShuyuan@foresee.com.cn
 * @date 2018年7月25日
 * @version 1.00.00
 * 
 *          <pre>
 * 修改记录 
 *    修改后版本:     修改人：  修改日期:     修改内容:
 *          </pre>
 */

public class AdminUserDTO  implements Serializable{

    /**
     * serialVersionUID:TODO。
     */
    private static final long serialVersionUID = 1L;

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
     * 电话号码
     */
    private String phone;
    
    /**
     * 登录名
     */
    private String loginName;
    
    /**
     * 密码
     */
    @JsonIgnore
    private String password;
    
    /**
     * 加密盐
     */
    @JsonIgnore
    private String salt;

    
    public String getUserId() {
        return userId;
    }

    
    public void setUserId(String userId) {
        this.userId = userId;
    }

    
    public String getUserName() {
        return userName;
    }

    
    public void setUserName(String userName) {
        this.userName = userName;
    }

    
    public String getUserSex() {
        return userSex;
    }

    
    public void setUserSex(String userSex) {
        this.userSex = userSex;
    }

    
    public String getUserHead() {
        return userHead;
    }

    
    public void setUserHead(String userHead) {
        this.userHead = userHead;
    }

    
    public Date getUserBirtd() {
        return userBirtd;
    }

    
    public void setUserBirtd(Date userBirtd) {
        this.userBirtd = userBirtd;
    }

    
    public String getIsValid() {
        return isValid;
    }

    
    public void setIsValid(String isValid) {
        this.isValid = isValid;
    }

    
    public String getPhone() {
        return phone;
    }

    
    public void setPhone(String phone) {
        this.phone = phone;
    }

    
    public String getLoginName() {
        return loginName;
    }

    
    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    
    public String getPassword() {
        return password;
    }

    
    public void setPassword(String password) {
        this.password = password;
    }

    
    public String getSalt() {
        return salt;
    }

    
    public void setSalt(String salt) {
        this.salt = salt;
    }


    @Override
    public String toString() {
        return "AdminUserDTO [userId=" + userId + ", userName=" + userName + ", userSex=" + userSex + ", userHead=" + userHead
                + ", userBirtd=" + userBirtd + ", isValid=" + isValid + ", phone=" + phone + ", loginName=" + loginName + ", password="
                + password + ", salt=" + salt + "]";
    }
    
    
}
