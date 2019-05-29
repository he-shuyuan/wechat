/**
 * Copyright(c) Foresee Science & Technology Ltd. 
 */
package com.ower.dsyz.admin.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ower.dsyz.admin.auto.dao.AdminInsRoleMapper;
import com.ower.dsyz.admin.auto.model.AdminInsRole;
import com.ower.dsyz.admin.manual.dao.AdminInsRoleExtMapper;
import com.ower.dsyz.admin.manual.dto.AdminInsRoleDTO;
import com.ower.dsyz.admin.manual.dto.AdminInsRoleDistDTO;
import com.ower.dsyz.admin.service.IAdminInsRoleService;
import com.ower.dsyz.common.core.util.IDUtil;
import com.ower.dsyz.common.mybatis.database.page.PageQueryResult;
import com.ower.dsyz.common.mybatis.database.page.PageRequestParam;


/**
 * <pre>
 * TODO。
 * </pre>
 *
 * @author HeShuyuan@foresee.com.cn
 * @date 2018年7月30日
 * @version 1.00.00
 * 
 *          <pre>
 * 修改记录 
 *    修改后版本:     修改人：  修改日期:     修改内容:
 *          </pre>
 */
@Service
public class AdminInsRoleServiceImpl implements IAdminInsRoleService {

    @Autowired
    private AdminInsRoleExtMapper adminInsRoleExtMapper;
    
    @Autowired
    private AdminInsRoleMapper adminInsRoleMapper;
    
    @Override
    public PageQueryResult<AdminInsRoleDTO> pageQueryAdminInsRoleList(PageRequestParam param) {

        return adminInsRoleExtMapper.pageQueryAdminInsRoleList(param);
    }

    @Override
    public int batchDelAdminInsRole(AdminInsRoleDistDTO adminInsRoleDistDTO) {

        return adminInsRoleExtMapper.batchDelAdminInsRole(adminInsRoleDistDTO);
    }

    @Override
    public int disAdminInsRole(AdminInsRoleDistDTO adminInsRoleDistDTO) {
        AdminInsRole adminInsRole = new AdminInsRole();
        adminInsRole.setInsId(adminInsRoleDistDTO.getInsId());
        if(adminInsRoleDistDTO.getRoleIdList()==null)
        {
         return 0;   
        }
        for(String id:adminInsRoleDistDTO.getRoleIdList()){
            adminInsRole.setInsRoleId(IDUtil.getUUID());
            adminInsRole.setRoleId(id);
            adminInsRoleMapper.insertSelective(adminInsRole);
        }
        return adminInsRoleDistDTO.getRoleIdList().size();
    }

}
