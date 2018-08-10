/**
 * Copyright(c) Foresee Science & Technology Ltd. 
 */
package com.ower.dsyz.admin.service;

import java.util.List;
import com.ower.dsyz.admin.manual.dto.AdminUserDTO;
import com.ower.dsyz.admin.manual.dto.AdminUserDepDTO;
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

public interface IAdminUserService {

    /**
     * 分页查询用户
     * @param param
     * @return PageQueryResult<AdminFunctionDTO>
     */
    PageQueryResult<AdminUserDepDTO> pageQueryAdminUserDTOList(PageRequestParam param);
    
    /**
     * 修改用户
     * @param adminUserDTO
     * @return int
     */
    int updateAdminUser(AdminUserDepDTO adminUserDepDTO);
    
    /**
     * 新增用户
     * @param adminUserDepDTO
     * @return int
     */
    int addAdminUser(AdminUserDepDTO adminUserDepDTO);
    
    
    /**
     * 
     * 获取用户部门信息
     * @param adminUserDepDTO
     * @return AdminUserDepDTO
     */
    List<AdminUserDepDTO> queryUserDepInfoList(AdminUserDepDTO adminUserDepDTO);
    
    /**
     * 
     * 获取用户部门信息
     * @param adminUserDepDTO
     * @return AdminUserDepDTO
     */
    AdminUserDepDTO queryUserDepInfo(AdminUserDepDTO adminUserDepDTO);
    
    
    /**
     * 分页查询用户
     * @param param
     * @return List<AdminFunctionDTO>
     */
    List<AdminUserDTO> queryAdminUserDTOList(AdminUserDTO adminUserDTO);
}
