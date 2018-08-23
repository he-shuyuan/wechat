/**
 * Copyright(c) Foresee Science & Technology Ltd. 
 */
package com.ower.dsyz.admin.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.ower.dsyz.admin.auto.dao.AdminRoleFunMapper;
import com.ower.dsyz.admin.auto.model.AdminRole;
import com.ower.dsyz.admin.auto.model.AdminRoleFun;
import com.ower.dsyz.admin.manual.constant.AdminConstant;
import com.ower.dsyz.admin.manual.dao.AdminRoleFunExtMapper;
import com.ower.dsyz.admin.manual.dto.AdminFunctionExtDTO;
import com.ower.dsyz.admin.manual.dto.AdminRoleExtDTO;
import com.ower.dsyz.admin.manual.dto.AdminRoleFunDTO;
import com.ower.dsyz.admin.manual.dto.AdminRoleFunListDTO;
import com.ower.dsyz.admin.manual.dto.UserLoginFunInfo;
import com.ower.dsyz.admin.service.IAdminRoleFunctionService;
import com.ower.dsyz.admin.service.IAdminRoleService;
import com.ower.dsyz.common.core.exception.CustomRunTimeException;
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
public class AdminRoleFunctionServiceImpl implements IAdminRoleFunctionService {
    
    @Autowired
    private AdminRoleFunExtMapper adminRoleFunExtMapper;
    
    @Autowired
    private AdminRoleFunMapper adminRoleFunMapper;
    
    @Autowired
    private IAdminRoleService adminRoleService;
    
    @Override
    public List<AdminRoleFunDTO> queryAdminRoleFunDTOTreeList(AdminRoleFunDTO adminRoleFunDTO) {
        ParamCheckUtil.checkEmpty(adminRoleFunDTO.getRoleId());
        if(StringUtils.isBlank(adminRoleFunDTO.getParentId())){
            adminRoleFunDTO.setCode(AdminConstant.FunctionConstant.DEFAULT_PARENT_TOP_CODE);
            adminRoleFunDTO.setBusTypeId("");
        }
        return adminRoleFunExtMapper.queryAdminRoleFunDTOTreeList(adminRoleFunDTO);
    }

    @Override
    @Transactional
    public int addAuthFunctionList(AdminRoleFunListDTO adminRoleFunListDTO) {
        ParamCheckUtil.checkEmpty(adminRoleFunListDTO.getRoleId());
        adminRoleFunExtMapper.delAdminRoleFunDTOList(adminRoleFunListDTO.getRoleId());
        if(adminRoleFunListDTO.getFunctionIdList() == null){
            return 0;
        }
        AdminRoleFun adminRoleFun = new AdminRoleFun(); 
        adminRoleFun.setRoleId(adminRoleFunListDTO.getRoleId());
        int result = 0;
        for(String id : adminRoleFunListDTO.getFunctionIdList()){
            adminRoleFun.setFunctionId(id);
            adminRoleFun.setId(IDUtil.getUUID());
            result+=adminRoleFunMapper.insertSelective(adminRoleFun);
        }
        return result;
    }

	@Override
	public UserLoginFunInfo queryUserRoleFunctionList(AdminRoleExtDTO adminRoleExtDTO) {
		UserLoginFunInfo info = new UserLoginFunInfo();
		List<AdminRole> list = adminRoleService.queryAdminRoleList(adminRoleExtDTO.getUserId(),adminRoleExtDTO.getInsId());
		if(list.isEmpty()){
			throw new CustomRunTimeException("用户没有角色");
		}
		info.setRoleList(list);
		Map<String,Object> map = new HashMap<>();
		map.put("list", list);
		map.put("busTypeId", adminRoleExtDTO.getBusTypeId());
		map.put("parentId", AdminConstant.FunctionConstant.DEFAULT_PARENT_TOP_ID);
		info.setMenuList(this.recurrenceQueryFun(map));
		return info;
	}

	/**
	 * 递归查询子菜单
	 * @param map
	 * @return
	 */
	private List<AdminFunctionExtDTO> recurrenceQueryFun(Map<String, Object> map) {
		List<AdminFunctionExtDTO> adminFunctionExtDTOList = adminRoleFunExtMapper.queryFunctionByRoleList(map);
		for(AdminFunctionExtDTO temp:adminFunctionExtDTOList){
			map.put("parentId", temp.getFunctionId());
			temp.setChildrens(recurrenceQueryFun(map));
		}
		return adminFunctionExtDTOList;
	}
    
}
