/**
 * Copyright(c) Foresee Science & Technology Ltd. 
 */
package com.ower.dsyz.admin.rest.back;

import java.util.List;
import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ower.dsyz.admin.auto.model.AdminDepartment;
import com.ower.dsyz.admin.manual.dto.AdminDepTreeDTO;
import com.ower.dsyz.admin.manual.dto.AdminDepartmentDTO;
import com.ower.dsyz.admin.service.IAdminDepartmentService;
import com.ower.dsyz.common.core.response.CustomResponse;

/**
 * <pre>
 * TODO。
 * </pre>
 *
 * @author HeShuyuan@foresee.com.cn
 * @date 2018年7月25日
 * @version 1.00.00
 * 
 *          <pre>
 * 修改记录 
 *    修改后版本:     修改人：  修改日期:     修改内容:
 *          </pre>
 */
@RestController
@RequestMapping("/back/adminDepartmentService/")
public class AdminDepartmentResource {

    @Resource
    IAdminDepartmentService adminDepartmentService;
    
    /**
     * 部门树列表
     * TODO。
     * @param adminDepTreeDTO
     * @return CustomResponse<List<AdminDepTreeDTO>>
     */
    @RequestMapping("queryAdminDepTreeDTOList")
    public CustomResponse<List<AdminDepTreeDTO>> queryAdminDepTreeDTOList(@RequestBody AdminDepTreeDTO adminDepTreeDTO){
        /*if(StringUtils.isNotBlank(adminDepTreeDTO.getParentDepId())){
            adminDepTreeDTO.setDepId(null);
        }
       return  CustomResponse.success(adminDepartmentService.queryDepTreeList(adminDepTreeDTO));*/
    	return CustomResponse.success(null);
    }
    
    
    /**
     * 新增部门
     * TODO。
     * @param adminDepTreeDTO
     * @return CustomResponse<AdminDepartment>
     */
    @RequestMapping("addAdminDepartment")
    public CustomResponse<AdminDepartment> addAdminDepartment(@RequestBody AdminDepartmentDTO adminDepartment){
        return  CustomResponse.success(adminDepartmentService.addDepartment(adminDepartment));
    }
    
    
    /**
     * 修改部门
     * TODO。
     * @param adminDepTreeDTO
     * @return CustomResponse<AdminDepartment>
     */
    @RequestMapping("updateDepartment")
    public CustomResponse<Integer> updataDepartment(@RequestBody AdminDepartmentDTO adminDepartment){
        return  CustomResponse.success(adminDepartmentService.updataDepartment(adminDepartment));
    }
    
    
    /**
     * 删除部门
     * TODO。
     * @param adminDepTreeDTO
     * @return CustomResponse<AdminDepartment>
     */
    @RequestMapping("delDepartment")
    public CustomResponse<Integer> delDepartment(@RequestBody AdminDepartment adminDepartment){
        return  CustomResponse.success(adminDepartmentService.delDepartment(adminDepartment));
    }
    
    /**
     * 获取部门信息
     * @param adminDepartmentDTO
     * @return CustomResponse<AdminDepartmentDTO>
     */
    @RequestMapping("queryAdmimDepartmentById")
    public CustomResponse<AdminDepartmentDTO> queryAdmimDepartmentById(@RequestBody AdminDepartmentDTO adminDepartmentDTO){
        return  CustomResponse.success(adminDepartmentService.queryAdmimDepartmentById(adminDepartmentDTO));
    }
}
