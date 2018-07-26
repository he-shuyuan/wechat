/**
 * Copyright(c) Foresee Science & Technology Ltd. 
 */
package com.ower.dsyz.admin.service;

import java.util.List;
import com.ower.dsyz.admin.manual.dto.AdminDepUserDTO;

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

public interface IAdminDepUserService {

    
    /**
     * 条件查询部门人员
     * TODO。
     * @param adminDepUserDTO
     * @return List<AdminDepUserDTO>
     */
    List<AdminDepUserDTO> queryAdminDepUserDTOList(AdminDepUserDTO adminDepUserDTO);
    
    
    /**
     * 更新部门人员
     * TODO。
     * @param adminDepUserDTO
     * @return int
     */
    int updateAdminDepUser(AdminDepUserDTO adminDepUserDTO);
    
    /**
     * 新建部门人员表
     * TODO。
     * @param adminDepUserDTO
     * @return int
     */
    int addAdminDepUser(AdminDepUserDTO adminDepUserDTO);
    
    
    /**
     * 移除用户
     * @param adminDepUserDTO
     * @return int
     */
    int removeAdminDepUser(AdminDepUserDTO adminDepUserDTO);
}
