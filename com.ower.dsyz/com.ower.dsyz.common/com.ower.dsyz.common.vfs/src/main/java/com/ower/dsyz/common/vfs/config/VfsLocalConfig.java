package com.ower.dsyz.common.vfs.config;

import java.util.Map;

import org.springframework.boot.context.properties.ConfigurationProperties;


@ConfigurationProperties(prefix = VfsLocalConfig.FD, ignoreUnknownFields = false)
public class VfsLocalConfig {

	/** 前缀 */
	public final static String FD = "vfs.local";
	        /**
	        vfs.local.target=/
	        vfs.local.parameter.path=D:/foresee/vfs_home
	        **/
	private String target;
	private Map<String,String> parameter;
    
    public String getTarget() {
        return target;
    }
    
    public void setTarget(String target) {
        this.target = target;
    }
    
    public Map<String, String> getParameter() {
        return parameter;
    }
    
    public void setParameter(Map<String, String> parameter) {
        this.parameter = parameter;
    } 
	
}
