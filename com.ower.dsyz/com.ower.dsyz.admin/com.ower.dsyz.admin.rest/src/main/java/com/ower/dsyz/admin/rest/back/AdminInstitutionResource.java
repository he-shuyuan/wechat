package com.ower.dsyz.admin.rest.back;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ower.dsyz.admin.manual.dto.AdminInstitutionDTO;
import com.ower.dsyz.admin.service.IAdminInstitutionService;
import com.ower.dsyz.common.core.page.PageQueryResult;
import com.ower.dsyz.common.core.page.PageRequestParam;
import com.ower.dsyz.common.core.response.CustomResponse;

@RestController
@RequestMapping("/back/adminInstitutionService/")
public class AdminInstitutionResource {

	@Resource
	IAdminInstitutionService  adminInstitutionService;
	
	@RequestMapping("pageQueryAdminInstitutionDTOList")
	public CustomResponse<PageQueryResult<AdminInstitutionDTO>> pageQueryAdminInstitutionDTOList(@RequestBody PageRequestParam param){
		return CustomResponse.success(adminInstitutionService.pageQueryAdminInstitutionDTOList(param));
	}
}
