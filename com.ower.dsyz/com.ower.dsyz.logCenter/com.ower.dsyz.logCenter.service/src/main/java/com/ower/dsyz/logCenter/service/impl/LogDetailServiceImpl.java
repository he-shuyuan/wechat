package com.ower.dsyz.logCenter.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ower.dsyz.common.core.util.IDUtil;
import com.ower.dsyz.logCenter.auto.dao.LogDetailMapper;
import com.ower.dsyz.logCenter.auto.model.LogDetail;
import com.ower.dsyz.logCenter.service.ILogDetailService;

@Service
public class LogDetailServiceImpl implements ILogDetailService{

	@Autowired
	LogDetailMapper logDetailMapper;
	
	@Override
	public int saveLogDetail(LogDetail logDetail) {
		logDetail.setId(IDUtil.getUUID());
		return logDetailMapper.insertSelective(logDetail);
	}

}
