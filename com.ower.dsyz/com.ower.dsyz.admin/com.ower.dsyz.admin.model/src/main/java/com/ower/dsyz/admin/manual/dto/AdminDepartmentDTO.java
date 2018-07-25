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
 * @author HeShuyuan@163.com
 * @date 2018年7月2日
 * @version 1.00.00
 * 
 *          <pre>
 * 修改记录 
 *    修改后版本:     修改人：  修改日期:     修改内容:
 *          </pre>
 */

public class AdminDepartmentDTO implements Serializable {

    /**
     * serialVersionUID:TODO。
     */
    private static final long serialVersionUID = 1L;
    
    /**
     * 
     * 表 : t_admin_department
     * 对应字段 : dep_id
     */
    private String depId;

    /**
     * 
     * 表 : t_admin_department
     * 对应字段 : dep_name
     */
    private String depName;

    /**
     * 
     * 表 : t_admin_department
     * 对应字段 : ins_id
     */
    private String insId;
    
    /**
     * 机构名称
     */
    private String insName;
    /**
     * 业务类型
     */
    private String busTypeId;
    
    /**
     * 业务名称
     */
    private String busTypeName;
    /**
     * 根机构的parent_id为000
     * 表 : t_admin_department
     * 对应字段 : parent_dep_Id
     */
    private String parentDepId;

    /**
     * 每层由三位数字构成，每层由001开始计数
     * 表 : t_admin_department
     * 对应字段 : path_code
     */
    private String pathCode;

    /**
     * 部门地址
     * 表 : t_admin_department
     * 对应字段 : address
     */
    private String address;

    /**
     * 联系人
     * 表 : t_admin_department
     * 对应字段 : contactor
     */
    private String contactor;

    /**
     * 所在地区
     * 表 : t_admin_department
     * 对应字段 : area
     */
    private String area;

    /**
     * 
     * 表 : t_admin_department
     * 对应字段 : longitude
     */
    private String longitude;

    /**
     * 
     * 表 : t_admin_department
     * 对应字段 : latitude
     */
    private String latitude;

    /**
     * Y:是，N:否
     * 表 : t_admin_department
     * 对应字段 : Is_valid
     */
    private String isValid;

    /**
     * 描述
     * 表 : t_admin_department
     * 对应字段 : REMARK
     */
    private String remark;

    
    public String getDepId() {
        return depId;
    }

    
    public void setDepId(String depId) {
        this.depId = depId;
    }

    
    public String getDepName() {
        return depName;
    }

    
    public void setDepName(String depName) {
        this.depName = depName;
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

    
    public String getPathCode() {
        return pathCode;
    }

    
    public void setPathCode(String pathCode) {
        this.pathCode = pathCode;
    }

    
    public String getAddress() {
        return address;
    }

    
    public void setAddress(String address) {
        this.address = address;
    }

    
    public String getContactor() {
        return contactor;
    }

    
    public void setContactor(String contactor) {
        this.contactor = contactor;
    }

    
    public String getArea() {
        return area;
    }

    
    public void setArea(String area) {
        this.area = area;
    }

    
    public String getLongitude() {
        return longitude;
    }

    
    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    
    public String getLatitude() {
        return latitude;
    }

    
    public void setLatitude(String latitude) {
        this.latitude = latitude;
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


    
    public String getInsName() {
        return insName;
    }


    
    public void setInsName(String insName) {
        this.insName = insName;
    }


    @Override
    public String toString() {
        return "AdminDepartmentDTO [depId=" + depId + ", depName=" + depName + ", insId=" + insId + ", busTypeId=" + busTypeId
                + ", busTypeName=" + busTypeName + ", parentDepId=" + parentDepId + ", pathCode=" + pathCode + ", address=" + address
                + ", contactor=" + contactor + ", area=" + area + ", longitude=" + longitude + ", latitude=" + latitude + ", isValid="
                + isValid + ", remark=" + remark + "]";
    }


  
}
