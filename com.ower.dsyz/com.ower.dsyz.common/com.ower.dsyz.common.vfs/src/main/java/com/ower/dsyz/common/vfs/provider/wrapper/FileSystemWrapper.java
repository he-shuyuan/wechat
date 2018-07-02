package com.ower.dsyz.common.vfs.provider.wrapper;

import java.io.IOException;
import java.util.Map;
import com.ower.dsyz.common.vfs.FileObject;
import com.ower.dsyz.common.vfs.FileSystem;

/**
 * 文件系统包装.
 * 
 * @author <a href="mailto:liji@foresee.com.cn">liji</a>
 */
public abstract class FileSystemWrapper implements FileSystem {

	protected FileSystem fileSystem;

	public FileSystemWrapper(FileSystem fileSystem) {
		this.fileSystem = fileSystem;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.foresee.mobile.base.vfs.FileSystem#resolveFile(java.lang.String)
	 */
	@Override
	public FileObject resolveFile(String filename) throws IOException {
		return (FileObject) this.fileSystem.resolveFile(filename);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.foresee.mobile.base.vfs.FileSystem#setTarget(java.lang.String)
	 */
	@Override
	public void setTarget(String target) {
		this.fileSystem.setTarget(target);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.foresee.mobile.base.vfs.FileSystem#setParameters(java.util.Map)
	 */
	@Override
	public void setParameters(Map<String, String> parameter) {
		this.fileSystem.setParameters(parameter);
	}

}
