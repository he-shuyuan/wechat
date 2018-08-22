/**
 * Copyright(c) Foresee Science & Technology Ltd. 
 */
package com.ower.dsyz.admin.service;

import com.ower.dsyz.admin.manual.dto.AdminInstitutionDTO;
import com.ower.dsyz.admin.manual.dto.UserLoginInsInfo;

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

public interface IAdminInsDepService {

    /**
     * 新增机构并且新增部门
     * TODO。
     * @param addInstitutionDTO void
     */
    void addInsAndDep(AdminInstitutionDTO addInstitutionDTO);

    
    /**
     * 获取用户机构信息
     * @param userId
     * @param busTypeId
     * @return
     */
	UserLoginInsInfo queryUserInsInfoByUserId(String userId, String busTypeId);
}
