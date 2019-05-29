/**
 * Copyright(c) Foresee Science & Technology Ltd. 
 */
package com.ower.dsyz.admin.service;

import com.ower.dsyz.admin.manual.dto.AdminInsRoleDTO;
import com.ower.dsyz.admin.manual.dto.AdminInsRoleDistDTO;
import com.ower.dsyz.common.mybatis.database.page.PageQueryResult;
import com.ower.dsyz.common.mybatis.database.page.PageRequestParam;

/**
 * <pre>
 * TODO。
 * </pre>
 *
 * @author HeShuyuan@foresee.com.cn
 * @date 2018年7月30日
 * @version 1.00.00
 * 
 *          <pre>
 * 修改记录 
 *    修改后版本:     修改人：  修改日期:     修改内容:
 *          </pre>
 */

public interface IAdminInsRoleService {

    /**
     * 机构角色分页查询
     * @param param
     * @return PageQueryResult<AdminInsRoleDTO>
     */
    PageQueryResult<AdminInsRoleDTO> pageQueryAdminInsRoleList(PageRequestParam param);
    
    
    /**
     * 删除机构角色关系
     * @param adminInsRoleDistDTO
     * @return int
     */
    int batchDelAdminInsRole(AdminInsRoleDistDTO adminInsRoleDistDTO);
    
    
    
    /**
     * 新增机构角色关系
     * @param adminInsRoleDistDTO
     * @return int
     */
    int disAdminInsRole(AdminInsRoleDistDTO adminInsRoleDistDTO);
}
