package com.ower.dsyz.admin.manual.dao;

import java.util.List;
import com.ower.dsyz.admin.manual.dto.AdminDepUserDTO;

public interface AdminDepUserExtMapper {
  
    /**
     * 条件查询部门人员
     * TODO。
     * @param adminDepUserDTO
     * @return List<AdminDepUserDTO>
     */
    List<AdminDepUserDTO> queryAdminDepUserDTOList(AdminDepUserDTO adminDepUserDTO);
}