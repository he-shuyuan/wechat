/*
 * <p>copyright description</p>
 * @author <a href="mailto:egcs@21cn.com">Dongsheng Song</a>
 * $Id: LocalFileSystem.java 1757 2005-03-23 05:51:35Z FORESEE.fanzhi $
 */

package com.ower.dsyz.common.vfs.provider.local;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.ower.dsyz.common.vfs.FileObject;
import com.ower.dsyz.common.vfs.provider.AbstractFileSystem;

/**
 * <p>
 * class description
 * </p>
 * 
 * @author <a href="mailto:egcs@21cn.com">Dongsheng Song </a>
 * @version $Revision: 1757 $
 */
public class LocalFileSystem extends AbstractFileSystem {

    private  Logger log =LoggerFactory.getLogger(this.getClass());
    
	/** 构造函数 */
	public LocalFileSystem() {
	}

	/** {@inheritDoc} */
	public String getName() {
		return "local";
	}

	/** {@inheritDoc} */
	public FileObject resolveFile(String filename) throws IOException {
		String target = this.getTarget();
		if (filename.startsWith(target)) {
			String str = filename.substring(target.length());
			if (log.isDebugEnabled()) {
				log.debug("path: " + this.getParameters().get("path"));
				log.debug("target: " + target);
				log.debug("filename: " + filename);
				log.debug("realname: " + this.getParameters().get("path") + str);
			}
			File file = new File(this.getParameters().get("path"), str);
			if (file.isDirectory() && filename.charAt(filename.length() - 1) != '/') {
				filename = filename + "/";
			}

			LocalFileObject lfo = new LocalFileObject(filename, file);
			return lfo;
		}
		throw new FileNotFoundException(filename);
	}

	public void setParameters(Map<String, String> parameters) {
		super.setParameters(parameters);
		String path = this.getParameters().get("path");
		File root = new File(path);
		try {
			if (root.exists()) {
				return;
			}
			if (!root.mkdirs()) {
				throw new RuntimeException();
			}
		} catch (Exception e) {
			throw new RuntimeException("无法解析本地文件系统路径:" + path);
		}
	}
}