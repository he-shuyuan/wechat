/*
 * <p>copyright description</p>
 * @author <a href="mailto:egcs@21cn.com">Dongsheng Song</a>
 * $Id: AbstractFileSystem.java 1757 2005-03-23 05:51:35Z FORESEE.fanzhi $
 */

package com.ower.dsyz.common.vfs.provider;

import java.io.IOException;
import java.util.Map;
import com.ower.dsyz.common.vfs.FileObject;
import com.ower.dsyz.common.vfs.FileSystem;

/**
 * <p>
 * class description
 * </p>
 * 
 * @author <a href="mailto:egcs@21cn.com">Dongsheng Song </a>
 * @version $Revision: 1757 $
 */
public abstract class AbstractFileSystem implements FileSystem {
	
	protected String target;
	protected Map<String, String> parameters;

	public void setTarget(String target) {
		this.target = target;
	}

	protected String getTarget() {
		return target;
	}

	protected Map<String, String> getParameters() {
		return parameters;
	}

	public void setParameters(Map<String, String> parameters) {
		this.parameters = parameters;
	}

	/** {@inheritDoc} */
	public String getName() {
		throw new Error("Not Implement");
	}

	/** {@inheritDoc} */
	public FileObject resolveFile(String filename) throws IOException {
		throw new IOException("Not Implement");
	}
}