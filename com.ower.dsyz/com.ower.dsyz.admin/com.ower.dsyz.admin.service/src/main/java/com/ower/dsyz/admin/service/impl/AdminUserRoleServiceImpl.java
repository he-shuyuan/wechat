package com.ower.dsyz.admin.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ower.dsyz.admin.manual.dao.AdminUserRoleExtMapper;
import com.ower.dsyz.admin.manual.dto.AdminUserRoleDTO;
import com.ower.dsyz.admin.service.IAdminUserRoleService;
import com.ower.dsyz.common.core.page.PageQueryResult;
import com.ower.dsyz.common.core.page.PageRequestParam;

@Service
public class AdminUserRoleServiceImpl implements IAdminUserRoleService {

	@Autowired
	AdminUserRoleExtMapper adminUserRoleExtMapper;
	
	@Override
	public PageQueryResult<AdminUserRoleDTO> pageQueryAdminUserRoleList(PageRequestParam param) {
		
		return adminUserRoleExtMapper.pageQueryAdminUserRoleList(param);
	}

}
