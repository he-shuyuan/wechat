/**
 * Copyright(c) Foresee Science & Technology Ltd. 
 */
package com.ower.dsyz.admin.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ower.dsyz.admin.manual.dto.AdminUserDTO;
import com.ower.dsyz.admin.manual.dto.UserLoginResuest;
import com.ower.dsyz.admin.service.IAdminUserLoginService;
import com.ower.dsyz.admin.service.IAdminUserService;
import com.ower.dsyz.common.core.exception.CustomRunTimeException;
import com.ower.dsyz.common.core.util.HashUtil;
import com.ower.dsyz.common.core.util.ParamCheckUtil;


/**
 * <pre>
 * TODO。
 * </pre>
 *
 * @author HeShuyuan@foresee.com.cn
 * @date 2018年8月7日
 * @version 1.00.00
 * 
 *          <pre>
 * 修改记录 
 *    修改后版本:     修改人：  修改日期:     修改内容:
 *          </pre>
 */
@Service
public class AdminUserLoginServiceImpl implements IAdminUserLoginService {

    @Autowired
    IAdminUserService adminUserService;
    
    @Override
    public AdminUserDTO login(UserLoginResuest param) {
        AdminUserDTO adminUserDTO = new AdminUserDTO();
        switch(param.getType()){
            case pass:
                adminUserDTO = this.accountPassLogin(param.getAccount(),param.getPassword());break;
            case eCode:
                break;
            case pCode:
                break;
            default:
                break;
        }
        return adminUserDTO;
    }
    
    
    /**
     * 用户名或手机号登录
     * @param account
     * @param password void
     */
    private AdminUserDTO accountPassLogin(String account, String password) {
        AdminUserDTO adminUserDTO = new AdminUserDTO();
        if(ParamCheckUtil.checkPhone(account)){
            adminUserDTO.setPhone(account);
        }else{
            adminUserDTO.setLoginName(account);
        }
        List<AdminUserDTO> list = adminUserService.queryAdminUserDTOList(adminUserDTO);
        if(list.isEmpty()){
            throw new CustomRunTimeException("00101", "登录账号不存在");
        }
        password = HashUtil.md5Hex(password+list.get(0).getSalt());
        if(!password.equals(list.get(0).getPassword())){
            throw new CustomRunTimeException("00102", "用户账号或密码错误");
        }
        if("N".equals(list.get(0).getIsValid())){
            throw new CustomRunTimeException("00103","用户已被禁用");
        }
        return list.get(0);
    }

}
