/**
 * Copyright(c) Foresee Science & Technology Ltd. 
 */
package com.ower.dsyz.admin.manual.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ower.dsyz.admin.auto.model.AdminRole;
import com.ower.dsyz.admin.manual.dto.AdminRoleDTO;
import com.ower.dsyz.common.mybatis.database.page.PageQueryResult;
import com.ower.dsyz.common.mybatis.database.page.PageRequestParam;

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

public interface AdminRoleExtMapper {

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
     * 根据userId和insId查询角色列表
     * @param userId
     * @param insId
     * @return
     */
	List<AdminRole> queryAdminRoleListByUserIdAndInsId(@Param("userId") String userId,@Param("insId") String insId);
}
