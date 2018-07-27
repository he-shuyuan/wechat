package com.ower.dsyz.admin.manual.dao;

import com.ower.dsyz.admin.manual.dto.AdminUserRoleDTO;
import com.ower.dsyz.common.core.page.PageQueryResult;
import com.ower.dsyz.common.core.page.PageRequestParam;

public interface AdminUserRoleExtMapper {
  
	/**
	 * 查询用户角分配
	 * @param param
	 * @return
	 */
	PageQueryResult<AdminUserRoleDTO> pageQueryAdminUserRoleList(PageRequestParam param);
}