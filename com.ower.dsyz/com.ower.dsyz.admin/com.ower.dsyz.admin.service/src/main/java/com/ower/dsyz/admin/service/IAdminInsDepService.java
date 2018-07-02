/**
 * Copyright(c) Foresee Science & Technology Ltd. 
 */
package com.ower.dsyz.admin.service;

import com.ower.dsyz.admin.manual.dto.AdminInstitutionDTO;

/**
 * <pre>
 * TODO。
 * </pre>
 *
 * @author HeShuyuan@foresee.com.cn
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
}
