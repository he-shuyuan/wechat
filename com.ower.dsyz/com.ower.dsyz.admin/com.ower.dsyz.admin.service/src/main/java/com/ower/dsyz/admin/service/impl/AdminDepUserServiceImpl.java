/**
 * Copyright(c) Foresee Science & Technology Ltd. 
 */
package com.ower.dsyz.admin.service.impl;

import java.util.List;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ower.dsyz.admin.auto.dao.AdminDepUserMapper;
import com.ower.dsyz.admin.auto.model.AdminDepUser;
import com.ower.dsyz.admin.manual.dao.AdminDepUserExtMapper;
import com.ower.dsyz.admin.manual.dto.AdminDepUserDTO;
import com.ower.dsyz.admin.service.IAdminDepUserService;
import com.ower.dsyz.common.core.util.IDUtil;
import com.ower.dsyz.common.core.util.ParamCheckUtil;


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
@Service
public class AdminDepUserServiceImpl implements IAdminDepUserService {

    @Autowired
    private AdminDepUserExtMapper adminDepUserExtMapper;
    
    @Autowired
    private AdminDepUserMapper adminDepUserMapper;
    
    @Override
    public List<AdminDepUserDTO> queryAdminDepUserDTOList(AdminDepUserDTO adminDepUserDTO) {

        return adminDepUserExtMapper.queryAdminDepUserDTOList(adminDepUserDTO);
    }

    @Override
    public int updateAdminDepUser(AdminDepUserDTO adminDepUserDTO) {
        AdminDepUser adminDepUser =new AdminDepUser();
        BeanUtils.copyProperties(adminDepUserDTO, adminDepUser);
        return adminDepUserMapper.updateByPrimaryKeySelective(adminDepUser);
    }

    @Override
    public int addAdminDepUser(AdminDepUserDTO adminDepUserDTO) {
        AdminDepUser adminDepUser =new AdminDepUser();
        BeanUtils.copyProperties(adminDepUserDTO, adminDepUser);
        adminDepUser.setDepUserId(IDUtil.getUUID());
        return adminDepUserMapper.insertSelective(adminDepUser);
    }

    @Override
    public int removeAdminDepUser(AdminDepUserDTO adminDepUserDTO) {
        ParamCheckUtil.checkEmpty(adminDepUserDTO.getDepId());
        ParamCheckUtil.checkEmpty(adminDepUserDTO.getUserId());
        List<AdminDepUserDTO> list = queryAdminDepUserDTOList(adminDepUserDTO);
        if(list.isEmpty()){
            return 0;
        }
        return adminDepUserMapper.deleteByPrimaryKey(list.get(0).getDepUserId());
    }

}
