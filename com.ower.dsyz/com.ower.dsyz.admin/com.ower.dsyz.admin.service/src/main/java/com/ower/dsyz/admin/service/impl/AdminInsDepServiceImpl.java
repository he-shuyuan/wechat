/**
 * Copyright(c) Foresee Science & Technology Ltd. 
 */
package com.ower.dsyz.admin.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.ower.dsyz.admin.manual.constant.AdminConstant;
import com.ower.dsyz.admin.manual.dto.AdminDepartmentDTO;
import com.ower.dsyz.admin.manual.dto.AdminInstitutionDTO;
import com.ower.dsyz.admin.service.IAdminDepartmentService;
import com.ower.dsyz.admin.service.IAdminInsDepService;
import com.ower.dsyz.admin.service.IAdminInstitutionService;


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
@Service
public class AdminInsDepServiceImpl implements IAdminInsDepService {

    @Autowired
    IAdminDepartmentService adminDepartmentService;
    
    @Autowired
    IAdminInstitutionService adminInstitutionService;
    
    @Override
    @Transactional
    public void addInsAndDep(AdminInstitutionDTO adminInstitution) {
        //新增机构
        String insId = adminInstitutionService.addInstitution(adminInstitution);
        //新增顶级部门
        AdminDepartmentDTO adminDepartment = new AdminDepartmentDTO();
        adminDepartment.setInsId(insId);
        adminDepartment.setDepId(insId);
        adminDepartment.setParentDepId(AdminConstant.DepartmentConstant.DEFAULT_PARENT_DEP_ID);
        adminDepartment.setDepName(adminInstitution.getInsName());
        adminDepartment.setBusTypeId(adminInstitution.getBusTypeId());
        adminDepartmentService.addDepartment(adminDepartment);
    }

}
