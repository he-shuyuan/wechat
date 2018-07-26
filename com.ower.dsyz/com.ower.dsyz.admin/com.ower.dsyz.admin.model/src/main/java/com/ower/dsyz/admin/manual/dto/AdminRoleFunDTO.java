package com.ower.dsyz.admin.manual.dto;

import java.io.Serializable;

public class AdminRoleFunDTO implements Serializable {
    /**
     * serialVersionUID:TODO。
     */
    private static final long serialVersionUID = 1L;

    /**
     * 
     * 表 : t_admin_role_fun
     * 对应字段 : id
     */
    private String id;

    /**
     * 
     * 表 : t_admin_role_fun
     * 对应字段 : role_id
     */
    private String roleId;
    
    /**
     * 
     * 表 : t_admin_role_fun
     * 对应字段 : function_id
     */
    private String functionId;

    /**
     * 部门名称
     */
    private String functionName;
    
    /**
     * 菜单code
     */
    private String code;
    /**
     * 父级菜单id
     */
    private String parentId;
    
    /**
     * 是否被选择
     */
    private Boolean hasSelected;
    
    /**
     * 子节点
     */
    private Boolean leaf;
    
    /**
     * 业务id
     */
    private String busTypeId;
    
    public String getId() {
        return id;
    }

    
    public void setId(String id) {
        this.id = id;
    }

    
    public String getRoleId() {
        return roleId;
    }

    
    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    
    public String getFunctionId() {
        return functionId;
    }

    
    public void setFunctionId(String functionId) {
        this.functionId = functionId;
    }

    
    public String getParentId() {
        return parentId;
    }

    
    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    
    

    
    
    
    public String getBusTypeId() {
        return busTypeId;
    }


    
    public void setBusTypeId(String busTypeId) {
        this.busTypeId = busTypeId;
    }


    public Boolean getHasSelected() {
        return hasSelected;
    }


    
    public void setHasSelected(Boolean hasSelected) {
        this.hasSelected = hasSelected;
    }


    public Boolean getLeaf() {
        return leaf;
    }

    
    public void setLeaf(Boolean leaf) {
        this.leaf = leaf;
    }


    
    public String getFunctionName() {
        return functionName;
    }


    
    public void setFunctionName(String functionName) {
        this.functionName = functionName;
    }


    
    public String getCode() {
        return code;
    }


    
    public void setCode(String code) {
        this.code = code;
    }


    @Override
    public String toString() {
        return "AdminRoleFunDTO [id=" + id + ", roleId=" + roleId + ", functionId=" + functionId + ", functionName=" + functionName
                + ", code=" + code + ", parentId=" + parentId + ", hasSelected=" + hasSelected + ", leaf=" + leaf + ", busTypeId="
                + busTypeId + "]";
    }



}