/**
 * Copyright(c) Foresee Science & Technology Ltd. 
 */
package com.ower.dsyz.admin.manual.dto;

import java.util.List;

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

public class AdminRoleFunListDTO {
    /**
     * 角色id
     */
    private String roleId;
    
    /**
     * 菜单列表
     */
    private List<String> functionIdList;

    
    public String getRoleId() {
        return roleId;
    }

    
    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    
    public List<String> getFunctionIdList() {
        return functionIdList;
    }

    
    public void setFunctionIdList(List<String> functionIdList) {
        this.functionIdList = functionIdList;
    }


    @Override
    public String toString() {
        return "AdminRoleFunListDTO [roleId=" + roleId + ", functionIdList=" + functionIdList + "]";
    }
    
}
