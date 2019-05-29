package com.ower.dsyz.admin.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ower.dsyz.admin.auto.dao.AdminUserRoleMapper;
import com.ower.dsyz.admin.auto.model.AdminUserRole;
import com.ower.dsyz.admin.manual.dao.AdminUserRoleExtMapper;
import com.ower.dsyz.admin.manual.dto.AdminUserRoleDTO;
import com.ower.dsyz.admin.manual.dto.AdminUserRoleDistDTO;
import com.ower.dsyz.admin.service.IAdminUserRoleService;
import com.ower.dsyz.common.core.util.IDUtil;
import com.ower.dsyz.common.mybatis.database.page.PageQueryResult;
import com.ower.dsyz.common.mybatis.database.page.PageRequestParam;

@Service
public class AdminUserRoleServiceImpl implements IAdminUserRoleService {

	@Autowired
	AdminUserRoleExtMapper adminUserRoleExtMapper;
	
	@Autowired
	AdminUserRoleMapper adminUserRoleMapper;
	
	@Override
	public PageQueryResult<AdminUserRoleDTO> pageQueryAdminUserRoleList(PageRequestParam param) {
		
		return adminUserRoleExtMapper.pageQueryAdminUserRoleList(param);
	}

	
	@Override
	@Transactional
	public int distAdminUserRole(AdminUserRoleDistDTO adminUserRoleDistDTO) {
		AdminUserRole adminUserRole = new AdminUserRole();
		adminUserRole.setUserId(adminUserRoleDistDTO.getUserId());
		if(adminUserRoleDistDTO.getRoleIdList() == null){
			return 0;
		}
		for(String roleId:adminUserRoleDistDTO.getRoleIdList()){
			adminUserRole.setId(IDUtil.getUUID());
			adminUserRole.setRoleId(roleId);
			adminUserRoleMapper.insertSelective(adminUserRole);
		}
		return adminUserRoleDistDTO.getRoleIdList().size();
	}

	@Override
	public int recyleAdminUserRole(AdminUserRoleDistDTO adminUserRoleDistDTO) {
		
		return adminUserRoleExtMapper.recyleAdminUserRole(adminUserRoleDistDTO);
	}

}
