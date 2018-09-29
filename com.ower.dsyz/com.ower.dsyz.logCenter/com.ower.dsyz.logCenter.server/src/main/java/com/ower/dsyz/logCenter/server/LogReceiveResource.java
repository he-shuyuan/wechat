package com.ower.dsyz.logCenter.server;

import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ower.dsyz.common.core.annotation.NotInAspect;
import com.ower.dsyz.common.core.response.CustomResponse;
import com.ower.dsyz.logCenter.bean.LoggerMessageBody;
import com.ower.dsyz.logCenter.bean.LoggerRestMessage;
import com.ower.dsyz.logCenter.service.ILogService;

@RestController
@RequestMapping("logReceiveService")
public class LogReceiveResource {

	@Autowired
	HttpServletRequest request;
	@Autowired
	ILogService logService;

	@NotInAspect
	@RequestMapping("save")
	public CustomResponse<Boolean> save(@RequestBody LoggerRestMessage<LoggerMessageBody> loggerMess) {
		return CustomResponse.success(logService.save(loggerMess, getRealeIp()));
	}

	private String getRealeIp() {
		String ip = request.getHeader("x-forwarded-for");
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("HTTP_CLIENT_IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("HTTP_X_FORWARDED_FOR");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}
		return ip;
	}
}
