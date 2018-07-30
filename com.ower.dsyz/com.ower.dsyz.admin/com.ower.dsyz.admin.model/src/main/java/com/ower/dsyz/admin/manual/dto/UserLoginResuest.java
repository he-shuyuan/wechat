/**
 * Copyright(c) Foresee Science & Technology Ltd.
 */
package com.ower.dsyz.admin.manual.dto;

import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.NotBlank;
import com.ower.dsyz.admin.manual.constant.LoginType;

/**
 * <pre>
 * 用户登录
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

public class UserLoginResuest {

    /**
     * 账号（手机或登录名）
     */
    @NotBlank(message = "登录账号不能为空")
    private String    account;

    /**
     * 密码
     */
    private String    password;

    /**
     * 登录类型
     */
    @NotNull(message = "登录类型不能为空")
    private LoginType type;

    /**
     * 验证码
     */
    private String    code;

    
    public String getAccount() {
        return account;
    }

    
    public void setAccount(String account) {
        this.account = account;
    }

    
    public String getPassword() {
        return password;
    }

    
    public void setPassword(String password) {
        this.password = password;
    }

    
    public LoginType getType() {
        return type;
    }

    
    public void setType(LoginType type) {
        this.type = type;
    }

    
    public String getCode() {
        return code;
    }

    
    public void setCode(String code) {
        this.code = code;
    }


    @Override
    public String toString() {
        return "UserLoginResuest [account=" + account + ", password=" + password + ", type=" + type + ", code=" + code + "]";
    }

}
