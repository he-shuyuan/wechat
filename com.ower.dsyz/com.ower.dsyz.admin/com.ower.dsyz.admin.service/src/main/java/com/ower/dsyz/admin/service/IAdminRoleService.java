/**
 * Copyright(c) Foresee Science & Technology Ltd. 
 */
package com.ower.dsyz.admin.service;

import java.util.List;
import com.ower.dsyz.admin.manual.dto.AdminRoleDTO;
import com.ower.dsyz.common.core.page.PageQueryResult;
import com.ower.dsyz.common.core.page.PageRequestParam;

/**
 * <pre>
 * TODO。
 * </pre>
 *
 * @author HeShuyuan@foresee.com.cn
 * @date 2018年7月25日
 * @version 1.00.00
 * 
 *          <pre>
 * 修改记录 
 *    修改后版本:     修改人：  修改日期:     修改内容:
 *          </pre>
 */

public interface IAdminRoleService {
    /**
     * 条件搜索角色
     * TODO。
     * @param adminRoleDTO
     * @return List<AdminRoleDTO>
     */
    List<AdminRoleDTO> queryAdminRoleDTOList(AdminRoleDTO adminRoleDTO);
    
    /**
     * 分页查询角色
     * @param param
     * @return PageQueryResult<AdminFunctionDTO>
     */
    PageQueryResult<AdminRoleDTO> pageQueryAdminRoleDTOList(PageRequestParam param);
    
    /**
     * 修改角色
     * TODO。
     * @param adminRoleDTO
     * @return int
     */
    int updateAdminRole(AdminRoleDTO adminRoleDTO);
    
    /**
     * 新增角色
     * TODO。
     * @param adminRoleDTO
     * @return int
     */
    int addAdminRole(AdminRoleDTO adminRoleDTO);
    
    /**
     * 删除角色
     * @param adminRoleDTO
     * @return int
     */
    int delAdminRole(AdminRoleDTO adminRoleDTO);
    
    /**
     * 通过id获取角色
     * @param id
     * @return AdminRoleDTO
     */
    AdminRoleDTO queryAdminRoleById(String id);
}
