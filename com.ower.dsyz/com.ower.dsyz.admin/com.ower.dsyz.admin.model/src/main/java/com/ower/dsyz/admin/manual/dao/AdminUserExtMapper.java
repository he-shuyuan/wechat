package com.ower.dsyz.admin.manual.dao;
import java.util.List;
import com.ower.dsyz.admin.manual.dto.AdminUserDTO;
import com.ower.dsyz.admin.manual.dto.AdminUserDepDTO;
import com.ower.dsyz.common.core.page.PageQueryResult;
import com.ower.dsyz.common.core.page.PageRequestParam;

public interface AdminUserExtMapper {

    /**
     * 分页查询用户
     * @param param
     * @return PageQueryResult<AdminFunctionDTO>
     */
    PageQueryResult<AdminUserDepDTO> pageQueryAdminUserDTOList(PageRequestParam param);
    
    
    /**
     * 分页查询用户
     * @param param
     * @return List<AdminFunctionDTO>
     */
    List<AdminUserDTO> queryAdminUserDTOList(AdminUserDTO adminUserDTO);
}