/**
 * Copyright(c) Foresee Science & Technology Ltd. 
 */
package com.ower.dsyz.admin.rest.back;

import java.util.List;
import javax.annotation.Resource;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ower.dsyz.admin.manual.dto.AdminFunctionDTO;
import com.ower.dsyz.admin.service.IAdminFunctionService;
import com.ower.dsyz.common.core.response.CustomResponse;
import com.ower.dsyz.common.core.util.ParamCheckUtil;

/**
 * <pre>
 * TODO。
 * </pre>
 *
 * @author HeShuyuan@foresee.com.cn
 * @date 2018年7月24日
 * @version 1.00.00
 * 
 *          <pre>
 * 修改记录 
 *    修改后版本:     修改人：  修改日期:     修改内容:
 *          </pre>
 */
@RestController
@RequestMapping("/back/adminFunctionService/")
public class AdminFunctionResource {
    
    @Value("${admin.function.top.code:HEAD}")
    private String topMenu;
    
    @Resource
    private IAdminFunctionService adminFunctionService;
    
    /**
     * 条件查询菜单
     * @param adminFunctionDTO
     * @return List<AdminFunctionDTO>
     */
    @RequestMapping("queryAdminFunctionList")
    public CustomResponse<List<AdminFunctionDTO>> queryAdminFunctionList(@RequestBody AdminFunctionDTO adminFunctionDTO){
        if(StringUtils.isBlank(adminFunctionDTO.getParentId())){
            adminFunctionDTO.setCode(topMenu);
            adminFunctionDTO.setBusTypeId("");
        }
        return CustomResponse.success(adminFunctionService.queryAdminFunctionList(adminFunctionDTO));
    }
    
    /**
     * 通过id查找admin
     * @param adminFunctionDTO
     * @return List<AdminFunctionDTO>
     */
    @RequestMapping("queryAdminFunctionById")
    public CustomResponse<AdminFunctionDTO> queryAdminFunctionById(@RequestBody AdminFunctionDTO adminFunctionDTO){
        ParamCheckUtil.checkEmpty(adminFunctionDTO.getFunctionId(), "菜单id不能为空");
        return CustomResponse.success(adminFunctionService.queryAdminFunctionById(adminFunctionDTO));
    }
}
