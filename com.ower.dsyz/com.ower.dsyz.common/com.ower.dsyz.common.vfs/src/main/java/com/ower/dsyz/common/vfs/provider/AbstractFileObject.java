/*
 * <p>copyright description</p>
 * @author <a href="mailto:egcs@21cn.com">Dongsheng Song</a>
 * $Id: AbstractFileObject.java 1757 2005-03-23 05:51:35Z FORESEE.fanzhi $
 */

package com.ower.dsyz.common.vfs.provider;

import java.io.IOException;
import java.util.Map;
import com.ower.dsyz.common.vfs.FileContent;
import com.ower.dsyz.common.vfs.FileObject;

/**
 * <p>
 * class description
 * </p>
 * 
 * @author <a href="mailto:egcs@21cn.com">Dongsheng Song </a>
 * @version $Revision: 1757 $
 */
public abstract class AbstractFileObject implements FileObject {

	/** 文件路径. */
	protected String vpath;
	

	/** {@inheritDoc} */
	public FileContent getContent(int mode) throws IOException {
		throw new IOException("Not Implement");
	}

	/** {@inheritDoc} */
	public boolean create() throws IOException {
		throw new IOException("Not Implement");
	}

	/** {@inheritDoc} */
	public boolean mkdir() throws IOException {
		throw new IOException("Not Implement");
	}

	/** {@inheritDoc} */
	public boolean delete() throws IOException {
		throw new IOException("Not Implement");
	}

	/** {@inheritDoc} */
	public boolean isFile() throws IOException {
		throw new IOException("Not Implement");
	}

	/** {@inheritDoc} */
	public boolean isDirectory() throws IOException {
		throw new IOException("Not Implement");
	}

	/** {@inheritDoc} */
	public String getName() {
		throw new Error("Not Implement");
	}

	/** {@inheritDoc} */
	public String getPath() {
		return vpath;
	}

	/** {@inheritDoc} */
	public long getLastModified() throws IOException {
		throw new IOException("Not Implement");
	}

	/** {@inheritDoc} */
	public long getLength() throws IOException {
		throw new IOException("Not Implement");
	}

	/** {@inheritDoc} */
	public Map<String, String> getMetadata() throws IOException {
		throw new IOException("Not Implement");
	}

	/** {@inheritDoc} */
	public void setMetadata(Map<String, String> metadata) throws IOException {
		throw new IOException("Not Implement");
	}

	/** {@inheritDoc} */
	public String[] list() throws IOException {
		throw new IOException("Not Implement");
	}

	/** {@inheritDoc} */
	public boolean exists() throws IOException {
		throw new IOException("Not Implement");
	}

	/** {@inheritDoc} */
	public String getMetadata(String name) throws IOException {
		throw new IOException("Not Implement");
	}

	/** {@inheritDoc} */
	public void setMetadata(String name, String value) throws IOException {
		throw new IOException("Not Implement");
	}

	/** {@inheritDoc} */
	public void copyTo(FileObject target) throws IOException {
		if (target.exists()) {
			if (target.isFile() && isFile()) {
				copyFile(target);
			} else if (target.isDirectory() && isDirectory()) {
				copyDirectory(target);
			} else {
				throw new IOException("source & dest is not same type");
			}
		} else {
			if (isFile()) {
				target.create();
				copyFile(target);
			} else {
				target.mkdir();
				copyDirectory(target);
			}
		}
	}

	/**
	 * 复制文件
	 * 
	 * @param fo
	 *            目标文件对象
	 * @throws IOException
	 *             一般异常
	 */
	private void copyFile(FileObject fo) throws IOException {
		FileContent fc = getContent(FileObject.READ_ONLY), fc2 = (FileContent) fo.getContent(FileObject.READ_WRITE);
		int length = 64 * 1024, n;
		byte[] buf = new byte[length];
		while ((n = fc.read(buf, 0, length)) > 0) {
			fc2.write(buf, 0, n);
		}
		fc.close();
		fc2.close();
	}

	/**
	 * 复制目录
	 * 
	 * @param fo
	 *            目标文件对象
	 */
	private void copyDirectory(FileObject fo) {
		throw new RuntimeException("Not Implement");
	}

	/** {@inheritDoc} */
	public byte[] readFull() throws IOException {
		FileContent fc = getContent(FileObject.READ_ONLY);
		int length = (int) fc.getLength();
		byte[] buf;

		if (length <= 0) {
			buf = new byte[0];
		} else {
			buf = new byte[length];
			fc.read(buf, 0, length);
		}
		fc.close();

		return buf;
	}
}