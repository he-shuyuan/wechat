package com.ower.dsyz.admin.rest.back;

import javax.annotation.Resource;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ower.dsyz.admin.auto.model.AdminInstitution;
import com.ower.dsyz.admin.manual.dto.AdminInstitutionDTO;
import com.ower.dsyz.admin.service.IAdminInsDepService;
import com.ower.dsyz.admin.service.IAdminInstitutionService;
import com.ower.dsyz.common.core.annotation.EnableValid;
import com.ower.dsyz.common.core.page.PageQueryResult;
import com.ower.dsyz.common.core.page.PageRequestParam;
import com.ower.dsyz.common.core.response.CustomResponse;

@RestController
@RequestMapping("/back/adminInstitutionService/")
public class AdminInstitutionResource {

	@Resource
	IAdminInstitutionService  adminInstitutionService;
	
	@Resource
	IAdminInsDepService adminInsDepService;
	
	/**
	 * 
	 * 查询部门列表
	 * @param param
	 * @return CustomResponse<PageQueryResult<AdminInstitutionDTO>>
	 */
	@RequestMapping("pageQueryAdminInstitutionDTOList")
	public CustomResponse<PageQueryResult<AdminInstitutionDTO>> pageQueryAdminInstitutionDTOList(@RequestBody PageRequestParam param){
		return CustomResponse.success(adminInstitutionService.pageQueryAdminInstitutionDTOList(param));
	}
	
	/**
	 * 添加部门
	 * @param adminInstitutionDTO
	 * @return CustomResponse<?>
	 */
	@RequestMapping("addInsAndDep")
	public CustomResponse<?> addInsAndDep(@RequestBody @EnableValid AdminInstitutionDTO adminInstitutionDTO){
	    adminInsDepService.addInsAndDep(adminInstitutionDTO);
	    return CustomResponse.success("新增成功");
	}
	
	/**
     * 修改部门
     * @param adminInstitutionDTO
     * @return CustomResponse<?>
     */
    @RequestMapping("updateAdminInstitution")
	public CustomResponse<?> updateAdminInstitution(@RequestBody AdminInstitutionDTO adminInstitutionDTO){
	    AdminInstitution adminInstitution = new AdminInstitution();
	    BeanUtils.copyProperties(adminInstitutionDTO, adminInstitution);
	    adminInstitutionService.updataInstitution(adminInstitution);
	    return CustomResponse.success("修改成功");
	}
}
