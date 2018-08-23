/**
 * Copyright(c) Foresee Science & Technology Ltd. 
 */
package com.ower.dsyz.admin.manual.dao;

import java.util.List;
import java.util.Map;

import com.ower.dsyz.admin.manual.dto.AdminFunctionExtDTO;
import com.ower.dsyz.admin.manual.dto.AdminRoleFunDTO;

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

public interface AdminRoleFunExtMapper {
    
    
    /**
     * 查询一分配菜单
     * @param adminRoleFunDTO
     * @return List<AdminRoleFunDTO>
     */
    List<AdminRoleFunDTO> queryAdminRoleFunDTOTreeList(AdminRoleFunDTO adminRoleFunDTO);
    
    /**
     * 移除角色授权
     * @param roleId
     * @return int
     */
    int delAdminRoleFunDTOList(String roleId);

    /**
     * 
     * @param map
     * @return
     */
	List<AdminFunctionExtDTO> queryFunctionByRoleList(Map<String, Object> map);
}
