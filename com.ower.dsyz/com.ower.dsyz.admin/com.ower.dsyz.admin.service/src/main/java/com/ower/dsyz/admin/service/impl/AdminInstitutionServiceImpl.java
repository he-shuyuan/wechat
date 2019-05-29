package com.ower.dsyz.admin.service.impl;

import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.ower.dsyz.admin.auto.dao.AdminInstitutionMapper;
import com.ower.dsyz.admin.auto.model.AdminInstitution;
import com.ower.dsyz.admin.manual.dao.AdminInstitutionExtMapper;
import com.ower.dsyz.admin.manual.dto.AdminInstitutionDTO;
import com.ower.dsyz.admin.service.IAdminInstitutionService;
import com.ower.dsyz.common.core.exception.BusinessException;
import com.ower.dsyz.common.core.util.IDUtil;
import com.ower.dsyz.common.mybatis.database.page.PageQueryResult;
import com.ower.dsyz.common.mybatis.database.page.PageRequestParam;

@Service
public class AdminInstitutionServiceImpl implements IAdminInstitutionService{

	@Autowired
	AdminInstitutionExtMapper adminInstitutionExtMapper;
	
	@Autowired
	AdminInstitutionMapper adminInstitutionMapper;
	
	@Override
	public PageQueryResult<AdminInstitutionDTO> pageQueryAdminInstitutionDTOList(PageRequestParam param) {
		return adminInstitutionExtMapper.pageQueryAdminInstitutionDTOList(param);
	}

    @Override
    @Transactional
    public String addInstitution(AdminInstitutionDTO addInstitutionDTO) {
        //重复校验
        this.checkInstutionRepeat(addInstitutionDTO);
        //新增机构
        AdminInstitution adminInstitution = new AdminInstitution();
        BeanUtils.copyProperties(addInstitutionDTO, adminInstitution);
        if(StringUtils.isBlank(adminInstitution.getInsId())){
           adminInstitution.setInsId(IDUtil.getUUID());
        }
        adminInstitutionMapper.insertSelective(adminInstitution);
       
        return adminInstitution.getInsId();
    }

    @Override
    public int delInstitution(AdminInstitution addInstitution) {
        if(StringUtils.isNotBlank(addInstitution.getInsId())){
            throw new BusinessException("id不能为空");
        }
        return adminInstitutionMapper.deleteByPrimaryKey(addInstitution.getInsId());
    }

    @Override
    public int updataInstitution(AdminInstitution adminInstitution) {
        //重复判断
        AdminInstitutionDTO adminInstitutionDTO = new AdminInstitutionDTO();
        BeanUtils.copyProperties(adminInstitution, adminInstitutionDTO);
        this.checkInstutionRepeat(adminInstitutionDTO);
        
        return adminInstitutionMapper.updateByPrimaryKeySelective(adminInstitution);
    }

    @Override
    public List<AdminInstitutionDTO> queryAdminInstitutionDTOList(AdminInstitutionDTO addInstitutionDTO) {
        
        return adminInstitutionExtMapper.queryAdminInstitutionDTOList(addInstitutionDTO);
    }

    /**
     * 检查企业名称和企业信用代码唯一
     * @param addInstitutionDTO void
     */
    private void checkInstutionRepeat(AdminInstitutionDTO adminInstitutionDTO){
        AdminInstitutionDTO tempDTO = new AdminInstitutionDTO();
       //机构名称
        if(StringUtils.isNotBlank(adminInstitutionDTO.getInsName())){
            tempDTO.setInsName(adminInstitutionDTO.getInsName());
            List<AdminInstitutionDTO> list = this.queryAdminInstitutionDTOList(tempDTO);
            if(!list.isEmpty() 
               && !(StringUtils.isNotBlank(adminInstitutionDTO.getInsId()) 
                       && list.get(0).getInsId().equals(adminInstitutionDTO.getInsId()))){
                throw new BusinessException("机构名称已存在");
            }
        }
        //机构信任新编码
        if(StringUtils.isNotBlank(adminInstitutionDTO.getOrganizationCode())){
            tempDTO = new AdminInstitutionDTO(); 
            tempDTO.setOrganizationCode(adminInstitutionDTO.getOrganizationCode());
            List<AdminInstitutionDTO> list = this.queryAdminInstitutionDTOList(tempDTO);
            if(!list.isEmpty() 
               && !(StringUtils.isNotBlank(adminInstitutionDTO.getInsId()) 
                       && list.get(0).getInsId().equals(adminInstitutionDTO.getInsId()))){
                throw new BusinessException("机构信用编码已存在");
            }
        }
    }

    @Override
    public AdminInstitution queryAdminInstitutionById(String id) {
        
        return adminInstitutionMapper.selectByPrimaryKey(id);
    }
}


