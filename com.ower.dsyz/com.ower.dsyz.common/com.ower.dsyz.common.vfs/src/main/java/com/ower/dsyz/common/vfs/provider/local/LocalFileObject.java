/*
 * <p>copyright description</p>
 * @author <a href="mailto:egcs@21cn.com">Dongsheng Song</a>
 * $Id: LocalFileObject.java 1757 2005-03-23 05:51:35Z FORESEE.fanzhi $
 */

package com.ower.dsyz.common.vfs.provider.local;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import com.ower.dsyz.common.vfs.FileContent;
import com.ower.dsyz.common.vfs.FileObject;
import com.ower.dsyz.common.vfs.provider.AbstractFileObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
/**
 * <p>
 * class description
 * </p>
 * 
 * @author <a href="mailto:egcs@21cn.com">Dongsheng Song </a>
 * @version $Revision: 1757 $
 */
public class LocalFileObject extends AbstractFileObject {

    private  Logger log =LoggerFactory.getLogger(this.getClass());
	
    /** 文件对象. */
	private File file;

	/**
	 * 构造函数.
	 * 
	 * @param fs
	 *            文件系统
	 * @param vpath
	 *            文件名
	 * @param file
	 *            文件对象
	 */
	public LocalFileObject(String vpath, File file) {
		this.vpath = vpath;
		this.file = file;
	}

	/** {@inheritDoc} */
	public boolean create() throws IOException {
		if (file.getParentFile().exists() || file.getParentFile().mkdirs()) {
			return file.createNewFile();
		} else {
			return false;
		}
	}

	/** {@inheritDoc} */
	public boolean mkdir() throws IOException {
		if (vpath.charAt(vpath.length() - 1) != '/') {
			vpath += "/";
		}

		return file.mkdirs();
	}

	/** {@inheritDoc} */
	public boolean delete() throws IOException {
		if (file.isFile()) {
			//本地文件系统没有保存元数据
//			metadataService.removeMetadata(this.vpath);
			return file.delete();
		} else {
//			metadataService.removeMetadata(this.vpath + "%");
		}
		ArrayList<File> stack = new ArrayList<File>();
		stack.add(file);
		while (stack.size() > 0) {
			File t = (File) stack.remove(stack.size() - 1);
			File[] list = t.listFiles();
			if ((list != null) && (list.length > 0)) {
				stack.add(t);
				for (int i = 0; i < list.length; i++) {
					if (list[i].isFile()) {
						if (!list[i].delete()) {
							log.info("未能删除文件:" + list[i].getName());
						}
					} else {
						stack.add(list[i]);
					}
				}
			} else {
				boolean rc = t.delete();
				if (!rc) {
					return false;
				}
			}
		}

		return true;
	}

	/** {@inheritDoc} */
	public boolean isFile() throws IOException {
		return file.isFile();
	}

	/** {@inheritDoc} */
	public boolean isDirectory() throws IOException {
		return file.isDirectory();
	}

	/** {@inheritDoc} */
	public String getName() {
		return file.getName();
	}

	/** {@inheritDoc} */
	public long getLastModified() throws IOException {
		return file.lastModified();
	}

	/** {@inheritDoc} */
	public long getLength() throws IOException {
		return file.length();
	}

	/** {@inheritDoc} */
	public String[] list() throws IOException {
		File[] files = file.listFiles();
		if (files == null) {
			return new String[0];
		}

		if (log.isDebugEnabled()) {
			log.debug("Before ACL.BROWSE filter size: " + files.length);
		}
		ArrayList<String> al = new ArrayList<String>(files.length);
		for (int i = 0; i < files.length; i++) {
			String target = vpath + files[i].getName() + (files[i].isDirectory() ? "/" : "");
			al.add(target);
		}
		if (log.isDebugEnabled()) {
			log.debug("After ACL.BROWSE filter size: " + al.size());
		}

		String[] rs = new String[al.size()];
		al.toArray(rs);

		return rs;
	}

	/** {@inheritDoc} */
	public boolean exists() throws IOException {
		return file.exists();
	}

	/** {@inheritDoc} */
	public FileContent getContent(int mode) throws IOException {
		return new LocalFileContent(file, mode);
	}

	@Override
	public void copyTo(FileObject target) throws IOException {
		// TODO Auto-generated method stub
		
	}

}