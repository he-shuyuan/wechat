/**
 * Copyright(c) Foresee Science & Technology Ltd. 
 */
package com.ower.dsyz.admin.manual.dao;

import java.util.List;
import com.ower.dsyz.admin.manual.dto.AdminDepartmentDTO;
import com.ower.dsyz.admin.manual.dto.DepPath;

/**
 * <pre>
 * TODO。
 * </pre>
 *
 * @author HeShuyuan@foresee.com.cn
 * @date 2018年7月2日
 * @version 1.00.00
 * 
 *          <pre>
 * 修改记录 
 *    修改后版本:     修改人：  修改日期:     修改内容:
 *          </pre>
 */

public interface AdminDepartmentExtMapper {

    /**
     * 查询部门列表
     * TODO。
     * @param adminDepartmentDTO
     * @return List<AdminDepartmentDTO>
     */
    List<AdminDepartmentDTO> queryDepartmentList(AdminDepartmentDTO adminDepartmentDTO);
    
    /**
     * 获取部门预处理路径
     * TODO。
     * @param depPath
     * @return List<DepPath>
     */
    List<DepPath> queryDepPathList(DepPath depPath);
}
