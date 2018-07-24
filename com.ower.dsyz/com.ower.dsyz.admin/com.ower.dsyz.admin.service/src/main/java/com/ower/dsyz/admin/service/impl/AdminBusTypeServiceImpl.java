/**
 * Copyright(c) Foresee Science & Technology Ltd. 
 */
package com.ower.dsyz.admin.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ower.dsyz.admin.auto.model.AdminBusiness;
import com.ower.dsyz.admin.auto.model.AdminInstitutionType;
import com.ower.dsyz.admin.manual.dao.AdminBusTypeExtMapper;
import com.ower.dsyz.admin.service.IAdminBusTypeService;


/**
 * <pre>
 * TODO。
 * </pre>
 *
 * @author HeShuyuan@foresee.com.cn
 * @date 2018年7月23日
 * @version 1.00.00
 * 
 *          <pre>
 * 修改记录 
 *    修改后版本:     修改人：  修改日期:     修改内容:
 *          </pre>
 */
@Service
public class AdminBusTypeServiceImpl implements IAdminBusTypeService {

    @Autowired
    private AdminBusTypeExtMapper adminBusTypeExtMapper;
    @Override
    public List<AdminBusiness> queryBusTypeList(AdminBusiness adminBusiness) {

        return adminBusTypeExtMapper.queryBusTypeList(adminBusiness);
    }

    @Override
    public List<AdminInstitutionType> queryInsTypeList(AdminInstitutionType adminInstitutionType) {

        return adminBusTypeExtMapper.queryInsTypeList(adminInstitutionType);
    }

}
