/**
 * Copyright(c) Foresee Science & Technology Ltd. 
 */
package com.ower.dsyz.admin.rest.back;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ower.dsyz.admin.manual.dto.AdminUserRoleDTO;
import com.ower.dsyz.admin.manual.dto.AdminUserRoleDistDTO;
import com.ower.dsyz.admin.service.IAdminUserRoleService;
import com.ower.dsyz.common.core.annotation.EnableValid;
import com.ower.dsyz.common.core.page.PageQueryResult;
import com.ower.dsyz.common.core.page.PageRequestParam;
import com.ower.dsyz.common.core.response.CustomResponse;

/**
 * <pre>
 * TODO。
 * </pre>
 *
 * @author HeShuyuan@foresee.com.cn
 * @date 2018年7月26日
 * @version 1.00.00
 * 
 *          <pre>
 * 修改记录 
 *    修改后版本:     修改人：  修改日期:     修改内容:
 *          </pre>
 */
@RestController
@RequestMapping("/back/adminUserRoleService/")
public class AdminUserRoleResource {
    
	@Resource
	IAdminUserRoleService adminUserRoleService;
	
    /**
     * 分页获取用户角色信息
     * @param param
     * @return CustomResponse<?>
     */
    @RequestMapping("pageQueryAdminUserRoleList")
    public CustomResponse<PageQueryResult<AdminUserRoleDTO>> pageQueryAdminUserRoleList(@RequestBody PageRequestParam param){
        return CustomResponse.success(adminUserRoleService.pageQueryAdminUserRoleList(param));
    }
    
    /**
     * 用户分配角色
     * @param param
     * @return CustomResponse<?>
     */
    @RequestMapping("distAdminUserRole")
    public CustomResponse<?> distAdminUserRole(@RequestBody @EnableValid AdminUserRoleDistDTO param){
        return null;
    }
    
    /**
     * 回收用户角色
     * @param param
     * @return CustomResponse<?>
     */
    @RequestMapping("recyleAdminUserRole")
    public CustomResponse<?> recyleAdminUserRole(@RequestBody @EnableValid AdminUserRoleDistDTO param){
        return null;
    }
}
