package com.ower.dsyz.admin.rest.back;

import javax.annotation.Resource;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ower.dsyz.admin.manual.dto.AdminInsRoleDTO;
import com.ower.dsyz.admin.manual.dto.AdminInsRoleDistDTO;
import com.ower.dsyz.common.core.annotation.EnableValid;
import com.ower.dsyz.common.core.response.CustomResponse;
import com.ower.dsyz.common.mybatis.database.page.PageQueryResult;
import com.ower.dsyz.common.mybatis.database.page.PageRequestParam;
import com.ower.dsyz.admin.service.IAdminInsRoleService;

@RestController
@RequestMapping("/back/adminInsRoleService/")
public class AdminInsRoleResource {

	@Resource
	IAdminInsRoleService  adminInsRoleService;
    /**
     * 分页获取机构角色信息
     * @param param
     * @return CustomResponse<?>
     */
    @RequestMapping("pageQueryAdminInsRoleList")
    public CustomResponse<PageQueryResult<AdminInsRoleDTO>> pageQueryAdminInsRoleList(@RequestBody PageRequestParam param){
        return CustomResponse.success(adminInsRoleService.pageQueryAdminInsRoleList(param));
    }
    
    /**
     * 用户分配角色
     * @param param
     * @return CustomResponse<?>
     */
    @RequestMapping("distAdminInsRole")
    public CustomResponse<Integer> distAdminInsRole(@RequestBody @EnableValid AdminInsRoleDistDTO adminInsRoleDistDTO){
        return CustomResponse.success(adminInsRoleService.disAdminInsRole(adminInsRoleDistDTO));
    }
    
    /**
     * 回收用户角色
     * @param param
     * @return CustomResponse<?>
     */
    @RequestMapping("recyleAdminInsRole")
    public CustomResponse<Integer> recyleAdminInsRole(@RequestBody @EnableValid AdminInsRoleDistDTO adminInsRoleDistDTO){
        return CustomResponse.success(adminInsRoleService.batchDelAdminInsRole(adminInsRoleDistDTO));
    }
}
