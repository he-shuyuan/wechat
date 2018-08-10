/**
 * Copyright(c) Foresee Science & Technology Ltd.
 */
package com.ower.dsyz.admin.service.impl;

import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.ower.dsyz.admin.auto.dao.AdminUserLoginMapper;
import com.ower.dsyz.admin.auto.dao.AdminUserMapper;
import com.ower.dsyz.admin.auto.model.AdminUser;
import com.ower.dsyz.admin.auto.model.AdminUserLogin;
import com.ower.dsyz.admin.manual.constant.AdminConstant;
import com.ower.dsyz.admin.manual.dao.AdminUserExtMapper;
import com.ower.dsyz.admin.manual.dto.AdminDepUserDTO;
import com.ower.dsyz.admin.manual.dto.AdminUserDTO;
import com.ower.dsyz.admin.manual.dto.AdminUserDepDTO;
import com.ower.dsyz.admin.service.IAdminDepUserService;
import com.ower.dsyz.admin.service.IAdminUserService;
import com.ower.dsyz.common.core.exception.CustomRunTimeException;
import com.ower.dsyz.common.core.page.PageQueryResult;
import com.ower.dsyz.common.core.page.PageRequestParam;
import com.ower.dsyz.common.core.util.HashUtil;
import com.ower.dsyz.common.core.util.IDUtil;

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
@Service
public class AdminUserServiceImpl implements IAdminUserService {

    private Logger               logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private AdminUserExtMapper   adminUserExtMapper;

    @Autowired
    private AdminUserMapper      adminUserMapper;

    @Autowired
    private AdminUserLoginMapper adminUserLoginMapper;

    @Autowired
    private IAdminDepUserService adminDepUserService;


    @Override
    public PageQueryResult<AdminUserDepDTO> pageQueryAdminUserDTOList(PageRequestParam param) {
        return adminUserExtMapper.pageQueryAdminUserDTOList(param);
    }

    @Override
    @Transactional
    public int updateAdminUser(AdminUserDepDTO adminUserDepDTO) {
        this.checkRepeat(adminUserDepDTO, true);
        //用户表
        AdminUser adminUser = new AdminUser();
        BeanUtils.copyProperties(adminUserDepDTO, adminUser);
        adminUserMapper.updateByPrimaryKeySelective(adminUser);
        //登录表
        AdminUserLogin adminUserLogin = new AdminUserLogin();
        BeanUtils.copyProperties(adminUserDepDTO, adminUser);
        adminUserLoginMapper.updateByPrimaryKeySelective(adminUserLogin);
        //用户所属部门信息
        this.updateAdminDepUser(adminUserDepDTO);
        return 0;
    }

    @Override
    @Transactional
    public int addAdminUser(AdminUserDepDTO adminUserDepDTO) {
        //根据电话号码账号是否已存在别的ins
        if (!this.isHasUserAccountInOtherIns(adminUserDepDTO)) {
            this.checkRepeat(adminUserDepDTO, false);
            adminUserDepDTO.setUserId(IDUtil.getUUID());
            //用户表
            AdminUser adminUser = new AdminUser();
            BeanUtils.copyProperties(adminUserDepDTO, adminUser);
            adminUserMapper.insertSelective(adminUser);
            //登录表
            AdminUserLogin adminUserLogin = new AdminUserLogin();
            BeanUtils.copyProperties(adminUserDepDTO, adminUserLogin);
            adminUserLogin.setSalt(IDUtil.getUUID());
            adminUserLogin.setPassword(HashUtil.md5Hex(AdminConstant.UserConstant.DEFAULT_PASSWORD+adminUserLogin.getSalt()));
            adminUserLoginMapper.insertSelective(adminUserLogin);
        }
        //用户所属部门信息
        this.updateAdminDepUser(adminUserDepDTO);
        return 0;
    }

    @Override
    public List<AdminUserDepDTO> queryUserDepInfoList(AdminUserDepDTO adminUserDepDTO) {

        return adminUserExtMapper.queryUserDepInfoList(adminUserDepDTO);
    }

    @Override
    public AdminUserDepDTO queryUserDepInfo(AdminUserDepDTO adminUserDepDTO) {
        List<AdminUserDepDTO> list = this.queryUserDepInfoList(adminUserDepDTO);
        if (list.isEmpty()) {
            return null;
        }
        return list.get(0);
    }

    /**
     * 查重，登录名和手机号 TODO。
     * @param adminUserDepDTO
     * @param update void
     */
    private void checkRepeat(AdminUserDepDTO adminUserDepDTO, Boolean update) {
        AdminUserDTO adminUserDTO = new AdminUserDTO();
        //登录名
        if (StringUtils.isNoneBlank(adminUserDepDTO.getLoginName())) {
            adminUserDTO.setLoginName(adminUserDepDTO.getLoginName());
            List<AdminUserDTO> list = adminUserExtMapper.queryAdminUserDTOList(adminUserDTO);
            if ((!list.isEmpty() && !update) || (update && list.get(0).getUserId().equals(adminUserDepDTO.getUserId()))) {
                throw new CustomRunTimeException("登录名已存在");
            }
        }
        //手机号
        if (StringUtils.isNoneBlank(adminUserDepDTO.getPhone())) {
            adminUserDTO = new AdminUserDTO();
            adminUserDTO.setPhone(adminUserDepDTO.getPhone());
            List<AdminUserDTO> list = adminUserExtMapper.queryAdminUserDTOList(adminUserDTO);
            if ((!list.isEmpty() && !update) || (update && list.get(0).getUserId().equals(adminUserDepDTO.getUserId()))) {
                throw new CustomRunTimeException("电话号码已存在");
            }
        }
    }

    /**
     * 更新用户所选部门 TODO。
     * @param adminUserDepDTO void
     */
    private void updateAdminDepUser(AdminUserDepDTO adminUserDepDTO) {
        if (StringUtils.isBlank(adminUserDepDTO.getDepId())) {
            return;
        }
        AdminDepUserDTO adminDepUserDTO = new AdminDepUserDTO();
        adminDepUserDTO.setUserId(adminUserDepDTO.getUserId());
        adminDepUserDTO.setIsValid("Y");
        adminDepUserDTO.setInsId(adminUserDepDTO.getInsId());
        List<AdminDepUserDTO> list = adminDepUserService.queryAdminDepUserDTOList(adminDepUserDTO);
        if (list.isEmpty()) {
            adminDepUserDTO.setDepId(adminUserDepDTO.getDepId());
            logger.info("用户还没有分配部门,新增部门分配记录=>{}", adminDepUserDTO);
            adminDepUserService.addAdminDepUser(adminDepUserDTO);
        } else {
            logger.info("用户已经拥有部门,判断是否更新记录=>{}", list.get(0));
            if (!list.get(0).getDepId().equals(adminUserDepDTO.getDepId())) {
                list.get(0).setIsValid("N");
                adminDepUserService.updateAdminDepUser(list.get(0));
                adminDepUserDTO.setDepId(adminUserDepDTO.getDepId());
                adminDepUserService.addAdminDepUser(adminDepUserDTO);
            }

        }
    }

    /**
     * 判断用户是否存在于别的机构
     * @param adminUserDepDTO
     * @return boolean
     */
    private boolean isHasUserAccountInOtherIns(AdminUserDepDTO adminUserDepDTO) {
        AdminDepUserDTO adminDepUserDTO = new AdminDepUserDTO();
        adminDepUserDTO.setPhone(adminUserDepDTO.getPhone());
        adminDepUserDTO.setIsValid("Y");
        List<AdminDepUserDTO> list = adminDepUserService.queryAdminDepUserDTOList(adminDepUserDTO);
        if (list.isEmpty()) {
            return false;
        } else {
            for (AdminDepUserDTO dto : list) {
                if (StringUtils.isNoneBlank(dto.getInsId()) && !dto.getInsId().equals(adminUserDepDTO.getInsId())) {
                    adminUserDepDTO.setUserId(dto.getUserId());
                    return true;
                }
            }
            return false;
        }
    }

    @Override
    public List<AdminUserDTO> queryAdminUserDTOList(AdminUserDTO adminUserDTO) {
        
        return adminUserExtMapper.queryAdminUserDTOList(adminUserDTO);
    }
}
