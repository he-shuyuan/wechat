package com.ower.dsyz.common.vfs.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.ower.dsyz.common.vfs.FileSystem;
import com.ower.dsyz.common.vfs.MountPoint;
import com.ower.dsyz.common.vfs.provider.local.LocalFileSystem;

@Configuration
@EnableConfigurationProperties(VfsLocalConfig.class)
public class MountPointConfig {

	@Autowired
	VfsLocalConfig vfsLocalConfig;
	@Bean
	public MountPoint localMountPoint(){
		MountPoint localMountPoint = new MountPoint();
		localMountPoint.setTarget(vfsLocalConfig.getTarget());
		localMountPoint.setParameters(vfsLocalConfig.getParameter());
		localMountPoint.setFileSystem(localFileSystem());
		return localMountPoint;
	}
	
	@Bean
	public FileSystem localFileSystem(){
		FileSystem localFileSystem =  new LocalFileSystem();
		localFileSystem.setTarget(vfsLocalConfig.getTarget());
		localFileSystem.setParameters(vfsLocalConfig.getParameter());
		return localFileSystem;
	}
}
