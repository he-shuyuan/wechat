package com.ower.dsyz.gateway.manual.dto;

import java.io.Serializable;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.ower.dsyz.common.base.bean.CustomRequestParam;

public class GatewayRequest implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String appName;
	private String serviceLevel;
	private String serviceName;
	private String serviceMethod;
	private String extMenthod;
	private String url;
	private String token;
	private String appId;
	private String requestId;
	private String sign;
	private String realIp;
	private CustomRequestParam param;
    private List<MultipartFile> files;
    
	public String getAppName() {
		return appName;
	}

	public void setAppName(String appName) {
		this.appName = appName;
	}

	public String getServiceLevel() {
		return serviceLevel;
	}

	public void setServiceLevel(String serviceLevel) {
		this.serviceLevel = serviceLevel;
	}

	public String getServiceName() {
		return serviceName;
	}

	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}

	public String getServiceMethod() {
		return serviceMethod;
	}

	public void setServiceMethod(String serviceMethod) {
		this.serviceMethod = serviceMethod;
	}

	public String getExtMenthod() {
		return extMenthod;
	}

	public void setExtMenthod(String extMenthod) {
		this.extMenthod = extMenthod;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getAppId() {
		return appId;
	}

	public void setAppId(String appId) {
		this.appId = appId;
	}

	public String getRequestId() {
		return requestId;
	}

	public void setRequestId(String requestId) {
		this.requestId = requestId;
	}

	public String getSign() {
		return sign;
	}

	public void setSign(String sign) {
		this.sign = sign;
	}

	public CustomRequestParam getParam() {
		return param;
	}

	public void setParam(CustomRequestParam param) {
		this.param = param;
	}

	
    public String getRealIp() {
        return realIp;
    }

    
    public void setRealIp(String realIp) {
        this.realIp = realIp;
    }

    public List<MultipartFile> getFiles() {
		return files;
	}

	public void setFiles(List<MultipartFile> files) {
		this.files = files;
	}

	@Override
    public String toString() {
        return "GatewayRequest [appName=" + appName + ", serviceLevel=" + serviceLevel + ", serviceName=" + serviceName
                + ", serviceMethod=" + serviceMethod + ", extMenthod=" + extMenthod + ", url=" + url + ", token=" + token + ", appId="
                + appId + ", requestId=" + requestId + ", sign=" + sign + ", realIp=" + realIp + ", param=" + param + "]";
    }

   

}
