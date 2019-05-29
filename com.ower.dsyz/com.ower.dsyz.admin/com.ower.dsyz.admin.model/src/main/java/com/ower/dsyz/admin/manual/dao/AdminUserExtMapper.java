package com.ower.dsyz.admin.manual.dao;
import java.util.List;
import com.ower.dsyz.admin.manual.dto.AdminUserDTO;
import com.ower.dsyz.admin.manual.dto.AdminUserDepDTO;
import com.ower.dsyz.common.mybatis.database.page.PageQueryResult;
import com.ower.dsyz.common.mybatis.database.page.PageRequestParam;


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
    
    
    /**
     * 
     * 获取用户部门信息
     * @param adminUserDepDTO
     * @return AdminUserDepDTO
     */
    List<AdminUserDepDTO> queryUserDepInfoList(AdminUserDepDTO adminUserDepDTO);
}