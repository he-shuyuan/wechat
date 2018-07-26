/**
 * Copyright(c) Foresee Science & Technology Ltd. 
 */
package com.ower.dsyz.admin.rest.back;

import java.util.List;
import javax.annotation.Resource;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ower.dsyz.admin.manual.dto.AdminRoleFunDTO;
import com.ower.dsyz.admin.manual.dto.AdminRoleFunListDTO;
import com.ower.dsyz.admin.service.IAdminRoleFunctionService;
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
@RequestMapping("/back/adminRoleFunctionService/")
public class AdminRoleFunctionResource {

    @Resource
    IAdminRoleFunctionService adminRoleFunctionService;
    
    /**
     * 菜单权限树
     * TODO。
     * @param adminRoleFunDTO
     * @return CustomResponse<List<AdminRoleFunDTO>>
     */
    @RequestMapping("queryAdminRoleFunDTOTreeList")
    public CustomResponse<List<AdminRoleFunDTO>> queryAdminRoleFunDTOTreeList(@RequestBody AdminRoleFunDTO adminRoleFunDTO) {
       
        return CustomResponse.success(adminRoleFunctionService.queryAdminRoleFunDTOTreeList(adminRoleFunDTO));
    }
    
    /**
     * 菜单授权
     * TODO。
     * @param adminRoleFunListDTO
     * @return CustomResponse<Integer>
     */
    @RequestMapping("addAuthFunctionList")
    public CustomResponse<Integer> addAuthFunctionList(@RequestBody  AdminRoleFunListDTO adminRoleFunListDTO) {
        return CustomResponse.success(adminRoleFunctionService.addAuthFunctionList(adminRoleFunListDTO));
    }
}
