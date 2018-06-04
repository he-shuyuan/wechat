package com.ower.dsyz.admin.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ower.dsyz.admin.manual.dao.AdminInstitutionExtMapper;
import com.ower.dsyz.admin.manual.dto.AdminInstitutionDTO;
import com.ower.dsyz.admin.service.IAdminInstitutionService;
import com.ower.dsyz.common.core.page.PageQueryResult;
import com.ower.dsyz.common.core.page.PageRequestParam;
@Service
public class AdminInstitutionServiceImpl implements IAdminInstitutionService{

	@Autowired
	AdminInstitutionExtMapper adminInstitutionExtMapper;
	
	@Override
	public PageQueryResult<AdminInstitutionDTO> pageQueryAdminInstitutionDTOList(PageRequestParam param) {
		return adminInstitutionExtMapper.pageQueryAdminInstitutionDTOList(param);
	}

}
