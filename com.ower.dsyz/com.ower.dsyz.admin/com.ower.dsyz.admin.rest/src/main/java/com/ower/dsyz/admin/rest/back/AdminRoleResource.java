/**
 * Copyright(c) Foresee Science & Technology Ltd. 
 */
package com.ower.dsyz.admin.rest.back;

import java.util.List;
import javax.annotation.Resource;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ower.dsyz.admin.manual.dto.AdminRoleDTO;
import com.ower.dsyz.admin.service.IAdminRoleService;
import com.ower.dsyz.common.core.response.CustomResponse;
import com.ower.dsyz.common.core.valid.util.ParamCheckUtil;
import com.ower.dsyz.common.mybatis.database.page.PageQueryResult;
import com.ower.dsyz.common.mybatis.database.page.PageRequestParam;

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
@RequestMapping("/back/adminRoleService")
public class AdminRoleResource {

    @Resource
    private IAdminRoleService adminRoleService;
    
    /**
     * 条件搜索角色
     * @param adminRoleDTO
     * @return List<AdminRoleDTO>
     */
    @RequestMapping("queryAdminRoleDTOList")
    public CustomResponse<List<AdminRoleDTO>> queryAdminRoleDTOList(@RequestBody AdminRoleDTO adminRoleDTO) {

        return CustomResponse.success(adminRoleService.queryAdminRoleDTOList(adminRoleDTO));
    }
    /**
     * 分页搜索角色
     * @param param
     * @return PageQueryResult<AdminRoleDTO>
     */
    @RequestMapping("pageQueryAdminRoleDTOList")
    public CustomResponse<PageQueryResult<AdminRoleDTO>> pageQueryAdminRoleDTOList(@RequestBody PageRequestParam param) {

        return CustomResponse.success(adminRoleService.pageQueryAdminRoleDTOList(param));
    }

    /**
     * 更新角色
     * TODO。
     * @param adminRoleDTO
     * @return CustomResponse<Integer>
     */
    @RequestMapping("updateAdminRoleDTO")
    public CustomResponse<Integer> updateAdminRoleDTO(@RequestBody AdminRoleDTO adminRoleDTO){
        return CustomResponse.success(adminRoleService.updateAdminRole(adminRoleDTO));
    }
    
    /**
     * 新增角色
     * @param adminRoleDTO
     * @return CustomResponse<Integer>
     */
    @RequestMapping("addAdminRoleDTO")
    public CustomResponse<Integer> addAdminRoleDTO(@RequestBody AdminRoleDTO adminRoleDTO){
        return CustomResponse.success(adminRoleService.addAdminRole(adminRoleDTO));
    }
    
    /**
     * 删除角色
     * TODO。
     * @param adminRoleDTO
     * @return CustomResponse<Integer>
     */
    @RequestMapping("delAdminRoleDTO")
    public CustomResponse<Integer> delAdminRoleDTO(@RequestBody AdminRoleDTO adminRoleDTO){
        return CustomResponse.success(adminRoleService.delAdminRole(adminRoleDTO));
    }
    
    
    /**
     * 获取角色详情
     * TODO。
     * @param adminRoleDTO
     * @return CustomResponse<Integer>
     */
    @RequestMapping("queryAdminRoleById")
    public CustomResponse<AdminRoleDTO> queryAdminRoleById(@RequestBody AdminRoleDTO adminRoleDTO){
        ParamCheckUtil.checkEmpty(adminRoleDTO.getRoleId());
        return CustomResponse.success(adminRoleService.queryAdminRoleById(adminRoleDTO.getRoleId()));
    }
    
}
