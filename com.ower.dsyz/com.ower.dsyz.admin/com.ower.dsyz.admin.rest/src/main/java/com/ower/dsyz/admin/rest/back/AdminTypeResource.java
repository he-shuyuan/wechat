/**
 * Copyright(c) Foresee Science & Technology Ltd. 
 */
package com.ower.dsyz.admin.rest.back;

import java.util.List;
import javax.annotation.Resource;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ower.dsyz.admin.auto.model.AdminBusiness;
import com.ower.dsyz.admin.auto.model.AdminInstitutionType;
import com.ower.dsyz.admin.service.IAdminBusTypeService;
import com.ower.dsyz.common.core.response.CustomResponse;

/**
 * <pre>
 * TODO。
 * </pre>
 *
 * @author HeShuyuan@163.com
 * @date 2018年7月20日
 * @version 1.00.00
 * 
 *          <pre>
 * 修改记录 
 *    修改后版本:     修改人：  修改日期:     修改内容:
 *          </pre>
 */
@RestController
@RequestMapping("/back/adminTypeService/")
public class AdminTypeResource {

    @Resource
    private IAdminBusTypeService adminBusTypeService;
    /**
     * 获取业务类型
     * @param adminBusiness
     * @return CustomResponse<AdminBusiness>
     */
    @RequestMapping("queryBusTypeList")
    public CustomResponse<List<AdminBusiness>> queryBusTypeList(AdminBusiness adminBusiness){
        return CustomResponse.success(adminBusTypeService.queryBusTypeList(adminBusiness));
    }
    
    /**
     * 获取组织类型
     * @param adminInstitutionType
     * @return CustomResponse<AdminInstitutionType>
     */
    @RequestMapping("queryInsTypeList")
    public CustomResponse<List<AdminInstitutionType>> queryInsTypeList(AdminInstitutionType adminInstitutionType){
        return CustomResponse.success(adminBusTypeService.queryInsTypeList(adminInstitutionType));
    }
}
