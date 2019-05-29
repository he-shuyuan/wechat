/**
 * Copyright(c) Foresee Science & Technology Ltd. 
 */
package com.ower.dsyz.admin.service.impl;

import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ower.dsyz.admin.auto.dao.AdminDepartmentMapper;
import com.ower.dsyz.admin.auto.model.AdminDepartment;
import com.ower.dsyz.admin.manual.constant.AdminConstant;
import com.ower.dsyz.admin.manual.dao.AdminDepartmentExtMapper;
import com.ower.dsyz.admin.manual.dto.AdminDepTreeDTO;
import com.ower.dsyz.admin.manual.dto.AdminDepartmentDTO;
import com.ower.dsyz.admin.manual.dto.DepPath;
import com.ower.dsyz.admin.service.IAdminDepartmentService;
import com.ower.dsyz.common.core.exception.BusinessException;
import com.ower.dsyz.common.core.util.IDUtil;
import com.ower.dsyz.common.core.valid.util.ParamCheckUtil;


/**
 * <pre>
 * TODO。
 * </pre>
 *
 * @author HeShuyuan@163.com
 * @date 2018年7月2日
 * @version 1.00.00
 * 
 *          <pre>
 * 修改记录 
 *    修改后版本:     修改人：  修改日期:     修改内容:
 *          </pre>
 */
@Service
public class AdminDepartmentServiceImpl implements IAdminDepartmentService {

    @Autowired
    AdminDepartmentMapper adminDepartmentMapper;
    
    @Autowired
    AdminDepartmentExtMapper adminDepartmentExtMapper;
    
    @Override
    public AdminDepartment addDepartment(AdminDepartmentDTO adminDepartmentDTO) {
        AdminDepartment adminDepartment = new AdminDepartment();
        BeanUtils.copyProperties(adminDepartmentDTO, adminDepartment);
        if(StringUtils.isBlank(adminDepartmentDTO.getDepId())){
            adminDepartment.setDepId(IDUtil.getUUID());
        }
        DepPath depPath = new DepPath();
        BeanUtils.copyProperties(adminDepartmentDTO, depPath);
        adminDepartment.setPathCode(this.buildPathCode(depPath));
        adminDepartmentMapper.insertSelective(adminDepartment);
        return adminDepartment;
    }

    @Override
    public int delDepartment(AdminDepartment adminDepartment) {

        return adminDepartmentMapper.deleteByPrimaryKey(adminDepartment.getDepId());
    }

    @Override
    public int updataDepartment(AdminDepartmentDTO adminDepartmentDTO) {
        AdminDepartment adminDepartment = new AdminDepartment();
        BeanUtils.copyProperties(adminDepartmentDTO, adminDepartment);
        return adminDepartmentMapper.updateByPrimaryKeySelective(adminDepartment);
    }

    @Override
    public List<AdminDepartmentDTO> queryDepartmentList(AdminDepartmentDTO adminDepartmentDTO) {

        return adminDepartmentExtMapper.queryDepartmentList(adminDepartmentDTO);
    }


    @Override
    public List<DepPath> queryDepPathList(DepPath depPath) {
        
        return adminDepartmentExtMapper.queryDepPathList(depPath);
    }
    
    
    @Override
    public List<AdminDepTreeDTO> queryDepTreeList(AdminDepTreeDTO adminDepTreeDTO) {
        
        return adminDepartmentExtMapper.queryDepTreeList(adminDepTreeDTO);
    }
    
    /**
     * 构造部门路径
     * @param deptPath
     * @return String
     */
    private String buildPathCode(DepPath deptPath){
        //顶级部门 机构null
        if(AdminConstant.DepartmentConstant.DEFAULT_PARENT_DEP_ID.equals(deptPath.getParentDepId())){
            deptPath.setInsId(null);
        }
        List<DepPath> deptPathList = this.queryDepPathList(deptPath);
        if(deptPathList.isEmpty()){
            return deptPath.getPathCode() + "001";
        }
        DepPath deptPathLast = deptPathList.get(deptPathList.size()-1);
        String str = deptPathLast.getPathCode().substring(deptPath.getPathCode().length());
        int lastIndex = 0;
        if(str.length()!=0){
            lastIndex = Integer.parseInt(str);
        }
        int i=1;
        for(;i<=lastIndex;i++){
            str = deptPathList.get(i-1).getPathCode().substring(deptPath.getPathCode().length());
            int tempNum = Integer.parseInt(str);
            if(tempNum == i){
                continue;
            }else{
                break;
            }
        }
        return deptPath.getPathCode() + String.format("%03d", i);
    }

    @Override
    public AdminDepartmentDTO queryAdmimDepartmentById(AdminDepartmentDTO adminDepartmentDTO) {
        ParamCheckUtil.checkEmpty(adminDepartmentDTO.getDepId());
        List<AdminDepartmentDTO> list = this.queryDepartmentList(adminDepartmentDTO);
        if(list.isEmpty()){
            throw new BusinessException("非法部门id");
        }
        return list.get(0);
    }

 
}
