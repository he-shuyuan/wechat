package com.ower.dsyz.admin.service;

import java.util.List;
import com.ower.dsyz.admin.auto.model.AdminInstitution;
import com.ower.dsyz.admin.manual.dto.AdminInstitutionDTO;
import com.ower.dsyz.common.core.page.PageQueryResult;
import com.ower.dsyz.common.core.page.PageRequestParam;

/**
 * 机构增删改查
 * @author 独树一帜
 *
 */
public interface IAdminInstitutionService {
   
	/**
	 * 分页查找机构
	 * @param adminInstitutionDTO
	 * @return
	 */
	PageQueryResult<AdminInstitutionDTO> pageQueryAdminInstitutionDTOList(PageRequestParam param);
	
	
	/**
	 * 添加企业
	 * @param addInstitution
	 * @return String
	 */
	String addInstitution(AdminInstitutionDTO addInstitutionDTO);
	
	
	/**
     * 删除企业
     * @param addInstitution
     * @return String
     */
    int delInstitution(AdminInstitution addInstitution);
    
    
    
    /**
     * 更新企业
     * TODO。
     * @param adminInstitution
     * @return int
     */
    int updataInstitution(AdminInstitution adminInstitution);
    
    
    /**
     * 条件查询企业
     * TODO。
     * @param addInstitutionDTO
     * @return List<AdminInstitutionDTO>
     */
    List<AdminInstitutionDTO> queryAdminInstitutionDTOList(AdminInstitutionDTO addInstitutionDTO);
    
    /**
     * 通过id获取机构
     * TODO。
     * @param id
     * @return AdminInstitution
     */
    AdminInstitution queryAdminInstitutionById(String id);
}
