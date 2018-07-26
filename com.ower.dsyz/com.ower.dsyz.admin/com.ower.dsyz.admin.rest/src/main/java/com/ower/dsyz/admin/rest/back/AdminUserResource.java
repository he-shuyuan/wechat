/**
 * Copyright(c) Foresee Science & Technology Ltd. 
 */
package com.ower.dsyz.admin.rest.back;

import javax.annotation.Resource;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ower.dsyz.admin.manual.dto.AdminDepUserDTO;
import com.ower.dsyz.admin.manual.dto.AdminUserDepDTO;
import com.ower.dsyz.admin.service.IAdminDepUserService;
import com.ower.dsyz.admin.service.IAdminUserService;
import com.ower.dsyz.common.core.page.PageQueryResult;
import com.ower.dsyz.common.core.page.PageRequestParam;
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
@RequestMapping("/back/adminUserService/")
public class AdminUserResource {

    @Resource
    private  IAdminUserService adminUserService;
    
    @Resource
    private IAdminDepUserService adminDepUserService;
    /**
     * 分页查询用户
     * @param param
     * @return PageQueryResult<AdminFunctionDTO>
     */
    @RequestMapping("pageQueryAdminUserDTOList")
    public CustomResponse<PageQueryResult<AdminUserDepDTO>> pageQueryAdminUserDTOList(@RequestBody  PageRequestParam param){
        if(StringUtils.isNoneBlank(param.getString("optionsRadios"))&& "0".equals(param.getString("optionsRadios"))){
            param.put("pathCode",null);
        }else{
            param.put("depId",null);
        }
        return CustomResponse.success(adminUserService.pageQueryAdminUserDTOList(param));
    }
    
    /**
     * 获取用户信息
     * TODO。
     * @param adminUserDepDTO
     * @return CustomResponse<AdminUserDepDTO>
     */
    @RequestMapping("queryUserDepInfo")
    public CustomResponse<AdminUserDepDTO> queryUserDepInfo(@RequestBody AdminUserDepDTO adminUserDepDTO){
       return CustomResponse.success(adminUserService.queryUserDepInfo(adminUserDepDTO));
    }
    
    /**
     * 新增用户
     * TODO。
     * @param adminUserDepDTO
     * @return CustomResponse<AdminUserDepDTO>
     */
    @RequestMapping("addAdminUser")
    public CustomResponse<Integer> addAdminUser(@RequestBody AdminUserDepDTO adminUserDepDTO){
       return CustomResponse.success(adminUserService.addAdminUser(adminUserDepDTO));
    }
    
    /**
     * 更新用户信息
     * TODO。
     * @param adminUserDepDTO
     * @return CustomResponse<AdminUserDepDTO>
     */
    @RequestMapping("updateAdminUser")
    public CustomResponse<Integer> updateAdminUser(@RequestBody AdminUserDepDTO adminUserDepDTO){
       return CustomResponse.success(adminUserService.updateAdminUser(adminUserDepDTO));
    }
    
    /**
     * 移除部门人员
     * TODO。
     * @param adminDepUserDTO
     * @return CustomResponse<Integer>
     */
    @RequestMapping("removeAdminDepUser")
    public CustomResponse<Integer>  removeAdminDepUser(@RequestBody  AdminDepUserDTO adminDepUserDTO){
        return CustomResponse.success(adminDepUserService.removeAdminDepUser(adminDepUserDTO));
    }
}
