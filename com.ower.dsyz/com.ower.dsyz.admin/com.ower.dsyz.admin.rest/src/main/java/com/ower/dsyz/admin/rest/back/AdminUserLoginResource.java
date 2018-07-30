/**
 * Copyright(c) Foresee Science & Technology Ltd. 
 */
package com.ower.dsyz.admin.rest.back;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ower.dsyz.admin.manual.dto.AdminUserDTO;
import com.ower.dsyz.admin.manual.dto.UserLoginResuest;
import com.ower.dsyz.common.core.annotation.EnableValid;
import com.ower.dsyz.common.core.response.CustomResponse;

/**
 * <pre>
 * TODO。
 * </pre>
 *
 * @author HeShuyuan@foresee.com.cn
 * @date 2018年7月30日
 * @version 1.00.00
 * 
 *          <pre>
 * 修改记录 
 *    修改后版本:     修改人：  修改日期:     修改内容:
 *          </pre>
 */
@RestController
@RequestMapping("/back/adminUserLoginService/")
public class AdminUserLoginResource {

    
    /**
     * 用户登录
     * @return CustomResponse<?>
     */
    @RequestMapping("login")
    public CustomResponse<AdminUserDTO> login(@RequestBody @EnableValid UserLoginResuest param){
        return null;
    }
}
