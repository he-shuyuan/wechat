/**
 * Copyright(c) Foresee Science & Technology Ltd. 
 */
package com.ower.dsyz.admin.service.impl;

import java.util.List;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ower.dsyz.admin.auto.dao.AdminRoleMapper;
import com.ower.dsyz.admin.auto.model.AdminRole;
import com.ower.dsyz.admin.manual.dao.AdminRoleExtMapper;
import com.ower.dsyz.admin.manual.dto.AdminRoleDTO;
import com.ower.dsyz.admin.service.IAdminRoleService;
import com.ower.dsyz.common.core.exception.BusinessException;
import com.ower.dsyz.common.core.util.IDUtil;
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
@Service
public class AdminRoleServiceImpl implements IAdminRoleService {

    @Autowired
    private AdminRoleExtMapper adminRoleExtMapper;
    
    @Autowired
    private AdminRoleMapper adminRoleMapper;
    
    @Override
    public List<AdminRoleDTO> queryAdminRoleDTOList(AdminRoleDTO adminRoleDTO) {

        return adminRoleExtMapper.queryAdminRoleDTOList(adminRoleDTO);
    }

    @Override
    public PageQueryResult<AdminRoleDTO> pageQueryAdminRoleDTOList(PageRequestParam param) {

        return adminRoleExtMapper.pageQueryAdminRoleDTOList(param);
    }

    @Override
    public int updateAdminRole(AdminRoleDTO adminRoleDTO) {
        AdminRole adminRole = new AdminRole();
        BeanUtils.copyProperties(adminRoleDTO, adminRole);
        return adminRoleMapper.updateByPrimaryKeySelective(adminRole);
    }

    @Override
    public int addAdminRole(AdminRoleDTO adminRoleDTO) {
        AdminRole adminRole = new AdminRole();
        BeanUtils.copyProperties(adminRoleDTO, adminRole);
        adminRole.setRoleId(IDUtil.getUUID());
        return adminRoleMapper.insertSelective(adminRole);
    }

    @Override
    public int delAdminRole(AdminRoleDTO adminRoleDTO) {
        ParamCheckUtil.checkEmpty(adminRoleDTO.getRoleId(), "角色id不能为空");
        return adminRoleMapper.deleteByPrimaryKey(adminRoleDTO.getRoleId());
    }

    @Override
    public AdminRoleDTO queryAdminRoleById(String id) {
        AdminRoleDTO adminRoleDTO = new AdminRoleDTO();
        adminRoleDTO.setRoleId(id);
        List<AdminRoleDTO> list = this.queryAdminRoleDTOList(adminRoleDTO);
        if(list.isEmpty()){
            throw new BusinessException("角色id不存在");
        }
        return list.get(0);
    }

	@Override
	public List<AdminRole> queryAdminRoleList(String userId, String insId) {
		return adminRoleExtMapper.queryAdminRoleListByUserIdAndInsId(userId,insId);
	}

}
