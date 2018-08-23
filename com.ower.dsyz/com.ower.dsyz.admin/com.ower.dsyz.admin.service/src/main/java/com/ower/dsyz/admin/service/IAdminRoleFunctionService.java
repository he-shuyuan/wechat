/**
 * Copyright(c) Foresee Science & Technology Ltd. 
 */
package com.ower.dsyz.admin.service;

import java.util.List;

import com.ower.dsyz.admin.manual.dto.AdminRoleExtDTO;
import com.ower.dsyz.admin.manual.dto.AdminRoleFunDTO;
import com.ower.dsyz.admin.manual.dto.AdminRoleFunListDTO;
import com.ower.dsyz.admin.manual.dto.UserLoginFunInfo;

/**
 * <pre>
 * TODO。
 * </pre>
 *
 * @author HeShuyuan@foresee.com.cn
 * @date 2018年7月26日
 * @version 1.00.00
 * 
 *          <pre>
 * 修改记录 
 *    修改后版本:     修改人：  修改日期:     修改内容:
 *          </pre>
 */

public interface IAdminRoleFunctionService {

    /**
     * 查询一分配菜单
     * @param adminRoleFunDTO
     * @return List<AdminRoleFunDTO>
     */
    List<AdminRoleFunDTO> queryAdminRoleFunDTOTreeList(AdminRoleFunDTO adminRoleFunDTO);
    
    /**
     * 角色菜单授权
     * @param adminRoleFunListDTO
     * @return int
     */
    int addAuthFunctionList(AdminRoleFunListDTO adminRoleFunListDTO);

    
    /**
     * 菜单列表
     * @param adminRoleExtDTO
     * @return
     */
    UserLoginFunInfo queryUserRoleFunctionList(AdminRoleExtDTO adminRoleExtDTO);
}
