package com.ower.dsyz.logCenter.service;

import com.ower.dsyz.logCenter.bean.LoggerMessageBody;
import com.ower.dsyz.logCenter.bean.LoggerRestMessage;

/**
 * 
 * @author Administrator
 *
 */
public interface ILogService {

	/**
	 * 记录日志
	 * @param loggerMess
	 * @return
	 */
	Boolean save(LoggerRestMessage<LoggerMessageBody> loggerMess,String ip);
}
