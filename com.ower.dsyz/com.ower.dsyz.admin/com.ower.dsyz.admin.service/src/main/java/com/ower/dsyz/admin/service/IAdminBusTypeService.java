/**
 * Copyright(c) Foresee Science & Technology Ltd. 
 */
package com.ower.dsyz.admin.service;

import java.util.List;
import com.ower.dsyz.admin.auto.model.AdminBusiness;
import com.ower.dsyz.admin.auto.model.AdminInstitutionType;

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

public interface IAdminBusTypeService {
    /**
     * 获取业务列表
     * @param adminBusiness
     * @return List<AdminBusiness>
     */
     List<AdminBusiness> queryBusTypeList(AdminBusiness adminBusiness);
     
     /**
      * 获取组织类型列表
      * @param adminInstitutionType
      * @return List<AdminInstitutionType>
      */
     List<AdminInstitutionType> queryInsTypeList(AdminInstitutionType adminInstitutionType);
}
