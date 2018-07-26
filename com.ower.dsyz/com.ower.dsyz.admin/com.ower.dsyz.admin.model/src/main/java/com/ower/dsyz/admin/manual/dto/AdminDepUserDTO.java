/**
 * Copyright(c) Foresee Science & Technology Ltd. 
 */
package com.ower.dsyz.admin.manual.dto;

import java.io.Serializable;

/**
 * <pre>
 * TODO。
 * </pre>
 *
 * @author HeShuyuan@foresee.com.cn
 * @date 2018年7月26日
 * @version 1.00.00
 * 
 *          <pre>
 * 修改记录 
 *    修改后版本:     修改人：  修改日期:     修改内容:
 *          </pre>
 */

public class AdminDepUserDTO implements Serializable{

    /**
     * serialVersionUID:TODO。
     */
    private static final long serialVersionUID = 1L;

    /**
     * 
     * 表 : t_admin_dep_user
     * 对应字段 : dep_user_id
     */
    private String depUserId;

    /**
     * 
     * 表 : t_admin_dep_user
     * 对应字段 : user_id
     */
    private String userId;

    /**
     * 
     * 表 : t_admin_dep_user
     * 对应字段 : dep_id
     */
    private String depId;
    
    /**
     * 手机号码
     */
    private String phone;
    /**
     * 是否有效，取值N/Y，缺省值是Y
     * 表 : t_admin_dep_user
     * 对应字段 : is_valid
     */
    private String isValid;
    
    /**
     * 机构id
     */
    private String insId;

    
    public String getDepUserId() {
        return depUserId;
    }

    
    public void setDepUserId(String depUserId) {
        this.depUserId = depUserId;
    }

    
    public String getUserId() {
        return userId;
    }

    
    public void setUserId(String userId) {
        this.userId = userId;
    }

    
    public String getDepId() {
        return depId;
    }

    
    public void setDepId(String depId) {
        this.depId = depId;
    }

    
    public String getIsValid() {
        return isValid;
    }

    
    public void setIsValid(String isValid) {
        this.isValid = isValid;
    }

    
    public String getInsId() {
        return insId;
    }

    
    public void setInsId(String insId) {
        this.insId = insId;
    }


    
    public String getPhone() {
        return phone;
    }


    
    public void setPhone(String phone) {
        this.phone = phone;
    }


    @Override
    public String toString() {
        return "AdminDepUserDTO [depUserId=" + depUserId + ", userId=" + userId + ", depId=" + depId + ", phone=" + phone + ", isValid="
                + isValid + ", insId=" + insId + "]";
    }

}
