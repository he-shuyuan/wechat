package com.ower.dsyz.logCenter.manual.dao;

import java.util.List;

import com.ower.dsyz.logCenter.auto.model.LogAuth;

public interface LogAuthExtMapper {
    
    /**
     * 获取权限列表
     * @param ipList
     * @return
     */
	List<LogAuth> queryLogAuthByAuth(List<String> ipList);
}