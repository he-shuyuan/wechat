/**
 * Copyright(c) Foresee Science & Technology Ltd. 
 */
package com.ower.dsyz.admin.manual.dto;

import java.io.Serializable;
import java.util.List;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.NotBlank;

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

public class AdminUserRoleDistDTO implements Serializable{

    /**
     * serialVersionUID:TODO。
     */
    private static final long serialVersionUID = 1L;

    @NotBlank(message="用户id不能为空")
    private String userId;
    
    @NotNull(message="角色列表不能为空")
    private List<String> roleIdList;


    
    public String getUserId() {
        return userId;
    }


    
    public void setUserId(String userId) {
        this.userId = userId;
    }


    
    public List<String> getRoleIdList() {
        return roleIdList;
    }


    
    public void setRoleIdList(List<String> roleIdList) {
        this.roleIdList = roleIdList;
    }



    @Override
    public String toString() {
        return "AdminUserRoleDistDTO [userId=" + userId + ", roleIdList=" + roleIdList + "]";
    }
    
    
}
