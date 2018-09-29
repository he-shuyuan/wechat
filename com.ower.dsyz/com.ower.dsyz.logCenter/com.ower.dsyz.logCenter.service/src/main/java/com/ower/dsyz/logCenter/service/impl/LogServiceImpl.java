package com.ower.dsyz.logCenter.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ower.dsyz.logCenter.auto.model.LogDetail;
import com.ower.dsyz.logCenter.bean.LoggerMessageBody;
import com.ower.dsyz.logCenter.bean.LoggerRestMessage;
import com.ower.dsyz.logCenter.service.ILogAuthService;
import com.ower.dsyz.logCenter.service.ILogDetailService;
import com.ower.dsyz.logCenter.service.ILogService;

@Service
public class LogServiceImpl implements ILogService{

	@Autowired
	ILogDetailService logDetailService;
	
	@Autowired
	ILogAuthService logAuthService;
	
	@Override
	public Boolean save(LoggerRestMessage<LoggerMessageBody> loggerMess, String ip) {
		LogDetail logDetail = new LogDetail();
		logDetail.setAppName(loggerMess.getHead().getAppName());
		logDetail.setIp(ip);
		logDetail.setClassName(loggerMess.getBody().getClassName());
		logDetail.setBody(loggerMess.getBody().getBody());
		logDetail.setLevel(loggerMess.getBody().getLevel());
		logDetail.setThreadName(loggerMess.getBody().getThreadName());
		logDetail.setTimestamp(loggerMess.getBody().getTimestamp());
		logDetailService.saveLogDetail(logDetail);
		return true;
	}

}
