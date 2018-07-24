/**
 * Copyright(c) Foresee Science & Technology Ltd. 
 */
package com.ower.dsyz.admin.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ower.dsyz.admin.manual.dao.AdminFunctionExtMapper;
import com.ower.dsyz.admin.manual.dto.AdminFunctionDTO;
import com.ower.dsyz.admin.service.IAdminFunctionService;
import com.ower.dsyz.common.core.exception.CustomRunTimeException;
import com.ower.dsyz.common.core.page.PageQueryResult;
import com.ower.dsyz.common.core.page.PageRequestParam;


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
            throw new CustomRunTimeException("1","找不到相应的数据");
        }
        return list.get(0);
    }

}
