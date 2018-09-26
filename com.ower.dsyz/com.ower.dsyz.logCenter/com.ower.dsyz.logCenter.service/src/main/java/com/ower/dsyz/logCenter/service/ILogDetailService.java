package com.ower.dsyz.logCenter.service;

import com.ower.dsyz.logCenter.auto.model.LogDetail;

public interface ILogDetailService {

	/**
	 * 保存日志信息
	 * @param record
	 * @return
	 */
	  int saveLogDetail(LogDetail logDetail); 
}
