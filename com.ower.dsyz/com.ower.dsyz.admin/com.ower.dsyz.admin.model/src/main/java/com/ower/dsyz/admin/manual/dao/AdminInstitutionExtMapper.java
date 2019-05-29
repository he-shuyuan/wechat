package com.ower.dsyz.admin.manual.dao;

import java.util.List;
import com.ower.dsyz.admin.manual.dto.AdminInstitutionDTO;
import com.ower.dsyz.common.mybatis.database.page.PageQueryResult;
import com.ower.dsyz.common.mybatis.database.page.PageRequestParam;


public interface AdminInstitutionExtMapper {
	/**
	 * 分页查找机构
	 * @param adminInstitutionDTO
	 * @return
	 */
	PageQueryResult<AdminInstitutionDTO> pageQueryAdminInstitutionDTOList(PageRequestParam param);
	
	 /**
     * 条件查询企业
     * TODO。
     * @param addInstitutionDTO
     * @return List<AdminInstitutionDTO>
     */
    List<AdminInstitutionDTO> queryAdminInstitutionDTOList(AdminInstitutionDTO addInstitutionDTO);
}