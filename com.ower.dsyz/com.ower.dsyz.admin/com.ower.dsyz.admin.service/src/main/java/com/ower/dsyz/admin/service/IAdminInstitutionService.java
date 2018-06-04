package com.ower.dsyz.admin.service;

import com.ower.dsyz.admin.manual.dto.AdminInstitutionDTO;
import com.ower.dsyz.common.core.page.PageQueryResult;
import com.ower.dsyz.common.core.page.PageRequestParam;

/**
 * 机构增删改查
 * @author 独树一帜
 *
 */
public interface IAdminInstitutionService {
   
	/**
	 * 分页查找机构
	 * @param adminInstitutionDTO
	 * @return
	 */
	PageQueryResult<AdminInstitutionDTO> pageQueryAdminInstitutionDTOList(PageRequestParam param);
}
