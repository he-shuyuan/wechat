/**
 * Copyright(c) Foresee Science & Technology Ltd. 
 */
package com.ower.dsyz.admin.rest.back;

import javax.annotation.Resource;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ower.dsyz.admin.manual.dto.AdminUserDepDTO;
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
    
    /**
     * 分页查询用户
     * @param param
     * @return PageQueryResult<AdminFunctionDTO>
     */
    @RequestMapping("pageQueryAdminUserDTOList")
    public CustomResponse<PageQueryResult<AdminUserDepDTO>> pageQueryAdminUserDTOList(@RequestBody  PageRequestParam param){
        return CustomResponse.success(adminUserService.pageQueryAdminUserDTOList(param));
    }
}
