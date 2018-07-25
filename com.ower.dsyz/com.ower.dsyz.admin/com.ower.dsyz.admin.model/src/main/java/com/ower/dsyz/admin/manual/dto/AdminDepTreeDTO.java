/**
 * Copyright(c) Foresee Science & Technology Ltd. 
 */
package com.ower.dsyz.admin.manual.dto;

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

public class AdminDepTreeDTO extends AdminDepartmentDTO{

    /**
     * serialVersionUID:TODO。
     */
    private static final long serialVersionUID = 1L;
    
    /**
     * 是否是叶子节点
     */
    private boolean isLeaf;
    
    /**
     * 孩子数
     */
    private int hasChildNum;

    
    public boolean isLeaf() {
        return isLeaf;
    }

    
    public void setLeaf(boolean isLeaf) {
        this.isLeaf = isLeaf;
    }

    
    public int getHasChildNum() {
        return hasChildNum;
    }

    
    public void setHasChildNum(int hasChildNum) {
        this.hasChildNum = hasChildNum;
    }


    @Override
    public String toString() {
        return "AdminDepTreeDTO [isLeaf=" + isLeaf + ", hasChildNum=" + hasChildNum + ", getDepId()=" + getDepId() + ", getDepName()="
                + getDepName() + ", getInsId()=" + getInsId() + ", getParentDepId()=" + getParentDepId() + ", getPathCode()="
                + getPathCode() + ", getAddress()=" + getAddress() + ", getContactor()=" + getContactor() + ", getArea()=" + getArea()
                + ", getLongitude()=" + getLongitude() + ", getLatitude()=" + getLatitude() + ", getIsValid()=" + getIsValid()
                + ", getRemark()=" + getRemark() + ", getBusTypeId()=" + getBusTypeId() + ", getBusTypeName()=" + getBusTypeName()
                + ", toString()=" + super.toString() + ", getClass()=" + getClass() + ", hashCode()=" + hashCode() + "]";
    }


    
    

    
}
