package com.ower.dsyz.logCenter.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ower.dsyz.logCenter.auto.model.LogAuth;
import com.ower.dsyz.logCenter.manual.dao.LogAuthExtMapper;
import com.ower.dsyz.logCenter.service.ILogAuthService;

@Service
public class LogAuthServiceImpl implements ILogAuthService{

	@Autowired
	LogAuthExtMapper logAuthExtMapper;
	
	@Override
	public List<LogAuth> queryLogAuthByAuth(String ip) {
	     List<String> ipList = new ArrayList<>();
		return logAuthExtMapper.queryLogAuthByAuth(ipList);
	}

}
