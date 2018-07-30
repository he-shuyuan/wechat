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

public class AdminInsRoleDistDTO implements Serializable{

    /**
     * serialVersionUID:TODO。
     */
    private static final long serialVersionUID = 1L;

    @NotBlank(message="机构id不能为空")
    private String insId;
    
    @NotNull(message="角色列表不能为空")
    private List<String> roleIdList;

    public String getInsId() {
        return insId;
    }

    public void setInsId(String insId) {
        this.insId = insId;
    }

   public List<String> getRoleIdList() {
        return roleIdList;
    }

    public void setRoleIdList(List<String> roleIdList) {
        this.roleIdList = roleIdList;
    }

    @Override
    public String toString() {
        return "AdminInsRoleDistDTO [insId=" + insId + ", roleIdList=" + roleIdList + "]";
    }

}
