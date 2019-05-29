/**
 * Copyright(c) Foresee Science & Technology Ltd. 
 */
package com.ower.dsyz.admin.service.impl;

import java.util.List;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ower.dsyz.admin.auto.dao.AdminFunctionMapper;
import com.ower.dsyz.admin.auto.model.AdminFunction;
import com.ower.dsyz.admin.manual.dao.AdminFunctionExtMapper;
import com.ower.dsyz.admin.manual.dto.AdminFunctionDTO;
import com.ower.dsyz.admin.service.IAdminFunctionService;
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
 * @date 2018年7月24日
 * @version 1.00.00
 * 
 *          <pre>
 * 修改记录 
 *    修改后版本:     修改人：  修改日期:     修改内容:
 *          </pre>
 */
@Service
public class AdminFunctionServiceImpl implements IAdminFunctionService {
    @Autowired
    private AdminFunctionExtMapper adminFunctionExtMapper;
    
    @Autowired
    private AdminFunctionMapper adminFunctionMapper;
    
    @Override
    public PageQueryResult<AdminFunctionDTO> pageQueryAdminFunctionDTOList(PageRequestParam param) {

        return adminFunctionExtMapper.pageQueryAdminFunctionDTOList(param);
    }

    @Override
    public List<AdminFunctionDTO> queryAdminFunctionList(AdminFunctionDTO adminFunctionDTO) {

        return adminFunctionExtMapper.queryAdminFunctionList(adminFunctionDTO);
    }

    @Override
    public AdminFunctionDTO queryAdminFunctionById(AdminFunctionDTO adminFunctionDTO) {
        List<AdminFunctionDTO> list = adminFunctionExtMapper.queryAdminFunctionList(adminFunctionDTO);
        if(list.isEmpty()){
            throw new BusinessException("1","找不到相应的数据");
        }
        return list.get(0);
    }

    @Override
    public AdminFunction addAdminFunction(AdminFunctionDTO adminFunctionDTO) {
        AdminFunction adminFunction = new AdminFunction();
        BeanUtils.copyProperties(adminFunctionDTO, adminFunction);
        adminFunction.setFunctionId(IDUtil.getUUID());
        adminFunctionMapper.insertSelective(adminFunction);
        return adminFunction;
    }

    @Override
    public int updateAdminFunction(AdminFunctionDTO adminFunctionDTO) {
        AdminFunction adminFunction = new AdminFunction();
        BeanUtils.copyProperties(adminFunctionDTO, adminFunction);
        return adminFunctionMapper.updateByPrimaryKeySelective(adminFunction);
    }

    @Override
    public int delAdminFunction(AdminFunctionDTO adminFunctionDTO) {
        ParamCheckUtil.checkEmpty(adminFunctionDTO.getFunctionId(), "菜单id不能为空");
        return adminFunctionMapper.deleteByPrimaryKey(adminFunctionDTO.getFunctionId());
    }

}
