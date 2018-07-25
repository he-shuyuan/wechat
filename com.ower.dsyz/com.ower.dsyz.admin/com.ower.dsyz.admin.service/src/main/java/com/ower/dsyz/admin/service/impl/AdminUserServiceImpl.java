/**
 * Copyright(c) Foresee Science & Technology Ltd. 
 */
package com.ower.dsyz.admin.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import com.ower.dsyz.admin.manual.dao.AdminUserExtMapper;
import com.ower.dsyz.admin.manual.dto.AdminUserDepDTO;
import com.ower.dsyz.admin.service.IAdminUserService;
import com.ower.dsyz.common.core.page.PageQueryResult;
import com.ower.dsyz.common.core.page.PageRequestParam;


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

    @Autowired
    private AdminUserExtMapper adminUserExtMapper;
    
    @Override
    public PageQueryResult<AdminUserDepDTO> pageQueryAdminUserDTOList(PageRequestParam param) {

        return adminUserExtMapper.pageQueryAdminUserDTOList(param);
    }

}
