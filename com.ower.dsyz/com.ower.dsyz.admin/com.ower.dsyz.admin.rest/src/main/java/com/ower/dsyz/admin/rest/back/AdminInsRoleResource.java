package com.ower.dsyz.admin.rest.back;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ower.dsyz.admin.manual.dto.AdminUserRoleDTO;
import com.ower.dsyz.admin.manual.dto.AdminUserRoleDistDTO;
import com.ower.dsyz.common.core.annotation.EnableValid;
import com.ower.dsyz.common.core.page.PageQueryResult;
import com.ower.dsyz.common.core.page.PageRequestParam;
import com.ower.dsyz.common.core.response.CustomResponse;

@RestController
@RequestMapping("/back/adminInsRoleService/")
public class AdminInsRoleResource {

	
    /**
     * 分页获取机构角色信息
     * @param param
     * @return CustomResponse<?>
     */
    @RequestMapping("pageQueryAdminInsRoleList")
    public CustomResponse<PageQueryResult<AdminUserRoleDTO>> pageQueryAdminInsRoleList(@RequestBody PageRequestParam param){
        return null;
    }
    
    /**
     * 用户分配角色
     * @param param
     * @return CustomResponse<?>
     */
    @RequestMapping("distAdminUserRole")
    public CustomResponse<Integer> distAdminUserRole(@RequestBody @EnableValid AdminUserRoleDistDTO param){
        return null;
    }
    
    /**
     * 回收用户角色
     * @param param
     * @return CustomResponse<?>
     */
    @RequestMapping("recyleAdminUserRole")
    public CustomResponse<Integer> recyleAdminUserRole(@RequestBody @EnableValid AdminUserRoleDistDTO param){
        return  null;
    }
}
