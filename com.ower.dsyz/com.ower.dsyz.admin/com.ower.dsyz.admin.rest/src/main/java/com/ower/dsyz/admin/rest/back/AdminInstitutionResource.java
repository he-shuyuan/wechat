package com.ower.dsyz.admin.rest.back;

import javax.annotation.Resource;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ower.dsyz.admin.auto.model.AdminInstitution;
import com.ower.dsyz.admin.manual.dto.AdminInstitutionDTO;
import com.ower.dsyz.admin.manual.dto.UserLoginInsInfo;
import com.ower.dsyz.admin.service.IAdminInsDepService;
import com.ower.dsyz.admin.service.IAdminInstitutionService;
import com.ower.dsyz.common.core.annotation.EnableValid;
import com.ower.dsyz.common.core.page.PageQueryResult;
import com.ower.dsyz.common.core.page.PageRequestParam;
import com.ower.dsyz.common.core.request.CustomRequestParam;
import com.ower.dsyz.common.core.response.CustomResponse;
import com.ower.dsyz.common.core.util.ParamCheckUtil;

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
	 * 添加机构
	 * @param adminInstitutionDTO
	 * @return CustomResponse<?>
	 */
	@RequestMapping("addInsAndDep")
	public CustomResponse<?> addInsAndDep(@RequestBody @EnableValid AdminInstitutionDTO adminInstitutionDTO){
	    adminInsDepService.addInsAndDep(adminInstitutionDTO);
	    return CustomResponse.success("新增成功");
	}
	
	/**
     * 修改机构
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
    /**
     * 根据id获取信息
     * TODO。
     * @param param
     * @return CustomResponse<AdminInstitution>
     */
    @RequestMapping("queryAdminInstitutionById")
    public CustomResponse<AdminInstitution> queryAdminInstitutionById(@RequestBody CustomRequestParam param){
        ParamCheckUtil.checkEmpty(param.getString("insId"));
        return CustomResponse.success(adminInstitutionService.queryAdminInstitutionById(param.getString("insId")));
    }
    
    
    /**
     * 获取用户的机构信息
     * @return
     */
    @RequestMapping("queryUserInsInfoByUserId")
    public CustomResponse<UserLoginInsInfo> queryUserInsInfoByUserId(@RequestBody CustomRequestParam param){
    	  ParamCheckUtil.checkEmpty(param.getString("userId"),"用户id不能为空");
    	  ParamCheckUtil.checkEmpty(param.getString("busTypeId"),"产品线id不能为空");
    	  return CustomResponse.success(adminInsDepService.queryUserInsInfoByUserId(param.getString("userId"),param.getString("busTypeId")));
    }
}
