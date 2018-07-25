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

public class AdminUserDepDTO extends AdminUserDTO {

    /**
     * serialVersionUID:TODO。
     */
    private static final long serialVersionUID = 1L;

    /**
     * 
     */
    private String depId;
    
    /**
     * 
     */
    private String pathCode;

    
    public String getDepId() {
        return depId;
    }

    
    public void setDepId(String depId) {
        this.depId = depId;
    }

    
    public String getPathCode() {
        return pathCode;
    }

    
    public void setPathCode(String pathCode) {
        this.pathCode = pathCode;
    }


    @Override
    public String toString() {
        return "AdminUserDepDTO [depId=" + depId + ", pathCode=" + pathCode + ", getUserId()=" + getUserId() + ", getUserName()="
                + getUserName() + ", getUserSex()=" + getUserSex() + ", getUserHead()=" + getUserHead() + ", getUserBirtd()="
                + getUserBirtd() + ", getIsValid()=" + getIsValid() + ", getPhone()=" + getPhone() + ", getLoginName()=" + getLoginName()
                + ", getPassword()=" + getPassword() + ", getSalt()=" + getSalt() + ", toString()=" + super.toString() + ", getClass()="
                + getClass() + ", hashCode()=" + hashCode() + "]";
    }
    
    
}
