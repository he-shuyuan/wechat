package com.ower.dsyz.admin.manual.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ower.dsyz.admin.manual.dto.AdminDepUserDTO;
import com.ower.dsyz.admin.manual.dto.AdminDepartmentDTO;

public interface AdminDepUserExtMapper {
  
    /**
     * 条件查询部门人员
     * TODO。
     * @param adminDepUserDTO
     * @return List<AdminDepUserDTO>
     */
    List<AdminDepUserDTO> queryAdminDepUserDTOList(AdminDepUserDTO adminDepUserDTO);
    
    
    /**
     * 获取人员所在列表
     * @param userId
     * @param busTypeId
     * @return
     */
    List<AdminDepartmentDTO> queryAdminUserInsInfoList(@Param("userId")String userId,@Param("busTypeId")String busTypeId);
}