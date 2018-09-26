package com.ower.dsyz.logCenter.service;

import java.util.List;

import com.ower.dsyz.logCenter.auto.model.LogAuth;

/**
 * 日志权限
 * @author Administrator
 *
 */
public interface ILogAuthService {

    /**
     * 获取权限列表
     * @param ipList
     * @return
     */
	List<LogAuth> queryLogAuthByAuth(String ip);
}
