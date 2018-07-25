/**
 * Copyright(c) Foresee Science & Technology Ltd. 
 */
package com.ower.dsyz.admin.service;

import java.util.List;
import com.ower.dsyz.admin.auto.model.AdminFunction;
import com.ower.dsyz.admin.manual.dto.AdminFunctionDTO;
import com.ower.dsyz.common.core.page.PageQueryResult;
import com.ower.dsyz.common.core.page.PageRequestParam;

/**
 * <pre>
 * TODO。
 * </pre>
 *
 * @author HeShuyuan@foresee.com.cn
 * @date 2018年7月24日
 * @version 1.00.00
 * 
 *          <pre>
 * 修改记录 
 *    修改后版本:     修改人：  修改日期:     修改内容:
 *          </pre>
 */

public interface IAdminFunctionService {
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
    
    /**
     * 通过id获取菜单
     * @param functionId
     * @return AdminFunctionDTO
     */
    AdminFunctionDTO queryAdminFunctionById(AdminFunctionDTO adminFunctionDTO);
    
    /**
     * 新增菜单
     * @param AdminFunctionDTO
     * @return AdminFunction
     */
    AdminFunction addAdminFunction(AdminFunctionDTO adminFunctionDTO);
    
    /**
     * 修改菜单
     * @param AdminFunctionDTO
     * @return int
     */
    int updateAdminFunction(AdminFunctionDTO adminFunctionDTO);
    
    
    /**
     * 删除菜单
     * @param AdminFunctionDTO
     * @return int
     */
    int delAdminFunction(AdminFunctionDTO adminFunctionDTO);
}
