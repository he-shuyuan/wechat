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
 * @date 2018年7月25日
 * @version 1.00.00
 * 
 *          <pre>
 * 修改记录 
 *    修改后版本:     修改人：  修改日期:     修改内容:
 *          </pre>
 */

public class AdminRoleDTO implements Serializable {
    /**
     * serialVersionUID:TODO。
     */
    private static final long serialVersionUID = 1L;

    /**
     * 
     * 表 : t_admin_role
     * 对应字段 : role_id
     */
    private String roleId;

    /**
     * 
     * 表 : t_admin_role
     * 对应字段 : bus_type_id
     */
    private String busTypeId;

    /**
     * 业务类型
     */
    private String busTypeName;
    /**
     * 
     * 表 : t_admin_role
     * 对应字段 : role_code
     */
    private String roleCode;

    /**
     * 
     * 表 : t_admin_role
     * 对应字段 : role_name
     */
    private String roleName;

    /**
     * 是否启用,默认Y
     * 表 : t_admin_role
     * 对应字段 : is_valid
     */
    private String isValid;

    /**
     * 描述
     * 表 : t_admin_role
     * 对应字段 : remark
     */
    private String remark;

    
    public String getRoleId() {
        return roleId;
    }

    
    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    
    public String getBusTypeId() {
        return busTypeId;
    }

    
    public void setBusTypeId(String busTypeId) {
        this.busTypeId = busTypeId;
    }

    
    public String getBusTypeName() {
        return busTypeName;
    }

    
    public void setBusTypeName(String busTypeName) {
        this.busTypeName = busTypeName;
    }

    
    public String getRoleCode() {
        return roleCode;
    }

    
    public void setRoleCode(String roleCode) {
        this.roleCode = roleCode;
    }

    
    public String getRoleName() {
        return roleName;
    }

    
    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    
    public String getIsValid() {
        return isValid;
    }

    
    public void setIsValid(String isValid) {
        this.isValid = isValid;
    }

    
    public String getRemark() {
        return remark;
    }

    
    public void setRemark(String remark) {
        this.remark = remark;
    }


    @Override
    public String toString() {
        return "AdminRoleDTO [roleId=" + roleId + ", busTypeId=" + busTypeId + ", busTypeName=" + busTypeName + ", roleCode=" + roleCode
                + ", roleName=" + roleName + ", isValid=" + isValid + ", remark=" + remark + "]";
    }
    
    
}
