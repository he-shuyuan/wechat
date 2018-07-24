package com.ower.dsyz.admin.manual.dao;

import java.util.List;
import com.ower.dsyz.admin.manual.dto.AdminFunctionDTO;
import com.ower.dsyz.common.core.page.PageQueryResult;
import com.ower.dsyz.common.core.page.PageRequestParam;

public interface AdminFunctionExtMapper {
	
    /**
     * 分页查询菜单
     * @param param
     * @return PageQueryResult<AdminFunctionDTO>
     */
    PageQueryResult<AdminFunctionDTO> pageQueryAdminFunctionDTOList(PageRequestParam param);
    
    /**
     * 条件查询菜单
     * @param adminFunctionDTO
     * @return List<AdminFunctionDTO>
     */
    List<AdminFunctionDTO> queryAdminFunctionList(AdminFunctionDTO adminFunctionDTO);
}