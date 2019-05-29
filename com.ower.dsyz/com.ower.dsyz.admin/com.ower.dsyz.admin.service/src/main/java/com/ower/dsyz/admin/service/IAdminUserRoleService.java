package com.ower.dsyz.admin.service;

import com.ower.dsyz.admin.manual.dto.AdminUserRoleDTO;
import com.ower.dsyz.admin.manual.dto.AdminUserRoleDistDTO;
import com.ower.dsyz.common.mybatis.database.page.PageQueryResult;
import com.ower.dsyz.common.mybatis.database.page.PageRequestParam;

public interface IAdminUserRoleService {

	  
		/**
		 * 查询用户角分配
		 * @param param
		 * @return
		 */
		PageQueryResult<AdminUserRoleDTO> pageQueryAdminUserRoleList(PageRequestParam param);
		
		
		
		
		 /**
	     * 用户分配角色
	     * @param param
	     * @return int
	     */
	   int distAdminUserRole(AdminUserRoleDistDTO adminUserRoleDistDTO);
	   
	   
	   
	   /**
	    * 用户回收角色
	    * @param param
	    * @return int
	    */
	  int recyleAdminUserRole(AdminUserRoleDistDTO adminUserRoleDistDTO);
}
