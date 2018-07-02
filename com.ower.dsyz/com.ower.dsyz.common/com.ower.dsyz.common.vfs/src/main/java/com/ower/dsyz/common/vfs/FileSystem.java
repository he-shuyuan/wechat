/*
 * <p>copyright description</p>
 * @author <a href="mailto:egcs@21cn.com">Dongsheng Song</a>
 * $Id: FileSystem.java 1757 2005-03-23 05:51:35Z FORESEE.fanzhi $
 */

package com.ower.dsyz.common.vfs;

import java.io.IOException;
import java.util.Map;


/**
 * <p>
 * 文件系统接口
 * </p>
 * 
 * @author <a href="mailto:egcs@21cn.com">Dongsheng Song </a>
 * @version $Revision: 1757 $
 */
public interface FileSystem {

    /**
	 * 取得文件系统的名称.
	 * 
	 * @return 文件系统名称
	 */
    public String getName();

    /**
	 * 在此文件系统构建文件对象
	 * 
	 * @param filename 文件名,带路径
	 * @return 文件对象
	 * @throws IOException 所有异常
	 */
    public FileObject resolveFile(String filename) throws IOException;

	/**
	 * @param target
	 */
	void setTarget(String target);

	/**
	 * @param parameter
	 */
	void setParameters(Map<String, String> parameter);

}