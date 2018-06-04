package com.ower.dsyz.admin.manual.dao;

import com.ower.dsyz.admin.manual.dto.AdminInstitutionDTO;
import com.ower.dsyz.common.core.page.PageQueryResult;
import com.ower.dsyz.common.core.page.PageRequestParam;

public interface AdminInstitutionExtMapper {
	/**
	 * 分页查找机构
	 * @param adminInstitutionDTO
	 * @return
	 */
	PageQueryResult<AdminInstitutionDTO> pageQueryAdminInstitutionDTOList(PageRequestParam param);
}