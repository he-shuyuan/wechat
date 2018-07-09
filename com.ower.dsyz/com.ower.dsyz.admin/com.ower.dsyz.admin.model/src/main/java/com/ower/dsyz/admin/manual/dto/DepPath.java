/**
 * Copyright(c) Foresee Science & Technology Ltd. 
 */
package com.ower.dsyz.admin.manual.dto;

import org.apache.commons.lang3.StringUtils;

/**
 * <pre>
 * 部门路径pathCode
 * </pre>
 *
 * @author HeShuyuan@163.com
 * @date 2018年4月25日
 * @version 1.00.00
 * 
 *          <pre>
 * 修改记录 
 *    修改后版本:     修改人：  修改日期:     修改内容:
 *          </pre>
 */

public class DepPath {
   
    /**
     * pathCode默认空格
     */
    private String pathCode = "";
    
    /**
     * 机构id
     */
    private String insId;
    
    /**
     * 父级部门Id
     */
    private String parentDepId;
    
    

    
    public String getPathCode() {
        return pathCode;
    }

    
    public void setPathCode(String pathCode) {
        if(StringUtils.isBlank(pathCode)){
            this.pathCode = "";
        }else{
            this.pathCode = pathCode;
        }
    }

    
    public String getInsId() {
        return insId;
    }

    
    public void setInsId(String insId) {
        this.insId = insId;
    }


    
    public String getParentDepId() {
        return parentDepId;
    }


    
    public void setParentDepId(String parentDepId) {
        this.parentDepId = parentDepId;
    }


    @Override
    public String toString() {
        return "DeptPath [pathCode=" + pathCode + ", insId=" + insId + ", parentDepId=" + parentDepId + "]";
    }
    
}
